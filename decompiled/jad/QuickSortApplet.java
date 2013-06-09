// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QuickSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class QuickSortApplet extends AlgorithmApplet
{

    public QuickSortApplet()
    {
        initalData = new IntArray(QuickSortAnimationWindow.DEFAULT_DATA);
        debugingMode = false;
        imageDirectory = "images/";
        algorithmFileName = RightPartitionFileName;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debugingMode)
        {
            Button b = new Button(Messages.getString("QuickSortApplet.1"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final QuickSortApplet this$0;

            
            {
                this$0 = QuickSortApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new QuickSortCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new QuickSortThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new QuickSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return initalData;
    }

    public void setLocationAndSize()
    {
        explainationWindow.setLocation(0, 0);
        explainationWindow.setSize(250, 735);
        window.setLocation(250, 0);
        window.setSize(420, 600);
        helpWindow.setLocation(250, 600);
        helpWindow.setSize(420, 135);
        animWindow.setLocation(670, 0);
        animWindow.setSize(355, 720);
    }

    private static final long serialVersionUID = 0xbf0e297d390cea63L;
    public static final String RightPartitionFileName = Messages.getString("QuickSortApplet.6");
    public static final String RandomPartitionFileName = Messages.getString("QuickSortApplet.5");
    public static final String MiddleOf3RandomPartitionFileName = Messages.getString("QuickSortApplet.4");
    public static final String MiddleOf3PartitionFileName = Messages.getString("QuickSortApplet.3");
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE = Messages.getString("QuickSortApplet.0");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("QuickSortApplet.2");
    protected IntArray initalData;
    protected boolean debugingMode;

}
