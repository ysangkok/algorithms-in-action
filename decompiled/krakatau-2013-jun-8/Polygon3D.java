public class Polygon3D {
    java.util.Vector points;
    
    public Polygon3D()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.points = a;
    }
    
    public void addPoint(Point3D a)
    {
        java.util.Vector a0 = this.points;
        a0.addElement((Object)a);
    }
    
    public Point3D getPoint(int i)
    {
        java.util.Vector a = this.points;
        Object a0 = a.elementAt(i);
        Point3D dummy = (Point3D)a0;
        Point3D a1 = (Point3D)a0;
        return a1;
    }
    
    public int size()
    {
        java.util.Vector a = this.points;
        int i = a.size();
        return i;
    }
}