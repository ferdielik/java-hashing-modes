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

    public Main()
    {
        hashFileController.createWorkBook();
        databaseController.createRandomTextDataBase(500);
        buildHashFiles();

        // 587363477;fdswvbcnnz;jwjveykcfa

        Integer studentNumber = 197984916;
//        System.out.println(hashing.get(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE,studentNumber, true).toString());
//        System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW, studentNumber, true).toString());
//

        List<Student> students = databaseController.readFromFile();
        for(Student student : students)
        {
            studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.DISCRETE_OVERFLOW, studentNumber, true).toString());
        }

        for(Student student : students)
        {
            studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.MID_SQUARE, ConflictMode.DISCRETE_OVERFLOW,studentNumber, true).toString());
        }

        for(Student student : students)
        {
            studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE,studentNumber, true).toString());
        }

        for(Student student : students)
        {
            studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.FOLDING, ConflictMode.DISCRETE_OVERFLOW,studentNumber, true).toString());
        }

        for(Student student : students)
        {
            studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.FOLDING, ConflictMode.LINEAR_PROBE,studentNumber, true).toString());
        }

        for(Student student : students)
        {
            studentNumber = student.getId();
            System.out.println(hashing.get(HashMode.DIVIDING_THE_REMAINING, ConflictMode.LINEAR_PROBE, studentNumber, true).toString());
        }

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








