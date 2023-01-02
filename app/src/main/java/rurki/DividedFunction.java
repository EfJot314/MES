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
        if(x >= f2.getXP() && x <= f2.getXK()){
            return f1.getValue(x) / f2.getValue(x);
        }
        return 0;
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
    public IFunction differential() {
        AddedFunction fg = new AddedFunction();
        fg.addFunction(new MultipliedFunction(f1.differential(), f2));
        fg.addFunction(new MultipliedFunction(f1, f2.differential()));
        MultipliedFunction fd = new MultipliedFunction(f2, f2);
        return new DividedFunction(fg, fd);
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        return new DividedFunction(f1.multiplyByNumber(a), f2);
    }


    
}
