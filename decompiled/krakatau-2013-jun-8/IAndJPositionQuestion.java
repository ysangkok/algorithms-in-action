public class IAndJPositionQuestion extends com.cim.AIA.Question {
    final public static String QUESTION_J_LABEL;
    final public static String QUESTION_SWAP_LABEL;
    final protected static String QUESTION_NAME;
    final protected static int ATTEMPTS_ALLOWABLE = 3;
    final protected static String CORRECT;
    final protected static String INCORRECT;
    final protected static String TOO_MANY;
    
    public IAndJPositionQuestion()
    {
        String s = IAndJPositionQuestion.QUESTION_NAME;
        String s0 = IAndJPositionQuestion.QUESTION_J_LABEL;
        super(s, s0, 3);
        String s1 = IAndJPositionQuestion.CORRECT;
        this.correctMessage = s1;
        String s2 = IAndJPositionQuestion.INCORRECT;
        this.inCorrectMessage = s2;
        String s3 = IAndJPositionQuestion.TOO_MANY;
        this.tooManyAttemptsMessage = s3;
        this.attemtpsAllowable = 3;
        this.inCorrectModalMode = false;
        this.displayInstructionsAgainIfWrong = false;
    }
    
    public void addAnswer(Integer a)
    {
        ((com.cim.AIA.Question)this).addAnswer((Object)a);
    }
    
    protected void gotCorrectAnswer()
    {
    }
    
    protected void gotInCorrectAnswer()
    {
    }
    
    protected boolean isCorrect(java.util.Vector a)
    {
        int i = 0;
        int i0 = a.size();
        java.util.Vector a0 = this.answers;
        int i1 = a0.size();
        label1: {
            int i2 = 0;
            label0: {
                if(i0 >= i1)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            java.util.Vector a1 = new java.util.Vector();
            int i3 = 0;
            int i4 = 0;
            while(true)
            {
                int i5 = 0;
                int i6 = 0;
                int i7 = a.size();
                i2 = i3;
                int i8 = i2;
                if(i4 >= i7)
                {
                    break;
                }
                else
                {
                    i5 = i8;
                }
                Object a2 = a.elementAt(i4);
                int i9 = i5;
                com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a2;
                int i10 = i9;
                com.cim.AIA.Selectable dummy0 = (com.cim.AIA.Selectable)a2;
                int i11 = i10;
                int i12 = i11;
                int i13 = 0;
                label2: {
                    Integer a3 = null;
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = 0;
                    while(true)
                    {
                        int i17 = 0;
                        java.util.Vector a4 = this.answers;
                        int i18 = i12;
                        int i19 = a4.size();
                        int i20 = i18;
                        int i21 = i20;
                        int i22 = i20;
                        if(i13 >= i19)
                        {
                            i6 = i22;
                            break label2;
                        }
                        else
                        {
                            i17 = i21;
                        }
                        java.util.Vector a5 = this.answers;
                        int i23 = i17;
                        Object a6 = a5.elementAt(i13);
                        int i24 = i23;
                        Integer dummy1 = (Integer)a6;
                        a3 = (Integer)a6;
                        int i25 = i24;
                        int i26 = a3.intValue();
                        int i27 = i25;
                        int i28 = ((com.cim.AIA.Selectable)a2).getIdentifier();
                        int i29 = i27;
                        int i30 = i29;
                        int i31 = i29;
                        if(i26 != i28)
                        {
                            int i32 = i31;
                            int i33 = i13 + 1;
                            i12 = i32;
                            i13 = i33;
                        }
                        else
                        {
                            i14 = i30;
                            break;
                        }
                    }
                    int i34 = i14;
                    int i35 = 1;
                    int i36 = 0;
                    while(true)
                    {
                        int i37 = 0;
                        int i38 = 0;
                        int i39 = 0;
                        int i40 = 0;
                        int i41 = a1.size();
                        int i42 = i34;
                        i15 = i35;
                        int i43 = i42;
                        int i44 = i15;
                        int i45 = i42;
                        if(i36 >= i41)
                        {
                            i16 = i45;
                            break;
                        }
                        else
                        {
                            i37 = i43;
                            i38 = i44;
                        }
                        Object a7 = a1.elementAt(i36);
                        int i46 = i37;
                        int i47 = i38;
                        Integer dummy2 = (Integer)a7;
                        Integer a8 = (Integer)a7;
                        int i48 = i46;
                        int i49 = i47;
                        int i50 = a8.intValue();
                        int i51 = i48;
                        int i52 = i49;
                        int i53 = a3.intValue();
                        int i54 = i51;
                        int i55 = i52;
                        int i56 = i54;
                        int i57 = i54;
                        int i58 = i55;
                        if(i50 != i53)
                        {
                            i39 = i57;
                            i40 = i58;
                        }
                        else
                        {
                            int i59 = i56;
                            i39 = i59;
                            i40 = 0;
                        }
                        int i60 = i36 + 1;
                        i34 = i39;
                        i35 = i40;
                        i36 = i60;
                    }
                    int i61 = i16;
                    if(i15 == 0)
                    {
                        i6 = i61;
                    }
                    else
                    {
                        int i62 = i16 + 1;
                        a1.addElement((Object)a3);
                        i6 = i62;
                    }
                }
                int i63 = i4 + 1;
                i3 = i6;
                i4 = i63;
            }
            java.util.Vector a9 = this.answers;
            int i64 = a9.size();
            i = i2 < i64?0:1;
        }
        return i != 0;
    }
    
    protected boolean isInCorrect(java.util.Vector a)
    {
        int i = 0;
        int i0 = a.size();
        java.util.Vector a0 = this.answers;
        int i1 = a0.size();
        label1: {
            int i2 = 0;
            label0: {
                if(i0 >= i1)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            java.util.Vector a1 = new java.util.Vector();
            int i3 = 0;
            int i4 = 0;
            while(true)
            {
                int i5 = 0;
                int i6 = 0;
                int i7 = a.size();
                i2 = i3;
                int i8 = i2;
                if(i4 >= i7)
                {
                    break;
                }
                else
                {
                    i5 = i8;
                }
                Object a2 = a.elementAt(i4);
                int i9 = i5;
                com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a2;
                int i10 = i9;
                com.cim.AIA.Selectable dummy0 = (com.cim.AIA.Selectable)a2;
                int i11 = i10;
                int i12 = i11;
                int i13 = 0;
                label2: {
                    Integer a3 = null;
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = 0;
                    while(true)
                    {
                        int i17 = 0;
                        java.util.Vector a4 = this.answers;
                        int i18 = i12;
                        int i19 = a4.size();
                        int i20 = i18;
                        int i21 = i20;
                        int i22 = i20;
                        if(i13 >= i19)
                        {
                            i6 = i22;
                            break label2;
                        }
                        else
                        {
                            i17 = i21;
                        }
                        java.util.Vector a5 = this.answers;
                        int i23 = i17;
                        Object a6 = a5.elementAt(i13);
                        int i24 = i23;
                        Integer dummy1 = (Integer)a6;
                        a3 = (Integer)a6;
                        int i25 = i24;
                        int i26 = a3.intValue();
                        int i27 = i25;
                        int i28 = ((com.cim.AIA.Selectable)a2).getIdentifier();
                        int i29 = i27;
                        int i30 = i29;
                        int i31 = i29;
                        if(i26 != i28)
                        {
                            int i32 = i31;
                            int i33 = i13 + 1;
                            i12 = i32;
                            i13 = i33;
                        }
                        else
                        {
                            i14 = i30;
                            break;
                        }
                    }
                    int i34 = i14;
                    int i35 = 1;
                    int i36 = 0;
                    while(true)
                    {
                        int i37 = 0;
                        int i38 = 0;
                        int i39 = 0;
                        int i40 = 0;
                        int i41 = a1.size();
                        int i42 = i34;
                        i15 = i35;
                        int i43 = i42;
                        int i44 = i15;
                        int i45 = i42;
                        if(i36 >= i41)
                        {
                            i16 = i45;
                            break;
                        }
                        else
                        {
                            i37 = i43;
                            i38 = i44;
                        }
                        Object a7 = a1.elementAt(i36);
                        int i46 = i37;
                        int i47 = i38;
                        Integer dummy2 = (Integer)a7;
                        Integer a8 = (Integer)a7;
                        int i48 = i46;
                        int i49 = i47;
                        int i50 = a8.intValue();
                        int i51 = i48;
                        int i52 = i49;
                        int i53 = a3.intValue();
                        int i54 = i51;
                        int i55 = i52;
                        int i56 = i54;
                        int i57 = i54;
                        int i58 = i55;
                        if(i50 != i53)
                        {
                            i39 = i57;
                            i40 = i58;
                        }
                        else
                        {
                            int i59 = i56;
                            i39 = i59;
                            i40 = 0;
                        }
                        int i60 = i36 + 1;
                        i34 = i39;
                        i35 = i40;
                        i36 = i60;
                    }
                    int i61 = i16;
                    if(i15 == 0)
                    {
                        i6 = i61;
                    }
                    else
                    {
                        int i62 = i16 + 1;
                        a1.addElement((Object)a3);
                        i6 = i62;
                    }
                }
                int i63 = i4 + 1;
                i3 = i6;
                i4 = i63;
            }
            java.util.Vector a9 = this.answers;
            int i64 = a9.size();
            i = i2 > i64?0:1;
        }
        return i != 0;
    }
    
    protected void tooManyWrongAnswers()
    {
        com.cim.AIA.AnimationWindow a = IAndJPositionQuestion.animationWindow;
        if(a != null)
        {
            com.cim.AIA.AnimationWindow a0 = IAndJPositionQuestion.animationWindow;
            HeapSortAnimationWindow dummy = (HeapSortAnimationWindow)a0;
            HeapSortAnimationWindow a1 = (HeapSortAnimationWindow)a0;
            a1.setNormalMode();
        }
    }
    
    protected void unHighlightAllSelected()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.selected;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.selected;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a1;
                com.cim.AIA.Selectable dummy0 = (com.cim.AIA.Selectable)a1;
                ((com.cim.AIA.Selectable)a1).unHighlight();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    static
    {
        StringBuilder a = new StringBuilder();
        String s = localization.Messages.getString("IAndJPositionQuestion.0");
        StringBuilder a0 = a.append(s);
        String s0 = HeapSortAnimationWindow.FRAME_TITLE;
        StringBuilder a1 = a0.append(s0);
        String s1 = localization.Messages.getString("IAndJPositionQuestion.1");
        StringBuilder a2 = a1.append(s1);
        String s2 = a2.toString();
        IAndJPositionQuestion.QUESTION_J_LABEL = s2;
        StringBuilder a3 = new StringBuilder();
        String s3 = localization.Messages.getString("IAndJPositionQuestion.2");
        StringBuilder a4 = a3.append(s3);
        String s4 = HeapSortAnimationWindow.FRAME_TITLE;
        StringBuilder a5 = a4.append(s4);
        String s5 = localization.Messages.getString("IAndJPositionQuestion.3");
        StringBuilder a6 = a5.append(s5);
        String s6 = a6.toString();
        IAndJPositionQuestion.QUESTION_SWAP_LABEL = s6;
        String s7 = localization.Messages.getString("IAndJPositionQuestion.4");
        IAndJPositionQuestion.QUESTION_NAME = s7;
        String s8 = localization.Messages.getString("IAndJPositionQuestion.5");
        IAndJPositionQuestion.CORRECT = s8;
        String s9 = localization.Messages.getString("IAndJPositionQuestion.6");
        IAndJPositionQuestion.INCORRECT = s9;
        StringBuilder a7 = new StringBuilder();
        String s10 = localization.Messages.getString("IAndJPositionQuestion.7");
        StringBuilder a8 = a7.append(s10);
        StringBuilder a9 = a8.append(3);
        String s11 = localization.Messages.getString("IAndJPositionQuestion.8");
        StringBuilder a10 = a9.append(s11);
        String s12 = a10.toString();
        IAndJPositionQuestion.TOO_MANY = s12;
    }
}