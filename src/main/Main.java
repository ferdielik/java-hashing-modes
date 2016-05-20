package main;

import java.util.List;
import java.util.Locale;

import controller.DatabaseController;
import controller.HashFileController;
import hashing.Hashing;
import hashing.Hashing.ConflictMode;
import hashing.Hashing.HashMode;

public class Main
{
    DatabaseController databaseController = new DatabaseController();
    HashFileController hashFileController = new HashFileController();
    Hashing hashing = new Hashing();

    Integer minSpeed = Integer.MAX_VALUE;
    String minSpeedName = "";

    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        hashFileController.createWorkBook();
        databaseController.createRandomTextDataBase(500);

        runHashMethod(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW);
        runHashMethod(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW);
        runHashMethod(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE);
        runHashMethod(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW);
        runHashMethod(HashMode.FOLDING, ConflictMode.LINEAR_PROBE);
        runHashMethod(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE);

        System.out.println("Fast method : " + minSpeedName + "   speed : " + minSpeed + " ms");

    }

    private void runHashMethod(HashMode hashMode, ConflictMode conflictMode)
    {
        String prettyName = getPrettyName(hashMode, conflictMode);
        long startTime = System.currentTimeMillis();
        List<Student> studentList = databaseController.readFromFile();

        for (Student student : studentList)
        {
            hashing.save(hashMode, conflictMode, student);
        }

        for (Student student : studentList)
        {
            Integer studentNumber = student.getId();
            hashing.get(hashMode, conflictMode, studentNumber).toString();
//            System.out.println(hashing.get(hashMode, conflictMode, studentNumber).toString());
        }

        long estimatedTime = System.currentTimeMillis() - startTime;

        Integer speed = Integer.valueOf(String.valueOf(estimatedTime));

        if (speed < minSpeed)
        {
            minSpeed = speed;
            minSpeedName = prettyName;
        }

        System.out.println("------------- " + prettyName + " ------------- time: " + estimatedTime + " ms");
    }

    private String getPrettyName(HashMode hashMode, ConflictMode conflictMode)
    {
        String name = hashMode.name() + " " + conflictMode.name();
        return name.toString().toLowerCase(new Locale("en-US")).toString().replace("_", " ");
    }
}








