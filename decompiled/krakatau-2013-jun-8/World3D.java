public class World3D {
    private Matrix polyWorldXform;
    private Matrix worldViewXform;
    private Matrix finalXform;
    private int translateX;
    private int translateY;
    final private double SCREEN_WIDTH;
    final private double SCREEN_HEIGHT;
    final private double PROJECTION_RATIO;
    final private double ZADJUSTMENT;
    
    public World3D()
    {
        super();
        this.translateX = 0;
        this.translateY = 0;
        this.SCREEN_WIDTH = 100.0;
        this.SCREEN_HEIGHT = 80.0;
        this.PROJECTION_RATIO = -10.0;
        this.ZADJUSTMENT = -200.0;
        Matrix a = new Matrix(4, 4);
        this.polyWorldXform = a;
        Matrix a0 = new Matrix(4, 4);
        this.worldViewXform = a0;
        this.initialisePolyWorld();
        this.initialiseWorldView();
    }
    
    public World3D(Matrix a, Matrix a0)
    {
        super();
        this.translateX = 0;
        this.translateY = 0;
        this.SCREEN_WIDTH = 100.0;
        this.SCREEN_HEIGHT = 80.0;
        this.PROJECTION_RATIO = -10.0;
        this.ZADJUSTMENT = -200.0;
        this.polyWorldXform = a;
        this.worldViewXform = a0;
    }
    
    public void calculateFinalXform()
    {
        Matrix a = this.worldViewXform;
        Matrix a0 = this.polyWorldXform;
        Matrix a1 = a.multiplyMatrix(a0);
        this.finalXform = a1;
    }
    
    public Matrix getPolyWorld()
    {
        Matrix a = this.polyWorldXform;
        return a;
    }
    
    public Matrix getWorldView()
    {
        Matrix a = this.worldViewXform;
        return a;
    }
    
    public void initialisePolyWorld()
    {
        Matrix a = this.polyWorldXform;
        a.setData(0, 0, 1.0);
        Matrix a0 = this.polyWorldXform;
        a0.setData(0, 1, 0.0);
        Matrix a1 = this.polyWorldXform;
        a1.setData(0, 2, 0.0);
        Matrix a2 = this.polyWorldXform;
        a2.setData(0, 3, 0.0);
        Matrix a3 = this.polyWorldXform;
        a3.setData(1, 0, 0.0);
        Matrix a4 = this.polyWorldXform;
        a4.setData(1, 1, 1.0);
        Matrix a5 = this.polyWorldXform;
        a5.setData(1, 2, 0.0);
        Matrix a6 = this.polyWorldXform;
        a6.setData(1, 3, 0.0);
        Matrix a7 = this.polyWorldXform;
        a7.setData(2, 0, 0.0);
        Matrix a8 = this.polyWorldXform;
        a8.setData(2, 1, 0.0);
        Matrix a9 = this.polyWorldXform;
        a9.setData(2, 2, 1.0);
        Matrix a10 = this.polyWorldXform;
        a10.setData(2, 3, 0.0);
        Matrix a11 = this.polyWorldXform;
        a11.setData(3, 0, 0.0);
        Matrix a12 = this.polyWorldXform;
        a12.setData(3, 1, 0.0);
        Matrix a13 = this.polyWorldXform;
        a13.setData(3, 2, -200.0);
        Matrix a14 = this.polyWorldXform;
        a14.setData(3, 3, 1.0);
    }
    
    public void initialiseWorldView()
    {
        Matrix a = this.worldViewXform;
        a.setData(0, 0, 1.0);
        Matrix a0 = this.worldViewXform;
        a0.setData(0, 1, 0.0);
        Matrix a1 = this.worldViewXform;
        a1.setData(0, 2, 0.0);
        Matrix a2 = this.worldViewXform;
        a2.setData(0, 3, 0.0);
        Matrix a3 = this.worldViewXform;
        a3.setData(1, 0, 0.0);
        Matrix a4 = this.worldViewXform;
        a4.setData(1, 1, 1.0);
        Matrix a5 = this.worldViewXform;
        a5.setData(1, 2, 0.0);
        Matrix a6 = this.worldViewXform;
        a6.setData(1, 3, 0.0);
        Matrix a7 = this.worldViewXform;
        a7.setData(2, 0, 0.0);
        Matrix a8 = this.worldViewXform;
        a8.setData(2, 1, 0.0);
        Matrix a9 = this.worldViewXform;
        a9.setData(2, 2, 1.0);
        Matrix a10 = this.worldViewXform;
        a10.setData(2, 3, 0.0);
        Matrix a11 = this.worldViewXform;
        a11.setData(3, 0, 0.0);
        Matrix a12 = this.worldViewXform;
        a12.setData(3, 1, 0.0);
        Matrix a13 = this.worldViewXform;
        a13.setData(3, 2, 0.0);
        Matrix a14 = this.worldViewXform;
        a14.setData(3, 3, 1.0);
    }
    
    private Point3D matrixToPoint(Matrix a)
    {
        Point3D a0 = new Point3D();
        double d = a.getData(0, 0);
        a0.x = d;
        double d0 = a.getData(0, 1);
        a0.y = d0;
        double d1 = a.getData(0, 2);
        a0.z = d1;
        double d2 = a.getData(0, 3);
        a0.w = d2;
        return a0;
    }
    
    private Matrix pointToMatrix(Point3D a)
    {
        Matrix a0 = new Matrix(1, 4);
        double d = a.x;
        a0.setData(0, 0, d);
        double d0 = a.y;
        a0.setData(0, 1, d0);
        double d1 = a.z;
        a0.setData(0, 2, d1);
        double d2 = a.w;
        a0.setData(0, 3, d2);
        return a0;
    }
    
    public java.awt.Polygon projectPolygon(Polygon3D a)
    {
        java.awt.Polygon a0 = new java.awt.Polygon();
        int i = 0;
        while(true)
        {
            int i0 = a.size();
            if(i >= i0)
            {
                return a0;
            }
            else
            {
                Point3D a1 = a.getPoint(i);
                Matrix a2 = this.pointToMatrix(a1);
                Matrix a3 = this.finalXform;
                Matrix a4 = a3.multiplyMatrix(a2);
                Point3D a5 = this.matrixToPoint(a4);
                java.awt.Point a6 = new java.awt.Point();
                double d = a5.x;
                double d0 = a5.z;
                double d1 = d / d0;
                double d2 = d1 * -10.0;
                double d3 = d2 * 50.0;
                double d4 = d3 + 0.5;
                double d5 = d4 + 50.0;
                int i1 = (int)d5;
                a6.x = i1;
                double d6 = a5.y;
                double d7 = a5.z;
                double d8 = d6 / d7;
                double d9 = d8 * -1.0;
                double d10 = d9 * -10.0;
                double d11 = d10 * 50.0;
                double d12 = d11 + 0.5;
                double d13 = d12 + 40.0;
                int i2 = (int)d13;
                a6.y = i2;
                java.io.PrintStream a7 = System.out;
                StringBuilder a8 = new StringBuilder();
                StringBuilder a9 = a8.append("X = ");
                int i3 = a6.x;
                int i4 = this.translateX;
                int i5 = i3 + i4;
                StringBuilder a10 = a9.append(i5);
                StringBuilder a11 = a10.append(" Y = ");
                int i6 = a6.y;
                int i7 = this.translateY;
                int i8 = i6 + i7;
                StringBuilder a12 = a11.append(i8);
                String s = a12.toString();
                a7.println(s);
                int i9 = a6.x;
                int i10 = this.translateX;
                int i11 = i9 + i10;
                int i12 = a6.y;
                int i13 = this.translateY;
                int i14 = i12 + i13;
                a0.addPoint(i11, i14);
                int i15 = i + 1;
                i = i15;
            }
        }
    }
    
    public void resetPolyWorldRotation()
    {
        Matrix a = this.polyWorldXform;
        a.setData(0, 0, 1.0);
        Matrix a0 = this.polyWorldXform;
        a0.setData(0, 1, 0.0);
        Matrix a1 = this.polyWorldXform;
        a1.setData(0, 2, 0.0);
        Matrix a2 = this.polyWorldXform;
        a2.setData(1, 0, 0.0);
        Matrix a3 = this.polyWorldXform;
        a3.setData(1, 1, 1.0);
        Matrix a4 = this.polyWorldXform;
        a4.setData(1, 2, 0.0);
        Matrix a5 = this.polyWorldXform;
        a5.setData(2, 0, 0.0);
        Matrix a6 = this.polyWorldXform;
        a6.setData(2, 1, 0.0);
        Matrix a7 = this.polyWorldXform;
        a7.setData(2, 2, 1.0);
    }
    
    public void resetPolyWorldTransformation()
    {
        Matrix a = this.polyWorldXform;
        a.setData(3, 0, 0.0);
        Matrix a0 = this.polyWorldXform;
        a0.setData(3, 1, 0.0);
        Matrix a1 = this.polyWorldXform;
        a1.setData(3, 2, 0.0);
    }
    
    public void resetWorldViewRotation()
    {
        Matrix a = this.worldViewXform;
        a.setData(0, 0, 1.0);
        Matrix a0 = this.worldViewXform;
        a0.setData(0, 1, 0.0);
        Matrix a1 = this.worldViewXform;
        a1.setData(0, 2, 0.0);
        Matrix a2 = this.worldViewXform;
        a2.setData(1, 0, 0.0);
        Matrix a3 = this.worldViewXform;
        a3.setData(1, 1, 1.0);
        Matrix a4 = this.worldViewXform;
        a4.setData(1, 2, 0.0);
        Matrix a5 = this.worldViewXform;
        a5.setData(2, 0, 0.0);
        Matrix a6 = this.worldViewXform;
        a6.setData(2, 1, 0.0);
        Matrix a7 = this.worldViewXform;
        a7.setData(2, 2, 1.0);
    }
    
    public void resetWorldViewTransformation()
    {
        Matrix a = this.worldViewXform;
        a.setData(3, 0, 0.0);
        Matrix a0 = this.worldViewXform;
        a0.setData(3, 1, 0.0);
        Matrix a1 = this.worldViewXform;
        a1.setData(3, 2, 0.0);
    }
    
    public void rotatePolyWorldX(double d)
    {
        double d0 = Math.cos(d);
        double d1 = Math.sin(d);
        Matrix a = this.polyWorldXform;
        double d2 = a.getData(0, 1);
        double d3 = d0 * d2;
        double d4 = -d1;
        Matrix a0 = this.polyWorldXform;
        double d5 = a0.getData(0, 2);
        double d6 = d4 * d5;
        double d7 = d3 + d6;
        Matrix a1 = this.polyWorldXform;
        double d8 = a1.getData(1, 1);
        double d9 = d0 * d8;
        double d10 = -d1;
        Matrix a2 = this.polyWorldXform;
        double d11 = a2.getData(1, 2);
        double d12 = d10 * d11;
        double d13 = d9 + d12;
        Matrix a3 = this.polyWorldXform;
        double d14 = a3.getData(2, 1);
        double d15 = d0 * d14;
        double d16 = -d1;
        Matrix a4 = this.polyWorldXform;
        double d17 = a4.getData(2, 2);
        double d18 = d16 * d17;
        double d19 = d15 + d18;
        Matrix a5 = this.polyWorldXform;
        double d20 = a5.getData(0, 1);
        double d21 = d1 * d20;
        Matrix a6 = this.polyWorldXform;
        double d22 = a6.getData(0, 2);
        double d23 = d0 * d22;
        double d24 = d21 + d23;
        Matrix a7 = this.polyWorldXform;
        double d25 = a7.getData(1, 1);
        double d26 = d1 * d25;
        Matrix a8 = this.polyWorldXform;
        double d27 = a8.getData(1, 2);
        double d28 = d0 * d27;
        double d29 = d26 + d28;
        Matrix a9 = this.polyWorldXform;
        double d30 = a9.getData(2, 1);
        double d31 = d1 * d30;
        Matrix a10 = this.polyWorldXform;
        double d32 = a10.getData(2, 2);
        double d33 = d0 * d32;
        double d34 = d31 + d33;
        Matrix a11 = this.polyWorldXform;
        a11.setData(0, 1, d7);
        Matrix a12 = this.polyWorldXform;
        a12.setData(1, 1, d13);
        Matrix a13 = this.polyWorldXform;
        a13.setData(2, 1, d19);
        Matrix a14 = this.polyWorldXform;
        a14.setData(0, 2, d24);
        Matrix a15 = this.polyWorldXform;
        a15.setData(1, 2, d29);
        Matrix a16 = this.polyWorldXform;
        a16.setData(2, 2, d34);
    }
    
    public void rotatePolyWorldY(double d)
    {
        double d0 = Math.cos(d);
        double d1 = Math.sin(d);
        Matrix a = this.polyWorldXform;
        double d2 = a.getData(0, 0);
        double d3 = d0 * d2;
        Matrix a0 = this.polyWorldXform;
        double d4 = a0.getData(0, 2);
        double d5 = d1 * d4;
        double d6 = d3 + d5;
        Matrix a1 = this.polyWorldXform;
        double d7 = a1.getData(1, 0);
        double d8 = d0 * d7;
        Matrix a2 = this.polyWorldXform;
        double d9 = a2.getData(1, 2);
        double d10 = d1 * d9;
        double d11 = d8 + d10;
        Matrix a3 = this.polyWorldXform;
        double d12 = a3.getData(2, 0);
        double d13 = d0 * d12;
        Matrix a4 = this.polyWorldXform;
        double d14 = a4.getData(2, 2);
        double d15 = d1 * d14;
        double d16 = d13 + d15;
        double d17 = -d1;
        Matrix a5 = this.polyWorldXform;
        double d18 = a5.getData(0, 0);
        double d19 = d17 * d18;
        Matrix a6 = this.polyWorldXform;
        double d20 = a6.getData(0, 2);
        double d21 = d0 * d20;
        double d22 = d19 + d21;
        double d23 = -d1;
        Matrix a7 = this.polyWorldXform;
        double d24 = a7.getData(1, 0);
        double d25 = d23 * d24;
        Matrix a8 = this.polyWorldXform;
        double d26 = a8.getData(1, 2);
        double d27 = d0 * d26;
        double d28 = d25 + d27;
        double d29 = -d1;
        Matrix a9 = this.polyWorldXform;
        double d30 = a9.getData(2, 0);
        double d31 = d29 * d30;
        Matrix a10 = this.polyWorldXform;
        double d32 = a10.getData(2, 2);
        double d33 = d0 * d32;
        double d34 = d31 + d33;
        Matrix a11 = this.polyWorldXform;
        a11.setData(0, 0, d6);
        Matrix a12 = this.polyWorldXform;
        a12.setData(1, 0, d11);
        Matrix a13 = this.polyWorldXform;
        a13.setData(2, 0, d16);
        Matrix a14 = this.polyWorldXform;
        a14.setData(0, 2, d22);
        Matrix a15 = this.polyWorldXform;
        a15.setData(1, 2, d28);
        Matrix a16 = this.polyWorldXform;
        a16.setData(2, 2, d34);
    }
    
    public void rotatePolyWorldZ(double d)
    {
        double d0 = Math.cos(d);
        double d1 = Math.sin(d);
        Matrix a = this.polyWorldXform;
        double d2 = a.getData(0, 0);
        double d3 = d0 * d2;
        double d4 = -d1;
        Matrix a0 = this.polyWorldXform;
        double d5 = a0.getData(0, 1);
        double d6 = d4 * d5;
        double d7 = d3 + d6;
        Matrix a1 = this.polyWorldXform;
        double d8 = a1.getData(1, 0);
        double d9 = d0 * d8;
        double d10 = -d1;
        Matrix a2 = this.polyWorldXform;
        double d11 = a2.getData(1, 1);
        double d12 = d10 * d11;
        double d13 = d9 + d12;
        Matrix a3 = this.polyWorldXform;
        double d14 = a3.getData(2, 0);
        double d15 = d0 * d14;
        double d16 = -d1;
        Matrix a4 = this.polyWorldXform;
        double d17 = a4.getData(2, 1);
        double d18 = d16 * d17;
        double d19 = d15 + d18;
        Matrix a5 = this.polyWorldXform;
        double d20 = a5.getData(0, 0);
        double d21 = d1 * d20;
        Matrix a6 = this.polyWorldXform;
        double d22 = a6.getData(0, 1);
        double d23 = d0 * d22;
        double d24 = d21 + d23;
        Matrix a7 = this.polyWorldXform;
        double d25 = a7.getData(1, 0);
        double d26 = d1 * d25;
        Matrix a8 = this.polyWorldXform;
        double d27 = a8.getData(1, 1);
        double d28 = d0 * d27;
        double d29 = d26 + d28;
        Matrix a9 = this.polyWorldXform;
        double d30 = a9.getData(2, 0);
        double d31 = d1 * d30;
        Matrix a10 = this.polyWorldXform;
        double d32 = a10.getData(2, 1);
        double d33 = d0 * d32;
        double d34 = d31 + d33;
        Matrix a11 = this.polyWorldXform;
        a11.setData(0, 0, d7);
        Matrix a12 = this.polyWorldXform;
        a12.setData(1, 0, d13);
        Matrix a13 = this.polyWorldXform;
        a13.setData(2, 0, d19);
        Matrix a14 = this.polyWorldXform;
        a14.setData(0, 1, d24);
        Matrix a15 = this.polyWorldXform;
        a15.setData(1, 1, d29);
        Matrix a16 = this.polyWorldXform;
        a16.setData(2, 1, d34);
    }
    
    public void setPolyWorld(Matrix a)
    {
        this.polyWorldXform = a;
    }
    
    public void setTranslation(int i, int i0)
    {
        this.translateX = i;
        this.translateY = i0;
    }
    
    public void setWorldView(Matrix a)
    {
        this.worldViewXform = a;
    }
    
    public void translatePolyWorldX(double d)
    {
        Matrix a = this.polyWorldXform;
        a.setData(3, 0, d);
    }
    
    public void translatePolyWorldY(double d)
    {
        Matrix a = this.polyWorldXform;
        a.setData(3, 1, d);
    }
    
    public void translatePolyWorldZ(double d)
    {
        Matrix a = this.polyWorldXform;
        double d0 = d + -200.0;
        a.setData(3, 2, d0);
    }
}