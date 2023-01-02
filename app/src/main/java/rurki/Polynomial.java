package rurki;



// wielomian jest przechowywany w postaci tablicy wspolczynnikow
// przyklad:
// dla funkcji kwadratowej w postaci ax^2+bx+c:
// degree = 2
// poly = [c, b, a]

public class Polynomial implements IFunction{

    public int degree;
    public double [] poly;
    
    public Polynomial(int degree){
        this.degree = degree;
        this.poly = new double[degree+1];
    }

    //ustawia wspolczynniki
    public void setFactor(int st, double val){
        poly[st] = val;
    }

    //zwraca wartosc
    public double getValue(double x){
        float val = 0;
        for(int i=0;i<degree+1;i++){
            val += poly[i] * Math.pow(x, i);
        }
        return val;
    }

    public IFunction multiplyFunction(IFunction g){
        Polynomial fg = new Polynomial(this.degree+g.getDegree());
        for(int i=0;i<this.degree+1;i++){
            for(int j=0;j<g.getDegree()+1;j++){
                fg.getPoly()[i+j] += this.poly[i]*g.getPoly()[j];
            }
        }
        return fg;
    }

    @Override
    public IFunction multiplyByNumber(double a) {
        Polynomial f = new Polynomial(degree);
        for(int i=0;i<degree+1;i++){
            f.setFactor(i, a*this.poly[i]);
        }
        return f;
    }

    public IFunction differential(){
        Polynomial g = new Polynomial(degree-1);
        for(int i=1;i<degree+1;i++){
            g.setFactor(i-1, this.poly[i]*i);
        }
        return g;
    }

    public String toString(){
        String toReturn = "";
        for(int i=0;i<degree+1;i++){
            if(i != 0){
                toReturn += " + ";
            }
            toReturn += poly[degree-i];
            if(degree-i > 0){
                toReturn += "*x";
                if(degree-i > 1){
                    toReturn += "^";
                    toReturn += (degree-i);
                }
            }

        }
        return toReturn;
    }

    @Override
    public double[] getPoly() {
        return poly;
    }

    @Override
    public int getDegree() {
        return this.degree;
    }

    @Override
    public void setXP(double xp) {}

    @Override
    public void setXK(double xk) {}

    @Override
    public double getXP() {
        return 0;
    }

    @Override
    public double getXK() {
        return 0;
    }

    





}
