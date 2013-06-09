// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTree.java


protected class isLeft
{

    void add(boolean isLeft)
    {
        if(next == null)
            next = new <init>(isLeft);
        else
            next.add(isLeft);
    }

    boolean isLeft;
    add next;
    final PatriciaTree this$0;

    (boolean isLeft)
    {
        this$0 = PatriciaTree.this;
        super();
        this.isLeft = isLeft;
    }
}
