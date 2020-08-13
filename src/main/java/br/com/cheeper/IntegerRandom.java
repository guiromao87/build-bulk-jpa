package br.com.cheeper;

public class IntegerRandom {
    public static int getRandomIntegerBetweenRange(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }
}
