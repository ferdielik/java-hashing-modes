package main;

import java.awt.geom.Point2D.Double;
import java.io.*;
import java.util.*;

/*
     hash dosyalarının total boyutu 1217 olsun , asal sayı kullanılması daha iyi.
     (tüm fonksiyonlar 1217 ye göre dizayn))
 */
public class Main
{
    public static int dataLength = 1217;

    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        createRandomTextDataBase(500);


        //readFromFile();
        // createHashTable(); //Taslak Hash Table
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

    public int hashDivision(int code)
    {
        return code % dataLength;
    }

    public int hashKatlama(int code)
    {
        int num1, num2, num3;
        int result = 0;

        num1 = code % 1000;

        result += reverseNumber(num1);
        num2 = (code % 1000000 - num1) / 1000;
        result += num2;
        num3 = (code - code % 1000000) / 1000000;
        result += reverseNumber(num3);
        return result;
    }

    public Long hashRoot(int code)
    {
        /*
        son 5 hanesinin karesinin orta 3 hanesi
         */
        Long lastFive = code % 100000L;
        Long root = lastFive * lastFive;

        String rootText = String.valueOf(root);
        System.out.println(rootText);
        int a = (rootText.length() / 2) - 2;
        String ortasi = rootText.substring(a, a + 3);
        return Long.valueOf(ortasi);
    }

    public int reverseNumber(int number)
    {
        StringBuffer numberText = new StringBuffer(String.valueOf(number));
        String reversedNumber = numberText.reverse().toString();
        return Integer.valueOf(reversedNumber);
    }

    public int sizeOfNumber(Integer number)
    {
        return String.valueOf(number).length();
    }

    public Integer generateRandomID()
    {
        return randomWithRange(100000000, 999999999);
    }

    public String generateRandomName()
    {
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        Integer N = randomWithRange(1, 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
        {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return sb.toString();
    }


    public Integer randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

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

}








