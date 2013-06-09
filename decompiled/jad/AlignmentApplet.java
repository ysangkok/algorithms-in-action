// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentApplet.java

import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import localization.Messages;

public class AlignmentApplet extends AlgorithmApplet
{

    public AlignmentApplet()
    {
        debug = false;
        imageDirectory = "images/";
        explanationTitle = " Introduction to Alignment";
        explanationFileName = EXPLANATION_FILE_NAME;
        if(debug)
        {
            Button b = new Button("Display Positions");
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    displayWindowLocations();
                }

                final AlignmentApplet this$0;

            
            {
                this$0 = AlignmentApplet.this;
                super();
            }
            });
            add(b);
        }
    }

    public AlgorithmCanvas getAlgorithmCanvas()
    {
        theAlignmentCanvas = new AlignmentCanvas();
        return theAlignmentCanvas;
    }

    public AlgorithmThread getAlgorithmThread(Copyable copyable, AlgorithmWindow algorithmWindow)
    {
        return new AlignmentThread(copyable, algorithmWindow);
    }

    public AnimationWindow getAnimationWindow(AlgorithmCanvas algorithmCanvas, AlgorithmThread algorithmThread, Applet applet)
    {
        return new AlignmentAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }

    public Copyable getInitialData()
    {
        return initialData;
    }

    protected void otherInitialisation()
    {
        expWin = explainationWindow;
        codeBaseString = getCodeBase().toString();
    }

    public void parseParameter(String parameter)
    {
        Alignment.setRunningMode(parameter);
        if(Alignment.runningMode == 1)
        {
            FIRST_FILE_NAME = GLOBAL_DIST_NOGAP_NAME;
            SECOND_FILE_NAME = GLOBAL_DIST_GAP_NAME;
            FIRST_EXPLANATION_NAME = EXPLANATION_DIST_NOGAP;
            SECOND_EXPLANATION_NAME = EXPLANATION_DIST_GAP;
            initialData = new StringArray(AlignmentAnimationWindow.GLOBAL_DIST_NOGAP_DATA);
        }
        if(Alignment.runningMode == 2)
        {
            FIRST_FILE_NAME = GLOBAL_SIM_NOGAP_NAME;
            SECOND_FILE_NAME = GLOBAL_SIM_GAP_NAME;
            FIRST_EXPLANATION_NAME = EXPLANATION_SIM_NOGAP;
            SECOND_EXPLANATION_NAME = EXPLANATION_SIM_GAP;
            initialData = new StringArray(AlignmentAnimationWindow.GLOBAL_SIM_NOGAP_DATA);
        }
        if(Alignment.runningMode == 3)
        {
            FIRST_FILE_NAME = LOCAL_SIM_NOGAP_NAME;
            SECOND_FILE_NAME = LOCAL_SIM_GAP_NAME;
            FIRST_EXPLANATION_NAME = EXPLANATION_SIMLT_NOGAP;
            initialData = new StringArray(AlignmentAnimationWindow.LOCAL_SIM_NOGAP_DATA);
        }
        algorithmFileName = FIRST_FILE_NAME;
        explanationFileName = FIRST_EXPLANATION_NAME;
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

    private static final long serialVersionUID = 0x4b71037f16629df5L;
    protected static final String IMAGE_DIRECTORY = "images/";
    public static final String GLOBAL_DIST_NOGAP_NAME = Messages.getString("AlignmentApplet.1");
    public static final String GLOBAL_DIST_GAP_NAME = Messages.getString("AlignmentApplet.2");
    public static final String GLOBAL_SIM_NOGAP_NAME = Messages.getString("AlignmentApplet.3");
    public static final String GLOBAL_SIM_GAP_NAME = Messages.getString("AlignmentApplet.4");
    public static final String LOCAL_SIM_NOGAP_NAME = Messages.getString("AlignmentApplet.5");
    public static final String LOCAL_SIM_GAP_NAME = Messages.getString("AlignmentApplet.6");
    public static String FIRST_FILE_NAME = "";
    public static String SECOND_FILE_NAME = "";
    public static AlignmentCanvas theAlignmentCanvas;
    protected static final String EXPLANATION_TITLE = " Introduction to Alignment";
    protected static final String EXPLANATION_DIST_NOGAP = Messages.getString("AlignmentApplet.10");
    protected static final String EXPLANATION_DIST_GAP = Messages.getString("AlignmentApplet.11");
    protected static final String EXPLANATION_SIM_NOGAP = Messages.getString("AlignmentApplet.12");
    protected static final String EXPLANATION_SIM_GAP = Messages.getString("AlignmentApplet.13");
    protected static final String EXPLANATION_SIMLT_NOGAP = Messages.getString("AlignmentApplet.14");
    public static ExplainationWindow expWin;
    public static String FIRST_EXPLANATION_NAME = "";
    public static String SECOND_EXPLANATION_NAME = "";
    public static String codeBaseString;
    protected static final String EXPLANATION_FILE_NAME = Messages.getString("AlignmentApplet.17");
    protected boolean debug;
    protected StringArray initialData;

}
