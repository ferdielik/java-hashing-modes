package hashing;

import main.Student;

/**
     hash dosyalarının total boyutu 1217 olsun , asal sayı kullanılması daha iyi.
     (tüm fonksiyonlar 1217 ye göre dizayn))
 */

public class DividingTheRemainingHashing implements Hashing
{
    private static int DATA_LENGTH = 1217;

    @Override
    public void addWithLinearProbe(Student student)
    {

    }

    @Override
    public void addWithDiscreteLeash(Student student)
    {

    }

    @Override
    public void searchWithDividingTheRemaining()
    {

    }

    private int hashDivision(int code)
    {
        return code % DATA_LENGTH;
    }
}
