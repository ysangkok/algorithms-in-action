// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.net.URL;
import localization.Messages;

public class RadixTrieMultiApplet extends AlgorithmApplet
{

    public RadixTrieMultiApplet()
    {
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
    }

    public Copyable getInitialData()
    {
        return new IntArray(RadixTrieMultiAnimationWindow.DEFAULT_DATA);
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

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new RadixTrieMultiThread(copyable, algorithmWindow);
    }

    protected AlgorithmWindow getAlgorithmWindow(String string, CodeCanvas codeCanvas)
    {
        return new SplitAlgorithmWindow(string, codeCanvas);
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new RadixTrieMultiCanvas();
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new RadixTrieMultiAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    protected void otherInitialisation()
    {
        ((RadixTrieMulti)algorithm).initialiseMethods(getCodeBase().toString(), (SplitAlgorithmWindow)window);
    }

    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("RadixTrieMultiApplet.1");
    protected static final String EXPLANATION_TITLE = Messages.getString("RadixTrieMultiApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("RadixTrieMultiApplet.3");

}
