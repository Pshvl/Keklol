package io;
import functions.*;
import functions.factory.*;
import java.io.*;
import operations.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("LR2/output/serialized linked list functions.bin"))) {
            double[] xValues = {1, 2, 3};
            double[] yValues = {2, 3, 4};

            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

            TabulatedFunction firstDerive = operator.derive(function);
            TabulatedFunction secondDerive = operator.derive(firstDerive);

            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, firstDerive);
            FunctionsIO.serialize(out, secondDerive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("LR2/output/serialized linked list functions.bin"))) {
            System.out.println("Function: " + FunctionsIO.deserialize(in));
            System.out.println("First derivative: " + FunctionsIO.deserialize(in));
            System.out.println("Second derivative: " + FunctionsIO.deserialize(in));
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
