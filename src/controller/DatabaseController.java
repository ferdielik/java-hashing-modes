package controller;

/*
 * ogrenci olusturma okuma
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Student;

public class DatabaseController
{
    public List<Student> readFromFile()
    {
        String file = "Users.txt";
        List<Student> students = new ArrayList<>();

        String line = null;

        try
        {
            FileReader fileRead = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileRead);

            while ((line = bufferedReader.readLine()) != null)
            {
                students.add(new Student(line));
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + file + "'");
        }
        catch (IOException ex)
        {
            System.out.println("Error reading file '" + file + "'");
        }
        return students;
    }

    public void createRandomTextDataBase(int size)
    {
        try
        {
            PrintWriter writer = new PrintWriter("Users.txt", "UTF-8");

            for (int i = 0; i < size; i++)
            {
                Student student = new Student();
                student.setId(generateRandomID());
                student.setName(generateRandomName());
                student.setSurname(generateRandomName());

                writer.println(student.toString());

            }
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    private Integer generateRandomID()
    {
        return randomWithRange(100000000, 999999999);
    }

    private String generateRandomName()
    {
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        //Integer N = randomWithRange(1, 10);
        Integer N = 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
        {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return sb.toString();
    }


    private Integer randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }



}
