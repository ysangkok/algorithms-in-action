// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import localization.Messages;

public class KMPApplet extends AlgorithmApplet
{

    public KMPApplet()
    {
        debug = false;
        imageDirectory = "images/";
        algorithmFileName = FIRST_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debug)
        {
            Button b = new Button(Messages.getString("KMPApplet.1"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final KMPApplet this$0;

            
            {
                this$0 = KMPApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        theKMPCanvas = new KMPCanvas();
        return theKMPCanvas;
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new KMPThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new KMPAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        initialData = new StringArray(KMPAnimationWindow.DEFAULT_DATA_1);
        return initialData;
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

    private static final long serialVersionUID = 0x7591087b3e17e90eL;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("KMPApplet.5");
    protected static final String EXPLANATION_TITLE = Messages.getString("KMPApplet.0");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("KMPApplet.4");
    public static KMPCanvas theKMPCanvas;
    public static String FIRST_FILE_NAME = Messages.getString("KMPApplet.3");
    public static String SECOND_FILE_NAME = Messages.getString("KMPApplet.2");
    protected boolean debug;
    protected StringArray initialData;

}
