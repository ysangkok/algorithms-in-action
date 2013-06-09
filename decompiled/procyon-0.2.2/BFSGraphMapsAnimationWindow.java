import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import aia.graph.common.*;
import java.applet.*;
import com.cim.AIA.*;

public class BFSGraphMapsAnimationWindow extends AnimationWindow
{
    private static final long serialVersionUID = -468854561379227817L;
    protected static final String FRAME_TITLE;
    protected static final String SELF_TEST_MODE_LABEL;
    public static int MINIMUM_NUMBER_OF_ELEMENTS;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS;
    public static int MAX_SIZE;
    public static int MIN_SIZE;
    public static final int[] DEFAULT_DATA;
    protected UserSelectionIntArrayDataSelection userData;
    protected BFSGraphMapsThread graphMapsThread;
    protected BFSGraphMapsAnimationWindowExt secondWindow;
    protected BFSGraphMapsCanvasExt secondCanvas;
    
    public String getAlgorithmName() {
        return "Breadth First Search";
    }
    
    public BFSGraphMapsAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.graphMapsThread = null;
        this.secondWindow = null;
        this.secondCanvas = null;
        this.frameTitle = BFSGraphMapsAnimationWindow.FRAME_TITLE;
        this.buildDate = "10/2/2000";
        this.buildBy = "George Kong";
        this.selfTestMode = new SelfTestModeSelection(BFSGraphMapsAnimationWindow.SELF_TEST_MODE_LABEL, false, this, thread);
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
                BFSGraphMapsAnimationWindow.this.setBackMode();
            }
            
            public void controlOther(final ControlEvent e) {
                if (e.getType() == 2340) {
                    BFSGraphMapsAnimationWindow.this.zoom(true);
                }
                else if (e.getType() == 2341) {
                    BFSGraphMapsAnimationWindow.this.zoom(false);
                }
                else if (e.getType() == 2342) {
                    BFSGraphMapsAnimationWindow.this.restartAlgorithm();
                }
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
        });
        this.loadGraphTemplates();
        this.userData = new UserSelectionIntArrayDataSelection(BFSGraphMapsAnimationWindow.USER_SELECTION_LABEL + "...", false, this, BFSGraphMapsAnimationWindow.USER_SELECTION_LABEL, BFSGraphMapsAnimationWindow.MIN_SIZE, BFSGraphMapsAnimationWindow.MAX_SIZE, BFSGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS, BFSGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS);
        this.userData.setReinitialiseAlgorithm(true);
        this.userData.setClearAlgorithmState(true);
        this.addDataSelection(this.userData);
        this.graphMapsThread = (BFSGraphMapsThread)thread;
        this.selfTestMode.setEnabled(false);
        this.secondCanvas = new BFSGraphMapsCanvasExt();
        thread.addRepaintListener(this.secondCanvas);
        this.secondWindow = new BFSGraphMapsAnimationWindowExt(this.secondCanvas, thread, applet);
    }
    
    protected void addNewLink() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(1));
    }
    
    protected void addNode() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setInsertionMode(true);
    }
    
    protected void changeKey() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setKeyChangeMode(true);
    }
    
    protected void deleteLink() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(0));
    }
    
    protected void deleteNode() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setDeleteMode(true);
    }
    
    public void exit() {
        super.exit();
        this.secondWindow.exit();
        this.secondWindow.dispose();
    }
    
    public void init() {
        super.init();
        this.secondWindow.init();
        this.secondWindow.setSize(440, 368);
        this.secondWindow.setLocation(585, 367);
        this.secondWindow.setVisible(true);
    }
    
    protected void initialiseLinkMenuItem() {
        final Menu mnuLink = new Menu(Messages.getString("GraphMapsAnimationWindow.5"));
        final MenuItem mnuAddLink = new MenuItem(Messages.getString("GraphMapsAnimationWindow.6"));
        final MenuItem mnuDeleteLink = new MenuItem(Messages.getString("GraphMapsAnimationWindow.7"));
        final MenuItem mnuSeparator = new MenuItem("-");
        final MenuItem mnuMonoDir = new MenuItem(Messages.getString("GraphMapsAnimationWindow.9"));
        final MenuItem mnuOmniDir = new MenuItem(Messages.getString("GraphMapsAnimationWindow.10"));
        mnuAddLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.addNewLink();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.deleteLink();
            }
        });
        mnuMonoDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.setOmniDirectional(false);
            }
        });
        mnuOmniDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.setOmniDirectional(true);
            }
        });
        mnuLink.add(mnuAddLink);
        mnuLink.add(mnuDeleteLink);
        mnuLink.add(mnuSeparator);
        mnuLink.add(mnuMonoDir);
        mnuLink.add(mnuOmniDir);
        this.menuBar.add(mnuLink);
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
    
    protected void initialiseNodesItem() {
        final Menu mnuNodeMenu = new Menu(Messages.getString("GraphMapsAnimationWindow.11"));
        final MenuItem itemAddNode = new MenuItem(Messages.getString("GraphMapsAnimationWindow.12"));
        final MenuItem itemDeleteNode = new MenuItem(Messages.getString("GraphMapsAnimationWindow.13"));
        final MenuItem itemChangeKey = new MenuItem(Messages.getString("GraphMapsAnimationWindow.14"));
        itemAddNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.addNode();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.deleteNode();
            }
        });
        itemChangeKey.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BFSGraphMapsAnimationWindow.this.changeKey();
            }
        });
        mnuNodeMenu.add(itemAddNode);
        mnuNodeMenu.add(itemDeleteNode);
        mnuNodeMenu.add(itemChangeKey);
        this.menuBar.add(mnuNodeMenu);
    }
    
    protected void loadGraphTemplates() {
        URL templateURL = null;
        InputStream templateIS = null;
        InputStreamReader templateISR = null;
        LineNumberReader templateLineReader = null;
        String strBuffer = null;
        try {
            templateURL = new URL(this.applet.getCodeBase(), "BreadthFirstSearch.data");
        }
        catch (MalformedURLException e) {
            System.out.println("Error in URL");
        }
        try {
            templateIS = templateURL.openStream();
        }
        catch (IOException e) {
            System.out.println("Error opening template file for reading");
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
            System.out.println("Error reading from template file");
        }
        try {
            templateIS.close();
        }
        catch (IOException e) {
            System.out.println("Error closing template file");
        }
    }
    
    private void processLine(final String strTemplateLine) {
        StringTokenizer tokenizer = null;
        final Vector<String> vecData = new Vector();
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
            System.out.println("Error in template file format");
        }
        final PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        this.addDataSelection(templateGraph);
    }
    
    protected void resetAlgorithmEditModes(final GraphMaps graphMaps) {
        graphMaps.setKeyChangeMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setInsertionMode(false);
        graphMaps.endEditLink();
    }
    
    protected void restartAlgorithm() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        final IntArray data = (IntArray)graphMaps.getData();
        this.graphMapsThread.setData(data);
    }
    
    protected void setBackMode() {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        if (graphMaps != null) {
            graphMaps.setBackMode();
        }
    }
    
    protected void setOmniDirectional(final boolean p_omniDir) {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        graphMaps.setOmniDirectional(p_omniDir);
    }
    
    protected void zoom(final boolean p_in) {
        final BFSGraphMaps graphMaps = (BFSGraphMaps)this.getAlgorithm();
        if (p_in) {
            graphMaps.zoomIn();
        }
        else {
            graphMaps.zoomOut();
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("GraphMapsAnimationWindow.0");
        SELF_TEST_MODE_LABEL = Messages.getString("GraphMapsAnimationWindow.1");
        BFSGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS = 0;
        BFSGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS = 500;
        BFSGraphMapsAnimationWindow.MAX_SIZE = 500;
        BFSGraphMapsAnimationWindow.MIN_SIZE = 0;
        DEFAULT_DATA = new int[] { 10, 215, 35, 305, 90, 340, 175, 285, 175, 215, 90, 240, 175, 185, 175, 115, 90, 130, 175, 75, 175, 0, 1, 1, 1, 2, 1, 1, 3, 1, 0, 4, 1, 4, 5, 1, 4, 6, 1, 0, 7, 1, 7, 8, 1, 7, 9, 1 };
    }
}
