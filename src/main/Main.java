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

    private Long test(HashMode hashMode, ConflictMode conflictMode)
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
            System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW, studentNumber).toString());
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("--------------------------"+hashMode.name()+ "------------" + conflictMode.name() + estimatedTime+"ms");
        return estimatedTime;
    }

    public Main()
    {
        hashFileController.createWorkBook();
        databaseController.createRandomTextDataBase(500);


        Float[] d = new Float[8];

        d[0] = Float.valueOf(test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW));
        d[1] = Float.valueOf(test(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW));
        d[2] = Float.valueOf(test(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE));
        d[3] = Float.valueOf(test(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW));
        d[4] = Float.valueOf(test(HashMode.FOLDING, ConflictMode.LINEAR_PROBE));
        d[5] = Float.valueOf(test(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE));
        System.out.println("en hızlı:"+Math.min(d[0], Math.min(d[1], Math.min(d[2], Math.min(d[3], Math.min(d[4], d[5]))))));

    }
}








