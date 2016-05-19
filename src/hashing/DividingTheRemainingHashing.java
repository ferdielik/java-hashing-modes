package hashing;

import controller.HashFileController;
import controller.HashFileController.Sheets;
import main.Student;

/**
     hash dosyalarının total boyutu 1217 olsun , asal sayı kullanılması daha iyi.
     (tüm fonksiyonlar 1217 ye göre dizayn))
 */

public class DividingTheRemainingHashing implements Hashing
{
    private static int DATA_LENGTH = 1217;
    private HashFileController hashFileController = new HashFileController();

    @Override
    public void addWithLinearProbe(Student student)
    {
        Long index = findIndex(student.getId());
        hashFileController.addCell(Sheets.dividingTheRemainingLinear, index, student);
    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {
        Long index = findIndex(student.getId());
        hashFileController.addCell(Sheets.dividingTheRemaining, index, student);
    }

    @Override
    public void searchWithDividingTheRemaining()
    {

    }

    private Long findIndex(int code)
    {
        return Long.valueOf(code % DATA_LENGTH);
    }
}
