package rurki;


public class MainClass {

    public static void main(String[] args) {

        int N = 10;
        double xp = 0;
        double xk = 3;

        double h = (xk-xp)/(N);


        //tworze liste funkcji ksztaltu
        IFunction [] fTab = new IFunction[N];
        for(int i=0;i<N;i++){
            fTab[i] = new ApproxFunction(xp+i*h, h);
        }

        //tworze funkcje eps
        AddedFunction eps = new AddedFunction();
        Polynomial fp = new Polynomial(0);
        fp.setFactor(0, 10);
        BoundFunction fg = new BoundFunction(0, 1, fp);
        eps.addFunction(fg);
        fp = new Polynomial(0);
        fp.setFactor(0, 5);
        fg = new BoundFunction(1, 2, fp);
        eps.addFunction(fg);
        fp = new Polynomial(0);
        fp.setFactor(0, 1);
        fg = new BoundFunction(2, 3, fp);
        eps.addFunction(fg);


        //tworze funkcje rho
        Polynomial p = new Polynomial(0);
        p.setFactor(0, 1);
        IFunction rho = new BoundFunction(xp, xk, p);

        //tworze macierz A
        Matrix A = new Matrix(N, N);
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                IFunction f1 = fTab[i];
                IFunction f2 = fTab[j];
                Integral integral = new Integral(0, 3, new MultipliedFunction(f1.differential(), f2.differential()));
                double val = f1.getValue(0)*f2.getValue(0) - integral.value;
                A.putVal(i, j, val);
            }
        }

        System.out.println(A);


        Polynomial p1 = new Polynomial(0);
        p1.setFactor(0, 2);
        IFunction f1 = new BoundFunction(xp, xk, p1);
        //tworze macierz B
        Matrix B = new Matrix(1, N);
        for(int i=0;i<N;i++){
            IFunction f2 = fTab[i];
            Integral integral1 = new Integral(0, 3, new MultipliedFunction(new DividedFunction(rho, eps), f2));
            double val = integral1.value + 5*f2.getValue(0);
            Integral integral2 = new Integral(xp, xk, new MultipliedFunction(f1.differential(), f2.differential()));
            double help = f1.getValue(0)*f2.getValue(0) - integral2.value;
            val -= help;
            B.putVal(i, 0, val);
        }



        //macierz wynikowa zawierajaca szukane wspolczynniki
        Matrix C = A.inverse().product(B);

        System.out.println(C);


        //wyznaczanie ostatecznego przyblizonego wzoru na fi
        AddedFunction fi = new AddedFunction();
        for (int i=0;i<fTab.length;i++) {
            fi.addFunction(fTab[i].multiplyByNumber(C.getVal(i, 0)));
        }
        fi.addFunction(f1);

        // AddedFunction fi = new AddedFunction();
        // for (int i=0;i<fTab.length;i++) {
        //     fi.addFunction(fTab[i]);
        // }

        String toSave = "";
        for(double i=xp+0.001; i<=xk; i+= 0.001){
            toSave += fi.getValue(i);
            // toSave += " , ";
            // toSave += i;
            toSave += "\n";
        }

        SaveData sd = new SaveData(toSave);

    }

}
