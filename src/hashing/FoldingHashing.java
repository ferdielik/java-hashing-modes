package hashing;

import controller.HashFileController;
import controller.HashFileController.HashModes;
import main.Student;

/**
 * Created by ferdielik on 19/05/16.
 */
public class FoldingHashing implements Hashing
{
    HashFileController hashFileController = new HashFileController();

    @Override
    public void addWithLinearProbe(Student student)
    {
        Long index = findIndex(student.getId());
        // cakisma kontrolleri eklenmeli
        hashFileController.save(HashModes.foldingLinear, index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {
        Long index = findIndex(student.getId());
        // cakisma kontrolleri eklenmeli
        hashFileController.save(HashModes.folding, index, student);
    }

    @Override
    public Student getStudent(Integer studentNumber)
    {
        Long index = findIndex(studentNumber);
        return hashFileController.getStudent(HashModes.folding, index);
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

    private Long findIndex(int code)
    {
        int num1, num2, num3;
        int result = 0;

        num1 = code % 1000;

        result += reverseNumber(num1);
        num2 = (code % 1000000 - num1) / 1000;
        result += num2;
        num3 = (code - code % 1000000) / 1000000;
        result += reverseNumber(num3);
        return Long.valueOf(result);
    }

    private int reverseNumber(int number)
    {
        StringBuffer numberText = new StringBuffer(String.valueOf(number));
        String reversedNumber = numberText.reverse().toString();
        return Integer.valueOf(reversedNumber);
    }
}
