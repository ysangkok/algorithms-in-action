package com.cim.AIA;

public class Exercise implements java.awt.event.ActionListener {
    protected java.awt.Dialog dialog;
    protected java.awt.Image correctImage;
    protected java.awt.Image incorrectImage;
    protected java.util.Vector exerciseQuestions;
    protected int questionPosition;
    protected com.cim.AIA.ExerciseQuestion currentQuestion;
    protected java.awt.Panel questionPanel;
    protected java.awt.Button ok;
    protected java.awt.Button next;
    protected java.awt.Button exit;
    protected java.awt.Frame frame;
    protected String title;
    
    public Exercise(java.awt.Frame a, String s, java.applet.Applet a0, String s0, String s1, String s2)
    {
        java.net.URL a1 = null;
        super();
        java.net.URL a2 = a0.getDocumentBase();
        String s3 = a2.toString();
        int i = s3.length();
        int i0 = i - 1;
        int i1 = i0;
        while(true)
        {
            int i2 = s3.charAt(i1);
            if(i2 == 47)
            {
                break;
            }
            else
            {
                int i3 = i1 + -1;
                i1 = i3;
            }
        }
        String s4 = s3.substring(0, i1);
        StringBuilder a3 = new StringBuilder();
        StringBuilder a4 = a3.append(s4);
        StringBuilder a5 = a4.append("/");
        String s5 = a5.toString();
        try
        {
            java.net.URL a6 = new java.net.URL(s5);
            a1 = a6;
        }
        catch(Exception ignoredException)
        {
            a1 = a2;
        }
        StringBuilder a7 = new StringBuilder();
        String s6 = a1.toString();
        StringBuilder a8 = a7.append(s6);
        StringBuilder a9 = a8.append("images/");
        String s7 = a9.toString();
        this.frame = a;
        this.title = s;
        java.util.Vector a10 = new java.util.Vector();
        this.exerciseQuestions = a10;
        this.questionPosition = 0;
        this.initaliseButtons();
        this.init(false);
        this.loadImages(a0, s7, s1, s2);
        com.cim.AIA.Exercise$1 a11 = new com.cim.AIA.Exercise$1(this);
        a.addWindowListener((java.awt.event.WindowListener)a11);
    }
    
    public Exercise(String s, java.applet.Applet a)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        this(a0, s, a, "", "correct.gif", "incorrect.gif");
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        int i = a.getModifiers();
        if(i <= 0)
        {
            java.awt.Button a0 = this.ok;
            a0.setEnabled(false);
        }
        else
        {
            java.awt.Button a1 = this.ok;
            a1.setEnabled(true);
        }
    }
    
    public void addExerciseQuestion(com.cim.AIA.ExerciseQuestion a)
    {
        java.util.Vector a0 = this.exerciseQuestions;
        a0.addElement((Object)a);
    }
    
    public void addExerciseQuestion(String s)
    {
        String[] a = new String[3];
        a[0] = "";
        a[1] = "";
        a[2] = "";
        java.util.StringTokenizer a0 = new java.util.StringTokenizer(s, "\t");
        int i = 0;
        int i0 = 0;
        while(true)
        {
            int i1 = 0;
            int i2 = 0;
            int i3 = a0.hasMoreTokens()?1:0;
            int i4 = i;
            label1: {
                int i5 = 0;
                label0: {
                    int i6 = 0;
                    int i7 = i4;
                    if(i3 == 0)
                    {
                        i5 = i4;
                        break label0;
                    }
                    else
                    {
                        i6 = i7;
                    }
                    int i8 = i6;
                    if(i0 >= 3)
                    {
                        i5 = i6;
                    }
                    else
                    {
                        i1 = i8;
                        break label1;
                    }
                }
                String s0 = a[0];
                String s1 = a[1];
                String s2 = a[2];
                com.cim.AIA.ExerciseQuestion a1 = new com.cim.AIA.ExerciseQuestion(s0, s1, s2, i5 != 0);
                this.addExerciseQuestion(a1);
                return;
            }
            String s3 = a0.nextToken();
            int i9 = i1;
            a[i0] = s3;
            int i10 = i9;
            label2: {
                int i11 = 0;
                int i12 = i10;
                int i13 = i10;
                if(i0 != 2)
                {
                    i2 = i13;
                    break label2;
                }
                else
                {
                    i11 = i12;
                }
                String s4 = a0.nextToken();
                int i14 = i11;
                int i15 = s4.indexOf("y");
                int i16 = i14;
                int i17 = i16;
                i2 = i15 == -1?i17:1;
            }
            int i18 = i0 + 1;
            i = i2;
            i0 = i18;
        }
    }
    
    protected void exit()
    {
        java.awt.Dialog a = this.dialog;
        if(a != null)
        {
            java.awt.Dialog a0 = this.dialog;
            a0.setVisible(false);
            java.awt.Dialog a1 = this.dialog;
            a1.dispose();
        }
        this.questionPosition = 0;
    }
    
    protected java.awt.Panel getQuestionPanel(boolean b)
    {
        java.awt.Panel a = null;
        int i = this.questionPosition;
        int i0 = b?1:0;
        label2: {
            java.awt.Panel a0 = null;
            label1: {
                label0: {
                    if(i < 0)
                    {
                        break label0;
                    }
                    int i1 = this.questionPosition;
                    java.util.Vector a1 = this.exerciseQuestions;
                    int i2 = a1.size();
                    if(i1 < i2)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.util.Vector a2 = this.exerciseQuestions;
            int i3 = this.questionPosition;
            Object a3 = a2.elementAt(i3);
            com.cim.AIA.ExerciseQuestion dummy = (com.cim.AIA.ExerciseQuestion)a3;
            com.cim.AIA.ExerciseQuestion a4 = (com.cim.AIA.ExerciseQuestion)a3;
            java.awt.Image a5 = this.correctImage;
            label4: {
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                label3: {
                    if(a5 != null)
                    {
                        break label3;
                    }
                    java.awt.Image a6 = this.incorrectImage;
                    if(a6 != null)
                    {
                        break label3;
                    }
                    java.awt.Panel a7 = a4.getPanel(i0 != 0);
                    a0 = a7;
                    break label4;
                }
                java.awt.Image a8 = this.correctImage;
                if(a8 == null)
                {
                    i4 = 0;
                    i5 = 0;
                }
                else
                {
                    java.awt.Image a9 = this.correctImage;
                    java.awt.Dialog a10 = this.dialog;
                    int i8 = a9.getWidth((java.awt.image.ImageObserver)a10);
                    int i9 = Math.max(i8, 0);
                    java.awt.Image a11 = this.correctImage;
                    java.awt.Dialog a12 = this.dialog;
                    int i10 = a11.getHeight((java.awt.image.ImageObserver)a12);
                    int i11 = Math.max(i10, 0);
                    i4 = i9;
                    i5 = i11;
                }
                java.awt.Image a13 = this.incorrectImage;
                if(a13 == null)
                {
                    i6 = i4;
                    i7 = i5;
                }
                else
                {
                    java.awt.Image a14 = this.incorrectImage;
                    java.awt.Dialog a15 = this.dialog;
                    int i12 = a14.getWidth((java.awt.image.ImageObserver)a15);
                    int i13 = Math.max(i12, i4);
                    java.awt.Image a16 = this.incorrectImage;
                    java.awt.Dialog a17 = this.dialog;
                    int i14 = a16.getHeight((java.awt.image.ImageObserver)a17);
                    int i15 = Math.max(i14, i5);
                    i6 = i13;
                    i7 = i15;
                }
                java.awt.Image a18 = this.correctImage;
                java.awt.Image a19 = this.incorrectImage;
                java.awt.Panel a20 = a4.getPanel(i6, i7, i0 != 0, a18, a19);
                a0 = a20;
            }
            this.currentQuestion = a4;
            if(i0 == 0)
            {
                com.cim.AIA.ExerciseQuestion a21 = this.currentQuestion;
                a21.addActionListener((java.awt.event.ActionListener)this);
            }
            a = a0;
        }
        return a;
    }
    
    protected void init(boolean b)
    {
        java.awt.Dialog a = this.dialog;
        int i = b?1:0;
        if(a != null)
        {
            java.awt.Dialog a0 = this.dialog;
            a0.setVisible(false);
            java.awt.Dialog a1 = this.dialog;
            a1.dispose();
        }
        java.awt.Frame a2 = this.frame;
        String s = this.title;
        java.awt.Dialog a3 = new java.awt.Dialog(a2, s, true);
        this.dialog = a3;
        java.awt.Dialog a4 = this.dialog;
        java.awt.BorderLayout a5 = new java.awt.BorderLayout();
        a4.setLayout((java.awt.LayoutManager)a5);
        java.awt.Panel a6 = this.getQuestionPanel(i != 0);
        this.questionPanel = a6;
        java.awt.Panel a7 = this.questionPanel;
        if(a7 != null)
        {
            java.awt.Dialog a8 = this.dialog;
            java.awt.Panel a9 = this.questionPanel;
            a8.add((java.awt.Component)a9, (Object)"Center");
        }
        java.awt.Panel a10 = new java.awt.Panel();
        java.awt.Button a11 = this.ok;
        java.awt.Component a12 = a10.add((java.awt.Component)a11);
        java.awt.Button a13 = this.next;
        java.awt.Component a14 = a10.add((java.awt.Component)a13);
        java.awt.Button a15 = this.exit;
        java.awt.Component a16 = a10.add((java.awt.Component)a15);
        java.awt.Dialog a17 = this.dialog;
        a17.add((java.awt.Component)a10, (Object)"South");
        java.awt.Dialog a18 = this.dialog;
        a18.pack();
    }
    
    protected void initaliseButtons()
    {
        String s = localization.Messages.getString("Exercise.17");
        java.awt.Button a = new java.awt.Button(s);
        this.ok = a;
        String s0 = localization.Messages.getString("Exercise.18");
        java.awt.Button a0 = new java.awt.Button(s0);
        this.next = a0;
        String s1 = localization.Messages.getString("Exercise.19");
        java.awt.Button a1 = new java.awt.Button(s1);
        this.exit = a1;
        java.awt.Button a2 = this.ok;
        com.cim.AIA.Exercise$2 a3 = new com.cim.AIA.Exercise$2(this);
        a2.addActionListener((java.awt.event.ActionListener)a3);
        java.awt.Button a4 = this.next;
        com.cim.AIA.Exercise$3 a5 = new com.cim.AIA.Exercise$3(this);
        a4.addActionListener((java.awt.event.ActionListener)a5);
        java.awt.Button a6 = this.exit;
        com.cim.AIA.Exercise$4 a7 = new com.cim.AIA.Exercise$4(this);
        a6.addActionListener((java.awt.event.ActionListener)a7);
        java.awt.Button a8 = this.ok;
        a8.setEnabled(false);
        java.awt.Button a9 = this.next;
        a9.setEnabled(false);
        java.awt.Button a10 = this.exit;
        a10.setEnabled(true);
    }
    
    public void initialiseExercise(String s, String s0)
    {
        java.util.Vector a = new java.util.Vector();
        this.exerciseQuestions = a;
        this.questionPosition = 0;
        label2: {
            java.net.URL a0 = null;
            java.io.BufferedReader a1 = null;
            label1: {
                java.net.MalformedURLException a2 = null;
                label0: {
                    StringBuilder a3 = null;
                    StringBuilder a4 = null;
                    StringBuilder a5 = null;
                    String s1 = null;
                    try
                    {
                        a3 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a6)
                    {
                        a2 = a6;
                        break label0;
                    }
                    try
                    {
                        a4 = a3.append(s);
                    }
                    catch(java.net.MalformedURLException a7)
                    {
                        a2 = a7;
                        break label0;
                    }
                    try
                    {
                        a5 = a4.append(s0);
                    }
                    catch(java.net.MalformedURLException a8)
                    {
                        a2 = a8;
                        break label0;
                    }
                    try
                    {
                        s1 = a5.toString();
                    }
                    catch(java.net.MalformedURLException a9)
                    {
                        a2 = a9;
                        break label0;
                    }
                    try
                    {
                        a0 = new java.net.URL(s1);
                        break label1;
                    }
                    catch(java.net.MalformedURLException a10)
                    {
                        a2 = a10;
                    }
                }
                java.io.PrintStream a11 = System.out;
                StringBuilder a12 = new StringBuilder();
                StringBuilder a13 = a12.append("Malformed URL: com.cim.AIA.Exercise ");
                StringBuilder a14 = a13.append((Object)a2);
                String s2 = a14.toString();
                a11.println(s2);
                break label2;
            }
            label5: {
                java.io.IOException a15 = null;
                label4: {
                    java.io.FileNotFoundException a16 = null;
                    label3: {
                        java.net.URLConnection a17 = null;
                        java.io.InputStream a18 = null;
                        java.io.InputStreamReader a19 = null;
                        try
                        {
                            try
                            {
                                a17 = a0.openConnection();
                            }
                            catch(java.io.FileNotFoundException a20)
                            {
                                a16 = a20;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a21)
                        {
                            a15 = a21;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a18 = a17.getInputStream();
                            }
                            catch(java.io.FileNotFoundException a22)
                            {
                                a16 = a22;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a23)
                        {
                            a15 = a23;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a19 = new java.io.InputStreamReader(a18);
                            }
                            catch(java.io.FileNotFoundException a24)
                            {
                                a16 = a24;
                                break label3;
                            }
                        }
                        catch(java.io.IOException a25)
                        {
                            a15 = a25;
                            break label4;
                        }
                        try
                        {
                            try
                            {
                                a1 = new java.io.BufferedReader((java.io.Reader)a19);
                                break label5;
                            }
                            catch(java.io.FileNotFoundException a26)
                            {
                                a16 = a26;
                            }
                        }
                        catch(java.io.IOException a27)
                        {
                            a15 = a27;
                            break label4;
                        }
                    }
                    java.io.PrintStream a28 = System.out;
                    StringBuilder a29 = new StringBuilder();
                    StringBuilder a30 = a29.append("FileNotFound: com.cim.AIA.Exercise ");
                    StringBuilder a31 = a30.append((Object)a16);
                    String s3 = a31.toString();
                    a28.println(s3);
                    break label2;
                }
                java.io.PrintStream a32 = System.out;
                StringBuilder a33 = new StringBuilder();
                StringBuilder a34 = a33.append("com.cim.AIA.Exercise: IOEXCEPTION");
                StringBuilder a35 = a34.append((Object)a15);
                String s4 = a35.toString();
                a32.println(s4);
                break label2;
            }
            try
            {
                while(true)
                {
                    String s5 = a1.readLine();
                    if(s5 == null)
                    {
                        break;
                    }
                    java.util.StringTokenizer a36 = new java.util.StringTokenizer(s5, "`");
                    String s6 = "";
                    while(true)
                    {
                        int i = a36.hasMoreTokens()?1:0;
                        if(i == 0)
                        {
                            this.addExerciseQuestion(s6);
                        }
                        else
                        {
                            StringBuilder a37 = new StringBuilder();
                            StringBuilder a38 = a37.append(s6);
                            String s7 = a36.nextToken();
                            StringBuilder a39 = a38.append(s7);
                            StringBuilder a40 = a39.append("\n");
                            String s8 = a40.toString();
                            s6 = s8;
                        }
                    }
                }
            }
            catch(java.io.IOException a41)
            {
                java.io.PrintStream a42 = System.out;
                StringBuilder a43 = new StringBuilder();
                StringBuilder a44 = a43.append("com.cim.AIA.Exercise: IOEXCEPTION");
                StringBuilder a45 = a44.append((Object)a41);
                String s9 = a45.toString();
                a42.println(s9);
            }
        }
    }
    
    protected void loadImages(java.applet.Applet a, String s, String s0, String s1)
    {
        java.awt.Dialog a0 = this.dialog;
        java.awt.MediaTracker a1 = new java.awt.MediaTracker((java.awt.Component)a0);
        label55: {
            java.net.MalformedURLException a2 = null;
            label1: {
                StringBuilder a3 = null;
                StringBuilder a4 = null;
                StringBuilder a5 = null;
                String s2 = null;
                java.net.URL a6 = null;
                StringBuilder a7 = null;
                java.net.URL a8 = null;
                StringBuilder a9 = null;
                StringBuilder a10 = null;
                StringBuilder a11 = null;
                String s3 = null;
                java.net.URL a12 = null;
                Class a13 = null;
                StringBuilder a14 = null;
                StringBuilder a15 = null;
                StringBuilder a16 = null;
                String s4 = null;
                java.io.InputStream a17 = null;
                java.awt.Image a18 = null;
                java.awt.Image a19 = null;
                java.awt.Image a20 = null;
                Class a21 = null;
                StringBuilder a22 = null;
                StringBuilder a23 = null;
                StringBuilder a24 = null;
                String s5 = null;
                java.io.InputStream a25 = null;
                StringBuilder a26 = null;
                StringBuilder a27 = null;
                StringBuilder a28 = null;
                String s6 = null;
                java.net.URL a29 = null;
                StringBuilder a30 = null;
                java.net.URL a31 = null;
                StringBuilder a32 = null;
                StringBuilder a33 = null;
                StringBuilder a34 = null;
                String s7 = null;
                java.net.URL a35 = null;
                java.awt.Image a36 = null;
                java.awt.Image a37 = null;
                java.awt.Image a38 = null;
                label0: try
                {
                    a3 = new StringBuilder();
                    break label0;
                }
                catch(java.net.MalformedURLException a39)
                {
                    a2 = a39;
                    break label1;
                }
                label2: try
                {
                    a4 = a3.append(s);
                    break label2;
                }
                catch(java.net.MalformedURLException a40)
                {
                    a2 = a40;
                    break label1;
                }
                label3: try
                {
                    a5 = a4.append(s0);
                    break label3;
                }
                catch(java.net.MalformedURLException a41)
                {
                    a2 = a41;
                    break label1;
                }
                label4: try
                {
                    s2 = a5.toString();
                    break label4;
                }
                catch(java.net.MalformedURLException a42)
                {
                    a2 = a42;
                    break label1;
                }
                label5: try
                {
                    a6 = new java.net.URL(s2);
                    break label5;
                }
                catch(java.net.MalformedURLException a43)
                {
                    a2 = a43;
                    break label1;
                }
                label6: try
                {
                    a7 = new StringBuilder();
                    break label6;
                }
                catch(java.net.MalformedURLException a44)
                {
                    a2 = a44;
                    break label1;
                }
                label7: try
                {
                    a8 = a.getCodeBase();
                    break label7;
                }
                catch(java.net.MalformedURLException a45)
                {
                    a2 = a45;
                    break label1;
                }
                label8: try
                {
                    a9 = a7.append((Object)a8);
                    break label8;
                }
                catch(java.net.MalformedURLException a46)
                {
                    a2 = a46;
                    break label1;
                }
                label9: try
                {
                    a10 = a9.append("images/");
                    break label9;
                }
                catch(java.net.MalformedURLException a47)
                {
                    a2 = a47;
                    break label1;
                }
                label10: try
                {
                    a11 = a10.append(s0);
                    break label10;
                }
                catch(java.net.MalformedURLException a48)
                {
                    a2 = a48;
                    break label1;
                }
                label11: try
                {
                    s3 = a11.toString();
                    break label11;
                }
                catch(java.net.MalformedURLException a49)
                {
                    a2 = a49;
                    break label1;
                }
                label12: try
                {
                    a12 = new java.net.URL(s3);
                    break label12;
                }
                catch(java.net.MalformedURLException a50)
                {
                    a2 = a50;
                    break label1;
                }
                label13: try
                {
                    a13 = ((Object)a).getClass();
                    break label13;
                }
                catch(java.net.MalformedURLException a51)
                {
                    a2 = a51;
                    break label1;
                }
                label14: try
                {
                    a14 = new StringBuilder();
                    break label14;
                }
                catch(java.net.MalformedURLException a52)
                {
                    a2 = a52;
                    break label1;
                }
                label15: try
                {
                    a15 = a14.append("images/");
                    break label15;
                }
                catch(java.net.MalformedURLException a53)
                {
                    a2 = a53;
                    break label1;
                }
                label16: try
                {
                    a16 = a15.append(s0);
                    break label16;
                }
                catch(java.net.MalformedURLException a54)
                {
                    a2 = a54;
                    break label1;
                }
                label17: try
                {
                    s4 = a16.toString();
                    break label17;
                }
                catch(java.net.MalformedURLException a55)
                {
                    a2 = a55;
                    break label1;
                }
                label18: try
                {
                    a17 = a13.getResourceAsStream(s4);
                    break label18;
                }
                catch(java.net.MalformedURLException a56)
                {
                    a2 = a56;
                    break label1;
                }
                label19: try
                {
                    a18 = com.cim.AIA.StreamImage.getImage(a17);
                    break label19;
                }
                catch(java.net.MalformedURLException a57)
                {
                    a2 = a57;
                    break label1;
                }
                label20: try
                {
                    this.correctImage = a18;
                    break label20;
                }
                catch(java.net.MalformedURLException a58)
                {
                    a2 = a58;
                    break label1;
                }
                label21: try
                {
                    a19 = this.correctImage;
                    break label21;
                }
                catch(java.net.MalformedURLException a59)
                {
                    a2 = a59;
                    break label1;
                }
                label22: {
                    java.io.PrintStream a60 = null;
                    StringBuilder a61 = null;
                    StringBuilder a62 = null;
                    String s8 = null;
                    StringBuilder a63 = null;
                    String s9 = null;
                    Object a64 = null;
                    java.awt.Image a65 = null;
                    if(a19 != null)
                    {
                        break label22;
                    }
                    try
                    {
                        a60 = System.out;
                    }
                    catch(java.net.MalformedURLException a66)
                    {
                        a2 = a66;
                        break label1;
                    }
                    try
                    {
                        a61 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a67)
                    {
                        a2 = a67;
                        break label1;
                    }
                    try
                    {
                        a62 = a61.append("Correct Image is loading from local...");
                    }
                    catch(java.net.MalformedURLException a68)
                    {
                        a2 = a68;
                        break label1;
                    }
                    try
                    {
                        s8 = a12.toString();
                    }
                    catch(java.net.MalformedURLException a69)
                    {
                        a2 = a69;
                        break label1;
                    }
                    try
                    {
                        a63 = a62.append(s8);
                    }
                    catch(java.net.MalformedURLException a70)
                    {
                        a2 = a70;
                        break label1;
                    }
                    try
                    {
                        s9 = a63.toString();
                    }
                    catch(java.net.MalformedURLException a71)
                    {
                        a2 = a71;
                        break label1;
                    }
                    try
                    {
                        a60.println(s9);
                    }
                    catch(java.net.MalformedURLException a72)
                    {
                        a2 = a72;
                        break label1;
                    }
                    try
                    {
                        a64 = a.getAppletContext();
                    }
                    catch(java.net.MalformedURLException a73)
                    {
                        a2 = a73;
                        break label1;
                    }
                    try
                    {
                        a65 = ((java.applet.AppletContext)a64).getImage(a12);
                    }
                    catch(java.net.MalformedURLException a74)
                    {
                        a2 = a74;
                        break label1;
                    }
                    try
                    {
                        this.correctImage = a65;
                    }
                    catch(java.net.MalformedURLException a75)
                    {
                        a2 = a75;
                        break label1;
                    }
                }
                label23: try
                {
                    a20 = this.correctImage;
                    break label23;
                }
                catch(java.net.MalformedURLException a76)
                {
                    a2 = a76;
                    break label1;
                }
                label27: {
                    StringBuilder a77 = null;
                    StringBuilder a78 = null;
                    StringBuilder a79 = null;
                    String s10 = null;
                    java.io.PrintStream a80 = null;
                    StringBuilder a81 = null;
                    StringBuilder a82 = null;
                    StringBuilder a83 = null;
                    String s11 = null;
                    label24: {
                        java.awt.Image a84 = null;
                        if(a20 == null)
                        {
                            break label24;
                        }
                        label25: try
                        {
                            a84 = this.correctImage;
                            break label25;
                        }
                        catch(java.net.MalformedURLException a85)
                        {
                            a2 = a85;
                            break label1;
                        }
                        label26: try
                        {
                            a1.addImage(a84, 0);
                            break label26;
                        }
                        catch(java.net.MalformedURLException a86)
                        {
                            a2 = a86;
                            break label1;
                        }
                        break label27;
                    }
                    try
                    {
                        a77 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a87)
                    {
                        a2 = a87;
                        break label1;
                    }
                    try
                    {
                        a78 = a77.append("com.cim.AIA.Exercise: Unable to load image: ");
                    }
                    catch(java.net.MalformedURLException a88)
                    {
                        a2 = a88;
                        break label1;
                    }
                    try
                    {
                        a79 = a78.append((Object)a6);
                    }
                    catch(java.net.MalformedURLException a89)
                    {
                        a2 = a89;
                        break label1;
                    }
                    try
                    {
                        s10 = a79.toString();
                    }
                    catch(java.net.MalformedURLException a90)
                    {
                        a2 = a90;
                        break label1;
                    }
                    try
                    {
                        a.showStatus(s10);
                    }
                    catch(java.net.MalformedURLException a91)
                    {
                        a2 = a91;
                        break label1;
                    }
                    try
                    {
                        a80 = System.out;
                    }
                    catch(java.net.MalformedURLException a92)
                    {
                        a2 = a92;
                        break label1;
                    }
                    try
                    {
                        a81 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a93)
                    {
                        a2 = a93;
                        break label1;
                    }
                    try
                    {
                        a82 = a81.append("com.cim.AIA.Exercise:  Unable to load image: ");
                    }
                    catch(java.net.MalformedURLException a94)
                    {
                        a2 = a94;
                        break label1;
                    }
                    try
                    {
                        a83 = a82.append((Object)a6);
                    }
                    catch(java.net.MalformedURLException a95)
                    {
                        a2 = a95;
                        break label1;
                    }
                    try
                    {
                        s11 = a83.toString();
                    }
                    catch(java.net.MalformedURLException a96)
                    {
                        a2 = a96;
                        break label1;
                    }
                    try
                    {
                        a80.println(s11);
                    }
                    catch(java.net.MalformedURLException a97)
                    {
                        a2 = a97;
                        break label1;
                    }
                }
                label28: try
                {
                    a21 = ((Object)a).getClass();
                    break label28;
                }
                catch(java.net.MalformedURLException a98)
                {
                    a2 = a98;
                    break label1;
                }
                label29: try
                {
                    a22 = new StringBuilder();
                    break label29;
                }
                catch(java.net.MalformedURLException a99)
                {
                    a2 = a99;
                    break label1;
                }
                label30: try
                {
                    a23 = a22.append("images/");
                    break label30;
                }
                catch(java.net.MalformedURLException a100)
                {
                    a2 = a100;
                    break label1;
                }
                label31: try
                {
                    a24 = a23.append(s1);
                    break label31;
                }
                catch(java.net.MalformedURLException a101)
                {
                    a2 = a101;
                    break label1;
                }
                label32: try
                {
                    s5 = a24.toString();
                    break label32;
                }
                catch(java.net.MalformedURLException a102)
                {
                    a2 = a102;
                    break label1;
                }
                label33: try
                {
                    a25 = a21.getResourceAsStream(s5);
                    break label33;
                }
                catch(java.net.MalformedURLException a103)
                {
                    a2 = a103;
                    break label1;
                }
                label34: try
                {
                    a26 = new StringBuilder();
                    break label34;
                }
                catch(java.net.MalformedURLException a104)
                {
                    a2 = a104;
                    break label1;
                }
                label35: try
                {
                    a27 = a26.append(s);
                    break label35;
                }
                catch(java.net.MalformedURLException a105)
                {
                    a2 = a105;
                    break label1;
                }
                label36: try
                {
                    a28 = a27.append(s1);
                    break label36;
                }
                catch(java.net.MalformedURLException a106)
                {
                    a2 = a106;
                    break label1;
                }
                label37: try
                {
                    s6 = a28.toString();
                    break label37;
                }
                catch(java.net.MalformedURLException a107)
                {
                    a2 = a107;
                    break label1;
                }
                label38: try
                {
                    a29 = new java.net.URL(s6);
                    break label38;
                }
                catch(java.net.MalformedURLException a108)
                {
                    a2 = a108;
                    break label1;
                }
                label39: try
                {
                    a30 = new StringBuilder();
                    break label39;
                }
                catch(java.net.MalformedURLException a109)
                {
                    a2 = a109;
                    break label1;
                }
                label40: try
                {
                    a31 = a.getCodeBase();
                    break label40;
                }
                catch(java.net.MalformedURLException a110)
                {
                    a2 = a110;
                    break label1;
                }
                label41: try
                {
                    a32 = a30.append((Object)a31);
                    break label41;
                }
                catch(java.net.MalformedURLException a111)
                {
                    a2 = a111;
                    break label1;
                }
                label42: try
                {
                    a33 = a32.append("images/");
                    break label42;
                }
                catch(java.net.MalformedURLException a112)
                {
                    a2 = a112;
                    break label1;
                }
                label43: try
                {
                    a34 = a33.append(s1);
                    break label43;
                }
                catch(java.net.MalformedURLException a113)
                {
                    a2 = a113;
                    break label1;
                }
                label44: try
                {
                    s7 = a34.toString();
                    break label44;
                }
                catch(java.net.MalformedURLException a114)
                {
                    a2 = a114;
                    break label1;
                }
                label45: try
                {
                    a35 = new java.net.URL(s7);
                    break label45;
                }
                catch(java.net.MalformedURLException a115)
                {
                    a2 = a115;
                    break label1;
                }
                label46: try
                {
                    a36 = com.cim.AIA.StreamImage.getImage(a25);
                    break label46;
                }
                catch(java.net.MalformedURLException a116)
                {
                    a2 = a116;
                    break label1;
                }
                label47: try
                {
                    this.incorrectImage = a36;
                    break label47;
                }
                catch(java.net.MalformedURLException a117)
                {
                    a2 = a117;
                    break label1;
                }
                label48: try
                {
                    a37 = this.incorrectImage;
                    break label48;
                }
                catch(java.net.MalformedURLException a118)
                {
                    a2 = a118;
                    break label1;
                }
                label49: {
                    java.io.PrintStream a119 = null;
                    StringBuilder a120 = null;
                    StringBuilder a121 = null;
                    String s12 = null;
                    StringBuilder a122 = null;
                    String s13 = null;
                    Object a123 = null;
                    java.awt.Image a124 = null;
                    if(a37 != null)
                    {
                        break label49;
                    }
                    try
                    {
                        a119 = System.out;
                    }
                    catch(java.net.MalformedURLException a125)
                    {
                        a2 = a125;
                        break label1;
                    }
                    try
                    {
                        a120 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a126)
                    {
                        a2 = a126;
                        break label1;
                    }
                    try
                    {
                        a121 = a120.append("Incorrect Image is loading from local...");
                    }
                    catch(java.net.MalformedURLException a127)
                    {
                        a2 = a127;
                        break label1;
                    }
                    try
                    {
                        s12 = a35.toString();
                    }
                    catch(java.net.MalformedURLException a128)
                    {
                        a2 = a128;
                        break label1;
                    }
                    try
                    {
                        a122 = a121.append(s12);
                    }
                    catch(java.net.MalformedURLException a129)
                    {
                        a2 = a129;
                        break label1;
                    }
                    try
                    {
                        s13 = a122.toString();
                    }
                    catch(java.net.MalformedURLException a130)
                    {
                        a2 = a130;
                        break label1;
                    }
                    try
                    {
                        a119.println(s13);
                    }
                    catch(java.net.MalformedURLException a131)
                    {
                        a2 = a131;
                        break label1;
                    }
                    try
                    {
                        a123 = a.getAppletContext();
                    }
                    catch(java.net.MalformedURLException a132)
                    {
                        a2 = a132;
                        break label1;
                    }
                    try
                    {
                        a124 = ((java.applet.AppletContext)a123).getImage(a35);
                    }
                    catch(java.net.MalformedURLException a133)
                    {
                        a2 = a133;
                        break label1;
                    }
                    try
                    {
                        this.incorrectImage = a124;
                    }
                    catch(java.net.MalformedURLException a134)
                    {
                        a2 = a134;
                        break label1;
                    }
                }
                label50: try
                {
                    a38 = this.incorrectImage;
                    break label50;
                }
                catch(java.net.MalformedURLException a135)
                {
                    a2 = a135;
                    break label1;
                }
                label54: {
                    StringBuilder a136 = null;
                    StringBuilder a137 = null;
                    StringBuilder a138 = null;
                    String s14 = null;
                    java.io.PrintStream a139 = null;
                    StringBuilder a140 = null;
                    StringBuilder a141 = null;
                    StringBuilder a142 = null;
                    String s15 = null;
                    label51: {
                        java.awt.Image a143 = null;
                        if(a38 == null)
                        {
                            break label51;
                        }
                        label52: try
                        {
                            a143 = this.incorrectImage;
                            break label52;
                        }
                        catch(java.net.MalformedURLException a144)
                        {
                            a2 = a144;
                            break label1;
                        }
                        label53: try
                        {
                            a1.addImage(a143, 0);
                            break label53;
                        }
                        catch(java.net.MalformedURLException a145)
                        {
                            a2 = a145;
                            break label1;
                        }
                        break label54;
                    }
                    try
                    {
                        a136 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a146)
                    {
                        a2 = a146;
                        break label1;
                    }
                    try
                    {
                        a137 = a136.append("com.cim.AIA.Exercise: Unable to load image: ");
                    }
                    catch(java.net.MalformedURLException a147)
                    {
                        a2 = a147;
                        break label1;
                    }
                    try
                    {
                        a138 = a137.append((Object)a29);
                    }
                    catch(java.net.MalformedURLException a148)
                    {
                        a2 = a148;
                        break label1;
                    }
                    try
                    {
                        s14 = a138.toString();
                    }
                    catch(java.net.MalformedURLException a149)
                    {
                        a2 = a149;
                        break label1;
                    }
                    try
                    {
                        a.showStatus(s14);
                    }
                    catch(java.net.MalformedURLException a150)
                    {
                        a2 = a150;
                        break label1;
                    }
                    try
                    {
                        a139 = System.out;
                    }
                    catch(java.net.MalformedURLException a151)
                    {
                        a2 = a151;
                        break label1;
                    }
                    try
                    {
                        a140 = new StringBuilder();
                    }
                    catch(java.net.MalformedURLException a152)
                    {
                        a2 = a152;
                        break label1;
                    }
                    try
                    {
                        a141 = a140.append("com.cim.AIA.Exercise: Unable to load image: ");
                    }
                    catch(java.net.MalformedURLException a153)
                    {
                        a2 = a153;
                        break label1;
                    }
                    try
                    {
                        a142 = a141.append((Object)a29);
                    }
                    catch(java.net.MalformedURLException a154)
                    {
                        a2 = a154;
                        break label1;
                    }
                    try
                    {
                        s15 = a142.toString();
                    }
                    catch(java.net.MalformedURLException a155)
                    {
                        a2 = a155;
                        break label1;
                    }
                    try
                    {
                        a139.println(s15);
                    }
                    catch(java.net.MalformedURLException a156)
                    {
                        a2 = a156;
                        break label1;
                    }
                }
                break label55;
            }
            StringBuilder a157 = new StringBuilder();
            StringBuilder a158 = a157.append("com.cim.AIA.Exercise: Invalid URL: ");
            StringBuilder a159 = a158.append((Object)a2);
            String s16 = a159.toString();
            a.showStatus(s16);
            java.io.PrintStream a160 = System.out;
            StringBuilder a161 = new StringBuilder();
            StringBuilder a162 = a161.append("com.cim.AIA.Exercise: Invalid URL: ");
            StringBuilder a163 = a162.append((Object)a2);
            String s17 = a163.toString();
            a160.println(s17);
        }
        try
        {
            a1.waitForAll();
        }
        catch(InterruptedException ignoredException)
        {
        }
    }
    
    protected void showDialog()
    {
        java.awt.Frame a = this.frame;
        java.awt.Toolkit a0 = a.getToolkit();
        java.awt.Dimension a1 = a0.getScreenSize();
        java.awt.Dialog a2 = this.dialog;
        int i = a1.width;
        java.awt.Dialog a3 = this.dialog;
        java.awt.Dimension a4 = a3.getSize();
        int i0 = a4.width;
        int i1 = i - i0;
        int i2 = i1 / 2;
        int i3 = a1.height;
        java.awt.Dialog a5 = this.dialog;
        java.awt.Dimension a6 = a5.getSize();
        int i4 = a6.height;
        int i5 = i3 - i4;
        int i6 = i5 / 2;
        a2.setLocation(i2, i6);
        java.awt.Dialog a7 = this.dialog;
        a7.setVisible(true);
    }
    
    public void start()
    {
        this.init(false);
        this.showDialog();
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.exerciseQuestions;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.exerciseQuestions;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.ExerciseQuestion dummy = (com.cim.AIA.ExerciseQuestion)a1;
                com.cim.AIA.ExerciseQuestion a2 = (com.cim.AIA.ExerciseQuestion)a1;
                a2.reinitialise();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
}