package main;

import java.util.ArrayList;
import java.util.List;

import controller.DatabaseController;
import controller.HashFileController;
import controller.HashFileController.Sheets;
import hashing.DividingTheRemainingHashing;
import hashing.FoldingHashing;
import hashing.MidSquareHashing;

public class Main
{
    //controllers
    DatabaseController databaseController = new DatabaseController();
    HashFileController hashFileController = new HashFileController();

    // hashings
    DividingTheRemainingHashing dividingTheRemainingHashing = new DividingTheRemainingHashing();
    MidSquareHashing midSquareHashing = new MidSquareHashing();
    FoldingHashing foldingHashing = new FoldingHashing();

    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        databaseController.createRandomTextDataBase(10);
        hashFileController.createWorkBook("output.xls");

        List<Student> studentList = databaseController.readFromFile();
        for(Student student: studentList)
        {
            dividingTheRemainingHashing.addWithDiscreteLeash(student);
            dividingTheRemainingHashing.addWithLinearProbe(student);

            midSquareHashing.addWithDiscreteLeash(student);
            midSquareHashing.addWithLinearProbe(student);

            foldingHashing.addWithDiscreteLeash(student);
            foldingHashing.addWithLinearProbe(student);
        }


        //readFromFile();
        // createHashTable(); //Taslak Hash Table
    }


    public int sizeOfNumber(Integer number)
    {
        return String.valueOf(number).length();
    }

}








