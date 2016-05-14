package main;


import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        createRandomText(20);
        readFromFile();
       // createHashTable(); //Taslak Hash Table


    }


    public static void createRandomText(int size) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Users.txt", "UTF-8");

        for (int i = 0; i < size; i++) {
            writer.println(generateRandomID() + ";" + generateRandomName());

        }
        writer.close();
    }

    public static int generateRandomID() {
        return randomWithRange(100000000, 999999999);
    }

    public static String generateRandomName() {
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int N = randomWithRange(5, 20);
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
            System.out.print(hashDivision(Std_No));
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

    public static int hashDivision(List No)//Taslak
    {
        int[] foo = new int[20];
        int i;
        for (i = 0; i < No.size(); i++) {
            foo[i] = Integer.parseInt((String) No.get(i));

        }
        int[] modfoo = new int[20];
       for(i=0;i<20;i++)
       {
           modfoo[foo[i]%10]=Integer.parseInt((String)No.get(i));

       }


        return modfoo[1];
    }

    public static int hashPowMid(List No)//Taslak
    {

        int[] modPowMid = new int[20];
        int[] powmid = new int[20];
        int pow = 0;
        int i = 0;
        for (i = 0; i < No.size(); i++) {
            powmid[i] = Integer.parseInt((String) No.get(i));

        }
        int firstIn = 0;
        pow = powmid[0] * powmid[0];
        while (pow > 0) {
            pow = pow / 10;
            firstIn++;
        }
        pow = powmid[0] * powmid[0];
        int middle = ((firstIn + 1) / 2) - 1;
        int[] powNum = new int[50];
        int a = 1;
        for (i = 1; i < firstIn; i++) {
            a = a * 10;
        }
        int temp = 0;
        for (i = 0; i < firstIn; i++) {
            temp = pow / a;
            powNum[i] = temp;
            pow = pow % a;
            a = a / 10;
        }
        int middStep = 0;
        int midStepRight = 0;
        middStep = powNum[middle];
        modPowMid[0] = middStep * 10 + midStepRight;
        modPowMid[0] = modPowMid[0] % 10;

        return modPowMid[0];

    }

    public static int hashFolding(List No)//Taslak
    {
        int[] folding = new int[20];
        int[] modFolding = new int[20];
        int i = 0;
        for (i = 0; i < No.size(); i++) {
            folding[i] = Integer.parseInt((String) No.get(i));

        }
        int num1, num2, num3;
        int sum = 0;
        num1 = folding[0] / 1000000;
        folding[0] = folding[0] % 1000000;
        num2 = folding[0] % 1000;
        num3 = folding[0] / 1;
        sum = num1 + num2 + num3;
        modFolding[0] = sum % 10;
        return modFolding[0];

    }


    public static void createHashTable() {
        // Create a hash map
        Hashtable balance = new Hashtable();
        Enumeration names;
        String str;
        double bal;

        balance.put("Zara", new Double(3434.34));
        balance.put("Mahnaz", new Double(123.22));
        balance.put("Ayan", new Double(1378.00));
        balance.put("Daisy", new Double(99.22));
        balance.put("Qadir", new Double(-19.08));

        // Show all balances in hash table.
        names = balance.keys();
        while (names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + ": " +
                    balance.get(str));
        }
        System.out.println();
        // Deposit 1,000 into Zara's account
        bal = ((Double) balance.get("Zara")).doubleValue();
        balance.put("Zara", new Double(bal + 1000));
        System.out.println("Zara's new balance: " +
                balance.get("Zara"));
    }
}








