package controller;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Locale;

import hashing.Hashing.ConflictMode;
import hashing.Hashing.HashMode;
import main.Student;

public class HashFileController
{
    private static int LINE_LENGTH = 31;
    private static Locale LOCALE = new Locale("en-EN");

    public void createWorkBook()
    {
        try
        {
            for (HashMode hashMode : HashMode.values())
            {
                for (ConflictMode conflictMode : ConflictMode.values())
                {
                    String fileName = hashMode.name().toLowerCase(LOCALE) + "_" + conflictMode.name().toLowerCase
                            (LOCALE) + ".txt";
                    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
                    writer.close();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void save(String fileName, Integer index, Student student)
    {
        try
        {
            File f = new File(fileName);
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "rw");

            randomAccessFile.seek((index * LINE_LENGTH));
            randomAccessFile.write(student.toString().getBytes());
            randomAccessFile.close();

            System.out.println("student saved for index : " + index + "   student number : " + student.getId() + " file name " + fileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public boolean isExist(String fileName, Integer index)
    {
        File f = new File(fileName);
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
            randomAccessFile.seek((index * LINE_LENGTH));
            //            System.out.println("look at :" + (index * LINE_LENGTH));
            String character = Character.toString((char) randomAccessFile.readByte());
            randomAccessFile.close();
            return !character.equals("\u0000");
        }
        catch (EOFException eofException)
        {
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Student getStudent(String fileName, Integer index)
    {
        File f = new File(fileName);
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
            StringBuilder studentText = new StringBuilder();
            randomAccessFile.seek(index * LINE_LENGTH);

            try
            {

                for (int a = 0; a < LINE_LENGTH; a++)
                {
                    String character = Character.toString((char) randomAccessFile.readByte());
                    if (character.equals("\u0000"))
                    {
                        randomAccessFile.close();
                        return null;
                    }
                    studentText.append(character);
                }

                randomAccessFile.close();
                return new Student(studentText.toString());
            }
            catch (EOFException eofException)
            {
                randomAccessFile.close();
                return new Student();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
