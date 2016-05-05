package main;

import java.util.ArrayList;
import java.util.Random;

public class Main {



    public static void main(String[] args) {

        System.out.println(generateRandomID());
        System.out.println(generateRandomName());
    }

    public static int generateRandomID()
    {
        int answer = randomWithRange(100000000,999999999);
        return answer;
    }

    public static String generateRandomName()
    {
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int N = randomWithRange(5,20);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        String randomName = sb.toString();
        return randomName;
    }



    public static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }


}