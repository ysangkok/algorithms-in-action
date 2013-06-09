package aia.graph.common;

public class NextVisitQuestion extends com.cim.AIA.Question {
    final protected static String NAME;
    final protected static String INSTRUCTIONS;
    final protected static int ATTEMPTS_ALLOWED = 3;
    final protected static String TOO_MANY;
    final protected static String CORRECT_MESSAGE;
    final protected static String INCORRECT_MESSAGE;
    protected int next_to_visit;
    
    public NextVisitQuestion(int i)
    {
        String s = aia.graph.common.NextVisitQuestion.NAME;
        String s0 = aia.graph.common.NextVisitQuestion.INSTRUCTIONS;
        super(s, s0, 3);
        this.next_to_visit = i;
        String s1 = aia.graph.common.NextVisitQuestion.TOO_MANY;
        this.tooManyAttemptsMessage = s1;
        String s2 = aia.graph.common.NextVisitQuestion.CORRECT_MESSAGE;
        this.correctMessage = s2;
        String s3 = aia.graph.common.NextVisitQuestion.INCORRECT_MESSAGE;
        this.inCorrectMessage = s3;
    }
    
    protected boolean isCorrect(java.util.Vector a)
    {
        int i = 0;
        while(true)
        {
            int i0 = 0;
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
                Object a0 = a.elementAt(i);
                com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a0;
                com.cim.AIA.Selectable dummy0 = (com.cim.AIA.Selectable)a0;
                int i2 = ((com.cim.AIA.Selectable)a0).getIdentifier();
                int i3 = this.next_to_visit;
                if(i2 != i3)
                {
                    int i4 = i + 1;
                    i = i4;
                    continue;
                }
                i0 = 1;
            }
            return i0 != 0;
        }
    }
    
    protected boolean isInCorrect(java.util.Vector a)
    {
        int i = 0;
        int i0 = a.size();
        label2: {
            label1: {
                label0: {
                    if(i0 >= 1)
                    {
                        break label0;
                    }
                    int i1 = this.isCorrect(a)?1:0;
                    if(i1 != 0)
                    {
                        break label1;
                    }
                }
                i = 1;
                break label2;
            }
            i = 0;
        }
        return i != 0;
    }
    
    protected void tooManyWrongAnswers()
    {
        com.cim.AIA.AnimationWindow a = aia.graph.common.NextVisitQuestion.animationWindow;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            String s = localization.Messages.getString("NextVisitQuestion.6");
            a0.println(s);
        }
        else
        {
            com.cim.AIA.AnimationWindow a1 = aia.graph.common.NextVisitQuestion.animationWindow;
            com.cim.AIA.ModeEvent a2 = new com.cim.AIA.ModeEvent((Object)this, 12356);
            a1.informModeListeners(a2);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("NextVisitQuestion.0");
        aia.graph.common.NextVisitQuestion.NAME = s;
        String s0 = localization.Messages.getString("NextVisitQuestion.1");
        aia.graph.common.NextVisitQuestion.INSTRUCTIONS = s0;
        StringBuilder a = new StringBuilder();
        String s1 = localization.Messages.getString("NextVisitQuestion.2");
        StringBuilder a0 = a.append(s1);
        String s2 = localization.Messages.getString("NextVisitQuestion.3");
        StringBuilder a1 = a0.append(s2);
        String s3 = a1.toString();
        aia.graph.common.NextVisitQuestion.TOO_MANY = s3;
        String s4 = localization.Messages.getString("NextVisitQuestion.4");
        aia.graph.common.NextVisitQuestion.CORRECT_MESSAGE = s4;
        String s5 = localization.Messages.getString("NextVisitQuestion.5");
        aia.graph.common.NextVisitQuestion.INCORRECT_MESSAGE = s5;
    }
}