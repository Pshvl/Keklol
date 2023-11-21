package io;
import operations.TabulatedDifferentialOperator;
import functions.*;
import functions.factory.*;
import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("LR2/input/binary function.bin"))) {

            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);

            System.out.println(function.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции");

            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();

            /*LinkedListTabulatedFunction linkedListTabulatedFunction = (LinkedListTabulatedFunction) FunctionsIO.readTabulatedFunction(inputReader, new LinkedListTabulatedFunctionFactory());*/

            TabulatedDifferentialOperator diff = new TabulatedDifferentialOperator(factory);
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputReader, factory);
            System.out.println(diff.derive(function).toString());


        }
        catch (IOException e) {
            e.printStackTrace();
    }}}