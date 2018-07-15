package task_to_10_07_2018;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MergeSortUp {

    public static void main(String[] args) {
        String[] strings = {"Russia", "Croatia", "Brazil", "England", "Ronaldo", "Arshavin"};
        mergeSortUp(strings);
        List<String> listOfStrings = Arrays.asList(strings);
        listOfStrings.forEach(System.out::print);
        System.out.println();
        String[] strings2 = {null, "Croatia", null, "England", "Ronaldo", null};
        mergeSortUp(strings2);
        listOfStrings = Arrays.asList(strings2);
        listOfStrings.forEach(System.out::print);
        System.out.println();
        Integer[] ints = {10, 24, 12, 5, 43, 30};
        mergeSortUp(ints);
        List<Integer> listOfIntegers = Arrays.asList(ints);
        listOfIntegers.forEach(num -> System.out.print(num+" "));
    }

    private static <T extends Comparable<T>> void mergeSortUp(T[] array) {
        if (array.length <= 1 || Arrays.stream(array).allMatch(Objects::isNull)) {
            return;
        }
        T notNull = Arrays.stream(array).filter(Objects::nonNull).findFirst().get();
        T[] leftArray = (T[])Array.newInstance(notNull.getClass(), array.length/2);
        T[] rightArray = (T[])Array.newInstance(notNull.getClass(), array.length == Integer.MAX_VALUE ? leftArray.length + 1
        : (array.length+1)/2);
        System.arraycopy(array, 0, leftArray, 0, leftArray.length);
        System.arraycopy(array, array.length/2, rightArray, 0, rightArray.length);
        mergeSortUp(leftArray);
        mergeSortUp(rightArray);
        //merging left and right arrays
        int indexOfElementFromLeftArrayToMerge = 0;
        int indexOfElementFromRightArrayToMerge = 0;
        T elementFromLeftArrayToMerge = null;
        T elementFromRightArrayToMerge = null;
        for (int i = 0; i < array.length; ++i) {
            if (indexOfElementFromLeftArrayToMerge < leftArray.length) {
                elementFromLeftArrayToMerge = leftArray[indexOfElementFromLeftArrayToMerge];
            }
            if (indexOfElementFromRightArrayToMerge < rightArray.length) {
                elementFromRightArrayToMerge = rightArray[indexOfElementFromRightArrayToMerge];
            }
            //If there is null element in at least one of the arrays leftArray or rightArray, we put it in the end of array

            //I tried to make the "if" statement shorter following Clean Code rule "if statement should be one line long" and created two separated functions ifLeftIndexIsNotInTheEndOfArray and bothElementsNotNull and one more addValueToArrayAndIncreaseValue, because in "if" statement should be only one command
            if(ifLeftIndexIsNotInTheEndOfArray(indexOfElementFromLeftArrayToMerge, leftArray.length, elementFromLeftArrayToMerge, elementFromRightArrayToMerge) || indexOfElementFromRightArrayToMerge == rightArray.length) {
               indexOfElementFromLeftArrayToMerge = addValueToArrayAndIncreaseValue(array, i, elementFromLeftArrayToMerge, indexOfElementFromLeftArrayToMerge);
            } else {
               indexOfElementFromRightArrayToMerge = addValueToArrayAndIncreaseValue(array, i, elementFromRightArrayToMerge, indexOfElementFromRightArrayToMerge);
            }
        }
    }

    private static<T extends Comparable<T>> boolean ifLeftIndexIsNotInTheEndOfArray(int leftIndex, int lengthOfFirstElement, T firstElement, T secondElement) {
        return leftIndex < lengthOfFirstElement &&
               (bothElementsNotNull(firstElement, secondElement) && firstElement.compareTo(secondElement) <= 0
               || secondElement == null);
    }
    private static<T> boolean bothElementsNotNull(T firstElement, T secondElement) {
        return firstElement != null && secondElement != null;
    }

    private static<T> int addValueToArrayAndIncreaseValue(T[] array, int indexToAdd, T elementToAdd, int indexOfElement) {
        array[indexToAdd] = elementToAdd;
        return indexOfElement + 1;
    }
}
