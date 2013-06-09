// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTreeApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.net.URL;
import localization.Messages;

public class RedBlackTreeApplet extends AlgorithmApplet
{

    public RedBlackTreeApplet()
    {
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new RedBlackTreeCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new RedBlackTreeThread(copyable, algorithmWindow);
    }

    protected AlgorithmWindow getAlgorithmWindow(String algorithmWindowName, CodeCanvas codeCanvas)
    {
        return new SplitAlgorithmWindow(algorithmWindowName, codeCanvas);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new RedBlackTreeAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return new IntArray(RedBlackTreeAnimationWindow.DEFAULT_DATA);
    }

    protected void otherInitialisation()
    {
        ((RedBlackTree)(RedBlackTree)algorithm).initialiseMethods(getCodeBase().toString(), (SplitAlgorithmWindow)(SplitAlgorithmWindow)window);
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

    private static final long serialVersionUID = 0xa319861d5a6fb7c0L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("RedBlackTreeApplet.2");
    protected static final String EXPLANATION_TITLE = Messages.getString("RedBlackTreeApplet.0");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("RedBlackTreeApplet.1");

}
