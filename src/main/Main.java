package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        createRandomText(20);
        readFromFile();

    }


    public static void createRandomText(int size) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Users.txt", "UTF-8");

        for (int i  = 0; i < size; i++) {
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
        int space = randomWithRange(5,N); // İsime space eklenecek !
        String randomName = sb.toString();
        return randomName;
    }


    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

        public static void readFromFile(){

            // The name of the file to open.
            String file = "Users.txt";

            // This will reference one line at a time
            String line = null;

            try {
                // FileReader reads text files in the default encoding.
                FileReader fileRead =
                        new FileReader(file);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader =
                        new BufferedReader(fileRead);

                while((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);

                }

                // Always close files.
                bufferedReader.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                        "Unable to open file '" +
                                file + "'");
            }
            catch(IOException ex) {
                System.out.println(
                        "Error reading file '"
                                + file + "'");
                // Or we could just do this:
                // ex.printStackTrace();
            }
        }
    }



