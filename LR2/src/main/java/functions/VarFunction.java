package functions;//1

import java.lang.Math;

/* Функция по варианту 10: y = 1 + tg^2(x) */
public class VarFunction implements MathFunction {
    public double apply(double x) {
        return 1 + Math.pow(Math.tan(x),2);
    }
}