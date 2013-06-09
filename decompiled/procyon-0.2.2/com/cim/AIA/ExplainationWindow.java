package com.cim.AIA;

import java.util.*;
import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;

public class ExplainationWindow extends Frame implements Helpable, ExplainationListener, ExitListener, ModeListener, ColorSelectionListener, FontSelectionListener
{
    private static final long serialVersionUID = -1482890918509768301L;
    protected static final String LOGO_IMAGE_NAME = "logo.gif";
    protected static final String TITLE_IMAGE_NAME = "title.gif";
    protected static String JAR_FILE_NAME;
    protected static String imgDir;
    protected TextArea basicInfoTextArea;
    protected TextArea infoTextArea;
    protected ScrollPane sp;
    protected Panel mainPanel;
    protected Label label1;
    protected Label label2;
    protected Applet applet;
    protected Image logoImage;
    protected Image titleImage;
    protected ImageCanvas logoCanvas;
    protected ImageCanvas titleCanvas;
    
    public ExplainationWindow(final String frameTitle, final String mainTitle, final String mainSubTitle) {
        super(frameTitle);
        this.logoImage = null;
        this.titleImage = null;
        this.setBackground(Color.white);
        this.sp = new ScrollPane(0);
        this.add(this.sp);
        final Panel panel = new Panel();
        this.sp.add(panel);
        final GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        final GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;
        c.anchor = 10;
        c.fill = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        final Font font = new Font("Courier", 1, 12);
        this.label1 = new Label(mainTitle, 0);
        this.label1.setFont(font);
        gbl.setConstraints(this.label1, c);
        panel.add(this.label1);
        c.weightx = 4.0;
        c.weighty = 4.0;
        this.basicInfoTextArea = new TextArea("", 15, 30, 1);
        this.infoTextArea = new TextArea("", 5, 30, 1);
        this.basicInfoTextArea.setEditable(false);
        this.infoTextArea.setEditable(false);
        gbl.setConstraints(this.basicInfoTextArea, c);
        panel.add(this.basicInfoTextArea);
        c.weightx = 0.0;
        c.weighty = 0.0;
        this.label2 = new Label(mainSubTitle, 0);
        this.label2.setFont(font);
        gbl.setConstraints(this.label2, c);
        panel.add(this.label2);
        c.weightx = 3.0;
        c.weighty = 3.0;
        gbl.setConstraints(this.infoTextArea, c);
        panel.add(this.infoTextArea);
        ConfigurationManager.getConfigurationManager().addColorSelectionListener(this);
        ConfigurationManager.getConfigurationManager().addFontSelectionListener(this);
    }
    
    public ExplainationWindow(final String frameTitle, final String mainTitle, final String mainSubTitle, final Applet applet) {
        super(frameTitle);
        this.logoImage = null;
        this.titleImage = null;
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
        catch (Exception ex2) {}
        Label_0402:
        Label_0649:
        {
             {
                InputStream i1;
                URL url;
                URL otherUrl;
                try {
                    ExplainationWindow.imgDir = ((AlgorithmApplet)applet).imageDirectory;
                    ExplainationWindow.imgDir = newURL.toString() + ExplainationWindow.imgDir;
                    i1 = applet.getClass().getResourceAsStream("images/logo.gif");
                    url = null;
                    otherUrl = new URL(applet.getCodeBase() + "images/" + "logo.gif");
                    this.logoImage = StreamImage.getImage(i1);
                    if (this.logoImage == null) {
                        System.out.println("Logo Image is loading from local..." + otherUrl.toString());
                        this.logoImage = applet.getAppletContext().getImage(otherUrl);
                    }
                    if (!new logoProcessor(otherUrl).checkFile(applet.getClass().getResourceAsStream("images/logo.gif"))) {
                        try {
                            url = new URL("http://ww2.cs.mu.oz.au/muaia/images/logo.gif");
                            this.logoImage = applet.getAppletContext().getImage(url);
                        }
                        catch (SecurityException ex3) {}
                    }
                    if (this.logoImage != null) {
                        tracker.addImage(this.logoImage, 0);
                        break Label_0402;
                    }
                    applet.showStatus("com.cim.AIA.ExplainationWindow: Unable to load image: " + url);
                    System.out.println("com.cim.AIA.ExplainationWindow: Unable to load image: " + url);
                }
                catch (MalformedURLException) {
                    final MalformedURLException ex;
                    final MalformedURLException e = ex;
                    applet.showStatus("com.cim.AIA.ExplainationWindow: Invalid URL: " + e);
                    System.out.println("com.cim.AIA.ExplainationWindow: Invalid URL: " + e);
                }
                return;
                try {
                    i1 = applet.getClass().getResourceAsStream("images/title.gif");
                    otherUrl = new URL(applet.getCodeBase() + "images/" + "title.gif");
                    this.titleImage = StreamImage.getImage(i1);
                    if (this.titleImage == null) {
                        System.out.println("TitleImage is loading from local..." + otherUrl.toString());
                        this.titleImage = applet.getAppletContext().getImage(otherUrl);
                    }
                    i1 = applet.getClass().getResourceAsStream("images/title.gif");
                    if (!new titleProcessor(url).checkFile(i1)) {
                        try {
                            url = new URL("http://ww2.cs.mu.oz.au/muaia/images/title.gif");
                            this.titleImage = applet.getAppletContext().getImage(url);
                        }
                        catch (SecurityException ex4) {}
                    }
                    if (this.titleImage != null) {
                        tracker.addImage(this.titleImage, 0);
                        break Label_0649;
                    }
                    applet.showStatus("com.cim.AIA.ExplainationWindow: Unable to load image: " + url);
                    System.out.println("com.cim.AIA.ExplainationWindow: Unable to load image: " + url);
                }
                catch (MalformedURLException) {}
            }
            return;
        }
        try {
            tracker.waitForAll();
        }
        catch (InterruptedException ex5) {}
        this.logoCanvas = new ImageCanvas(this.logoImage, null);
        this.titleCanvas = new ImageCanvas(this.titleImage, null);
        this.setBackground(Color.white);
        this.mainPanel = new Panel();
        final Panel logoPanel = new Panel();
        final GridBagLayout gbLayout2 = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        logoPanel.setLayout(gbLayout2);
        logoPanel.setBackground(Color.gray.brighter());
        this.buildConstraints(constraints, 0, 0, 1, 1, 20, 100);
        constraints.insets = new Insets(0, 5, 5, 0);
        constraints.anchor = 17;
        constraints.fill = 0;
        gbLayout2.setConstraints(this.logoCanvas, constraints);
        logoPanel.add(this.logoCanvas);
        this.buildConstraints(constraints, 1, 0, 1, 1, 80, 100);
        constraints.insets = new Insets(0, 2, 0, 0);
        gbLayout2.setConstraints(this.titleCanvas, constraints);
        logoPanel.add(this.titleCanvas);
        final GridBagLayout gbl = new GridBagLayout();
        this.mainPanel.setLayout(gbl);
        final GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;
        c.anchor = 10;
        c.fill = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        final Font font = new Font("Courier", 1, 12);
        this.label1 = new Label(mainTitle, 0);
        this.label1.setFont(font);
        gbl.setConstraints(logoPanel, c);
        this.mainPanel.add(logoPanel);
        gbl.setConstraints(this.label1, c);
        this.mainPanel.add(this.label1);
        c.weightx = 4.0;
        c.weighty = 4.0;
        this.basicInfoTextArea = new TextArea("", 15, 30, 1);
        this.infoTextArea = new TextArea("", 5, 30, 1);
        this.basicInfoTextArea.setEditable(false);
        this.infoTextArea.setEditable(false);
        gbl.setConstraints(this.basicInfoTextArea, c);
        this.mainPanel.add(this.basicInfoTextArea);
        c.weightx = 0.0;
        c.weighty = 0.0;
        this.label2 = new Label(mainSubTitle, 0);
        this.label2.setFont(font);
        gbl.setConstraints(this.label2, c);
        this.mainPanel.add(this.label2);
        c.weightx = 3.0;
        c.weighty = 3.0;
        gbl.setConstraints(this.infoTextArea, c);
        this.mainPanel.add(this.infoTextArea);
        this.add(this.mainPanel);
        ConfigurationManager.getConfigurationManager().addColorSelectionListener(this);
        ConfigurationManager.getConfigurationManager().addFontSelectionListener(this);
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.logoCanvas.addHelpListener(helpListener);
        this.titleCanvas.addHelpListener(helpListener);
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
        this.basicInfoTextArea = null;
        this.infoTextArea = null;
        if (this.sp != null) {
            this.sp.removeAll();
        }
        this.sp = null;
        if (this.mainPanel != null) {
            this.mainPanel.removeAll();
        }
        this.mainPanel = null;
        this.label1 = null;
        this.label2 = null;
        this.applet = null;
        if (this.logoImage != null) {
            this.logoImage.flush();
        }
        this.logoImage = null;
        if (this.titleImage != null) {
            this.titleImage.flush();
        }
        this.titleImage = null;
        if (this.logoCanvas != null) {
            this.logoCanvas.cleanUp();
        }
        this.logoCanvas = null;
        if (this.titleCanvas != null) {
            this.titleCanvas.cleanUp();
        }
        this.titleCanvas = null;
    }
    
    public String getClassName() {
        return "ExplainationWindow";
    }
    
    public void initialiseMainData(final String dataPath, final String fileName) {
        URL url = null;
        try {
            url = new URL(dataPath + fileName);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: ExplainationWindow: " + e);
            return;
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF8"));
        }
        catch (FileNotFoundException e2) {
            System.out.println("FileNotFound: ExplainationWindow: " + e2);
            return;
        }
        catch (IOException e2) {
            System.out.println("com.cim.AIA.ExplainationWindow: IOEXCEPTION" + e2);
            return;
        }
        while (true) {
            this.setMainText("");
            Label_0155:
            {
                Label_0170:
                {
                    final String line;
                    try {
                        line = in.readLine();
                        if (line != null) {
                            break Label_0170;
                        }
                    }
                    catch (IOException) {
                        final IOException ex;
                        final IOException e3 = ex;
                        System.out.println("com.cim.AIA.ExplainationWindow: IOEXCEPTION" + e3);
                        return;
                    }
                    break;
                    try {
                        this.basicInfoTextArea.append(line + "\n");
                        break Label_0155;
                    }
                    catch (IOException) {}
                }
            }
            break;
        }
    }
    
    public void modeNormal(final ModeEvent e) {
        this.setVisible(true);
    }
    
    public void modeOther(final ModeEvent e) {
        this.setVisible(true);
    }
    
    public void modeQuiz(final ModeEvent e) {
        this.setVisible(true);
    }
    
    public void modeSelfTest(final ModeEvent e) {
        this.setVisible(false);
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.basicInfoTextArea.setBackground(colorSelection.getColor());
            this.infoTextArea.setBackground(colorSelection.getColor());
            this.label1.setBackground(colorSelection.getColor());
            this.label2.setBackground(colorSelection.getColor());
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.basicInfoTextArea.setForeground(colorSelection.getColor());
            this.infoTextArea.setForeground(colorSelection.getColor());
            this.label1.setForeground(colorSelection.getColor());
            this.label2.setForeground(colorSelection.getColor());
        }
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
        this.cleanUp();
    }
    
    public void processExplainationEvent(final ExplainationEvent e) {
        this.setSubInfoText(e.getTitle(), e.getExplaination());
    }
    
    public void processFontSelection(final FontSelection fontSelection) {
        final String atribute = fontSelection.getAtributeName();
        if (atribute.equalsIgnoreCase("Normal Font")) {
            this.infoTextArea.setFont(fontSelection.getFont());
            this.basicInfoTextArea.setFont(fontSelection.getFont());
            final Font temp = fontSelection.getFont();
            this.label1.setFont(new Font(temp.getName(), 1, temp.getSize()));
            this.label2.setFont(new Font(temp.getName(), 1, temp.getSize()));
        }
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.logoCanvas.removeHelpListener(helpListener);
        this.titleCanvas.removeHelpListener(helpListener);
    }
    
    public void setMainText(final String string) {
        this.basicInfoTextArea.setText(string);
    }
    
    public void setMainTitle(final String title) {
        title.replace('\t', ' ');
        String tempString = " ";
        final StringTokenizer st = new StringTokenizer(title, " ");
        while (st.hasMoreTokens()) {
            tempString = tempString + st.nextToken() + " ";
        }
        this.label1.setText(tempString);
    }
    
    public void setSubInfoText(final String title, final String string) {
        title.replace('\t', ' ');
        String tempString = " ";
        final StringTokenizer st = new StringTokenizer(title, " ");
        while (st.hasMoreTokens()) {
            tempString = tempString + st.nextToken() + " ";
        }
        if (this.label2.getText().compareTo(tempString) != 0) {
            this.label2.setText(tempString);
        }
        if (this.infoTextArea.getText().compareTo(string) != 0) {
            this.infoTextArea.setText(string);
        }
    }
    
    static {
        ExplainationWindow.JAR_FILE_NAME = "aia.jar";
    }
}
