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
    public double getXP() {
        double minX = functions.get(0).getXP();
        for(IFunction f : functions){
            if(f.getXP() < minX){
                minX = f.getXP();
            }
        }
        return minX;
    }

    @Override
    public double getXK() {
        double maxX = functions.get(0).getXK();
        for(IFunction f : functions){
            if(f.getXK() < maxX){
                maxX = f.getXP();
            }
        }
        return maxX;
    }

    @Override
    public IFunction differential() {
        AddedFunction toReturn = new AddedFunction();
        for(IFunction f : functions){
            toReturn.addFunction(f.differential());
        }
        return toReturn;
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        AddedFunction toReturn = new AddedFunction();
        for(IFunction f : functions){
            toReturn.addFunction(f.multiplyByNumber(a));
        }
        return toReturn;
    }


}
