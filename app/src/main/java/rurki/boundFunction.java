package rurki;

public class boundFunction implements IFunction{
    
    public double xp;
    public double xk;
    private IFunction f;

    public boundFunction(double xp, double xk){
        this.xp = xp;
        this.xk = xk;
        this.f = new Polynomial(0);
    }

    public void setFunction(IFunction f){
        this.f = f;
    }

    @Override
    public double getValue(double x) {
        if(x >= xp && x <= xk){
            return this.f.getValue(x);
        }
        return 0;
    }

    @Override
    public IFunction differential() {
        boundFunction toReturn = new boundFunction(xp, xk);
        toReturn.setFunction(this.f.differential());
        return toReturn;
    }


    @Override
    public IFunction multiplyFunction(IFunction g) {
        double gp = g.getXP();
        double gk = g.getXK();
        //jesli przedzialy sie nie nakladaja
        if(gp > xk || xp > gk){
            boundFunction toReturn = new boundFunction(0,0);
            toReturn.setFunction(new Polynomial(0));
            return toReturn;
        }
        //jesli przedzialy sie nakladaja
        boundFunction toReturn = new boundFunction(Math.max(xp, gp), Math.min(xk, gk));
        toReturn.setFunction(this.f.multiplyFunction(g));
        return toReturn;
    }

    

    public String toString(){
        return f.toString();
    }

    @Override
    public double[] getPoly() {
        return this.f.getPoly();
    }

    @Override
    public int getDegree() {
        return this.f.getDegree();
    }


    public void setXP(double xp){
        this.xp = xp;
    }

    public void setXK(double xk){
        this.xk = xk;
    }

    @Override
    public double getXP() {
        return xp;
    }

    @Override
    public double getXK() {
        return xk;
    }

    

}
