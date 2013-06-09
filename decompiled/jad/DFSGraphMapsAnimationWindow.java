// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMapsAnimationWindow.java

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

public class DFSGraphMapsAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "Depth First Search";
    }

    public DFSGraphMapsAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        graphMapsThread = null;
        secondWindow = null;
        secondCanvas = null;
        frameTitle = FRAME_TITLE;
        buildDate = "10/2/2000";
        buildBy = "George Kong";
        selfTestMode = new SelfTestModeSelection(SELF_TEST_MODE_LABEL, false, this, thread);
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

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        loadGraphTemplates();
        userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, MIN_SIZE, MAX_SIZE, MINIMUM_NUMBER_OF_ELEMENTS, MAXIMUM_NUMBER_OF_ELEMENTS);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        graphMapsThread = (DFSGraphMapsThread)thread;
        selfTestMode.setEnabled(false);
        secondCanvas = new DFSGraphMapsCanvasExt();
        thread.addRepaintListener(secondCanvas);
        secondWindow = new DFSGraphMapsAnimationWindowExt(secondCanvas, thread, applet);
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
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
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
        Menu mnuLink = new Menu(Messages.getString("DFSGraphMapsAnimationWindow.5"));
        MenuItem mnuAddLink = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.6"));
        MenuItem mnuDeleteLink = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.7"));
        MenuItem mnuSeparator = new MenuItem("-");
        MenuItem mnuMonoDir = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.9"));
        MenuItem mnuOmniDir = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.10"));
        mnuAddLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                addNewLink();
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                deleteLink();
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuMonoDir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setOmniDirectional(false);
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuOmniDir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setOmniDirectional(true);
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuLink.add(mnuAddLink);
        mnuLink.add(mnuDeleteLink);
        mnuLink.add(mnuSeparator);
        mnuLink.add(mnuMonoDir);
        mnuLink.add(mnuOmniDir);
        menuBar.add(mnuLink);
    }

    protected void initialiseNodesItem()
    {
        Menu mnuNodeMenu = new Menu(Messages.getString("DFSGraphMapsAnimationWindow.11"));
        MenuItem itemAddNode = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.12"));
        MenuItem itemDeleteNode = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.13"));
        MenuItem itemChangeKey = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.14"));
        itemAddNode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                addNode();
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                deleteNode();
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
                super();
            }
        });
        itemChangeKey.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                changeKey();
            }

            final DFSGraphMapsAnimationWindow this$0;

            
            {
                this$0 = DFSGraphMapsAnimationWindow.this;
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
        graphMaps.setInsertionMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setKeyChangeMode(false);
        graphMaps.endEditLink();
    }

    protected void addNewLink()
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(1));
    }

    protected void deleteLink()
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(0));
    }

    protected void addNode()
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.setInsertionMode(true);
    }

    protected void deleteNode()
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.setDeleteMode(true);
    }

    protected void changeKey()
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        resetAlgorithmEditModes(graphMaps);
        graphMaps.setKeyChangeMode(true);
    }

    protected void setOmniDirectional(boolean p_omniDir)
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        graphMaps.setOmniDirectional(p_omniDir);
    }

    protected void zoom(boolean p_in)
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        if(p_in)
            graphMaps.zoomIn();
        else
            graphMaps.zoomOut();
    }

    protected void restartAlgorithm()
    {
        DFSGraphMaps graphMaps = (DFSGraphMaps)getAlgorithm();
        com.cim.AIA.IntArray data = graphMaps.getData();
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
            templateURL = new URL(applet.getCodeBase(), Messages.getString("DFSGraphMapsAnimationWindow.15"));
        }
        catch(MalformedURLException e)
        {
            System.out.println("Error in URL");
        }
        try
        {
            templateIS = templateURL.openStream();
        }
        catch(IOException e)
        {
            System.out.println("Error opening template file for reading");
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
            System.out.println("Error reading from template file");
        }
        try
        {
            templateIS.close();
        }
        catch(IOException e)
        {
            System.out.println("Error closing template file");
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
            System.out.println("Error in template file format");
        }
        PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        addDataSelection(templateGraph);
    }

    protected static final String FRAME_TITLE = Messages.getString("DFSGraphMapsAnimationWindow.0");
    protected static final String SELF_TEST_MODE_LABEL = Messages.getString("DFSGraphMapsAnimationWindow.1");
    public static int MINIMUM_NUMBER_OF_ELEMENTS = 0;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS = 300;
    public static int MAX_SIZE = 500;
    public static int MIN_SIZE = 0;
    public static final int DEFAULT_DATA[] = {
        10, 215, 35, 305, 90, 340, 175, 285, 175, 215, 
        90, 240, 175, 185, 175, 115, 90, 130, 175, 75, 
        175, 0, 1, 1, 1, 2, 1, 1, 3, 1, 
        0, 4, 1, 4, 5, 1, 4, 6, 1, 0, 
        7, 1, 7, 8, 1, 7, 9, 1
    };
    protected UserSelectionIntArrayDataSelection userData;
    protected DFSGraphMapsThread graphMapsThread;
    protected DFSGraphMapsAnimationWindowExt secondWindow;
    protected DFSGraphMapsCanvasExt secondCanvas;

}
