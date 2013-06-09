// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphMapsApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import localization.Messages;

public class BFSGraphMapsApplet extends AlgorithmApplet
{

    public BFSGraphMapsApplet()
    {
        debug = false;
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debug)
        {
            Button b = new Button(Messages.getString("GraphMapsApplet.4"));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final BFSGraphMapsApplet this$0;

            
            {
                this$0 = BFSGraphMapsApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new BFSGraphMapsCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new BFSGraphMapsThread(copyable, algorithmWindow);
    }

    public AlgorithmWindow getAlgorithmWindow(String algorithmWindowName, CodeCanvas codeCanvas)
    {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new BFSGraphMapsAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        int tmp[] = BFSGraphMapsAnimationWindow.DEFAULT_DATA;
        IntArray tmptmp = new IntArray(tmp);
        return tmptmp;
    }

    protected void otherInitialisation()
    {
        ((BFSGraphMaps)(BFSGraphMaps)algorithm).initialiseMethods(getCodeBase().toString(), (SplitAlgorithmWindow)(SplitAlgorithmWindow)window);
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

    private static final long serialVersionUID = 0x9b6f83f0f2ac7ad8L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("GraphMapsApplet.1");
    protected static final String EXPLANATION_TITLE = Messages.getString("GraphMapsApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("GraphMapsApplet.0");
    protected boolean debug;

}
