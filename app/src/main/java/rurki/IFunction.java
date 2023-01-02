package rurki;

public interface IFunction {

    public double getValue(double x);

    public double getXP();

    public double getXK();

    public IFunction differential();

    public IFunction multiplyByNumber(double a);

    public String toString();
}
