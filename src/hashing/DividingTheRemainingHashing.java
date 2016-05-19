package hashing;

import controller.HashFileController;
import controller.HashFileController.HashMode;
import main.Student;

/**
 * hash dosyalarının total boyutu 1217 olsun , asal sayı kullanılması daha iyi.
 * (tüm fonksiyonlar 1217 ye göre dizayn))
 */

public class DividingTheRemainingHashing implements Hashing
{
    private static int DATA_LENGTH = 1217;
    private HashFileController hashFileController = new HashFileController();

    @Override
    public void addWithLinearProbe(Student student)
    {
        Integer index = findLinearIndex(student.getId());
        hashFileController.save(HashMode.dividingTheRemainingLinear, index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {
        Integer index = findIndex(student.getId());
        hashFileController.save(HashMode.dividingTheRemaining, index, student);
    }

    @Override
    public Student getStudent(Integer studentNumber)
    {
        Integer index = findIndex(studentNumber);
        return hashFileController.getStudent(HashMode.dividingTheRemaining, index);
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

    private Integer findLinearIndex(int code)
    {
        Integer index = findIndex(code);
        if (existStudentsLinear(index))
        {
            System.out.println("Cakisti : " + index);
            return findLinearIndex(code + 1);
        }
        return index;
    }

    private Integer findIndex(int code)
    {
        return code % DATA_LENGTH;
    }
}
