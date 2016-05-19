//package samples;
//
//import jxl.read.biff.BiffException;
//
//import jxl.write.WriteException;
//import main.Student;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.util.*;
//
///*
//      hash dosyalar˝n˝n total boyutu 1217 olsun , asal say˝ kullan˝lmas˝ daha iyi.
//     (t¸m fonksiyonlar 1217 ye gˆre dizayn))
// */
//public class sample
//{
//    public static int dataLength = 1217;
//
//    public static void main(String[] args) throws WriteException, IOException, BiffException {
//        new sample();
//    }
//
//    public sample()
//            throws BiffException, IOException, WriteException
//    {
//        createRandomExcelDataBase();
//
//
//        //readFromFile();
//        // createHashTable(); //Taslak Hash Table
//    }
//
//    public void createRandomExcelDataBase() throws BiffException, IOException, WriteException
//    {
//
//        //Blank workbook
//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        //Create a blank sheet
//        XSSFSheet sheet = workbook.createSheet("Employee Data");
//
//        //This data needs to be written (Object[])
//        Map<String, Object[]> data = new TreeMap<String, Object[]>();
//        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
//        data.put("2", new Object[] {1, "Amit", "Shukla"});
//        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
//        data.put("4", new Object[] {3, "John", "Adwards"});
//        data.put("5", new Object[] {4, "Brian", "Schultz"});
//
//        //Iterate over data and write to sheet
//        Set<String> keyset = data.keySet();
//        int rownum = 0;
//        for (String key : keyset)
//        {
//            XSSFRow row = sheet.createRow(rownum++);
//            Object [] objArr = data.get(key);
//            int cellnum = 0;
//            for (Object obj : objArr)
//            {
//                XSSFCell cell = row.createCell(cellnum++);
//                if(obj instanceof String)
//                    cell.setCellValue((String)obj);
//                else if(obj instanceof Integer)
//                    cell.setCellValue((Integer)obj);
//            }
//        }
//        try
//        {
//            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
//            workbook.write(out);
//            out.close();
//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    public void createRandomTextDataBase(int size)
//    {
//        try
//        {
//            PrintWriter writer = new PrintWriter("Users.txt", "UTF-8");
//
//            for (int i = 0; i < size; i++)
//            {
//                Student student = new Student();
//                student.setId(generateRandomID());
//                student.setName(generateRandomName());
//                student.setSurname(generateRandomName());
//
//                writer.println(student.toString());
//
//            }
//            writer.close();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.toString());
//        }
//    }
//
//    public int hashDivision(int code)
//    {
//        return code % dataLength;
//    }
//
//    public int hashKatlama(int code)
//    {
//        int num1, num2, num3;
//        int result = 0;
//        num1 = code % 1000;
//        result += reverseNumber(num1);
//        num2 = (code % 1000000 - num1) / 1000;
//        result += num2;
//        num3 = (code - code % 1000000) / 1000000;
//        result += reverseNumber(num3);
//        return result;
//    }
//
//    public Long hashRoot(int code)
//    {
//        /*
//        son 5 hanesinin karesinin orta 3 hanesi
//         */
//        Long lastFive = code % 100000L;
//        Long root = lastFive * lastFive;
//
//        String rootText = String.valueOf(root);
//        //System.out.println(rootText);
//        int a = (rootText.length() / 2) - 2;
//        String ortasi = rootText.substring(a, a + 3);
//        return Long.valueOf(ortasi);
//    }
//
//    public int reverseNumber(int number)
//    {
//        int result = 0;
//        int j = 10;
//        for (int i = 0; i < sizeOfNumber(number); i++)
//        {
//            result += ((number % j - number % (j / 10)) / (int) Math.pow(10, i)) * (int) Math.pow(10, sizeOfNumber(number) - i - 1);
//            j *= 10;
//        }
//        return result;
//
//    }
//
//    public int sizeOfNumber(int number)
//    {
//        int i = 1;
//        int j = 10;
//        while (number % j != number)
//        {
//            i++;
//            j *= 10;
//        }
//        return i;
//
//    }
//
//    public Integer generateRandomID()
//    {
//        return randomWithRange(100000000, 999999999);
//    }
//
//    public String generateRandomName()
//    {
//        Random r = new Random(); // just create one and keep it around
//        String alphabet = "abcdefghijklmnopqrstuvwxyz";
//
//        Integer N = randomWithRange(1, 10);
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < N; i++)
//        {
//            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
//        }
//        Integer space = randomWithRange(5, N); // ›sime space eklenecek !
//        String randomName = sb.toString();
//        return randomName;
//    }
//
//
//    public Integer randomWithRange(int min, int max)
//    {
//        int range = (max - min) + 1;
//
//        return Integer.valueOf(String.valueOf((Math.random() * range) + min));
//    }
//
//    public void readFromFile()
//    {
//
//        // The name of the file to open.
//        String file = "Users.txt";
//        List<Student> students = new ArrayList<>();
//
//        // This will reference one line at a time
//        String line = null;
//
//        try
//        {
//            // FileReader reads text files in the default encoding.
//            FileReader fileRead = new FileReader(file);
//
//            // Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader = new BufferedReader(fileRead);
//
//            while ((line = bufferedReader.readLine()) != null)
//            {
//                students.add(new Student(line));
//            }
//            //System.out.print(hashDivision(Std_No));
//            // Always close files.
//            bufferedReader.close();
//        }
//        catch (FileNotFoundException ex)
//        {
//            System.out.println( "Unable to open file '" + file + "'");
//        }
//        catch (IOException ex)
//        {
//            System.out.println("Error reading file '" + file + "'");
//            // Or we could just do this:
//            // ex.printStackTrace();
//        }
//    }
//
//
//}
//
//
//
//
//
//
//
//
