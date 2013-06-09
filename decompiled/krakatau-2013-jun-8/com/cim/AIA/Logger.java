package com.cim.AIA;

public class Logger {
    final protected static int TEXT_OUTPUT = 1;
    final protected static int XML_OUTPUT = 2;
    final protected static String TEXT_CGISCRIPT = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog.cgi";
    final protected static String XML_CGISCRIPT = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog2.pl";
    final protected static String LOCAL_LOGFILE = "aiatest.log";
    final protected static int TMGRAN = 60000;
    public static java.text.DateFormat datefmt;
    final public static String AIASERVER_TIMEZONE = "Australia/Melbourne";
    protected static boolean OUTPUTADDINFO;
    final private static int SPEEDMODERATOR = 40;
    public static boolean DEBUG;
    protected static boolean DEBUG_NOSERVER;
    protected String userName;
    protected java.util.Date startTime;
    protected java.util.Date endTime;
    protected int numOfBacks;
    protected int numOfSteps;
    protected int numOfRuns;
    protected int numOfExplanations;
    protected int numOfExpansions;
    protected int numOfDataSelections;
    protected int numOfAddBreakPoints;
    protected int numOfDelBreakPoints;
    protected java.util.Vector listOfExplanations;
    protected java.util.Vector listOfBacks;
    protected java.util.Vector listOfAddBreakPoints;
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
    protected java.util.Vector logs;
    protected boolean dumpTrace;
    protected boolean enabled;
    protected boolean doLog;
    protected boolean doBegin;
    protected int output_mode;
    protected String cgiscript;
    protected String logdir;
    protected boolean urlencode;
    protected com.cim.AIA.RunLog currentRun;
    protected int speed;
    protected String userPW;
    
    public Logger()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.listOfExplanations = a;
        java.util.Vector a0 = new java.util.Vector();
        this.listOfBacks = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.listOfAddBreakPoints = a1;
        this.XMLLOG_VERSION = "1.0.4";
        this.doBuffering = false;
        this.bufferSize = 300;
        this.currentSize = 0;
        this.currentFile = null;
        java.util.Vector a2 = new java.util.Vector();
        this.logs = a2;
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
    
    public static void setLogAddInfo(boolean b)
    {
        com.cim.AIA.Logger.OUTPUTADDINFO = b;
    }
    
    public static String stripDecimal(float f, int i)
    {
        String s = null;
        String s0 = Float.toString(f);
        int i0 = s0.equals((Object)"Infinity")?1:0;
        label2: {
            label1: {
                label0: {
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    int i1 = s0.equals((Object)"NaN")?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                s = s0;
                break label2;
            }
            int i2 = 0;
            while(true)
            {
                int i3 = s0.charAt(i2);
                if(i3 == 46)
                {
                    break;
                }
                else
                {
                    int i4 = i2 + 1;
                    i2 = i4;
                }
            }
            int i5 = i2 + 1;
            int i6 = i;
            int i7 = i5;
            while(true)
            {
                int i8 = s0.length();
                if(i7 >= i8)
                {
                    break;
                }
                if(i6 <= 0)
                {
                    break;
                }
                else
                {
                    int i9 = i7 + 1;
                    int i10 = i6 + -1;
                    i6 = i10;
                    i7 = i9;
                }
            }
            String s1 = s0.substring(0, i7);
            int i11 = i6;
            String s2 = s1;
            while(true)
            {
                if(i11 == 0)
                {
                    break;
                }
                else
                {
                    StringBuilder a = new StringBuilder();
                    StringBuilder a0 = a.append(s2);
                    StringBuilder a1 = a0.append("0");
                    String s3 = a1.toString();
                    int i12 = i11 + -1;
                    i11 = i12;
                    s2 = s3;
                }
            }
            s = s2;
        }
        return s;
    }
    
    public synchronized void addLog(com.cim.AIA.Log a)
    {
        int i = this.doLog?1:0;
        label2: {
            label1: {
                label0: {
                    if(i == 0)
                    {
                        break label0;
                    }
                    int i0 = this.enabled?1:0;
                    if(i0 != 0)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            int i1 = this.currentSize;
            int i2 = i1 + 1;
            this.currentSize = i2;
            int i3 = this.output_mode;
            label4: {
                label3: {
                    if(i3 != 2)
                    {
                        break label3;
                    }
                    int i4 = a.isXMLLogged()?1:0;
                    if(i4 == 0)
                    {
                        break label4;
                    }
                }
                java.util.Vector a0 = this.logs;
                a0.addElement((Object)a);
            }
            int i5 = a.tclass;
            int i6 = a.event;
            String s = a.addInfo;
            this.sideLine((byte)i5, (byte)i6, s, a);
            int i7 = this.doBuffering?1:0;
            label5: {
                if(i7 == 0)
                {
                    break label5;
                }
                int i8 = this.currentSize;
                int i9 = this.bufferSize;
                if(i8 >= i9)
                {
                    String s0 = this.currentFile;
                    String s1 = this.dumpToServer(s0);
                    this.currentFile = s1;
                    this.currentSize = 0;
                    java.util.Vector a1 = new java.util.Vector();
                    this.logs = a1;
                }
            }
        }
    }
    
    public void beginGuestLogging(String s, String s0)
    {
        int i = this.enabled?1:0;
        if(i != 0)
        {
            this.doLog = false;
            this.enabled = false;
            this.dumpGuestToServer(s, s0);
        }
    }
    
    public void beginLogging()
    {
        this.beginLogging("Unknown Algorithm");
    }
    
    public void beginLogging(String s)
    {
        this.beginLogging(s, "aia");
    }
    
    public void beginLogging(String s, String s0)
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            this.doLog = true;
            this.userName = s0;
            this.currentSize = 0;
            java.util.Vector a = new java.util.Vector();
            this.logs = a;
            this.clearSummary();
            com.cim.AIA.Log a0 = new com.cim.AIA.Log((byte)2, (byte)1, (String)null, s);
            this.addLog(a0);
            this.doBegin = true;
            int i0 = this.output_mode;
            label2: {
                if(i0 == 2)
                {
                    break label2;
                }
                int i1 = this.dumpTrace?1:0;
                if(i1 != 0)
                {
                    this.currentFile = "NewFile";
                    String s1 = this.currentFile;
                    String s2 = this.dumpToServer(s1);
                    this.currentFile = s2;
                }
                else
                {
                    this.currentFile = "NoFile";
                    this.dumpTrace = true;
                    String s3 = this.currentFile;
                    String s4 = this.dumpToServer(s3);
                    this.currentFile = s4;
                    this.dumpTrace = false;
                }
                this.currentSize = 0;
                java.util.Vector a1 = new java.util.Vector();
                this.logs = a1;
            }
        }
    }
    
    public void beginLogging(String s, String s0, String s1)
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            this.doLog = true;
            this.userName = s0;
            this.userPW = s1;
            this.currentSize = 0;
            java.util.Vector a = new java.util.Vector();
            this.logs = a;
            this.clearSummary();
            com.cim.AIA.Log a0 = new com.cim.AIA.Log((byte)2, (byte)1, (String)null, s);
            this.addLog(a0);
            this.doBegin = true;
            int i0 = this.output_mode;
            label2: {
                if(i0 == 2)
                {
                    break label2;
                }
                int i1 = this.dumpTrace?1:0;
                if(i1 != 0)
                {
                    this.currentFile = "NewFile";
                    String s2 = this.currentFile;
                    String s3 = this.dumpToServer(s2);
                    this.currentFile = s3;
                }
                else
                {
                    this.currentFile = "NoFile";
                    this.dumpTrace = true;
                    String s4 = this.currentFile;
                    String s5 = this.dumpToServer(s4);
                    this.currentFile = s5;
                    this.dumpTrace = false;
                }
                this.currentSize = 0;
                java.util.Vector a1 = new java.util.Vector();
                this.logs = a1;
            }
        }
    }
    
    public void clearSummary()
    {
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
        java.util.Vector a = new java.util.Vector();
        this.listOfExplanations = a;
        java.util.Vector a0 = new java.util.Vector();
        this.listOfBacks = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.listOfAddBreakPoints = a1;
        this.nameOfAlgorithm = null;
        this.lastDisplay = null;
        this.totalDisplays = 0;
        this.totalSteps = 0;
        this.isOnStep = false;
    }
    
    private synchronized void dumpGuestToServer(String s, String s0)
    {
        int i = com.cim.AIA.Logger.DEBUG?1:0;
        if(i != 0)
        {
            java.io.PrintStream a = System.err;
            a.println("in Logger.dumpGuestToServer()");
        }
        try
        {
            try
            {
                String s1 = this.cgiscript;
                java.net.URL a0 = new java.net.URL(s1);
                java.net.URLConnection a1 = a0.openConnection();
                a1.setDoOutput(true);
                a1.setAllowUserInteraction(true);
                a1.setUseCaches(false);
                int i0 = this.output_mode;
                if(i0 != 2)
                {
                    java.io.OutputStream a2 = a1.getOutputStream();
                    java.io.PrintStream a3 = new java.io.PrintStream(a2);
                    a3.print("AIA Log\n");
                    a3.print("Guest\n");
                    a3.print("NoFile\n");
                    StringBuilder a4 = new StringBuilder();
                    StringBuilder a5 = a4.append(s);
                    StringBuilder a6 = a5.append(": ");
                    StringBuilder a7 = a6.append(s0);
                    StringBuilder a8 = a7.append((char)10);
                    String s2 = a8.toString();
                    a3.print(s2);
                    a3.close();
                }
                else
                {
                    a1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    java.io.OutputStream a9 = a1.getOutputStream();
                    java.io.DataOutputStream a10 = new java.io.DataOutputStream(a9);
                    StringBuffer a11 = new StringBuffer();
                    StringBuilder a12 = new StringBuilder();
                    StringBuilder a13 = a12.append("user=");
                    String s3 = java.net.URLEncoder.encode(s);
                    StringBuilder a14 = a13.append(s3);
                    String s4 = a14.toString();
                    StringBuffer a15 = a11.append(s4);
                    StringBuilder a16 = new StringBuilder();
                    StringBuilder a17 = a16.append("&algorithm=");
                    String s5 = java.net.URLEncoder.encode(s0);
                    StringBuilder a18 = a17.append(s5);
                    String s6 = a18.toString();
                    StringBuffer a19 = a11.append(s6);
                    String s7 = a11.toString();
                    a10.writeBytes(s7);
                    a10.close();
                }
                java.io.InputStream a20 = a1.getInputStream();
                java.io.DataInputStream a21 = new java.io.DataInputStream(a20);
                while(true)
                {
                    String s8 = a21.readLine();
                    if(s8 == null)
                    {
                        break;
                    }
                    else
                    {
                    }
                }
                a21.close();
            }
            catch(SecurityException ignoredException)
            {
                java.io.PrintStream a22 = System.out;
                a22.println("AiA WARNING: No logging because of non local server");
                java.io.PrintStream a23 = System.out;
                a23.println("             use NOLOG=true to avoid this warning");
                java.io.PrintStream a24 = System.out;
                a24.println("             Disabling Logging");
                this.pause();
                this.enabled = false;
            }
        }
        catch(java.io.IOException a25)
        {
            java.io.PrintStream a26 = System.out;
            StringBuilder a27 = new StringBuilder();
            StringBuilder a28 = a27.append("Exception: ");
            StringBuilder a29 = a28.append((Object)a25);
            String s9 = a29.toString();
            a26.println(s9);
        }
    }
    
    public void dumpLog()
    {
        java.io.PrintStream a = System.out;
        a.println("Dumping log....");
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.logs;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.logs;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.Log dummy = (com.cim.AIA.Log)a2;
                com.cim.AIA.Log a3 = (com.cim.AIA.Log)a2;
                a3.print();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void dumpOutput()
    {
        java.io.PrintStream a = System.out;
        a.println("Dumping output....");
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.logs;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.logs;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.Log dummy = (com.cim.AIA.Log)a2;
                com.cim.AIA.Log a3 = (com.cim.AIA.Log)a2;
                java.io.PrintStream a4 = System.out;
                int i1 = this.output_mode;
                String s = a3.output(i1);
                a4.print(s);
                int i2 = i + 1;
                i = i2;
            }
        }
    }
    
    public void dumpSummary()
    {
        java.io.PrintStream a = System.out;
        a.println("Dumping Summary....");
        java.io.PrintStream a0 = System.out;
        StringBuilder a1 = new StringBuilder();
        StringBuilder a2 = a1.append("Summary for ");
        String s = this.nameOfAlgorithm;
        StringBuilder a3 = a2.append(s);
        String s0 = a3.toString();
        a0.println(s0);
        java.io.PrintStream a4 = System.out;
        a4.println("-----------");
        java.util.Date a5 = this.endTime;
        long j = a5.getTime();
        java.util.Date a6 = this.startTime;
        long j0 = a6.getTime();
        long j1 = j - j0;
        long j2 = j1 / 60000L;
        java.io.PrintStream a7 = System.out;
        StringBuilder a8 = new StringBuilder();
        StringBuilder a9 = a8.append("Duration(min): ");
        StringBuilder a10 = a9.append(j2);
        String s1 = a10.toString();
        a7.println(s1);
        java.io.PrintStream a11 = System.out;
        StringBuilder a12 = new StringBuilder();
        StringBuilder a13 = a12.append("StepClicks/RunClicks: ");
        int i = this.numOfSteps;
        StringBuilder a14 = a13.append(i);
        StringBuilder a15 = a14.append("/");
        int i0 = this.numOfRuns;
        StringBuilder a16 = a15.append(i0);
        StringBuilder a17 = a16.append("(");
        int i1 = this.numOfSteps;
        float f = (float)i1;
        int i2 = this.numOfRuns;
        float f0 = (float)i2;
        float f1 = f / f0;
        String s2 = com.cim.AIA.Logger.stripDecimal(f1, 2);
        StringBuilder a18 = a17.append(s2);
        StringBuilder a19 = a18.append(")");
        String s3 = a19.toString();
        a11.println(s3);
        java.io.PrintStream a20 = System.out;
        StringBuilder a21 = new StringBuilder();
        StringBuilder a22 = a21.append("Backs: ");
        int i3 = this.numOfBacks;
        StringBuilder a23 = a22.append(i3);
        String s4 = a23.toString();
        a20.println(s4);
        java.io.PrintStream a24 = System.out;
        StringBuilder a25 = new StringBuilder();
        StringBuilder a26 = a25.append("Displays/Steps: ");
        int i4 = this.totalDisplays;
        StringBuilder a27 = a26.append(i4);
        StringBuilder a28 = a27.append("/");
        int i5 = this.totalSteps;
        StringBuilder a29 = a28.append(i5);
        StringBuilder a30 = a29.append("(");
        int i6 = this.totalDisplays;
        float f2 = (float)i6;
        int i7 = this.totalSteps;
        float f3 = (float)i7;
        float f4 = f2 / f3;
        String s5 = com.cim.AIA.Logger.stripDecimal(f4, 2);
        StringBuilder a31 = a30.append(s5);
        StringBuilder a32 = a31.append(")");
        String s6 = a32.toString();
        a24.println(s6);
        java.io.PrintStream a33 = System.out;
        StringBuilder a34 = new StringBuilder();
        StringBuilder a35 = a34.append("Folders: ");
        int i8 = this.numOfExpansions;
        StringBuilder a36 = a35.append(i8);
        String s7 = a36.toString();
        a33.println(s7);
        java.io.PrintStream a37 = System.out;
        StringBuilder a38 = new StringBuilder();
        StringBuilder a39 = a38.append("Explanations: ");
        int i9 = this.numOfExplanations;
        StringBuilder a40 = a39.append(i9);
        String s8 = a40.toString();
        a37.println(s8);
        java.io.PrintStream a41 = System.out;
        StringBuilder a42 = new StringBuilder();
        StringBuilder a43 = a42.append("Data Selection: ");
        int i10 = this.numOfDataSelections;
        StringBuilder a44 = a43.append(i10);
        String s9 = a44.toString();
        a41.println(s9);
        java.io.PrintStream a45 = System.out;
        StringBuilder a46 = new StringBuilder();
        StringBuilder a47 = a46.append("First Step: ");
        int i11 = this.firstStep;
        StringBuilder a48 = a47.append(i11);
        String s10 = a48.toString();
        a45.println(s10);
        java.io.PrintStream a49 = System.out;
        StringBuilder a50 = new StringBuilder();
        StringBuilder a51 = a50.append("First Back: ");
        int i12 = this.firstBack;
        StringBuilder a52 = a51.append(i12);
        String s11 = a52.toString();
        a49.println(s11);
        java.io.PrintStream a53 = System.out;
        StringBuilder a54 = new StringBuilder();
        StringBuilder a55 = a54.append("First Run: ");
        int i13 = this.firstRun;
        StringBuilder a56 = a55.append(i13);
        String s12 = a56.toString();
        a53.println(s12);
        java.io.PrintStream a57 = System.out;
        StringBuilder a58 = new StringBuilder();
        StringBuilder a59 = a58.append("First Explanation: ");
        int i14 = this.firstExplanation;
        StringBuilder a60 = a59.append(i14);
        String s13 = a60.toString();
        a57.println(s13);
        java.io.PrintStream a61 = System.out;
        StringBuilder a62 = new StringBuilder();
        StringBuilder a63 = a62.append("First Data Selection: ");
        int i15 = this.firstDataSelection;
        StringBuilder a64 = a63.append(i15);
        String s14 = a64.toString();
        a61.println(s14);
        java.io.PrintStream a65 = System.out;
        StringBuilder a66 = new StringBuilder();
        StringBuilder a67 = a66.append("First Folder: ");
        int i16 = this.firstFolder;
        StringBuilder a68 = a67.append(i16);
        String s15 = a68.toString();
        a65.println(s15);
        java.io.PrintStream a69 = System.out;
        a69.println("Explanations List: ");
        int i17 = 0;
        while(true)
        {
            java.util.Vector a70 = this.listOfExplanations;
            int i18 = a70.size();
            if(i17 >= i18)
            {
                break;
            }
            else
            {
                java.io.PrintStream a71 = System.out;
                StringBuilder a72 = new StringBuilder();
                StringBuilder a73 = a72.append("    ");
                java.util.Vector a74 = this.listOfExplanations;
                Object a75 = a74.elementAt(i17);
                String dummy = (String)a75;
                String s16 = (String)a75;
                StringBuilder a76 = a73.append(s16);
                String s17 = a76.toString();
                a71.println(s17);
                int i19 = i17 + 1;
                i17 = i19;
            }
        }
        java.io.PrintStream a77 = System.out;
        a77.println("Back List: ");
        int i20 = 0;
        while(true)
        {
            java.util.Vector a78 = this.listOfBacks;
            int i21 = a78.size();
            if(i20 >= i21)
            {
                return;
            }
            else
            {
                java.io.PrintStream a79 = System.out;
                StringBuilder a80 = new StringBuilder();
                StringBuilder a81 = a80.append("    ");
                java.util.Vector a82 = this.listOfBacks;
                Object a83 = a82.elementAt(i20);
                String dummy0 = (String)a83;
                String s18 = (String)a83;
                StringBuilder a84 = a81.append(s18);
                String s19 = a84.toString();
                a79.println(s19);
                int i22 = i20 + 1;
                i20 = i22;
            }
        }
    }
    
    private synchronized String dumpSummaryToServer(String s)
    {
        String s0 = null;
        label0: {
            try
            {
                try
                {
                    try
                    {
                        String s1 = this.cgiscript;
                        java.net.URL a = new java.net.URL(s1);
                        java.net.URLConnection a0 = a.openConnection();
                        a0.setDoOutput(true);
                        a0.setAllowUserInteraction(true);
                        a0.setUseCaches(false);
                        int i = this.output_mode;
                        switch(i){
                            case 2: {
                                String s2 = this.dumpXMLSummary(a0);
                                s0 = s2;
                                break label0;
                            }
                            case 1: {
                                String s3 = this.dumpTextSummary(a0, s);
                                s0 = s3;
                                break label0;
                            }
                        }
                    }
                    catch(java.net.MalformedURLException a1)
                    {
                        java.io.PrintStream a2 = System.out;
                        StringBuilder a3 = new StringBuilder();
                        StringBuilder a4 = a3.append("Exception: ");
                        StringBuilder a5 = a4.append((Object)a1);
                        String s4 = a5.toString();
                        a2.println(s4);
                    }
                }
                catch(SecurityException ignoredException)
                {
                    java.io.PrintStream a6 = System.out;
                    a6.println("AiA WARNING: No logging because of non local server");
                    java.io.PrintStream a7 = System.out;
                    a7.println("             use NOLOG=true to avoid this warning");
                    java.io.PrintStream a8 = System.out;
                    a8.println("             Disabling Logging");
                    this.pause();
                    this.enabled = false;
                }
            }
            catch(java.io.IOException a9)
            {
                java.io.PrintStream a10 = System.out;
                StringBuilder a11 = new StringBuilder();
                StringBuilder a12 = a11.append("Exception: ");
                StringBuilder a13 = a12.append((Object)a9);
                String s5 = a13.toString();
                a10.println(s5);
            }
            s0 = "NewFile";
        }
        return s0;
    }
    
    private synchronized String dumpText(java.net.URLConnection a, String s)
    {
        java.io.OutputStream a0 = a.getOutputStream();
        java.io.PrintStream a1 = new java.io.PrintStream(a0);
        a1.print("AIA Log\n");
        int i = this.doBegin?1:0;
        if(i != 0)
        {
            a1.print("Begin\n");
            this.doBegin = false;
        }
        StringBuilder a2 = new StringBuilder();
        StringBuilder a3 = a2.append(s);
        StringBuilder a4 = a3.append((char)10);
        String s0 = a4.toString();
        a1.print(s0);
        int i0 = 0;
        while(true)
        {
            java.util.Vector a5 = this.logs;
            int i1 = a5.size();
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                java.util.Vector a6 = this.logs;
                Object a7 = a6.elementAt(i0);
                com.cim.AIA.Log dummy = (com.cim.AIA.Log)a7;
                com.cim.AIA.Log a8 = (com.cim.AIA.Log)a7;
                int i2 = this.output_mode;
                String s1 = a8.output(i2);
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
        a1.close();
        java.io.InputStream a9 = a.getInputStream();
        java.io.DataInputStream a10 = new java.io.DataInputStream(a9);
        String s2 = null;
        while(true)
        {
            String s3 = a10.readLine();
            if(s3 == null)
            {
                break;
            }
            else
            {
                s2 = s3;
            }
        }
        a10.close();
        String s4 = s2 == null?"NewFile":s2;
        return s4;
    }
    
    private synchronized String dumpTextSummary(java.net.URLConnection a, String s)
    {
        java.io.OutputStream a0 = a.getOutputStream();
        java.io.PrintStream a1 = new java.io.PrintStream(a0);
        a1.print("AIA Log\n");
        a1.print("Summary\n");
        StringBuilder a2 = new StringBuilder();
        StringBuilder a3 = a2.append(s);
        StringBuilder a4 = a3.append((char)10);
        String s0 = a4.toString();
        a1.print(s0);
        StringBuilder a5 = new StringBuilder();
        StringBuilder a6 = a5.append("User: ");
        String s1 = this.userName;
        StringBuilder a7 = a6.append(s1);
        StringBuilder a8 = a7.append((char)10);
        String s2 = a8.toString();
        a1.print(s2);
        StringBuilder a9 = new StringBuilder();
        StringBuilder a10 = a9.append("Summary for ");
        String s3 = this.nameOfAlgorithm;
        StringBuilder a11 = a10.append(s3);
        StringBuilder a12 = a11.append((char)10);
        String s4 = a12.toString();
        a1.print(s4);
        java.util.Date a13 = this.endTime;
        long j = a13.getTime();
        java.util.Date a14 = this.startTime;
        long j0 = a14.getTime();
        long j1 = j - j0;
        long j2 = j1 / 60000L;
        StringBuilder a15 = new StringBuilder();
        StringBuilder a16 = a15.append("Duration(min): ");
        StringBuilder a17 = a16.append(j2);
        StringBuilder a18 = a17.append((char)10);
        String s5 = a18.toString();
        a1.print(s5);
        StringBuilder a19 = new StringBuilder();
        StringBuilder a20 = a19.append("StepClicks/RunClicks: ");
        int i = this.numOfSteps;
        StringBuilder a21 = a20.append(i);
        StringBuilder a22 = a21.append("/");
        int i0 = this.numOfRuns;
        StringBuilder a23 = a22.append(i0);
        StringBuilder a24 = a23.append(" ");
        int i1 = this.numOfSteps;
        float f = (float)i1;
        int i2 = this.numOfRuns;
        float f0 = (float)i2;
        float f1 = f / f0;
        String s6 = com.cim.AIA.Logger.stripDecimal(f1, 2);
        StringBuilder a25 = a24.append(s6);
        StringBuilder a26 = a25.append("");
        StringBuilder a27 = a26.append((char)10);
        String s7 = a27.toString();
        a1.print(s7);
        StringBuilder a28 = new StringBuilder();
        StringBuilder a29 = a28.append("Backs: ");
        int i3 = this.numOfBacks;
        StringBuilder a30 = a29.append(i3);
        StringBuilder a31 = a30.append((char)10);
        String s8 = a31.toString();
        a1.print(s8);
        StringBuilder a32 = new StringBuilder();
        StringBuilder a33 = a32.append("Displays/Steps: ");
        int i4 = this.totalDisplays;
        StringBuilder a34 = a33.append(i4);
        StringBuilder a35 = a34.append("/");
        int i5 = this.totalSteps;
        StringBuilder a36 = a35.append(i5);
        StringBuilder a37 = a36.append(" ");
        int i6 = this.totalDisplays;
        float f2 = (float)i6;
        int i7 = this.totalSteps;
        float f3 = (float)i7;
        float f4 = f2 / f3;
        String s9 = com.cim.AIA.Logger.stripDecimal(f4, 2);
        StringBuilder a38 = a37.append(s9);
        StringBuilder a39 = a38.append("");
        StringBuilder a40 = a39.append((char)10);
        String s10 = a40.toString();
        a1.print(s10);
        StringBuilder a41 = new StringBuilder();
        StringBuilder a42 = a41.append("Backs/Steps: ");
        int i8 = this.numOfBacks;
        StringBuilder a43 = a42.append(i8);
        StringBuilder a44 = a43.append("/");
        int i9 = this.totalSteps;
        StringBuilder a45 = a44.append(i9);
        StringBuilder a46 = a45.append(" ");
        int i10 = this.numOfBacks;
        float f5 = (float)i10;
        int i11 = this.totalSteps;
        float f6 = (float)i11;
        float f7 = f5 / f6;
        String s11 = com.cim.AIA.Logger.stripDecimal(f7, 2);
        StringBuilder a47 = a46.append(s11);
        StringBuilder a48 = a47.append("");
        StringBuilder a49 = a48.append((char)10);
        String s12 = a49.toString();
        a1.print(s12);
        StringBuilder a50 = new StringBuilder();
        StringBuilder a51 = a50.append("Folders: ");
        int i12 = this.numOfExpansions;
        StringBuilder a52 = a51.append(i12);
        StringBuilder a53 = a52.append((char)10);
        String s13 = a53.toString();
        a1.print(s13);
        StringBuilder a54 = new StringBuilder();
        StringBuilder a55 = a54.append("Explanations: ");
        int i13 = this.numOfExplanations;
        StringBuilder a56 = a55.append(i13);
        StringBuilder a57 = a56.append((char)10);
        String s14 = a57.toString();
        a1.print(s14);
        StringBuilder a58 = new StringBuilder();
        StringBuilder a59 = a58.append("Data Selection: ");
        int i14 = this.numOfDataSelections;
        StringBuilder a60 = a59.append(i14);
        StringBuilder a61 = a60.append((char)10);
        String s15 = a61.toString();
        a1.print(s15);
        StringBuilder a62 = new StringBuilder();
        StringBuilder a63 = a62.append("Add Breakpoints: ");
        int i15 = this.numOfAddBreakPoints;
        StringBuilder a64 = a63.append(i15);
        StringBuilder a65 = a64.append((char)10);
        String s16 = a65.toString();
        a1.print(s16);
        StringBuilder a66 = new StringBuilder();
        StringBuilder a67 = a66.append("Del Breakpoints: ");
        int i16 = this.numOfDelBreakPoints;
        StringBuilder a68 = a67.append(i16);
        StringBuilder a69 = a68.append((char)10);
        String s17 = a69.toString();
        a1.print(s17);
        StringBuilder a70 = new StringBuilder();
        StringBuilder a71 = a70.append("First Step: ");
        int i17 = this.firstStep;
        StringBuilder a72 = a71.append(i17);
        StringBuilder a73 = a72.append((char)10);
        String s18 = a73.toString();
        a1.print(s18);
        StringBuilder a74 = new StringBuilder();
        StringBuilder a75 = a74.append("First Back: ");
        int i18 = this.firstBack;
        StringBuilder a76 = a75.append(i18);
        StringBuilder a77 = a76.append((char)10);
        String s19 = a77.toString();
        a1.print(s19);
        StringBuilder a78 = new StringBuilder();
        StringBuilder a79 = a78.append("First Run: ");
        int i19 = this.firstRun;
        StringBuilder a80 = a79.append(i19);
        StringBuilder a81 = a80.append((char)10);
        String s20 = a81.toString();
        a1.print(s20);
        StringBuilder a82 = new StringBuilder();
        StringBuilder a83 = a82.append("First Explanation: ");
        int i20 = this.firstExplanation;
        StringBuilder a84 = a83.append(i20);
        StringBuilder a85 = a84.append((char)10);
        String s21 = a85.toString();
        a1.print(s21);
        StringBuilder a86 = new StringBuilder();
        StringBuilder a87 = a86.append("First Data Selection: ");
        int i21 = this.firstDataSelection;
        StringBuilder a88 = a87.append(i21);
        StringBuilder a89 = a88.append((char)10);
        String s22 = a89.toString();
        a1.print(s22);
        StringBuilder a90 = new StringBuilder();
        StringBuilder a91 = a90.append("First Folder: ");
        int i22 = this.firstFolder;
        StringBuilder a92 = a91.append(i22);
        StringBuilder a93 = a92.append((char)10);
        String s23 = a93.toString();
        a1.print(s23);
        a1.print("Explanations List: \n");
        int i23 = 0;
        while(true)
        {
            java.util.Vector a94 = this.listOfExplanations;
            int i24 = a94.size();
            if(i23 >= i24)
            {
                break;
            }
            else
            {
                StringBuilder a95 = new StringBuilder();
                StringBuilder a96 = a95.append("    ");
                java.util.Vector a97 = this.listOfExplanations;
                Object a98 = a97.elementAt(i23);
                String dummy = (String)a98;
                String s24 = (String)a98;
                StringBuilder a99 = a96.append(s24);
                StringBuilder a100 = a99.append((char)10);
                String s25 = a100.toString();
                a1.print(s25);
                int i25 = i23 + 1;
                i23 = i25;
            }
        }
        a1.print("Back List: \n");
        int i26 = 0;
        while(true)
        {
            java.util.Vector a101 = this.listOfBacks;
            int i27 = a101.size();
            if(i26 >= i27)
            {
                break;
            }
            else
            {
                StringBuilder a102 = new StringBuilder();
                StringBuilder a103 = a102.append("    ");
                java.util.Vector a104 = this.listOfBacks;
                Object a105 = a104.elementAt(i26);
                String dummy0 = (String)a105;
                String s26 = (String)a105;
                StringBuilder a106 = a103.append(s26);
                StringBuilder a107 = a106.append((char)10);
                String s27 = a107.toString();
                a1.print(s27);
                int i28 = i26 + 1;
                i26 = i28;
            }
        }
        a1.print("Break Point List: \n");
        int i29 = 0;
        while(true)
        {
            java.util.Vector a108 = this.listOfAddBreakPoints;
            int i30 = a108.size();
            if(i29 >= i30)
            {
                break;
            }
            else
            {
                StringBuilder a109 = new StringBuilder();
                StringBuilder a110 = a109.append("    ");
                java.util.Vector a111 = this.listOfAddBreakPoints;
                Object a112 = a111.elementAt(i29);
                String dummy1 = (String)a112;
                String s28 = (String)a112;
                StringBuilder a113 = a110.append(s28);
                StringBuilder a114 = a113.append((char)10);
                String s29 = a114.toString();
                a1.print(s29);
                int i31 = i29 + 1;
                i29 = i31;
            }
        }
        a1.close();
        java.io.InputStream a115 = a.getInputStream();
        java.io.DataInputStream a116 = new java.io.DataInputStream(a115);
        String s30 = null;
        while(true)
        {
            String s31 = a116.readLine();
            if(s31 == null)
            {
                break;
            }
            else
            {
                s30 = s31;
            }
        }
        a116.close();
        String s32 = s30 == null?"NewFile":s30;
        return s32;
    }
    
    private synchronized String dumpToServer(String s)
    {
        String s0 = null;
        int i = com.cim.AIA.Logger.DEBUG?1:0;
        if(i != 0)
        {
            java.io.PrintStream a = System.err;
            StringBuilder a0 = new StringBuilder();
            StringBuilder a1 = a0.append("Dumping to server.  Filename: ");
            StringBuilder a2 = a1.append(s);
            String s1 = a2.toString();
            a.println(s1);
        }
        int i0 = this.dumpTrace?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                s0 = s;
                break label1;
            }
            label3: {
                SecurityException a3 = null;
                try
                {
                    try
                    {
                        java.net.URLConnection a4 = null;
                        int i1 = com.cim.AIA.Logger.DEBUG_NOSERVER?1:0;
                        label2: {
                            java.net.URLConnection a5 = null;
                            if(i1 != 0)
                            {
                                a4 = a5;
                                break label2;
                            }
                            String s2 = this.cgiscript;
                            java.net.URL a6 = new java.net.URL(s2);
                            java.net.URLConnection a7 = a6.openConnection();
                            int i2 = com.cim.AIA.Logger.DEBUG?1:0;
                            if(i2 != 0)
                            {
                                java.io.PrintStream a8 = System.err;
                                StringBuilder a9 = new StringBuilder();
                                StringBuilder a10 = a9.append("Calling CGI script");
                                String s3 = this.cgiscript;
                                StringBuilder a11 = a10.append(s3);
                                String s4 = a11.toString();
                                a8.println(s4);
                            }
                            a7.setDoOutput(true);
                            a7.setAllowUserInteraction(true);
                            a7.setUseCaches(false);
                            a4 = a7;
                        }
                        int i3 = this.output_mode;
                        switch(i3){
                            case 2: {
                                String s5 = this.dumpXML(a4);
                                s0 = s5;
                                break label1;
                            }
                            case 1: {
                                String s6 = this.dumpText(a4, s);
                                s0 = s6;
                                break label1;
                            }
                        }
                    }
                    catch(java.io.IOException a12)
                    {
                        java.io.PrintStream a13 = System.out;
                        StringBuilder a14 = new StringBuilder();
                        StringBuilder a15 = a14.append("Exception: ");
                        StringBuilder a16 = a15.append((Object)a12);
                        String s7 = a16.toString();
                        a13.println(s7);
                        break label3;
                    }
                }
                catch(SecurityException a17)
                {
                    a3 = a17;
                }
                int i4 = com.cim.AIA.Logger.DEBUG?1:0;
                if(i4 != 0)
                {
                    a3.printStackTrace();
                }
                java.io.PrintStream a18 = System.out;
                a18.println("AiA WARNING: No logging because of non local server");
                java.io.PrintStream a19 = System.out;
                a19.println("             use NOLOG=true to avoid this warning");
                java.io.PrintStream a20 = System.out;
                a20.println("             Disabling Logging");
                this.pause();
                this.enabled = false;
            }
            s0 = "NewFile";
        }
        return s0;
    }
    
    private synchronized String dumpXML(java.net.URLConnection a)
    {
        java.net.URLConnection a0 = null;
        java.io.DataOutputStream a1 = null;
        String s = null;
        label1: {
            label0: {
                if(a == null)
                {
                    break label0;
                }
                a.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                java.io.OutputStream a2 = a.getOutputStream();
                java.io.DataOutputStream a3 = new java.io.DataOutputStream(a2);
                a0 = a;
                a1 = a3;
                break label1;
            }
            int i = com.cim.AIA.Logger.DEBUG_NOSERVER?1:0;
            java.net.URLConnection a4 = null;
            java.io.DataOutputStream a5 = null;
            if(i == 0)
            {
                a0 = a4;
                a1 = a5;
            }
            else
            {
                java.io.PrintStream a6 = System.err;
                java.io.DataOutputStream a7 = new java.io.DataOutputStream((java.io.OutputStream)a6);
                a0 = null;
                a1 = a7;
            }
        }
        StringBuffer a8 = new StringBuffer();
        int i0 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i0 != 0)
        {
            java.io.PrintStream a9 = System.err;
            StringBuilder a10 = new StringBuilder();
            StringBuilder a11 = a10.append("logs.size() : ");
            java.util.Vector a12 = this.logs;
            int i1 = a12.size();
            StringBuilder a13 = a11.append(i1);
            String s0 = a13.toString();
            a9.println(s0);
        }
        StringBuilder a14 = new StringBuilder();
        StringBuilder a15 = a14.append("user=");
        String s1 = this.userName;
        String s2 = java.net.URLEncoder.encode(s1);
        StringBuilder a16 = a15.append(s2);
        String s3 = a16.toString();
        StringBuffer a17 = a8.append(s3);
        StringBuilder a18 = new StringBuilder();
        StringBuilder a19 = a18.append("&algorithm=");
        String s4 = this.nameOfAlgorithm;
        String s5 = java.net.URLEncoder.encode(s4);
        StringBuilder a20 = a19.append(s5);
        String s6 = a20.toString();
        StringBuffer a21 = a8.append(s6);
        StringBuilder a22 = new StringBuilder();
        StringBuilder a23 = a22.append("&nlogs=");
        java.util.Vector a24 = this.logs;
        int i2 = a24.size();
        StringBuilder a25 = a23.append(i2);
        String s7 = a25.toString();
        StringBuffer a26 = a8.append(s7);
        StringBuffer a27 = new StringBuffer();
        int i3 = 0;
        while(true)
        {
            java.util.Vector a28 = this.logs;
            int i4 = a28.size();
            if(i3 >= i4)
            {
                break;
            }
            else
            {
                java.util.Vector a29 = this.logs;
                Object a30 = a29.elementAt(i3);
                com.cim.AIA.Log dummy = (com.cim.AIA.Log)a30;
                com.cim.AIA.Log a31 = (com.cim.AIA.Log)a30;
                int i5 = this.output_mode;
                String s8 = a31.output(i5);
                StringBuffer a32 = a27.append(s8);
                int i6 = i3 + 1;
                i3 = i6;
            }
        }
        String s9 = a27.toString();
        StringBuilder a33 = new StringBuilder();
        StringBuilder a34 = a33.append("&log=");
        String s10 = java.net.URLEncoder.encode(s9);
        StringBuilder a35 = a34.append(s10);
        String s11 = a35.toString();
        StringBuffer a36 = a8.append(s11);
        StringBuilder a37 = new StringBuilder();
        StringBuilder a38 = a37.append("&uid=");
        String s12 = this.userPW;
        String s13 = java.net.URLEncoder.encode(s12);
        StringBuilder a39 = a38.append(s13);
        String s14 = a39.toString();
        StringBuffer a40 = a8.append(s14);
        int i7 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i7 != 0)
        {
            java.io.PrintStream a41 = System.err;
            String s15 = a8.toString();
            a41.println(s15);
        }
        String s16 = a8.toString();
        a1.writeBytes(s16);
        a1.flush();
        a1.close();
        label2: {
            String s17 = null;
            if(a0 == null)
            {
                s = s17;
                break label2;
            }
            java.io.InputStream a42 = a0.getInputStream();
            java.io.DataInputStream a43 = new java.io.DataInputStream(a42);
            String s18 = null;
            while(true)
            {
                String s19 = a43.readLine();
                if(s19 == null)
                {
                    s = s18;
                    break;
                }
                else
                {
                    s18 = s19;
                }
            }
        }
        String s20 = s != null?"XML":null;
        return s20;
    }
    
    private synchronized String dumpXMLSummary(java.net.URLConnection a)
    {
        a.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        java.io.OutputStream a0 = a.getOutputStream();
        java.io.DataOutputStream a1 = new java.io.DataOutputStream(a0);
        StringBuffer a2 = new StringBuffer();
        int i = com.cim.AIA.Logger.DEBUG?1:0;
        if(i != 0)
        {
            java.io.PrintStream a3 = System.err;
            StringBuilder a4 = new StringBuilder();
            StringBuilder a5 = a4.append("logs.size() : ");
            java.util.Vector a6 = this.logs;
            int i0 = a6.size();
            StringBuilder a7 = a5.append(i0);
            String s = a7.toString();
            a3.println(s);
        }
        StringBuilder a8 = new StringBuilder();
        StringBuilder a9 = a8.append("user=");
        String s0 = this.userName;
        String s1 = java.net.URLEncoder.encode(s0);
        StringBuilder a10 = a9.append(s1);
        String s2 = a10.toString();
        StringBuffer a11 = a2.append(s2);
        StringBuilder a12 = new StringBuilder();
        StringBuilder a13 = a12.append("&algorithm=");
        String s3 = this.nameOfAlgorithm;
        String s4 = java.net.URLEncoder.encode(s3);
        StringBuilder a14 = a13.append(s4);
        String s5 = a14.toString();
        StringBuffer a15 = a2.append(s5);
        StringBuilder a16 = new StringBuilder();
        StringBuilder a17 = a16.append("&nlogs=");
        java.util.Vector a18 = this.logs;
        int i1 = a18.size();
        StringBuilder a19 = a17.append(i1);
        String s6 = a19.toString();
        StringBuffer a20 = a2.append(s6);
        String s7 = this.logdir;
        if(s7 != null)
        {
            StringBuilder a21 = new StringBuilder();
            StringBuilder a22 = a21.append("&logdir=");
            String s8 = this.logdir;
            String s9 = java.net.URLEncoder.encode(s8);
            StringBuilder a23 = a22.append(s9);
            String s10 = a23.toString();
            StringBuffer a24 = a2.append(s10);
        }
        StringBuffer a25 = new StringBuffer();
        StringBuffer a26 = a25.append("<?xml version=\"1.0\" standalone=\"yes\" ?>\n");
        StringBuffer a27 = a25.append("<!DOCTYPE session>\n");
        StringBuilder a28 = new StringBuilder();
        StringBuilder a29 = a28.append("<session version=\"");
        String s11 = this.XMLLOG_VERSION;
        StringBuilder a30 = a29.append(s11);
        StringBuilder a31 = a30.append("\">\n");
        String s12 = a31.toString();
        StringBuffer a32 = a25.append(s12);
        StringBuilder a33 = new StringBuilder();
        StringBuilder a34 = a33.append("<userID>");
        String s13 = this.userName;
        StringBuilder a35 = a34.append(s13);
        StringBuilder a36 = a35.append("</userID>\n");
        String s14 = a36.toString();
        StringBuffer a37 = a25.append(s14);
        StringBuilder a38 = new StringBuilder();
        StringBuilder a39 = a38.append("<algorithm>");
        String s15 = this.nameOfAlgorithm;
        StringBuilder a40 = a39.append(s15);
        StringBuilder a41 = a40.append("</algorithm>\n");
        String s16 = a41.toString();
        StringBuffer a42 = a25.append(s16);
        StringBuilder a43 = new StringBuilder();
        StringBuilder a44 = a43.append("<connectingIP>");
        StringBuilder a45 = a44.append("localhost/127.0.0.1");
        StringBuilder a46 = a45.append("</connectingIP>\n");
        String s17 = a46.toString();
        StringBuffer a47 = a25.append(s17);
        StringBuilder a48 = new StringBuilder();
        StringBuilder a49 = a48.append("<starttime>");
        java.text.DateFormat a50 = com.cim.AIA.Logger.datefmt;
        java.util.Date a51 = this.startTime;
        String s18 = a50.format(a51);
        StringBuilder a52 = a49.append(s18);
        StringBuilder a53 = a52.append("</starttime>\n");
        String s19 = a53.toString();
        StringBuffer a54 = a25.append(s19);
        StringBuilder a55 = new StringBuilder();
        StringBuilder a56 = a55.append("<endtime>");
        java.text.DateFormat a57 = com.cim.AIA.Logger.datefmt;
        java.util.Date a58 = this.endTime;
        String s20 = a57.format(a58);
        StringBuilder a59 = a56.append(s20);
        StringBuilder a60 = a59.append("</endtime>\n");
        String s21 = a60.toString();
        StringBuffer a61 = a25.append(s21);
        StringBuffer a62 = a25.append("<timezone>Australia/Melbourne</timezone>\n");
        StringBuilder a63 = new StringBuilder();
        StringBuilder a64 = a63.append("<startmtime>");
        java.util.Date a65 = this.startTime;
        long j = a65.getTime();
        StringBuilder a66 = a64.append(j);
        StringBuilder a67 = a66.append("</startmtime>\n");
        String s22 = a67.toString();
        StringBuffer a68 = a25.append(s22);
        StringBuilder a69 = new StringBuilder();
        StringBuilder a70 = a69.append("<endmtime>");
        java.util.Date a71 = this.endTime;
        long j0 = a71.getTime();
        StringBuilder a72 = a70.append(j0);
        StringBuilder a73 = a72.append("</endmtime>\n");
        String s23 = a73.toString();
        StringBuffer a74 = a25.append(s23);
        int i2 = 0;
        while(true)
        {
            java.util.Vector a75 = this.logs;
            int i3 = a75.size();
            if(i2 >= i3)
            {
                break;
            }
            else
            {
                java.util.Vector a76 = this.logs;
                Object a77 = a76.elementAt(i2);
                com.cim.AIA.Log dummy = (com.cim.AIA.Log)a77;
                com.cim.AIA.Log a78 = (com.cim.AIA.Log)a77;
                int i4 = this.output_mode;
                String s24 = a78.output(i4);
                StringBuffer a79 = a25.append(s24);
                int i5 = i2 + 1;
                i2 = i5;
            }
        }
        StringBuffer a80 = a25.append("</session>\n");
        String s25 = a25.toString();
        StringBuilder a81 = new StringBuilder();
        StringBuilder a82 = a81.append("&log=");
        String s26 = java.net.URLEncoder.encode(s25);
        StringBuilder a83 = a82.append(s26);
        String s27 = a83.toString();
        StringBuffer a84 = a2.append(s27);
        StringBuilder a85 = new StringBuilder();
        StringBuilder a86 = a85.append("&uid=");
        String s28 = this.userPW;
        String s29 = java.net.URLEncoder.encode(s28);
        StringBuilder a87 = a86.append(s29);
        String s30 = a87.toString();
        StringBuffer a88 = a2.append(s30);
        int i6 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i6 != 0)
        {
            java.io.PrintStream a89 = System.err;
            String s31 = a2.toString();
            a89.println(s31);
        }
        String s32 = a2.toString();
        a1.writeBytes(s32);
        a1.flush();
        a1.close();
        java.io.InputStream a90 = a.getInputStream();
        java.io.DataInputStream a91 = new java.io.DataInputStream(a90);
        String s33 = null;
        while(true)
        {
            String s34 = a91.readLine();
            if(s34 == null)
            {
                break;
            }
            else
            {
                s33 = s34;
            }
        }
        String s35 = s33 != null?"XML":null;
        return s35;
    }
    
    public void endLogging()
    {
        int i = this.dumpTrace?1:0;
        if(i == 0)
        {
            this.dumpTrace = true;
            java.util.Vector a = new java.util.Vector();
            this.logs = a;
            this.currentSize = 0;
        }
        com.cim.AIA.Log a0 = new com.cim.AIA.Log((byte)2, (byte)2, (String)null);
        this.addLog(a0);
        this.doLog = false;
        this.endLogProcedure();
        this.enabled = false;
    }
    
    private void endLogProcedure()
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = this.output_mode;
            if(i0 != 2)
            {
                String s = this.currentFile;
                String s0 = this.dumpToServer(s);
                this.currentFile = s0;
            }
            String s1 = this.currentFile;
            String s2 = this.dumpSummaryToServer(s1);
            this.currentFile = s2;
        }
    }
    
    public boolean getDumpTrace()
    {
        int i = this.dumpTrace?1:0;
        return i != 0;
    }
    
    public boolean getEnabled()
    {
        int i = this.enabled?1:0;
        return i != 0;
    }
    
    public int getOutputMode()
    {
        int i = this.output_mode;
        return i;
    }
    
    public int getSpeed()
    {
        int i = this.speed;
        return i;
    }
    
    public void pause()
    {
        this.doLog = false;
    }
    
    public void resume()
    {
        this.doLog = true;
    }
    
    public void setBufferSize(int i)
    {
        this.bufferSize = i;
    }
    
    public void setCGIScript(String s)
    {
        this.cgiscript = s;
    }
    
    public void setDumpTrace(boolean b)
    {
        this.dumpTrace = b;
    }
    
    public void setEnabled(boolean b)
    {
        this.enabled = b;
    }
    
    public void setLogDir(String s)
    {
        this.logdir = s;
    }
    
    public void setOutputMode(int i)
    {
        this.output_mode = i;
    }
    
    public void setOutputMode(String s)
    {
        int i = 0;
        int i0 = this.output_mode;
        int i1 = s.equalsIgnoreCase("xml")?1:0;
        label1: {
            label0: {
                if(i1 == 0)
                {
                    break label0;
                }
                i = 2;
                break label1;
            }
            int i2 = s.equalsIgnoreCase("text")?1:0;
            i = i2 == 0?i0:1;
        }
        this.output_mode = i;
        switch(i){
            case 2: {
                this.cgiscript = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog2.pl";
                break;
            }
            case 1: {
                this.cgiscript = "http://ww2.cs.mu.oz.au/us/cgi-bin/aialog.cgi";
                break;
            }
        }
    }
    
    public void setSpeed(int i)
    {
        int i0 = i / 40;
        this.speed = i0;
    }
    
    public void sideLine(byte a, byte a0, String s, com.cim.AIA.Log a1)
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = a;
        int i16 = a;
        int i17 = a0;
        if(a0 != 1)
        {
            i = i16;
            i0 = i17;
        }
        else
        {
            this.nameOfAlgorithm = s;
            java.util.Date a2 = new java.util.Date();
            this.startTime = a2;
            i = i15;
            i0 = 1;
        }
        if(i0 != 2)
        {
            i1 = i0;
        }
        else
        {
            java.util.Date a3 = new java.util.Date();
            this.endTime = a3;
            i1 = 2;
        }
        int i18 = this.isOnStep?1:0;
        label0: {
            if(i18 == 0)
            {
                i2 = i;
                break label0;
            }
            if(i != 3)
            {
                i2 = i;
            }
            else
            {
                int i19 = this.totalDisplays;
                int i20 = i19 + 1;
                this.totalDisplays = i20;
                i2 = 3;
            }
        }
        if(i2 != 3)
        {
            i3 = i2;
        }
        else
        {
            this.lastDisplay = s;
            i3 = 3;
        }
        int i21 = this.isOnStep?1:0;
        label1: {
            if(i21 == 0)
            {
                break label1;
            }
            label2: {
                if(i3 == 4)
                {
                    break label2;
                }
                if(i3 != 2)
                {
                    break label1;
                }
            }
            int i22 = this.totalSteps;
            int i23 = i22 + 1;
            this.totalSteps = i23;
        }
        label3: {
            if(i1 != 3)
            {
                i4 = i1;
                break label3;
            }
            com.cim.AIA.RunLog a4 = this.currentRun;
            if(a4 == null)
            {
                i4 = 3;
                break label3;
            }
            com.cim.AIA.RunLog a5 = this.currentRun;
            a5.incrSteps();
            Class a6 = ((Object)a1).getClass();
            int i24 = a6.isAssignableFrom(com.cim.AIA.StepLog.class)?1:0;
            if(i24 == 0)
            {
                java.io.PrintStream a7 = System.err;
                a7.println("Logger.sideline(): Warning: log is not of type StepLog");
                i4 = 3;
            }
            else
            {
                com.cim.AIA.RunLog a8 = this.currentRun;
                com.cim.AIA.StepLog dummy = (com.cim.AIA.StepLog)a1;
                com.cim.AIA.StepLog a9 = (com.cim.AIA.StepLog)a1;
                int i25 = a9.getHiddenLines();
                a8.incrHiddenLines(i25);
                i4 = 3;
            }
        }
        if(i4 != 13)
        {
            i5 = i4;
        }
        else
        {
            com.cim.AIA.RunLog a10 = this.currentRun;
            i5 = 13;
        }
        label4: {
            if(i5 != 19)
            {
                i6 = i5;
                break label4;
            }
            int i26 = this.firstStep;
            if(i26 == -1)
            {
                java.util.Date a11 = a1.getTime();
                long j = a11.getTime();
                java.util.Date a12 = this.startTime;
                long j0 = a12.getTime();
                long j1 = j - j0;
                int i27 = (int)j1;
                int i28 = i27 / 60000;
                this.firstStep = i28;
            }
            int i29 = this.numOfSteps;
            int i30 = i29 + 1;
            this.numOfSteps = i30;
            this.isOnStep = true;
            i6 = 19;
        }
        label5: {
            if(i6 != 17)
            {
                i7 = i6;
                break label5;
            }
            int i31 = this.firstRun;
            if(i31 == -1)
            {
                java.util.Date a13 = a1.getTime();
                long j2 = a13.getTime();
                java.util.Date a14 = this.startTime;
                long j3 = a14.getTime();
                long j4 = j2 - j3;
                int i32 = (int)j4;
                int i33 = i32 / 60000;
                this.firstRun = i33;
            }
            int i34 = this.numOfRuns;
            int i35 = i34 + 1;
            this.numOfRuns = i35;
            this.isOnStep = true;
            com.cim.AIA.RunLog dummy0 = (com.cim.AIA.RunLog)a1;
            com.cim.AIA.RunLog a15 = (com.cim.AIA.RunLog)a1;
            int i36 = this.speed;
            a15.setStartSpeed(i36);
            com.cim.AIA.RunLog dummy1 = (com.cim.AIA.RunLog)a1;
            com.cim.AIA.RunLog a16 = (com.cim.AIA.RunLog)a1;
            this.currentRun = a16;
            i7 = 17;
        }
        label6: {
            if(i7 != 6)
            {
                i8 = i7;
                break label6;
            }
            int i37 = this.firstBack;
            if(i37 == -1)
            {
                java.util.Date a17 = a1.getTime();
                long j5 = a17.getTime();
                java.util.Date a18 = this.startTime;
                long j6 = a18.getTime();
                long j7 = j5 - j6;
                int i38 = (int)j7;
                int i39 = i38 / 60000;
                this.firstBack = i39;
            }
            int i40 = this.numOfBacks;
            int i41 = i40 + 1;
            this.numOfBacks = i41;
            java.util.Vector a19 = this.listOfBacks;
            String s0 = this.lastDisplay;
            a19.addElement((Object)s0);
            this.isOnStep = false;
            i8 = 6;
        }
        label7: {
            if(i8 != 9)
            {
                i9 = i8;
                break label7;
            }
            int i42 = this.firstDataSelection;
            if(i42 == -1)
            {
                java.util.Date a20 = a1.getTime();
                long j8 = a20.getTime();
                java.util.Date a21 = this.startTime;
                long j9 = a21.getTime();
                long j10 = j8 - j9;
                int i43 = (int)j10;
                int i44 = i43 / 60000;
                this.firstDataSelection = i44;
            }
            int i45 = this.numOfDataSelections;
            int i46 = i45 + 1;
            this.numOfDataSelections = i46;
            i9 = 9;
        }
        label8: {
            if(i9 != 10)
            {
                i10 = i9;
                break label8;
            }
            int i47 = this.firstExplanation;
            if(i47 == -1)
            {
                java.util.Date a22 = a1.getTime();
                long j11 = a22.getTime();
                java.util.Date a23 = this.startTime;
                long j12 = a23.getTime();
                long j13 = j11 - j12;
                int i48 = (int)j13;
                int i49 = i48 / 60000;
                this.firstExplanation = i49;
            }
            int i50 = this.numOfExplanations;
            int i51 = i50 + 1;
            this.numOfExplanations = i51;
            java.util.Vector a24 = this.listOfExplanations;
            a24.addElement((Object)s);
            i10 = 10;
        }
        label10: {
            label9: {
                if(i10 == 12)
                {
                    break label9;
                }
                if(i10 != 11)
                {
                    break label10;
                }
            }
            int i52 = this.firstFolder;
            if(i52 == -1)
            {
                java.util.Date a25 = a1.getTime();
                long j14 = a25.getTime();
                java.util.Date a26 = this.startTime;
                long j15 = a26.getTime();
                long j16 = j14 - j15;
                int i53 = (int)j16;
                int i54 = i53 / 60000;
                this.firstFolder = i54;
            }
            int i55 = this.numOfExpansions;
            int i56 = i55 + 1;
            this.numOfExpansions = i56;
        }
        if(i10 != 7)
        {
            i11 = i10;
        }
        else
        {
            int i57 = this.numOfAddBreakPoints;
            int i58 = i57 + 1;
            this.numOfAddBreakPoints = i58;
            java.util.Vector a27 = this.listOfAddBreakPoints;
            a27.addElement((Object)s);
            i11 = 7;
        }
        if(i11 != 8)
        {
            i12 = i11;
        }
        else
        {
            int i59 = this.numOfDelBreakPoints;
            int i60 = i59 + 1;
            this.numOfDelBreakPoints = i60;
            i12 = 8;
        }
        label11: {
            if(i12 != 14)
            {
                i13 = i12;
                break label11;
            }
            com.cim.AIA.RunLog a28 = this.currentRun;
            if(a28 == null)
            {
                i13 = 14;
            }
            else
            {
                com.cim.AIA.RunLog a29 = this.currentRun;
                int i61 = this.speed;
                a29.setEndSpeed(i61);
                com.cim.AIA.RunLog a30 = this.currentRun;
                a30.setExitMode(1);
                i13 = 14;
            }
        }
        label12: {
            if(i13 != 15)
            {
                i14 = i13;
                break label12;
            }
            com.cim.AIA.RunLog a31 = this.currentRun;
            if(a31 == null)
            {
                i14 = 15;
            }
            else
            {
                com.cim.AIA.RunLog a32 = this.currentRun;
                int i62 = this.speed;
                a32.setEndSpeed(i62);
                i14 = 15;
            }
        }
        label13: {
            if(i14 != 20)
            {
                break label13;
            }
            com.cim.AIA.RunLog a33 = this.currentRun;
            if(a33 == null)
            {
                break label13;
            }
            int i63 = com.cim.AIA.Logger.DEBUG?1:0;
            if(i63 != 0)
            {
                java.io.PrintStream a34 = System.err;
                a34.println("Setting run log as complete");
                java.io.PrintStream a35 = System.err;
                StringBuilder a36 = new StringBuilder();
                StringBuilder a37 = a36.append("hiddenLines= ");
                com.cim.AIA.RunLog a38 = this.currentRun;
                int i64 = a38.getHiddenLines();
                StringBuilder a39 = a37.append(i64);
                String s1 = a39.toString();
                a35.println(s1);
                java.io.PrintStream a40 = System.err;
                StringBuilder a41 = new StringBuilder();
                StringBuilder a42 = a41.append("nsteps = ");
                com.cim.AIA.RunLog a43 = this.currentRun;
                int i65 = a43.getNSteps();
                StringBuilder a44 = a42.append(i65);
                String s2 = a44.toString();
                a40.println(s2);
            }
            com.cim.AIA.RunLog a45 = this.currentRun;
            int i66 = this.speed;
            a45.setEndSpeed(i66);
            com.cim.AIA.RunLog a46 = this.currentRun;
            a46.setExitMode(2);
        }
    }
    
    static
    {
        java.text.SimpleDateFormat a = new java.text.SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        com.cim.AIA.Logger.datefmt = a;
        com.cim.AIA.Logger.OUTPUTADDINFO = true;
        com.cim.AIA.Logger.DEBUG = false;
        com.cim.AIA.Logger.DEBUG_NOSERVER = false;
        java.util.SimpleTimeZone a0 = new java.util.SimpleTimeZone(36000000, "Australia/Melbourne", 9, -1, 1, 7200000, 2, -1, 1, 7200000);
        java.text.DateFormat a1 = com.cim.AIA.Logger.datefmt;
        a1.setTimeZone((java.util.TimeZone)a0);
    }
}