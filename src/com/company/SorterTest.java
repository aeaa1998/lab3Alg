package com.company;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class SorterTest  {
    Sorter sorter = new Sorter();
    ArrayList<IterableStructure<Integer>> sorted =  new ArrayList<IterableStructure<Integer>>(){{
        for (int i = 1; i <= 3000; i++) {
            add(new IterableStructure<Integer>(i));
        }
    }};
    ArrayList<IterableStructure<Integer>> iterableList =  new ArrayList<>();
    @Test
    public void testSelectionSort() {
        for (int i = 3000; i > 0; i--) {
            iterableList.add(new IterableStructure<>(i));
        }
        ArrayList<IterableStructure<Integer>> select = sorter.selectionSort(iterableList);
        for (int i = 0; i < select.size(); i++) {
            Assert.assertEquals(select.get(i).getElement(), sorted.get(i).getElement());
        }
        iterableList.clear();
    }
    @Test
    public void testMergeSort() {
        for (int i = 3000; i > 0; i--) {
            iterableList.add(new IterableStructure<>(i));
        }
        ArrayList<IterableStructure<Integer>> m = sorter.mergeSort(iterableList);
        for (int i = 0; i < m.size(); i++) {
            Assert.assertEquals(m.get(i).getElement(), sorted.get(i).getElement());
        }
        iterableList.clear();
    }
    @Test
    public void testQuickSort() {
        for (int i = 3000; i > 0; i--) {
            iterableList.add(new IterableStructure<>(i));
        }
        ArrayList<IterableStructure<Integer>> quick = sorter.quickSort(iterableList, 0, iterableList.size() -1);
        for (int i = 0; i < quick.size(); i++) {
            Assert.assertEquals(quick.get(i).getElement(), sorted.get(i).getElement());
        }
        iterableList.clear();
    }
    @Test
    public void testRadixSort() {
        for (int i = 3000; i > 0; i--) {

            iterableList.add(new IterableStructure<>(i));
        }
        ArrayList<IterableStructure<Integer>> radix = sorter.radixSort(iterableList);
        for (int i = 0; i < radix.size(); i++) {
            Assert.assertEquals(radix.get(i).getElement(), sorted.get(i).getElement());
        }
        iterableList.clear();
    }
    @Test
    public void testHeapSort() {
        for (int i = 3000; i > 0; i--) {
            iterableList.add(new IterableStructure<>(i));
        }
        ArrayList<IterableStructure<Integer>> heap = sorter.heapSort(iterableList);
        for (int i = 0; i < heap.size(); i++) {
            Assert.assertEquals(heap.get(i).getElement(), sorted.get(i).getElement());
        }
        iterableList.clear();
    }
}
