package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static io.FunctionsIO.writeTabulatedFunction;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        try(BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
            BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))){

            double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
            double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};

            TabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
            TabulatedFunction linkListFunc = new LinkedListTabulatedFunction(xValuesTest, yValuesTest);

            writeTabulatedFunction(arrayWriter, arrayFunc);
            writeTabulatedFunction(linkedListWriter, linkListFunc);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
