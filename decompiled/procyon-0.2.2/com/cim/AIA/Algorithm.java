package com.cim.AIA;

import java.util.*;

public abstract class Algorithm
{
    protected AlgorithmThread algorithmThread;
    protected boolean debugMode;
    protected boolean enabled;
    private boolean started;
    
    public Algorithm(final AlgorithmThread algorithmThread, final Object data) {
        super();
        this.debugMode = true;
        this.enabled = true;
        this.started = false;
        this.algorithmThread = algorithmThread;
    }
    
    protected void askQuestionsWithoutSetPosition() {
        if (this.algorithmThread != null) {
            if (this.hasQuestions() && this.algorithmThread.getHandleQuestions()) {
                this.algorithmThread.paintAndWait(this.algorithmThread.getPosition(), false);
            }
        }
        else {
            System.out.println("com.cim.AIA.Algorithm.askQuestionWithoutSetPosition: algorithmThread is null");
        }
    }
    
    protected void changeData(final Object data) {
        this.reInitialise(data);
    }
    
    public void cleanUp() {
        this.algorithmThread = null;
    }
    
    protected void clearState() {
    }
    
    protected void displayMessage(final String message) {
        if (this.debugMode) {
            System.out.println(message);
        }
    }
    
    protected abstract Vector<E> generateQuestions();
    
    public abstract MultipleTween generateTweens(final Paintable p0, final Object p1, final int p2);
    
    public BreakPoint getBreakPoint() {
        return this.algorithmThread.getBreakPoint();
    }
    
    public Logger getLogger() {
        return this.algorithmThread.getLogger();
    }
    
    protected abstract boolean hasQuestions();
    
    public boolean haveStarted() {
        return this.started;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public abstract void reInitialise(final Object p0);
    
    protected abstract void removeAllAnimationRequests();
    
    protected abstract void removeAllQuestions();
    
    public void repaint() {
        if (this.algorithmThread != null) {
            this.algorithmThread.informRepaintListeners();
        }
        else {
            System.out.println("com.cim.AIA.Algorithm.repaint: algorithmThread is null");
        }
    }
    
    protected abstract void run();
    
    protected void run(final boolean canChangeMode) {
        this.run();
    }
    
    public void runAlgorithm(final boolean canChangeMode) {
        if (this.enabled) {
            this.started = true;
            this.run(canChangeMode);
        }
    }
    
    protected void setEnabled(final boolean state) {
        this.enabled = state;
    }
    
    protected void setPosition(final String key) {
        if (this.algorithmThread != null) {
            this.algorithmThread.paintAndWait(key);
        }
        else {
            System.out.println("com.cim.AIA.Algorithm.setPosition: algorithmThread is null");
        }
    }
    
    protected void storeState(final boolean forceStore) {
    }
}
