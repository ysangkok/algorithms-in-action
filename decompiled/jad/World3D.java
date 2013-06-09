// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   World3D.java

import java.awt.Point;
import java.awt.Polygon;
import java.io.PrintStream;

public class World3D
{

    public World3D()
    {
        translateX = 0;
        translateY = 0;
        polyWorldXform = new Matrix(4, 4);
        worldViewXform = new Matrix(4, 4);
        initialisePolyWorld();
        initialiseWorldView();
    }

    public World3D(Matrix polyWorld, Matrix worldView)
    {
        translateX = 0;
        translateY = 0;
        polyWorldXform = polyWorld;
        worldViewXform = worldView;
    }

    public void calculateFinalXform()
    {
        finalXform = worldViewXform.multiplyMatrix(polyWorldXform);
    }

    public Matrix getPolyWorld()
    {
        return polyWorldXform;
    }

    public Matrix getWorldView()
    {
        return worldViewXform;
    }

    public void initialisePolyWorld()
    {
        polyWorldXform.setData(0, 0, 1.0D);
        polyWorldXform.setData(0, 1, 0.0D);
        polyWorldXform.setData(0, 2, 0.0D);
        polyWorldXform.setData(0, 3, 0.0D);
        polyWorldXform.setData(1, 0, 0.0D);
        polyWorldXform.setData(1, 1, 1.0D);
        polyWorldXform.setData(1, 2, 0.0D);
        polyWorldXform.setData(1, 3, 0.0D);
        polyWorldXform.setData(2, 0, 0.0D);
        polyWorldXform.setData(2, 1, 0.0D);
        polyWorldXform.setData(2, 2, 1.0D);
        polyWorldXform.setData(2, 3, 0.0D);
        polyWorldXform.setData(3, 0, 0.0D);
        polyWorldXform.setData(3, 1, 0.0D);
        polyWorldXform.setData(3, 2, -200D);
        polyWorldXform.setData(3, 3, 1.0D);
    }

    public void initialiseWorldView()
    {
        worldViewXform.setData(0, 0, 1.0D);
        worldViewXform.setData(0, 1, 0.0D);
        worldViewXform.setData(0, 2, 0.0D);
        worldViewXform.setData(0, 3, 0.0D);
        worldViewXform.setData(1, 0, 0.0D);
        worldViewXform.setData(1, 1, 1.0D);
        worldViewXform.setData(1, 2, 0.0D);
        worldViewXform.setData(1, 3, 0.0D);
        worldViewXform.setData(2, 0, 0.0D);
        worldViewXform.setData(2, 1, 0.0D);
        worldViewXform.setData(2, 2, 1.0D);
        worldViewXform.setData(2, 3, 0.0D);
        worldViewXform.setData(3, 0, 0.0D);
        worldViewXform.setData(3, 1, 0.0D);
        worldViewXform.setData(3, 2, 0.0D);
        worldViewXform.setData(3, 3, 1.0D);
    }

    private Point3D matrixToPoint(Matrix theMatrix)
    {
        Point3D result = new Point3D();
        result.x = theMatrix.getData(0, 0);
        result.y = theMatrix.getData(0, 1);
        result.z = theMatrix.getData(0, 2);
        result.w = theMatrix.getData(0, 3);
        return result;
    }

    private Matrix pointToMatrix(Point3D thePoint)
    {
        Matrix result = new Matrix(1, 4);
        result.setData(0, 0, thePoint.x);
        result.setData(0, 1, thePoint.y);
        result.setData(0, 2, thePoint.z);
        result.setData(0, 3, thePoint.w);
        return result;
    }

    public Polygon projectPolygon(Polygon3D polygon)
    {
        Polygon projectedPoly = new Polygon();
        for(int i = 0; i < polygon.size(); i++)
        {
            Matrix tempMatrix = pointToMatrix(polygon.getPoint(i));
            Matrix transformedMatrix = finalXform.multiplyMatrix(tempMatrix);
            Point3D transformedPoint = matrixToPoint(transformedMatrix);
            Point projectedPoint = new Point();
            projectedPoint.x = (int)((transformedPoint.x / transformedPoint.z) * -10D * 50D + 0.5D + 50D);
            projectedPoint.y = (int)((transformedPoint.y / transformedPoint.z) * -1D * -10D * 50D + 0.5D + 40D);
            System.out.println((new StringBuilder()).append("X = ").append(projectedPoint.x + translateX).append(" Y = ").append(projectedPoint.y + translateY).toString());
            projectedPoly.addPoint(projectedPoint.x + translateX, projectedPoint.y + translateY);
        }

        return projectedPoly;
    }

    public void resetPolyWorldRotation()
    {
        polyWorldXform.setData(0, 0, 1.0D);
        polyWorldXform.setData(0, 1, 0.0D);
        polyWorldXform.setData(0, 2, 0.0D);
        polyWorldXform.setData(1, 0, 0.0D);
        polyWorldXform.setData(1, 1, 1.0D);
        polyWorldXform.setData(1, 2, 0.0D);
        polyWorldXform.setData(2, 0, 0.0D);
        polyWorldXform.setData(2, 1, 0.0D);
        polyWorldXform.setData(2, 2, 1.0D);
    }

    public void resetPolyWorldTransformation()
    {
        polyWorldXform.setData(3, 0, 0.0D);
        polyWorldXform.setData(3, 1, 0.0D);
        polyWorldXform.setData(3, 2, 0.0D);
    }

    public void resetWorldViewRotation()
    {
        worldViewXform.setData(0, 0, 1.0D);
        worldViewXform.setData(0, 1, 0.0D);
        worldViewXform.setData(0, 2, 0.0D);
        worldViewXform.setData(1, 0, 0.0D);
        worldViewXform.setData(1, 1, 1.0D);
        worldViewXform.setData(1, 2, 0.0D);
        worldViewXform.setData(2, 0, 0.0D);
        worldViewXform.setData(2, 1, 0.0D);
        worldViewXform.setData(2, 2, 1.0D);
    }

    public void resetWorldViewTransformation()
    {
        worldViewXform.setData(3, 0, 0.0D);
        worldViewXform.setData(3, 1, 0.0D);
        worldViewXform.setData(3, 2, 0.0D);
    }

    public void rotatePolyWorldX(double rotation)
    {
        double cosTemp = Math.cos(rotation);
        double sinTemp = Math.sin(rotation);
        double temp10 = cosTemp * polyWorldXform.getData(0, 1) + -sinTemp * polyWorldXform.getData(0, 2);
        double temp11 = cosTemp * polyWorldXform.getData(1, 1) + -sinTemp * polyWorldXform.getData(1, 2);
        double temp12 = cosTemp * polyWorldXform.getData(2, 1) + -sinTemp * polyWorldXform.getData(2, 2);
        double temp20 = sinTemp * polyWorldXform.getData(0, 1) + cosTemp * polyWorldXform.getData(0, 2);
        double temp21 = sinTemp * polyWorldXform.getData(1, 1) + cosTemp * polyWorldXform.getData(1, 2);
        double temp22 = sinTemp * polyWorldXform.getData(2, 1) + cosTemp * polyWorldXform.getData(2, 2);
        polyWorldXform.setData(0, 1, temp10);
        polyWorldXform.setData(1, 1, temp11);
        polyWorldXform.setData(2, 1, temp12);
        polyWorldXform.setData(0, 2, temp20);
        polyWorldXform.setData(1, 2, temp21);
        polyWorldXform.setData(2, 2, temp22);
    }

    public void rotatePolyWorldY(double rotation)
    {
        double cosTemp = Math.cos(rotation);
        double sinTemp = Math.sin(rotation);
        double temp00 = cosTemp * polyWorldXform.getData(0, 0) + sinTemp * polyWorldXform.getData(0, 2);
        double temp01 = cosTemp * polyWorldXform.getData(1, 0) + sinTemp * polyWorldXform.getData(1, 2);
        double temp02 = cosTemp * polyWorldXform.getData(2, 0) + sinTemp * polyWorldXform.getData(2, 2);
        double temp20 = -sinTemp * polyWorldXform.getData(0, 0) + cosTemp * polyWorldXform.getData(0, 2);
        double temp21 = -sinTemp * polyWorldXform.getData(1, 0) + cosTemp * polyWorldXform.getData(1, 2);
        double temp22 = -sinTemp * polyWorldXform.getData(2, 0) + cosTemp * polyWorldXform.getData(2, 2);
        polyWorldXform.setData(0, 0, temp00);
        polyWorldXform.setData(1, 0, temp01);
        polyWorldXform.setData(2, 0, temp02);
        polyWorldXform.setData(0, 2, temp20);
        polyWorldXform.setData(1, 2, temp21);
        polyWorldXform.setData(2, 2, temp22);
    }

    public void rotatePolyWorldZ(double rotation)
    {
        double cosTemp = Math.cos(rotation);
        double sinTemp = Math.sin(rotation);
        double temp00 = cosTemp * polyWorldXform.getData(0, 0) + -sinTemp * polyWorldXform.getData(0, 1);
        double temp01 = cosTemp * polyWorldXform.getData(1, 0) + -sinTemp * polyWorldXform.getData(1, 1);
        double temp02 = cosTemp * polyWorldXform.getData(2, 0) + -sinTemp * polyWorldXform.getData(2, 1);
        double temp10 = sinTemp * polyWorldXform.getData(0, 0) + cosTemp * polyWorldXform.getData(0, 1);
        double temp11 = sinTemp * polyWorldXform.getData(1, 0) + cosTemp * polyWorldXform.getData(1, 1);
        double temp12 = sinTemp * polyWorldXform.getData(2, 0) + cosTemp * polyWorldXform.getData(2, 1);
        polyWorldXform.setData(0, 0, temp00);
        polyWorldXform.setData(1, 0, temp01);
        polyWorldXform.setData(2, 0, temp02);
        polyWorldXform.setData(0, 1, temp10);
        polyWorldXform.setData(1, 1, temp11);
        polyWorldXform.setData(2, 1, temp12);
    }

    public void setPolyWorld(Matrix newPolyWorld)
    {
        polyWorldXform = newPolyWorld;
    }

    public void setTranslation(int x, int y)
    {
        translateX = x;
        translateY = y;
    }

    public void setWorldView(Matrix newWorldView)
    {
        worldViewXform = newWorldView;
    }

    public void translatePolyWorldX(double translation)
    {
        polyWorldXform.setData(3, 0, translation);
    }

    public void translatePolyWorldY(double translation)
    {
        polyWorldXform.setData(3, 1, translation);
    }

    public void translatePolyWorldZ(double translation)
    {
        polyWorldXform.setData(3, 2, translation + -200D);
    }

    private Matrix polyWorldXform;
    private Matrix worldViewXform;
    private Matrix finalXform;
    private int translateX;
    private int translateY;
    private final double SCREEN_WIDTH = 100D;
    private final double SCREEN_HEIGHT = 80D;
    private final double PROJECTION_RATIO = -10D;
    private final double ZADJUSTMENT = -200D;
}
