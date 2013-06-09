package com.cim.AIA;

class AnimationWindow$2 implements java.awt.event.AdjustmentListener {
    final com.cim.AIA.AnimationWindow this$0;
    
    AnimationWindow$2(com.cim.AIA.AnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void adjustmentValueChanged(java.awt.event.AdjustmentEvent a)
    {
        com.cim.AIA.AnimationWindow a0 = this.this$0;
        com.cim.AIA.AlgorithmThread a1 = a0.thread;
        com.cim.AIA.AnimationWindow a2 = this.this$0;
        int i = a2.maxSpeed;
        com.cim.AIA.AnimationWindow a3 = this.this$0;
        java.awt.Scrollbar a4 = a3.speedBar;
        int i0 = a4.getValue();
        int i1 = i - i0;
        a1.setRunSleepDuration(i1);
        com.cim.AIA.AnimationWindow a5 = this.this$0;
        com.cim.AIA.Logger a6 = a5.getLogger();
        com.cim.AIA.AnimationWindow a7 = this.this$0;
        java.awt.Scrollbar a8 = a7.speedBar;
        int i2 = a8.getValue();
        a6.setSpeed(i2);
        com.cim.AIA.AnimationWindow a9 = this.this$0;
        com.cim.AIA.AlgorithmCanvas a10 = a9.canvas;
        com.cim.AIA.AnimationWindow a11 = this.this$0;
        int i3 = a11.maxSpeed;
        com.cim.AIA.AnimationWindow a12 = this.this$0;
        java.awt.Scrollbar a13 = a12.speedBar;
        int i4 = a13.getValue();
        int i5 = i3 - i4;
        a10.setTweenSpeed(i5);
    }
}