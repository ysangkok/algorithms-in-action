public class Point3D {
    public double x;
    public double y;
    public double z;
    public double w;
    
    public Point3D()
    {
        this(0.0, 0.0, 0.0, 0.0);
    }
    
    public Point3D(double d, double d0, double d1)
    {
        this(d, d0, d1, 1.0);
    }
    
    public Point3D(double d, double d0, double d1, double d2)
    {
        super();
        this.x = d;
        this.y = d0;
        this.z = d1;
        this.w = d2;
    }
}