// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InsertionSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class InsertionSortApplet extends AlgorithmApplet
{

    public InsertionSortApplet()
    {
        imageDirectory = "images/";
        algorithmFileName = ALGORITHM_FILE_NAME;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new InsertionSortCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new InsertionSortThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new InsertionSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return new IntArray(InsertionSortAnimationWindow.DEFAULT_DATA);
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

    private static final long serialVersionUID = 0x7844dbe8b539e87cL;
    public static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME = Messages.getString("InsertionSortApplet.0");
    protected static final String EXPLANATION_TITLE = Messages.getString("InsertionSortApplet.2");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("InsertionSortApplet.1");

}
