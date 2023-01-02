package rurki;

public class ApproxFunction implements IFunction{
    
    private double xp, xk;

    public double xi;

    private AddedFunction f;

    public ApproxFunction(double x, double h){
        xi = x;
        xp = x-h;
        xk = x+h;
        f = new AddedFunction();

        Polynomial f1 = new Polynomial(1);
        Polynomial f2 = new Polynomial(1);

        f1.setFactor(0, -xp/h);
        f1.setFactor(1, 1/h);
        f2.setFactor(0, xk/h);
        f2.setFactor(1, -1/h);

        f.addFunction(new BoundFunction(xp, x, f1));
        f.addFunction(new BoundFunction(x, xk, f2));

    }

    @Override
    public double getValue(double x) {
        return f.getValue(x);
    }

    @Override
    public double getXP() {
        return xp;
    }

    @Override
    public double getXK() {
        return xk;
    }

    @Override
    public IFunction differential() {
        return f.differential();
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        return f.multiplyByNumber(a);
    }

}
