public class IAndJPositionQuestionQS extends com.cim.AIA.Question {
    final protected static String QUESTION_NAME;
    final protected static String QUESTION_END_UP_LABEL;
    final protected static String QUESTION_SWAP_LABEL;
    final protected static int ATTEMPTS_ALLOWABLE = 3;
    final protected static String CORRECT;
    final protected static String INCORRECT;
    final protected static String TOO_MANY;
    
    public IAndJPositionQuestionQS()
    {
        String s = IAndJPositionQuestionQS.QUESTION_NAME;
        String s0 = IAndJPositionQuestionQS.QUESTION_SWAP_LABEL;
        super(s, s0, 3);
        String s1 = IAndJPositionQuestionQS.CORRECT;
        this.correctMessage = s1;
        String s2 = IAndJPositionQuestionQS.INCORRECT;
        this.inCorrectMessage = s2;
        String s3 = IAndJPositionQuestionQS.TOO_MANY;
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
        com.cim.AIA.AnimationWindow a = IAndJPositionQuestionQS.animationWindow;
        if(a != null)
        {
            com.cim.AIA.AnimationWindow a0 = IAndJPositionQuestionQS.animationWindow;
            QuickSortAnimationWindow dummy = (QuickSortAnimationWindow)a0;
            QuickSortAnimationWindow a1 = (QuickSortAnimationWindow)a0;
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
        String s = localization.Messages.getString("IAndJPositionQuestionQS.0");
        IAndJPositionQuestionQS.QUESTION_NAME = s;
        String s0 = localization.Messages.getString("IAndJPositionQuestionQS.1");
        IAndJPositionQuestionQS.QUESTION_END_UP_LABEL = s0;
        String s1 = localization.Messages.getString("IAndJPositionQuestionQS.2");
        IAndJPositionQuestionQS.QUESTION_SWAP_LABEL = s1;
        String s2 = localization.Messages.getString("IAndJPositionQuestionQS.3");
        IAndJPositionQuestionQS.CORRECT = s2;
        String s3 = localization.Messages.getString("IAndJPositionQuestionQS.4");
        IAndJPositionQuestionQS.INCORRECT = s3;
        StringBuilder a = new StringBuilder();
        String s4 = localization.Messages.getString("IAndJPositionQuestionQS.5");
        StringBuilder a0 = a.append(s4);
        StringBuilder a1 = a0.append(3);
        String s5 = localization.Messages.getString("IAndJPositionQuestionQS.6");
        StringBuilder a2 = a1.append(s5);
        String s6 = a2.toString();
        IAndJPositionQuestionQS.TOO_MANY = s6;
    }
}