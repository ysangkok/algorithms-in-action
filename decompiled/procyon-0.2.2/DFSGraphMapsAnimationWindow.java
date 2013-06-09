import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import localization.*;
import java.awt.*;
import aia.graph.common.*;
import java.applet.*;
import com.cim.AIA.*;

public class DFSGraphMapsAnimationWindow extends AnimationWindow
{
    protected static final String FRAME_TITLE;
    protected static final String SELF_TEST_MODE_LABEL;
    public static int MINIMUM_NUMBER_OF_ELEMENTS;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS;
    public static int MAX_SIZE;
    public static int MIN_SIZE;
    public static final int[] DEFAULT_DATA;
    protected UserSelectionIntArrayDataSelection userData;
    protected DFSGraphMapsThread graphMapsThread;
    protected DFSGraphMapsAnimationWindowExt secondWindow;
    protected DFSGraphMapsCanvasExt secondCanvas;
    
    public String getAlgorithmName() {
        return "Depth First Search";
    }
    
    public DFSGraphMapsAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.graphMapsThread = null;
        this.secondWindow = null;
        this.secondCanvas = null;
        this.frameTitle = DFSGraphMapsAnimationWindow.FRAME_TITLE;
        this.buildDate = "10/2/2000";
        this.buildBy = "George Kong";
        this.selfTestMode = new SelfTestModeSelection(DFSGraphMapsAnimationWindow.SELF_TEST_MODE_LABEL, false, this, thread);
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
                DFSGraphMapsAnimationWindow.this.setBackMode();
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
                    DFSGraphMapsAnimationWindow.this.zoom(true);
                }
                else if (e.getType() == 2341) {
                    DFSGraphMapsAnimationWindow.this.zoom(false);
                }
                else if (e.getType() == 2342) {
                    DFSGraphMapsAnimationWindow.this.restartAlgorithm();
                }
            }
        });
        this.loadGraphTemplates();
        this.userData = new UserSelectionIntArrayDataSelection(DFSGraphMapsAnimationWindow.USER_SELECTION_LABEL + "...", false, this, DFSGraphMapsAnimationWindow.USER_SELECTION_LABEL, DFSGraphMapsAnimationWindow.MIN_SIZE, DFSGraphMapsAnimationWindow.MAX_SIZE, DFSGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS, DFSGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS);
        this.userData.setReinitialiseAlgorithm(true);
        this.userData.setClearAlgorithmState(true);
        this.addDataSelection(this.userData);
        this.graphMapsThread = (DFSGraphMapsThread)thread;
        this.selfTestMode.setEnabled(false);
        this.secondCanvas = new DFSGraphMapsCanvasExt();
        thread.addRepaintListener(this.secondCanvas);
        this.secondWindow = new DFSGraphMapsAnimationWindowExt(this.secondCanvas, thread, applet);
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
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
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
        final Menu mnuLink = new Menu(Messages.getString("DFSGraphMapsAnimationWindow.5"));
        final MenuItem mnuAddLink = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.6"));
        final MenuItem mnuDeleteLink = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.7"));
        final MenuItem mnuSeparator = new MenuItem("-");
        final MenuItem mnuMonoDir = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.9"));
        final MenuItem mnuOmniDir = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.10"));
        mnuAddLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.addNewLink();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.deleteLink();
            }
        });
        mnuMonoDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.setOmniDirectional(false);
            }
        });
        mnuOmniDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.setOmniDirectional(true);
            }
        });
        mnuLink.add(mnuAddLink);
        mnuLink.add(mnuDeleteLink);
        mnuLink.add(mnuSeparator);
        mnuLink.add(mnuMonoDir);
        mnuLink.add(mnuOmniDir);
        this.menuBar.add(mnuLink);
    }
    
    protected void initialiseNodesItem() {
        final Menu mnuNodeMenu = new Menu(Messages.getString("DFSGraphMapsAnimationWindow.11"));
        final MenuItem itemAddNode = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.12"));
        final MenuItem itemDeleteNode = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.13"));
        final MenuItem itemChangeKey = new MenuItem(Messages.getString("DFSGraphMapsAnimationWindow.14"));
        itemAddNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.addNode();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.deleteNode();
            }
        });
        itemChangeKey.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                DFSGraphMapsAnimationWindow.this.changeKey();
            }
        });
        mnuNodeMenu.add(itemAddNode);
        mnuNodeMenu.add(itemDeleteNode);
        mnuNodeMenu.add(itemChangeKey);
        this.menuBar.add(mnuNodeMenu);
    }
    
    protected void resetAlgorithmEditModes(final GraphMaps graphMaps) {
        graphMaps.setInsertionMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setKeyChangeMode(false);
        graphMaps.endEditLink();
    }
    
    protected void addNewLink() {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(1));
    }
    
    protected void deleteLink() {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.startEditLink(new Integer(0));
    }
    
    protected void addNode() {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setInsertionMode(true);
    }
    
    protected void deleteNode() {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setDeleteMode(true);
    }
    
    protected void changeKey() {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        this.resetAlgorithmEditModes(graphMaps);
        graphMaps.setKeyChangeMode(true);
    }
    
    protected void setOmniDirectional(final boolean p_omniDir) {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        graphMaps.setOmniDirectional(p_omniDir);
    }
    
    protected void zoom(final boolean p_in) {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        if (p_in) {
            graphMaps.zoomIn();
        }
        else {
            graphMaps.zoomOut();
        }
    }
    
    protected void restartAlgorithm() {
        final DFSGraphMaps graphMaps = (DFSGraphMaps)this.getAlgorithm();
        final IntArray data = graphMaps.getData();
        this.graphMapsThread.setData(data);
    }
    
    protected void loadGraphTemplates() {
        URL templateURL = null;
        InputStream templateIS = null;
        InputStreamReader templateISR = null;
        LineNumberReader templateLineReader = null;
        String strBuffer = null;
        try {
            templateURL = new URL(this.applet.getCodeBase(), Messages.getString("DFSGraphMapsAnimationWindow.15"));
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
            System.out.println("Error in template file format");
        }
        final PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        this.addDataSelection(templateGraph);
    }
    
    static {
        FRAME_TITLE = Messages.getString("DFSGraphMapsAnimationWindow.0");
        SELF_TEST_MODE_LABEL = Messages.getString("DFSGraphMapsAnimationWindow.1");
        DFSGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS = 0;
        DFSGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS = 300;
        DFSGraphMapsAnimationWindow.MAX_SIZE = 500;
        DFSGraphMapsAnimationWindow.MIN_SIZE = 0;
        DEFAULT_DATA = new int[] { 10, 215, 35, 305, 90, 340, 175, 285, 175, 215, 90, 240, 175, 185, 175, 115, 90, 130, 175, 75, 175, 0, 1, 1, 1, 2, 1, 1, 3, 1, 0, 4, 1, 4, 5, 1, 4, 6, 1, 0, 7, 1, 7, 8, 1, 7, 9, 1 };
    }
}
