package rurki;

public interface IFunction {

    public double getValue(double x);

    public double[] getPoly();

    public double getXP();

    public double getXK();

    public void setXP(double xp);

    public void setXK(double xk);

    public int getDegree();

    public IFunction differential();


    public IFunction multiplyFunction(IFunction g);

    public String toString();
}
