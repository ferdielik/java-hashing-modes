package hashing;

import controller.HashFileController;
import controller.HashFileController.HashMode;
import main.Student;

/**
 * Created by ferdielik on 19/05/16.
 */
public class FoldingHashing implements Hashing
{
    private static int DATA_LENGTH = 1217;
    HashFileController hashFileController = new HashFileController();

    @Override
    public void addWithLinearProbe(Student student)
    {
        Integer index = findLinearIndex(student.getId());
        // cakisma kontrolleri eklenmeli
        hashFileController.save(HashMode.foldingLinear, index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {
        Integer index = findIndex(student.getId());
        // cakisma kontrolleri eklenmeli
        hashFileController.save(HashMode.folding, index, student);
    }

    @Override
    public Student getStudent(Integer studentNumber)
    {
        Integer index = findIndex(studentNumber);
        return hashFileController.getStudent(HashMode.folding, index);
    }

    @Override
    public boolean existStudents(Integer index)
    {
        return hashFileController.isExist(HashMode.midSquare, index);
    }

    @Override
    public boolean existStudentsLinear(Integer index)
    {
        return hashFileController.isExist(HashMode.midSquareLinear, index);
    }

    //    public static void main(String[] argvs)
    //    {
    //        new FoldingHashing();
    //    }
    //
    //    public FoldingHashing()
    //    {
    //        System.out.println(findIndex(123456789));
    //    }

    private Integer findLinearIndex(int code)
    {
        Integer index = findIndex(code);
        if (existStudentsLinear(index))
        {
            return findLinearIndex(code + 1);
        }
        return index;
    }

    private Integer findIndex(int code)
    {
        int num1, num2, num3;
        int result = 0;

        num1 = code % 1000;

        result += reverseNumber(num1);
        num2 = (code % 1000000 - num1) / 1000;
        result += num2;
        num3 = (code - code % 1000000) / 1000000;
        result += reverseNumber(num3);

        return result % DATA_LENGTH;
    }

    private int reverseNumber(int number)
    {
        StringBuffer numberText = new StringBuffer(String.valueOf(number));
        String reversedNumber = numberText.reverse().toString();
        return Integer.valueOf(reversedNumber);
    }
}
