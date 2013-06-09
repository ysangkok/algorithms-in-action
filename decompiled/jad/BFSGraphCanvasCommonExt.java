// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphCanvasCommonExt.java

import aia.graph.common.GraphCanvasCommonExt;
import aia.graph.common.GraphMaps;

public class BFSGraphCanvasCommonExt extends GraphCanvasCommonExt
{

    BFSGraphCanvasCommonExt(BFSGraphMapsCanvasExt p_canvas, GraphMaps p_algorithm)
    {
        m_canvas = p_canvas;
        m_algorithm = p_algorithm;
    }
}
