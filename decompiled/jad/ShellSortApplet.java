// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellSortApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import localization.Messages;

public class ShellSortApplet extends AlgorithmApplet
{

    public ShellSortApplet()
    {
        imageDirectory = "images/";
        algorithmFileName = HSubfileComparisonFileName;
        explanationTitle = EXPLANATION_TITLE;
        explanationFileName = EXPLANATION_FILE_NAME;
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        return new ShellSortCanvas();
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new ShellSortThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new ShellSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return new IntArray(ShellSortAnimationWindow.DEFAULT_DATA);
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

    private static final long serialVersionUID = 0x82948476ccc2a278L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String HSubfileComparisonFileName = Messages.getString("ShellSortApplet.3");
    protected static final String SinglePassComparisonFileName = Messages.getString("ShellSortApplet.2");
    protected static final String EXPLANATION_TITLE = Messages.getString("ShellSortApplet.1");
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("ShellSortApplet.4");

}
