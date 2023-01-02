package rurki;


public class MainClass {

    public static void main(String[] args) {
        int N = 11;
        double xp = 0;
        double xk = 3;

        double h = (xk-xp)/(N);

        //tworze liste funkcji ksztaltu
        IFunction [] fTab = new IFunction[N];
        for(int i=0;i<N;i++){
            fTab[i] = new ApproxFunction(xp+h*(i-1), xp+h*(i+1));
        }

        //tworze funkcje eps
        AddedFunction eps = new AddedFunction();
        boundFunction fg = new boundFunction(0, 1);
        Polynomial fp = new Polynomial(0);
        fp.setFactor(0, 10);
        fg.setFunction(fp);
        eps.addFunction(fg);
        fg = new boundFunction(1, 2);
        fp = new Polynomial(0);
        fp.setFactor(0, 5);
        fg.setFunction(fp);
        eps.addFunction(fg);
        fg = new boundFunction(2, 3);
        fp = new Polynomial(0);
        fp.setFactor(0, 1);
        fg.setFunction(fp);
        eps.addFunction(fg);

        //tworze funkcje rho
        Polynomial p = new Polynomial(0);
        p.setFactor(0, 1);

        //tworze macierz A
        Matrix A = new Matrix(N, N);
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                IFunction f1 = fTab[i];
                IFunction f2 = fTab[j];
                Integral integral = new Integral(0, 3, f1.differential().multiplyFunction(f2.differential()));
                double val = f1.getValue(0)*f2.getValue(0) - integral.value;
                A.putVal(i, j, val);
            }
        }

        Polynomial f1 = new Polynomial(0);
        f1.setFactor(0, 2);
        //tworze macierz B
        Matrix B = new Matrix(1, N);
        for(int i=0;i<N;i++){
            IFunction f2 = fTab[i];
            Integral integral1 = new Integral(0, 3, new DividedFunction(p, eps).multiplyFunction(f2));
            double val = integral1.value + 5*f2.getValue(0);
            Integral integral2 = new Integral(xp, xk, f1.differential().multiplyFunction(f2.differential()));
            double help = f1.getValue(0)*f2.getValue(0) - integral2.value;
            val -= help;
            B.putVal(i, 0, val);
        }

        //macierz wynikowa zawierajaca szukane wspolczynniki
        Matrix C = A.inverse().product(B);

        //wyznaczanie ostatecznego przyblizonego wzoru na fi
        AddedFunction fi = new AddedFunction();
        for (int i=0;i<fTab.length;i++) {
            fi.addFunction(fTab[i].multiplyByNumber(C.getVal(i, 0)));
        }

        System.out.println(fi.getValue(2));

    }

}
