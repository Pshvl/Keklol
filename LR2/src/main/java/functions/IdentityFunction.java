package functions;

public class IdentityFunction implements MathFunction
{
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "IdentityFunction{}";
    }
    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
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
