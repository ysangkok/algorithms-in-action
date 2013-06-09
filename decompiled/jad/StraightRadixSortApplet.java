// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StraightRadixSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class StraightRadixSortApplet extends AlgorithmApplet
{

    public StraightRadixSortApplet()
    {
        debug = false;
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debug)
        {
            Button b = new Button(Messages.getString("StraightRadixSortApplet.0"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final StraightRadixSortApplet this$0;

            
            {
                this$0 = StraightRadixSortApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new StraightRadixSortCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new StraightRadixSortThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new StraightRadixSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return (new RandomIntArrayDataSelection("", false, animWindow, 3, 20, 0, 15)).getData();
    }

    public void setLocationAndSize()
    {
        explainationWindow.setLocation(0, 0);
        explainationWindow.setSize(235, 735);
        window.setLocation(235, 135);
        window.setSize(350, 600);
        helpWindow.setLocation(235, 0);
        helpWindow.setSize(350, 135);
        animWindow.setLocation(585, 0);
        animWindow.setSize(440, 720);
    }

    private static final long serialVersionUID = 0x8453971028688653L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("StraightRadixSortApplet.3");
    protected static final String EXPLANATION_TITLE = Messages.getString("StraightRadixSortApplet.1");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("StraightRadixSortApplet.2");
    protected boolean debug;

}
