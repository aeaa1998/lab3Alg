package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Sorter {
    public <T extends Comparable<T>> ArrayList<IterableStructure<T>> selectionSort(ArrayList<IterableStructure<T>> list){
        for (int index = 0; index < list.size() - 1 ; index++) {
            IterableStructure<T> currentMinmum = list.get(index);

            for (int i = index + 1; i < list.size(); i++) {
                IterableStructure<T> nextvalue = list.get(i);
                currentMinmum = currentMinmum.compareTo(nextvalue.getElement()) > 0 ? nextvalue : currentMinmum;
            }
            int exchangeIndex = list.indexOf(currentMinmum);
            if (exchangeIndex != index){
                list.set(exchangeIndex, list.get(index));
                list.set(index, currentMinmum);
            }
        }
        return list;
    }

    public <T extends Comparable<T>> ArrayList<IterableStructure<T>> mergeSort(ArrayList<IterableStructure<T>> list){
        int leftIndex = (int)Math.floor(list.size()/2);
        if (list.size() <= 1){ return list; }
        ArrayList<IterableStructure<T>> leftArray = new ArrayList<>();
        leftArray.addAll(list.subList(0, leftIndex));
        ArrayList<IterableStructure<T>> rightArray = new ArrayList<>();
        rightArray.addAll(list.subList(leftIndex , list.size()));

        if (leftArray.size() == 2) leftArray = sortCouple(leftArray);
        else if (leftArray.size() > 2) leftArray = mergeSort(leftArray);

        if (rightArray.size() == 2) rightArray = sortCouple(rightArray);
        else if (rightArray.size() > 2) rightArray = mergeSort(rightArray);

        return merge(leftArray, rightArray);
    }

    private <T extends Comparable<T>> ArrayList<IterableStructure<T>> merge(
            ArrayList<IterableStructure<T>> leftSide,
            ArrayList<IterableStructure<T>> rightSide
    ){
        int leftCounter = 0, rightCounter =0;
        ArrayList<IterableStructure<T>> finalArray = new ArrayList<>();
        while (leftCounter < leftSide.size() && rightCounter < rightSide.size()){
            if (leftSide.get(leftCounter).compareTo(rightSide.get(rightCounter).getElement()) < 1){
                finalArray.add(leftSide.get(leftCounter));
                leftCounter ++;
            }else{
                finalArray.add(rightSide.get(rightCounter));
                rightCounter ++;
            }
        }
        while (leftCounter < leftSide.size()){ finalArray.add(leftSide.get(leftCounter)); leftCounter ++;}
        while (rightCounter < rightSide.size()){ finalArray.add(rightSide.get(rightCounter)); rightCounter ++;}
        return finalArray;
    }

    private <T extends Comparable<T>> ArrayList<IterableStructure<T>> sortCouple(ArrayList<IterableStructure<T>> list){
        if (list.get(0).compareTo(list.get(1).getElement()) > 0){
            IterableStructure<T> holder = list.get(0);
            list.set(0, list.get(1));
            list.set(1, holder);
        }
        return list;
    }


    public <T extends Comparable<T>> ArrayList<IterableStructure<T>> quickSort(ArrayList<IterableStructure<T>> list, int start, int end){
        int partition = partition(list, start, end);

        if(partition - 1 > start) {
            quickSort(list, start, partition - 1);
        }
        if(partition + 1 < end) {
            quickSort(list, partition + 1, end);
        }
        return list;
    }

    private <T extends Comparable<T>> int partition(ArrayList<IterableStructure<T>> list, int start, int end){
        IterableStructure<T> pivot = list.get(end);
        for(int i=start; i<end; i++){
            if(list.get(i).compareTo(pivot.getElement()) < 1){
                IterableStructure<T> holder = list.get(start);
                list.set(start, list.get(i));
                list.set(i, holder);
                start++;
            }
        }
        IterableStructure<T> holder = list.get(start);
        list.set(start, pivot);
        list.set(end, holder);
        return start;
    }

    public <T extends Comparable<T>> ArrayList<IterableStructure<T>> radixSort(ArrayList<IterableStructure<T>> list){
        IterableStructure<T> max = maxValue(list);
        Integer maxValue = (Integer) max.getElement();
        for (int exp = 1; maxValue/exp > 0; exp *= 10)
            radixSortList(list, exp);
        return list;
    }

    private  <T extends Comparable<T>> void radixSortList(ArrayList<IterableStructure<T>> list, int e){
        Integer[] output = new Integer[list.size()];

        int[] count = new int[list.size()];
        for (IterableStructure<T> s : list) {
            count[((Integer) s.getElement() / e) % 10]++;
        }
        for(int i = 1; i < list.size(); i++){ count[i] = count[i] + count[i-1]; }
        for(int i = list.size()-1; i >=0; i--){
            output[count[((Integer) list.get(i).getElement() / e) % 10] - 1] = (Integer) list.get(i).getElement();
            count[((Integer) list.get(i).getElement()/e)%10]--;
        }
        for(int i = 0; i < output.length; i++){
            list.get(i).setElement((T)output[i]);
        }
    }


    private  <T extends Comparable<T>> IterableStructure<T> maxValue(ArrayList<IterableStructure<T>> list){
        IterableStructure<T> max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            max = (max.compareTo(list.get(i).getElement()) >= 0) ? max : list.get(i);
        }
        return max;
    }

    public <T extends Comparable<T>> ArrayList<IterableStructure<T>> heapSort(ArrayList<IterableStructure<T>> list)
    {
        for (int i = list.size() / 2 - 1; i >= 0; i--)
            heapList(list, list.size(), i);

        for (int i=list.size() - 1; i>=0; i--)
        {
            T holder = list.get(0).getElement();
            list.get(0).setElement(list.get(i).getElement());
            list.get(i).setElement(holder);
            heapList(list, i, 0);
        }
        return list;
    }

    private <T extends Comparable<T>> void heapList(ArrayList<IterableStructure<T>> list, int n, int i)
    {
        int largest = i;
        int lelft = 2*i + 1;
        int right = 2*i + 2;
        if (lelft < n && list.get(lelft).compareTo(list.get(largest).getElement()) > 0) largest = lelft;
        if (right < n && list.get(right).compareTo(list.get(largest).getElement()) > 0) largest = right;
        if (largest != i)
        {
            T holder = list.get(i).getElement();
            list.get(i).setElement(list.get(largest).getElement());
            list.get(largest).setElement(holder);
            heapList(list, n, largest);
        }
    }

}
