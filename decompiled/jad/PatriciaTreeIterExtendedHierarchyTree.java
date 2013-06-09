// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeIterExtendedHierarchyTree.java

import com.cim.AIA.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class PatriciaTreeIterExtendedHierarchyTree extends HierarchyTree
{

    public PatriciaTreeIterExtendedHierarchyTree()
    {
        borderColor = Color.white;
    }

    public PatriciaTreeIterExtendedHierarchyTree(Tree tree)
    {
        super(tree);
    }

    public PatriciaTreeIterExtendedHierarchyTree(Tree tree, Node node)
    {
        super(tree, node);
    }

    public PatriciaTreeIterExtendedHierarchyTree(Node node)
    {
        super(node);
    }

    public Point getParentConnectPoint()
    {
        if(parentTree == null || elements.size() == 0)
            return null;
        PatriciaTreeIterExtendedHierarchyTree parent = (PatriciaTreeIterExtendedHierarchyTree)parentTree;
        Point connectionPoint = new Point(parent.pos.x + parent.getWidth() / 2, parent.pos.y + parent.getHeight());
        if(parent.children.size() == 1)
            return connectionPoint;
        int childValue = 0;
        int i = 0;
        do
        {
            if(i >= elements.size())
                break;
            try
            {
                childValue = ((Integer)(Integer)((Node)elements.elementAt(i)).getObject()).intValue();
                break;
            }
            catch(ClassCastException e)
            {
                i++;
            }
        } while(true);
        for(i = 0; i < parent.children.size(); i++)
        {
            PatriciaTreeIterExtendedHierarchyTree child = (PatriciaTreeIterExtendedHierarchyTree)parent.children.elementAt(i);
            if(child != this)
                continue;
            for(int j = 0; j < parent.elements.size(); j++)
            {
                int parentValue = 0;
                try
                {
                    parentValue = ((Integer)(Integer)((Node)parent.elements.elementAt(j)).getObject()).intValue();
                }
                catch(ClassCastException e) { }
                if(i == 0)
                {
                    connectionPoint.x = parent.pos.x;
                    connectionPoint.y = parent.pos.y + parent.elementAt(0).getHeight();
                    break;
                }
                if(i == parent.children.size() - 1)
                {
                    connectionPoint.x = parent.pos.x + parent.getWidth();
                    break;
                }
                if(j == 0)
                    connectionPoint.x = parent.pos.x;
                if(childValue > parentValue && parentValue != 0)
                    connectionPoint.x += parent.getWidthOfNode(j);
            }

        }

        return connectionPoint;
    }
}
