// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMapsApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import localization.Messages;

public class DFSGraphMapsApplet extends AlgorithmApplet
{

    public DFSGraphMapsApplet()
    {
        debug = false;
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debug)
        {
            Button b = new Button(Messages.getString("DFSGraphMapsApplet.4"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final DFSGraphMapsApplet this$0;

            
            {
                this$0 = DFSGraphMapsApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public Copyable getInitialData()
    {
        int tmp[] = DFSGraphMapsAnimationWindow.DEFAULT_DATA;
        IntArray tmptmp = new IntArray(tmp);
        return tmptmp;
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
        animWindow.setSize(440, 367);
    }

    protected void otherInitialisation()
    {
        ((DFSGraphMaps)(DFSGraphMaps)algorithm).initialiseMethods(getCodeBase().toString(), (SplitAlgorithmWindow)(SplitAlgorithmWindow)window);
    }

    public AlgorithmWindow getAlgorithmWindow(String algorithmWindowName, CodeCanvas codeCanvas)
    {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new DFSGraphMapsThread(copyable, algorithmWindow);
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new DFSGraphMapsCanvas();
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new DFSGraphMapsAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("DFSGraphMapsApplet.1");
    protected static final String EXPLANATION_TITLE = Messages.getString("DFSGraphMapsApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("DFSGraphMapsApplet.3");
    protected boolean debug;

}
