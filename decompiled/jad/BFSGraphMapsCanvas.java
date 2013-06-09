// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphMapsCanvas.java

import aia.graph.common.GraphDialogEventHandler;
import aia.graph.common.GraphMapsCanvas;
import com.cim.AIA.MultipleTween;

public class BFSGraphMapsCanvas extends GraphMapsCanvas
    implements GraphDialogEventHandler
{

    BFSGraphMapsCanvas()
    {
        tweens = new MultipleTween(this);
    }

    private static final long serialVersionUID = 0x722428960c16f0daL;
}
