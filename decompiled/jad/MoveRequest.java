// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveRequest.java

import com.cim.AIA.Node;

public class MoveRequest
{

    public MoveRequest(Node source, Node dest)
    {
        this.source = source;
        this.dest = dest;
    }

    public Node getDest()
    {
        return dest;
    }

    public Node getSource()
    {
        return source;
    }

    Node source;
    Node dest;
}
