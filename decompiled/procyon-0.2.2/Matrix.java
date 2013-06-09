public class Matrix
{
    private double[][] data;
    private int xSize;
    private int ySize;
    
    public Matrix(final double[][] newData) {
        super();
        this.data = newData;
        this.xSize = newData.length;
        this.ySize = newData[newData.length - 1].length;
    }
    
    public Matrix(final int xSize, final int ySize) {
        super();
        this.data = new double[xSize][ySize];
        this.xSize = xSize;
        this.ySize = ySize;
    }
    
    public double getData(final int x, final int y) {
        return this.data[x][y];
    }
    
    public Matrix multiplyMatrix(final Matrix bMatrix) {
        if (bMatrix.ySize != this.xSize) {
            return null;
        }
        final Matrix result = new Matrix(bMatrix.xSize, this.ySize);
        for (int y = 0; y < this.ySize; ++y) {
            int x = 0;
            while (x < bMatrix.xSize) {
                double temp = 0.0;
                int k = 0;
                while (k < this.xSize) {
                    temp = temp + this.getData(k, y) * bMatrix.getData(x, k);
                    ++k;
                }
                result.setData(x, y, temp);
                ++x;
            }
        }
        return result;
    }
    
    public void printMatrix() {
        for (int y = 0; y < this.ySize; ++y) {
            int x = 0;
            while (x < this.xSize) {
                System.out.print("" + this.data[x][y] + " ");
                ++x;
            }
            System.out.println();
        }
    }
    
    public void setData(final int x, final int y, final double theData) {
        this.data[x][y] = theData;
    }
}
