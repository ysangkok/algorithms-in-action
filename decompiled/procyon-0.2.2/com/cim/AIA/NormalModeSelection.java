package com.cim.AIA;

public class NormalModeSelection extends ModeSelection
{
    private static final long serialVersionUID = -4407664769268176118L;
    protected AlgorithmThread algorithmThread;
    
    public NormalModeSelection(final String name, final boolean state, final AnimationWindow animWindow, final AlgorithmThread algorithmThread) {
        super(name, state, animWindow);
        this.algorithmThread = algorithmThread;
    }
    
    protected ModeEvent getModeEvent() {
        return new ModeEvent(this, 12356);
    }
    
    protected void initialiseMode() {
        this.algorithmThread.setHandleQuestions(false);
    }
    
    public void modeNormal(final ModeEvent e) {
        this.setState(true);
    }
    
    public void modeOther(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeQuiz(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeSelfTest(final ModeEvent e) {
        this.setState(false);
    }
}
