package samples;

import hashing.Hashing;
import hashing.Hashing.ConflictMode;
import hashing.Hashing.HashMode;

/**
 * Created by ferdielik on 19/05/16.
 */
public class Test
{
    Hashing hashing = new Hashing();

    Test()
    {
        System.out.println("492715066 --  expected : 1075 folding index : " + hashing.findIndex(HashMode.FOLDING,  492715066));
        System.out.println("492715066 --  expected : 1075 folding index : " + hashing.findIndex(HashMode.DIVIDING_THE_REMAINING,  492715066));
        System.out.println("492715066 --  expected : 1075 folding index : " + hashing.findIndex(HashMode.MID_SQUARE,  492715066));

        //        System.out.println("927150671 -- folding index : " + hashing.findIndexForSave(HashMode.FOLDING, ConflictMode.LINEAR_PROBE, 927150671));
        //        System.out.println("192415066 -- folding index : " + hashing.findIndexForSave(HashMode.FOLDING, ConflictMode.LINEAR_PROBE, 192415066));
        //        System.out.println(hashing.findIndexForSave(HashMode.FOLDING, ConflictMode.LINEAR_PROBE,207923400));
    }

    public static void main(String[] argvs)
    {
        new Test();
    }
}
