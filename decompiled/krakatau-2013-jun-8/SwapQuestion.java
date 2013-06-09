public class SwapQuestion extends com.cim.AIA.Question {
    final protected static String NAME;
    final protected static String INSTRUCTIONS;
    final protected static int ATTEMPTS_ALLOWED = 3;
    final protected static String TOO_MANY;
    final protected static String CORRECT_MESSAGE;
    final protected static String INCORRECT_MESSAGE;
    protected int first;
    protected int second;
    
    public SwapQuestion(int i, int i0)
    {
        String s = SwapQuestion.NAME;
        String s0 = SwapQuestion.INSTRUCTIONS;
        super(s, s0, 3);
        this.first = i;
        this.second = i0;
        String s1 = SwapQuestion.TOO_MANY;
        this.tooManyAttemptsMessage = s1;
        String s2 = SwapQuestion.CORRECT_MESSAGE;
        this.correctMessage = s2;
        String s3 = SwapQuestion.INCORRECT_MESSAGE;
        this.inCorrectMessage = s3;
    }
    
    protected boolean isCorrect(java.util.Vector a)
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            int i3 = 0;
            int i4 = a.size();
            int i5 = i;
            int i6 = i0;
            label4: {
                int i7 = 0;
                label1: {
                    int i8 = 0;
                    int i9 = 0;
                    int i10 = 0;
                    int i11 = 0;
                    int i12 = 0;
                    label0: {
                        int i13 = i5;
                        int i14 = i6;
                        if(i1 < i4)
                        {
                            i8 = i13;
                            i9 = i14;
                            break label0;
                        }
                        i7 = 0;
                        break label1;
                    }
                    Object a0 = a.elementAt(i1);
                    int i15 = i8;
                    int i16 = i9;
                    com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a0;
                    int i17 = i15;
                    int i18 = i16;
                    com.cim.AIA.Selectable dummy0 = (com.cim.AIA.Selectable)a0;
                    int i19 = i17;
                    int i20 = i18;
                    int i21 = ((com.cim.AIA.Selectable)a0).getIdentifier();
                    int i22 = i19;
                    int i23 = i20;
                    int i24 = this.first;
                    int i25 = i22;
                    int i26 = i23;
                    label3: {
                        int i27 = 0;
                        int i28 = 0;
                        label2: {
                            int i29 = 0;
                            int i30 = 0;
                            int i31 = 0;
                            int i32 = i25;
                            int i33 = i26;
                            int i34 = i25;
                            int i35 = i26;
                            if(i21 != i24)
                            {
                                i27 = i34;
                                i28 = i35;
                                break label2;
                            }
                            else
                            {
                                i29 = i32;
                                i30 = i33;
                            }
                            int i36 = i30;
                            int i37 = i29;
                            int i38 = i30;
                            if(i29 != 0)
                            {
                                i27 = i37;
                                i28 = i38;
                                break label2;
                            }
                            else
                            {
                                i31 = i36;
                            }
                            i10 = 1;
                            i11 = i31;
                            break label3;
                        }
                        int i39 = ((com.cim.AIA.Selectable)a0).getIdentifier();
                        int i40 = i27;
                        int i41 = i28;
                        int i42 = this.second;
                        int i43 = i40;
                        int i44 = i41;
                        int i45 = i43;
                        int i46 = i43;
                        int i47 = i44;
                        if(i39 != i42)
                        {
                            i10 = i46;
                            i11 = i47;
                        }
                        else
                        {
                            int i48 = i45;
                            i10 = i48;
                            i11 = 1;
                        }
                    }
                    int i49 = i11;
                    int i50 = i10;
                    int i51 = i11;
                    if(i10 == 0)
                    {
                        i2 = i50;
                        i3 = i51;
                        break label4;
                    }
                    else
                    {
                        i12 = i49;
                    }
                    int i52 = i12;
                    if(i12 == 0)
                    {
                        i2 = 1;
                        i3 = i52;
                        break label4;
                    }
                    i7 = 1;
                }
                return i7 != 0;
            }
            int i53 = i1 + 1;
            i = i2;
            i0 = i3;
            i1 = i53;
        }
    }
    
    protected boolean isInCorrect(java.util.Vector a)
    {
        int i = 0;
        int i0 = a.size();
        label2: {
            label1: {
                label0: {
                    if(i0 < 2)
                    {
                        break label0;
                    }
                    int i1 = this.isCorrect(a)?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                i = 0;
                break label2;
            }
            i = 1;
        }
        return i != 0;
    }
    
    protected void tooManyWrongAnswers()
    {
        com.cim.AIA.AnimationWindow a = SwapQuestion.animationWindow;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            String s = localization.Messages.getString("SwapQuestion.6");
            a0.println(s);
        }
        else
        {
            com.cim.AIA.AnimationWindow a1 = SwapQuestion.animationWindow;
            com.cim.AIA.ModeEvent a2 = new com.cim.AIA.ModeEvent((Object)this, 12356);
            a1.informModeListeners(a2);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("SwapQuestion.0");
        SwapQuestion.NAME = s;
        String s0 = localization.Messages.getString("SwapQuestion.1");
        SwapQuestion.INSTRUCTIONS = s0;
        StringBuilder a = new StringBuilder();
        String s1 = localization.Messages.getString("SwapQuestion.2");
        StringBuilder a0 = a.append(s1);
        String s2 = localization.Messages.getString("SwapQuestion.3");
        StringBuilder a1 = a0.append(s2);
        String s3 = a1.toString();
        SwapQuestion.TOO_MANY = s3;
        String s4 = localization.Messages.getString("SwapQuestion.4");
        SwapQuestion.CORRECT_MESSAGE = s4;
        String s5 = localization.Messages.getString("SwapQuestion.5");
        SwapQuestion.INCORRECT_MESSAGE = s5;
    }
}