// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeapSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class HeapSortApplet extends AlgorithmApplet
{

    public HeapSortApplet()
    {
        debugingMode = false;
        imageDirectory = "images/";
        algorithmFileName = FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debugingMode)
        {
            Button b = new Button(Messages.getString("HeapSortApplet.0"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final HeapSortApplet this$0;

            
            {
                this$0 = HeapSortApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new HeapSortCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new HeapSortThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new HeapSortAnimationWindow(algorithmCanvas, algorithmThread, this);
    }

    public Copyable getInitialData()
    {
        return (new RandomIntArrayDataSelection("", false, null, 3, 20, 1, 99)).getData();
    }

    protected void setLocationAndSize()
    {
        explainationWindow.setLocation(0, 0);
        explainationWindow.setSize(250, 735);
        window.setLocation(250, 0);
        window.setSize(385, 735);
        helpWindow.setLocation(635, 0);
        helpWindow.setSize(390, 150);
        animWindow.setLocation(635, 150);
        animWindow.setSize(390, 565);
    }

    private static final long serialVersionUID = 0xaae8aabb6112c007L;
    protected static final String FILE_NAME = Messages.getString("HeapSortApplet.3");
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE = Messages.getString("HeapSortApplet.1");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("HeapSortApplet.2");
    protected boolean debugingMode;

}
