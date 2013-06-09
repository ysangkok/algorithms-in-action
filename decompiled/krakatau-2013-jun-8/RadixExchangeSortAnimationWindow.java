public class RadixExchangeSortAnimationWindow extends com.cim.AIA.AnimationWindow {
    final private static long serialVersionUID = 5946860209465611136L;
    final protected static String BUILD_DATE = "25/05/1999";
    final protected static String BUILD_BY = "T.Witham";
    final protected static String FRAME_TITLE;
    final public static int[] DEFAULT_DATA;
    final public static int[] SORTED_DATA;
    final public static int[] REVERSE_SORTED_DATA;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int minSize;
    protected int maxSize;
    
    public String getAlgorithmName()
    {
        return "AIA: Radix Exchange Sort";
    }
    
    public RadixExchangeSortAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.minimumNumberOfElements = 3;
        this.maximumNumberOfElements = 20;
        this.minSize = 1;
        this.maxSize = 99;
        this.buildDate = "25/05/1999";
        this.buildBy = "T.Witham";
        String s = RadixExchangeSortAnimationWindow.FRAME_TITLE;
        this.frameTitle = s;
        String s0 = RadixExchangeSortAnimationWindow.DEFAULT_LABEL;
        int[] a2 = RadixExchangeSortAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a3 = new com.cim.AIA.PresetIntArrayDataSelection(s0, true, (com.cim.AIA.AnimationWindow)this, a2);
        this.addDataSelection((com.cim.AIA.DataSelection)a3);
        String s1 = RadixExchangeSortAnimationWindow.SORTED_LABEL;
        int[] a4 = RadixExchangeSortAnimationWindow.SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a5 = new com.cim.AIA.PresetIntArrayDataSelection(s1, false, (com.cim.AIA.AnimationWindow)this, a4);
        this.addDataSelection((com.cim.AIA.DataSelection)a5);
        String s2 = RadixExchangeSortAnimationWindow.REVERSE_SORTED_LABEL;
        int[] a6 = RadixExchangeSortAnimationWindow.REVERSE_SORTED_DATA;
        com.cim.AIA.PresetIntArrayDataSelection a7 = new com.cim.AIA.PresetIntArrayDataSelection(s2, false, (com.cim.AIA.AnimationWindow)this, a6);
        this.addDataSelection((com.cim.AIA.DataSelection)a7);
        String s3 = RadixExchangeSortAnimationWindow.SAME_LABEL;
        int i = this.minimumNumberOfElements;
        int i0 = this.maximumNumberOfElements;
        int i1 = this.minSize;
        int i2 = this.maxSize;
        com.cim.AIA.RandomSameIntArrayDataSelection a8 = new com.cim.AIA.RandomSameIntArrayDataSelection(s3, false, (com.cim.AIA.AnimationWindow)this, i, i0, i1, i2);
        this.addDataSelection((com.cim.AIA.DataSelection)a8);
        String s4 = RadixExchangeSortAnimationWindow.RANDOM_LABEL;
        int i3 = this.minimumNumberOfElements;
        int i4 = this.maximumNumberOfElements;
        int i5 = this.minSize;
        int i6 = this.maxSize;
        com.cim.AIA.RandomIntArrayDataSelection a9 = new com.cim.AIA.RandomIntArrayDataSelection(s4, false, (com.cim.AIA.AnimationWindow)this, i3, i4, i5, i6);
        this.addDataSelection((com.cim.AIA.DataSelection)a9);
        StringBuilder a10 = new StringBuilder();
        String s5 = RadixExchangeSortAnimationWindow.USER_SELECTION_LABEL;
        StringBuilder a11 = a10.append(s5);
        StringBuilder a12 = a11.append("...");
        String s6 = a12.toString();
        String s7 = RadixExchangeSortAnimationWindow.USER_SELECTION_LABEL;
        int i7 = this.minSize;
        int i8 = this.maxSize;
        int i9 = this.minimumNumberOfElements;
        int i10 = this.maximumNumberOfElements;
        com.cim.AIA.UserSelectionIntArrayDataSelection a13 = new com.cim.AIA.UserSelectionIntArrayDataSelection(s6, false, (com.cim.AIA.AnimationWindow)this, s7, i7, i8, i9, i10);
        this.addDataSelection((com.cim.AIA.DataSelection)a13);
        String s8 = com.cim.AIA.AnimationWindow.STEP_LABEL;
        String s9 = com.cim.AIA.AnimationWindow.STEP_INSTRUCTIONS;
        com.cim.AIA.StepButton a14 = new com.cim.AIA.StepButton(s8, s9, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a14);
        String s10 = com.cim.AIA.AnimationWindow.BACK_LABEL;
        String s11 = com.cim.AIA.AnimationWindow.BACK_INSTRUCTIONS;
        com.cim.AIA.BackButton a15 = new com.cim.AIA.BackButton(s10, s11, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a15);
        String s12 = com.cim.AIA.AnimationWindow.RUN_LABEL;
        String s13 = com.cim.AIA.AnimationWindow.RUN_INSTRUCTIONS;
        com.cim.AIA.RunButton a16 = new com.cim.AIA.RunButton(s12, s13, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a16);
        String s14 = com.cim.AIA.AnimationWindow.PAUSE_LABEL;
        String s15 = com.cim.AIA.AnimationWindow.PAUSE_INSTRUCTIONS;
        com.cim.AIA.PauseButton a17 = new com.cim.AIA.PauseButton(s14, s15, (com.cim.AIA.Controlable)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a17);
        String s16 = com.cim.AIA.AnimationWindow.RESET_LABEL;
        String s17 = com.cim.AIA.AnimationWindow.RESET_INSTRUCTIONS;
        com.cim.AIA.ResetButton a18 = new com.cim.AIA.ResetButton(s16, s17, (com.cim.AIA.AnimationWindow)this, a0);
        this.addControlButton((com.cim.AIA.ControlButton)a18);
        com.cim.AIA.SelfTestModeSelection a19 = this.selfTestMode;
        a19.setEnabled(false);
    }
    
    static
    {
        String s = localization.Messages.getString("RadixExchangeSortAnimationWindow.2");
        RadixExchangeSortAnimationWindow.FRAME_TITLE = s;
        int[] a = new int[15];
        a[0] = 75;
        a[1] = 5;
        a[2] = 70;
        a[3] = 10;
        a[4] = 65;
        a[5] = 15;
        a[6] = 60;
        a[7] = 20;
        a[8] = 55;
        a[9] = 25;
        a[10] = 50;
        a[11] = 30;
        a[12] = 45;
        a[13] = 35;
        a[14] = 40;
        RadixExchangeSortAnimationWindow.DEFAULT_DATA = a;
        int[] a0 = new int[15];
        a0[0] = 5;
        a0[1] = 10;
        a0[2] = 15;
        a0[3] = 20;
        a0[4] = 25;
        a0[5] = 30;
        a0[6] = 35;
        a0[7] = 40;
        a0[8] = 45;
        a0[9] = 50;
        a0[10] = 55;
        a0[11] = 60;
        a0[12] = 65;
        a0[13] = 70;
        a0[14] = 75;
        RadixExchangeSortAnimationWindow.SORTED_DATA = a0;
        int[] a1 = new int[15];
        a1[0] = 75;
        a1[1] = 70;
        a1[2] = 65;
        a1[3] = 60;
        a1[4] = 55;
        a1[5] = 50;
        a1[6] = 45;
        a1[7] = 40;
        a1[8] = 35;
        a1[9] = 30;
        a1[10] = 25;
        a1[11] = 20;
        a1[12] = 15;
        a1[13] = 10;
        a1[14] = 5;
        RadixExchangeSortAnimationWindow.REVERSE_SORTED_DATA = a1;
    }
}