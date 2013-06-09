package com.cim.AIA;

public class NormalModeSelection extends com.cim.AIA.ModeSelection {
    final private static long serialVersionUID = -4407664769268176118L;
    protected com.cim.AIA.AlgorithmThread algorithmThread;
    
    public NormalModeSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, b, (com.cim.AIA.Modeable)a);
        this.algorithmThread = a0;
    }
    
    protected com.cim.AIA.ModeEvent getModeEvent()
    {
        com.cim.AIA.ModeEvent a = new com.cim.AIA.ModeEvent((Object)this, 12356);
        return a;
    }
    
    protected void initialiseMode()
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        a.setHandleQuestions(false);
    }
    
    public void modeNormal(com.cim.AIA.ModeEvent a)
    {
        this.setState(true);
    }
    
    public void modeOther(com.cim.AIA.ModeEvent a)
    {
        this.setState(false);
    }
    
    public void modeQuiz(com.cim.AIA.ModeEvent a)
    {
        this.setState(false);
    }
    
    public void modeSelfTest(com.cim.AIA.ModeEvent a)
    {
        this.setState(false);
    }
}