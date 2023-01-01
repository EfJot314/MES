package rurki;


// funkcja sluzaca do przyblizania szukanej funkcji
// ma ona postac paraboli z ramionami skierowanymi w dol
public class ApproxFunction extends boundFunction{


    public ApproxFunction(double xp, double xk){
        super(xp, xk);
        Polynomial g = new Polynomial(2);
        g.setFactor(0, -xp*xk);
        g.setFactor(1, xp+xk);
        g.setFactor(2, -1);
        this.setFunction(g);
    }


    
}
