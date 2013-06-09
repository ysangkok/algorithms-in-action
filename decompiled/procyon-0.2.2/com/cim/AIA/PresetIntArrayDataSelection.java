package com.cim.AIA;

public class PresetIntArrayDataSelection extends DataSelection
{
    private static final long serialVersionUID = -7456313318083260168L;
    protected int[] data;
    
    public PresetIntArrayDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final int[] data) {
        super(name, state, animWindow);
        this.data = data;
    }
    
    public Copyable getData() {
        return new IntArray(this.data);
    }
}
