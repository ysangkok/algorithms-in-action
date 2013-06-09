// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixExchangeSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class RadixExchangeSortApplet extends AlgorithmApplet
{

    public RadixExchangeSortApplet()
    {
        initalData = new IntArray(RadixExchangeSortAnimationWindow.DEFAULT_DATA);
        debugingMode = false;
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debugingMode)
        {
            Button b = new Button(Messages.getString("RadixExchangeSortApplet.4"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final RadixExchangeSortApplet this$0;

            
            {
                this$0 = RadixExchangeSortApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new RadixExchangeSortCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new RadixExchangeSortThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new RadixExchangeSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return initalData;
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

    private static final long serialVersionUID = 0xee444df752a2bdf3L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("RadixExchangeSortApplet.0");
    protected static final String EXPLANATION_TITLE = Messages.getString("RadixExchangeSortApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("RadixExchangeSortApplet.1");
    protected IntArray initalData;
    protected boolean debugingMode;

}
