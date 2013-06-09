// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphMapsCanvasExt.java

import aia.graph.common.*;
import com.cim.AIA.MultipleTween;

public class BFSGraphMapsCanvasExt extends GraphMapsCanvasExt
    implements GraphDialogEventHandler
{

    BFSGraphMapsCanvasExt()
    {
        tweens = new MultipleTween(this);
        addSelectionListener(new DragSelectionListener(this));
    }

    private static final long serialVersionUID = 0x6f15b1f647938145L;
}
