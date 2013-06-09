// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigitalSearchTreeApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.net.URL;
import localization.Messages;

public class DigitalSearchTreeApplet extends AlgorithmApplet
{

    public DigitalSearchTreeApplet()
    {
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_FILE_NAME;
        explanationFileName = EXPLANATION_FILE_NAME;
        lastWindow = window;
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new DigitalSearchTreeCanvas();
    }

    public AlgorithmWindow getAlgorithmWindow()
    {
        return lastWindow;
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new DigitalSearchTreeThread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }

    protected AlgorithmWindow getAlgorithmWindow(String string, CodeCanvas codeCanvas)
    {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new DigitalSearchTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return new IntArray(DigitalSearchTreeAnimationWindow.DEFAULT_DATA);
    }

    protected void otherInitialisation()
    {
        ((DigitalSearchTree)algorithm).initialiseMethods(getCodeBase().toString(), (SplitAlgorithmWindow)window);
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

    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("DigitalSearchTreeApplet.1");
    protected static final String EXPLANATION_TITLE = Messages.getString("DigitalSearchTreeApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("DigitalSearchTreeApplet.3");
    protected static AlgorithmWindow lastWindow = null;

}
