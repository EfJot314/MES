package rurki;

public class Integral {

    private double xp;
    private double xk;
    private IFunction f;

    public double value;
    
    public Integral(double xp, double xk, IFunction f){
        this.xp = xp;
        this.xk = xk;
        this.f = f;

        this.value = getValue();
    }

    // private double getValue(){
    //     double val = 0;
    //     double c = 1;
    //     double x = 0.577350269;

    //     val = (xk-xp)/2 * c * (f.getValue((xk-xp)/2 * (-x) + (xk+xp)/2) + f.getValue((xk-xp)/2 * (x) + (xk+xp)/2));

    //     return val;
    // }


    private double getValue(){

        int N = 10000;
        double dx = (xk-xp)/N;
        double val = 0;

        for(int i=0;i<N;i++){
            double x = i*dx;
            val += (f.getValue(x)+f.getValue(x+dx))*dx/2;
        }

        return val;
    }


}
