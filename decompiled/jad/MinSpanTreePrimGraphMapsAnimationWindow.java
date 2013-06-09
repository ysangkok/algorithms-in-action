// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimGraphMapsAnimationWindow.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;
import localization.Messages;

public class MinSpanTreePrimGraphMapsAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "Minimum Spanning Tree";
    }

    public MinSpanTreePrimGraphMapsAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        graphMapsThread = null;
        secondWindow = null;
        secondCanvas = null;
        frameTitle = FRAME_TITLE;
        buildDate = "10/2/2000";
        buildBy = "George Kong";
        selfTestMode = new SelfTestModeSelection("Self Test Mode", false, this, thread);
        addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        addControlButton(new GraphMapsRestartButton(this, thread));
        addControlButton(new ZoomInButton(this, thread));
        addControlButton(new ZoomOutButton(this, thread));
        addControlListener(new ControlListener() {

            public void controlBack(ControlEvent e)
            {
                setBackMode();
            }

            public void controlPause(ControlEvent controlevent)
            {
            }

            public void controlReset(ControlEvent controlevent)
            {
            }

            public void controlRestart(ControlEvent controlevent)
            {
            }

            public void controlRun(ControlEvent controlevent)
            {
            }

            public void controlStep(ControlEvent controlevent)
            {
            }

            public void controlOther(ControlEvent e)
            {
                if(e.getType() == 2340)
                    zoom(true);
                else
                if(e.getType() == 2341)
                    zoom(false);
                else
                if(e.getType() == 2342)
                    restartAlgorithm();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        loadGraphTemplates();
        userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, MIN_SIZE, MAX_SIZE, MINIMUM_NUMBER_OF_ELEMENTS, MAXIMUM_NUMBER_OF_ELEMENTS);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        MinSpanTreePrimGraphMaps graphMaps = (MinSpanTreePrimGraphMaps)getAlgorithm();
        graphMapsThread = (MinSpanTreePrimGraphMapsThread)thread;
        selfTestMode.setEnabled(false);
        secondCanvas = new MinSpanTreePrimGraphMapsCanvasExt();
        thread.addRepaintListener(secondCanvas);
        secondWindow = new MinSpanTreePrimGraphMapsAnimationWindowExt(secondCanvas, thread, applet);
    }

    public void init()
    {
        super.init();
        secondWindow.init();
        secondWindow.setSize(440, 368);
        secondWindow.setLocation(585, 367);
        secondWindow.setVisible(true);
    }

    public void exit()
    {
        super.exit();
        secondWindow.exit();
        secondWindow.dispose();
    }

    protected void setBackMode()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        if(graphMaps != null)
            graphMaps.setBackMode();
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseAlgorithmMenuItem();
        initialiseModeSelectionMenuItem();
        initialiseDataSelectionMenuItem();
        initialiseLinkMenuItem();
        initialiseNodesItem();
        initialiseHelpMenuItem();
    }

    protected void initialiseLinkMenuItem()
    {
        Menu mnuLink = new Menu(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.20"));
        MenuItem mnuAddLink = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.6"));
        MenuItem mnuDeleteLink = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.7"));
        MenuItem mnuEditLink = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.8"));
        mnuAddLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                addNewLink();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                deleteLink();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuEditLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                editLink();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuLink.add(mnuAddLink);
        mnuLink.add(mnuDeleteLink);
        mnuLink.add(mnuEditLink);
        menuBar.add(mnuLink);
    }

    protected void initialiseNodesItem()
    {
        Menu mnuNodeMenu = new Menu(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.9"));
        MenuItem itemAddNode = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.10"));
        MenuItem itemDeleteNode = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.11"));
        MenuItem itemChangeKey = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.12"));
        itemAddNode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                addNode();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                deleteNode();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        itemChangeKey.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                changeKey();
            }

            final MinSpanTreePrimGraphMapsAnimationWindow this$0;

            
            {
                this$0 = MinSpanTreePrimGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuNodeMenu.add(itemAddNode);
        mnuNodeMenu.add(itemDeleteNode);
        mnuNodeMenu.add(itemChangeKey);
        menuBar.add(mnuNodeMenu);
    }

    protected void resetAlgorithmEditModes(GraphMaps graphMaps)
    {
        graphMaps.setKeyChangeMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setInsertionMode(false);
        graphMaps.endEditLink();
    }

    protected void addNode()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.setInsertionMode(true);
    }

    protected void deleteNode()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.setDeleteMode(true);
    }

    protected void changeKey()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.setKeyChangeMode(true);
    }

    protected void addNewLink()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(1));
    }

    protected void deleteLink()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(0));
    }

    protected void editLink()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(null);
    }

    protected void setOmniDirectional(boolean p_omniDir)
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        graphMaps.setOmniDirectional(p_omniDir);
    }

    protected void zoom(boolean p_in)
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        if(p_in)
            graphMaps.zoomIn();
        else
            graphMaps.zoomOut();
    }

    protected void restartAlgorithm()
    {
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        IntArray data = (IntArray)graphMaps.getData();
        graphMapsThread.setData(data);
    }

    protected void loadGraphTemplates()
    {
        URL templateURL = null;
        InputStream templateIS = null;
        InputStreamReader templateISR = null;
        LineNumberReader templateLineReader = null;
        String strBuffer = null;
        try
        {
            templateURL = new URL(applet.getCodeBase(), Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.13"));
        }
        catch(MalformedURLException e)
        {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.14"));
        }
        try
        {
            templateIS = templateURL.openStream();
        }
        catch(IOException e)
        {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.15"));
        }
        try
        {
            templateISR = new InputStreamReader(templateIS);
            templateLineReader = new LineNumberReader(templateISR);
            do
            {
                if((strBuffer = templateLineReader.readLine()) == null)
                    break;
                if(strBuffer.trim().length() != 0 && strBuffer.charAt(0) != '#')
                    processLine(strBuffer);
            } while(true);
        }
        catch(IOException e)
        {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.16"));
        }
        try
        {
            templateIS.close();
        }
        catch(IOException e)
        {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.17"));
        }
    }

    private void processLine(String strTemplateLine)
    {
        StringTokenizer tokenizer = null;
        Vector vecData = new Vector();
        tokenizer = new StringTokenizer(strTemplateLine, ",");
        String strGraphName = tokenizer.nextToken();
        String strToken;
        for(; tokenizer.hasMoreTokens(); vecData.addElement(strToken))
            strToken = tokenizer.nextToken();

        int data[] = new int[vecData.size()];
        try
        {
            for(int i = 0; i < vecData.size(); i++)
            {
                String strToken = (String)vecData.elementAt(i);
                data[i] = Integer.parseInt(strToken.trim());
            }

        }
        catch(NumberFormatException e)
        {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.19"));
        }
        PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        addDataSelection(templateGraph);
    }

    protected static final String FRAME_TITLE = Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.0");
    protected static final String SELF_TEST_MODE_LABEL = "Self Test Mode";
    public static int MINIMUM_NUMBER_OF_ELEMENTS = 0;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS = 500;
    public static int MAX_SIZE = 500;
    public static int MIN_SIZE = 0;
    public static final int DEFAULT_DATA[] = {
        10, 135, 35, 230, 90, 290, 175, 225, 175, 135, 
        90, 165, 175, 110, 175, 29, 90, 45, 175, -5, 
        175, 0, 1, 8, 1, 2, 6, 1, 3, 5, 
        0, 4, 8, 4, 5, 4, 4, 6, 3, 0, 
        7, 10, 7, 8, 13, 7, 9, 9, 4, 7, 
        9, 4, 1, 12, 9, 8, 7, 8, 6, 12, 
        6, 5, 9, 5, 3, 7, 3, 2, 12
    };
    protected UserSelectionIntArrayDataSelection userData;
    protected MinSpanTreePrimGraphMapsThread graphMapsThread;
    protected MinSpanTreePrimGraphMapsAnimationWindowExt secondWindow;
    protected MinSpanTreePrimGraphMapsCanvasExt secondCanvas;

}
