package com.cim.AIA;

abstract public class Question implements com.cim.AIA.SelectionListener, com.cim.AIA.Finishable, com.cim.AIA.ExitListener {
    protected static com.cim.AIA.AnimationWindow animationWindow;
    final public static int INFINATE_ATTEMPTS = -1;
    protected static com.cim.common.MessageDialog messageDialog;
    protected static java.awt.Point messageDialogPoint;
    protected java.util.Vector finishListeners;
    protected String name;
    protected String questionInstructions;
    protected String title;
    protected String correctMessage;
    protected String inCorrectMessage;
    protected String tooManyAttemptsMessage;
    protected java.util.Vector answers;
    protected int timesWrong;
    protected int attemtpsAllowable;
    protected java.util.Vector selected;
    protected boolean enabled;
    protected boolean questionInstructionsModalMode;
    protected boolean correctModalMode;
    protected boolean timesWrongModalMode;
    protected boolean inCorrectModalMode;
    protected boolean displayInstructionsAgainIfWrong;
    protected boolean displayCorrectMessage;
    protected boolean displayInCorrectMessage;
    protected boolean displayTooManyAttemptsMessage;
    
    public static void closeDialog()
    {
        com.cim.common.MessageDialog a = com.cim.AIA.Question.messageDialog;
        if(a != null)
        {
            com.cim.common.MessageDialog a0 = com.cim.AIA.Question.messageDialog;
            a0.setVisible(false);
            com.cim.common.MessageDialog a1 = com.cim.AIA.Question.messageDialog;
            a1.dispose();
        }
    }
    
    public static void setAnimationWindow(com.cim.AIA.AnimationWindow a)
    {
        com.cim.AIA.Question.animationWindow = a;
    }
    
    public Question(String s, String s0, int i)
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.finishListeners = a;
        String s1 = localization.Messages.getString("Question.0");
        this.title = s1;
        String s2 = localization.Messages.getString("Question.1");
        this.correctMessage = s2;
        String s3 = localization.Messages.getString("Question.2");
        this.inCorrectMessage = s3;
        String s4 = localization.Messages.getString("Question.3");
        this.tooManyAttemptsMessage = s4;
        this.timesWrong = 0;
        this.attemtpsAllowable = 1;
        this.enabled = true;
        this.questionInstructionsModalMode = false;
        this.correctModalMode = false;
        this.timesWrongModalMode = true;
        this.inCorrectModalMode = true;
        this.displayInstructionsAgainIfWrong = true;
        this.displayCorrectMessage = true;
        this.displayInCorrectMessage = true;
        this.displayTooManyAttemptsMessage = true;
        this.name = s;
        this.questionInstructions = s0;
        this.attemtpsAllowable = i;
        java.util.Vector a0 = new java.util.Vector();
        this.answers = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.selected = a1;
    }
    
    public void addAnswer(Object a)
    {
        if(a != null)
        {
            java.util.Vector a0 = this.answers;
            a0.addElement(a);
        }
    }
    
    public void addFinishListener(com.cim.AIA.FinishListener a)
    {
        java.util.Vector a0 = this.finishListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void ask()
    {
        int i = this.enabled?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            java.util.Vector a = new java.util.Vector();
            this.selected = a;
            int i0 = this.attemtpsAllowable;
            label1: {
                if(i0 != -1)
                {
                    break label1;
                }
                String s = this.questionInstructions;
                int i1 = this.questionInstructionsModalMode?1:0;
                this.displayMessage(s, i1 != 0);
                break label0;
            }
            int i2 = this.timesWrong;
            int i3 = this.attemtpsAllowable;
            if(i2 < i3)
            {
                String s0 = this.questionInstructions;
                int i4 = this.questionInstructionsModalMode?1:0;
                this.displayMessage(s0, i4 != 0);
            }
        }
    }
    
    protected void displayMessage(String s, boolean b)
    {
        int i = 0;
        int i0 = 0;
        int i1 = b?1:0;
        int i2 = b?1:0;
        if(!b)
        {
            i = i2;
            i0 = 0;
        }
        else
        {
            i = i1;
            i0 = 1;
        }
        com.cim.AIA.Question.messageDialogPoint = null;
        com.cim.common.MessageDialog a = com.cim.AIA.Question.messageDialog;
        label0: {
            if(a == null)
            {
                break label0;
            }
            com.cim.common.MessageDialog a0 = com.cim.AIA.Question.messageDialog;
            java.awt.Point a1 = a0.getLocation();
            int i3 = a1.x;
            label1: {
                if(i3 == 0)
                {
                    break label1;
                }
                int i4 = a1.y;
                if(i4 != 0)
                {
                    int i5 = a1.x;
                    int i6 = a1.y;
                    java.awt.Point a2 = new java.awt.Point(i5, i6);
                    com.cim.AIA.Question.messageDialogPoint = a2;
                }
            }
            com.cim.common.MessageDialog a3 = com.cim.AIA.Question.messageDialog;
            a3.setVisible(false);
            com.cim.common.MessageDialog a4 = com.cim.AIA.Question.messageDialog;
            a4.dispose();
        }
        com.cim.common.MessageDialog a5 = new com.cim.common.MessageDialog(s, i != 0, i0 != 0);
        com.cim.AIA.Question.messageDialog = a5;
        java.awt.Point a6 = com.cim.AIA.Question.messageDialogPoint;
        if(a6 != null)
        {
            com.cim.common.MessageDialog a7 = com.cim.AIA.Question.messageDialog;
            java.awt.Point a8 = com.cim.AIA.Question.messageDialogPoint;
            a7.setLocation(a8);
        }
        com.cim.common.MessageDialog a9 = com.cim.AIA.Question.messageDialog;
        String s0 = this.title;
        a9.setTitle(s0);
        com.cim.common.MessageDialog a10 = com.cim.AIA.Question.messageDialog;
        a10.setVisible(true);
    }
    
    protected void finish()
    {
        com.cim.AIA.FinishEvent a = new com.cim.AIA.FinishEvent((Object)this);
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.finishListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                this.enabled = false;
                return;
            }
            else
            {
                java.util.Vector a1 = this.finishListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.FinishListener dummy = (com.cim.AIA.FinishListener)a2;
                ((com.cim.AIA.FinishListener)a2).processFinishEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void gotCorrectAnswer()
    {
    }
    
    protected void gotInCorrectAnswer()
    {
    }
    
    abstract protected boolean isCorrect(java.util.Vector arg);
    
    
    abstract protected boolean isInCorrect(java.util.Vector arg);
    
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.finish();
        java.util.Vector a0 = this.finishListeners;
        a0.removeAllElements();
        com.cim.common.MessageDialog a1 = com.cim.AIA.Question.messageDialog;
        a1.setVisible(false);
        com.cim.common.MessageDialog a2 = com.cim.AIA.Question.messageDialog;
        a2.dispose();
    }
    
    public void processSelectionEvent(com.cim.AIA.SelectionEvent a)
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            Object a0 = a.paramObj;
            java.util.Vector a1 = this.selected;
            a1.addElement(a0);
            java.util.Vector a2 = this.selected;
            int i0 = this.isCorrect(a2)?1:0;
            label5: {
                label3: {
                    label2: {
                        if(i0 == 0)
                        {
                            break label2;
                        }
                        int i1 = this.displayCorrectMessage?1:0;
                        if(i1 != 0)
                        {
                            String s = this.correctMessage;
                            int i2 = this.correctModalMode?1:0;
                            this.displayMessage(s, i2 != 0);
                        }
                        this.gotCorrectAnswer();
                        this.finish();
                        break label3;
                    }
                    java.util.Vector a3 = this.selected;
                    int i3 = this.isInCorrect(a3)?1:0;
                    if(i3 == 0)
                    {
                        break label3;
                    }
                    int i4 = this.timesWrong;
                    int i5 = i4 + 1;
                    this.timesWrong = i5;
                    int i6 = this.displayInCorrectMessage?1:0;
                    if(i6 != 0)
                    {
                        String s0 = this.inCorrectMessage;
                        int i7 = this.inCorrectModalMode?1:0;
                        this.displayMessage(s0, i7 != 0);
                    }
                    this.gotInCorrectAnswer();
                    int i8 = this.attemtpsAllowable;
                    label4: {
                        if(i8 == -1)
                        {
                            break label4;
                        }
                        int i9 = this.timesWrong;
                        int i10 = this.attemtpsAllowable;
                        if(i9 >= i10)
                        {
                            break label5;
                        }
                    }
                    Object a4 = a.getSource();
                    com.cim.AIA.Questionable dummy = (com.cim.AIA.Questionable)a4;
                    com.cim.AIA.Questionable dummy0 = (com.cim.AIA.Questionable)a4;
                    ((com.cim.AIA.Questionable)a4).removeAllHighlight();
                    java.util.Vector a5 = new java.util.Vector();
                    this.selected = a5;
                    int i11 = this.displayInstructionsAgainIfWrong?1:0;
                    if(i11 != 0)
                    {
                        this.ask();
                    }
                }
                break label1;
            }
            int i12 = this.displayTooManyAttemptsMessage?1:0;
            if(i12 != 0)
            {
                String s1 = this.tooManyAttemptsMessage;
                int i13 = this.timesWrongModalMode?1:0;
                this.displayMessage(s1, i13 != 0);
            }
            this.tooManyWrongAnswers();
            this.finish();
        }
    }
    
    public void removeFinishListener(com.cim.AIA.FinishListener a)
    {
        java.util.Vector a0 = this.finishListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void reset()
    {
        this.timesWrong = 0;
        com.cim.common.MessageDialog a = com.cim.AIA.Question.messageDialog;
        if(a != null)
        {
            com.cim.common.MessageDialog a0 = com.cim.AIA.Question.messageDialog;
            a0.dispose();
        }
        java.util.Vector a1 = new java.util.Vector();
        this.selected = a1;
        this.enabled = true;
    }
    
    public void setInstructions(String s)
    {
        this.questionInstructions = s;
    }
    
    protected void tooManyWrongAnswers()
    {
    }
}