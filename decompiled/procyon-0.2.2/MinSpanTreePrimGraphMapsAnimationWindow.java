import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import localization.*;
import java.awt.*;
import aia.graph.common.*;
import java.applet.*;
import com.cim.AIA.*;

public class MinSpanTreePrimGraphMapsAnimationWindow extends AnimationWindow
{
    protected static final String FRAME_TITLE;
    protected static final String SELF_TEST_MODE_LABEL = "Self Test Mode";
    public static int MINIMUM_NUMBER_OF_ELEMENTS;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS;
    public static int MAX_SIZE;
    public static int MIN_SIZE;
    public static final int[] DEFAULT_DATA;
    protected UserSelectionIntArrayDataSelection userData;
    protected MinSpanTreePrimGraphMapsThread graphMapsThread;
    protected MinSpanTreePrimGraphMapsAnimationWindowExt secondWindow;
    protected MinSpanTreePrimGraphMapsCanvasExt secondCanvas;
    
    public String getAlgorithmName() {
        return "Minimum Spanning Tree";
    }
    
    public MinSpanTreePrimGraphMapsAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.graphMapsThread = null;
        this.secondWindow = null;
        this.secondCanvas = null;
        this.frameTitle = MinSpanTreePrimGraphMapsAnimationWindow.FRAME_TITLE;
        this.buildDate = "10/2/2000";
        this.buildBy = "George Kong";
        this.selfTestMode = new SelfTestModeSelection("Self Test Mode", false, this, thread);
        this.addControlButton(new StepButton(AnimationWindow.STEP_LABEL, AnimationWindow.STEP_INSTRUCTIONS, this, thread));
        this.addControlButton(new BackButton(AnimationWindow.BACK_LABEL, AnimationWindow.BACK_INSTRUCTIONS, this, thread));
        this.addControlButton(new RunButton(AnimationWindow.RUN_LABEL, AnimationWindow.RUN_INSTRUCTIONS, this, thread));
        this.addControlButton(new PauseButton(AnimationWindow.PAUSE_LABEL, AnimationWindow.PAUSE_INSTRUCTIONS, this, thread));
        this.addControlButton(new ResetButton(AnimationWindow.RESET_LABEL, AnimationWindow.RESET_INSTRUCTIONS, this, thread));
        this.addControlButton(new GraphMapsRestartButton(this, thread));
        this.addControlButton(new ZoomInButton(this, thread));
        this.addControlButton(new ZoomOutButton(this, thread));
        this.addControlListener(new ControlListener() {
            public void controlBack(final ControlEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.setBackMode();
            }
            
            public void controlPause(final ControlEvent e) {
            }
            
            public void controlReset(final ControlEvent e) {
            }
            
            public void controlRestart(final ControlEvent e) {
            }
            
            public void controlRun(final ControlEvent e) {
            }
            
            public void controlStep(final ControlEvent e) {
            }
            
            public void controlOther(final ControlEvent e) {
                if (e.getType() == 2340) {
                    MinSpanTreePrimGraphMapsAnimationWindow.this.zoom(true);
                }
                else if (e.getType() == 2341) {
                    MinSpanTreePrimGraphMapsAnimationWindow.this.zoom(false);
                }
                else if (e.getType() == 2342) {
                    MinSpanTreePrimGraphMapsAnimationWindow.this.restartAlgorithm();
                }
            }
        });
        this.loadGraphTemplates();
        this.userData = new UserSelectionIntArrayDataSelection(MinSpanTreePrimGraphMapsAnimationWindow.USER_SELECTION_LABEL + "...", false, this, MinSpanTreePrimGraphMapsAnimationWindow.USER_SELECTION_LABEL, MinSpanTreePrimGraphMapsAnimationWindow.MIN_SIZE, MinSpanTreePrimGraphMapsAnimationWindow.MAX_SIZE, MinSpanTreePrimGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS, MinSpanTreePrimGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS);
        this.userData.setReinitialiseAlgorithm(true);
        this.userData.setClearAlgorithmState(true);
        this.addDataSelection(this.userData);
        final MinSpanTreePrimGraphMaps graphMaps = (MinSpanTreePrimGraphMaps)this.getAlgorithm();
        this.graphMapsThread = (MinSpanTreePrimGraphMapsThread)thread;
        this.selfTestMode.setEnabled(false);
        this.secondCanvas = new MinSpanTreePrimGraphMapsCanvasExt();
        thread.addRepaintListener(this.secondCanvas);
        this.secondWindow = new MinSpanTreePrimGraphMapsAnimationWindowExt(this.secondCanvas, thread, applet);
    }
    
    public void init() {
        super.init();
        this.secondWindow.init();
        this.secondWindow.setSize(440, 368);
        this.secondWindow.setLocation(585, 367);
        this.secondWindow.setVisible(true);
    }
    
    public void exit() {
        super.exit();
        this.secondWindow.exit();
        this.secondWindow.dispose();
    }
    
    protected void setBackMode() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        if (graphMaps != null) {
            graphMaps.setBackMode();
        }
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseLinkMenuItem();
        this.initialiseNodesItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseLinkMenuItem() {
        final Menu mnuLink = new Menu(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.20"));
        final MenuItem mnuAddLink = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.6"));
        final MenuItem mnuDeleteLink = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.7"));
        final MenuItem mnuEditLink = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.8"));
        mnuAddLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.addNewLink();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.deleteLink();
            }
        });
        mnuEditLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.editLink();
            }
        });
        mnuLink.add(mnuAddLink);
        mnuLink.add(mnuDeleteLink);
        mnuLink.add(mnuEditLink);
        this.menuBar.add(mnuLink);
    }
    
    protected void initialiseNodesItem() {
        final Menu mnuNodeMenu = new Menu(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.9"));
        final MenuItem itemAddNode = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.10"));
        final MenuItem itemDeleteNode = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.11"));
        final MenuItem itemChangeKey = new MenuItem(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.12"));
        itemAddNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.addNode();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.deleteNode();
            }
        });
        itemChangeKey.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MinSpanTreePrimGraphMapsAnimationWindow.this.changeKey();
            }
        });
        mnuNodeMenu.add(itemAddNode);
        mnuNodeMenu.add(itemDeleteNode);
        mnuNodeMenu.add(itemChangeKey);
        this.menuBar.add(mnuNodeMenu);
    }
    
    protected void resetAlgorithmEditModes(final GraphMaps graphMaps) {
        graphMaps.setKeyChangeMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setInsertionMode(false);
        graphMaps.endEditLink();
    }
    
    protected void addNode() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setInsertionMode(true);
    }
    
    protected void deleteNode() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setDeleteMode(true);
    }
    
    protected void changeKey() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setKeyChangeMode(true);
    }
    
    protected void addNewLink() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(1));
    }
    
    protected void deleteLink() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(0));
    }
    
    protected void editLink() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(null);
    }
    
    protected void setOmniDirectional(final boolean p_omniDir) {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        graphMaps.setOmniDirectional(p_omniDir);
    }
    
    protected void zoom(final boolean p_in) {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        if (p_in) {
            graphMaps.zoomIn();
        }
        else {
            graphMaps.zoomOut();
        }
    }
    
    protected void restartAlgorithm() {
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        final IntArray data = (IntArray)graphMaps.getData();
        this.graphMapsThread.setData(data);
    }
    
    protected void loadGraphTemplates() {
        URL templateURL = null;
        InputStream templateIS = null;
        InputStreamReader templateISR = null;
        LineNumberReader templateLineReader = null;
        String strBuffer = null;
        try {
            templateURL = new URL(this.applet.getCodeBase(), Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.13"));
        }
        catch (MalformedURLException e) {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.14"));
        }
        try {
            templateIS = templateURL.openStream();
        }
        catch (IOException e) {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.15"));
        }
        try {
            templateISR = new InputStreamReader(templateIS);
            templateLineReader = new LineNumberReader(templateISR);
            while ((strBuffer = templateLineReader.readLine()) != null) {
                if (strBuffer.trim().length() != 0) {
                    if (strBuffer.charAt(0) != '#') {
                        this.processLine(strBuffer);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.16"));
        }
        try {
            templateIS.close();
        }
        catch (IOException e) {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.17"));
        }
    }
    
    private void processLine(final String strTemplateLine) {
        StringTokenizer tokenizer = null;
        final Vector<E> vecData = new Vector();
        tokenizer = new StringTokenizer(strTemplateLine, ",");
        final String strGraphName = tokenizer.nextToken();
        String strToken;
        while (tokenizer.hasMoreTokens()) {
            strToken = tokenizer.nextToken();
            vecData.addElement(strToken);
        }
        final int[] data = new int[vecData.size()];
        try {
            for (int i = 0; i < vecData.size(); ++i) {
                strToken = (String)vecData.elementAt(i);
                data[i] = Integer.parseInt(strToken.trim());
            }
        }
        catch (NumberFormatException e) {
            System.out.println(Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.19"));
        }
        final PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        this.addDataSelection(templateGraph);
    }
    
    static {
        FRAME_TITLE = Messages.getString("MinSpanTreePrimGraphMapsAnimationWindow.0");
        MinSpanTreePrimGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS = 0;
        MinSpanTreePrimGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS = 500;
        MinSpanTreePrimGraphMapsAnimationWindow.MAX_SIZE = 500;
        MinSpanTreePrimGraphMapsAnimationWindow.MIN_SIZE = 0;
        DEFAULT_DATA = new int[] { 10, 135, 35, 230, 90, 290, 175, 225, 175, 135, 90, 165, 175, 110, 175, 29, 90, 45, 175, -5, 175, 0, 1, 8, 1, 2, 6, 1, 3, 5, 0, 4, 8, 4, 5, 4, 4, 6, 3, 0, 7, 10, 7, 8, 13, 7, 9, 9, 4, 7, 9, 4, 1, 12, 9, 8, 7, 8, 6, 12, 6, 5, 9, 5, 3, 7, 3, 2, 12 };
    }
}
