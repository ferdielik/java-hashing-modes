package hashing;

import controller.HashFileController;
import main.Student;


public class Hashing
{
    public enum ConflictMode
    {
        DISCRETE_OVERFLOW,
        LINEAR_PROBE
    }

    public enum HashMode
    {
        FOLDING,
        MID_SQUARE,
        DIVIDING_THE_REMAINING
    }

    private static int DATA_LENGTH = 1511;
    private HashFileController hashFileController = new HashFileController();

    public void save(HashMode hashMode, ConflictMode conflictMode, Student student)
    {
        Integer index = findIndexForSave(hashMode, conflictMode, student.getId());
        String fileName = getFileName(hashMode, conflictMode);
        hashFileController.save(fileName, index, student);
    }
    private Integer firstNumber = 0;
    public Student get(HashMode hashMode, ConflictMode conflictMode, Integer studentNumber, Boolean hashEnable)
    {
//        System.out.println("found index  " + studentNumber);
        Integer index = studentNumber;
        if(hashEnable)
        {
            index = findIndex(hashMode, studentNumber);
            firstNumber = studentNumber;

        }

        String fileName = getFileName(hashMode, conflictMode);
        if(hashFileController.getStudent(fileName,index) == null)
        {
            return new Student();
        }
        Student studentt = hashFileController.getStudent(fileName,index);
        System.out.println(studentt.toString());

        if(firstNumber.equals(studentt.getId()))
        {
//            System.out.println("          path : " + getFileName(hashMode, conflictMode) + "  " + index);
            return hashFileController.getStudent(fileName, index);
        }
        Integer newIndex = index + 1;
        if(ConflictMode.DISCRETE_OVERFLOW.equals(conflictMode) && index < 1000)
        {
            newIndex = 1000;
        }
        System.out.println("naew index  " + newIndex);
        return get(hashMode, conflictMode, newIndex, false);
    }

    public Integer findIndex(HashMode hashMode, Integer number)
    {
        if (HashMode.DIVIDING_THE_REMAINING.equals(hashMode))
        {
            return dividingTheRemainingFindIndex(number);
        }
        else if (HashMode.MID_SQUARE.equals(hashMode))
        {
            return midSquareFindIndex(number);
        }
        else
        {
            return foldingFindIndex(number);
        }
    }

    private Integer findIndexForOverflow(HashMode hashMode, Integer number, Boolean hashEnable)
    {
        Integer index = number;
        if (hashEnable)
        {
            index = findIndex(hashMode, number);
        }

        if (existStudents(hashMode, ConflictMode.DISCRETE_OVERFLOW, index))
        {
            if (index < 1000)
            {
                findIndexForOverflow(hashMode, 1000, false);
            }
            else
            {
                findIndexForOverflow(hashMode, index + 1, false);
            }
        }
        return index;
    }


    public Integer findIndexForSave(HashMode hashMode, ConflictMode conflictMode, Integer number)
    {
        if (ConflictMode.DISCRETE_OVERFLOW.equals(conflictMode))
        {
            return findIndexForOverflow(hashMode, number, true);
        }
        else
        {
            return findIndexForLinear(hashMode, number, true);
        }

    }

    private Integer findIndexForLinear(HashMode hashMode, Integer number, Boolean hashEnable)
    {
        Integer index = number;
        if (hashEnable)
        {
            index = findIndex(hashMode, number);
        }

        if (existStudents(hashMode, ConflictMode.LINEAR_PROBE, index))
        {
            System.out.println("cakisti new id " + number);
            return findIndexForLinear(hashMode, index + 1, false);
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
        Integer num1 = reverse(numberText.substring(0,3));
        Integer num2 = Integer.valueOf(numberText.substring(3,6));
        Integer num3 = reverse(numberText.substring(6,9));

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

    private String getFileName(HashMode hashMode, ConflictMode conflictMode)
    {
        return hashMode.name().toLowerCase() + "_" + conflictMode.name().toLowerCase() + ".txt";
    }
}
