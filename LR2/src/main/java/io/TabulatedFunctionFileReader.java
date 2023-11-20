package io;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.io.*;

import static io.FunctionsIO.readTabulatedFunction;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try(BufferedReader arrayReader = new BufferedReader(new FileReader("input/function.txt"));
            BufferedReader linkedListReader = new BufferedReader(new FileReader("input/function.txt"))){
            TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction arrayFunc = readTabulatedFunction(arrayReader, arrayFactory);

            TabulatedFunctionFactory linkListFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction linkListFunc = readTabulatedFunction(linkedListReader, linkListFactory);

            System.out.println(arrayFunc);
            System.out.println(linkListFunc);
            
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
