package functions;

public class IdentityFunction implements MathFunction
{
    public double apply(double x) {
        return x;
    }

    public final String toString() {
        return "IdentityFunction{}";
    }

    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Object clone() {
           /* try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println(e.getMessage());
                return null;
            } */
        return new IdentityFunction();
    }
}
