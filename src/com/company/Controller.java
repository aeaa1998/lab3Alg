package com.company;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private int numberOfElements;
    private ArrayList<IterableStructure<Integer>> iterableList =  new ArrayList<>();
    private ArrayList<IterableStructure<String>> iterableListStrings =  new ArrayList<>();
    private Sorter sorter = new Sorter();
    private SecureRandom rnd = new SecureRandom();
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
    private ArrayList<String> typeOptions =  new ArrayList<>(){{
        add("Numeros");
        add("Letras");
    }};
    private View view = new View();


    public void init(){
        numberOfElements = view.intInput("Ingrese el numero de elementos tiene que ser mayor a 1 y menor o igual a 500000", "Ingrese un valor valido", 1, 500000);
        fillIterable(numberOfElements);
        int type = view.selectOptions(typeOptions, "Ingrese desea ordenar letras o numeros.", "Ingrese un valor valido");
        if(type != 0){
            correctoptions.remove(3);
        }
        switch (view.selectOptions(correctoptions, "Ingrese el numero de opcion", "Ingrese un valor valido")){
            case 0:
                if (type == 0) printArray(sorter.selectionSort(iterableList));
                else printArray(sorter.selectionSort(iterableListStrings));
                break;
            case 1:
                if (type == 0) printArray(sorter.mergeSort(iterableList));
                else  printArray(sorter.mergeSort(iterableListStrings));
                break;
            case 2:
                if (type == 0) printArray(sorter.quickSort(iterableList, 0 ,iterableList.size() - 1));
                else  printArray(sorter.quickSort(iterableListStrings, 0 ,iterableListStrings.size() - 1));
                break;
            case 3:
                if (type == 0) printArray(sorter.radixSort(iterableList));
                else  printArray(sorter.heapSort(iterableListStrings));
                break;
            case 4:
                if (type == 0) printArray(sorter.heapSort(iterableList));
                else printArray(sorter.heapSort(iterableListStrings));
                break;
        }
    }

    private void fillIterable(int numberOfElements){
        try {
            PrintWriter writer = new PrintWriter("random.txt", "UTF-8");
            PrintWriter writerTxt = new PrintWriter("randomTexts.txt", "UTF-8");
            if (view.selectOptions(orderedOptions, "Escoja el numero de como quiere el orden de los numeros denerados", "Ingrese un valor valido") != 1){
                for (int i = numberOfElements; i > 0; i--) {
                    writerTxt.print(randomString((int)(Math.random() * (12)) + 1) + " ");
                    writer.print((int)(Math.random() * (numberOfElements+1)) + " ");
                }
            }else{
                for (int i = 1; i <= numberOfElements; i++) {
                    writerTxt.print(randomString((int)(Math.random() * (12)) + 1) + " ");
                    writer.print(i + " ");
                }
            }
            writerTxt.close();
            writer.close();
            List<String> strings = Files.readAllLines(Path.of("random.txt"));
            List<String> letters = Files.readAllLines(Path.of("randomTexts.txt"));
            for (String line:
                    strings) {
                for (String number : line.split(" ")) {
                    iterableList.add(new IterableStructure<>(Integer.valueOf(number)));
                }
            }
            for (String line:
                    letters) {
                for (String letter : line.split(" ")) {
                    iterableListStrings.add(new IterableStructure<>(letter));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private  <T extends Comparable<T>> void printArray(ArrayList<IterableStructure<T>> iterableList){
        for (IterableStructure<T> struct : iterableList) {
            view.print(struct.getElement().toString());
        }
    }


    private String randomString(int codeLength) {
        String id = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        List<Character> temp = id.chars()
                .mapToObj(i -> (char)i)
                .collect(Collectors.toList());
        Collections.shuffle(temp, new SecureRandom());
        return temp.stream()
                .map(Object::toString)
                .limit(codeLength)
                .collect(Collectors.joining());
    }

}
