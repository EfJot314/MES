package rurki;

public class MultipliedFunction implements IFunction{
    
    private IFunction f1;
    private IFunction f2;

    public MultipliedFunction(IFunction f1, IFunction f2){
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public double getValue(double x) {
        return f1.getValue(x)*f2.getValue(x);
    }

    @Override
    public double getXP() {
        if(f1.getXK() < f2.getXP()){
            return Double.MIN_VALUE;
        }
        return Math.max(f1.getXP(), f2.getXP());
        
    }

    @Override
    public double getXK() {
        if(f1.getXK() < f2.getXP()){
            return Double.MAX_VALUE;
        }
        return Math.min(f1.getXK(), f2.getXK());
    }

    @Override
    public IFunction differential() {
        AddedFunction toReturn = new AddedFunction();
        toReturn.addFunction(new MultipliedFunction(f1.differential(), f2));
        toReturn.addFunction(new MultipliedFunction(f1, f2.differential()));
        return toReturn;
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        return new MultipliedFunction(f1.multiplyByNumber(a), f2);
    }

}
