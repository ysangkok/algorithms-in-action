class AlignmentAnimationWindow$VariationMethod$1 implements java.awt.event.ItemListener {
    final AlignmentAnimationWindow val$this$0;
    final String val$name;
    final AlignmentAnimationWindow$VariationMethod this$1;
    
    AlignmentAnimationWindow$VariationMethod$1(AlignmentAnimationWindow$VariationMethod a, AlignmentAnimationWindow a0, String s)
    {
        this.this$1 = a;
        this.val$this$0 = a0;
        this.val$name = s;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        AlignmentAnimationWindow$VariationMethod a0 = this.this$1;
        AlignmentAnimationWindow a1 = a0.animWindow;
        a1.resetVariationButtons();
        int i = Alignment.runningMode;
        label0: {
            if(i != 1)
            {
                break label0;
            }
            AlignmentAnimationWindow$VariationMethod a2 = this.this$1;
            AlignmentAnimationWindow a3 = a2.this$0;
            com.cim.AIA.PresetStringArrayDataSelection a4 = a3.ds1;
            int i0 = a4.getState()?1:0;
            label1: {
                if(i0 != 0)
                {
                    break label1;
                }
                AlignmentAnimationWindow$VariationMethod a5 = this.this$1;
                AlignmentAnimationWindow a6 = a5.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a7 = a6.ds2;
                int i1 = a7.getState()?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
            }
            int i2 = Alignment.currentVariation;
            if(i2 != 1)
            {
                AlignmentAnimationWindow$VariationMethod a8 = this.this$1;
                AlignmentAnimationWindow a9 = a8.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a10 = a9.ds2;
                a10.setState(false);
                AlignmentAnimationWindow$VariationMethod a11 = this.this$1;
                AlignmentAnimationWindow a12 = a11.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a13 = a12.ds1;
                Object a14 = a13.getData();
                AlignmentAnimationWindow$VariationMethod a15 = this.this$1;
                AlignmentAnimationWindow a16 = a15.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a17 = a16.ds1;
                a17.setState(true);
                AlignmentAnimationWindow$VariationMethod a18 = this.this$1;
                AlignmentAnimationWindow a19 = a18.this$0;
                a19.setCurrentData((com.cim.AIA.Copyable)a14, true, true);
            }
            else
            {
                AlignmentAnimationWindow$VariationMethod a20 = this.this$1;
                AlignmentAnimationWindow a21 = a20.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a22 = a21.ds1;
                a22.setState(false);
                AlignmentAnimationWindow$VariationMethod a23 = this.this$1;
                AlignmentAnimationWindow a24 = a23.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a25 = a24.ds2;
                Object a26 = a25.getData();
                AlignmentAnimationWindow$VariationMethod a27 = this.this$1;
                AlignmentAnimationWindow a28 = a27.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a29 = a28.ds2;
                a29.setState(true);
                AlignmentAnimationWindow$VariationMethod a30 = this.this$1;
                AlignmentAnimationWindow a31 = a30.this$0;
                a31.setCurrentData((com.cim.AIA.Copyable)a26, true, true);
            }
        }
        int i3 = Alignment.runningMode;
        label2: {
            if(i3 != 2)
            {
                break label2;
            }
            AlignmentAnimationWindow$VariationMethod a32 = this.this$1;
            AlignmentAnimationWindow a33 = a32.this$0;
            com.cim.AIA.PresetStringArrayDataSelection a34 = a33.ds3;
            int i4 = a34.getState()?1:0;
            label3: {
                if(i4 != 0)
                {
                    break label3;
                }
                AlignmentAnimationWindow$VariationMethod a35 = this.this$1;
                AlignmentAnimationWindow a36 = a35.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a37 = a36.ds4;
                int i5 = a37.getState()?1:0;
                if(i5 == 0)
                {
                    break label2;
                }
            }
            int i6 = Alignment.currentVariation;
            if(i6 != 1)
            {
                AlignmentAnimationWindow$VariationMethod a38 = this.this$1;
                AlignmentAnimationWindow a39 = a38.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a40 = a39.ds4;
                a40.setState(false);
                AlignmentAnimationWindow$VariationMethod a41 = this.this$1;
                AlignmentAnimationWindow a42 = a41.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a43 = a42.ds3;
                Object a44 = a43.getData();
                AlignmentAnimationWindow$VariationMethod a45 = this.this$1;
                AlignmentAnimationWindow a46 = a45.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a47 = a46.ds3;
                a47.setState(true);
                AlignmentAnimationWindow$VariationMethod a48 = this.this$1;
                AlignmentAnimationWindow a49 = a48.this$0;
                a49.setCurrentData((com.cim.AIA.Copyable)a44, true, true);
            }
            else
            {
                AlignmentAnimationWindow$VariationMethod a50 = this.this$1;
                AlignmentAnimationWindow a51 = a50.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a52 = a51.ds3;
                a52.setState(false);
                AlignmentAnimationWindow$VariationMethod a53 = this.this$1;
                AlignmentAnimationWindow a54 = a53.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a55 = a54.ds4;
                Object a56 = a55.getData();
                AlignmentAnimationWindow$VariationMethod a57 = this.this$1;
                AlignmentAnimationWindow a58 = a57.this$0;
                com.cim.AIA.PresetStringArrayDataSelection a59 = a58.ds4;
                a59.setState(true);
                AlignmentAnimationWindow$VariationMethod a60 = this.this$1;
                AlignmentAnimationWindow a61 = a60.this$0;
                a61.setCurrentData((com.cim.AIA.Copyable)a56, true, true);
            }
        }
        AlignmentAnimationWindow$VariationMethod a62 = this.this$1;
        a62.setState(true);
        com.cim.AIA.ExplainationWindow a63 = AlignmentApplet.expWin;
        String s = AlignmentApplet.codeBaseString;
        AlignmentAnimationWindow$VariationMethod a64 = this.this$1;
        String s0 = a64.expName;
        a63.initialiseMainData(s, s0);
        AlignmentAnimationWindow$VariationMethod a65 = this.this$1;
        Alignment a66 = a65.alignment;
        AlignmentAnimationWindow$VariationMethod a67 = this.this$1;
        int i7 = a67.variationMethod;
        a66.setVariation(i7);
        AlignmentAnimationWindow$VariationMethod a68 = this.this$1;
        AlignmentAnimationWindow a69 = a68.this$0;
        a69.resetThread(true, true, false, false);
        AlignmentAnimationWindow$VariationMethod a70 = this.this$1;
        com.cim.AIA.AlgorithmWindow a71 = a70.algoWindow;
        AlignmentAnimationWindow$VariationMethod a72 = this.this$1;
        com.cim.AIA.LadderTree a73 = a72.ladderTree;
        a71.setLadderTree(a73);
        String s1 = this.val$name;
        com.cim.AIA.Log a74 = new com.cim.AIA.Log((byte)4, (byte)21, (String)null, s1);
        AlignmentAnimationWindow$VariationMethod a75 = this.this$1;
        AlignmentAnimationWindow a76 = a75.this$0;
        com.cim.AIA.Logger a77 = a76.getLogger();
        if(a77 != null)
        {
            AlignmentAnimationWindow$VariationMethod a78 = this.this$1;
            AlignmentAnimationWindow a79 = a78.this$0;
            com.cim.AIA.Logger a80 = a79.getLogger();
            a80.addLog(a74);
        }
    }
}