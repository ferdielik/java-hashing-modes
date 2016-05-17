package main;

import java.io.*;
import java.util.*;

/*
      hash dosyalarının total boyutu 1217 olsun , asal sayı kullanılması daha iyi.
     (tüm fonksiyonlar 1217 ye göre dizayn))
 */
public class Main {
    public static int dataLength = 1217;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        createRandomTextDataBase(500);
        //readFromFile();
        // createHashTable(); //Taslak Hash Table

    }


    public static void createRandomTextDataBase(int size) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Users.txt", "UTF-8");

        for (int i = 0; i < size; i++) {
            writer.println(generateRandomID() + ";" + generateRandomName() + ";" + generateRandomName());

        }
        writer.close();
    }

    public static int bolenKalan(int number) {
        return number % dataLength;
    }

    public static int katlama(int number) {
        int num1, num2, num3;
        int result = 0;
        num1 = number % 1000;

        result += convertNumberToOpposite(num1);
        num2 = (number % 1000000 - num1) / 1000;
        result += num2;
        num3 = (number - number % 1000000) / 1000000;

        result += convertNumberToOpposite(num3);
        return result;
    }

    public static int convertNumberToOpposite(int number) {
        int result = 0;
        int j = 10;
        for (int i = 0; i < numberOfLength(number); i++) {
            result += ((number % j - number % (j / 10)) / (int) Math.pow(10, i)) * (int) Math.pow(10, numberOfLength(number) - i - 1);
            j *= 10;
        }
        return result;

    }

    public static int numberOfLength(int number) {
        int i = 1;
        int j = 10;
        while (number % j != number) {
            i++;
            j *= 10;
        }
        return i;

    }

    public static int generateRandomID() {
        return randomWithRange(100000000, 999999999);
    }

    public static String generateRandomName() {
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int N = randomWithRange(1, 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        int space = randomWithRange(5, N); // İsime space eklenecek !
        String randomName = sb.toString();
        return randomName;
    }


    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static void readFromFile() {

        // The name of the file to open.
        String file = "Users.txt";
        List<String> Std_No = new ArrayList();
        List<String> Std_Name = new ArrayList();

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileRead =
                    new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileRead);

            while ((line = bufferedReader.readLine()) != null) {

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
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            file + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + file + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }


}








