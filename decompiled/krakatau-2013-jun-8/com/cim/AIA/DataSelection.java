package com.cim.AIA;

abstract public class DataSelection extends java.awt.CheckboxMenuItem {
    protected com.cim.AIA.AnimationWindow animationWindow;
    protected boolean reInitialiseAlgorithm;
    protected boolean clearAlgorithmState;
    
    public DataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a)
    {
        super(s, b);
        this.reInitialiseAlgorithm = true;
        this.clearAlgorithmState = true;
        this.animationWindow = a;
        com.cim.AIA.DataSelection$1 a0 = new com.cim.AIA.DataSelection$1(this, s);
        this.addItemListener((java.awt.event.ItemListener)a0);
    }
    
    abstract public com.cim.AIA.Copyable getData();
    
    
    public void setClearAlgorithmState(boolean b)
    {
        this.clearAlgorithmState = b;
    }
    
    public void setReinitialiseAlgorithm(boolean b)
    {
        this.reInitialiseAlgorithm = b;
    }
}