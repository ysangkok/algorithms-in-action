package com.cim.AIA;

import java.net.*;
import java.awt.*;
import java.applet.*;
import localization.*;
import javax.swing.*;

public abstract class AlgorithmApplet extends JApplet implements ExitListener
{
    public static final boolean DEBUG = 0;
    private boolean doWindow;
    public String jarFileName;
    public String imageDirectory;
    protected String algorithmWindowName;
    protected String algorithmFileName;
    protected String explanationWindowName;
    protected String explanationTitle;
    protected String explanationFileName;
    protected String explanationCodeTitle;
    protected String helpWindowTitle;
    private String appletInfo;
    protected AlgorithmWindow window;
    protected HelpWindow helpWindow;
    protected ExplainationWindow explainationWindow;
    protected AnimationWindow animWindow;
    protected Algorithm algorithm;
    protected Logger logger;
    protected BreakPoint breakpoint;
    
    public AlgorithmApplet() {
        super();
        this.jarFileName = "aia.jar";
        this.imageDirectory = "images/";
        this.algorithmWindowName = Messages.getString("AlgorithmApplet.2");
        this.algorithmFileName = "algorithm";
        this.explanationWindowName = Messages.getString("AlgorithmApplet.4");
        this.explanationTitle = Messages.getString("AlgorithmApplet.5");
        this.explanationFileName = "explanation";
        this.explanationCodeTitle = Messages.getString("AlgorithmApplet.7");
        this.helpWindowTitle = Messages.getString("AlgorithmApplet.8");
        this.appletInfo = "";
        this.logger = new Logger();
        this.breakpoint = new BreakPoint();
    }
    
    public void cleanUp() {
        this.window = null;
        this.helpWindow = null;
        this.explainationWindow = null;
        this.animWindow = null;
        this.logger = null;
        this.algorithm.cleanUp();
        this.algorithm = null;
        if (this.breakpoint != null) {
            this.breakpoint.cleanUp();
        }
        this.breakpoint = null;
    }
    
    public void destroy() {
        System.out.println("Destroyed Called");
        if (this.animWindow != null) {
            this.animWindow.exit();
        }
        else {
            System.err.println("Warning: animWindow null in AlgorithmApplet.destroy()");
        }
    }
    
    public void displayWindowLocations() {
        System.out.println("Animation Window: " + this.animWindow.getLocation().x + "," + this.animWindow.getLocation().y + " size: " + this.animWindow.getSize().width + " " + this.animWindow.getSize().height);
        System.out.println("Explanation Window: " + this.explainationWindow.getLocation().x + "," + this.explainationWindow.getLocation().y + " size: " + this.explainationWindow.getSize().width + " " + this.explainationWindow.getSize().height);
        System.out.println("Help Window: " + this.helpWindow.getLocation().x + "," + this.helpWindow.getLocation().y + " size: " + this.helpWindow.getSize().width + " " + this.helpWindow.getSize().height);
        System.out.println("Algorithm Window: " + this.window.getLocation().x + "," + this.window.getLocation().y + " size: " + this.window.getSize().width + " " + this.window.getSize().height);
    }
    
    public void displayWindows() {
        if (this.doWindow) {
            this.explainationWindow.setVisible(true);
            this.window.setVisible(true);
        }
        else {
            this.window.setVisible(true);
            this.window.openAll();
            this.window.setSize(10, 10);
            this.window.toBack();
            this.window.setResizable(false);
        }
        this.helpWindow.setVisible(true);
        this.animWindow.setVisible(true);
    }
    
    public abstract AlgorithmCanvas getAlgorithmCanvas();
    
    public abstract AlgorithmThread getAlgorithmThread(final Copyable p0, final AlgorithmWindow p1);
    
    protected AlgorithmWindow getAlgorithmWindow(final String algorithmWindowName, final CodeCanvas codeCanvas) {
        return new AlgorithmWindow(algorithmWindowName, codeCanvas);
    }
    
    public abstract AnimationWindow getAnimationWindow(final AlgorithmCanvas p0, final AlgorithmThread p1, final Applet p2);
    
    public final String getAppletInfo() {
        return this.appletInfo;
    }
    
    public BreakPoint getBreakPoint() {
        return this.breakpoint;
    }
    
    public abstract Copyable getInitialData();
    
    public Logger getLogger() {
        return this.logger;
    }
    
    public final void init() {
        ConfigurationManager.reset();
        final String tempStr1 = this.getParameter("JARFILE");
        if (tempStr1 != null) {
            this.jarFileName = tempStr1;
        }
        final String tempStr2 = this.getParameter("NOLOG");
        final boolean doLog = tempStr2 == null || !tempStr2.equals("true");
        final String tempStr3 = this.getParameter("NOEXP");
        if (tempStr3 != null && tempStr3.equals("true")) {
            this.doWindow = false;
        }
        else {
            this.doWindow = true;
        }
        final String tempStr4 = this.getParameter("NOSTEP");
        if (tempStr4 != null && tempStr4.equals("true")) {
            StepButton.allDisable(true);
        }
        final String tempStr5 = this.getParameter("NOBACK");
        if (tempStr5 != null && tempStr5.equals("true")) {
            BackButton.allDisable(true);
        }
        final String tempStr6 = this.getParameter("NOTRACE");
        if (tempStr6 != null && tempStr6.equals("true")) {
            if (Logger.DEBUG) {
                System.err.println("Turning tracing off.");
            }
            this.logger.setDumpTrace(false);
        }
        final String tempStr65 = this.getParameter("OUTPUTMODE");
        if (tempStr65 != null) {
            this.logger.setOutputMode(tempStr65);
        }
        final String tempStr7 = this.getParameter("CGISCRIPT");
        if (tempStr7 != null) {
            this.logger.setCGIScript(tempStr7);
        }
        final String tempStr71 = this.getParameter("LOGDIR");
        if (tempStr71 != null) {
            this.logger.setLogDir(tempStr71);
        }
        final String tempStr72 = this.getParameter("LOGADDINFO");
        if (tempStr72 != null) {
            Logger.setLogAddInfo(Boolean.getBoolean(tempStr72));
        }
        final String tempStr8 = this.getParameter("BUFFERSIZE");
        if (tempStr8 != null) {
            int buffersize = -1;
            try {
                buffersize = Integer.parseInt(tempStr8);
            }
            catch (NumberFormatException e) {
                System.out.println("BUFFERSIZE parameter wrong format");
                buffersize = -1;
            }
            if (buffersize != -1) {
                this.logger.setBufferSize(buffersize);
            }
        }
        final String tempStr9 = this.getParameter("EXTRA");
        this.parseParameter(tempStr9);
        final String tempStr10 = this.getParameter("COOKIES");
        final boolean requireCookies = tempStr10 != null && tempStr10.equals("true");
        String userName = this.getParameter("CUSER");
        final String tempStr11 = this.getParameter("NOBREAKPOINT");
        if (tempStr11 != null && tempStr11.equals("true")) {
            this.breakpoint.enabled = false;
        }
        else {
            this.breakpoint.enabled = true;
        }
        final String tempStr12 = this.getParameter("ULOG");
        final boolean uLog = tempStr12 != null && tempStr12.equals("true");
        final String userPW = this.getParameter("CID");
        if (userName == null) {
            if (requireCookies) {
                System.out.println("AiA ERROR: Cookies must be enabled for this module to operate");
                System.out.println("           Please enable cookies and go to the login screen again");
                return;
            }
            System.out.println("AiA WARNING: Cookies are not enabled, cannot determine user");
            System.out.println("             Assuming user=aia");
            userName = "aia";
        }
        final Frame frame = new Frame();
        final ProgressDialog progressDialog = new ProgressDialog(frame, Messages.getString("AlgorithmApplet.43"), Messages.getString("AlgorithmApplet.44"), false, 10, 1);
        progressDialog.setSize(300, 130);
        final Dimension screen_size = frame.getToolkit().getScreenSize();
        progressDialog.setLocation((screen_size.width - progressDialog.getSize().width) / 2, (screen_size.height - progressDialog.getSize().height) / 2);
        int upToMarker = 1;
        progressDialog.setVisible(true);
        this.helpWindow = new HelpWindow(this.helpWindowTitle);
        this.helpWindow.setSize(300, 150);
        progressDialog.setProgressMark(upToMarker++);
        URL newURL = this.getDocumentBase();
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
        final CodeCanvas codeCanvas = new CodeCanvas(this, this.getCodeBase().toString(), this.algorithmFileName, newURL.toString() + this.imageDirectory);
        progressDialog.setProgressMark(upToMarker++);
        this.window = this.getAlgorithmWindow(this.algorithmWindowName, codeCanvas);
        this.window.setLogger(this.logger);
        this.window.setBreakPoint(this.breakpoint);
        this.window.addHelpListener(this.helpWindow);
        this.window.setSize(400, 600);
        progressDialog.setProgressMark(upToMarker++);
        final AlgorithmThread thread = this.getAlgorithmThread(this.getInitialData(), this.window);
        thread.setLogger(this.logger);
        thread.setBreakPoint(this.breakpoint);
        this.algorithm = thread.getAlgorithm();
        progressDialog.setProgressMark(upToMarker++);
        final AlgorithmCanvas canvas = this.getAlgorithmCanvas();
        progressDialog.setProgressMark(upToMarker++);
        thread.addQuestionable(canvas);
        this.explainationWindow = new ExplainationWindow(this.explanationWindowName, this.explanationTitle, this.explanationCodeTitle, this);
        progressDialog.setProgressMark(upToMarker++);
        this.explainationWindow.initialiseMainData(this.getCodeBase().toString(), this.explanationFileName);
        this.explainationWindow.addHelpListener(this.helpWindow);
        progressDialog.setProgressMark(upToMarker++);
        this.animWindow = this.getAnimationWindow(canvas, thread, this);
        this.logger.setEnabled(doLog);
        final String lang = Messages.getActiveLocale().getLanguage();
        final String userNameAndLang = userName + "@" + lang;
        if (uLog || userName.startsWith("[regstud]")) {
            this.logger.beginLogging(this.animWindow.getAlgorithmName(), userNameAndLang, userPW);
        }
        else {
            this.logger.beginGuestLogging(userNameAndLang, this.animWindow.getAlgorithmName());
        }
        this.breakpoint.initialise(thread, this.window, this.animWindow);
        this.animWindow.addHelpListener(this.helpWindow);
        this.animWindow.addModeListener(this.explainationWindow);
        this.animWindow.addModeListener(thread);
        if (this.window instanceof ControlListener) {
            this.animWindow.addControlListener((ControlListener)((ControlListener)this.window));
        }
        Question.setAnimationWindow(this.animWindow);
        canvas.setParent(this.animWindow.getContainer());
        this.window.addKeyListener(this.animWindow);
        codeCanvas.addKeyListener(this.animWindow);
        this.window.addWindowListener(this.animWindow);
        this.animWindow.setSize(600, 600);
        this.animWindow.init();
        progressDialog.setProgressMark(upToMarker++);
        this.appletInfo = this.animWindow.getBuildInfo();
        thread.addFinishListener(this.animWindow);
        this.window.addExplainationListener(this.explainationWindow);
        this.explainationWindow.setSize(250, 600);
        thread.addRepaintListener(canvas);
        this.animWindow.addExitListener(this);
        this.animWindow.addExitListener(this.helpWindow);
        this.animWindow.addExitListener(this.explainationWindow);
        this.animWindow.addExitListener(this.window);
        this.animWindow.addExitListener(this.animWindow);
        this.animWindow.addExitListener(thread);
        progressDialog.setProgressMark(upToMarker++);
        this.otherInitialisation();
        this.initialiseConfiguration();
        thread.start();
        this.animWindow.setCurrentData(this.getInitialData());
        progressDialog.setProgressMark(upToMarker++);
        this.setLocationAndSize();
        progressDialog.setVisible(false);
        this.displayWindows();
        progressDialog.dispose();
        this.animWindow.requestFocus();
    }
    
    protected void initialiseConfiguration() {
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection backgroundSelection = new ColorSelection(Color.white, ColorSelection.BACKGROUND);
        backgroundSelection.appliesToAll();
        cm.addColorSelection(backgroundSelection);
        final ColorSelection textColorSelection = new ColorSelection(Color.black, ColorSelection.TEXT_COLOR);
        textColorSelection.appliesToAll();
        cm.addColorSelection(textColorSelection);
        final FontSelection textFontSelection = new FontSelection(new Font(Messages.getString("AlgorithmApplet.1"), 0, 12), "Normal Font");
        textFontSelection.appliesToAll();
        cm.addFontSelection(textFontSelection);
    }
    
    protected void otherInitialisation() {
    }
    
    public void parseParameter(final String parameter) {
    }
    
    public void processExitEvent(final ExitEvent e) {
        if (this.logger != null) {
            this.logger.endLogging();
        }
        this.setVisible(false);
        this.stop();
        this.removeAll();
        this.cleanUp();
    }
    
    protected abstract void setLocationAndSize();
}
