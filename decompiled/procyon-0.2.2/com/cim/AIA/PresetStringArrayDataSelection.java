package com.cim.AIA;

public class PresetStringArrayDataSelection extends DataSelection
{
    private static final long serialVersionUID = 3546261853244134347L;
    protected String[] data;
    
    public PresetStringArrayDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final String[] data) {
        super(name, state, animWindow);
        this.data = data;
    }
    
    public Copyable getData() {
        return new StringArray(this.data);
    }
}
