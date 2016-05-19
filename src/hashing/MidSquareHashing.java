package hashing;

import controller.HashFileController;
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
        hashFileController.addCell(index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {

    }

    @Override
    public void searchWithDividingTheRemaining()
    {

    }

    private Long findIndex(int code)
    {
        /*
        son 5 hanesinin karesinin orta 3 hanesi
         */
        Long lastFive = code % 100000L;
        Long root = lastFive * lastFive;

        String rootText = String.valueOf(root);
        System.out.println(rootText);
        int a = (rootText.length() / 2) - 2;
        String ortasi = rootText.substring(a, a + 3);
        return Long.valueOf(ortasi);
    }
}
