package com.cim.AIA;

import java.awt.event.*;

class AnimationWindow$2 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        AnimationWindow.this.thread.setRunSleepDuration(AnimationWindow.this.maxSpeed - AnimationWindow.this.speedBar.getValue());
        AnimationWindow.this.getLogger().setSpeed(AnimationWindow.this.speedBar.getValue());
        AnimationWindow.this.canvas.setTweenSpeed(AnimationWindow.this.maxSpeed - AnimationWindow.this.speedBar.getValue());
    }
}