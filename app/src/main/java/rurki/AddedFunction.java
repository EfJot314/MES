package rurki;

import java.util.ArrayList;
import java.util.List;

public class AddedFunction implements IFunction{
    
    private List<IFunction> functions;

    public AddedFunction(){
        this.functions = new ArrayList<>();
    }

    public void addFunction(IFunction f){
        this.functions.add(f);
    }

    public double getValue(double x){
        double val = 0;
        for(IFunction f : functions){
            val += f.getValue(x);
        }
        return val;
    }

    @Override
    public double[] getPoly() {
        return null;
    }

    @Override
    public double getXP() {
        double minX = functions.get(0).getXP();
        for (IFunction f : functions) {
            if(minX > f.getXP()){
                minX = f.getXP();
            }
        }
        return minX;
    }

    @Override
    public double getXK() {
        double maxX = functions.get(0).getXK();
        for (IFunction f : functions) {
            if(maxX > f.getXK()){
                maxX = f.getXK();
            }
        }
        return maxX;
    }

    @Override
    public void setXP(double xp) {}

    @Override
    public void setXK(double xk) {}

    @Override
    public int getDegree() {
        int maxD = functions.get(0).getDegree();
        for (IFunction f : functions) {
            if(maxD > f.getDegree()){
                maxD = f.getDegree();
            }
        }
        return maxD;
    }

    @Override
    public IFunction differential() {
        AddedFunction g = new AddedFunction();
        for(IFunction f : functions){
            g.addFunction(f.differential());
        }
        return g;
    }

    @Override
    public IFunction multiplyFunction(IFunction g) {
        AddedFunction fg = new AddedFunction();
        for(IFunction f : functions){
            fg.addFunction(f.multiplyFunction(g));
        }
        return fg;
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        AddedFunction g = new AddedFunction();
        for(IFunction f : functions){
            g.addFunction(f.multiplyByNumber(a));
        }
        return g;
    }

    public String toString(){
        String toReturn = "funkcja zlozona z "+functions.size()+" funkcji:\n";
        for (IFunction f : functions) {
            toReturn += f.toString();
            toReturn += "\n";
        }
        return toReturn;
    }

    

}
