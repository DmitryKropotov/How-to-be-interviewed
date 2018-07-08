package task_to_10_07_2018;

import java.util.Arrays;
import java.util.List;

public class InsertionSort {

    public static void main(String[] args) {
       String[] strings = {"Russia", "Croatia", "Brazil", "England", "Ronaldo", "Arshavin"};
       insertionSort(strings);
       List<String> listOfStrings = Arrays.asList(strings);
       listOfStrings.forEach(System.out::print);
       Integer[] ints = {10, 24, 12, 5, 43, 30};
       insertionSort(ints);
       List<Integer> listOfIntegers = Arrays.asList(ints);
       listOfIntegers.forEach(num -> System.out.print(num+" "));
    }

    private static <T extends Comparable<T>> void insertionSort (T[] array) {
       for (int i = 1; i < array.length; ++i) {
          T unorderedValue = array[i];
          for (int j = i; j>0 && array[j-1].compareTo(unorderedValue) > 0; --j) {
             array[j] = array[j-1];
             if(j==1 || array[j-2].compareTo(unorderedValue) <= 0) {
                array[j-1] = unorderedValue;
             }
          }
       }
    }

}
