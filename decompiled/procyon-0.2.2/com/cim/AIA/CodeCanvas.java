package com.cim.AIA;

import localization.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import com.cim.common.*;

public class CodeCanvas extends BasicCanvas implements MouseListener, MouseMotionListener, Explainable, ExplainationListener, Helpable, HelpListener, ColorSelectionListener, FontSelectionListener
{
    private static final long serialVersionUID = -977061214392447714L;
    protected static String minmaxLabel;
    protected static LadderTree minmaxParent;
    protected static boolean inMinMax;
    protected static boolean isLabelMinMax;
    protected static boolean isFirstMinMax;
    protected static boolean isLastMinMax;
    protected static AlgorithmLine firstInstance;
    protected static AlgorithmLine labelInstance;
    protected static AlgorithmLine lastInstance;
    protected static final String START_MINMAX = "%[";
    protected static final String END_MINMAX = "]%";
    public static final String OPEN_FOLDER_HELP_TITLE;
    public static final Color DEFAULT_HIGHLIGHT_COLOR;
    public static final String OPEN_FOLDER_INSTRUCTIONS;
    public static final String CLOSE_FOLDER_HELP_TITLE;
    public static final String CLOSE_FOLDER_INSTRUCTIONS;
    public static final String LINE_HELP_TITLE;
    public static final String LINE_INSTRUCTIONS;
    protected static final String START_SEPARATOR = "$";
    protected static final String STOP_SEPARATOR = "@";
    protected static final String ALWAYS_OPEN_MARKER = "~";
    protected static final String EXPAND_AS_OWN_MARKER = "`";
    protected static final String OPEN_FOLDER_IMAGE_NAME = "open.gif";
    protected static final String CLOSED_FOLDER_IMAGE_NAME = "folder.gif";
    protected static final String LOGO_IMAGE_NAME = "logo.gif";
    protected static String JAR_FILE_NAME;
    protected static final int POSITION_HIGHLIGHT_LEVEL = 1;
    protected static final int EXPLAIN_HIGHLIGHT_LEVEL = 2;
    protected static final int BREAKPOINT_HIGHLIGHT_LEVEL = 3;
    protected static String CODE_POSITION_MARKER;
    protected static String EXPLANATION_POSITION_MARKER;
    protected static String COMMENT_LINE_COLOR;
    protected static String BREAKPOINT_COLOR;
    protected final int MINMAX_XGAP = 2;
    protected final int MINMAX_LINELENGTH = 5;
    protected Color commentColor;
    protected Color codeColor;
    protected Color backgroundColor;
    protected Color highlightColor;
    protected Color explainColor;
    protected Color breakPointColor;
    protected Vector<ExplainationListener> explainationListeners;
    protected Vector<HelpListener> helpListeners;
    protected LadderTree ladderTree;
    protected String fileName;
    protected String dataPath;
    protected String expandedImageName;
    protected String collapsedImageName;
    public Image logoImage;
    protected Image expandedImage;
    protected Image collapsedImage;
    public Dimension logoImageSize;
    protected Dimension expandedImageSize;
    protected Dimension collapsedImageSize;
    protected String normalFontName;
    protected int normalFontSize;
    protected Font normalFont;
    BreakPoint breakpoint;
    protected final int imageBuffer = 5;
    protected final int ySpacing = 2;
    protected Container parent;
    protected String currentPosition;
    
    public static LadderTree getLadderTreeFromFile(final String dataPath, final String fileName, final Logger logger, final BreakPoint brkpnt) {
        LadderTree ladderTree = null;
        LadderTree currentParent = null;
        URL url = null;
        try {
            url = new URL(dataPath + fileName);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: CodeCanvas: " + e);
            return null;
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
        }
        catch (FileNotFoundException e2) {
            System.out.println("com.cim.AIA.CodeCanvas: FileNotFound: " + e2);
            return null;
        }
        catch (IOException e2) {
            System.out.println("com.cim.AUA.CodeCanvas: IO EXCEPTION: " + e2);
            return null;
        }
        while (true) {
            boolean newNode = false;
            boolean endNode = false;
            boolean alwaysExpanded = false;
            boolean expandAsOwn = false;
            Label_0171:
            {
                Label_0186:
                Label_0208:
                {
                    String line;
                    final IOException ex;
                    final IOException e3;
                    {
                        try {
                            line = in.readLine();
                            if (line != null) {
                                break Label_0186;
                            }
                        }
                        catch (IOException) {
                            e3 = ex;
                            System.out.println("com.cim.AIA.CodeCanavs: IOException: " + e3);
                            return null;
                        }
                        break;
                        try {
                            line = replaceString(line, "\t", "");
                            if (line.length() != 0) {
                                break Label_0208;
                            }
                        }
                        catch (IOException) {}
                    }
                    break Label_0171;
                    try {
                        if (line.indexOf("%[") != -1) {
                            line = replaceString(line, "%[", "");
                            CodeCanvas.minmaxParent = currentParent;
                            CodeCanvas.inMinMax = true;
                            CodeCanvas.isLabelMinMax = true;
                            CodeCanvas.isFirstMinMax = false;
                            CodeCanvas.isLastMinMax = false;
                        }
                        if (line.indexOf("]%") != -1) {
                            line = replaceString(line, "]%", "");
                            CodeCanvas.isLastMinMax = true;
                        }
                        if (line.indexOf("~") != -1) {
                            alwaysExpanded = true;
                            line = replaceString(line, "~", "");
                        }
                        if (line.indexOf("`") != -1) {
                            expandAsOwn = true;
                            line = replaceString(line, "`", "");
                        }
                        if (line.indexOf("@") != -1) {
                            line = replaceString(line, "@", "");
                            endNode = true;
                        }
                        if (line.indexOf("$") != -1) {
                            line = replaceString(line, "$", "");
                            newNode = true;
                        }
                        if (endNode) {
                            endNode = false;
                            if ((LadderTree)currentParent.getParent() != null) {
                                currentParent = (LadderTree)currentParent.getParent();
                            }
                        }
                        final StringTokenizer st = new StringTokenizer(line, " ");
                        line = "";
                        while (st.hasMoreTokens()) {
                            line = line + st.nextToken() + " ";
                        }
                        if (line.length() != 0) {
                            final AlgorithmLine algLine = new AlgorithmLine(line);
                            if (CodeCanvas.inMinMax) {
                                if (CodeCanvas.isLabelMinMax) {
                                    algLine.setIsLabelMinMax(true);
                                    CodeCanvas.minmaxLabel = algLine.getLabel();
                                    CodeCanvas.labelInstance = algLine;
                                    CodeCanvas.isLabelMinMax = false;
                                    CodeCanvas.isFirstMinMax = true;
                                }
                                else if (CodeCanvas.isFirstMinMax) {
                                    algLine.setIsFirstMinMax(true);
                                    CodeCanvas.firstInstance = algLine;
                                    CodeCanvas.isFirstMinMax = false;
                                }
                                if (currentParent == CodeCanvas.minmaxParent) {
                                    CodeCanvas.lastInstance = algLine;
                                }
                                algLine.setIsMinMax(true);
                                algLine.setMinMaxLabel(CodeCanvas.minmaxLabel);
                                algLine.setFirstInstance(CodeCanvas.firstInstance);
                                algLine.setLabelInstance(CodeCanvas.labelInstance);
                                if (CodeCanvas.isLastMinMax) {
                                    if (currentParent == CodeCanvas.minmaxParent) {
                                        algLine.setIsLastMinMax(true);
                                    }
                                    else {
                                        CodeCanvas.lastInstance.setIsLastMinMax(true);
                                    }
                                    CodeCanvas.isLastMinMax = false;
                                    CodeCanvas.inMinMax = false;
                                }
                            }
                            final LadderTree node = new LadderTree(currentParent, algLine, logger, brkpnt);
                            if (newNode) {
                                if (currentParent == null) {
                                    ladderTree = node;
                                }
                                currentParent = node;
                                newNode = false;
                            }
                            if (alwaysExpanded) {
                                node.setAlwaysExpanded(true);
                                alwaysExpanded = false;
                            }
                            if (expandAsOwn) {
                                node.setExpandAsOwn(true);
                                expandAsOwn = false;
                            }
                        }
                        break Label_0171;
                    }
                    catch (IOException) {}
                }
            }
            break;
        }
        return ladderTree;
    }
    
    protected static String replaceString(String source, final String target, final String newString) {
        final int loc = source.indexOf(target);
        if (loc == -1) {
            return source;
        }
        final String head = source.substring(0, loc);
        final String tail = source.substring(loc + target.length());
        source = head + newString + tail;
        return replaceString(source, target, newString);
    }
    
    public CodeCanvas(final Applet applet, final String dataPath, final String fileName, final String imgDir) {
        this(applet, dataPath, fileName, imgDir, "open.gif", "folder.gif");
    }
    
    public CodeCanvas(final Applet applet, final String dataPath, final String fileName, final String imgDir, final String expandedImageName, final String collapsedImageName) {
        super();
        this.MINMAX_XGAP = 2;
        this.MINMAX_LINELENGTH = 5;
        this.commentColor = Color.blue;
        this.codeColor = Color.black;
        this.backgroundColor = Color.white;
        this.highlightColor = CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        this.explainColor = Color.gray;
        this.breakPointColor = Color.red;
        this.explainationListeners = new Vector();
        this.helpListeners = new Vector();
        this.logoImage = null;
        this.expandedImage = null;
        this.collapsedImage = null;
        this.normalFontName = "Courier";
        this.normalFontSize = 12;
        this.normalFont = new Font(this.normalFontName, 0, this.normalFontSize);
        this.imageBuffer = 5;
        this.ySpacing = 2;
        this.currentPosition = "";
        this.dataPath = dataPath;
        this.fileName = fileName;
        this.expandedImageName = expandedImageName;
        this.collapsedImageName = collapsedImageName;
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, CodeCanvas.CODE_POSITION_MARKER);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection explainationSelection = new ColorSelection(this.explainColor, CodeCanvas.EXPLANATION_POSITION_MARKER);
        explainationSelection.addClass(this);
        cm.addColorSelection(explainationSelection);
        final ColorSelection breakpointSelection = new ColorSelection(this.breakPointColor, CodeCanvas.BREAKPOINT_COLOR);
        explainationSelection.addClass(this);
        cm.addColorSelection(breakpointSelection);
        final ColorSelection commentSelection = new ColorSelection(this.commentColor, CodeCanvas.COMMENT_LINE_COLOR);
        commentSelection.addClass(this);
        cm.addColorSelection(commentSelection);
        cm.addColorSelectionListener(this);
        cm.addFontSelectionListener(this);
        final MediaTracker tracker = new MediaTracker(this);
        Label_0502:
        Label_0698:
        Label_0939:
        {
             {
                InputStream i1;
                URL otherUrl;
                final MalformedURLException ex;
                final MalformedURLException e;
                {
                    try {
                        i1 = applet.getClass().getResourceAsStream("images/" + expandedImageName);
                        otherUrl = new URL(applet.getCodeBase() + "images/" + expandedImageName);
                        this.expandedImage = StreamImage.getImage(i1);
                        if (this.expandedImage == null) {
                            System.out.println("Expanded Image is loading from local..." + otherUrl.toString());
                            this.expandedImage = applet.getAppletContext().getImage(otherUrl);
                        }
                        if (this.expandedImage != null) {
                            tracker.addImage(this.expandedImage, 0);
                            break Label_0502;
                        }
                        applet.showStatus("com.cim.AIA.CodeCanvas: Unable to load image: " + otherUrl);
                        System.out.println("com.cim.AIA.CodeCanvas:  Unable to load image: " + otherUrl);
                    }
                    catch (MalformedURLException) {
                        e = ex;
                        applet.showStatus("com.cim.AIA.CodeCanvas: Invalid URL: " + e);
                        System.out.println("com.cim.AIA.CodeCanavas: Invalid URL: " + e);
                    }
                    return;
                    try {
                        i1 = applet.getClass().getResourceAsStream("images/" + collapsedImageName);
                        otherUrl = new URL(applet.getCodeBase() + "images/" + collapsedImageName);
                        this.collapsedImage = StreamImage.getImage(i1);
                        if (this.collapsedImage == null) {
                            System.out.println("Collapsed Image is loading from local..." + otherUrl.toString());
                            this.collapsedImage = applet.getAppletContext().getImage(otherUrl);
                        }
                        if (this.collapsedImage != null) {
                            tracker.addImage(this.collapsedImage, 0);
                            break Label_0698;
                        }
                        applet.showStatus("com.cim.AIA.CodeCanvas: Unable to load image: " + otherUrl);
                        System.out.println("com.cim.AIA.CodeCanvas: Unable to load image: " + otherUrl);
                    }
                    catch (MalformedURLException) {}
                }
                return;
                try {
                    i1 = applet.getClass().getResourceAsStream("images/logo.gif");
                    otherUrl = new URL(applet.getCodeBase() + "images/" + "logo.gif");
                    this.logoImage = StreamImage.getImage(i1);
                    if (this.logoImage == null) {
                        System.out.println("Logo Image is loading from local..." + otherUrl.toString());
                        this.logoImage = applet.getAppletContext().getImage(otherUrl);
                    }
                    i1 = applet.getClass().getResourceAsStream("images/logo.gif");
                    final logoProcessor tp1 = new logoProcessor(otherUrl);
                    try {
                        if (!tp1.checkFile(i1)) {
                            final URL url = new URL("http://ww2.cs.mu.oz.au/muaia/images/logo.gif");
                            this.logoImage = applet.getAppletContext().getImage(url);
                        }
                    }
                    catch (SecurityException ex2) {}
                    if (this.logoImage != null) {
                        tracker.addImage(this.logoImage, 0);
                        break Label_0939;
                    }
                    applet.showStatus("com.cim.AIA.CodeCanvas: Unable to load image: " + otherUrl);
                    System.out.println("com.cim.AIA.CodeCanvas: Unable to load image: " + otherUrl);
                }
                catch (MalformedURLException) {}
            }
            return;
        }
        try {
            tracker.waitForAll();
        }
        catch (InterruptedException ex3) {}
        this.expandedImageSize = new Dimension(this.expandedImage.getWidth(this), this.expandedImage.getHeight(this));
        this.collapsedImageSize = new Dimension(this.collapsedImage.getWidth(this), this.collapsedImage.getHeight(this));
        this.logoImageSize = new Dimension(this.logoImage.getWidth(this), this.logoImage.getHeight(this));
        Logger logger = null;
        if (AlgorithmApplet.class.isAssignableFrom(applet.getClass())) {
            logger = ((AlgorithmApplet)applet).getLogger();
            this.breakpoint = ((AlgorithmApplet)applet).getBreakPoint();
        }
        else {
            logger = null;
            this.breakpoint = new BreakPoint();
        }
        this.initialiseTree(dataPath, fileName, logger, this.breakpoint);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    protected CodeCanvas(final Image openedImage, final Image closedImage, final Logger logger, final BreakPoint brkpnt) {
        super();
        this.MINMAX_XGAP = 2;
        this.MINMAX_LINELENGTH = 5;
        this.commentColor = Color.blue;
        this.codeColor = Color.black;
        this.backgroundColor = Color.white;
        this.highlightColor = CodeCanvas.DEFAULT_HIGHLIGHT_COLOR;
        this.explainColor = Color.gray;
        this.breakPointColor = Color.red;
        this.explainationListeners = new Vector();
        this.helpListeners = new Vector();
        this.logoImage = null;
        this.expandedImage = null;
        this.collapsedImage = null;
        this.normalFontName = "Courier";
        this.normalFontSize = 12;
        this.normalFont = new Font(this.normalFontName, 0, this.normalFontSize);
        this.imageBuffer = 5;
        this.ySpacing = 2;
        this.currentPosition = "";
        this.expandedImage = openedImage;
        this.collapsedImage = closedImage;
        this.breakpoint = brkpnt;
        this.expandedImageSize = new Dimension(this.expandedImage.getWidth(this), this.expandedImage.getHeight(this));
        this.collapsedImageSize = new Dimension(this.collapsedImage.getWidth(this), this.collapsedImage.getHeight(this));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        cm.addColorSelectionListener(this);
        cm.addFontSelectionListener(this);
        this.ladderTree = new LadderTree(null, new AlgorithmLine("", "", ""), logger, brkpnt);
    }
    
    public void addExplainationListener(final ExplainationListener explainationListener) {
        this.explainationListeners.addElement(explainationListener);
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.helpListeners.addElement(helpListener);
    }
    
    public void cleanUp() {
        if (this.explainationListeners != null) {
            this.explainationListeners.removeAllElements();
        }
        this.explainationListeners = null;
        if (this.helpListeners != null) {
            this.helpListeners.removeAllElements();
        }
        this.helpListeners = null;
        this.ladderTree = null;
        if (this.logoImage != null) {
            this.logoImage.flush();
        }
        this.logoImage = null;
        if (this.expandedImage != null) {
            this.expandedImage.flush();
        }
        this.expandedImage = null;
        if (this.collapsedImage != null) {
            this.collapsedImage.flush();
        }
        this.collapsedImage = null;
        this.normalFont = null;
        this.parent = null;
        this.breakpoint = null;
    }
    
    public void collapseEntireTree() {
        this.ladderTree.expandEntireLadderTree(false);
        this.repaint();
    }
    
    public boolean containsKey(final String key) {
        return this.ladderTree.containsKey(key);
    }
    
    public void display(final Graphics g) {
        this.ladderTree.displayTree(g, this);
    }
    
    public void drawAlgorithmLine(final Graphics g, final AlgorithmLine algLine, final Point pos, final boolean expanded, final boolean drawImage) {
        this.drawAlgorithmLine(g, algLine, pos, expanded, drawImage, true);
    }
    
    public void drawAlgorithmLine(final Graphics g, final AlgorithmLine algLine, final Point pos, final boolean expanded, final boolean drawImage, final boolean drawBits) {
        if (algLine.getIsLabelMinMax()) {
            return;
        }
        g.setFont(this.normalFont);
        final int fontHeight = g.getFontMetrics().getHeight();
        final int totalHeight = Math.max(fontHeight, this.expandedImageSize.height);
        if (drawImage) {
            if (expanded) {
                g.drawImage(this.expandedImage, pos.x - this.expandedImageSize.width - 5, pos.y, null);
            }
            else {
                g.drawImage(this.collapsedImage, pos.x - this.expandedImageSize.width - 5, pos.y, null);
            }
        }
        final String label = algLine.getLabel();
        final Point labelPoint = new Point(pos.x, totalHeight / 2 + fontHeight / 2 + pos.y);
        if (algLine.getIsMinMax()) {
            final Point point = labelPoint;
            point.x = point.x + (g.getFontMetrics().stringWidth(algLine.getMinMaxLabel()) + 5);
            final Point point2 = labelPoint;
            point2.x = point2.x + 14;
            if (algLine.getIsFirstMinMax()) {
                algLine.setLocation(new Point(labelPoint.x, pos.y));
            }
        }
        g.setColor(this.backgroundColor);
        if (algLine.isHighlighted()) {
            switch (algLine.getHighlightLevel()) {
                case 1: {
                    g.setColor(this.highlightColor);
                    break;
                }
                case 2: {
                    g.setColor(this.explainColor);
                    break;
                }
                case 3: {
                    g.setColor(this.breakPointColor);
                    break;
                }
            }
        }
        if (expanded && drawImage) {
            g.fillRect(labelPoint.x, pos.y, g.getFontMetrics().stringWidth(label) + this.getXBuffer(), g.getFontMetrics().getHeight());
        }
        else {
            g.fillRect(labelPoint.x, pos.y, g.getFontMetrics().stringWidth(label), g.getFontMetrics().getHeight());
        }
        if (algLine.getIsMinMax()) {
            g.setColor(this.codeColor);
            if (drawBits) {
                g.drawLine(labelPoint.x - 2 - 5, pos.y + g.getFontMetrics().getAscent(), labelPoint.x - 2, pos.y + g.getFontMetrics().getAscent());
            }
            if (algLine.getIsLastMinMax()) {
                final Point firstPnt = algLine.getFirstInstance().getLocation();
                final int secondY = (pos.y - firstPnt.y) / 2 + firstPnt.y + g.getFontMetrics().getAscent();
                g.drawLine(labelPoint.x - 2 - 5, firstPnt.y + g.getFontMetrics().getAscent(), labelPoint.x - 2 - 5, pos.y + g.getFontMetrics().getAscent());
                g.drawLine(labelPoint.x - 2 - 5 - 5, secondY, labelPoint.x - 2 - 5, secondY);
                g.setColor(this.backgroundColor);
                if (algLine.getLabelInstance().isHighlighted()) {
                    switch (algLine.getLabelInstance().getHighlightLevel()) {
                        case 1: {
                            g.setColor(this.highlightColor);
                            break;
                        }
                        case 2: {
                            g.setColor(this.explainColor);
                            break;
                        }
                    }
                }
                g.fillRect(pos.x, (pos.y - firstPnt.y) / 2 + firstPnt.y, g.getFontMetrics().stringWidth(algLine.getMinMaxLabel()), g.getFontMetrics().getHeight());
                g.setColor(this.codeColor);
                algLine.getLabelInstance().setLocation(new Point(pos.x, (pos.y - firstPnt.y) / 2 + firstPnt.y));
                g.drawString(algLine.getMinMaxLabel(), pos.x, secondY + g.getFontMetrics().getAscent() / 2);
            }
        }
        if (expanded && drawImage) {
            g.setColor(this.commentColor);
            g.drawString("//", labelPoint.x, labelPoint.y);
            g.drawString(label, labelPoint.x + this.getXBuffer(), labelPoint.y);
        }
        else {
            g.setColor(this.codeColor);
            if (algLine.isComment()) {
                g.setColor(this.commentColor);
            }
            g.drawString(label, labelPoint.x, labelPoint.y);
        }
    }
    
    public void expandEntireTree() {
        this.ladderTree.expandEntireLadderTree(true);
        this.repaint();
    }
    
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public String getClassName() {
        return "CodeCanvas";
    }
    
    public Image getCollapsedImage() {
        return this.collapsedImage;
    }
    
    public Dimension getCollapsedImageSize() {
        return this.collapsedImageSize;
    }
    
    public Image getExpandedImage() {
        return this.expandedImage;
    }
    
    public Dimension getExpandedImageSize() {
        return this.expandedImageSize;
    }
    
    public int getHeightForAnAlgorithmLine() {
        final Graphics g = this.getGraphics();
        g.setFont(this.normalFont);
        return g.getFontMetrics().getHeight();
    }
    
    public int getImageBuffer() {
        return 5;
    }
    
    protected LadderTree getLadderTree(final String key) {
        return this.ladderTree.getLadderTree(key);
    }
    
    public int getMinMaxLineLength() {
        return 5;
    }
    
    public int getMinMaxXGap() {
        return 2;
    }
    
    public String getPosition() {
        return this.currentPosition;
    }
    
    public synchronized String getWhatHighlighted(final String key) {
        return this.ladderTree.getWhatHighlighted(key);
    }
    
    public int getWidthOf(final AlgorithmLine algLine) {
        final Graphics g = this.getGraphics();
        if (g == null) {
            return 0;
        }
        g.setFont(this.normalFont);
        return g.getFontMetrics().stringWidth(algLine.getLabel());
    }
    
    public int getWidthOf(final String str) {
        final Graphics g = this.getGraphics();
        if (g == null) {
            return 0;
        }
        g.setFont(this.normalFont);
        return g.getFontMetrics().stringWidth(str);
    }
    
    public int getXBuffer() {
        final Graphics g = this.getGraphics();
        return g.getFontMetrics().stringWidth("/t") + Math.max(this.expandedImageSize.width, this.collapsedImageSize.width);
    }
    
    public int getYBuffer() {
        return this.getHeightForAnAlgorithmLine() + 2;
    }
    
    public void initialiseTree(final String fileName, final Logger logger, final BreakPoint brkpnt) {
        this.initialiseTree(this.dataPath, fileName, logger, brkpnt);
    }
    
    public void initialiseTree(final String dataPath, final String fileName, final Logger logger, final BreakPoint brkpnt) {
        final LadderTree tempLadderTree = getLadderTreeFromFile(dataPath, fileName, logger, brkpnt);
        if (tempLadderTree != null) {
            this.ladderTree = tempLadderTree;
            this.ladderTree.setPosition(this.currentPosition);
        }
    }
    
    public boolean isExpandable(final String key) {
        return this.ladderTree.isExpandable(key);
    }
    
    public boolean isExpanded(final String key) {
        return this.ladderTree.isExpanded(key);
    }
    
    public synchronized boolean isVisible(final String key) {
        return this.ladderTree.isVisible(key);
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.ladderTree.processMouseEvent(e, this, false);
    }
    
    public void mousePressed(final MouseEvent e) {
        final LadderTree temp = this.ladderTree.processMouseEvent(e, this, true);
        if (temp != null) {
            this.ladderTree = temp;
            this.repaint();
        }
        else if (this.breakpoint != null && this.breakpoint.inMutex) {
            this.breakpoint.processMutex(null);
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.backgroundColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(CodeCanvas.CODE_POSITION_MARKER)) {
            this.highlightColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(CodeCanvas.EXPLANATION_POSITION_MARKER)) {
            this.explainColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(CodeCanvas.COMMENT_LINE_COLOR)) {
            this.commentColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.codeColor = colorSelection.getColor();
        }
        else if (atribute.equalsIgnoreCase(CodeCanvas.BREAKPOINT_COLOR)) {
            this.breakPointColor = colorSelection.getColor();
        }
    }
    
    public void processExplainationEvent(final ExplainationEvent e) {
        for (int i = 0; i < this.explainationListeners.size(); ++i) {
            ((ExplainationListener)this.explainationListeners.elementAt(i)).processExplainationEvent(e);
        }
    }
    
    public void processFontSelection(final FontSelection fontSelection) {
        final String atribute = fontSelection.getAtributeName();
        if (atribute.equalsIgnoreCase("Normal Font")) {
            this.normalFont = fontSelection.getFont();
        }
    }
    
    public void processHelpEvent(final HelpEvent e) {
        for (int i = 0; i < this.helpListeners.size(); ++i) {
            ((HelpListener)this.helpListeners.elementAt(i)).processHelpEvent(e);
        }
    }
    
    public void removeExplainationListener(final ExplainationListener explainationListener) {
        this.explainationListeners.removeElement(explainationListener);
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.helpListeners.removeElement(helpListener);
    }
    
    public void setLadderTree(final LadderTree ladderTree) {
        this.ladderTree = ladderTree;
        if (this.breakpoint != null) {
            this.breakpoint.reInit();
        }
        this.repaint();
    }
    
    public void setParent(final Container parent) {
        this.parent = parent;
    }
    
    public void setPosition(final String keyId) {
        if (this.ladderTree == null) {
            System.out.println("com.cim.AIA.CodeCanvas.setPosition: LadderTree is null. Unable to set position");
            return;
        }
        this.ladderTree.setPosition(keyId);
        this.currentPosition = keyId;
        this.repaint();
    }
    
    public void setSize(final int width, final int height) {
        if (this.parent != null) {
            super.setSize(Math.max(this.parent.getSize().width, width), Math.max(this.parent.getSize().height, height));
        }
        else {
            super.setSize(width, height);
        }
    }
    
    protected boolean shouldRepaint(final String key) {
        final LadderTree child = this.ladderTree.getLadderTree(key);
        if (child != null) {
            if (child.isVisible()) {
                return false;
            }
            for (LadderTree parent = (LadderTree)child.getParent(); parent != null; parent = (LadderTree)parent.getParent()) {
                if (parent.isExpandable() && parent.isVisible() && !parent.isExpanded()) {
                    if (child.getAlgorithmLine().isLastLine(parent.getAlgorithmLine().getKey())) {
                        return true;
                    }
                    int i = 0;
                    while (i < parent.children.size()) {
                        final LadderTree sibling = (LadderTree)parent.children.elementAt(i);
                        if (sibling.isVisible() && child.getAlgorithmLine().isLastLine(sibling.getAlgorithmLine().getKey())) {
                            return true;
                        }
                        ++i;
                    }
                }
            }
        }
        return false;
    }
    
    static {
        CodeCanvas.inMinMax = false;
        CodeCanvas.isLabelMinMax = false;
        CodeCanvas.isFirstMinMax = false;
        CodeCanvas.isLastMinMax = false;
        CodeCanvas.firstInstance = null;
        CodeCanvas.labelInstance = null;
        CodeCanvas.lastInstance = null;
        OPEN_FOLDER_HELP_TITLE = Messages.getString("CodeCanvas.2");
        DEFAULT_HIGHLIGHT_COLOR = new Color(140, 204, 0);
        OPEN_FOLDER_INSTRUCTIONS = Messages.getString("CodeCanvas.3") + Messages.getString("CodeCanvas.4");
        CLOSE_FOLDER_HELP_TITLE = Messages.getString("CodeCanvas.5");
        CLOSE_FOLDER_INSTRUCTIONS = Messages.getString("CodeCanvas.6") + Messages.getString("CodeCanvas.7");
        LINE_HELP_TITLE = Messages.getString("CodeCanvas.8");
        LINE_INSTRUCTIONS = Messages.getString("CodeCanvas.9") + Messages.getString("CodeCanvas.10");
        CodeCanvas.JAR_FILE_NAME = "aia.jar";
        CodeCanvas.CODE_POSITION_MARKER = Messages.getString("CodeCanvas.21");
        CodeCanvas.EXPLANATION_POSITION_MARKER = Messages.getString("CodeCanvas.22");
        CodeCanvas.COMMENT_LINE_COLOR = Messages.getString("CodeCanvas.23");
        CodeCanvas.BREAKPOINT_COLOR = Messages.getString("CodeCanvas.24");
    }
}
