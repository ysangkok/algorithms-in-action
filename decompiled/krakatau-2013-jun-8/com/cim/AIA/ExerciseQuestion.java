package com.cim.AIA;

public class ExerciseQuestion implements java.awt.event.ItemListener {
    protected String questionString;
    protected java.util.Vector possibleAnswers;
    protected java.util.Vector correctAnswers;
    protected java.util.Vector canvases;
    protected boolean radioMode;
    protected java.awt.CheckboxGroup checkboxGroup;
    protected com.cim.AIA.NonRadioCheckboxGroup nonRadioCheckboxGroup;
    protected java.util.Vector checkboxes;
    protected java.util.Vector actionListeners;
    protected int numberOfItemsSelected;
    
    public ExerciseQuestion(String s, boolean b)
    {
        super();
        int i = b?1:0;
        this.radioMode = true;
        this.numberOfItemsSelected = 0;
        java.util.Vector a = new java.util.Vector();
        this.possibleAnswers = a;
        java.util.Vector a0 = new java.util.Vector();
        this.correctAnswers = a0;
        this.questionString = s;
        this.radioMode = i != 0;
        java.awt.CheckboxGroup a1 = new java.awt.CheckboxGroup();
        this.checkboxGroup = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.checkboxes = a2;
        com.cim.AIA.NonRadioCheckboxGroup a3 = new com.cim.AIA.NonRadioCheckboxGroup();
        this.nonRadioCheckboxGroup = a3;
        java.util.Vector a4 = new java.util.Vector();
        this.actionListeners = a4;
    }
    
    public ExerciseQuestion(String s, String s0, String s1, boolean b)
    {
        this(s, b);
        java.util.StringTokenizer a = new java.util.StringTokenizer(s0, "|");
        while(true)
        {
            int i = a.hasMoreTokens()?1:0;
            if(i == 0)
            {
                break;
            }
            else
            {
                java.util.Vector a0 = this.possibleAnswers;
                String s2 = a.nextToken();
                com.cim.AIA.ExerciseQuestionOption a1 = new com.cim.AIA.ExerciseQuestionOption(s2);
                a0.addElement((Object)a1);
            }
        }
        java.util.StringTokenizer a2 = new java.util.StringTokenizer(s1, "|");
        while(true)
        {
            int i0 = a2.hasMoreTokens()?1:0;
            if(i0 == 0)
            {
                return;
            }
            label0: {
                java.util.Vector a3 = null;
                String s3 = null;
                Integer a4 = null;
                try
                {
                    a3 = this.correctAnswers;
                }
                catch(NumberFormatException ignoredException)
                {
                    break label0;
                }
                try
                {
                    s3 = a2.nextToken();
                }
                catch(NumberFormatException ignoredException0)
                {
                    break label0;
                }
                try
                {
                    a4 = new Integer(s3);
                }
                catch(NumberFormatException ignoredException1)
                {
                    break label0;
                }
                try
                {
                    a3.addElement((Object)a4);
                    continue;
                }
                catch(NumberFormatException ignoredException2)
                {
                }
            }
            java.io.PrintStream a5 = System.out;
            StringBuilder a6 = new StringBuilder();
            StringBuilder a7 = a6.append("com.cim.AIA.ExerciseQuestion Incorrect Format of correct answer at question: '");
            StringBuilder a8 = a7.append(s);
            StringBuilder a9 = a8.append("'.");
            String s4 = a9.toString();
            a5.println(s4);
        }
    }
    
    public void addActionListener(java.awt.event.ActionListener a)
    {
        java.util.Vector a0 = this.actionListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addOption(com.cim.AIA.ExerciseQuestionOption a, boolean b)
    {
        java.util.Vector a0 = this.possibleAnswers;
        int i = b?1:0;
        a0.addElement((Object)a);
        if(i != 0)
        {
            java.util.Vector a1 = this.correctAnswers;
            java.util.Vector a2 = this.possibleAnswers;
            int i0 = a2.indexOf((Object)a);
            int i1 = i0 + 1;
            Integer a3 = new Integer(i1);
            a1.addElement((Object)a3);
        }
    }
    
    protected java.awt.Panel getAllOptionsPanel(int i, int i0, boolean b, java.awt.Image a, java.awt.Image a0)
    {
        int i1 = b?1:0;
        java.awt.Panel a1 = new java.awt.Panel();
        java.awt.GridBagLayout a2 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a3 = new java.awt.GridBagConstraints();
        a1.setLayout((java.awt.LayoutManager)a2);
        java.awt.Insets a4 = new java.awt.Insets(1, 1, 1, 1);
        a3.insets = a4;
        a3.anchor = 18;
        java.awt.Image a5 = a;
        java.awt.Image a6 = a0;
        int i2 = 0;
        while(true)
        {
            java.awt.Image a7 = null;
            java.awt.Image a8 = null;
            java.awt.Image a9 = null;
            java.awt.Image a10 = null;
            java.util.Vector a11 = this.possibleAnswers;
            java.awt.Image a12 = a5;
            java.awt.Image a13 = a6;
            int i3 = a11.size();
            java.awt.Image a14 = a12;
            java.awt.Image a15 = a13;
            java.awt.Image a16 = a14;
            java.awt.Image a17 = a15;
            if(i2 >= i3)
            {
                return a1;
            }
            else
            {
                a7 = a16;
                a8 = a17;
            }
            java.util.Vector a18 = this.possibleAnswers;
            java.awt.Image a19 = a7;
            java.awt.Image a20 = a8;
            Object a21 = a18.elementAt(i2);
            java.awt.Image a22 = a19;
            java.awt.Image a23 = a20;
            com.cim.AIA.ExerciseQuestionOption dummy = (com.cim.AIA.ExerciseQuestionOption)a21;
            com.cim.AIA.ExerciseQuestionOption a24 = (com.cim.AIA.ExerciseQuestionOption)a21;
            java.awt.Image a25 = a22;
            java.awt.Image a26 = a23;
            java.awt.Panel a27 = this.getOptionAndExplanationPanel(a24, i2, i1 != 0);
            java.awt.Image a28 = a25;
            java.awt.Image a29 = a26;
            java.awt.Image a30 = a28;
            java.awt.Image a31 = a29;
            com.cim.AIA.ExerciseQuestion$ImageCanvas a32 = new com.cim.AIA.ExerciseQuestion$ImageCanvas(this);
            java.awt.Image a33 = a30;
            java.awt.Image a34 = a31;
            a32.setSize(i, i0);
            java.awt.Image a35 = a33;
            java.awt.Image a36 = a34;
            label0: {
                java.awt.Image a37 = null;
                java.awt.Image a38 = null;
                java.awt.Image a39 = null;
                java.awt.Image a40 = null;
                int i4 = 0;
                java.awt.Image a41 = null;
                java.awt.Image a42 = null;
                java.awt.Image a43 = null;
                java.awt.Image a44 = null;
                java.awt.Image a45 = null;
                java.awt.Image a46 = null;
                java.awt.Image a47 = a35;
                java.awt.Image a48 = a36;
                java.awt.Image a49 = a35;
                java.awt.Image a50 = a36;
                if(i1 == 0)
                {
                    a9 = a49;
                    a10 = a50;
                    break label0;
                }
                else
                {
                    a37 = a47;
                    a38 = a48;
                }
                String s = a24.getOption();
                java.awt.Image a51 = a37;
                java.awt.Image a52 = a38;
                int i5 = this.isCorrectAnswer(s)?1:0;
                java.awt.Image a53 = a51;
                java.awt.Image a54 = a52;
                java.util.Vector a55 = this.checkboxes;
                java.awt.Image a56 = a53;
                java.awt.Image a57 = a54;
                Object a58 = a55.elementAt(i2);
                java.awt.Image a59 = a56;
                java.awt.Image a60 = a57;
                java.awt.Checkbox dummy0 = (java.awt.Checkbox)a58;
                java.awt.Checkbox a61 = (java.awt.Checkbox)a58;
                java.awt.Image a62 = a59;
                java.awt.Image a63 = a60;
                int i6 = a61.getState()?1:0;
                java.awt.Image a64 = a62;
                java.awt.Image a65 = a63;
                java.awt.Image a66 = a64;
                java.awt.Image a67 = a65;
                java.awt.Image a68 = a64;
                java.awt.Image a69 = a65;
                if(i5 == 0)
                {
                    java.awt.Image a70 = a68;
                    java.awt.Image a71 = a69;
                    java.awt.Color a72 = java.awt.Color.red;
                    java.awt.Image a73 = a70;
                    java.awt.Image a74 = a71;
                    a61.setBackground(a72);
                    a39 = a73;
                    a40 = a74;
                    i4 = 0;
                }
                else
                {
                    java.awt.Image a75 = a66;
                    java.awt.Image a76 = a67;
                    java.awt.Color a77 = java.awt.Color.green;
                    java.awt.Image a78 = a75;
                    java.awt.Image a79 = a76;
                    a61.setBackground(a77);
                    java.awt.Image a80 = a78;
                    java.awt.Image a81 = a79;
                    a39 = a80;
                    a40 = a81;
                    i4 = i5;
                }
                label1: {
                    java.awt.Image a82 = null;
                    java.awt.Image a83 = null;
                    java.awt.Image a84 = null;
                    java.awt.Image a85 = null;
                    java.awt.Image a86 = a39;
                    java.awt.Image a87 = a40;
                    java.awt.Image a88 = a39;
                    java.awt.Image a89 = a40;
                    if(i4 == 0)
                    {
                        a41 = a88;
                        a42 = a89;
                        break label1;
                    }
                    else
                    {
                        a82 = a86;
                        a83 = a87;
                    }
                    java.awt.Image a90 = a82;
                    java.awt.Image a91 = a83;
                    java.awt.Image a92 = a82;
                    java.awt.Image a93 = a83;
                    if(i6 == 0)
                    {
                        a41 = a92;
                        a42 = a93;
                        break label1;
                    }
                    else
                    {
                        a84 = a90;
                        a85 = a91;
                    }
                    java.awt.Image a94 = a84;
                    java.awt.Image a95 = a85;
                    java.awt.Image a96 = a85;
                    if(a84 == null)
                    {
                        java.awt.Image a97 = a96;
                        java.awt.Color a98 = java.awt.Color.green;
                        java.awt.Image a99 = a97;
                        a32.setBackground(a98);
                        java.awt.Image a100 = a99;
                        a9 = null;
                        a10 = a100;
                        break label0;
                    }
                    else
                    {
                        java.awt.Image a101 = a94;
                        java.awt.Image a102 = a95;
                        a32.setImage(a101);
                        java.awt.Image a103 = a101;
                        java.awt.Image a104 = a102;
                        a9 = a103;
                        a10 = a104;
                        break label0;
                    }
                }
                java.awt.Image a105 = a41;
                java.awt.Image a106 = a42;
                java.awt.Image a107 = a41;
                java.awt.Image a108 = a42;
                if(i6 == 0)
                {
                    a9 = a107;
                    a10 = a108;
                    break label0;
                }
                else
                {
                    a43 = a105;
                    a44 = a106;
                }
                java.awt.Image a109 = a43;
                java.awt.Image a110 = a44;
                java.awt.Image a111 = a43;
                java.awt.Image a112 = a44;
                if(i4 != 0)
                {
                    a9 = a111;
                    a10 = a112;
                    break label0;
                }
                else
                {
                    a45 = a109;
                    a46 = a110;
                }
                java.awt.Image a113 = a45;
                java.awt.Image a114 = a46;
                java.awt.Image a115 = a45;
                if(a46 == null)
                {
                    java.awt.Image a116 = a115;
                    java.awt.Color a117 = java.awt.Color.red;
                    java.awt.Image a118 = a116;
                    a32.setBackground(a117);
                    a9 = a118;
                    a10 = null;
                }
                else
                {
                    java.awt.Image a119 = a113;
                    java.awt.Image a120 = a114;
                    a32.setImage(a120);
                    java.awt.Image a121 = a119;
                    java.awt.Image a122 = a120;
                    a9 = a121;
                    a10 = a122;
                }
            }
            a3.gridwidth = i;
            java.awt.Image a123 = a9;
            java.awt.Image a124 = a10;
            a2.setConstraints((java.awt.Component)a32, a3);
            java.awt.Image a125 = a123;
            java.awt.Image a126 = a124;
            java.awt.Component a127 = a1.add((java.awt.Component)a32);
            java.awt.Image a128 = a125;
            java.awt.Image a129 = a126;
            a3.gridwidth = 0;
            java.awt.Image a130 = a128;
            java.awt.Image a131 = a129;
            a2.setConstraints((java.awt.Component)a27, a3);
            java.awt.Image a132 = a130;
            java.awt.Image a133 = a131;
            java.awt.Component a134 = a1.add((java.awt.Component)a27);
            java.awt.Image a135 = a132;
            java.awt.Image a136 = a133;
            int i7 = i2 + 1;
            a5 = a135;
            a6 = a136;
            i2 = i7;
        }
    }
    
    protected java.awt.Panel getOptionAndExplanationPanel(com.cim.AIA.ExerciseQuestionOption a, int i, boolean b)
    {
        String s = null;
        int i0 = b?1:0;
        java.awt.Panel a0 = new java.awt.Panel();
        java.awt.GridBagLayout a1 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a2 = new java.awt.GridBagConstraints();
        a0.setLayout((java.awt.LayoutManager)a1);
        java.awt.Insets a3 = new java.awt.Insets(0, 2, 0, 2);
        a2.insets = a3;
        a2.anchor = 18;
        a2.gridwidth = 0;
        a2.fill = 0;
        java.awt.Panel a4 = this.getOptionPanel(a, i, i0 != 0);
        a1.setConstraints((java.awt.Component)a4, a2);
        java.awt.Component a5 = a0.add((java.awt.Component)a4);
        if(i0 == 0)
        {
            s = "";
        }
        else
        {
            String s0 = a.getExplanation();
            s = s0;
        }
        com.cim.common.MultiLineLabel a6 = new com.cim.common.MultiLineLabel(s, 1, 1, 0);
        a2.anchor = 17;
        a2.fill = 0;
        a1.setConstraints((java.awt.Component)a6, a2);
        java.awt.Component a7 = a0.add((java.awt.Component)a6);
        return a0;
    }
    
    protected java.awt.Panel getOptionPanel(com.cim.AIA.ExerciseQuestionOption a, int i, boolean b)
    {
        java.awt.Checkbox a0 = null;
        int i0 = b?1:0;
        java.awt.Panel a1 = new java.awt.Panel();
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                java.util.Vector a2 = this.checkboxes;
                Object a3 = a2.elementAt(i);
                java.awt.Checkbox dummy = (java.awt.Checkbox)a3;
                java.awt.Checkbox a4 = (java.awt.Checkbox)a3;
                a0 = a4;
                break label1;
            }
            String s = a.getOption();
            java.awt.Checkbox a5 = new java.awt.Checkbox(s, false);
            a5.addItemListener((java.awt.event.ItemListener)this);
            java.util.Vector a6 = this.checkboxes;
            a6.addElement((Object)a5);
            int i1 = this.radioMode?1:0;
            if(i1 == 0)
            {
                com.cim.AIA.NonRadioCheckboxGroup a7 = this.nonRadioCheckboxGroup;
                a7.add(a5);
                a0 = a5;
            }
            else
            {
                java.awt.CheckboxGroup a8 = this.checkboxGroup;
                a5.setCheckboxGroup(a8);
                a0 = a5;
            }
        }
        java.awt.Component a9 = a1.add((java.awt.Component)a0);
        return a1;
    }
    
    public java.awt.Panel getPanel(boolean b)
    {
        java.awt.Panel a = this.getPanel(30, 30, b, (java.awt.Image)null, (java.awt.Image)null);
        return a;
    }
    
    public java.awt.Panel getPanel(int i, int i0, boolean b, java.awt.Image a, java.awt.Image a0)
    {
        int i1 = b?1:0;
        java.awt.Panel a1 = new java.awt.Panel();
        java.awt.GridBagLayout a2 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a3 = new java.awt.GridBagConstraints();
        a1.setLayout((java.awt.LayoutManager)a2);
        java.awt.Insets a4 = new java.awt.Insets(2, 2, 2, 2);
        a3.insets = a4;
        a3.gridwidth = 0;
        a3.anchor = 11;
        String s = this.questionString;
        com.cim.common.MultiLineLabel a5 = new com.cim.common.MultiLineLabel(s, 1, 1, 1);
        java.awt.Font a6 = new java.awt.Font("Helvetica", 1, 14);
        a5.setFont(a6);
        a2.setConstraints((java.awt.Component)a5, a3);
        java.awt.Component a7 = a1.add((java.awt.Component)a5);
        a3.anchor = 18;
        java.awt.Panel a8 = this.getAllOptionsPanel(i, i0, i1 != 0, a, a0);
        a2.setConstraints((java.awt.Component)a8, a3);
        java.awt.Component a9 = a1.add((java.awt.Component)a8);
        return a1;
    }
    
    public String getQuestionDescription()
    {
        String s = this.questionString;
        return s;
    }
    
    protected void informActionListeners()
    {
        int i = this.numberOfItemsSelected;
        int i0 = this.numberOfItemsSelected;
        java.awt.event.ActionEvent a = new java.awt.event.ActionEvent((Object)this, i, "", i0);
        int i1 = 0;
        while(true)
        {
            java.util.Vector a0 = this.actionListeners;
            int i2 = a0.size();
            if(i1 >= i2)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.actionListeners;
                Object a2 = a1.elementAt(i1);
                java.awt.event.ActionListener dummy = (java.awt.event.ActionListener)a2;
                ((java.awt.event.ActionListener)a2).actionPerformed(a);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    protected boolean isCorrectAnswer(String s)
    {
        int i = 0;
        while(true)
        {
            int i0 = 0;
            java.util.Vector a = this.correctAnswers;
            int i1 = a.size();
            label1: {
                label0: {
                    if(i < i1)
                    {
                        break label0;
                    }
                    i0 = 0;
                    break label1;
                }
                java.util.Vector a0 = this.correctAnswers;
                Object a1 = a0.elementAt(i);
                Integer dummy = (Integer)a1;
                Integer a2 = (Integer)a1;
                int i2 = a2.intValue();
                int i3 = i2 - 1;
                java.util.Vector a3 = this.possibleAnswers;
                Object a4 = a3.elementAt(i3);
                com.cim.AIA.ExerciseQuestionOption dummy0 = (com.cim.AIA.ExerciseQuestionOption)a4;
                com.cim.AIA.ExerciseQuestionOption a5 = (com.cim.AIA.ExerciseQuestionOption)a4;
                String s0 = a5.getOption();
                int i4 = s.equals((Object)s0)?1:0;
                if(i4 == 0)
                {
                    int i5 = i + 1;
                    i = i5;
                    continue;
                }
                i0 = 1;
            }
            return i0 != 0;
        }
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        Object a0 = a.getSource();
        java.awt.Checkbox dummy = (java.awt.Checkbox)a0;
        java.awt.Checkbox a1 = (java.awt.Checkbox)a0;
        int i = a1.getState()?1:0;
        if(i == 0)
        {
            int i0 = this.numberOfItemsSelected;
            int i1 = i0 - 1;
            this.numberOfItemsSelected = i1;
        }
        else
        {
            int i2 = this.numberOfItemsSelected;
            int i3 = i2 + 1;
            this.numberOfItemsSelected = i3;
        }
        this.informActionListeners();
    }
    
    public void reinitialise()
    {
        java.awt.CheckboxGroup a = new java.awt.CheckboxGroup();
        this.checkboxGroup = a;
        java.util.Vector a0 = new java.util.Vector();
        this.checkboxes = a0;
        com.cim.AIA.NonRadioCheckboxGroup a1 = new com.cim.AIA.NonRadioCheckboxGroup();
        this.nonRadioCheckboxGroup = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.actionListeners = a2;
    }
    
    public void removeAllActionListeners()
    {
        java.util.Vector a = this.actionListeners;
        a.removeAllElements();
    }
}