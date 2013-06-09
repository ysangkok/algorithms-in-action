// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tree234Applet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.net.URL;
import localization.Messages;

public class Tree234Applet extends AlgorithmApplet
{

    public Tree234Applet()
    {
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
    }

    public Copyable getInitialData()
    {
        return new IntArray(Tree234AnimationWindow.DEFAULT_DATA);
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

    protected void otherInitialisation()
    {
        ((Tree234Tree)(Tree234Tree)algorithm).initialiseMethods(getCodeBase().toString(), (SplitAlgorithmWindow)(SplitAlgorithmWindow)window);
    }

    protected AlgorithmWindow getAlgorithmWindow(String algorithmWindowName, CodeCanvas codeCanvas)
    {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new Tree234Thread(copyable, (SplitAlgorithmWindow)algorithmWindow);
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new Tree234Canvas();
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new Tree234AnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("Tree234Applet.1");
    protected static final String EXPLANATION_TITLE = Messages.getString("Tree234Applet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("Tree234Applet.3");

}
