package hashing;

import controller.HashFileController;
import main.Student;


public class Hashing
{
    public enum ConflictMode
    {
        DISCRETE_OVERFLOW,
        LINEAR_PROBE,
        NONE
    }

    public enum HashMode
    {
        FOLDING,
        MID_SQUARE,
        DIVIDING_THE_REMAINING,
        NONE
    }

    private static int DATA_LENGTH = 1511;
    private HashFileController hashFileController = new HashFileController();

    public void save(HashMode hashMode, ConflictMode conflictMode, Student student)
    {
        Integer index = findIndex(hashMode, conflictMode, student.getId());
        String fileName = getFileName(hashMode, conflictMode);
        hashFileController.save(fileName, index, student);
    }

    public Student get(HashMode hashMode, ConflictMode conflictMode, Integer studentNumber)
    {
        Integer index = findIndex(hashMode, ConflictMode.NONE, studentNumber);
        String fileName = getFileName(hashMode, conflictMode);

        if (hashFileController.getStudent(fileName, index) == null)
        {
            return new Student();
        }

        return hashFileController.getStudent(fileName, index);
    }

    public Integer findIndex(HashMode hashMode, ConflictMode conflictMode, Integer number)
    {
        Integer index = -1;
        if (HashMode.DIVIDING_THE_REMAINING.equals(hashMode))
        {
            index = dividingTheRemainingFindIndex(number);
        }
        else if (HashMode.MID_SQUARE.equals(hashMode))
        {
            index = midSquareFindIndex(number);
        }
        else if (HashMode.FOLDING.equals(hashMode))
        {
            index = foldingFindIndex(number);
        }

        return fixConflict(hashMode, conflictMode, index);
    }

    private Integer fixConflict(HashMode hashMode, ConflictMode conflictMode, Integer index)
    {
        if (existStudents(hashMode, conflictMode, index))
        {
            index++;

            if (ConflictMode.DISCRETE_OVERFLOW.equals(conflictMode) && index < 1000)
            {
                index = 1000;
            }

            if (ConflictMode.NONE.equals(conflictMode) && isCorrectResult(hashMode, conflictMode, index))
            {
                return index;
            }

            return fixConflict(HashMode.NONE, conflictMode, index);
        }

        return index;
    }

    private Integer midSquareFindIndex(Integer number)
    {
        /*
        son 5 hanesinin karesinin orta 3 hanesi
         */
        Long lastFive = number % 100000L;
        Long root = lastFive * lastFive;

        String rootText = String.valueOf(root);
        int a = (rootText.length() / 2) - 2;
        String ortasi = rootText.substring(a, a + 3);
        return Integer.valueOf(ortasi) % DATA_LENGTH;
    }

    private Integer foldingFindIndex(Integer number)
    {
        /**
         *
         * 492715066
         * num1 = 066
         * result = 660
         *
         * num2 715
         * result = 715 + 660
         *
         * num3 492
         * result = 294 + 715 + 660
         *
         */

        StringBuffer numberText = new StringBuffer(String.valueOf(number));
        Integer num1 = reverse(numberText.substring(0, 3));
        Integer num2 = Integer.valueOf(numberText.substring(3, 6));
        Integer num3 = reverse(numberText.substring(6, 9));

        //        System.out.println(num1 + "  " + num2 + "  " + num3);
        return (num1 + num2 + num3) % DATA_LENGTH;
    }

    private Integer dividingTheRemainingFindIndex(Integer number)
    {
        return number % DATA_LENGTH;
    }

    private Integer reverse(String number)
    {
        StringBuffer numberText = new StringBuffer(number);
        return Integer.valueOf(numberText.reverse().toString());
    }

    private boolean existStudents(HashMode hashMode, ConflictMode conflictMode, Integer index)
    {
        String fileName = getFileName(hashMode, conflictMode);
        return hashFileController.isExist(fileName, index);
    }

    private boolean isCorrectResult(HashMode hashMode, ConflictMode conflictMode, Integer index)
    {
        String fileName = getFileName(hashMode, conflictMode);
        Student student = hashFileController.getStudent(fileName, index);
        return index.equals(student.getId());
    }

    private String getFileName(HashMode hashMode, ConflictMode conflictMode)
    {
        return hashMode.name().toLowerCase() + "_" + conflictMode.name().toLowerCase() + ".txt";
    }
}
