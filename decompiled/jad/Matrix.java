// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Matrix.java

import java.io.PrintStream;

public class Matrix
{

    public Matrix(double newData[][])
    {
        data = newData;
        xSize = newData.length;
        ySize = newData[newData.length - 1].length;
    }

    public Matrix(int xSize, int ySize)
    {
        data = new double[xSize][ySize];
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public double getData(int x, int y)
    {
        return data[x][y];
    }

    public Matrix multiplyMatrix(Matrix bMatrix)
    {
        if(bMatrix.ySize != xSize)
            return null;
        Matrix result = new Matrix(bMatrix.xSize, ySize);
        for(int y = 0; y < ySize; y++)
        {
            for(int x = 0; x < bMatrix.xSize; x++)
            {
                double temp = 0.0D;
                for(int k = 0; k < xSize; k++)
                    temp += getData(k, y) * bMatrix.getData(x, k);

                result.setData(x, y, temp);
            }

        }

        return result;
    }

    public void printMatrix()
    {
        for(int y = 0; y < ySize; y++)
        {
            for(int x = 0; x < xSize; x++)
                System.out.print((new StringBuilder()).append("").append(data[x][y]).append(" ").toString());

            System.out.println();
        }

    }

    public void setData(int x, int y, double theData)
    {
        data[x][y] = theData;
    }

    private double data[][];
    private int xSize;
    private int ySize;
}
