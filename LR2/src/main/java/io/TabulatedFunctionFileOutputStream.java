package io;
import java.io.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {

        try (BufferedOutputStream array = new BufferedOutputStream(new FileOutputStream("LR2/output/array function.bin"));
             BufferedOutputStream linkedList = new BufferedOutputStream(new FileOutputStream("LR2/output/linked list function.bin"))) {

            double[] xVal = {1, 2, 3, 4, 5};
            double[] yVal = {1, 2, 3, 4, 5};

            LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(xVal, yVal);
            ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xVal, yVal);


            FunctionsIO.writeTabulatedFunction(array, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedList, listFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}