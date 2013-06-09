package com.cim.AIA;

import java.io.*;
import java.net.*;
import localization.*;
import java.applet.*;
import com.cim.common.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public abstract class AnimationWindow extends Frame implements FinishListener, ExitListener, Exitable, Helpable, KeyListener, WindowListener, MouseMotionListener, Controlable, Modeable
{
    public static final String DEFAULT_LABEL;
    public static final String SORTED_LABEL;
    public static final String REVERSE_SORTED_LABEL;
    public static final String SAME_LABEL;
    public static final String RANDOM_LABEL;
    public static final String USER_SELECTION_LABEL;
    public static final String NORMAL_MODE_LABEL;
    public static final String USER_PREDICTION_MODE_LABEL;
    public static final String QUIZ_MODE_LABEL;
    public static final String STEP_LABEL;
    public static final String BACK_LABEL;
    public static final String RUN_LABEL;
    public static final String PAUSE_LABEL;
    public static final String RESET_LABEL;
    public static final String STEP_INSTRUCTIONS;
    public static final String BACK_INSTRUCTIONS;
    public static final String RUN_INSTRUCTIONS;
    public static final String PAUSE_INSTRUCTIONS;
    public static final String RESET_INSTRUCTIONS;
    public static final int START_EVENT = 123128;
    public static final int FINISH_EVENT = 123129;
    protected static String JAR_FILE_NAME;
    protected static final String LOGO_IMAGE_NAME = "smalllogo.gif";
    protected static String imgDir;
    protected String buildDate;
    protected String buildBy;
    public String frameTitle;
    protected String slowerLabel;
    protected String fasterLabel;
    protected String algorithmMenuLabel;
    protected String configurationLabel;
    protected String exitLabel;
    protected String problemSelectionLabel;
    protected String modeSelectionLabel;
    protected String helpLabel;
    protected String aboutLabel;
    protected Vector<ControlButton> controlButtons;
    protected int maxSpeed;
    protected MenuBar menuBar;
    protected Menu algorithmMenu;
    protected RadioMenu dataMenu;
    protected RadioMenu modeMenu;
    protected Menu help;
    protected MenuItem exit;
    protected MenuItem configuration;
    protected MenuItem about;
    protected Scrollbar speedBar;
    protected Panel controlPanel;
    protected AlgorithmThread thread;
    protected AlgorithmCanvas canvas;
    protected Copyable currentData;
    protected Vector<DataSelection> dataSelections;
    protected Vector<ModeSelection> modeSelections;
    protected Vector<ExitListener> exitListeners;
    protected ScrollPane scrollPane;
    protected Vector<HelpListener> helpListeners;
    protected Vector<ControlListener> controlListeners;
    protected Vector<ModeListener> modeListeners;
    protected boolean canStepForward;
    protected Applet applet;
    protected NormalModeSelection normalMode;
    protected SelfTestModeSelection selfTestMode;
    protected Image logoImage;
    protected ImageCanvas logoCanvas;
    
    public AnimationWindow(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super();
        this.buildDate = "dd/mm/yyyy";
        this.buildBy = "Initial Surname";
        this.frameTitle = "AIA: AlgorithmName";
        this.slowerLabel = Messages.getString("AnimationWindow.24");
        this.fasterLabel = Messages.getString("AnimationWindow.25");
        this.algorithmMenuLabel = Messages.getString("AnimationWindow.26");
        this.configurationLabel = Messages.getString("AnimationWindow.27");
        this.exitLabel = Messages.getString("AnimationWindow.28");
        this.problemSelectionLabel = Messages.getString("AnimationWindow.29");
        this.modeSelectionLabel = Messages.getString("AnimationWindow.30");
        this.helpLabel = Messages.getString("AnimationWindow.31");
        this.aboutLabel = Messages.getString("AnimationWindow.32");
        this.controlButtons = new Vector();
        this.maxSpeed = 4000;
        this.dataSelections = new Vector();
        this.modeSelections = new Vector();
        this.exitListeners = new Vector();
        this.helpListeners = new Vector();
        this.controlListeners = new Vector();
        this.modeListeners = new Vector();
        this.canStepForward = true;
        this.logoImage = null;
        this.applet = applet;
        this.canvas = canvas;
        this.thread = thread;
        this.scrollPane = new ScrollPane(0);
        this.algorithmMenu = new Menu(this.algorithmMenuLabel);
        this.configuration = new MenuItem(this.configurationLabel);
        this.exit = new MenuItem(this.exitLabel);
        this.dataMenu = new RadioMenu(this.problemSelectionLabel, true);
        this.modeMenu = new RadioMenu(this.modeSelectionLabel, true);
        this.help = new Menu(this.helpLabel);
        this.about = new MenuItem(this.aboutLabel + "...");
        this.controlPanel = new Panel();
        final MediaTracker tracker = new MediaTracker(this);
        URL newURL = applet.getDocumentBase();
        String modifier = newURL.toString();
        int i = modifier.length() - 1;
        while (modifier.charAt(i) != '/') {
            --i;
        }
        modifier = modifier.substring(0, i);
        modifier = modifier + "/";
        try {
            newURL = new URL(modifier);
        }
        catch (Exception ex) {}
        URL url;
        final URL otherUrl;
        InputStream i1;
        Label_0739:
        {
            try {
                AnimationWindow.imgDir = ((AlgorithmApplet)applet).imageDirectory;
                AnimationWindow.imgDir = newURL.toString() + AnimationWindow.imgDir;
                url = null;
                otherUrl = new URL(applet.getCodeBase() + "images/" + "smalllogo.gif");
                i1 = applet.getClass().getResourceAsStream("images/smalllogo.gif");
                this.logoImage = StreamImage.getImage(i1);
                if (this.logoImage == null) {
                    System.out.println("Logo Image is loading from local..." + otherUrl.toString());
                    this.logoImage = applet.getAppletContext().getImage(otherUrl);
                }
                i1 = applet.getClass().getResourceAsStream("images/smalllogo.gif");
                if (!new smalllogoProcessor(url).checkFile(i1)) {
                    try {
                        url = new URL("http://ww2.cs.mu.oz.au/muaia/images/smalllogo.gif");
                        this.logoImage = applet.getAppletContext().getImage(url);
                    }
                    catch (SecurityException ex2) {}
                }
                if (this.logoImage != null) {
                    tracker.addImage(this.logoImage, 0);
                    break Label_0739;
                }
                applet.showStatus("com.cim.AIA.AnimationWindow: Unable to load image: " + url);
                System.out.println("com.cim.AIA.AnimationWindow: Unable to load image: " + url);
            }
            catch (MalformedURLException e) {
                applet.showStatus("com.cim.AIA.AnimationWindow: Invalid URL: " + e);
                System.out.println("com.cim.AIA.AnimationWindow: Invalid URL: " + e);
            }
            return;
        }
        try {
            tracker.waitForAll();
        }
        catch (InterruptedException ex3) {}
        this.logoCanvas = new ImageCanvas(this.logoImage, null);
        this.initialiseDefaultModeMenu();
        this.addKeyListener(this);
        canvas.addKeyListener(this);
        this.addWindowListener(this);
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
                AnimationWindow.this.requestFocus();
            }
            
            public void mouseExited(final MouseEvent e) {
            }
            
            public void mousePressed(final MouseEvent e) {
            }
            
            public void mouseReleased(final MouseEvent e) {
            }
        });
        this.addMouseMotionListener(this);
        canvas.addMouseMotionListener(this);
    }
    
    public void addControlButton(final ControlButton controlButton) {
        this.controlButtons.addElement(controlButton);
        this.addControlListener(controlButton);
        for (int i = 0; i < this.helpListeners.size(); ++i) {
            final HelpListener helpListener = (HelpListener)this.helpListeners.elementAt(i);
            if (!controlButton.helpListeners.contains(helpListener)) {
                controlButton.addHelpListener(helpListener);
            }
        }
        this.controlPanel.add(controlButton);
    }
    
    public void addControlListener(final ControlListener controlListener) {
        this.controlListeners.addElement(controlListener);
    }
    
    public void addDataSelection(final DataSelection dataSelection) {
        this.dataMenu.add(dataSelection);
        this.dataSelections.addElement(dataSelection);
    }
    
    public void addExitListener(final ExitListener exitListener) {
        this.exitListeners.addElement(exitListener);
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.helpListeners.addElement(helpListener);
        if (this.logoCanvas != null && !this.logoCanvas.helpListeners.contains(helpListener)) {
            this.logoCanvas.addHelpListener(helpListener);
        }
        for (int i = 0; i < this.controlButtons.size(); ++i) {
            final ControlButton controlButton = (ControlButton)this.controlButtons.elementAt(i);
            if (!controlButton.helpListeners.contains(helpListener)) {
                controlButton.addHelpListener(helpListener);
            }
        }
    }
    
    public void addModeListener(final ModeListener modeListener) {
        this.modeListeners.addElement(modeListener);
    }
    
    public void addModeSelection(final ModeSelection modeSelection) {
        this.modeMenu.add(modeSelection);
        this.modeSelections.addElement(modeSelection);
        this.addModeListener(modeSelection);
    }
    
    private void buildConstraints(final GridBagConstraints gbc, final int gx, final int gy, final int gw, final int gh, final int wx, final int wy) {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = (double)wx;
        gbc.weighty = (double)wy;
    }
    
    public void cleanUp() {
        if (this.controlButtons != null) {
            this.controlButtons.removeAllElements();
        }
        this.controlButtons = null;
        int i;
        if (this.menuBar != null) {
            for (i = 0; i < this.menuBar.getMenuCount(); ++i) {
                this.menuBar.remove(i);
            }
        }
        this.menuBar = null;
        if (this.algorithmMenu != null) {
            for (i = 0; i < this.algorithmMenu.getItemCount(); ++i) {
                this.algorithmMenu.remove(i);
            }
        }
        this.algorithmMenu = null;
        if (this.help != null) {
            for (i = 0; i < this.help.getItemCount(); ++i) {
                this.help.remove(i);
            }
        }
        this.help = null;
        if (this.dataMenu != null) {
            for (i = 0; i < this.dataMenu.getItemCount(); ++i) {
                this.dataMenu.remove(i);
            }
        }
        this.dataMenu = null;
        if (this.modeMenu != null) {
            for (i = 0; i < this.modeMenu.getItemCount(); ++i) {
                this.modeMenu.remove(i);
            }
        }
        this.modeMenu = null;
        this.exit = null;
        this.configuration = null;
        this.about = null;
        this.speedBar = null;
        if (this.controlPanel != null) {
            this.controlPanel.removeAll();
        }
        this.controlPanel = null;
        if (this.scrollPane != null) {
            this.scrollPane.removeAll();
        }
        this.scrollPane = null;
        this.thread = null;
        if (this.canvas != null) {
            this.canvas.cleanUp();
        }
        this.canvas = null;
        this.currentData = null;
        if (this.dataSelections != null) {
            this.dataSelections.removeAllElements();
        }
        this.dataSelections = null;
        if (this.modeSelections != null) {
            this.modeSelections.removeAllElements();
        }
        this.modeSelections = null;
        if (this.helpListeners != null) {
            this.helpListeners.removeAllElements();
        }
        this.helpListeners = null;
        if (this.controlListeners != null) {
            this.controlListeners.removeAllElements();
        }
        this.controlListeners = null;
        if (this.modeListeners != null) {
            this.modeListeners.removeAllElements();
        }
        this.modeListeners = null;
        this.applet = null;
        this.normalMode = null;
        this.selfTestMode = null;
        if (this.logoImage != null) {
            this.logoImage.flush();
        }
        this.logoImage = null;
        if (this.logoCanvas != null) {
            this.logoCanvas.cleanUp();
        }
        this.logoCanvas = null;
    }
    
    public void exit() {
        if (this.thread != null) {
            this.thread.endThread();
        }
        final ExitEvent e = new ExitEvent(this);
        if (this.exitListeners != null) {
            for (int i = 0; this.exitListeners != null && i < this.exitListeners.size(); ++i) {
                ((ExitListener)this.exitListeners.elementAt(i)).processExitEvent(e);
            }
            this.exitListeners.removeAllElements();
            this.exitListeners = null;
        }
    }
    
    protected Algorithm getAlgorithm() {
        return this.thread.getAlgorithm();
    }
    
    protected AlgorithmWindow getAlgorithmWindow() {
        return this.thread.getAlgorithmWindow();
    }
    
    public BreakPoint getBreakPoint() {
        return this.thread.getBreakPoint();
    }
    
    public final String getBuildInfo() {
        return this.frameTitle + ". Build Date: " + this.buildDate + " (" + this.buildBy + ") \n" + "Copyright (c) 1998-99 Department of Computer Science, " + "Melbourne University" + "\n" + "By Commercial Interactive Media " + "(http://www.cim.com.au info@cim.com.au)";
    }
    
    public Container getContainer() {
        return this.scrollPane;
    }
    
    public Object getCurrentData() {
        return this.currentData.copy();
    }
    
    public Logger getLogger() {
        return this.thread.getLogger();
    }
    
    public AlgorithmThread getThread() {
        return this.thread;
    }
    
    public void informControlListeners(final ControlEvent e) {
        this.requestFocus();
        final int type = e.getType();
        for (int i = 0; i < this.controlListeners.size(); ++i) {
            final ControlListener controlListener = (ControlListener)this.controlListeners.elementAt(i);
            switch (type) {
                case 123123: {
                    controlListener.controlStep(e);
                    break;
                }
                case 123124: {
                    controlListener.controlBack(e);
                    break;
                }
                case 123125: {
                    controlListener.controlPause(e);
                    break;
                }
                case 123126: {
                    controlListener.controlReset(e);
                    break;
                }
                case 123127: {
                    controlListener.controlRun(e);
                    break;
                }
                case 123128: {
                    controlListener.controlRestart(e);
                    break;
                }
                default: {
                    controlListener.controlOther(e);
                    break;
                }
            }
        }
    }
    
    public void informModeListeners(final ModeEvent e) {
        final int type = e.getType();
        for (int i = 0; i < this.modeListeners.size(); ++i) {
            final ModeListener modeListener = (ModeListener)this.modeListeners.elementAt(i);
            switch (type) {
                case 12356: {
                    modeListener.modeNormal(e);
                    break;
                }
                case 12357: {
                    modeListener.modeSelfTest(e);
                    break;
                }
                case 12358: {
                    modeListener.modeQuiz(e);
                    break;
                }
                default: {
                    modeListener.modeOther(e);
                    break;
                }
            }
        }
    }
    
    public void init() {
        this.setTitle(this.frameTitle);
        this.initialiseMenuBar();
        final Panel panel2;
        final Panel panel = panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        final Panel logoControlPanel = new Panel();
        final GridBagLayout gbLayout2 = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        logoControlPanel.setLayout(gbLayout2);
        logoControlPanel.setBackground(Color.gray.brighter());
        this.buildConstraints(constraints, 0, 0, 1, 1, 5, 100);
        constraints.insets = new Insets(0, 5, 5, 0);
        constraints.anchor = 17;
        constraints.fill = 0;
        gbLayout2.setConstraints(this.logoCanvas, constraints);
        logoControlPanel.add(this.logoCanvas);
        this.controlPanel.setBackground(Color.gray.brighter());
        this.buildConstraints(constraints, 1, 0, 1, 1, 95, 100);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = 10;
        gbLayout2.setConstraints(this.controlPanel, constraints);
        logoControlPanel.add(this.controlPanel);
        this.resetButtons();
        panel.add(logoControlPanel, "North");
        panel.addMouseMotionListener(this);
        this.scrollPane.add(this.canvas);
        panel.add(this.scrollPane, "Center");
        final GridBagLayout gb = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();
        final Panel speedPanel = new Panel();
        speedPanel.setBackground(Color.gray.brighter());
        speedPanel.setLayout(gb);
        c.insets = new Insets(0, 0, 0, 0);
        final Label slow = new Label(this.slowerLabel, 1);
        gb.setConstraints(slow, c);
        speedPanel.add(slow);
        this.speedBar = new Scrollbar(0, this.maxSpeed / 2, 1, 1, this.maxSpeed);
        this.getLogger().setSpeed(this.speedBar.getValue());
        this.speedBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                AnimationWindow.this.thread.setRunSleepDuration(AnimationWindow.this.maxSpeed - AnimationWindow.this.speedBar.getValue());
                AnimationWindow.this.getLogger().setSpeed(AnimationWindow.this.speedBar.getValue());
                AnimationWindow.this.canvas.setTweenSpeed(AnimationWindow.this.maxSpeed - AnimationWindow.this.speedBar.getValue());
            }
        });
        this.thread.setRunSleepDuration(this.maxSpeed - this.speedBar.getValue());
        this.canvas.setTweenSpeed(this.maxSpeed - this.speedBar.getValue());
        c.fill = 2;
        c.weightx = 10.0;
        gb.setConstraints(this.speedBar, c);
        speedPanel.add(this.speedBar);
        final Label fast = new Label(this.fasterLabel, 1);
        c.weightx = 0.0;
        c.fill = 0;
        gb.setConstraints(fast, c);
        speedPanel.add(fast);
        panel.add(speedPanel, "South");
        this.add(panel);
    }
    
    protected void initialiseAlgorithmMenuItem() {
        this.configuration.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ConfigurationManager.getConfigurationManager().askUserForChanges();
            }
        });
        this.algorithmMenu.add(this.configuration);
        this.algorithmMenu.addSeparator();
        this.exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AnimationWindow.this.exit();
            }
        });
        this.algorithmMenu.add(this.exit);
        this.menuBar.add(this.algorithmMenu);
    }
    
    protected void initialiseDataSelectionMenuItem() {
        this.menuBar.add(this.dataMenu);
    }
    
    protected void initialiseDefaultModeMenu() {
        this.normalMode = new NormalModeSelection(AnimationWindow.NORMAL_MODE_LABEL, true, this, this.thread);
        this.selfTestMode = new SelfTestModeSelection(AnimationWindow.USER_PREDICTION_MODE_LABEL, false, this, this.thread);
        this.addModeSelection(this.normalMode);
        this.addModeSelection(this.selfTestMode);
    }
    
    protected void initialiseHelpMenuItem() {
        this.help.addSeparator();
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final MessageDialog messageDialog;
                final MessageDialog aboutMessage = messageDialog = new MessageDialog(AnimationWindow.this.getBuildInfo(), true, true);
                messageDialog.setTitle(AnimationWindow.this.aboutLabel + " " + AnimationWindow.this.frameTitle);
                aboutMessage.setVisible(true);
            }
        });
        this.help.add(this.about);
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseAlgorithmMenuItem();
        this.initialiseModeSelectionMenuItem();
        this.initialiseDataSelectionMenuItem();
        this.initialiseHelpMenuItem();
    }
    
    protected void initialiseModeSelectionMenuItem() {
        this.menuBar.add(this.modeMenu);
    }
    
    public void keyPressed(final KeyEvent e) {
        this.processAKeyEvent(e);
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.requestFocus();
    }
    
    protected void processAKeyEvent(final KeyEvent e) {
        final int code = e.getKeyCode();
        if ((code == 40 || code == 39) && this.canStepForward) {
            this.informControlListeners(new ControlEvent(this, 123123));
            this.thread.stepMode();
        }
        else if (code == 38 || code == 37) {
            this.canStepForward = true;
            this.informControlListeners(new ControlEvent(this, 123124));
            this.thread.backMode();
        }
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.removeMouseMotionListener(this);
        this.canvas.removeMouseMotionListener(this);
        this.removeAll();
        this.cleanUp();
        this.dispose();
    }
    
    public void processFinishEvent(final FinishEvent e) {
        this.canStepForward = false;
        this.informControlListeners(new ControlEvent(this, 123129));
    }
    
    public void removeControlListener(final ControlListener controlListener) {
        this.controlListeners.removeElement(controlListener);
    }
    
    public void removeExitListener(final ExitListener exitListener) {
        this.exitListeners.removeElement(exitListener);
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.helpListeners.removeElement(helpListener);
        if (this.logoCanvas.helpListeners.contains(helpListener)) {
            this.logoCanvas.removeHelpListener(helpListener);
        }
        for (int i = 0; i < this.controlButtons.size(); ++i) {
            final ControlButton controlButton = (ControlButton)this.controlButtons.elementAt(i);
            if (controlButton.helpListeners.contains(helpListener)) {
                controlButton.removeHelpListener(helpListener);
            }
        }
    }
    
    public void removeModeListener(final ModeListener modeListener) {
        this.modeListeners.removeElement(modeListener);
    }
    
    protected void resetButtons() {
        this.informControlListeners(new ControlEvent(this, 123128));
    }
    
    protected void resetDataSelectionCheckBoxes() {
        this.resetButtons();
        for (int i = 0; i < this.dataSelections.size(); ++i) {
            ((DataSelection)this.dataSelections.elementAt(i)).setState(false);
        }
    }
    
    protected void resetModeCheckBoxes() {
        for (int i = 0; i < this.modeSelections.size(); ++i) {
            ((ModeSelection)this.modeSelections.elementAt(i)).setState(false);
        }
    }
    
    public void resetThread() {
        this.canStepForward = true;
        this.thread.setData(this.currentData);
    }
    
    public void resetThread(final boolean reInitialiseAlgorithm, final boolean clearAlgorithmState, final boolean storeAlgorithmState, final boolean forceAlgorithmStore) {
        this.canStepForward = true;
        this.thread.setData(this.currentData, reInitialiseAlgorithm, clearAlgorithmState, storeAlgorithmState, forceAlgorithmStore);
    }
    
    public void setCurrentData(final Copyable currentData) {
        this.setCurrentData(currentData, true, true);
    }
    
    public void setCurrentData(final Copyable currentData, final boolean reInitialiseAlgorithm, final boolean clearAlgorithmState) {
        this.currentData = currentData;
        this.thread.setData(currentData, reInitialiseAlgorithm, clearAlgorithmState, false, false);
    }
    
    public void windowActivated(final WindowEvent e) {
    }
    
    public void windowClosed(final WindowEvent e) {
    }
    
    public void windowClosing(final WindowEvent e) {
        this.exit();
    }
    
    public void windowDeactivated(final WindowEvent e) {
    }
    
    public void windowDeiconified(final WindowEvent e) {
    }
    
    public void windowIconified(final WindowEvent e) {
    }
    
    public void windowOpened(final WindowEvent e) {
    }
    
    public abstract String getAlgorithmName();
    
    static {
        DEFAULT_LABEL = Messages.getString("AnimationWindow.3");
        SORTED_LABEL = Messages.getString("AnimationWindow.4");
        REVERSE_SORTED_LABEL = Messages.getString("AnimationWindow.5");
        SAME_LABEL = Messages.getString("AnimationWindow.6");
        RANDOM_LABEL = Messages.getString("AnimationWindow.7");
        USER_SELECTION_LABEL = Messages.getString("AnimationWindow.8");
        NORMAL_MODE_LABEL = Messages.getString("AnimationWindow.9");
        USER_PREDICTION_MODE_LABEL = Messages.getString("AnimationWindow.10");
        QUIZ_MODE_LABEL = Messages.getString("AnimationWindow.11");
        STEP_LABEL = Messages.getString("AnimationWindow.12");
        BACK_LABEL = Messages.getString("AnimationWindow.13");
        RUN_LABEL = Messages.getString("AnimationWindow.14");
        PAUSE_LABEL = Messages.getString("AnimationWindow.15");
        RESET_LABEL = Messages.getString("AnimationWindow.16");
        STEP_INSTRUCTIONS = Messages.getString("AnimationWindow.17");
        BACK_INSTRUCTIONS = Messages.getString("AnimationWindow.18");
        RUN_INSTRUCTIONS = Messages.getString("AnimationWindow.19") + Messages.getString("AnimationWindow.20") + Messages.getString("AnimationWindow.21");
        PAUSE_INSTRUCTIONS = Messages.getString("AnimationWindow.22");
        RESET_INSTRUCTIONS = Messages.getString("AnimationWindow.23");
        AnimationWindow.JAR_FILE_NAME = "aia.jar";
    }
}
