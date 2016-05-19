package controller;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import hashing.Hashing.ConflictMode;
import hashing.Hashing.HashMode;
import main.Student;

public class HashFileController
{
    private static int LINE_LENGTH = 31;

    public void createWorkBook()
    {
        try
        {
            for (HashMode hashMode : HashMode.values())
            {
                for (ConflictMode conflictMode : ConflictMode.values())
                {
                    String fileName = hashMode.name().toLowerCase() + "_" + conflictMode.name().toLowerCase() + ".txt";
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

            randomAccessFile.seek(index * LINE_LENGTH);
            randomAccessFile.write(student.toString().getBytes());

            randomAccessFile.seek(index * LINE_LENGTH);
            randomAccessFile.close();
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
            randomAccessFile.seek(index * LINE_LENGTH);
            String character = Character.toString((char) randomAccessFile.readByte());
            return !character.equals("\u0000");
        }
        catch (EOFException eofExceptione)
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
            for (int a = 0; a < LINE_LENGTH; a++)
            {
                String character = Character.toString((char) randomAccessFile.readByte());
                if (character.equals("\u0000"))
                {
                    return null;
                }
                studentText.append(character);
            }
            if (isNotEmpty(studentText.toString()))
            {
                return new Student(studentText.toString());
            }
            randomAccessFile.close();
        }
        catch (EOFException eofException)
        {
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isNotEmpty(String line)
    {
        return !line.equals("\u0000\u0000\u0000\u0000\u0000\u0000" +
                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
    }


}
