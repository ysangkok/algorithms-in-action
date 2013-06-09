package com.cim.AIA;

import java.text.*;
import java.util.*;

public class Log
{
    public static final byte TCLASS_UNREGISTERED = 0;
    public static final byte TCLASS_UNKNOWN = 1;
    public static final byte TCLASS_SYSTEM = 2;
    public static final byte TCLASS_DISPLAY = 3;
    public static final byte TCLASS_CONTROL = 4;
    public static final byte TCLASS_PSEUDOCODE = 5;
    public static final byte TCLASS_EXPLANATION = 6;
    public static final int N_TCLASSES = 7;
    public static final byte EVENT_UNREGISTERED = 0;
    public static final byte EVENT_BEGINSESSION = 1;
    public static final byte EVENT_ENDSESSION = 2;
    public static final byte EVENT_RUNSTEP = 3;
    public static final byte EVENT_OPENALL = 4;
    public static final byte EVENT_CLOSEALL = 5;
    public static final byte EVENT_BACK = 6;
    public static final byte EVENT_ADDBREAK = 7;
    public static final byte EVENT_DELETEBREAK = 8;
    public static final byte EVENT_DATASELECT = 9;
    public static final byte EVENT_EXPLANATION = 10;
    public static final byte EVENT_COLLAPSE = 11;
    public static final byte EVENT_EXPAND = 12;
    public static final byte EVENT_ALGORITHMLINE = 13;
    public static final byte EVENT_PAUSE = 14;
    public static final byte EVENT_RESET = 15;
    public static final byte EVENT_RESTART = 16;
    public static final byte EVENT_RUN = 17;
    public static final byte EVENT_METHODSELECTION = 18;
    public static final byte EVENT_STEP = 19;
    public static final byte EVENT_FINISH = 20;
    public static final byte EVENT_CHANGECODE = 21;
    public static final byte EVENT_CHANGEPARAMS = 22;
    public static final int N_EVENTS = 23;
    protected static Vector<String> tClasses;
    protected static Vector<String> events;
    protected static boolean[] xmllogged;
    public Date dateLogged;
    public byte tclass;
    public byte event;
    public String lineno;
    public String addInfo;
    protected Calendar cal;
    
    public static String convertEvent(final byte code) {
        return (String)Log.events.elementAt(code);
    }
    
    public static byte convertEvent(final String name) {
        if (name == null) {
            return 0;
        }
        for (byte i = 0; i < Log.events.size(); i = (byte)(i + 1)) {
            if (name.equals(Log.events.elementAt(i))) {
                return i;
            }
        }
        return 0;
    }
    
    public static String convertTClass(final byte code) {
        return (String)Log.tClasses.elementAt(code);
    }
    
    public static byte convertTClass(final String name) {
        if (name == null) {
            return 0;
        }
        for (byte i = 0; i < Log.tClasses.size(); i = (byte)(i + 1)) {
            if (name.equals(Log.tClasses.elementAt(i))) {
                return i;
            }
        }
        return 0;
    }
    
    public static Log createLog(final String stclass, final String sevent) {
        final byte tclass = convertTClass(stclass);
        final byte event = convertEvent(sevent);
        Log log = null;
        switch (event) {
            case 0: {
                break;
            }
            case 7:
            case 10:
            case 11:
            case 12:
            case 19: {
                log = new StepLog(tclass, event, null, 0);
                break;
            }
            case 17: {
                log = new RunLog(tclass, event, null);
                break;
            }
            default: {
                log = new Log(tclass, event);
                break;
            }
        }
        return log;
    }
    
    public static boolean isXMLLogged(final byte event) {
        return Log.xmllogged[event];
    }
    
    static String replaceAll(final String str, final String pat1, final String pat2) {
        final StringBuffer newstr = new StringBuffer();
        int i0 = 0;
        for (int i1 = 0; (i1 = str.indexOf(pat1, i0)) != -1; i0 = i1 + pat1.length()) {
            newstr.append(str.substring(i0, i1));
            newstr.append(pat2);
        }
        newstr.append(str.substring(i0));
        return newstr.toString();
    }
    
    public Log(final byte tclass, final byte event) {
        this(tclass, event, null, "");
    }
    
    public Log(final byte tclass, final byte event, final String lineno) {
        this(tclass, event, lineno, "");
    }
    
    public Log(final byte tclass, final byte event, final String lineno, final String add) {
        super();
        this.cal = Calendar.getInstance();
        this.dateLogged = new Date();
        this.tclass = tclass;
        this.event = event;
        this.lineno = lineno;
        this.addInfo = add;
    }
    
    public String getAddInfo() {
        return this.addInfo;
    }
    
    public Calendar getDate() {
        this.cal.setTime(this.dateLogged);
        return this.cal;
    }
    
    public byte getEvent() {
        return this.event;
    }
    
    public String getLineNo() {
        return this.lineno;
    }
    
    public byte getTClass() {
        return this.tclass;
    }
    
    public Date getTime() {
        return this.dateLogged;
    }
    
    public boolean isXMLLogged() {
        return isXMLLogged(this.event);
    }
    
    public String output(final int output_mode) {
        switch (output_mode) {
            case 1: {
                return this.textOutput();
            }
            case 2: {
                return this.toXML();
            }
            default: {
                return null;
            }
        }
    }
    
    public void print() {
        this.cal.setTime(this.dateLogged);
        System.out.println("" + convertTClass(this.tclass) + ": " + convertEvent(this.event) + ": " + this.addInfo + ", logged on " + this.cal.get(5) + "-" + this.cal.get(2) + "-" + this.cal.get(1) + ": " + this.cal.get(11) + ":" + this.cal.get(12));
    }
    
    public void setParam(final String param, final String val) {
        if (param.equalsIgnoreCase("linenumber")) {
            this.lineno = val;
        }
        else if (param.equalsIgnoreCase("option")) {
            this.addInfo = val;
        }
    }
    
    public void setTClass(final String stclass) {
        final byte tcls = convertTClass(stclass);
        this.tclass = tcls;
    }
    
    public void setTime(final String time) {
        try {
            this.dateLogged = Logger.datefmt.parse(time);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public String textOutput() {
        this.cal.setTime(this.dateLogged);
        return convertTClass(this.tclass) + "#" + convertEvent(this.event) + "#" + this.addInfo + "#" + this.cal.get(5) + "-" + this.cal.get(2) + "-" + this.cal.get(1) + ": " + this.cal.get(11) + ":" + this.cal.get(12) + "#" + '\n';
    }
    
    public String toXML() {
        if (!isXMLLogged(this.event)) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        this.xmlOpen(sb);
        this.xmlBody(sb);
        this.xmlClose(sb);
        return sb.toString();
    }
    
    protected void xmlBody(final StringBuffer sb) {
        sb.append("<name>" + convertEvent(this.event) + "</name>\n");
        sb.append("<tclass>" + convertTClass(this.tclass) + "</tclass>\n");
        sb.append("<time>" + Logger.datefmt.format(this.dateLogged) + "</time>\n");
        if (this.lineno != null) {
            sb.append("<param name="linenumber" value="" + this.lineno + ""/>\n");
        }
        if (Logger.OUTPUTADDINFO && this.addInfo != null && !this.addInfo.equals("") && !this.addInfo.equals(this.lineno)) {
            String safeinfo = new String(this.addInfo);
            safeinfo = replaceAll(safeinfo, "<", "&lt;");
            safeinfo = replaceAll(safeinfo, ">", "&gt;");
            safeinfo = replaceAll(safeinfo, """, "&quot;");
            safeinfo = replaceAll(safeinfo, "&", "&amp;");
            safeinfo = replaceAll(safeinfo, "'", "&apos;");
            sb.append("<param name="option" value="" + safeinfo + ""/>\n");
        }
    }
    
    protected void xmlClose(final StringBuffer sb) {
        sb.append("</action>\n");
    }
    
    protected void xmlOpen(final StringBuffer sb) {
        sb.append("<action>\n");
    }
    
    static {
        Log.tClasses = new Vector(7);
        Log.tClasses.setSize(7);
        Log.tClasses.setElementAt("Unregistered", 0);
        Log.tClasses.setElementAt("Unknown", 1);
        Log.tClasses.setElementAt("System", 2);
        Log.tClasses.setElementAt("Display", 3);
        Log.tClasses.setElementAt("Control", 4);
        Log.tClasses.setElementAt("Pseudocode", 5);
        Log.tClasses.setElementAt("Explanation", 6);
        Log.events = new Vector();
        if (Logger.DEBUG) {
            System.err.println("Setting events array size to 23");
        }
        Log.events.setSize(23);
        Log.events.setElementAt("Unregistered", 0);
        Log.events.setElementAt("Begin Session", 1);
        Log.events.setElementAt("End Session", 2);
        Log.events.setElementAt("Run Step", 3);
        Log.events.setElementAt("Open All", 4);
        Log.events.setElementAt("Close All", 5);
        Log.events.setElementAt("Add BreakPoint", 7);
        Log.events.setElementAt("Del BreakPoint", 8);
        Log.events.setElementAt("Data Selection", 9);
        Log.events.setElementAt("Explanation", 10);
        Log.events.setElementAt("Collapse", 11);
        Log.events.setElementAt("Expand", 12);
        Log.events.setElementAt("Algorithm On Line", 13);
        Log.events.setElementAt("Pause Button", 14);
        Log.events.setElementAt("Reset Button", 15);
        Log.events.setElementAt("Restart/ChangeMode Button", 16);
        Log.events.setElementAt("Run Button", 17);
        Log.events.setElementAt("Method Selection", 18);
        Log.events.setElementAt("Step Button", 19);
        Log.events.setElementAt("Back Button", 6);
        Log.events.setElementAt("Change Code", 21);
        Log.events.setElementAt("Change Parameters", 22);
        Log.xmllogged = new boolean[Log.events.size()];
        for (int i = 0; i < Log.events.size(); ++i) {
            Log.xmllogged[i] = false;
        }
        Log.xmllogged[19] = true;
        Log.xmllogged[6] = true;
        Log.xmllogged[17] = true;
        Log.xmllogged[14] = true;
        Log.xmllogged[10] = true;
        Log.xmllogged[7] = true;
        Log.xmllogged[8] = true;
        Log.xmllogged[12] = true;
        Log.xmllogged[11] = true;
        Log.xmllogged[15] = true;
        Log.xmllogged[4] = true;
        Log.xmllogged[5] = true;
        Log.xmllogged[21] = true;
        Log.xmllogged[9] = true;
        Log.xmllogged[22] = true;
    }
}
