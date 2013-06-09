package com.cim.AIA;

import java.awt.*;

public class BinaryElement extends VerticalBar
{
    public static final Color BORDER_COLOR;
    protected int bitLength;
    protected int yTextBuffer;
    
    public BinaryElement(final int sequenceNumber, final Color color, final Color borderColor, final int value, final Point position, final int yTextBuffer, final int bitLength) {
        super(sequenceNumber, value, color, borderColor, position);
        this.drawValueMarker = false;
        this.bitLength = bitLength;
        this.yTextBuffer = yTextBuffer;
    }
    
    public BinaryElement(final int sequenceNumber, final Color color, final int value, final Point position, final int yTextBuffer, final int bitLength) {
        super(sequenceNumber, value, color, BinaryElement.BORDER_COLOR, position);
        this.drawValueMarker = false;
        this.bitLength = bitLength;
        this.yTextBuffer = yTextBuffer;
    }
    
    protected void drawElementValue(final Graphics g) {
        g.setColor(this.borderColor);
        g.fillRect(this.position.x - this.borderThickness / 2, this.position.y - this.borderThickness / 2 - this.columYSpacing - this.yTextBuffer - (g.getFontMetrics().getHeight() + this.yTextBuffer) * this.bitLength, this.columWidth + this.borderThickness, (g.getFontMetrics().getHeight() + this.yTextBuffer) * this.bitLength + this.yTextBuffer + this.borderThickness);
        g.setColor(this.color);
        g.fillRect(this.position.x, this.position.y - this.columYSpacing - this.yTextBuffer - (g.getFontMetrics().getHeight() + this.yTextBuffer) * this.bitLength, this.columWidth, (g.getFontMetrics().getHeight() + this.yTextBuffer) * this.bitLength + this.yTextBuffer);
        g.setColor(this.textColor);
        String binaryString = "";
        for (int i = 0; i < this.bitLength; ++i) {
            binaryString = binaryString + "0";
        }
        final String binary = Integer.toBinaryString(((Integer)((Integer)this.value)).intValue());
        binaryString = binaryString.substring(0, binaryString.length() - binary.length()) + binary;
        for (int j = binaryString.length() - 1; j >= 0; --j) {
            final String string = "" + binaryString.charAt(j);
            g.drawString(string, this.position.x + this.columWidth / 2 - g.getFontMetrics().stringWidth(string) / 2, this.position.y - this.columYSpacing - this.yTextBuffer - (this.bitLength - j - 1) * (this.yTextBuffer + g.getFontMetrics().getHeight()));
        }
    }
    
    public int getHeight(final Graphics g) {
        int noOfMarkers = 0;
        if (this.drawValueMarker) {
            ++noOfMarkers;
        }
        if (this.drawMarkers) {
            noOfMarkers = noOfMarkers + this.markers.size();
        }
        return this.columYSpacing + this.yTextBuffer + (this.bitLength - 1) * (this.yTextBuffer + g.getFontMetrics().getHeight()) + this.borderThickness + noOfMarkers * g.getFontMetrics().getHeight();
    }
    
    public void setBitLength(final int bitLength) {
        this.bitLength = bitLength;
    }
    
    static {
        BORDER_COLOR = Color.white;
    }
}
