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
    private static int LINE_LENGTH = 21;

    Sample() throws IOException
    {
        String file = "deneme.txt";
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        List<Student> students = databaseController.readFromFile();

        Student student = students.get(0);
        Student student2 = students.get(2);
        Student student4 = students.get(4);


        writer.println("deneme");
        writer.println("deneme2");
        writer.close();

        File f = new File("deneme.txt");
        RandomAccessFile islem = new RandomAccessFile(f, "rw");
        byte veri = islem.readByte();
        System.out.println((char) veri);

        islem.seek(4 * LINE_LENGTH);
        islem.write(student4.toString().getBytes());

        islem.seek(0 * LINE_LENGTH);
        islem.write(student.toString().getBytes());

        islem.seek(2 * LINE_LENGTH);
        islem.write(student2.toString().getBytes());


        StringBuilder deneme = new StringBuilder("");
        islem.seek(0 * LINE_LENGTH);
        for(int a=0; a<LINE_LENGTH; a++)
        {
            String dene = Character.toString ((char) islem.readByte());
            deneme.append(dene);
        }
        System.out.println(deneme.toString());


        deneme = new StringBuilder("");
        islem.seek(2 * LINE_LENGTH);
        for(int a=0; a<LINE_LENGTH; a++)
        {
            String dene = Character.toString ((char) islem.readByte());
            deneme.append(dene);
        }
        System.out.println(deneme.toString());

//
//        byte[] b2 = {1, 2, 3, 4, 5, 6, 7, 8};
//
//        System.out.println(islem.read(b2,0,10));

    }





    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    // iki fonksiyon düzenlenecek !!!!

    private static Integer checkForLinear(File abc ,int code )
    {
        while (true)
        {
            if(checkClear(code) == true)
            {
                return code;
            }else
            {
                code = (code +1)%LINE_LENGTH;
            }
        }

    }

    private static boolean checkClear(int code) {

        return false;
    }
}
