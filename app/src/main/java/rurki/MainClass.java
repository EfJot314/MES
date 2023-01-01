package rurki;


public class MainClass {

    public static void main(String[] args) {
        int N = 5;
        double xp = 0;
        double xk = 3;

        double h = (xk-xp)/(N);

        //tworze liste funkcji ksztaltu
        IFunction [] fTab = new IFunction[N];
        for(int i=0;i<N;i++){
            fTab[i] = new ApproxFunction(xp+h*(i-1), xp+h*(i+1));
        }

        //tworze funkcje eps
        //TODO

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


        //tworze macierz B
        Matrix B = new Matrix(1, N);
        for(int i=0;i<N;i++){
            //TODO
        }


        System.out.println(A);
        


    }

}
