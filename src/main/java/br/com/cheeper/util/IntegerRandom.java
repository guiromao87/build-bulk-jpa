package br.com.cheeper.util;

import java.util.*;

public class IntegerRandom {

    private int tamanho;
    private LinkedList<Integer> sacola = new LinkedList<>();

    public IntegerRandom(int tamanho) {
        this.tamanho = tamanho;

        for(int i=1; i <= this.tamanho; i++) {
            this.sacola.add(i);
        }

        Collections.shuffle(sacola);
    }

    public int proximo(){
        return this.sacola.poll();
    }
}
