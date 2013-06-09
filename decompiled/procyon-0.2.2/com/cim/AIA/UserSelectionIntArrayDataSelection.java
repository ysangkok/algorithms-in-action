package com.cim.AIA;

public class UserSelectionIntArrayDataSelection extends DataSelection
{
    private static final long serialVersionUID = 6336065059735441792L;
    protected int[] items;
    protected int maxValue;
    protected int minValue;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected String title;
    
    public UserSelectionIntArrayDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final String title, final int minValue, final int maxValue, final int minimumNumberOfElements, final int maximumNumberOfElements) {
        super(name, state, animWindow);
        this.title = title;
        this.items = this.items;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minimumNumberOfElements = minimumNumberOfElements;
        this.maximumNumberOfElements = maximumNumberOfElements;
    }
    
    public Copyable getData() {
        final int[] items = (int[])((int[])this.animationWindow.getCurrentData());
        final UserSelectionDataDialog userSelectionDataDialog = new UserSelectionDataDialog(this.title, items, this.minValue, this.maxValue, this.minimumNumberOfElements, this.maximumNumberOfElements);
        userSelectionDataDialog.setVisible(true);
        return new IntArray(userSelectionDataDialog.getData());
    }
}
