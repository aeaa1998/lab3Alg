package com.company;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private int numberOfElements;
    private ArrayList<IterableStructure<Integer>> iterableList =  new ArrayList<>();
    private Sorter sorter = new Sorter();
    private ArrayList<String> correctoptions =  new ArrayList<>(){{
        add("Selection sort");
        add("Merge sort");
        add("Quick sort");
        add("Radix sort");
        add("Heap sort");
    }};
    private ArrayList<String> orderedOptions =  new ArrayList<>(){{
        add("Ordenada");
        add("Desordenada");
    }};
    private View view = new View();


    public void init(){
        numberOfElements = view.intInput("Ingrese el numero de elementos tiene que ser mayor a 1 y menor o igual a 500000", "Ingrese un valor valido", 1, 500000);
        fillIterable(numberOfElements);
        switch (view.selectOptions(correctoptions, "Ingrese el numero de opcion", "Ingrese un valor valido")){
            case 0:
                printArray(sorter.selectionSort(iterableList));
                break;
            case 1:
                printArray(sorter.mergeSort(iterableList));
                break;
            case 2:
                printArray(sorter.quickSort(iterableList, 0 ,iterableList.size() - 1));
                break;
            case 3:
                printArray(sorter.radixSort(iterableList));
                break;
            case 4:
                printArray(sorter.heapSort(iterableList));
                break;
        }
    }

    private void fillIterable(int numberOfElements){
        try {
            PrintWriter writer = new PrintWriter("random.txt", "UTF-8");
            if (view.selectOptions(orderedOptions, "Escoja el numero de como quiere el orden de los numeros denerados", "Ingrese un valor valido") != 0){
                for (int i = numberOfElements; i > 0; i--) {
                    writer.print((int)(Math.random() * (numberOfElements+1)) + " ");
                }
            }else{
                for (int i = 1; i <= numberOfElements; i++) {
                    writer.print(i + " ");
                }
            }
            writer.close();
            List<String> strings = Files.readAllLines(Path.of("random.txt"));
            for (String line:
                    strings) {
                for (String number : line.split(" ")) {
                    iterableList.add(new IterableStructure<>(Integer.valueOf(number)));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void printArray(ArrayList<IterableStructure<Integer>> iterableList){
        for (IterableStructure<Integer> struct : iterableList) {
            view.print(struct.getElement().toString());
        }
    }
}
