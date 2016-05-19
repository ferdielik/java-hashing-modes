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

    Integer minSpeed = Integer.MAX_VALUE;
    String minSpeedName = "";
    // hashings

    public static void main(String[] args)
    {
        new Main();
    }

    private void test(HashMode hashMode, ConflictMode conflictMode)
    {
        long startTime = System.currentTimeMillis();
        List<Student> studentList = databaseController.readFromFile();

        for (Student student : studentList)
        {
            hashing.save(hashMode, conflictMode, student);
        }

        for (Student student : studentList)
        {
            Integer studentNumber = student.getId();
            System.out.println(hashing.get(hashMode, conflictMode, studentNumber).toString());
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        Integer speed = Integer.valueOf(String.valueOf(estimatedTime));
        if( speed < minSpeed)
        {
            minSpeed = speed;
            minSpeedName =  hashMode.name() + " " + conflictMode.name();
        }
        System.out.println("--------------------------"+hashMode.name()+ "------------" + conflictMode.name() + estimatedTime+"ms");

    }

    public Main()
    {
        hashFileController.createWorkBook();
        databaseController.createRandomTextDataBase(500);

        test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW);
        test(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW);
        test(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE);
        test(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW);
        test(HashMode.FOLDING, ConflictMode.LINEAR_PROBE);
        test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE);


        System.out.println("Fast method : " + minSpeedName + "   speed : " + minSpeed);

    }
}








