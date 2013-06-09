import java.awt.*;

public class World3D
{
    private Matrix polyWorldXform;
    private Matrix worldViewXform;
    private Matrix finalXform;
    private int translateX;
    private int translateY;
    private final double SCREEN_WIDTH = 100.0;
    private final double SCREEN_HEIGHT = 80.0;
    private final double PROJECTION_RATIO = -10.0;
    private final double ZADJUSTMENT = -200.0;
    
    public World3D() {
        super();
        this.translateX = 0;
        this.translateY = 0;
        this.SCREEN_WIDTH = 100.0;
        this.SCREEN_HEIGHT = 80.0;
        this.PROJECTION_RATIO = -10.0;
        this.ZADJUSTMENT = -200.0;
        this.polyWorldXform = new Matrix(4, 4);
        this.worldViewXform = new Matrix(4, 4);
        this.initialisePolyWorld();
        this.initialiseWorldView();
    }
    
    public World3D(final Matrix polyWorld, final Matrix worldView) {
        super();
        this.translateX = 0;
        this.translateY = 0;
        this.SCREEN_WIDTH = 100.0;
        this.SCREEN_HEIGHT = 80.0;
        this.PROJECTION_RATIO = -10.0;
        this.ZADJUSTMENT = -200.0;
        this.polyWorldXform = polyWorld;
        this.worldViewXform = worldView;
    }
    
    public void calculateFinalXform() {
        this.finalXform = this.worldViewXform.multiplyMatrix(this.polyWorldXform);
    }
    
    public Matrix getPolyWorld() {
        return this.polyWorldXform;
    }
    
    public Matrix getWorldView() {
        return this.worldViewXform;
    }
    
    public void initialisePolyWorld() {
        this.polyWorldXform.setData(0, 0, 1.0);
        this.polyWorldXform.setData(0, 1, 0.0);
        this.polyWorldXform.setData(0, 2, 0.0);
        this.polyWorldXform.setData(0, 3, 0.0);
        this.polyWorldXform.setData(1, 0, 0.0);
        this.polyWorldXform.setData(1, 1, 1.0);
        this.polyWorldXform.setData(1, 2, 0.0);
        this.polyWorldXform.setData(1, 3, 0.0);
        this.polyWorldXform.setData(2, 0, 0.0);
        this.polyWorldXform.setData(2, 1, 0.0);
        this.polyWorldXform.setData(2, 2, 1.0);
        this.polyWorldXform.setData(2, 3, 0.0);
        this.polyWorldXform.setData(3, 0, 0.0);
        this.polyWorldXform.setData(3, 1, 0.0);
        this.polyWorldXform.setData(3, 2, -200.0);
        this.polyWorldXform.setData(3, 3, 1.0);
    }
    
    public void initialiseWorldView() {
        this.worldViewXform.setData(0, 0, 1.0);
        this.worldViewXform.setData(0, 1, 0.0);
        this.worldViewXform.setData(0, 2, 0.0);
        this.worldViewXform.setData(0, 3, 0.0);
        this.worldViewXform.setData(1, 0, 0.0);
        this.worldViewXform.setData(1, 1, 1.0);
        this.worldViewXform.setData(1, 2, 0.0);
        this.worldViewXform.setData(1, 3, 0.0);
        this.worldViewXform.setData(2, 0, 0.0);
        this.worldViewXform.setData(2, 1, 0.0);
        this.worldViewXform.setData(2, 2, 1.0);
        this.worldViewXform.setData(2, 3, 0.0);
        this.worldViewXform.setData(3, 0, 0.0);
        this.worldViewXform.setData(3, 1, 0.0);
        this.worldViewXform.setData(3, 2, 0.0);
        this.worldViewXform.setData(3, 3, 1.0);
    }
    
    private Point3D matrixToPoint(final Matrix theMatrix) {
        final Point3D result = new Point3D();
        result.x = theMatrix.getData(0, 0);
        result.y = theMatrix.getData(0, 1);
        result.z = theMatrix.getData(0, 2);
        result.w = theMatrix.getData(0, 3);
        return result;
    }
    
    private Matrix pointToMatrix(final Point3D thePoint) {
        final Matrix result = new Matrix(1, 4);
        result.setData(0, 0, thePoint.x);
        result.setData(0, 1, thePoint.y);
        result.setData(0, 2, thePoint.z);
        result.setData(0, 3, thePoint.w);
        return result;
    }
    
    public Polygon projectPolygon(final Polygon3D polygon) {
        final Polygon projectedPoly = new Polygon();
        for (int i = 0; i < polygon.size(); ++i) {
            final Matrix tempMatrix = this.pointToMatrix(polygon.getPoint(i));
            final Matrix transformedMatrix = this.finalXform.multiplyMatrix(tempMatrix);
            final Point3D transformedPoint = this.matrixToPoint(transformedMatrix);
            final Point projectedPoint = new Point();
            projectedPoint.x = (int)(transformedPoint.x / transformedPoint.z * -10.0 * 50.0 + 0.5 + 50.0);
            projectedPoint.y = (int)(transformedPoint.y / transformedPoint.z * -1.0 * -10.0 * 50.0 + 0.5 + 40.0);
            System.out.println("X = " + (projectedPoint.x + this.translateX) + " Y = " + (projectedPoint.y + this.translateY));
            projectedPoly.addPoint(projectedPoint.x + this.translateX, projectedPoint.y + this.translateY);
        }
        return projectedPoly;
    }
    
    public void resetPolyWorldRotation() {
        this.polyWorldXform.setData(0, 0, 1.0);
        this.polyWorldXform.setData(0, 1, 0.0);
        this.polyWorldXform.setData(0, 2, 0.0);
        this.polyWorldXform.setData(1, 0, 0.0);
        this.polyWorldXform.setData(1, 1, 1.0);
        this.polyWorldXform.setData(1, 2, 0.0);
        this.polyWorldXform.setData(2, 0, 0.0);
        this.polyWorldXform.setData(2, 1, 0.0);
        this.polyWorldXform.setData(2, 2, 1.0);
    }
    
    public void resetPolyWorldTransformation() {
        this.polyWorldXform.setData(3, 0, 0.0);
        this.polyWorldXform.setData(3, 1, 0.0);
        this.polyWorldXform.setData(3, 2, 0.0);
    }
    
    public void resetWorldViewRotation() {
        this.worldViewXform.setData(0, 0, 1.0);
        this.worldViewXform.setData(0, 1, 0.0);
        this.worldViewXform.setData(0, 2, 0.0);
        this.worldViewXform.setData(1, 0, 0.0);
        this.worldViewXform.setData(1, 1, 1.0);
        this.worldViewXform.setData(1, 2, 0.0);
        this.worldViewXform.setData(2, 0, 0.0);
        this.worldViewXform.setData(2, 1, 0.0);
        this.worldViewXform.setData(2, 2, 1.0);
    }
    
    public void resetWorldViewTransformation() {
        this.worldViewXform.setData(3, 0, 0.0);
        this.worldViewXform.setData(3, 1, 0.0);
        this.worldViewXform.setData(3, 2, 0.0);
    }
    
    public void rotatePolyWorldX(final double rotation) {
        final double cosTemp = Math.cos(rotation);
        final double sinTemp = Math.sin(rotation);
        final double temp10 = cosTemp * this.polyWorldXform.getData(0, 1) + -sinTemp * this.polyWorldXform.getData(0, 2);
        final double temp11 = cosTemp * this.polyWorldXform.getData(1, 1) + -sinTemp * this.polyWorldXform.getData(1, 2);
        final double temp12 = cosTemp * this.polyWorldXform.getData(2, 1) + -sinTemp * this.polyWorldXform.getData(2, 2);
        final double temp20 = sinTemp * this.polyWorldXform.getData(0, 1) + cosTemp * this.polyWorldXform.getData(0, 2);
        final double temp21 = sinTemp * this.polyWorldXform.getData(1, 1) + cosTemp * this.polyWorldXform.getData(1, 2);
        final double temp22 = sinTemp * this.polyWorldXform.getData(2, 1) + cosTemp * this.polyWorldXform.getData(2, 2);
        this.polyWorldXform.setData(0, 1, temp10);
        this.polyWorldXform.setData(1, 1, temp11);
        this.polyWorldXform.setData(2, 1, temp12);
        this.polyWorldXform.setData(0, 2, temp20);
        this.polyWorldXform.setData(1, 2, temp21);
        this.polyWorldXform.setData(2, 2, temp22);
    }
    
    public void rotatePolyWorldY(final double rotation) {
        final double cosTemp = Math.cos(rotation);
        final double sinTemp = Math.sin(rotation);
        final double temp00 = cosTemp * this.polyWorldXform.getData(0, 0) + sinTemp * this.polyWorldXform.getData(0, 2);
        final double temp01 = cosTemp * this.polyWorldXform.getData(1, 0) + sinTemp * this.polyWorldXform.getData(1, 2);
        final double temp02 = cosTemp * this.polyWorldXform.getData(2, 0) + sinTemp * this.polyWorldXform.getData(2, 2);
        final double temp20 = -sinTemp * this.polyWorldXform.getData(0, 0) + cosTemp * this.polyWorldXform.getData(0, 2);
        final double temp21 = -sinTemp * this.polyWorldXform.getData(1, 0) + cosTemp * this.polyWorldXform.getData(1, 2);
        final double temp22 = -sinTemp * this.polyWorldXform.getData(2, 0) + cosTemp * this.polyWorldXform.getData(2, 2);
        this.polyWorldXform.setData(0, 0, temp00);
        this.polyWorldXform.setData(1, 0, temp01);
        this.polyWorldXform.setData(2, 0, temp02);
        this.polyWorldXform.setData(0, 2, temp20);
        this.polyWorldXform.setData(1, 2, temp21);
        this.polyWorldXform.setData(2, 2, temp22);
    }
    
    public void rotatePolyWorldZ(final double rotation) {
        final double cosTemp = Math.cos(rotation);
        final double sinTemp = Math.sin(rotation);
        final double temp00 = cosTemp * this.polyWorldXform.getData(0, 0) + -sinTemp * this.polyWorldXform.getData(0, 1);
        final double temp01 = cosTemp * this.polyWorldXform.getData(1, 0) + -sinTemp * this.polyWorldXform.getData(1, 1);
        final double temp02 = cosTemp * this.polyWorldXform.getData(2, 0) + -sinTemp * this.polyWorldXform.getData(2, 1);
        final double temp10 = sinTemp * this.polyWorldXform.getData(0, 0) + cosTemp * this.polyWorldXform.getData(0, 1);
        final double temp11 = sinTemp * this.polyWorldXform.getData(1, 0) + cosTemp * this.polyWorldXform.getData(1, 1);
        final double temp12 = sinTemp * this.polyWorldXform.getData(2, 0) + cosTemp * this.polyWorldXform.getData(2, 1);
        this.polyWorldXform.setData(0, 0, temp00);
        this.polyWorldXform.setData(1, 0, temp01);
        this.polyWorldXform.setData(2, 0, temp02);
        this.polyWorldXform.setData(0, 1, temp10);
        this.polyWorldXform.setData(1, 1, temp11);
        this.polyWorldXform.setData(2, 1, temp12);
    }
    
    public void setPolyWorld(final Matrix newPolyWorld) {
        this.polyWorldXform = newPolyWorld;
    }
    
    public void setTranslation(final int x, final int y) {
        this.translateX = x;
        this.translateY = y;
    }
    
    public void setWorldView(final Matrix newWorldView) {
        this.worldViewXform = newWorldView;
    }
    
    public void translatePolyWorldX(final double translation) {
        this.polyWorldXform.setData(3, 0, translation);
    }
    
    public void translatePolyWorldY(final double translation) {
        this.polyWorldXform.setData(3, 1, translation);
    }
    
    public void translatePolyWorldZ(final double translation) {
        this.polyWorldXform.setData(3, 2, translation + -200.0);
    }
}
