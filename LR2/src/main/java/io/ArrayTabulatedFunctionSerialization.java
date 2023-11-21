package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) throws IOException {
        try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))){
            double [] xValuesTest = {3.3, 4.3, 5.9, 9, 11.32};
            double [] yValuesTest = {36, 2.2, 0.1, 42, 6.54};

            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();

            TabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValuesTest, yValuesTest);
            TabulatedFunction diff1 = differentialOperator.derive(arrayFunc);
            TabulatedFunction diff2 = differentialOperator.derive(diff1);

            FunctionsIO.serialize(out, arrayFunc);
            FunctionsIO.serialize(out, diff1);
            FunctionsIO.serialize(out, diff2);

        } catch (IOException e){
            e.printStackTrace();
        }

        try(BufferedInputStream out = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))){

           TabulatedFunction arrayFunc = FunctionsIO.deserialize(out);
           TabulatedFunction diff1 = FunctionsIO.deserialize(out);
           TabulatedFunction diff2 = FunctionsIO.deserialize(out);

           System.out.println(arrayFunc.toString());
           System.out.println(diff1.toString());
           System.out.println(diff2.toString());
        } catch (ClassNotFoundException e) {
            throw new IOException (e);
        } catch (IOException e){
            e.printStackTrace();

        }
    }
}
