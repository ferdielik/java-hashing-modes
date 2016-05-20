package samples;

import java.util.Locale;

import controller.HashFileController;
import hashing.Hashing;
import hashing.Hashing.ConflictMode;
import hashing.Hashing.HashMode;
import hashing.Hashing.Process;
import main.Student;

/**
 * Created by ferdielik on 19/05/16.
 */
public class Test
{
    Hashing hashing = new Hashing();
    HashFileController hashFileController = new HashFileController();

    Test()
    {
        hashFileController.createWorkBook();
//        for( HashMode hashMode : HashMode.values())
//        {
//            if(HashMode.NONE.equals(hashMode))
//                continue;
//            for(ConflictMode conflictMode : ConflictMode.values())
//            {
//                if(ConflictMode.NONE.equals(conflictMode))
//                    continue;
//                System.out.println("492715066 --  " + conflictMode.name() + " " + hashMode.name()  +" folding index : " + hashing.findIndex(hashMode,  conflictMode, 492715066));
//                System.out.println("399630850 --  " + conflictMode.name() + " " + hashMode.name()  +" folding index : " + hashing.findIndex(hashMode,  conflictMode, 399630850));
//            }
//        }

        Integer studentNumber = 507708460;
        Integer studentNumber2 = 605077300;
        Student student = new Student(studentNumber + ";denemedene;denedeneme");
        Student student2 = new Student(studentNumber2 + ";2enemedene;denedeneme");

        HashMode hashMode = HashMode.DIVIDING_THE_REMAINING;
        ConflictMode conflictMode = ConflictMode.LINEAR_PROBE;

        hashing.save(hashMode, conflictMode, student);
        hashing.save(hashMode, conflictMode, student2);
        hashing.save(hashMode, conflictMode, student2);

        System.out.println();

        System.out.println("founded for number : " + studentNumber + "  is a " + hashing.get(hashMode, conflictMode, studentNumber));
        System.out.println("founded for number : " + studentNumber2 + "  is a " + hashing.get(hashMode, conflictMode, studentNumber2));




        //        System.out.println( studentNumber + " ------------------------------------------------------------------  expected : ? folding index : " + hashing.findIndex(HashMode.DIVIDING_THE_REMAINING,  ConflictMode.LINEAR_PROBE, 507708460));
        //        hashFileController.save(fileName, hashing.findIndex(hashMode, conflictMode, Process.WRITE, studentNumber), student);
        //        hashFileController.save(fileName, hashing.findIndex(hashMode, conflictMode, Process.WRITE, studentNumber2), student2);

        //        hashFileController.save(fileName, hashing.findIndex(hashMode, conflictMode, studentNumber2), student2);
//        System.out.println(studentNumber2 + " ------------------------------------------------------------------  expected : ? folding index : " + hashing.findIndex(HashMode.DIVIDING_THE_REMAINING,  ConflictMode.LINEAR_PROBE ,605077300));
//        System.out.println(studentNumber2 + " ------------------------------------------------------------------  expected : ? folding index : " + hashing.findIndex(HashMode.DIVIDING_THE_REMAINING,  ConflictMode.NONE ,605077300));


//        System.out.println("605077300 --  expected : ? folding index : " + hashing.findIndex(HashMode.DIVIDING_THE_REMAINING,  ConflictMode.DISCRETE_OVERFLOW ,605077300));

//        System.out.println("399630850 --  expected : ? folding index : " + hashing.findIndex(HashMode.MID_SQUARE, ConflictMode.LINEAR_PROBE, 399630850));
//        System.out.println("249220778 --  expected : ? folding index : " + hashing.findIndex(HashMode.MID_SQUARE,ConflictMode.LINEAR_PROBE,  249220778));
////        System.out.println("249220778 --  expected : ? folding index : " + hashing.find(HashMode.MID_SQUARE,  249220778));

        //        System.out.println("927150671 -- folding index : " + hashing.findIndexForSave(HashMode.FOLDING, ConflictMode.LINEAR_PROBE, 927150671));
        //        System.out.println("192415066 -- folding index : " + hashing.findIndexForSave(HashMode.FOLDING, ConflictMode.LINEAR_PROBE, 192415066));
        //        System.out.println(hashing.findIndexForSave(HashMode.FOLDING, ConflictMode.LINEAR_PROBE,207923400));
    }

    public static void main(String[] argvs)
    {
        new Test();
    }
}
