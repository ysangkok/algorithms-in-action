package com.cim.AIA;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class Logger
{
    protected static final int TEXT_OUTPUT = 1;
    protected static final int XML_OUTPUT = 2;
    protected static final String TEXT_CGISCRIPT = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog.cgi";
    protected static final String XML_CGISCRIPT = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog2.pl";
    protected static final String LOCAL_LOGFILE = "aiatest.log";
    protected static final int TMGRAN = 60000;
    public static DateFormat datefmt;
    public static final String AIASERVER_TIMEZONE = "Australia/Melbourne";
    protected static boolean OUTPUTADDINFO;
    private static final int SPEEDMODERATOR = 40;
    public static boolean DEBUG;
    protected static boolean DEBUG_NOSERVER;
    protected String userName;
    protected Date startTime;
    protected Date endTime;
    protected int numOfBacks;
    protected int numOfSteps;
    protected int numOfRuns;
    protected int numOfExplanations;
    protected int numOfExpansions;
    protected int numOfDataSelections;
    protected int numOfAddBreakPoints;
    protected int numOfDelBreakPoints;
    protected Vector<String> listOfExplanations;
    protected Vector<String> listOfBacks;
    protected Vector<String> listOfAddBreakPoints;
    protected String lastDisplay;
    protected String nameOfAlgorithm;
    protected String XMLLOG_VERSION;
    protected int totalDisplays;
    protected int totalSteps;
    protected boolean isOnStep;
    protected boolean doBuffering;
    protected int firstStep;
    protected int firstBack;
    protected int firstRun;
    protected int firstExplanation;
    protected int firstDataSelection;
    protected int firstFolder;
    protected int bufferSize;
    protected int currentSize;
    protected String currentFile;
    protected Vector<Log> logs;
    protected boolean dumpTrace;
    protected boolean enabled;
    protected boolean doLog;
    protected boolean doBegin;
    protected int output_mode;
    protected String cgiscript;
    protected String logdir;
    protected boolean urlencode;
    protected RunLog currentRun;
    protected int speed;
    protected String userPW;
    
    public Logger() {
        super();
        this.listOfExplanations = new Vector();
        this.listOfBacks = new Vector();
        this.listOfAddBreakPoints = new Vector();
        this.XMLLOG_VERSION = "1.0.4";
        this.doBuffering = false;
        this.bufferSize = 300;
        this.currentSize = 0;
        this.currentFile = null;
        this.logs = new Vector();
        this.dumpTrace = true;
        this.enabled = true;
        this.doLog = false;
        this.doBegin = false;
        this.output_mode = 2;
        this.cgiscript = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog2.pl";
        this.logdir = null;
        this.urlencode = true;
        this.currentRun = null;
        this.speed = -1;
    }
    
    public static void setLogAddInfo(final boolean logaddinfo) {
        Logger.OUTPUTADDINFO = logaddinfo;
    }
    
    public static String stripDecimal(final float num, int dec) {
        String numStr = Float.toString(num);
        if (numStr.equals("Infinity") || numStr.equals("NaN")) {
            return numStr;
        }
        int counter = 0;
        while (numStr.charAt(counter) != '.') {
            ++counter;
        }
        ++counter;
        while (counter < numStr.length() && dec > 0) {
            ++counter;
            --dec;
        }
        numStr = numStr.substring(0, counter);
        for (; dec != 0; --dec) {
            numStr = numStr + "0";
        }
        return numStr;
    }
    
    public synchronized void addLog(final Log l1) {
        if (!this.doLog || !this.enabled) {
            return;
        }
        this.currentSize = this.currentSize + 1;
        if (this.output_mode != 2 || l1.isXMLLogged()) {
            this.logs.addElement(l1);
        }
        this.sideLine(l1.tclass, l1.event, l1.addInfo, l1);
        if (this.doBuffering && this.currentSize >= this.bufferSize) {
            this.currentFile = this.dumpToServer(this.currentFile);
            this.currentSize = 0;
            this.logs = new Vector();
        }
    }
    
    public void beginGuestLogging(final String userName, final String moduleName) {
        if (!this.enabled) {
            return;
        }
        this.doLog = false;
        this.enabled = false;
        this.dumpGuestToServer(userName, moduleName);
    }
    
    public void beginLogging() {
        this.beginLogging("Unknown Algorithm");
    }
    
    public void beginLogging(final String name) {
        this.beginLogging(name, "aia");
    }
    
    public void beginLogging(final String name, final String username) {
        if (!this.enabled) {
            return;
        }
        this.doLog = true;
        this.userName = username;
        this.currentSize = 0;
        this.logs = new Vector();
        this.clearSummary();
        final Log l1 = new Log(2, 1, null, name);
        this.addLog(l1);
        this.doBegin = true;
        if (this.output_mode != 2) {
            if (!this.dumpTrace) {
                this.currentFile = "NoFile";
                this.dumpTrace = true;
                this.currentFile = this.dumpToServer(this.currentFile);
                this.dumpTrace = false;
            }
            else {
                this.currentFile = "NewFile";
                this.currentFile = this.dumpToServer(this.currentFile);
            }
            this.currentSize = 0;
            this.logs = new Vector();
        }
    }
    
    public void beginLogging(final String name, final String username, final String userpw) {
        if (!this.enabled) {
            return;
        }
        this.doLog = true;
        this.userName = username;
        this.userPW = userpw;
        this.currentSize = 0;
        this.logs = new Vector();
        this.clearSummary();
        final Log l1 = new Log(2, 1, null, name);
        this.addLog(l1);
        this.doBegin = true;
        if (this.output_mode != 2) {
            if (!this.dumpTrace) {
                this.currentFile = "NoFile";
                this.dumpTrace = true;
                this.currentFile = this.dumpToServer(this.currentFile);
                this.dumpTrace = false;
            }
            else {
                this.currentFile = "NewFile";
                this.currentFile = this.dumpToServer(this.currentFile);
            }
            this.currentSize = 0;
            this.logs = new Vector();
        }
    }
    
    public void clearSummary() {
        this.firstStep = -1;
        this.firstBack = -1;
        this.firstRun = -1;
        this.firstExplanation = -1;
        this.firstDataSelection = -1;
        this.firstFolder = -1;
        this.numOfBacks = 0;
        this.numOfSteps = 0;
        this.numOfRuns = 0;
        this.numOfExplanations = 0;
        this.numOfExpansions = 0;
        this.numOfDataSelections = 0;
        this.numOfAddBreakPoints = 0;
        this.numOfDelBreakPoints = 0;
        this.startTime = null;
        this.endTime = null;
        this.listOfExplanations = new Vector();
        this.listOfBacks = new Vector();
        this.listOfAddBreakPoints = new Vector();
        this.nameOfAlgorithm = null;
        this.lastDisplay = null;
        this.totalDisplays = 0;
        this.totalSteps = 0;
        this.isOnStep = false;
    }
    
    private synchronized void dumpGuestToServer(final String user, final String line) {
        final URL u;
        final URLConnection c;
        final DataOutputStream out;
        final StringBuffer sb2;
        final StringBuffer sb;
        final PrintStream out2;
        final DataInputStream in;
        Label_0014:
        {
            if (!Logger.DEBUG) {
                break Label_0014;
            }
            System.err.println("in Logger.dumpGuestToServer()");
            try {
                u = new URL(this.cgiscript);
                c = u.openConnection();
                c.setDoOutput(true);
                c.setAllowUserInteraction(true);
                c.setUseCaches(false);
                if (this.output_mode == 2) {
                    c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    out = new DataOutputStream(c.getOutputStream());
                    sb = (sb2 = new StringBuffer());
                    sb2.append("user=" + URLEncoder.encode(user));
                    sb.append("&algorithm=" + URLEncoder.encode(line));
                    out.writeBytes(sb.toString());
                    out.close();
                }
                else {
                    out2 = new PrintStream(c.getOutputStream());
                    out2.print("AIA Log\n");
                    out2.print("Guest\n");
                    out2.print("NoFile\n");
                    out2.print(user + ": " + line + '\n');
                    out2.close();
                }
                in = new DataInputStream(c.getInputStream());
                while (in.readLine() != null) {}
                in.close();
            }
            catch (IOException e) {
                System.out.println("Exception: " + e);
            }
            catch (SecurityException e2) {
                System.out.println("AiA WARNING: No logging because of non local server");
                System.out.println("             use NOLOG=true to avoid this warning");
                System.out.println("             Disabling Logging");
                this.pause();
                this.enabled = false;
            }
        }
    }
    
    public void dumpLog() {
        System.out.println("Dumping log....");
        for (int i = 0; i < this.logs.size(); ++i) {
            final Log l1 = (Log)this.logs.elementAt(i);
            l1.print();
        }
    }
    
    public void dumpOutput() {
        System.out.println("Dumping output....");
        for (int i = 0; i < this.logs.size(); ++i) {
            final Log l1 = (Log)this.logs.elementAt(i);
            System.out.print(l1.output(this.output_mode));
        }
    }
    
    public void dumpSummary() {
        System.out.println("Dumping Summary....");
        System.out.println("Summary for " + this.nameOfAlgorithm);
        System.out.println("-----------");
        final long duration = (this.endTime.getTime() - this.startTime.getTime()) / 60000L;
        System.out.println("Duration(min): " + duration);
        System.out.println("StepClicks/RunClicks: " + this.numOfSteps + "/" + this.numOfRuns + "(" + stripDecimal((float)this.numOfSteps / (float)this.numOfRuns, 2) + ")");
        System.out.println("Backs: " + this.numOfBacks);
        System.out.println("Displays/Steps: " + this.totalDisplays + "/" + this.totalSteps + "(" + stripDecimal((float)this.totalDisplays / (float)this.totalSteps, 2) + ")");
        System.out.println("Folders: " + this.numOfExpansions);
        System.out.println("Explanations: " + this.numOfExplanations);
        System.out.println("Data Selection: " + this.numOfDataSelections);
        System.out.println("First Step: " + this.firstStep);
        System.out.println("First Back: " + this.firstBack);
        System.out.println("First Run: " + this.firstRun);
        System.out.println("First Explanation: " + this.firstExplanation);
        System.out.println("First Data Selection: " + this.firstDataSelection);
        System.out.println("First Folder: " + this.firstFolder);
        System.out.println("Explanations List: ");
        for (int i = 0; i < this.listOfExplanations.size(); ++i) {
            System.out.println("    " + (String)this.listOfExplanations.elementAt(i));
        }
        System.out.println("Back List: ");
        for (int i = 0; i < this.listOfBacks.size(); ++i) {
            System.out.println("    " + (String)this.listOfBacks.elementAt(i));
        }
    }
    
    private synchronized String dumpSummaryToServer(final String filename) {
        final URL u;
        final URLConnection c;
        final String dumpTextSummary;
        final MalformedURLException ex;
        MalformedURLException e;
        final IOException ex2;
        final SecurityException ex3;
        final SecurityException e2;
        final String dumpXMLSummary;
        Label_0071:
        {
            try {
                u = new URL(this.cgiscript);
                c = u.openConnection();
                c.setDoOutput(true);
                c.setAllowUserInteraction(true);
                c.setUseCaches(false);
                switch (this.output_mode) {
                    case 1: {
                        dumpTextSummary = this.dumpTextSummary(c, filename);
                        break;
                    }
                    case 2: {
                        break Label_0071;
                    }
                }
            }
            catch (MalformedURLException) {
                e = ex;
                System.out.println("Exception: " + e);
                return "NewFile";
            }
            catch (IOException) {
                e = ex2;
                System.out.println("Exception: " + e);
                return "NewFile";
            }
            catch (SecurityException) {
                e2 = ex3;
                System.out.println("AiA WARNING: No logging because of non local server");
                System.out.println("             use NOLOG=true to avoid this warning");
                System.out.println("             Disabling Logging");
                this.pause();
                this.enabled = false;
            }
            return dumpTextSummary;
            try {
                dumpXMLSummary = this.dumpXMLSummary(c);
            }
            catch (MalformedURLException) {}
            catch (IOException) {}
            catch (SecurityException) {}
        }
        return dumpXMLSummary;
    }
    
    private synchronized String dumpText(final URLConnection c, final String fname) throws IOException, SecurityException {
        String s = null;
        final PrintStream out = new PrintStream(c.getOutputStream());
        out.print("AIA Log\n");
        if (this.doBegin) {
            out.print("Begin\n");
            this.doBegin = false;
        }
        out.print(fname + '\n');
        for (int i = 0; i < this.logs.size(); ++i) {
            final Log l1 = (Log)this.logs.elementAt(i);
            l1.output(this.output_mode);
        }
        out.close();
        final DataInputStream in = new DataInputStream(c.getInputStream());
        String j;
        while ((j = in.readLine()) != null) {
            s = j;
        }
        in.close();
        if (s != null) {
            return s;
        }
        return "NewFile";
    }
    
    private synchronized String dumpTextSummary(final URLConnection c, final String filename) throws IOException, SecurityException {
        String s = null;
        final PrintStream out = new PrintStream(c.getOutputStream());
        out.print("AIA Log\n");
        out.print("Summary\n");
        out.print(filename + '\n');
        out.print("User: " + this.userName + '\n');
        out.print("Summary for " + this.nameOfAlgorithm + '\n');
        final long duration = (this.endTime.getTime() - this.startTime.getTime()) / 60000L;
        out.print("Duration(min): " + duration + '\n');
        out.print("StepClicks/RunClicks: " + this.numOfSteps + "/" + this.numOfRuns + " " + stripDecimal((float)this.numOfSteps / (float)this.numOfRuns, 2) + "" + '\n');
        out.print("Backs: " + this.numOfBacks + '\n');
        out.print("Displays/Steps: " + this.totalDisplays + "/" + this.totalSteps + " " + stripDecimal((float)this.totalDisplays / (float)this.totalSteps, 2) + "" + '\n');
        out.print("Backs/Steps: " + this.numOfBacks + "/" + this.totalSteps + " " + stripDecimal((float)this.numOfBacks / (float)this.totalSteps, 2) + "" + '\n');
        out.print("Folders: " + this.numOfExpansions + '\n');
        out.print("Explanations: " + this.numOfExplanations + '\n');
        out.print("Data Selection: " + this.numOfDataSelections + '\n');
        out.print("Add Breakpoints: " + this.numOfAddBreakPoints + '\n');
        out.print("Del Breakpoints: " + this.numOfDelBreakPoints + '\n');
        out.print("First Step: " + this.firstStep + '\n');
        out.print("First Back: " + this.firstBack + '\n');
        out.print("First Run: " + this.firstRun + '\n');
        out.print("First Explanation: " + this.firstExplanation + '\n');
        out.print("First Data Selection: " + this.firstDataSelection + '\n');
        out.print("First Folder: " + this.firstFolder + '\n');
        out.print("Explanations List: \n");
        for (int i = 0; i < this.listOfExplanations.size(); ++i) {
            out.print("    " + (String)this.listOfExplanations.elementAt(i) + '\n');
        }
        out.print("Back List: \n");
        for (int i = 0; i < this.listOfBacks.size(); ++i) {
            out.print("    " + (String)this.listOfBacks.elementAt(i) + '\n');
        }
        out.print("Break Point List: \n");
        for (int i = 0; i < this.listOfAddBreakPoints.size(); ++i) {
            out.print("    " + (String)this.listOfAddBreakPoints.elementAt(i) + '\n');
        }
        out.close();
        final DataInputStream in = new DataInputStream(c.getInputStream());
        String j;
        while ((j = in.readLine()) != null) {
            s = j;
        }
        in.close();
        if (s != null) {
            return s;
        }
        return "NewFile";
    }
    
    private synchronized String dumpToServer(final String filename) {
        if (Logger.DEBUG) {
            System.err.println("Dumping to server.  Filename: " + filename);
        }
        if (!this.dumpTrace) {
            return filename;
        }
        URLConnection c;
        final URL u;
        final String dumpText;
        final IOException ex;
        final IOException e;
        final SecurityException ex2;
        final SecurityException e2;
        final String dumpXML;
        Label_0151:
        {
            try {
                c = null;
                if (!Logger.DEBUG_NOSERVER) {
                    u = new URL(this.cgiscript);
                    c = u.openConnection();
                    if (Logger.DEBUG) {
                        System.err.println("Calling CGI script" + this.cgiscript);
                    }
                    c.setDoOutput(true);
                    c.setAllowUserInteraction(true);
                    c.setUseCaches(false);
                }
                switch (this.output_mode) {
                    case 1: {
                        dumpText = this.dumpText(c, filename);
                        break;
                    }
                    case 2: {
                        break Label_0151;
                    }
                }
            }
            catch (IOException) {
                e = ex;
                System.out.println("Exception: " + e);
                return "NewFile";
            }
            catch (SecurityException) {
                e2 = ex2;
                if (Logger.DEBUG) {
                    e2.printStackTrace();
                }
                System.out.println("AiA WARNING: No logging because of non local server");
                System.out.println("             use NOLOG=true to avoid this warning");
                System.out.println("             Disabling Logging");
                this.pause();
                this.enabled = false;
            }
            return dumpText;
            try {
                dumpXML = this.dumpXML(c);
            }
            catch (IOException) {}
            catch (SecurityException) {}
        }
        return dumpXML;
    }
    
    private synchronized String dumpXML(final URLConnection c) throws IOException, SecurityException {
        DataOutputStream out = null;
        if (c != null) {
            c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            out = new DataOutputStream(c.getOutputStream());
        }
        else if (Logger.DEBUG_NOSERVER) {
            out = new DataOutputStream(System.err);
        }
        final StringBuffer content = new StringBuffer();
        if (Logger.DEBUG) {
            System.err.println("logs.size() : " + this.logs.size());
        }
        content.append("user=" + URLEncoder.encode(this.userName));
        content.append("&algorithm=" + URLEncoder.encode(this.nameOfAlgorithm));
        content.append("&nlogs=" + this.logs.size());
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < this.logs.size(); ++i) {
            final Log l1 = (Log)this.logs.elementAt(i);
            buf.append(l1.output(this.output_mode));
        }
        final String thelog = buf.toString();
        content.append("&log=" + URLEncoder.encode(thelog));
        content.append("&uid=" + URLEncoder.encode(this.userPW));
        if (Logger.DEBUG) {
            System.err.println(content.toString());
        }
        out.writeBytes(content.toString());
        out.flush();
        out.close();
        String filename = null;
        if (c != null) {
            final DataInputStream in = new DataInputStream(c.getInputStream());
            String str;
            while ((str = in.readLine()) != null) {
                filename = str;
            }
        }
        return (filename != null) ? "XML" : filename;
    }
    
    private synchronized String dumpXMLSummary(final URLConnection c) throws IOException, SecurityException {
        c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        final DataOutputStream out = new DataOutputStream(c.getOutputStream());
        final StringBuffer content = new StringBuffer();
        if (Logger.DEBUG) {
            System.err.println("logs.size() : " + this.logs.size());
        }
        content.append("user=" + URLEncoder.encode(this.userName));
        content.append("&algorithm=" + URLEncoder.encode(this.nameOfAlgorithm));
        content.append("&nlogs=" + this.logs.size());
        if (this.logdir != null) {
            content.append("&logdir=" + URLEncoder.encode(this.logdir));
        }
        final StringBuffer buf = new StringBuffer();
        buf.append("<?xml version="1.0" standalone="yes" ?>\n");
        buf.append("<!DOCTYPE session>\n");
        buf.append("<session version="" + this.XMLLOG_VERSION + "">\n");
        buf.append("<userID>" + this.userName + "</userID>\n");
        buf.append("<algorithm>" + this.nameOfAlgorithm + "</algorithm>\n");
        final String ipaddr = "localhost/127.0.0.1";
        buf.append("<connectingIP>" + ipaddr + "</connectingIP>\n");
        buf.append("<starttime>" + Logger.datefmt.format(this.startTime) + "</starttime>\n");
        buf.append("<endtime>" + Logger.datefmt.format(this.endTime) + "</endtime>\n");
        buf.append("<timezone>Australia/Melbourne</timezone>\n");
        buf.append("<startmtime>" + this.startTime.getTime() + "</startmtime>\n");
        buf.append("<endmtime>" + this.endTime.getTime() + "</endmtime>\n");
        for (int i = 0; i < this.logs.size(); ++i) {
            final Log l1 = (Log)this.logs.elementAt(i);
            buf.append(l1.output(this.output_mode));
        }
        buf.append("</session>\n");
        final String thelog = buf.toString();
        content.append("&log=" + URLEncoder.encode(thelog));
        content.append("&uid=" + URLEncoder.encode(this.userPW));
        if (Logger.DEBUG) {
            System.err.println(content.toString());
        }
        out.writeBytes(content.toString());
        out.flush();
        out.close();
        final DataInputStream in = new DataInputStream(c.getInputStream());
        String filename = null;
        String str;
        while ((str = in.readLine()) != null) {
            filename = str;
        }
        return (filename != null) ? "XML" : filename;
    }
    
    public void endLogging() {
        if (!this.dumpTrace) {
            this.dumpTrace = true;
            this.logs = new Vector();
            this.currentSize = 0;
        }
        final Log l1 = new Log(2, 2, null);
        this.addLog(l1);
        this.doLog = false;
        this.endLogProcedure();
        this.enabled = false;
    }
    
    private void endLogProcedure() {
        if (!this.enabled) {
            return;
        }
        if (this.output_mode != 2) {
            this.currentFile = this.dumpToServer(this.currentFile);
        }
        this.currentFile = this.dumpSummaryToServer(this.currentFile);
    }
    
    public boolean getDumpTrace() {
        return this.dumpTrace;
    }
    
    public boolean getEnabled() {
        return this.enabled;
    }
    
    public int getOutputMode() {
        return this.output_mode;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void pause() {
        this.doLog = false;
    }
    
    public void resume() {
        this.doLog = true;
    }
    
    public void setBufferSize(final int size) {
        this.bufferSize = size;
    }
    
    public void setCGIScript(final String path) {
        this.cgiscript = path;
    }
    
    public void setDumpTrace(final boolean state) {
        this.dumpTrace = state;
    }
    
    public void setEnabled(final boolean state) {
        this.enabled = state;
    }
    
    public void setLogDir(final String path) {
        this.logdir = path;
    }
    
    public void setOutputMode(final int mode) {
        this.output_mode = mode;
    }
    
    public void setOutputMode(final String smode) {
        int mode = this.output_mode;
        if (smode.equalsIgnoreCase("xml")) {
            mode = 2;
        }
        else if (smode.equalsIgnoreCase("text")) {
            mode = 1;
        }
        this.output_mode = mode;
        switch (mode) {
            case 1: {
                this.cgiscript = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog.cgi";
                break;
            }
            case 2: {
                this.cgiscript = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog2.pl";
                break;
            }
        }
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed / 40;
    }
    
    public void sideLine(final byte tClass, final byte event, final String add, final Log log) {
        if (event == 1) {
            this.nameOfAlgorithm = add;
            this.startTime = new Date();
        }
        if (event == 2) {
            this.endTime = new Date();
        }
        if (this.isOnStep && tClass == 3) {
            this.totalDisplays = this.totalDisplays + 1;
        }
        if (tClass == 3) {
            this.lastDisplay = add;
        }
        if (this.isOnStep && (tClass == 4 || tClass == 2)) {
            this.totalSteps = this.totalSteps + 1;
        }
        if (event == 3 && this.currentRun != null) {
            this.currentRun.incrSteps();
            if (log.getClass().isAssignableFrom(StepLog.class)) {
                this.currentRun.incrHiddenLines(((StepLog)log).getHiddenLines());
            }
            else {
                System.err.println("Logger.sideline(): Warning: log is not of type StepLog");
            }
        }
        if (event != 13 || this.currentRun != null) {}
        if (event == 19) {
            if (this.firstStep == -1) {
                this.firstStep = (int)(log.getTime().getTime() - this.startTime.getTime()) / 60000;
            }
            this.numOfSteps = this.numOfSteps + 1;
            this.isOnStep = true;
        }
        if (event == 17) {
            if (this.firstRun == -1) {
                this.firstRun = (int)(log.getTime().getTime() - this.startTime.getTime()) / 60000;
            }
            this.numOfRuns = this.numOfRuns + 1;
            this.isOnStep = true;
            ((RunLog)log).setStartSpeed(this.speed);
            this.currentRun = (RunLog)log;
        }
        if (event == 6) {
            if (this.firstBack == -1) {
                this.firstBack = (int)(log.getTime().getTime() - this.startTime.getTime()) / 60000;
            }
            this.numOfBacks = this.numOfBacks + 1;
            this.listOfBacks.addElement(this.lastDisplay);
            this.isOnStep = false;
        }
        if (event == 9) {
            if (this.firstDataSelection == -1) {
                this.firstDataSelection = (int)(log.getTime().getTime() - this.startTime.getTime()) / 60000;
            }
            this.numOfDataSelections = this.numOfDataSelections + 1;
        }
        if (event == 10) {
            if (this.firstExplanation == -1) {
                this.firstExplanation = (int)(log.getTime().getTime() - this.startTime.getTime()) / 60000;
            }
            this.numOfExplanations = this.numOfExplanations + 1;
            this.listOfExplanations.addElement(add);
        }
        if (event == 12 || event == 11) {
            if (this.firstFolder == -1) {
                this.firstFolder = (int)(log.getTime().getTime() - this.startTime.getTime()) / 60000;
            }
            this.numOfExpansions = this.numOfExpansions + 1;
        }
        if (event == 7) {
            this.numOfAddBreakPoints = this.numOfAddBreakPoints + 1;
            this.listOfAddBreakPoints.addElement(add);
        }
        if (event == 8) {
            this.numOfDelBreakPoints = this.numOfDelBreakPoints + 1;
        }
        if (event == 14 && this.currentRun != null) {
            this.currentRun.setEndSpeed(this.speed);
            this.currentRun.setExitMode(1);
        }
        if (event == 15 && this.currentRun != null) {
            this.currentRun.setEndSpeed(this.speed);
        }
        if (event == 20 && this.currentRun != null) {
            if (Logger.DEBUG) {
                System.err.println("Setting run log as complete");
                System.err.println("hiddenLines= " + this.currentRun.getHiddenLines());
                System.err.println("nsteps = " + this.currentRun.getNSteps());
            }
            this.currentRun.setEndSpeed(this.speed);
            this.currentRun.setExitMode(2);
        }
    }
    
    static {
        Logger.datefmt = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Logger.OUTPUTADDINFO = true;
        Logger.DEBUG = false;
        Logger.DEBUG_NOSERVER = false;
        final TimeZone tz = new SimpleTimeZone(36000000, "Australia/Melbourne", 9, -1, 1, 7200000, 2, -1, 1, 7200000);
        Logger.datefmt.setTimeZone(tz);
    }
}
