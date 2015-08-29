package myutil;

class MatrixError extends Exception{

    public MatrixError(){}
    public MatrixError(String err){
        super(err);
    }
}

public class Matrix {
    private float [][] mass;
    private int w, h;
    private boolean transposed;

    public Matrix(int w, int h){
        this.w = w;
        this.h = h;
        transposed = false;
        mass = new float[w][h];
    }

    public int getW(){
        if (transposed) return h;
        else return w;
    }

    public int getH(){
        if (transposed) return w;
        else return h;
    }

    public float get(int x, int y) throws MatrixError{
        if (transposed)
            if (x < 0 || y < 0 || x >= h || y >= w)
                throw new MatrixError("Element not found");
        else
            if (x < 0 || y < 0 || x >= w || y >= h)
                throw new MatrixError("Element not found");

        if (transposed) return mass[y][x];
        else return mass[x][y];
    }

}
