package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import main.Student;

/**
 * Bu arkadas ile excele yazma okuma islemlerini yapariz
 */
public class HashFileController
{
    private static int LINE_LENGTH = 21;

    public enum HashModes
    {
        midSquare,
        midSquareLinear,
        folding,
        foldingLinear,
        dividingTheRemaining,
        dividingTheRemainingLinear
    }

    public void createWorkBook()
    {
//        try
//        {
//            for (HashModes sheet : HashModes.values())
//            {
//                PrintWriter writer = new PrintWriter(sheet.name() + ".txt", "UTF-8");
//                writer.close();
//            }
//
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }

    public void save(HashModes sheet, Long index, Student student)
    {
        try
        {
            File f = new File(sheet.name() + ".txt");
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "rw");

            randomAccessFile.seek(index * LINE_LENGTH);
            randomAccessFile.write(student.toString().getBytes());

            System.out.println(student.toString());
            randomAccessFile.seek(index * LINE_LENGTH);
            System.out.println("asasas" + randomAccessFile.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("hata");
        }

    }

    public Student getStudent(HashModes sheet, Long index)
    {
        System.out.println("index : " + index);

        File f = new File(sheet.name() + ".txt");
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
            StringBuilder studentText = new StringBuilder();
            randomAccessFile.seek(index * LINE_LENGTH);
            for (int a = 0; a < LINE_LENGTH; a++)
            {
                String character = Character.toString((char) randomAccessFile.readByte());
                studentText.append(character);
            }
            return new Student(studentText.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
