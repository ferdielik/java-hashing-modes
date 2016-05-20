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

    public enum Process
    {
        READ,
        WRITE
    }

    private static int DATA_LENGTH = 1511;
    private HashFileController hashFileController = new HashFileController();

    public void save(HashMode hashMode, ConflictMode conflictMode, Student student)
    {
        Integer index = findIndex(hashMode, conflictMode, Process.WRITE, student.getId());
        String fileName = getFileName(hashMode, conflictMode);
        hashFileController.save(fileName, index, student);
    }

    public Student get(HashMode hashMode, ConflictMode conflictMode, Integer studentNumber)
    {
        Integer index = findIndex(hashMode, conflictMode, Process.READ, studentNumber);
        String fileName = getFileName(hashMode, conflictMode);

        if (hashFileController.getStudent(fileName, index) == null)
        {
            return new Student();
        }

        Student student = hashFileController.getStudent(fileName, index);

        System.out.print("founded index :" + index + " student number : " + studentNumber + " student : " + student + "  filename : " + fileName);
        System.out.println(" correct result : " + student.getId().equals(studentNumber));
        return student;
    }

    public Integer findIndex(HashMode hashMode, ConflictMode conflictMode, Process process,  Integer number)
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
        String fileName = getFileName(hashMode, conflictMode);
        return fixConflict(fileName,  conflictMode, process, index , number);
    }

    private Integer fixConflict(String fileName, ConflictMode conflictMode, Process process, Integer index, Integer studentNumber)
    {
//        System.out.println("pre conflict : index : " + index + " student number : " + studentNumber + " exist : " + existStudents(fileName, index));
        if (existStudents(fileName, index))
        {
            if(Process.READ.equals(process) && isCorrectResult(fileName, index, studentNumber))
            {
                return index;
            }

            index++;

            if (ConflictMode.DISCRETE_OVERFLOW.equals(conflictMode) && index < 1000)
            {
                index = 1000;
            }
            return fixConflict(fileName, conflictMode, process, index, studentNumber);

        }

        return index;
    }

    private Integer dividingTheRemainingFindIndex(Integer number)
    {
        return number % DATA_LENGTH;
    }

    private Integer midSquareFindIndex(Integer number)
    {
        /**
         * kare ortasi
         * http://www.brpreiss.com/books/opus5/html/page214.html
         */

        int k = 10; // M==1024
        int w = 32;

        Integer hashed = (number * number) >>> (w - k);
        return hashed % DATA_LENGTH;
    }

    private Integer foldingFindIndex(Integer number)
    {
        /**
         * 399630850
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


    private boolean existStudents(String fileName, Integer index)
    {
        return hashFileController.isExist(fileName, index);
    }

    private boolean isCorrectResult(String fileName, Integer index, Integer studentNumber)
    {
        Student student = hashFileController.getStudent(fileName, index);
//        System.out.println("isCorrectResult : index " + index + "student number " + studentNumber + "    : " + studentNumber.equals(student.getId()));
        return studentNumber.equals(student.getId());
    }

    private String getFileName(HashMode hashMode, ConflictMode conflictMode)
    {
        return hashMode.name().toLowerCase() + "_" + conflictMode.name().toLowerCase() + ".txt";
    }

    private Integer reverse(String number)
    {
        StringBuffer numberText = new StringBuffer(number);
        return Integer.valueOf(numberText.reverse().toString());
    }
}
