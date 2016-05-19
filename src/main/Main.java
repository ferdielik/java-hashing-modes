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
    HashFileController hashFileController= new HashFileController();
    Hashing hashing = new Hashing();
    // hashings

    public static void main(String[] args)
    {
        new Main();
    }

    private Long test(HashMode hashMode, ConflictMode conflictMode)
    {
        long startTime = System.currentTimeMillis();
        List<Student> studentList = databaseController.readFromFile();

        for (Student student : studentList)
        {
            hashing.save(hashMode, conflictMode, student);
        }

        for(Student student : studentList)
        {
            Integer studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW, studentNumber, true).toString());
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("--------------------------"+hashMode.name()+ "------------" + conflictMode.name() + estimatedTime+"ms");
        return estimatedTime;
    }

    public Main()
    {
        hashFileController.createWorkBook();
        databaseController.createRandomTextDataBase(500);

        float min = Float.MAX_VALUE;

        if(  test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW)  < min)
        {
            min = test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW);
        }

        test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW);
        test(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW);
        test(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE);
        test(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW);
        test(HashMode.FOLDING, ConflictMode.LINEAR_PROBE);
        test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE);

    }
}








