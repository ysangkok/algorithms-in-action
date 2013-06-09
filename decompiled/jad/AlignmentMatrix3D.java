// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentMatrix3D.java

import com.cim.AIA.Drawable;
import java.awt.*;

public class AlignmentMatrix3D
    implements Drawable
{

    public AlignmentMatrix3D()
    {
        HIGHLIGHT_COLOR = Color.red;
        IRRELEVANT_COLOR = Color.lightGray;
        NOTHING_COLOR = new Color(240, 240, 240);
        DARKENED_COLOR = new Color(79, 147, 205);
        DARKENIRRELEVANT_COLOR = new Color(120, 200, 255);
        DISABLE_COLOR = Color.gray;
        theMatrix = new Polygon3D[3][3][3];
        layerColor = new Color[3][3][3];
        fillColor = new Color[3][3][3];
        theWorld = new World3D();
        isVisible = false;
        prebuiltPolygons = new Polygon[27];
        theWorld.initialisePolyWorld();
        theWorld.initialiseWorldView();
        rotateX(180D);
        rotateY(-1D);
        translateX(-100D);
        translateZ(-100D);
        xMove = 250;
        yMove = 60;
        theWorld.setTranslation(xMove, yMove);
        createArray();
        initialiseColor();
        height = width = 0;
        for(int n = 0; n < 27; n++)
        {
            prebuiltPolygons[n] = new Polygon();
            prebuiltPolygons[n].addPoint(arrayX[4 * n], arrayY[4 * n]);
            prebuiltPolygons[n].addPoint(arrayX[4 * n + 1], arrayY[4 * n + 1]);
            prebuiltPolygons[n].addPoint(arrayX[4 * n + 2], arrayY[4 * n + 2]);
            prebuiltPolygons[n].addPoint(arrayX[4 * n + 3], arrayY[4 * n + 3]);
        }

    }

    private void createArray()
    {
        double size = 20D;
        double gaps = 20D;
        double zGap = 20D;
        double originZ = -20D;
        for(int z = 0; z < 3; z++)
        {
            double originY = -50D;
            for(int y = 0; y < 3; y++)
            {
                double originX = -50D;
                for(int x = 0; x < 3; x++)
                {
                    theMatrix[x][y][z] = new Polygon3D();
                    theMatrix[x][y][z].addPoint(new Point3D(originX, originY, originZ, 1.0D));
                    theMatrix[x][y][z].addPoint(new Point3D(originX, originY + size, originZ, 1.0D));
                    theMatrix[x][y][z].addPoint(new Point3D(originX + size, originY + size, originZ, 1.0D));
                    theMatrix[x][y][z].addPoint(new Point3D(originX + size, originY, originZ, 1.0D));
                    originX = originX + gaps + size;
                }

                originY = originY + gaps + size;
            }

            originZ += zGap;
        }

    }

    public void darkenX(int z)
    {
        layerColor[1][1][z] = DARKENED_COLOR;
        layerColor[1][2][z] = DARKENED_COLOR;
        layerColor[2][1][z] = DARKENED_COLOR;
        layerColor[2][2][z] = DARKENED_COLOR;
        layerColor[0][0][z] = DARKENIRRELEVANT_COLOR;
        layerColor[1][0][z] = DARKENIRRELEVANT_COLOR;
        layerColor[2][0][z] = DARKENIRRELEVANT_COLOR;
        layerColor[0][1][z] = DARKENIRRELEVANT_COLOR;
        layerColor[0][2][z] = DARKENIRRELEVANT_COLOR;
    }

    public void disable(int x, int y)
    {
        disable(x, y, 0);
        disable(x, y, 1);
        disable(x, y, 2);
    }

    public void disable(int x, int y, int z)
    {
        fillColor(x, y, z, DISABLE_COLOR);
    }

    public void draw(Graphics g)
    {
        if(!isVisible)
            return;
        for(int z = 2; z >= 0; z--)
        {
            for(int y = 0; y < 3; y++)
            {
                for(int x = 0; x < 3; x++)
                {
                    Polygon projectedPolygon = prebuiltPolygons[(2 - z) * 9 + y * 3 + x];
                    if(fillColor[x][y][z] != null)
                    {
                        g.setColor(fillColor[x][y][z]);
                        g.fillPolygon(projectedPolygon);
                    }
                    g.setColor(layerColor[x][y][z]);
                    g.drawPolygon(projectedPolygon);
                }

            }

        }

    }

    public void draw(Graphics g, Point p)
    {
        theWorld.setTranslation(p.x + xMove, p.y + yMove);
        draw(g);
    }

    public void fillColor(int x, int y, int z, Color color)
    {
        fillColor[x][y][z] = color;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void highlightA()
    {
        highlightX(0);
    }

    public void highlightB()
    {
        highlightX(1);
    }

    public void highlightC()
    {
        highlightX(2);
    }

    public void highlightX(int z)
    {
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
                layerColor[x][y][z] = HIGHLIGHT_COLOR;

        }

    }

    public void highlightX(int x, int y)
    {
        for(int z = 0; z < 3; z++)
            layerColor[x][y][z] = HIGHLIGHT_COLOR;

    }

    public void highlightX(int x, int y, int z)
    {
        layerColor[x][y][z] = HIGHLIGHT_COLOR;
    }

    public void initialiseColor()
    {
        for(int z = 0; z < 3; z++)
        {
            for(int y = 0; y < 3; y++)
            {
                for(int x = 0; x < 3; x++)
                {
                    layerColor[x][y][z] = IRRELEVANT_COLOR;
                    fillColor[x][y][z] = Color.white;
                }

            }

        }

        for(int z = 0; z < 3; z++)
        {
            layerColor[0][0][z] = NOTHING_COLOR;
            layerColor[0][1][z] = NOTHING_COLOR;
            layerColor[0][2][z] = NOTHING_COLOR;
            layerColor[1][0][z] = NOTHING_COLOR;
            layerColor[2][0][z] = NOTHING_COLOR;
        }

    }

    public void irrelevantA()
    {
        irrelevantX(0);
    }

    public void irrelevantAll()
    {
        irrelevantX(0);
        irrelevantX(1);
        irrelevantX(2);
    }

    public void irrelevantB()
    {
        irrelevantX(1);
    }

    public void irrelevantC()
    {
        irrelevantX(2);
    }

    public void irrelevantX(int z)
    {
        layerColor[0][0][z] = NOTHING_COLOR;
        layerColor[0][1][z] = NOTHING_COLOR;
        layerColor[0][2][z] = NOTHING_COLOR;
        layerColor[1][0][z] = NOTHING_COLOR;
        layerColor[2][0][z] = NOTHING_COLOR;
    }

    public void moveX(int translation)
    {
        xMove = translation;
        theWorld.setTranslation(xMove, yMove);
    }

    public void rotateX(double degrees)
    {
        theWorld.rotatePolyWorldX(0.017453292519943295D * degrees);
    }

    public void rotateY(double degrees)
    {
        theWorld.rotatePolyWorldY(0.017453292519943295D * degrees);
    }

    public void rotateZ(double degrees)
    {
        theWorld.rotatePolyWorldZ(0.017453292519943295D * degrees);
    }

    public void setColor(int x, int y, int z, Color color)
    {
        layerColor[x][y][z] = color;
    }

    public void setLocation(int x, int y)
    {
        theWorld.setTranslation(x + xMove, y + yMove);
    }

    public void setVisible(boolean state)
    {
        isVisible = state;
    }

    public void translateX(double translation)
    {
        theWorld.translatePolyWorldX(translation);
    }

    public void translateY(double translation)
    {
        theWorld.translatePolyWorldY(translation);
    }

    public void translateZ(double translation)
    {
        theWorld.translatePolyWorldZ(translation);
    }

    public void unDarkenX(int z)
    {
        layerColor[1][1][z] = IRRELEVANT_COLOR;
        layerColor[1][2][z] = IRRELEVANT_COLOR;
        layerColor[2][1][z] = IRRELEVANT_COLOR;
        layerColor[2][2][z] = IRRELEVANT_COLOR;
        layerColor[0][0][z] = NOTHING_COLOR;
        layerColor[1][0][z] = NOTHING_COLOR;
        layerColor[2][0][z] = NOTHING_COLOR;
        layerColor[0][1][z] = NOTHING_COLOR;
        layerColor[0][2][z] = NOTHING_COLOR;
    }

    public void unHighlightX(int z)
    {
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
                layerColor[x][y][z] = IRRELEVANT_COLOR;

        }

    }

    public void unHighlightX(int x, int y)
    {
        for(int z = 0; z < 3; z++)
            layerColor[x][y][z] = IRRELEVANT_COLOR;

    }

    public void unHighlightX(int x, int y, int z)
    {
        layerColor[x][y][z] = IRRELEVANT_COLOR;
    }

    private Color HIGHLIGHT_COLOR;
    private Color IRRELEVANT_COLOR;
    private Color NOTHING_COLOR;
    private Color DARKENED_COLOR;
    private Color DARKENIRRELEVANT_COLOR;
    private Color DISABLE_COLOR;
    private Polygon3D theMatrix[][][];
    private Color layerColor[][][];
    private Color fillColor[][][];
    private World3D theWorld;
    private final double DEFAULT_SIZE = 20D;
    private final double DEFAULT_GAP = 20D;
    private final double DEFAULT_ZGAP = 20D;
    private final double DEFAULT_X = -50D;
    private final double DEFAULT_Y = -50D;
    private final double DEFAULT_Z = -20D;
    private int height;
    private int width;
    private int xMove;
    private int yMove;
    private boolean isVisible;
    int arrayX[] = {
        88, 88, 119, 119, 150, 150, 181, 181, 212, 212, 
        243, 243, 88, 88, 119, 119, 150, 150, 181, 181, 
        212, 212, 243, 243, 88, 88, 119, 119, 150, 150, 
        181, 181, 212, 212, 243, 243, 72, 72, 105, 105, 
        138, 138, 171, 171, 204, 204, 237, 237, 72, 72, 
        105, 105, 138, 138, 171, 171, 204, 204, 237, 237, 
        72, 72, 105, 105, 138, 138, 171, 171, 204, 204, 
        237, 237, 53, 53, 89, 89, 124, 124, 160, 160, 
        195, 195, 231, 231, 53, 53, 89, 89, 124, 124, 
        160, 160, 195, 195, 231, 231, 53, 53, 89, 89, 
        124, 124, 160, 160, 195, 195, 231, 231
    };
    int arrayY[] = {
        418, 449, 449, 418, 418, 449, 449, 418, 418, 449, 
        449, 418, 479, 511, 511, 479, 479, 511, 511, 479, 
        479, 511, 511, 479, 542, 573, 573, 542, 542, 573, 
        573, 542, 542, 573, 573, 542, 413, 446, 446, 413, 
        413, 446, 446, 413, 413, 446, 446, 412, 478, 512, 
        512, 478, 478, 512, 512, 478, 478, 512, 512, 478, 
        545, 578, 578, 545, 545, 578, 578, 545, 545, 578, 
        579, 545, 407, 443, 443, 407, 407, 442, 442, 407, 
        407, 442, 442, 406, 477, 513, 513, 477, 477, 513, 
        513, 477, 477, 513, 513, 477, 548, 584, 584, 548, 
        549, 584, 584, 549, 549, 584, 585, 549
    };
    Polygon prebuiltPolygons[];
}
