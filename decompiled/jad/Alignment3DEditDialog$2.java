// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Alignment3DEditDialog.java

import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import localization.Messages;

class this._cls0
    implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
        System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.2")).append(xScroll.getValue()).toString());
        System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.3")).append(yScroll.getValue()).toString());
        System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.4")).append(zScroll.getValue()).toString());
        System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.5")).append(xTranslate.getValue()).toString());
        System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.6")).append(yTranslate.getValue()).toString());
        System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.7")).append(zTranslate.getValue()).toString());
    }

    final Alignment3DEditDialog this$0;

    nt()
    {
        this$0 = Alignment3DEditDialog.this;
        super();
    }
}
