package rurki;

public class DividedFunction implements IFunction{

    private IFunction f1;
    private IFunction f2;
    
    public DividedFunction(IFunction f1, IFunction f2){
        this.f1 = f1;
        this.f2 = f2;
    }


    @Override
    public double getValue(double x) {
        if(x <= f2.getXK() || x >= f2.getXP()){
            return f1.getValue(x)/f2.getValue(x);
        }
        return f1.getValue(x)/f2.getValue(x);   //to trzeba poprawic
    }

    @Override
    public double[] getPoly() {
        return null;
    }

    @Override
    public double getXP() {
        return f2.getXP();
    }

    @Override
    public double getXK() {
        return f2.getXK();
    }

    @Override
    public void setXP(double xp) {}

    @Override
    public void setXK(double xk) {}

    @Override
    public int getDegree() {
        return -1;
    }

    @Override
    public IFunction differential() {
        return null;
    }

    @Override
    public IFunction multiplyFunction(IFunction g) {
        DividedFunction fg = new DividedFunction(f1.multiplyFunction(g), f2);
        return fg;
    }


    @Override
    public IFunction multiplyByNumber(double a) {
        DividedFunction g = new DividedFunction(f1.multiplyByNumber(a), f2);
        return g;
    }
    
}
