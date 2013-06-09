import java.awt.*;
import com.cim.AIA.*;

public class AlignmentMatrix3D implements Drawable
{
    private Color HIGHLIGHT_COLOR;
    private Color IRRELEVANT_COLOR;
    private Color NOTHING_COLOR;
    private Color DARKENED_COLOR;
    private Color DARKENIRRELEVANT_COLOR;
    private Color DISABLE_COLOR;
    private Polygon3D[][][] theMatrix;
    private Color[][][] layerColor;
    private Color[][][] fillColor;
    private World3D theWorld;
    private final double DEFAULT_SIZE = 20.0;
    private final double DEFAULT_GAP = 20.0;
    private final double DEFAULT_ZGAP = 20.0;
    private final double DEFAULT_X = -50.0;
    private final double DEFAULT_Y = -50.0;
    private final double DEFAULT_Z = -20.0;
    private int height;
    private int width;
    private int xMove;
    private int yMove;
    private boolean isVisible;
    int[] arrayX;
    int[] arrayY;
    Polygon[] prebuiltPolygons;
    
    public AlignmentMatrix3D() {
        super();
        this.HIGHLIGHT_COLOR = Color.red;
        this.IRRELEVANT_COLOR = Color.lightGray;
        this.NOTHING_COLOR = new Color(240, 240, 240);
        this.DARKENED_COLOR = new Color(79, 147, 205);
        this.DARKENIRRELEVANT_COLOR = new Color(120, 200, 255);
        this.DISABLE_COLOR = Color.gray;
        this.theMatrix = new Polygon3D[3][3][3];
        this.layerColor = new Color[3][3][3];
        this.fillColor = new Color[3][3][3];
        this.theWorld = new World3D();
        this.DEFAULT_SIZE = 20.0;
        this.DEFAULT_GAP = 20.0;
        this.DEFAULT_ZGAP = 20.0;
        this.DEFAULT_X = -50.0;
        this.DEFAULT_Y = -50.0;
        this.DEFAULT_Z = -20.0;
        this.isVisible = false;
        this.arrayX = new int[] { 88, 88, 119, 119, 150, 150, 181, 181, 212, 212, 243, 243, 88, 88, 119, 119, 150, 150, 181, 181, 212, 212, 243, 243, 88, 88, 119, 119, 150, 150, 181, 181, 212, 212, 243, 243, 72, 72, 105, 105, 138, 138, 171, 171, 204, 204, 237, 237, 72, 72, 105, 105, 138, 138, 171, 171, 204, 204, 237, 237, 72, 72, 105, 105, 138, 138, 171, 171, 204, 204, 237, 237, 53, 53, 89, 89, 124, 124, 160, 160, 195, 195, 231, 231, 53, 53, 89, 89, 124, 124, 160, 160, 195, 195, 231, 231, 53, 53, 89, 89, 124, 124, 160, 160, 195, 195, 231, 231 };
        this.arrayY = new int[] { 418, 449, 449, 418, 418, 449, 449, 418, 418, 449, 449, 418, 479, 511, 511, 479, 479, 511, 511, 479, 479, 511, 511, 479, 542, 573, 573, 542, 542, 573, 573, 542, 542, 573, 573, 542, 413, 446, 446, 413, 413, 446, 446, 413, 413, 446, 446, 412, 478, 512, 512, 478, 478, 512, 512, 478, 478, 512, 512, 478, 545, 578, 578, 545, 545, 578, 578, 545, 545, 578, 579, 545, 407, 443, 443, 407, 407, 442, 442, 407, 407, 442, 442, 406, 477, 513, 513, 477, 477, 513, 513, 477, 477, 513, 513, 477, 548, 584, 584, 548, 549, 584, 584, 549, 549, 584, 585, 549 };
        this.prebuiltPolygons = new Polygon[27];
        this.theWorld.initialisePolyWorld();
        this.theWorld.initialiseWorldView();
        this.rotateX(180.0);
        this.rotateY(-1.0);
        this.translateX(-100.0);
        this.translateZ(-100.0);
        this.xMove = 250;
        this.yMove = 60;
        this.theWorld.setTranslation(this.xMove, this.yMove);
        this.createArray();
        this.initialiseColor();
        final int n2 = 0;
        this.width = n2;
        this.height = n2;
        for (int n = 0; n < 27; ++n) {
            this.prebuiltPolygons[n] = new Polygon();
            this.prebuiltPolygons[n].addPoint(this.arrayX[4 * n], this.arrayY[4 * n]);
            this.prebuiltPolygons[n].addPoint(this.arrayX[4 * n + 1], this.arrayY[4 * n + 1]);
            this.prebuiltPolygons[n].addPoint(this.arrayX[4 * n + 2], this.arrayY[4 * n + 2]);
            this.prebuiltPolygons[n].addPoint(this.arrayX[4 * n + 3], this.arrayY[4 * n + 3]);
        }
    }
    
    private void createArray() {
        final double size = 20.0;
        final double gaps = 20.0;
        final double zGap = 20.0;
        double originZ = -20.0;
        for (int z = 0; z < 3; ++z) {
            double originY = -50.0;
            int y = 0;
            while (y < 3) {
                double originX = -50.0;
                int x = 0;
                while (x < 3) {
                    this.theMatrix[x][y][z] = new Polygon3D();
                    this.theMatrix[x][y][z].addPoint(new Point3D(originX, originY, originZ, 1.0));
                    this.theMatrix[x][y][z].addPoint(new Point3D(originX, originY + size, originZ, 1.0));
                    this.theMatrix[x][y][z].addPoint(new Point3D(originX + size, originY + size, originZ, 1.0));
                    this.theMatrix[x][y][z].addPoint(new Point3D(originX + size, originY, originZ, 1.0));
                    originX = originX + gaps + size;
                    ++x;
                }
                originY = originY + gaps + size;
                ++y;
            }
            originZ = originZ + zGap;
        }
    }
    
    public void darkenX(final int z) {
        this.layerColor[1][1][z] = this.DARKENED_COLOR;
        this.layerColor[1][2][z] = this.DARKENED_COLOR;
        this.layerColor[2][1][z] = this.DARKENED_COLOR;
        this.layerColor[2][2][z] = this.DARKENED_COLOR;
        this.layerColor[0][0][z] = this.DARKENIRRELEVANT_COLOR;
        this.layerColor[1][0][z] = this.DARKENIRRELEVANT_COLOR;
        this.layerColor[2][0][z] = this.DARKENIRRELEVANT_COLOR;
        this.layerColor[0][1][z] = this.DARKENIRRELEVANT_COLOR;
        this.layerColor[0][2][z] = this.DARKENIRRELEVANT_COLOR;
    }
    
    public void disable(final int x, final int y) {
        this.disable(x, y, 0);
        this.disable(x, y, 1);
        this.disable(x, y, 2);
    }
    
    public void disable(final int x, final int y, final int z) {
        this.fillColor(x, y, z, this.DISABLE_COLOR);
    }
    
    public void draw(final Graphics g) {
        if (!this.isVisible) {
            return;
        }
        for (int z = 2; z >= 0; --z) {
            int y = 0;
            while (y < 3) {
                int x = 0;
                while (x < 3) {
                    final Polygon projectedPolygon = this.prebuiltPolygons[(2 - z) * 9 + y * 3 + x];
                    if (this.fillColor[x][y][z] != null) {
                        g.setColor(this.fillColor[x][y][z]);
                        g.fillPolygon(projectedPolygon);
                    }
                    g.setColor(this.layerColor[x][y][z]);
                    g.drawPolygon(projectedPolygon);
                    ++x;
                }
                ++y;
            }
        }
    }
    
    public void draw(final Graphics g, final Point p) {
        this.theWorld.setTranslation(p.x + this.xMove, p.y + this.yMove);
        this.draw(g);
    }
    
    public void fillColor(final int x, final int y, final int z, final Color color) {
        this.fillColor[x][y][z] = color;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void highlightA() {
        this.highlightX(0);
    }
    
    public void highlightB() {
        this.highlightX(1);
    }
    
    public void highlightC() {
        this.highlightX(2);
    }
    
    public void highlightX(final int z) {
        for (int y = 0; y < 3; ++y) {
            int x = 0;
            while (x < 3) {
                this.layerColor[x][y][z] = this.HIGHLIGHT_COLOR;
                ++x;
            }
        }
    }
    
    public void highlightX(final int x, final int y) {
        for (int z = 0; z < 3; ++z) {
            this.layerColor[x][y][z] = this.HIGHLIGHT_COLOR;
        }
    }
    
    public void highlightX(final int x, final int y, final int z) {
        this.layerColor[x][y][z] = this.HIGHLIGHT_COLOR;
    }
    
    public void initialiseColor() {
        for (int z = 0; z < 3; ++z) {
            int y = 0;
            while (y < 3) {
                int x = 0;
                while (x < 3) {
                    this.layerColor[x][y][z] = this.IRRELEVANT_COLOR;
                    this.fillColor[x][y][z] = Color.white;
                    ++x;
                }
                ++y;
            }
        }
        for (int z = 0; z < 3; ++z) {
            this.layerColor[0][0][z] = this.NOTHING_COLOR;
            this.layerColor[0][1][z] = this.NOTHING_COLOR;
            this.layerColor[0][2][z] = this.NOTHING_COLOR;
            this.layerColor[1][0][z] = this.NOTHING_COLOR;
            this.layerColor[2][0][z] = this.NOTHING_COLOR;
        }
    }
    
    public void irrelevantA() {
        this.irrelevantX(0);
    }
    
    public void irrelevantAll() {
        this.irrelevantX(0);
        this.irrelevantX(1);
        this.irrelevantX(2);
    }
    
    public void irrelevantB() {
        this.irrelevantX(1);
    }
    
    public void irrelevantC() {
        this.irrelevantX(2);
    }
    
    public void irrelevantX(final int z) {
        this.layerColor[0][0][z] = this.NOTHING_COLOR;
        this.layerColor[0][1][z] = this.NOTHING_COLOR;
        this.layerColor[0][2][z] = this.NOTHING_COLOR;
        this.layerColor[1][0][z] = this.NOTHING_COLOR;
        this.layerColor[2][0][z] = this.NOTHING_COLOR;
    }
    
    public void moveX(final int translation) {
        this.xMove = translation;
        this.theWorld.setTranslation(this.xMove, this.yMove);
    }
    
    public void rotateX(final double degrees) {
        this.theWorld.rotatePolyWorldX(0.017453292519943295 * degrees);
    }
    
    public void rotateY(final double degrees) {
        this.theWorld.rotatePolyWorldY(0.017453292519943295 * degrees);
    }
    
    public void rotateZ(final double degrees) {
        this.theWorld.rotatePolyWorldZ(0.017453292519943295 * degrees);
    }
    
    public void setColor(final int x, final int y, final int z, final Color color) {
        this.layerColor[x][y][z] = color;
    }
    
    public void setLocation(final int x, final int y) {
        this.theWorld.setTranslation(x + this.xMove, y + this.yMove);
    }
    
    public void setVisible(final boolean state) {
        this.isVisible = state;
    }
    
    public void translateX(final double translation) {
        this.theWorld.translatePolyWorldX(translation);
    }
    
    public void translateY(final double translation) {
        this.theWorld.translatePolyWorldY(translation);
    }
    
    public void translateZ(final double translation) {
        this.theWorld.translatePolyWorldZ(translation);
    }
    
    public void unDarkenX(final int z) {
        this.layerColor[1][1][z] = this.IRRELEVANT_COLOR;
        this.layerColor[1][2][z] = this.IRRELEVANT_COLOR;
        this.layerColor[2][1][z] = this.IRRELEVANT_COLOR;
        this.layerColor[2][2][z] = this.IRRELEVANT_COLOR;
        this.layerColor[0][0][z] = this.NOTHING_COLOR;
        this.layerColor[1][0][z] = this.NOTHING_COLOR;
        this.layerColor[2][0][z] = this.NOTHING_COLOR;
        this.layerColor[0][1][z] = this.NOTHING_COLOR;
        this.layerColor[0][2][z] = this.NOTHING_COLOR;
    }
    
    public void unHighlightX(final int z) {
        for (int y = 0; y < 3; ++y) {
            int x = 0;
            while (x < 3) {
                this.layerColor[x][y][z] = this.IRRELEVANT_COLOR;
                ++x;
            }
        }
    }
    
    public void unHighlightX(final int x, final int y) {
        for (int z = 0; z < 3; ++z) {
            this.layerColor[x][y][z] = this.IRRELEVANT_COLOR;
        }
    }
    
    public void unHighlightX(final int x, final int y, final int z) {
        this.layerColor[x][y][z] = this.IRRELEVANT_COLOR;
    }
}
