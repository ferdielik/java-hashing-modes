package main;

import controller.DatabaseController;
import controller.HashFileController;
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
        databaseController.createRandomTextDataBase(500);

        //readFromFile();
        // createHashTable(); //Taslak Hash Table
    }


    public int sizeOfNumber(Integer number)
    {
        return String.valueOf(number).length();
    }

}








