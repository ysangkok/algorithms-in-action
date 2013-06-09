package aia.graph.common;

public class NextNewLinkQuestion extends com.cim.AIA.Question {
    final protected static String NAME;
    final protected static String INSTRUCTIONS;
    final protected static int ATTEMPTS_ALLOWED = 3;
    final protected static String TOO_MANY;
    final protected static String CORRECT_MESSAGE;
    final protected static String INCORRECT_MESSAGE;
    protected int from_node;
    protected int to_node;
    
    public NextNewLinkQuestion(int i, int i0)
    {
        String s = aia.graph.common.NextNewLinkQuestion.NAME;
        String s0 = aia.graph.common.NextNewLinkQuestion.INSTRUCTIONS;
        super(s, s0, 3);
        this.from_node = i;
        this.to_node = i0;
        String s1 = aia.graph.common.NextNewLinkQuestion.TOO_MANY;
        this.tooManyAttemptsMessage = s1;
        String s2 = aia.graph.common.NextNewLinkQuestion.CORRECT_MESSAGE;
        this.correctMessage = s2;
        String s3 = aia.graph.common.NextNewLinkQuestion.INCORRECT_MESSAGE;
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
                    int i24 = this.from_node;
                    int i25 = i22;
                    int i26 = i23;
                    label3: {
                        int i27 = 0;
                        int i28 = 0;
                        label2: {
                            int i29 = 0;
                            int i30 = i26;
                            int i31 = i25;
                            int i32 = i26;
                            if(i21 != i24)
                            {
                                i27 = i31;
                                i28 = i32;
                                break label2;
                            }
                            else
                            {
                                i29 = i30;
                            }
                            i10 = 1;
                            i11 = i29;
                            break label3;
                        }
                        int i33 = ((com.cim.AIA.Selectable)a0).getIdentifier();
                        int i34 = i27;
                        int i35 = i28;
                        int i36 = this.to_node;
                        int i37 = i34;
                        int i38 = i35;
                        int i39 = i37;
                        int i40 = i37;
                        int i41 = i38;
                        if(i33 != i36)
                        {
                            i10 = i40;
                            i11 = i41;
                        }
                        else
                        {
                            int i42 = i39;
                            i10 = i42;
                            i11 = 1;
                        }
                    }
                    int i43 = i11;
                    int i44 = i10;
                    int i45 = i11;
                    if(i10 == 0)
                    {
                        i2 = i44;
                        i3 = i45;
                        break label4;
                    }
                    else
                    {
                        i12 = i43;
                    }
                    int i46 = i12;
                    if(i12 == 0)
                    {
                        i2 = 1;
                        i3 = i46;
                        break label4;
                    }
                    i7 = 1;
                }
                return i7 != 0;
            }
            int i47 = i1 + 1;
            i = i2;
            i0 = i3;
            i1 = i47;
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
        com.cim.AIA.AnimationWindow a = aia.graph.common.NextNewLinkQuestion.animationWindow;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            String s = localization.Messages.getString("NextNewLinkQuestion.6");
            a0.println(s);
        }
        else
        {
            com.cim.AIA.AnimationWindow a1 = aia.graph.common.NextNewLinkQuestion.animationWindow;
            com.cim.AIA.ModeEvent a2 = new com.cim.AIA.ModeEvent((Object)this, 12356);
            a1.informModeListeners(a2);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("NextNewLinkQuestion.0");
        aia.graph.common.NextNewLinkQuestion.NAME = s;
        String s0 = localization.Messages.getString("NextNewLinkQuestion.1");
        aia.graph.common.NextNewLinkQuestion.INSTRUCTIONS = s0;
        StringBuilder a = new StringBuilder();
        String s1 = localization.Messages.getString("NextNewLinkQuestion.2");
        StringBuilder a0 = a.append(s1);
        String s2 = localization.Messages.getString("NextNewLinkQuestion.3");
        StringBuilder a1 = a0.append(s2);
        String s3 = a1.toString();
        aia.graph.common.NextNewLinkQuestion.TOO_MANY = s3;
        String s4 = localization.Messages.getString("NextNewLinkQuestion.4");
        aia.graph.common.NextNewLinkQuestion.CORRECT_MESSAGE = s4;
        String s5 = localization.Messages.getString("NextNewLinkQuestion.5");
        aia.graph.common.NextNewLinkQuestion.INCORRECT_MESSAGE = s5;
    }
}