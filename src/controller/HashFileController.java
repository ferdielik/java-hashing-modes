package controller;

import java.io.EOFException;
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
    private static int LINE_LENGTH = 31;

    public enum HashMode
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
        try
        {
            for (HashMode sheet : HashMode.values())
            {
                PrintWriter writer = new PrintWriter(sheet.name() + ".txt", "UTF-8");
                writer.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void save(HashMode sheet, Integer index, Student student)
    {
        try
        {
            File f = new File(sheet.name() + ".txt");
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "rw");

            randomAccessFile.seek(index * LINE_LENGTH);
            randomAccessFile.write(student.toString().getBytes());

            System.out.println("save -- " + student.toString());
            randomAccessFile.seek(index * LINE_LENGTH);
            System.out.println("asasas" + randomAccessFile.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("hata");
        }

    }

    public boolean isExist(HashMode hashMode, Integer index)
    {
        File f = new File(hashMode.name() + ".txt");
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

    public Student getStudent(HashMode hashMode, Integer index)
    {
        System.out.println("index : " + index);

        File f = new File(hashMode.name() + ".txt");
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
            StringBuilder studentText = new StringBuilder();
            randomAccessFile.seek(index * LINE_LENGTH);
            for (int a = 0; a < LINE_LENGTH; a++)
            {
                System.out.println("focused " + hashMode.name() + "    " + index * LINE_LENGTH);
                String character = Character.toString((char) randomAccessFile.readByte());
                if (character.equals("\u0000"))
                {
                    return null;
                }
                studentText.append(character);
            }
            if (isNotEmpty(studentText.toString()))
            {
                System.out.println("aslkjalk -- " + studentText.toString());
                return new Student(studentText.toString());
            }
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
