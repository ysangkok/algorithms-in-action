package com.cim.AIA;

import java.awt.*;
import java.applet.*;

public class QuizModeSelection extends ModeSelection
{
    private static final long serialVersionUID = -7659477580384849480L;
    protected AlgorithmThread algorithmThread;
    protected Exercise exercise;
    
    public QuizModeSelection(final String name, final boolean state, final AnimationWindow animWindow, final AlgorithmThread algorithmThread, final Applet applet, final String title, final String filename, final String imgDir, final String correctImageFilename, final String incorrectImageFilename) {
        super(name, state, animWindow);
        this.algorithmThread = algorithmThread;
        this.exercise = new Exercise(new Frame(), title, applet, imgDir, correctImageFilename, incorrectImageFilename);
        this.exercise.initialiseExercise(applet.getCodeBase().toString(), filename);
    }
    
    protected ModeEvent getModeEvent() {
        return new ModeEvent(this, 12358);
    }
    
    protected void initialiseMode() {
        this.algorithmThread.setHandleQuestions(false);
        this.exercise.start();
        this.modeable.informModeListeners(new ModeEvent(this, 12356));
    }
    
    public void modeNormal(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeOther(final ModeEvent e) {
        this.setState(false);
    }
    
    public void modeQuiz(final ModeEvent e) {
        this.setState(true);
    }
    
    public void modeSelfTest(final ModeEvent e) {
        this.setState(false);
    }
}
