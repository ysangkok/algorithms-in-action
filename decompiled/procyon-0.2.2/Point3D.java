public class Point3D
{
    public double x;
    public double y;
    public double z;
    public double w;
    
    public Point3D() {
        this(0.0, 0.0, 0.0, 0.0);
    }
    
    public Point3D(final double x, final double y, final double z) {
        this(x, y, z, 1.0);
    }
    
    public Point3D(final double x, final double y, final double z, final double w) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
