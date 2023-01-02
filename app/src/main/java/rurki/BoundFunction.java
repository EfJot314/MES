package rurki;

public class BoundFunction implements IFunction{

    private double xp, xk;
    private Polynomial f;

    public BoundFunction(double xp, double xk, Polynomial f){
        this.xp = xp;
        this.xk = xk;
        this.f = f;
    }

    @Override
    public double getValue(double x) {
        if(x >= xp && x <= xk){
            return f.getValue(x);
        }
        return 0;
    }

    @Override
    public double getXP() {
        return this.xp;
    }

    @Override
    public double getXK() {
        return this.xk;
    }

    @Override
    public IFunction differential() {
        return new BoundFunction(xp, xk, f.differential());
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        return new BoundFunction(xp, xk, f.multiplyByNumber(a));
    }
    
    
}
