package task_to_17_07_18;

import task_to_17_07_18.Data.Bolt;
import task_to_17_07_18.Data.Nut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class BoltsAndNuts {

    public static void main(String[] args) {
        List<Bolt> bolts = new ArrayList<>();
        bolts.add(new Bolt(4));
        bolts.add(new Bolt(2));
        bolts.add(new Bolt(45));
        bolts.add(new Bolt(1));

        List<Nut> nuts = new ArrayList<>();
        nuts.add(new Nut(45));
        nuts.add(new Nut(4));
        nuts.add(new Nut(1));
        nuts.add(new Nut(2));
        HashMap<Bolt, Nut> pairs = makeCouplesOfBoltsAndNuts(bolts, nuts, (bolt, nut) -> bolt.getNum() - nut.getNum());
        pairs.forEach((bolt, nut) -> {
            System.out.println("bolt is "+bolt.getNum()+" nut is "+nut.getNum());
        });
    }

    private static HashMap<Bolt, Nut> makeCouplesOfBoltsAndNuts(List<Bolt> bolts, List<Nut> nuts, BiFunction<Bolt, Nut, Integer> comparatorOfBoltAndNut) {
        if(bolts.size() != nuts.size() || bolts.isEmpty()) {
            throw new IllegalArgumentException("Amount of bolts and nuts should be the same and they shouldn't be empty");
        }
        //sorting the nuts by insertion sort
        //To compare two nuts nut1 and nut2, we need to count nut1.getNum() - nut2.getNum(), what is equal (nut1.getNum() - bolt.getNum()) - (nut2.getNum() - bolt.getNum()) = (bolt.getNum() - nut2.getNum()) - (bolt.getNum() - nut1.getNum()) = comparator.compareBoltAndNut(bolt, nut2) - comparator.compareBoltAndNut(bolt, nut1)
        BiFunction<Nut, Nut, Integer> comparatorOfTwoNuts = (nut1, nut2) -> {Bolt bolt = new Bolt(0);
        return comparatorOfBoltAndNut.apply(bolt, nut2) - comparatorOfBoltAndNut.apply(bolt, nut1);};
        for (int i = 1; i < nuts.size(); ++i) {
            Nut unorderedNut = nuts.get(i);
            for (int j = i; j>0 && comparatorOfTwoNuts.apply(nuts.get(j-1), unorderedNut) > 0; --j) {
                nuts.remove(j);
                nuts.add(j, nuts.get(j - 1));
                if(j==1 || comparatorOfTwoNuts.apply(nuts.get(j - 2), unorderedNut) <= 0) {
                    nuts.remove(j - 1);
                    nuts.add(j - 1, unorderedNut);
                }
            }
        }
        //End of sorting of nuts
        HashMap<Bolt, Nut> result = new HashMap<>();
        bolts.forEach(bolt -> {
            Nut nut = binarySearchOfNut(bolt, nuts, comparatorOfBoltAndNut);
            result.put(bolt, nut);
            nuts.remove(nut);
        });
        return result;
    }

    private static Nut binarySearchOfNut(Bolt bolt, List<Nut> nuts, BiFunction<Bolt, Nut, Integer> comparatorOfBoltAndNut) {
        int lo = 0;
        int hi = nuts.size();
        while (lo < hi) {
            int currentPosition = lo + (hi - lo)/2;
            Nut nut = nuts.get(currentPosition);
            if(comparatorOfBoltAndNut.apply(bolt, nut) > 0) {
                hi = currentPosition - 1;
            }
            else if(comparatorOfBoltAndNut.apply(bolt, nut) < 0) {
                lo = currentPosition + 1;
            }
            else {
                return nut;
            }
        }
        throw new NoSuchElementException("The nut, corresponding the bolt, wasn't found");
    }
}
