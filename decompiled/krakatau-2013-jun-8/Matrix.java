public class Matrix {
    private double[][] data;
    private int xSize;
    private int ySize;
    
    public Matrix(double[][] a)
    {
        super();
        this.data = a;
        int i = a.length;
        this.xSize = i;
        int i0 = a.length;
        int i1 = i0 - 1;
        double[] a0 = a[i1];
        int i2 = a0.length;
        this.ySize = i2;
    }
    
    public Matrix(int i, int i0)
    {
        super();
        double[][] a = new double[i][i0];
        this.data = a;
        this.xSize = i;
        this.ySize = i0;
    }
    
    public double getData(int i, int i0)
    {
        double[][] a = this.data;
        double[] a0 = a[i];
        double d = a0[i0];
        return d;
    }
    
    public Matrix multiplyMatrix(Matrix a)
    {
        Matrix a0 = null;
        int i = a.ySize;
        int i0 = this.xSize;
        label1: {
            label0: {
                if(i == i0)
                {
                    break label0;
                }
                a0 = null;
                break label1;
            }
            int i1 = a.xSize;
            int i2 = this.ySize;
            Matrix a1 = new Matrix(i1, i2);
            int i3 = 0;
            label2: while(true)
            {
                int i4 = this.ySize;
                if(i3 >= i4)
                {
                    break;
                }
                int i5 = 0;
                while(true)
                {
                    int i6 = a.xSize;
                    if(i5 >= i6)
                    {
                        int i7 = i3 + 1;
                        i3 = i7;
                        continue label2;
                    }
                    double d = 0.0;
                    int i8 = 0;
                    while(true)
                    {
                        int i9 = this.xSize;
                        if(i8 >= i9)
                        {
                            a1.setData(i5, i3, d);
                            int i10 = i5 + 1;
                            i5 = i10;
                        }
                        else
                        {
                            double d0 = this.getData(i8, i3);
                            double d1 = a.getData(i5, i8);
                            double d2 = d0 * d1;
                            double d3 = d + d2;
                            int i11 = i8 + 1;
                            d = d3;
                            i8 = i11;
                        }
                    }
                }
            }
            a0 = a1;
        }
        return a0;
    }
    
    public void printMatrix()
    {
        int i = 0;
        while(true)
        {
            int i0 = this.ySize;
            if(i >= i0)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                int i2 = this.xSize;
                if(i1 >= i2)
                {
                    java.io.PrintStream a = System.out;
                    a.println();
                    int i3 = i + 1;
                    i = i3;
                }
                else
                {
                    java.io.PrintStream a0 = System.out;
                    StringBuilder a1 = new StringBuilder();
                    StringBuilder a2 = a1.append("");
                    double[][] a3 = this.data;
                    double[] a4 = a3[i1];
                    double d = a4[i];
                    StringBuilder a5 = a2.append(d);
                    StringBuilder a6 = a5.append(" ");
                    String s = a6.toString();
                    a0.print(s);
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
        }
    }
    
    public void setData(int i, int i0, double d)
    {
        double[][] a = this.data;
        double[] a0 = a[i];
        a0[i0] = d;
    }
}