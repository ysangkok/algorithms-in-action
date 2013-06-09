package com.cim.AIA;

public class QuizModeSelection extends com.cim.AIA.ModeSelection {
    final private static long serialVersionUID = -7659477580384849480L;
    protected com.cim.AIA.AlgorithmThread algorithmThread;
    protected com.cim.AIA.Exercise exercise;
    
    public QuizModeSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1, String s0, String s1, String s2, String s3, String s4)
    {
        super(s, b, (com.cim.AIA.Modeable)a);
        this.algorithmThread = a0;
        java.awt.Frame a2 = new java.awt.Frame();
        com.cim.AIA.Exercise a3 = new com.cim.AIA.Exercise(a2, s0, a1, s2, s3, s4);
        this.exercise = a3;
        com.cim.AIA.Exercise a4 = this.exercise;
        java.net.URL a5 = a1.getCodeBase();
        String s5 = a5.toString();
        a4.initialiseExercise(s5, s1);
    }
    
    protected com.cim.AIA.ModeEvent getModeEvent()
    {
        com.cim.AIA.ModeEvent a = new com.cim.AIA.ModeEvent((Object)this, 12358);
        return a;
    }
    
    protected void initialiseMode()
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        a.setHandleQuestions(false);
        com.cim.AIA.Exercise a0 = this.exercise;
        a0.start();
        Object a1 = this.modeable;
        com.cim.AIA.ModeEvent a2 = new com.cim.AIA.ModeEvent((Object)this, 12356);
        ((com.cim.AIA.Modeable)a1).informModeListeners(a2);
    }
    
    public void modeNormal(com.cim.AIA.ModeEvent a)
    {
        this.setState(false);
    }
    
    public void modeOther(com.cim.AIA.ModeEvent a)
    {
        this.setState(false);
    }
    
    public void modeQuiz(com.cim.AIA.ModeEvent a)
    {
        this.setState(true);
    }
    
    public void modeSelfTest(com.cim.AIA.ModeEvent a)
    {
        this.setState(false);
    }
}