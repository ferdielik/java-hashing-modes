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

    public Student get(HashMode hashMode, ConflictMode conflictMode, Integer studentNumber)
    {
        Integer index = findIndex(hashMode, studentNumber);
        String fileName = getFileName(hashMode, conflictMode);
        return hashFileController.getStudent(fileName, index);
    }

    private Integer findIndex(HashMode hashMode, Integer number)
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

    private Integer findIndexForOverflow(HashMode hashMode, Integer number)
    {
        Integer index = findIndex(hashMode, number);
        if (existStudents(hashMode, ConflictMode.DISCRETE_OVERFLOW, index))
        {
            if (number < 1000)
            {
                findIndexForOverflow(hashMode, 1000);
            }
            else
            {
                findIndexForOverflow(hashMode, number + 1);
            }
        }
        return index;
    }

    private Integer findIndexForSave(HashMode hashMode, ConflictMode conflictMode, Integer number)
    {
        if (ConflictMode.DISCRETE_OVERFLOW.equals(conflictMode))
        {
            return findIndexForOverflow(hashMode, number);
        }
        else
        {
            return findIndexForLinear(hashMode, number);
        }

    }

    private Integer findIndexForLinear(HashMode hashMode, Integer number)
    {
        Integer index = findIndex(hashMode, number);
        if (existStudents(hashMode, ConflictMode.LINEAR_PROBE, index))
        {
            return findIndexForLinear(hashMode, number + 1);
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
        int num1, num2, num3;
        int result = 0;

        num1 = number % 1000;

        result += reverseNumber(num1);
        num2 = (number % 1000000 - num1) / 1000;
        result += num2;
        num3 = (number - number % 1000000) / 1000000;
        result += reverseNumber(num3);

        return result % DATA_LENGTH;
    }

    private Integer dividingTheRemainingFindIndex(Integer number)
    {
        return number % DATA_LENGTH;
    }


    private Integer reverseNumber(int number)
    {
        StringBuffer numberText = new StringBuffer(String.valueOf(number));
        String reversedNumber = numberText.reverse().toString();
        return Integer.valueOf(reversedNumber);
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
