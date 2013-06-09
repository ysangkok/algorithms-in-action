// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeBUSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class MergeBUSortApplet extends AlgorithmApplet
{

    public MergeBUSortApplet()
    {
        initalData = new IntArray(MergeBUSortAnimationWindow.DEFAULT_DATA);
        debugingMode = true;
        imageDirectory = "images/";
        algorithmFileName = RightPartitionFileName;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debugingMode)
        {
            Button b = new Button("Display Positions");
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final MergeBUSortApplet this$0;

            
            {
                this$0 = MergeBUSortApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public Copyable getInitialData()
    {
        return initalData;
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new MergeBUSortThread(copyable, algorithmWindow);
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new MergeBUSortCanvas();
    }

    public void setLocationAndSize()
    {
        explainationWindow.setLocation(0, 0);
        explainationWindow.setSize(250, 735);
        window.setLocation(250, 0);
        window.setSize(570, 765);
        helpWindow.setLocation(250, 600);
        helpWindow.setSize(420, 135);
        animWindow.setLocation(820, 0);
        animWindow.setSize(600, 765);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new MergeBUSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    private static final long serialVersionUID = 0x4c4e03c5987f9658L;
    public static final String RightPartitionFileName = Messages.getString("MergeBUSortApplet.0");
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE = Messages.getString("MergeBUSortApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("MergeBUSortApplet.3");
    public static final int ANIM_WIN_WIDTH = 600;
    public static final int ANIM_WIN_HEIGHT = 765;
    protected IntArray initalData;
    protected boolean debugingMode;

}
