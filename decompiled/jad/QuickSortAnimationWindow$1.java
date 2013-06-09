// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSortAnimationWindow.java

import com.cim.AIA.Log;
import com.cim.AIA.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

class this._cls0
    implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.paramString();
        int i1 = cmd.indexOf("cmd=") + 4;
        int i2 = cmd.indexOf(",", i1);
        cmd = cmd.substring(i1, i2);
        Log l1 = new Log((byte)4, (byte)21, null, cmd);
        if(getLogger() != null)
            getLogger().addLog(l1);
        if(Logger.DEBUG)
        {
            System.err.println("Partition method action listener called.");
            System.err.println((new StringBuilder()).append("paramString=").append(e.paramString()).toString());
            System.err.println((new StringBuilder()).append("cmd=").append(cmd).toString());
        }
    }

    final QuickSortAnimationWindow this$0;

    er()
    {
        this$0 = QuickSortAnimationWindow.this;
        super();
    }
}
