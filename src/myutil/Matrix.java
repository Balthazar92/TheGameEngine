package myutil;

class MatrixError extends Exception{

    public MatrixError(){}
    public MatrixError(String err){
        super(err);
    }
}

public class Matrix {
    float [][] mass;
    int w, h; //размеры матрицы
    boolean transport; //является ли матрица транспонированной

    public Matrix(int w, int h){
        this.w = w;
        this.h = h;
        transport = false;
        mass = new float[w][h];
    }

    public int getW(){
        if (transport) return h;
        else return w;
    }

    public int getH(){
        if (transport) return w;
        else return h;
    }

    public float get(int x, int y) throws MatrixError{
        if (transport)
            if (x < 0 || y < 0 || x >= h || y >= w)
                throw new MatrixError("Элемент не существует");
        else
            if (x < 0 || y < 0 || x >= w || y >= h)
                throw new MatrixError("Элемент не существует");
        
        if (transport) return mass[y][x];
        else return mass[x][y];
    }

}
