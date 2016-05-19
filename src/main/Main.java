package main;


import java.util.List;

import controller.DatabaseController;
import controller.HashFileController;
import hashing.Hashing;
import hashing.Hashing.ConflictMode;
import hashing.Hashing.HashMode;

public class Main
{
    //controllers
    DatabaseController databaseController = new DatabaseController();
    HashFileController hashFileController = new HashFileController();
    Hashing hashing = new Hashing();
    // hashings

    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        hashFileController.createWorkBook();
        databaseController.createRandomTextDataBase(100);
        buildHashFiles();

        // 255415565;lluoktzwgs;fsidukrpff -- ornek ogrenci

//        System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW, 255415565).toString());
//        System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE, 255415565).toString());
//        System.out.println(hashing.get(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW,255415565).toString());
//        System.out.println(hashing.get(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE,255415565).toString());
//        System.out.println(hashing.get(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW,255415565).toString());
//        System.out.println(hashing.get(HashMode.FOLDING, ConflictMode.LINEAR_PROBE,255415565).toString());


    }

    private void buildHashFiles()
    {
        List<Student> studentList = databaseController.readFromFile();
        for (Student student : studentList)
        {
            //            dividingTheRemainingHashing.addWithDiscreteOverflow(student);
            hashing.save(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW, student);
            hashing.save(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE, student);

            //            midSquareHashing.addWithDiscreteOverflow(student);
            hashing.save(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW, student);
            hashing.save(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE, student);

            //            foldingHashing.addWithDiscreteOverflow(student);
            hashing.save(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW, student);
            hashing.save(HashMode.FOLDING, ConflictMode.LINEAR_PROBE, student);
        }
    }

    public int sizeOfNumber(Integer number)
    {
        return String.valueOf(number).length();
    }

}








