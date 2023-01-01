package rurki;



// wielomian jest przechowywany w postaci tablicy wspolczynnikow
// przyklad:
// dla funkcji kwadratowej w postaci ax^2+bx+c:
// degree = 2
// poly = [c, b, a]

public class Polynomial implements IFunction{

    private int degree;
    private float [] poly;
    
    public Polynomial(int degree){
        this.degree = degree;
        this.poly = new float[degree+1];
    }

    //ustawia wspolczynniki
    public void setFactor(int st, float val){
        poly[st] = val;
    }

    //zwraca wartosc
    public float getValue(float x){
        float val = 0;
        for(int i=0;i<degree+1;i++){
            val += poly[i] * Math.pow(x, i);
        }
        return val;
    }


}
