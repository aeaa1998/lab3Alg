package com.company;

public class IterableStructure <T> implements Comparable <T> {
    private T element;

    public IterableStructure(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    };

    public T setElement(T e) {
        return element = e;
    };
    @Override
    public int compareTo(T o) {
        if (this.element instanceof Integer){
            return (Integer)this.element - (Integer)o;
        }else{
            return ((String)this.element).compareTo((String)o);
        }
    }
}
