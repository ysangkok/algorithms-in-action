package com.cim.AIA;

public class Log {
    final public static byte TCLASS_UNREGISTERED = 0;
    final public static byte TCLASS_UNKNOWN = 1;
    final public static byte TCLASS_SYSTEM = 2;
    final public static byte TCLASS_DISPLAY = 3;
    final public static byte TCLASS_CONTROL = 4;
    final public static byte TCLASS_PSEUDOCODE = 5;
    final public static byte TCLASS_EXPLANATION = 6;
    final public static int N_TCLASSES = 7;
    final public static byte EVENT_UNREGISTERED = 0;
    final public static byte EVENT_BEGINSESSION = 1;
    final public static byte EVENT_ENDSESSION = 2;
    final public static byte EVENT_RUNSTEP = 3;
    final public static byte EVENT_OPENALL = 4;
    final public static byte EVENT_CLOSEALL = 5;
    final public static byte EVENT_BACK = 6;
    final public static byte EVENT_ADDBREAK = 7;
    final public static byte EVENT_DELETEBREAK = 8;
    final public static byte EVENT_DATASELECT = 9;
    final public static byte EVENT_EXPLANATION = 10;
    final public static byte EVENT_COLLAPSE = 11;
    final public static byte EVENT_EXPAND = 12;
    final public static byte EVENT_ALGORITHMLINE = 13;
    final public static byte EVENT_PAUSE = 14;
    final public static byte EVENT_RESET = 15;
    final public static byte EVENT_RESTART = 16;
    final public static byte EVENT_RUN = 17;
    final public static byte EVENT_METHODSELECTION = 18;
    final public static byte EVENT_STEP = 19;
    final public static byte EVENT_FINISH = 20;
    final public static byte EVENT_CHANGECODE = 21;
    final public static byte EVENT_CHANGEPARAMS = 22;
    final public static int N_EVENTS = 23;
    protected static java.util.Vector tClasses;
    protected static java.util.Vector events;
    protected static boolean[] xmllogged;
    public java.util.Date dateLogged;
    public byte tclass;
    public byte event;
    public String lineno;
    public String addInfo;
    protected java.util.Calendar cal;
    
    public static String convertEvent(byte a)
    {
        java.util.Vector a0 = com.cim.AIA.Log.events;
        int i = a;
        Object a1 = a0.elementAt(i);
        String dummy = (String)a1;
        String s = (String)a1;
        return s;
    }
    
    public static byte convertEvent(String s)
    {
        int i = 0;
        label1: {
            label0: {
                if(s != null)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a = com.cim.AIA.Log.events;
                int i1 = a.size();
                label2: {
                    if(i0 < i1)
                    {
                        break label2;
                    }
                    i = 0;
                    break;
                }
                java.util.Vector a0 = com.cim.AIA.Log.events;
                Object a1 = a0.elementAt(i0);
                int i2 = s.equals(a1)?1:0;
                if(i2 == 0)
                {
                    int i3 = i0 + 1;
                    int i4 = (byte)i3;
                    i0 = i4;
                    continue;
                }
                i = i0;
                break;
            }
        }
        return (byte)i;
    }
    
    public static String convertTClass(byte a)
    {
        java.util.Vector a0 = com.cim.AIA.Log.tClasses;
        int i = a;
        Object a1 = a0.elementAt(i);
        String dummy = (String)a1;
        String s = (String)a1;
        return s;
    }
    
    public static byte convertTClass(String s)
    {
        int i = 0;
        label1: {
            label0: {
                if(s != null)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a = com.cim.AIA.Log.tClasses;
                int i1 = a.size();
                label2: {
                    if(i0 < i1)
                    {
                        break label2;
                    }
                    i = 0;
                    break;
                }
                java.util.Vector a0 = com.cim.AIA.Log.tClasses;
                Object a1 = a0.elementAt(i0);
                int i2 = s.equals(a1)?1:0;
                if(i2 == 0)
                {
                    int i3 = i0 + 1;
                    int i4 = (byte)i3;
                    i0 = i4;
                    continue;
                }
                i = i0;
                break;
            }
        }
        return (byte)i;
    }
    
    public static com.cim.AIA.Log createLog(String s, String s0)
    {
        com.cim.AIA.Log a = null;
        int i = com.cim.AIA.Log.convertTClass(s);
        int i0 = com.cim.AIA.Log.convertEvent(s0);
        switch(i0){
            case 17: {
                com.cim.AIA.RunLog a0 = new com.cim.AIA.RunLog((byte)i, (byte)i0, (String)null);
                a = a0;
                break;
            }
            case 7: case 10: case 11: case 12: case 19: {
                com.cim.AIA.StepLog a1 = new com.cim.AIA.StepLog((byte)i, (byte)i0, (String)null, 0);
                a = a1;
                break;
            }
            case 0: {
                a = null;
                break;
            }
            default: {
                com.cim.AIA.Log a2 = new com.cim.AIA.Log((byte)i, (byte)i0);
                a = a2;
            }
        }
        return a;
    }
    
    public static boolean isXMLLogged(byte a)
    {
        boolean[] a0 = com.cim.AIA.Log.xmllogged;
        int i = a;
        int i0 = a0[i]?1:0;
        return i0 != 0;
    }
    
    static String replaceAll(String s, String s0, String s1)
    {
        StringBuffer a = new StringBuffer();
        int i = 0;
        while(true)
        {
            int i0 = s.indexOf(s0, i);
            if(i0 == -1)
            {
                String s2 = s.substring(i);
                StringBuffer a0 = a.append(s2);
                String s3 = a.toString();
                return s3;
            }
            else
            {
                String s4 = s.substring(i, i0);
                StringBuffer a1 = a.append(s4);
                StringBuffer a2 = a.append(s1);
                int i1 = s0.length();
                int i2 = i0 + i1;
                i = i2;
            }
        }
    }
    
    public Log(byte a, byte a0)
    {
        this(a, a0, (String)null, "");
    }
    
    public Log(byte a, byte a0, String s)
    {
        this(a, a0, s, "");
    }
    
    public Log(byte a, byte a0, String s, String s0)
    {
        super();
        int i = a;
        int i0 = a0;
        java.util.Calendar a1 = java.util.Calendar.getInstance();
        this.cal = a1;
        java.util.Date a2 = new java.util.Date();
        this.dateLogged = a2;
        this.tclass = (byte)i;
        this.event = (byte)i0;
        this.lineno = s;
        this.addInfo = s0;
    }
    
    public String getAddInfo()
    {
        String s = this.addInfo;
        return s;
    }
    
    public java.util.Calendar getDate()
    {
        java.util.Calendar a = this.cal;
        java.util.Date a0 = this.dateLogged;
        a.setTime(a0);
        java.util.Calendar a1 = this.cal;
        return a1;
    }
    
    public byte getEvent()
    {
        int i = this.event;
        return (byte)i;
    }
    
    public String getLineNo()
    {
        String s = this.lineno;
        return s;
    }
    
    public byte getTClass()
    {
        int i = this.tclass;
        return (byte)i;
    }
    
    public java.util.Date getTime()
    {
        java.util.Date a = this.dateLogged;
        return a;
    }
    
    public boolean isXMLLogged()
    {
        int i = this.event;
        int i0 = com.cim.AIA.Log.isXMLLogged((byte)i)?1:0;
        return i0 != 0;
    }
    
    public String output(int i)
    {
        String s = null;
        switch(i){
            case 2: {
                String s0 = this.toXML();
                s = s0;
                break;
            }
            case 1: {
                String s1 = this.textOutput();
                s = s1;
                break;
            }
            default: {
                s = null;
            }
        }
        return s;
    }
    
    public void print()
    {
        java.util.Calendar a = this.cal;
        java.util.Date a0 = this.dateLogged;
        a.setTime(a0);
        java.io.PrintStream a1 = System.out;
        StringBuilder a2 = new StringBuilder();
        StringBuilder a3 = a2.append("");
        int i = this.tclass;
        String s = com.cim.AIA.Log.convertTClass((byte)i);
        StringBuilder a4 = a3.append(s);
        StringBuilder a5 = a4.append(": ");
        int i0 = this.event;
        String s0 = com.cim.AIA.Log.convertEvent((byte)i0);
        StringBuilder a6 = a5.append(s0);
        StringBuilder a7 = a6.append(": ");
        String s1 = this.addInfo;
        StringBuilder a8 = a7.append(s1);
        StringBuilder a9 = a8.append(", logged on ");
        java.util.Calendar a10 = this.cal;
        int i1 = a10.get(5);
        StringBuilder a11 = a9.append(i1);
        StringBuilder a12 = a11.append("-");
        java.util.Calendar a13 = this.cal;
        int i2 = a13.get(2);
        StringBuilder a14 = a12.append(i2);
        StringBuilder a15 = a14.append("-");
        java.util.Calendar a16 = this.cal;
        int i3 = a16.get(1);
        StringBuilder a17 = a15.append(i3);
        StringBuilder a18 = a17.append(": ");
        java.util.Calendar a19 = this.cal;
        int i4 = a19.get(11);
        StringBuilder a20 = a18.append(i4);
        StringBuilder a21 = a20.append(":");
        java.util.Calendar a22 = this.cal;
        int i5 = a22.get(12);
        StringBuilder a23 = a21.append(i5);
        String s2 = a23.toString();
        a1.println(s2);
    }
    
    public void setParam(String s, String s0)
    {
        int i = s.equalsIgnoreCase("linenumber")?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                this.lineno = s0;
                break label1;
            }
            int i0 = s.equalsIgnoreCase("option")?1:0;
            if(i0 != 0)
            {
                this.addInfo = s0;
            }
        }
    }
    
    public void setTClass(String s)
    {
        int i = com.cim.AIA.Log.convertTClass(s);
        this.tclass = (byte)i;
    }
    
    public void setTime(String s)
    {
        {
            label1: {
                java.text.ParseException a = null;
                label0: {
                    java.text.DateFormat a0 = null;
                    java.util.Date a1 = null;
                    try
                    {
                        a0 = com.cim.AIA.Logger.datefmt;
                    }
                    catch(java.text.ParseException a2)
                    {
                        a = a2;
                        break label0;
                    }
                    try
                    {
                        a1 = a0.parse(s);
                    }
                    catch(java.text.ParseException a3)
                    {
                        a = a3;
                        break label0;
                    }
                    try
                    {
                        this.dateLogged = a1;
                        break label1;
                    }
                    catch(java.text.ParseException a4)
                    {
                        a = a4;
                    }
                }
                a.printStackTrace();
            }
        }
    }
    
    public String textOutput()
    {
        java.util.Calendar a = this.cal;
        java.util.Date a0 = this.dateLogged;
        a.setTime(a0);
        StringBuilder a1 = new StringBuilder();
        int i = this.tclass;
        String s = com.cim.AIA.Log.convertTClass((byte)i);
        StringBuilder a2 = a1.append(s);
        StringBuilder a3 = a2.append("#");
        int i0 = this.event;
        String s0 = com.cim.AIA.Log.convertEvent((byte)i0);
        StringBuilder a4 = a3.append(s0);
        StringBuilder a5 = a4.append("#");
        String s1 = this.addInfo;
        StringBuilder a6 = a5.append(s1);
        StringBuilder a7 = a6.append("#");
        java.util.Calendar a8 = this.cal;
        int i1 = a8.get(5);
        StringBuilder a9 = a7.append(i1);
        StringBuilder a10 = a9.append("-");
        java.util.Calendar a11 = this.cal;
        int i2 = a11.get(2);
        StringBuilder a12 = a10.append(i2);
        StringBuilder a13 = a12.append("-");
        java.util.Calendar a14 = this.cal;
        int i3 = a14.get(1);
        StringBuilder a15 = a13.append(i3);
        StringBuilder a16 = a15.append(": ");
        java.util.Calendar a17 = this.cal;
        int i4 = a17.get(11);
        StringBuilder a18 = a16.append(i4);
        StringBuilder a19 = a18.append(":");
        java.util.Calendar a20 = this.cal;
        int i5 = a20.get(12);
        StringBuilder a21 = a19.append(i5);
        StringBuilder a22 = a21.append("#");
        StringBuilder a23 = a22.append((char)10);
        String s2 = a23.toString();
        return s2;
    }
    
    public String toXML()
    {
        String s = null;
        int i = this.event;
        int i0 = com.cim.AIA.Log.isXMLLogged((byte)i)?1:0;
        if(i0 != 0)
        {
            StringBuffer a = new StringBuffer();
            this.xmlOpen(a);
            this.xmlBody(a);
            this.xmlClose(a);
            String s0 = a.toString();
            s = s0;
        }
        else
        {
            s = "";
        }
        return s;
    }
    
    protected void xmlBody(StringBuffer a)
    {
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("<name>");
        int i = this.event;
        String s = com.cim.AIA.Log.convertEvent((byte)i);
        StringBuilder a2 = a1.append(s);
        StringBuilder a3 = a2.append("</name>\n");
        String s0 = a3.toString();
        StringBuffer a4 = a.append(s0);
        StringBuilder a5 = new StringBuilder();
        StringBuilder a6 = a5.append("<tclass>");
        int i0 = this.tclass;
        String s1 = com.cim.AIA.Log.convertTClass((byte)i0);
        StringBuilder a7 = a6.append(s1);
        StringBuilder a8 = a7.append("</tclass>\n");
        String s2 = a8.toString();
        StringBuffer a9 = a.append(s2);
        StringBuilder a10 = new StringBuilder();
        StringBuilder a11 = a10.append("<time>");
        java.text.DateFormat a12 = com.cim.AIA.Logger.datefmt;
        java.util.Date a13 = this.dateLogged;
        String s3 = a12.format(a13);
        StringBuilder a14 = a11.append(s3);
        StringBuilder a15 = a14.append("</time>\n");
        String s4 = a15.toString();
        StringBuffer a16 = a.append(s4);
        String s5 = this.lineno;
        if(s5 != null)
        {
            StringBuilder a17 = new StringBuilder();
            StringBuilder a18 = a17.append("<param name=\"linenumber\" value=\"");
            String s6 = this.lineno;
            StringBuilder a19 = a18.append(s6);
            StringBuilder a20 = a19.append("\"/>\n");
            String s7 = a20.toString();
            StringBuffer a21 = a.append(s7);
        }
        int i1 = com.cim.AIA.Logger.OUTPUTADDINFO?1:0;
        label0: {
            if(i1 == 0)
            {
                break label0;
            }
            String s8 = this.addInfo;
            if(s8 == null)
            {
                break label0;
            }
            String s9 = this.addInfo;
            int i2 = s9.equals((Object)"")?1:0;
            if(i2 != 0)
            {
                break label0;
            }
            String s10 = this.addInfo;
            String s11 = this.lineno;
            int i3 = s10.equals((Object)s11)?1:0;
            if(i3 == 0)
            {
                String s12 = this.addInfo;
                String s13 = new String(s12);
                String s14 = com.cim.AIA.Log.replaceAll(s13, "<", "&lt;");
                String s15 = com.cim.AIA.Log.replaceAll(s14, ">", "&gt;");
                String s16 = com.cim.AIA.Log.replaceAll(s15, "\"", "&quot;");
                String s17 = com.cim.AIA.Log.replaceAll(s16, "&", "&amp;");
                String s18 = com.cim.AIA.Log.replaceAll(s17, "'", "&apos;");
                StringBuilder a22 = new StringBuilder();
                StringBuilder a23 = a22.append("<param name=\"option\" value=\"");
                StringBuilder a24 = a23.append(s18);
                StringBuilder a25 = a24.append("\"/>\n");
                String s19 = a25.toString();
                StringBuffer a26 = a.append(s19);
            }
        }
    }
    
    protected void xmlClose(StringBuffer a)
    {
        StringBuffer a0 = a.append("</action>\n");
    }
    
    protected void xmlOpen(StringBuffer a)
    {
        StringBuffer a0 = a.append("<action>\n");
    }
    
    static
    {
        java.util.Vector a = new java.util.Vector(7);
        com.cim.AIA.Log.tClasses = a;
        java.util.Vector a0 = com.cim.AIA.Log.tClasses;
        a0.setSize(7);
        java.util.Vector a1 = com.cim.AIA.Log.tClasses;
        a1.setElementAt((Object)"Unregistered", 0);
        java.util.Vector a2 = com.cim.AIA.Log.tClasses;
        a2.setElementAt((Object)"Unknown", 1);
        java.util.Vector a3 = com.cim.AIA.Log.tClasses;
        a3.setElementAt((Object)"System", 2);
        java.util.Vector a4 = com.cim.AIA.Log.tClasses;
        a4.setElementAt((Object)"Display", 3);
        java.util.Vector a5 = com.cim.AIA.Log.tClasses;
        a5.setElementAt((Object)"Control", 4);
        java.util.Vector a6 = com.cim.AIA.Log.tClasses;
        a6.setElementAt((Object)"Pseudocode", 5);
        java.util.Vector a7 = com.cim.AIA.Log.tClasses;
        a7.setElementAt((Object)"Explanation", 6);
        java.util.Vector a8 = new java.util.Vector();
        com.cim.AIA.Log.events = a8;
        int i = com.cim.AIA.Logger.DEBUG?1:0;
        if(i != 0)
        {
            java.io.PrintStream a9 = System.err;
            a9.println("Setting events array size to 23");
        }
        java.util.Vector a10 = com.cim.AIA.Log.events;
        a10.setSize(23);
        java.util.Vector a11 = com.cim.AIA.Log.events;
        a11.setElementAt((Object)"Unregistered", 0);
        java.util.Vector a12 = com.cim.AIA.Log.events;
        a12.setElementAt((Object)"Begin Session", 1);
        java.util.Vector a13 = com.cim.AIA.Log.events;
        a13.setElementAt((Object)"End Session", 2);
        java.util.Vector a14 = com.cim.AIA.Log.events;
        a14.setElementAt((Object)"Run Step", 3);
        java.util.Vector a15 = com.cim.AIA.Log.events;
        a15.setElementAt((Object)"Open All", 4);
        java.util.Vector a16 = com.cim.AIA.Log.events;
        a16.setElementAt((Object)"Close All", 5);
        java.util.Vector a17 = com.cim.AIA.Log.events;
        a17.setElementAt((Object)"Add BreakPoint", 7);
        java.util.Vector a18 = com.cim.AIA.Log.events;
        a18.setElementAt((Object)"Del BreakPoint", 8);
        java.util.Vector a19 = com.cim.AIA.Log.events;
        a19.setElementAt((Object)"Data Selection", 9);
        java.util.Vector a20 = com.cim.AIA.Log.events;
        a20.setElementAt((Object)"Explanation", 10);
        java.util.Vector a21 = com.cim.AIA.Log.events;
        a21.setElementAt((Object)"Collapse", 11);
        java.util.Vector a22 = com.cim.AIA.Log.events;
        a22.setElementAt((Object)"Expand", 12);
        java.util.Vector a23 = com.cim.AIA.Log.events;
        a23.setElementAt((Object)"Algorithm On Line", 13);
        java.util.Vector a24 = com.cim.AIA.Log.events;
        a24.setElementAt((Object)"Pause Button", 14);
        java.util.Vector a25 = com.cim.AIA.Log.events;
        a25.setElementAt((Object)"Reset Button", 15);
        java.util.Vector a26 = com.cim.AIA.Log.events;
        a26.setElementAt((Object)"Restart/ChangeMode Button", 16);
        java.util.Vector a27 = com.cim.AIA.Log.events;
        a27.setElementAt((Object)"Run Button", 17);
        java.util.Vector a28 = com.cim.AIA.Log.events;
        a28.setElementAt((Object)"Method Selection", 18);
        java.util.Vector a29 = com.cim.AIA.Log.events;
        a29.setElementAt((Object)"Step Button", 19);
        java.util.Vector a30 = com.cim.AIA.Log.events;
        a30.setElementAt((Object)"Back Button", 6);
        java.util.Vector a31 = com.cim.AIA.Log.events;
        a31.setElementAt((Object)"Change Code", 21);
        java.util.Vector a32 = com.cim.AIA.Log.events;
        a32.setElementAt((Object)"Change Parameters", 22);
        java.util.Vector a33 = com.cim.AIA.Log.events;
        int i0 = a33.size();
        boolean[] a34 = new boolean[i0];
        com.cim.AIA.Log.xmllogged = a34;
        int i1 = 0;
        while(true)
        {
            java.util.Vector a35 = com.cim.AIA.Log.events;
            int i2 = a35.size();
            if(i1 >= i2)
            {
                boolean[] a36 = com.cim.AIA.Log.xmllogged;
                a36[19] = true;
                boolean[] a37 = com.cim.AIA.Log.xmllogged;
                a37[6] = true;
                boolean[] a38 = com.cim.AIA.Log.xmllogged;
                a38[17] = true;
                boolean[] a39 = com.cim.AIA.Log.xmllogged;
                a39[14] = true;
                boolean[] a40 = com.cim.AIA.Log.xmllogged;
                a40[10] = true;
                boolean[] a41 = com.cim.AIA.Log.xmllogged;
                a41[7] = true;
                boolean[] a42 = com.cim.AIA.Log.xmllogged;
                a42[8] = true;
                boolean[] a43 = com.cim.AIA.Log.xmllogged;
                a43[12] = true;
                boolean[] a44 = com.cim.AIA.Log.xmllogged;
                a44[11] = true;
                boolean[] a45 = com.cim.AIA.Log.xmllogged;
                a45[15] = true;
                boolean[] a46 = com.cim.AIA.Log.xmllogged;
                a46[4] = true;
                boolean[] a47 = com.cim.AIA.Log.xmllogged;
                a47[5] = true;
                boolean[] a48 = com.cim.AIA.Log.xmllogged;
                a48[21] = true;
                boolean[] a49 = com.cim.AIA.Log.xmllogged;
                a49[9] = true;
                boolean[] a50 = com.cim.AIA.Log.xmllogged;
                a50[22] = true;
                return;
            }
            else
            {
                boolean[] a51 = com.cim.AIA.Log.xmllogged;
                a51[i1] = false;
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
}