package samples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.List;

import controller.DatabaseController;
import main.Student;

/**
 * Created by ferdielik on 19/05/16.
 */
public class Sample
{

    DatabaseController databaseController = new DatabaseController();

    public static void main(String[] argvs) throws IOException
    {
        new Sample();
    }

    private static int LINE_LENGTH = 31;

    private Integer midSquareFindIndex(Integer number)
    {
        /*
        son 5 hanesinin karesinin orta 3 hanesi
         */
        Long lastFive = number % 100000L;
        Long root = lastFive * lastFive;

        String rootText = String.valueOf(root);
        int a = (rootText.length() / 2) - 2;
        //        System.out.println("Number : " + number + ",  rootText  : " + rootText + ",   lastFive : " +
        // lastFive);

        String ortasi = rootText.substring(a, a + 3);
        return Integer.valueOf(ortasi) % 1511;
    }

    private Integer anothermidSquare(Integer x)
    {
        /**
         * http://www.brpreiss.com/books/opus5/html/page214.html
         */

        int k = 10; // M==1024
        int w = 32;

        return (x * x) >>> (w - k) ;
    }

    static int a[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    static int middleSquareNumber(int numb, int dig)
    {
        int sqn = numb * numb, next_num = 0;
        int trim = (dig / 2);
        sqn = sqn / a[trim];
        for (int i = 0; i < dig; i++)
        {
            next_num += (sqn % (a[trim])) * (a[i]);
            sqn = sqn / 10;
        }
        return next_num;
    }

    Sample() throws IOException
    {
        //        System.out.println("midSquareFindIndex - > " + midSquareFindIndex(162400006));
        System.out.println("anothermidSquare - > " + anothermidSquare(162400006));
        System.out.println("middleSquareNumber - > " + middleSquareNumber(162400006, 3));

        System.out.println("");
        System.out.println("midSquareFindIndex - > " + midSquareFindIndex(492715066));
        System.out.println("anothermidSquare - > " + anothermidSquare(492715066));
        System.out.println("middleSquareNumber - > " + middleSquareNumber(492715066, 3));
    }


    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1)
        {
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    // iki fonksiyon düzenlenecek !!!!

    private static Integer checkForLinear(File abc, int code)
    {
        while (true)
        {
            if (checkClear(code) == true)
            {
                return code;
            }
            else
            {
                code = (code + 1) % LINE_LENGTH;
            }
        }

    }

    private static boolean checkClear(int code)
    {

        return false;
    }
}
