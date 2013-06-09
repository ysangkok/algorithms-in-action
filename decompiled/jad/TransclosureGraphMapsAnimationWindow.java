// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransclosureGraphMapsAnimationWindow.java

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

public class TransclosureGraphMapsAnimationWindow extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "Transitive Closure";
    }

    public TransclosureGraphMapsAnimationWindow(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        graphMapsThread = null;
        secondWindow = null;
        secondCanvas = null;
        frameTitle = FRAME_TITLE;
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

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        loadGraphTemplates();
        userData = new UserSelectionIntArrayDataSelection((new StringBuilder()).append(USER_SELECTION_LABEL).append("...").toString(), false, this, USER_SELECTION_LABEL, MIN_SIZE, MAX_SIZE, MINIMUM_NUMBER_OF_ELEMENTS, MAXIMUM_NUMBER_OF_ELEMENTS);
        userData.setReinitialiseAlgorithm(true);
        userData.setClearAlgorithmState(true);
        addDataSelection(userData);
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        graphMapsThread = (TransclosureGraphMapsThread)thread;
        selfTestMode.setEnabled(false);
        secondCanvas = new TransclosureGraphMapsCanvasExt();
        thread.addRepaintListener(secondCanvas);
        secondWindow = new TransclosureGraphMapsAnimationWindowExt(secondCanvas, thread, applet);
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
        Menu mnuLink = new Menu(Messages.getString("TransclosureGraphMapsAnimationWindow.3"));
        MenuItem mnuAddLink = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.4"));
        MenuItem mnuDeleteLink = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.5"));
        MenuItem mnuSeparator = new MenuItem("-");
        MenuItem mnuMonoDir = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.7"));
        MenuItem mnuOmniDir = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.8"));
        mnuAddLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                addNewLink();
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                deleteLink();
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuMonoDir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setOmniDirectional(false);
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuOmniDir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setOmniDirectional(true);
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
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
        Menu mnuNodeMenu = new Menu(Messages.getString("TransclosureGraphMapsAnimationWindow.9"));
        MenuItem itemAddNode = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.10"));
        MenuItem itemDeleteNode = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.11"));
        MenuItem itemKeyChange = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.12"));
        itemAddNode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                addNode();
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                deleteNode();
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        itemKeyChange.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                keyChange();
            }

            final TransclosureGraphMapsAnimationWindow this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindow.this;
                super();
            }
        });
        mnuNodeMenu.add(itemAddNode);
        mnuNodeMenu.add(itemDeleteNode);
        mnuNodeMenu.add(itemKeyChange);
        menuBar.add(mnuNodeMenu);
    }

    protected void resetAlgorithmEditModes(GraphMaps graphMaps)
    {
        graphMaps.setInsertionMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setKeyChangeMode(false);
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

    protected void keyChange()
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
            templateURL = new URL(applet.getCodeBase(), Messages.getString("TransclosureGraphMapsAnimationWindow.13"));
        }
        catch(MalformedURLException e)
        {
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.14"));
        }
        try
        {
            templateIS = templateURL.openStream();
        }
        catch(IOException e)
        {
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.15"));
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
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.16"));
        }
        try
        {
            templateIS.close();
        }
        catch(IOException e)
        {
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.17"));
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
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.19"));
        }
        PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        addDataSelection(templateGraph);
    }

    protected static final String FRAME_TITLE = Messages.getString("TransclosureGraphMapsAnimationWindow.0");
    protected static final String SELF_TEST_MODE_LABEL = Messages.getString("TransclosureGraphMapsAnimationWindow.1");
    public static int MINIMUM_NUMBER_OF_ELEMENTS = 0;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS = 500;
    public static int MAX_SIZE = 500;
    public static int MIN_SIZE = 0;
    public static final int DEFAULT_DATA[] = {
        5, 110, 45, 175, 100, 140, 160, 80, 160, 45, 
        100, 0, 1, 1, 1, 2, 1, 2, 3, 1, 
        3, 4, 1, 0, 4, 1
    };
    protected UserSelectionIntArrayDataSelection userData;
    protected TransclosureGraphMapsThread graphMapsThread;
    protected TransclosureGraphMapsAnimationWindowExt secondWindow;
    protected TransclosureGraphMapsCanvasExt secondCanvas;

}
