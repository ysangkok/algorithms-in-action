package com.cim.AIA;

public class SelfTestModeSelection extends ModeSelection
{
    private static final long serialVersionUID = 4681599444333443209L;
    protected AlgorithmThread algorithmThread;
    
    public SelfTestModeSelection(final String name, final boolean state, final AnimationWindow animWindow, final AlgorithmThread algorithmThread) {
        super(name, state, animWindow);
        this.algorithmThread = algorithmThread;
    }
    
    protected ModeEvent getModeEvent() {
        return new ModeEvent(this, 12357);
    }
    
    protected void initialiseMode() {
        this.algorithmThread.setHandleQuestions(true);
    }
    
    public void modeNormal(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeOther(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeQuiz(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeSelfTest(final ModeEvent e) {
        this.setState(true);
    }
}
