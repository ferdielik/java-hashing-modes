package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        createRandomText(20);

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


}