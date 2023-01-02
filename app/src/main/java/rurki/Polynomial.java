package rurki;



// wielomian jest przechowywany w postaci tablicy wspolczynnikow
// przyklad:
// dla funkcji kwadratowej w postaci ax^2+bx+c:
// degree = 2
// poly = [c, b, a]

public class Polynomial{

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

    public Polynomial differential(){
        Polynomial toReturn;
        if(degree == 0){
            toReturn = new Polynomial(0);
            toReturn.setFactor(0, 0);
            return toReturn;
        }
        toReturn = new Polynomial(degree-1);
        for(int i=1;i<degree+1;i++){
            toReturn.setFactor(i-1, i*this.poly[i]);
        }
        return toReturn;
    }

    public Polynomial multiplyByNumber(double a){
        Polynomial toReturn = new Polynomial(degree);
        for(int i=0;i<degree+1;i++){
            toReturn.setFactor(i, a*this.poly[i]);
        }
        return toReturn;
    }


    




}
