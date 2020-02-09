package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();

        try {
            int t = 10000;
            PrintWriter writer = new PrintWriter("random.txt", "UTF-8");
            ArrayList<IterableStructure<Integer>> iterableList =  new ArrayList<>();
            for (int i = t; i > 0; i--) {
                writer.print((int)(Math.random() * (t+1)) + " ");
            }

            writer.close();
            List<String> strings = Files.readAllLines(Path.of("random.txt"));
            for (String line:
                    strings) {
                for (String number : line.split(" ")) {
                    iterableList.add(new IterableStructure<>(Integer.valueOf(number)));
                }
            }
            long start = System.nanoTime();

//            ArrayList<IterableStructure<Integer>> selection = sorter.selectionSort(iterableList);
//            ArrayList<IterableStructure<Integer>> merge = sorter.mergeSort(iterableList);
            ArrayList<IterableStructure<Integer>> quick = sorter.quickSort(iterableList, 0, iterableList.size() -1);
//            ArrayList<IterableStructure<Integer>> heap = sorter.heapSort(iterableList);
//            for (IterableStructure<Integer> struct : heap) {
//                System.out.print(struct.getElement() + "\n");
//
//            }

            long elapsedTime = System.nanoTime() - start;
            System.out.print("Tiempo en que tardo el Selection sort" + "\n nanosegundos:" + (elapsedTime));


        }catch (Exception e){

        }

    }
}
