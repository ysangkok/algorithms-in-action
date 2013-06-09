import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import localization.*;
import java.awt.*;
import aia.graph.common.*;
import java.applet.*;
import com.cim.AIA.*;

public class TransclosureGraphMapsAnimationWindow extends AnimationWindow
{
    protected static final String FRAME_TITLE;
    protected static final String SELF_TEST_MODE_LABEL;
    public static int MINIMUM_NUMBER_OF_ELEMENTS;
    public static int MAXIMUM_NUMBER_OF_ELEMENTS;
    public static int MAX_SIZE;
    public static int MIN_SIZE;
    public static final int[] DEFAULT_DATA;
    protected UserSelectionIntArrayDataSelection userData;
    protected TransclosureGraphMapsThread graphMapsThread;
    protected TransclosureGraphMapsAnimationWindowExt secondWindow;
    protected TransclosureGraphMapsCanvasExt secondCanvas;
    
    public String getAlgorithmName() {
        return "Transitive Closure";
    }
    
    public TransclosureGraphMapsAnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.graphMapsThread = null;
        this.secondWindow = null;
        this.secondCanvas = null;
        this.frameTitle = TransclosureGraphMapsAnimationWindow.FRAME_TITLE;
        this.selfTestMode = new SelfTestModeSelection(TransclosureGraphMapsAnimationWindow.SELF_TEST_MODE_LABEL, false, this, thread);
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
                TransclosureGraphMapsAnimationWindow.this.setBackMode();
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
                    TransclosureGraphMapsAnimationWindow.this.zoom(true);
                }
                else if (e.getType() == 2341) {
                    TransclosureGraphMapsAnimationWindow.this.zoom(false);
                }
                else if (e.getType() == 2342) {
                    TransclosureGraphMapsAnimationWindow.this.restartAlgorithm();
                }
            }
        });
        this.loadGraphTemplates();
        this.userData = new UserSelectionIntArrayDataSelection(TransclosureGraphMapsAnimationWindow.USER_SELECTION_LABEL + "...", false, this, TransclosureGraphMapsAnimationWindow.USER_SELECTION_LABEL, TransclosureGraphMapsAnimationWindow.MIN_SIZE, TransclosureGraphMapsAnimationWindow.MAX_SIZE, TransclosureGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS, TransclosureGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS);
        this.userData.setReinitialiseAlgorithm(true);
        this.userData.setClearAlgorithmState(true);
        this.addDataSelection(this.userData);
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.graphMapsThread = (TransclosureGraphMapsThread)thread;
        this.selfTestMode.setEnabled(false);
        this.secondCanvas = new TransclosureGraphMapsCanvasExt();
        thread.addRepaintListener(this.secondCanvas);
        this.secondWindow = new TransclosureGraphMapsAnimationWindowExt(this.secondCanvas, thread, applet);
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
        final Menu mnuLink = new Menu(Messages.getString("TransclosureGraphMapsAnimationWindow.3"));
        final MenuItem mnuAddLink = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.4"));
        final MenuItem mnuDeleteLink = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.5"));
        final MenuItem mnuSeparator = new MenuItem("-");
        final MenuItem mnuMonoDir = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.7"));
        final MenuItem mnuOmniDir = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.8"));
        mnuAddLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.addNewLink();
            }
        });
        mnuDeleteLink.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.deleteLink();
            }
        });
        mnuMonoDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.setOmniDirectional(false);
            }
        });
        mnuOmniDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.setOmniDirectional(true);
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
        final Menu mnuNodeMenu = new Menu(Messages.getString("TransclosureGraphMapsAnimationWindow.9"));
        final MenuItem itemAddNode = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.10"));
        final MenuItem itemDeleteNode = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.11"));
        final MenuItem itemKeyChange = new MenuItem(Messages.getString("TransclosureGraphMapsAnimationWindow.12"));
        itemAddNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.addNode();
            }
        });
        itemDeleteNode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.deleteNode();
            }
        });
        itemKeyChange.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TransclosureGraphMapsAnimationWindow.this.keyChange();
            }
        });
        mnuNodeMenu.add(itemAddNode);
        mnuNodeMenu.add(itemDeleteNode);
        mnuNodeMenu.add(itemKeyChange);
        this.menuBar.add(mnuNodeMenu);
    }
    
    protected void resetAlgorithmEditModes(final GraphMaps graphMaps) {
        graphMaps.setInsertionMode(false);
        graphMaps.setDeleteMode(false);
        graphMaps.setKeyChangeMode(false);
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
    
    protected void keyChange() {
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
            templateURL = new URL(this.applet.getCodeBase(), Messages.getString("TransclosureGraphMapsAnimationWindow.13"));
        }
        catch (MalformedURLException e) {
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.14"));
        }
        try {
            templateIS = templateURL.openStream();
        }
        catch (IOException e) {
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.15"));
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
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.16"));
        }
        try {
            templateIS.close();
        }
        catch (IOException e) {
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.17"));
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
            System.out.println(Messages.getString("TransclosureGraphMapsAnimationWindow.19"));
        }
        final PresetIntArrayDataSelection templateGraph = new PresetIntArrayDataSelection(strGraphName, true, this, data);
        templateGraph.setReinitialiseAlgorithm(true);
        templateGraph.setClearAlgorithmState(true);
        this.addDataSelection(templateGraph);
    }
    
    static {
        FRAME_TITLE = Messages.getString("TransclosureGraphMapsAnimationWindow.0");
        SELF_TEST_MODE_LABEL = Messages.getString("TransclosureGraphMapsAnimationWindow.1");
        TransclosureGraphMapsAnimationWindow.MINIMUM_NUMBER_OF_ELEMENTS = 0;
        TransclosureGraphMapsAnimationWindow.MAXIMUM_NUMBER_OF_ELEMENTS = 500;
        TransclosureGraphMapsAnimationWindow.MAX_SIZE = 500;
        TransclosureGraphMapsAnimationWindow.MIN_SIZE = 0;
        DEFAULT_DATA = new int[] { 5, 110, 45, 175, 100, 140, 160, 80, 160, 45, 100, 0, 1, 1, 1, 2, 1, 2, 3, 1, 3, 4, 1, 0, 4, 1 };
    }
}
