package hashing;

import controller.HashFileController;
import controller.HashFileController.HashModes;
import main.Student;

/**
 * Created by ferdielik on 19/05/16.
 */
public class MidSquareHashing implements Hashing
{

    HashFileController hashFileController = new HashFileController();

    @Override
    public void addWithLinearProbe(Student student)
    {
        Long index = findIndex(student.getId());
        hashFileController.save(HashModes.midSquareLinear, index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {
        Long index = findIndex(student.getId());
        hashFileController.save(HashModes.midSquare, index, student);
    }

    @Override
    public Student getStudent(Integer studentNumber)
    {
        Long index = findIndex(studentNumber);
        return hashFileController.getStudent(HashModes.midSquare, index);
    }
//
//    public static void main(String[] argvs)
//    {
//        new MidSquareHashing();
//    }
//
//    public MidSquareHashing()
//    {
//        System.out.println(findDeneme(123456789));
//        System.out.println(findIndex(123456789));
//    }

    private int findDeneme(int x)
    {
        /**
         * http://www.brpreiss.com/books/opus5/html/page214.html
         */

        int k = 10; // M==1024
        int w = 32;

        return (x * x) >>> (w - k);
    }

    private Long findIndex(int code)
    {
        /*
        son 5 hanesinin karesinin orta 3 hanesi
         */
        Long lastFive = code % 100000L;
        Long root = lastFive * lastFive;

        String rootText = String.valueOf(root);
        //        System.out.println(rootText);
        int a = (rootText.length() / 2) - 2;
        String ortasi = rootText.substring(a, a + 3);
        return Long.valueOf(ortasi);
    }
}
