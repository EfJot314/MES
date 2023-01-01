package rurki;


// funkcja sluzaca do przyblizania szukanej funkcji
// ma ona postac 'piku' ktory na krancu przedzialu ma wartosc 0, a najwwieksza wartosc w srodku przedzialu
// boki 'gorki' sa funkcjami liniowymi o wspolczynniku kierunkowym rownym a=+-1
public class ApproxFunction implements IFunction{

    private float xp;
    private float xk;
    private float xs;
    private Polynomial p1;
    private Polynomial p2;

    public ApproxFunction(float xp, float xk){
        this.xp = xp;
        this.xk = xk;
        this.xs = (xp+xk)/2;
        this.p1 = new Polynomial(1);
        this.p1.setFactor(0, -xp);
        this.p1.setFactor(1, 1);
        this.p2 = new Polynomial(1);
        this.p2.setFactor(0, xk);
        this.p2.setFactor(1, -1);
    }

    @Override
    public float getValue(float x) {
        //jesli jestem w pierwszej polowie przedzialu to biore wartosc z p1
        if(x >= xp && x <= xs){
            return p1.getValue(x);
        }
        //jesli w drugiej polowie przedzialu to z p2
        if(x > xs && x <= xk){
            return p2.getValue(x);
        }
        //jesli nie jestem w przedziale to 0
        return 0;
    }
    
}
