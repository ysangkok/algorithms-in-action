// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewLinks.java

import com.cim.AIA.Line;
import com.cim.AIA.Node;
import java.awt.Color;

public class NewLinks
{

    public NewLinks(Node source, boolean dir, Node destination)
    {
        srcNode = source;
        destNode = destination;
        direction = dir;
    }

    public Line getLine()
    {
        int srcX;
        if(!direction)
            srcX = srcNode.getX();
        else
            srcX = srcNode.getX() + srcNode.getWidth();
        Line newLine = new Line(srcX, srcNode.getY() + srcNode.getHeight(), destNode.getX() + destNode.getWidth() / 2, destNode.getY());
        newLine.showArrowHead(true);
        newLine.showAsDotted(true);
        newLine.setDistanceFromStartForArrowHead(-3);
        newLine.setColor(LINECOLOR);
        return newLine;
    }

    private static final Color LINECOLOR;
    private Node srcNode;
    private Node destNode;
    private boolean direction;

    static 
    {
        LINECOLOR = Color.blue;
    }
}
