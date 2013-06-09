import java.util.*;

public class Polygon3D
{
    Vector<Point3D> points;
    
    public Polygon3D() {
        super();
        this.points = new Vector();
    }
    
    public void addPoint(final Point3D thePoint) {
        this.points.addElement(thePoint);
    }
    
    public Point3D getPoint(final int index) {
        return (Point3D)this.points.elementAt(index);
    }
    
    public int size() {
        return this.points.size();
    }
}
