package main;

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

        System.out.println(hashRoot(123499999));
        //        createRandomTextDataBase(500);

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
                writer.println(generateRandomID() + ";" + generateRandomName() + ";" + generateRandomName());

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
        result += convertNumberToOpposite(num1);
        num2 = (code % 1000000 - num1) / 1000;
        result += num2;
        num3 = (code - code % 1000000) / 1000000;
        result += convertNumberToOpposite(num3);
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

    public int convertNumberToOpposite(int number)
    {
        int result = 0;
        int j = 10;
        for (int i = 0; i < sizeOfNumber(number); i++)
        {
            result += ((number % j - number % (j / 10)) / (int) Math.pow(10, i)) * (int) Math.pow(10, sizeOfNumber
                    (number) - i - 1);
            j *= 10;
        }
        return result;

    }

    public int sizeOfNumber(int number)
    {
        int i = 1;
        int j = 10;
        while (number % j != number)
        {
            i++;
            j *= 10;
        }
        return i;

    }

    public int generateRandomID()
    {
        return randomWithRange(100000000, 999999999);
    }

    public String generateRandomName()
    {
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int N = randomWithRange(1, 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
        {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        int space = randomWithRange(5, N); // İsime space eklenecek !
        String randomName = sb.toString();
        return randomName;
    }


    public int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public void readFromFile()
    {

        // The name of the file to open.
        String file = "Users.txt";
        List<String> Std_No = new ArrayList();
        List<String> Std_Name = new ArrayList();

        // This will reference one line at a time
        String line = null;

        try
        {
            // FileReader reads text files in the default encoding.
            FileReader fileRead =
                    new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileRead);

            while ((line = bufferedReader.readLine()) != null)
            {

                String[] parts = line.split(";");
                String stdNo = parts[0];
                String name = parts[1];
                // System.out.println(stdNo);
                Std_No.add(stdNo);
                Std_Name.add(name);

                //  System.out.println(name);
            }
            //System.out.print(hashDivision(Std_No));
            // Always close files.
            bufferedReader.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(
                    "Unable to open file '" +
                            file + "'");
        }
        catch (IOException ex)
        {
            System.out.println(
                    "Error reading file '"
                            + file + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }


}








