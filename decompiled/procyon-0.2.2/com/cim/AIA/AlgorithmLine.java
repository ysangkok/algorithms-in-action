package com.cim.AIA;

import java.util.*;
import java.awt.*;

public class AlgorithmLine
{
    protected static final String NO_EXPLAINATION = "";
    protected static boolean DEBUG_MODE;
    protected Point location;
    protected boolean isMinMax;
    protected boolean isLabelMinMax;
    protected boolean isFirstMinMax;
    protected boolean isLastMinMax;
    protected AlgorithmLine firstInstance;
    protected AlgorithmLine labelInstance;
    protected String minmaxLabel;
    protected String label;
    protected String key;
    protected String explaination;
    protected String alternativeLabel;
    protected Vector<String> triggers;
    protected int triggerCount;
    protected Vector<String> lastLineKeys;
    protected int highlight;
    
    public static void setDebugMode(final boolean mode) {
        AlgorithmLine.DEBUG_MODE = mode;
    }
    
    public AlgorithmLine(final String algLine) {
        super();
        this.location = new Point();
        this.isMinMax = false;
        this.isLabelMinMax = false;
        this.isFirstMinMax = false;
        this.isLastMinMax = false;
        this.firstInstance = null;
        this.labelInstance = null;
        this.label = "";
        this.key = "";
        this.explaination = "";
        this.alternativeLabel = "";
        this.triggers = new Vector();
        this.triggerCount = 0;
        this.lastLineKeys = new Vector();
        this.highlight = 0;
        final StringTokenizer tok = new StringTokenizer(algLine, "#");
        final String templabel = tok.nextToken();
        final StringTokenizer labTok = new StringTokenizer(templabel, "|");
        this.label = labTok.nextToken();
        if (labTok.hasMoreTokens()) {
            this.alternativeLabel = labTok.nextToken();
        }
        if (tok.hasMoreTokens()) {
            final String tempKey = tok.nextToken();
            final StringTokenizer keyTok = new StringTokenizer(tempKey, "|");
            this.key = keyTok.nextToken();
            this.key = this.strip(this.key);
            while (keyTok.hasMoreTokens()) {
                String temp = keyTok.nextToken();
                temp = this.strip(temp);
                this.addTrigger(temp);
            }
        }
        if (tok.hasMoreTokens()) {
            final String tempExplaination = tok.nextToken();
            final StringTokenizer lastLineTok = new StringTokenizer(tempExplaination, "|");
            while (lastLineTok.countTokens() > 1) {
                final String lastLineOfKey = this.strip(lastLineTok.nextToken());
                this.lastLineKeys.addElement(lastLineOfKey);
            }
            this.explaination = lastLineTok.nextToken();
        }
        while (tok.hasMoreTokens()) {
            this.explaination = this.explaination + "\n\n" + tok.nextToken();
        }
    }
    
    public AlgorithmLine(final String label, final String key, final String explaination) {
        super();
        this.location = new Point();
        this.isMinMax = false;
        this.isLabelMinMax = false;
        this.isFirstMinMax = false;
        this.isLastMinMax = false;
        this.firstInstance = null;
        this.labelInstance = null;
        this.label = "";
        this.key = "";
        this.explaination = "";
        this.alternativeLabel = "";
        this.triggers = new Vector();
        this.triggerCount = 0;
        this.lastLineKeys = new Vector();
        this.highlight = 0;
        this.label = label;
        this.key = this.strip(key);
        this.explaination = explaination;
    }
    
    public AlgorithmLine(final String label, final String alternativeLabel, final String key, final String explaination) {
        this(label, key, explaination);
        this.alternativeLabel = alternativeLabel;
    }
    
    public void addLastLineKey(final String lastLineOfKey) {
        this.lastLineKeys.addElement(lastLineOfKey);
    }
    
    public void addTrigger(final String key) {
        this.triggers.addElement(key);
    }
    
    public void decreaseTriggerCount() {
        if (this.triggerCount >= 1) {
            this.triggerCount = this.triggerCount - 1;
        }
    }
    
    public String getExplaination() {
        if (this.explaination == "") {
            return "";
        }
        return this.explaination;
    }
    
    public AlgorithmLine getFirstInstance() {
        return this.firstInstance;
    }
    
    public int getHighlightLevel() {
        return this.highlight;
    }
    
    public boolean getIsFirstMinMax() {
        return this.isFirstMinMax;
    }
    
    public boolean getIsLabelMinMax() {
        return this.isLabelMinMax;
    }
    
    public boolean getIsLastMinMax() {
        return this.isLastMinMax;
    }
    
    public boolean getIsMinMax() {
        return this.isMinMax;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public String getLabel() {
        if (this.alternativeLabel == "") {
            if (AlgorithmLine.DEBUG_MODE) {
                return this.label + " " + this.key;
            }
            return this.label;
        }
        else if (this.triggerCount == 0) {
            if (AlgorithmLine.DEBUG_MODE) {
                return this.label + " " + this.key;
            }
            return this.label;
        }
        else {
            if (AlgorithmLine.DEBUG_MODE) {
                return this.alternativeLabel + " " + this.key;
            }
            return this.alternativeLabel;
        }
    }
    
    public AlgorithmLine getLabelInstance() {
        return this.labelInstance;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public String getMinMaxLabel() {
        return this.minmaxLabel;
    }
    
    public Vector<String> getTriggers() {
        return this.triggers;
    }
    
    public void increaseTriggerCount() {
        this.triggerCount = this.triggerCount + 1;
    }
    
    public boolean isComment() {
        return this.label.indexOf("//") == 0;
    }
    
    public boolean isHighlighted() {
        return this.highlight != 0;
    }
    
    public boolean isLastLine(final String key) {
        for (int i = 0; i < this.lastLineKeys.size(); ++i) {
            if (((String)this.lastLineKeys.elementAt(i)).equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }
    
    public void setFirstInstance(final AlgorithmLine inst) {
        this.firstInstance = inst;
    }
    
    public void setHighlightLevel(final int highlight) {
        this.highlight = highlight;
    }
    
    public void setIsFirstMinMax(final boolean state) {
        this.isFirstMinMax = state;
    }
    
    public void setIsLabelMinMax(final boolean state) {
        this.isLabelMinMax = state;
    }
    
    public void setIsLastMinMax(final boolean state) {
        this.isLastMinMax = state;
    }
    
    public void setIsMinMax(final boolean state) {
        this.isMinMax = state;
    }
    
    public void setLabelInstance(final AlgorithmLine inst) {
        this.labelInstance = inst;
    }
    
    public void setLocation(final Point pnt) {
        this.location = pnt;
    }
    
    public void setMinMaxLabel(final String label) {
        this.minmaxLabel = label;
    }
    
    protected String strip(final String string) {
        string.trim();
        final StringTokenizer st = new StringTokenizer(string, " ");
        String temp = "";
        while (st.hasMoreTokens()) {
            temp = temp + st.nextToken();
        }
        return temp;
    }
    
    static {
        AlgorithmLine.DEBUG_MODE = false;
    }
}
