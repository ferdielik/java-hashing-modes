package hashing;

import controller.HashFileController;
import controller.HashFileController.HashMode;
import main.Student;

/**
 * Created by ferdielik on 19/05/16.
 */
public class MidSquareHashing implements Hashing
{
    private static int DATA_LENGTH = 1217;
    HashFileController hashFileController = new HashFileController();

    @Override
    public void addWithLinearProbe(Student student)
    {
        Integer index = findLinearIndex(student.getId());
        hashFileController.save(HashMode.midSquareLinear, index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {
        Integer index = findIndex(student.getId());
        hashFileController.save(HashMode.midSquare, index, student);
    }

    @Override
    public Student getStudent(Integer studentNumber)
    {
        Integer index = findIndex(studentNumber);
        return hashFileController.getStudent(HashMode.midSquare, index);
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

    @Override
    public Student getStudentLinear(Integer studentNumber)
    {
        Integer index = findLinearIndex(studentNumber);
        return hashFileController.getStudent(HashMode.midSquareLinear, index);
    }


    private Integer findLinearIndex(int code)
    {
        Integer index = findIndex(code);
        if (existStudentsLinear(index))
        {
            System.out.println("sended code :" + code + 1);
            return findLinearIndex(code + 1);
        }
        return index;
    }

    private Integer findIndex(int code)
    {
        /*
        son 5 hanesinin karesinin orta 3 hanesi
         */
        Long lastFive = code % 100000L;
        Long root = lastFive * lastFive;

        String rootText = String.valueOf(root);
        System.out.println("roottext = " + rootText + "   code: " + code);
        int a = (rootText.length() / 2) - 2;
        String ortasi = rootText.substring(a, a + 3);
        return Integer.valueOf(ortasi) % DATA_LENGTH;
    }
}
