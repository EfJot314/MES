package rurki;


//klasa implementujaca macierz
// i - numer wiersza, liczony od 0
// j - numer kolumny, liczony od 0
public class Matrix {
    public int width;
    public int height;
    private double tab[][];

    public Matrix(int width, int height){
        this.width = width;
        this.height = height;
        this.tab = new double[height][width];
    }


    public void putVal(int i, int j, double val){
        tab[i][j] = val;
    }

    public double getVal(int i, int j){
        return tab[i][j];
    }

    public Matrix transposed(){
        Matrix toReturn = new Matrix(height, width);
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                toReturn.putVal(i, j, getVal(j, i));
            }
        }
        return toReturn;
    }

    public Matrix scalarMultiplication(double a){
        Matrix toReturn = new Matrix(width, height);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                toReturn.putVal(i, j, a*this.getVal(i, j));
            }
        }
        return toReturn;
    }

    public Matrix product(Matrix matrix){
        //jesli wysokosc macierzy B jest rowna szerokosci macierzy A
        if(this.width == matrix.height){
            //towrze macierz wynikowa o odpowiednich rozmiarach
            Matrix toReturn = new Matrix(matrix.width, this.height);

            //wypelniam ja
            for(int i=0;i<this.height;i++){
                for(int j=0;j<matrix.width;j++){
                    float value = 0;
                    for(int x=0;x<this.width;x++){
                        value += this.getVal(i, x)*matrix.getVal(x, j);
                    }
                    toReturn.putVal(i, j, value);
                }
            }
            //returnuje
            return toReturn;
        }
        //jesli rozmiary macierzy nie byly prawidlowe to zwracam null-a
        return null;

    }

    public double det(){
        double w = 0;
        //jesli to jest zwykla macierz 2x2 lub 1x1 to zwracam wyliczona wartosc
        if(this.height == 1){
            return this.getVal(0, 0);
        }
        if(this.height == 2){
            w = this.getVal(0, 0)*this.getVal(1, 1) - this.getVal(0, 1)*this.getVal(1, 0);
            return w;
        }
        int i = 0;
        for(int j=0;j<this.width;j++){
            w += this.complement(i, j)*this.getVal(i, j);
        }
        return w;
    }

    public Matrix complementMatrix(){
        Matrix toReturn = new Matrix(width, height);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                toReturn.putVal(i, j, this.complement(i, j));
            }
        }
        return toReturn;
    }

    public Matrix inverse(){
        double w = this.det();
        if(w != 0){
            return this.complementMatrix().transposed().scalarMultiplication(1/w);
        }
        return null;
    }

    private double complement(int i, int j){
        if(this.height > 1){
            return Math.pow(-1, (i+j))*this.withoutIJ(i, j).det();
        }
        return 1;
    }


    private Matrix withoutIJ(int I, int J){
        Matrix toReturn = new Matrix(this.width-1, this.height-1);
        for(int i=0;i<this.height;i++){
            if(i != I){
                for(int j=0;j<this.width;j++){
                    if(j != J){
                        //jesli 'i' i 'j' nie sa wybranymi I i J to dodaje element do macierzy wynikowej
                        int indI;
                        int indJ;
                        if(i < I){
                            indI = i;
                        }
                        else{
                            indI = i-1;
                        }
                        if(j < J){
                            indJ = j;
                        }
                        else{
                            indJ = j-1;
                        }
                        toReturn.putVal(indI, indJ, this.getVal(i, j));
                    }
                }
            }
            
        }
        return toReturn;
    }







    public String toString(){
        String toReturn = "\n";
        for(int j=0;j<height;j++){
            toReturn += "|";
            for(int i=0;i<width;i++){
                toReturn += this.getVal(j, i);
                toReturn += "\t";
            }
            toReturn += "|\n";
        }
        toReturn += "\n";
        return toReturn;
    }
}
