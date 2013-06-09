// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class MergeSortApplet extends AlgorithmApplet
{

    public MergeSortApplet()
    {
        initalData = new IntArray(MergeSortAnimationWindow.DEFAULT_DATA);
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

                final MergeSortApplet this$0;

            
            {
                this$0 = MergeSortApplet.this;
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
        return new MergeSortThread(copyable, algorithmWindow);
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new MergeSortCanvas();
    }

    public void setLocationAndSize()
    {
        explainationWindow.setLocation(0, 0);
        explainationWindow.setSize(250, 735);
        window.setLocation(250, 0);
        window.setSize(420, 765);
        helpWindow.setLocation(250, 600);
        helpWindow.setSize(420, 135);
        animWindow.setLocation(670, 0);
        animWindow.setSize(600, 765);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new MergeSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    private static final long serialVersionUID = 0x5214965bbcdd4be7L;
    public static final String RightPartitionFileName = Messages.getString("MergeSortApplet.0");
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE = Messages.getString("MergeSortApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("MergeSortApplet.3");
    public static final int ANIM_WIN_WIDTH = 600;
    public static final int ANIM_WIN_HEIGHT = 765;
    protected IntArray initalData;
    protected boolean debugingMode;

}
