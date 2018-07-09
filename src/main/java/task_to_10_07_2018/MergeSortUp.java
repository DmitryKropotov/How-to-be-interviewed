package task_to_10_07_2018;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MergeSortUp {

    public static void main(String[] args) {
        String[] strings = {"Russia", "Croatia", "Brazil", "England", "Ronaldo", "Arshavin"};
        mergeSortUp(strings);
        List<String> listOfStrings = Arrays.asList(strings);
        listOfStrings.forEach(System.out::print);
        Integer[] ints = {10, 24, 12, 5, 43, 30};
        mergeSortUp(ints);
        List<Integer> listOfIntegers = Arrays.asList(ints);
        listOfIntegers.forEach(num -> System.out.print(num+" "));
    }

    private static <T extends Comparable<T>> void mergeSortUp(T[] array) {
        if (array.length <= 1) {
            return;
        }
        T[] leftArray = (T[])Array.newInstance(array[0].getClass(), array.length/2);
        T[] rightArray = (T[])Array.newInstance(array[0].getClass(), array.length == Integer.MAX_VALUE ? leftArray.length + 1
        : (array.length+1)/2);
        System.arraycopy(array, 0, leftArray, 0, leftArray.length);
        System.arraycopy(array, array.length/2, rightArray, 0, rightArray.length);
        mergeSortUp(leftArray);
        mergeSortUp(rightArray);
        //merging left and right arrays
        int indexOfElementFromLeftArrayToMerge = 0;
        int indexOfElementFromRightArrayToMerge = 0;
        for (int i = 0; i < array.length; ++i) {
            T elementFromLeftArrayToMerge = indexOfElementFromLeftArrayToMerge < leftArray.length ?
                    leftArray[indexOfElementFromLeftArrayToMerge] : null;
            T elementFromRightArrayToMerge = indexOfElementFromRightArrayToMerge < rightArray.length ?
                    rightArray[indexOfElementFromRightArrayToMerge] : null;
            if(elementFromRightArrayToMerge == null || elementFromLeftArrayToMerge != null && elementFromLeftArrayToMerge.compareTo(elementFromRightArrayToMerge) <= 0) {
               array[i] = elementFromLeftArrayToMerge;
               indexOfElementFromLeftArrayToMerge++;
            } else {
               array[i] = elementFromRightArrayToMerge;
               indexOfElementFromRightArrayToMerge++;
            }
        }
    }
}
