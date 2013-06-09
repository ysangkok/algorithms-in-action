// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.net.URL;
import localization.Messages;

public class PatriciaTreeApplet extends AlgorithmApplet
{

    public PatriciaTreeApplet()
    {
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new PatriciaTreeCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new PatriciaTreeThread(copyable, algorithmWindow);
    }

    protected AlgorithmWindow getAlgorithmWindow(String algorithmWindowName, CodeCanvas codeCanvas)
    {
        return new MultiAlgorithmWindow(this, algorithmWindowName, codeCanvas);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new PatriciaTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return new IntArray(PatriciaTreeAnimationWindow.DEFAULT_DATA);
    }

    protected void otherInitialisation()
    {
        ((PatriciaTree)algorithm).initialiseMethods(getCodeBase().toString(), (MultiAlgorithmWindow)window);
        ((PatriciaTree)algorithm).initialiseCanvases(getCodeBase().toString(), (MultiAlgorithmWindow)window);
    }

    public void setLocationAndSize()
    {
        explainationWindow.setLocation(0, 0);
        explainationWindow.setSize(235, 735);
        window.setLocation(235, 0);
        window.setSize(350, 735);
        helpWindow.setLocation(235, 0);
        helpWindow.setSize(350, 135);
        animWindow.setLocation(585, 0);
        animWindow.setSize(440, 720);
    }

    private static final long serialVersionUID = 0xcb4e0e64114d37c8L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("PatriciaTreeApplet.0");
    protected static final String EXPLANATION_TITLE = Messages.getString("PatriciaTreeApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("PatriciaTreeApplet.3");

}
