package com.cim.AIA;

import java.awt.*;

public class Line implements Drawable, Moveable
{
    public static final int START_OF_LINE = -1;
    public static final int MIDDLE_OF_LINE = -2;
    public static final int END_OF_LINE = -3;
    protected double x1;
    protected double x2;
    protected double y1;
    protected double y2;
    protected int dottedGap;
    protected boolean showAsThick;
    protected int lineThickness;
    protected boolean showAsDotted;
    protected boolean showArrowHead;
    protected int distanceFromStartForArrowHead;
    protected Color rayColor;
    protected Color textColor;
    protected String label;
    protected int distanceFromStartForLabel;
    protected int xLabelOffset;
    protected int yLabelOffset;
    protected int arrowHeadWidth;
    protected int arrowHeadLength;
    
    public static double getDegrees(final double radians) {
        return 57.29577951308232 * radians;
    }
    
    public static DoublePoint getIntersection(final Line line1, final Line line2) {
        final double x_coord = (line1.getConstant() - line2.getConstant()) / (line2.getSlope() - line1.getSlope());
        if (Double.isInfinite(x_coord)) {
            return null;
        }
        DoublePoint i_point;
        if (Double.isInfinite(line2.getSlope()) || Double.isInfinite(line2.getConstant())) {
            i_point = new DoublePoint(line2.x1, line1.getSlope() * line2.x1 + line1.getConstant());
            return i_point;
        }
        if (Double.isInfinite(line1.getSlope()) || Double.isInfinite(line1.getConstant())) {
            i_point = new DoublePoint(line1.x1, line2.getSlope() * line1.x1 + line2.getConstant());
            return i_point;
        }
        i_point = new DoublePoint(x_coord, line1.getSlope() * x_coord + line1.getConstant());
        return i_point;
    }
    
    public static double getOpposite(final double angle, final double adjacent) {
        return Math.tan(getRadians(angle)) * adjacent;
    }
    
    public static double getRadians(final double degrees) {
        return degrees / 57.29577951308232;
    }
    
    public static double getTheta(final double opposite, final double adjacent) {
        return getDegrees(Math.atan(opposite / adjacent));
    }
    
    public static int round(final double double_value) {
        if (double_value > 2.147483647E9) {
            return 2147483647;
        }
        return (int)Math.round(double_value);
    }
    
    public Line(final double x1, final double y1, final double x2, final double y2) {
        super();
        this.dottedGap = 5;
        this.showAsThick = false;
        this.lineThickness = 2;
        this.showAsDotted = false;
        this.showArrowHead = false;
        this.distanceFromStartForArrowHead = -2;
        this.rayColor = Color.black;
        this.textColor = Color.black;
        this.label = "";
        this.distanceFromStartForLabel = -2;
        this.xLabelOffset = 0;
        this.yLabelOffset = 0;
        this.arrowHeadWidth = 5;
        this.arrowHeadLength = 10;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    public Line(final DoublePoint from, final DoublePoint to) {
        this(from.x, from.y, to.x, to.y);
    }
    
    public Line(final int x1, final int y1, final int x2, final int y2) {
        this((double)x1, (double)y1, (double)x2, (double)y2);
    }
    
    public Line(final int x1, final int y1, final int x2, final int y2, final Color rayColor) {
        this(x1, y1, x2, y2);
        this.rayColor = rayColor;
    }
    
    public Line(final Point from, final Point to) {
        this(from.x, from.y, to.x, to.y);
    }
    
    public void changeDirection() {
        final double temp_x = this.x1;
        final double temp_y = this.y1;
        this.x1 = this.x2;
        this.y1 = this.y2;
        this.x2 = temp_x;
        this.y2 = temp_y;
    }
    
    public Line copy() {
        final Line tempLine = new Line(this.x1, this.y1, this.x2, this.y2);
        tempLine.rayColor = this.rayColor;
        tempLine.textColor = this.textColor;
        tempLine.showAsThick = this.showAsThick;
        tempLine.showAsDotted = this.showAsDotted;
        tempLine.showArrowHead = this.showArrowHead;
        tempLine.label = this.label;
        tempLine.lineThickness = this.lineThickness;
        tempLine.distanceFromStartForArrowHead = this.distanceFromStartForArrowHead;
        tempLine.distanceFromStartForLabel = this.distanceFromStartForLabel;
        tempLine.dottedGap = this.dottedGap;
        tempLine.xLabelOffset = this.xLabelOffset;
        tempLine.yLabelOffset = this.yLabelOffset;
        tempLine.arrowHeadWidth = this.arrowHeadWidth;
        tempLine.arrowHeadLength = this.arrowHeadLength;
        return tempLine;
    }
    
    public void draw(final Graphics g) {
        // java.lang.ArrayIndexOutOfBoundsException: -1
        //     at java.util.ArrayList.elementData(ArrayList.java:371)
        //     at java.util.ArrayList.get(ArrayList.java:384)
        //     at com.strobel.assembler.ir.StackMappingVisitor.getStackValue(StackMappingVisitor.java:68)
        //     at com.strobel.decompiler.ast.AstBuilder.createModifiedStack(AstBuilder.java:1402)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:898)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:64)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:69)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:618)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:520)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:456)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:441)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:158)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:55)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:188)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:146)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:91)
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void draw(final Graphics g, final Point p) {
        this.setX(p.x);
        this.setY(p.y);
        this.draw(g);
    }
    
    public boolean equals(final Line new_line) {
        return new_line.x1 == this.x1 && new_line.x2 == this.x2 && new_line.y1 == this.y1 && new_line.y2 == this.y2;
    }
    
    public double getAngle() {
        return getTheta(this.y1 - this.y2, this.x2 - this.x1);
    }
    
    public Color getColor() {
        return this.rayColor;
    }
    
    public double getConstant() {
        return this.y1 - this.getSlope() * this.x1;
    }
    
    public int getIntX1() {
        return round(this.x1);
    }
    
    public int getIntX2() {
        return round(this.x2);
    }
    
    public int getIntY1() {
        return round(this.y1);
    }
    
    public int getIntY2() {
        return round(this.y2);
    }
    
    public int getLength() {
        final double xDelta = Math.max(this.x1, this.x2) - Math.min(this.x1, this.x2);
        final double yDelta = Math.max(this.y1, this.y2) - Math.min(this.y1, this.y2);
        return (int)Math.sqrt(Math.pow(xDelta, 2.0) + Math.pow(yDelta, 2.0));
    }
    
    public double getSlope() {
        return (this.y2 - this.y1) / (this.x2 - this.x1);
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public int getX() {
        return (int)this.x2;
    }
    
    public double getX1() {
        return this.x1;
    }
    
    public double getX2() {
        return this.x2;
    }
    
    public double getXForY(final double y_coord) {
        final DoublePoint db_point = getIntersection(this, new Line(0.0, y_coord, 1.7976931348623157E308, y_coord));
        if (db_point == null) {
            return this.x1;
        }
        return db_point.x;
    }
    
    public int getY() {
        return (int)this.y2;
    }
    
    public double getY1() {
        return this.y1;
    }
    
    public double getY2() {
        return this.y2;
    }
    
    public double getYForX(final double x_coord) {
        return getIntersection(this, new Line(x_coord, 0.0, x_coord, 1.7976931348623157E308)).y;
    }
    
    public Point intersectAt(final Line line2) {
        final DoublePoint d = getIntersection(this, line2);
        if (d == null) {
            return null;
        }
        return new Point(round(d.x), round(d.y));
    }
    
    public void setArrowHeadWidth(final int width) {
        this.arrowHeadWidth = width;
    }
    
    public void setColor(final Color rayColor) {
        this.rayColor = rayColor;
    }
    
    public void setDistanceFromStartForArrowHead(final int distance) {
        this.distanceFromStartForArrowHead = distance;
    }
    
    public void setDistanceFromStartForLabel(final int distance) {
        this.distanceFromStartForLabel = distance;
    }
    
    public void setDottedGap(final int gap) {
        this.dottedGap = gap;
    }
    
    public void setEndPosition(final DoublePoint point) {
        this.x2 = point.x;
        this.y2 = point.y;
    }
    
    public void setEndPosition(final Point point) {
        this.setIntX2(point.x);
        this.setIntY2(point.y);
    }
    
    public void setIntX1(final int x1) {
        this.x1 = (double)x1;
    }
    
    public void setIntX2(final int x2) {
        this.x2 = (double)x2;
    }
    
    public void setIntY1(final int y1) {
        this.y1 = (double)y1;
    }
    
    public void setIntY2(final int y2) {
        this.y2 = (double)y2;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public void setLineThickness(final int thickness) {
        this.lineThickness = thickness;
    }
    
    public void setStartPosition(final DoublePoint point) {
        this.x1 = point.x;
        this.y1 = point.y;
    }
    
    public void setStartPosition(final Point point) {
        this.setIntX1(point.x);
        this.setIntY1(point.y);
    }
    
    public void setTextColor(final Color textColor) {
        this.textColor = textColor;
    }
    
    public void setX(final int x) {
        this.x2 = (double)x;
    }
    
    public void setXLabelOffset(final int xOffset) {
        this.xLabelOffset = xOffset;
    }
    
    public void setY(final int y) {
        this.y2 = (double)y;
    }
    
    public void setYLabelOffset(final int yOffset) {
        this.yLabelOffset = yOffset;
    }
    
    public void shiftEntire(final int xDelta, final int yDelta) {
        this.x1 = this.x1 + (double)xDelta;
        this.x2 = this.x2 + (double)xDelta;
        this.y1 = this.y1 + (double)yDelta;
        this.y2 = this.y2 + (double)yDelta;
    }
    
    public void showArrowHead(final boolean state) {
        this.showArrowHead = state;
    }
    
    public void showAsDotted(final boolean state) {
        this.showAsDotted = state;
    }
    
    public void showAsThick(final boolean state) {
        this.showAsThick = state;
    }
    
    public String toString() {
        return new String("com.cim.AIA.Line: X1 = " + this.x1 + " Y1 = " + this.y1 + " X2 = " + this.x2 + " Y2 = " + this.y2);
    }
}
