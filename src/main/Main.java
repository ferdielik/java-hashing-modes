package main;


import java.util.List;

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
//        hashFileController.createWorkBook();
//        databaseController.createRandomTextDataBase(100);
//        buildHashFiles();

        // 513153387;cysvcuezux;yhcgsoqjrl -- ornek ogrenci

        System.out.println(dividingTheRemainingHashing.getStudentLinear(513153387).toString());
        System.out.println(midSquareHashing.getStudentLinear(513153387));
        System.out.println(foldingHashing.getStudentLinear(513153387));


    }

    private void buildHashFiles()
    {
        List<Student> studentList = databaseController.readFromFile();
        for(Student student: studentList)
        {
//            dividingTheRemainingHashing.addWithDiscreteLeash(student);
            dividingTheRemainingHashing.addWithLinearProbe(student);

//            midSquareHashing.addWithDiscreteLeash(student);
            midSquareHashing.addWithLinearProbe(student);

//            foldingHashing.addWithDiscreteLeash(student);
            foldingHashing.addWithLinearProbe(student);
        }
    }

    public int sizeOfNumber(Integer number)
    {
        return String.valueOf(number).length();
    }

}








