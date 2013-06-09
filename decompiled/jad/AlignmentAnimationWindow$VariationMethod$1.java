// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentAnimationWindow.java

import com.cim.AIA.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class val.name
    implements ItemListener
{

    public void itemStateChanged(ItemEvent e)
    {
        imWindow.resetVariationButtons();
        if(Alignment.runningMode == 1 && (ds1.getState() || ds2.getState()))
            if(Alignment.currentVariation == 1)
            {
                ds1.setState(false);
                com.cim.AIA.Copyable temp = ds2.getData();
                ds2.setState(true);
                setCurrentData(temp, true, true);
            } else
            {
                ds2.setState(false);
                com.cim.AIA.Copyable temp = ds1.getData();
                ds1.setState(true);
                setCurrentData(temp, true, true);
            }
        if(Alignment.runningMode == 2 && (ds3.getState() || ds4.getState()))
            if(Alignment.currentVariation == 1)
            {
                ds3.setState(false);
                com.cim.AIA.Copyable temp = ds4.getData();
                ds4.setState(true);
                setCurrentData(temp, true, true);
            } else
            {
                ds4.setState(false);
                com.cim.AIA.Copyable temp = ds3.getData();
                ds3.setState(true);
                setCurrentData(temp, true, true);
            }
        tState(true);
        AlignmentApplet.expWin.initialiseMainData(AlignmentApplet.codeBaseString, pName);
        ignment.setVariation(riationMethod);
        resetThread(true, true, false, false);
        goWindow.setLadderTree(dderTree);
        Log log = new Log((byte)4, (byte)21, null, val$name);
        if(getLogger() != null)
            getLogger().addLog(log);
    }

    final AlignmentAnimationWindow val$this$0;
    final String val$name;
    final val.name this$1;

    ()
    {
        this$1 = final_;
        val$this$0 = alignmentanimationwindow;
        val$name = String.this;
        super();
    }
}
