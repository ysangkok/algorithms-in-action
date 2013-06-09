package com.cim.AIA;

public class UserSelectionStringDataSelection extends DataSelection
{
    private static final long serialVersionUID = -8110746691025590588L;
    public static final int ALLOW_TEXT = 1;
    public static final int ALLOW_NUMBERS = 2;
    public static final int ALLOW_OTHERS = 4;
    public static final int ALLOW_SPACE = 32;
    public static final int TOLOWER = 8;
    public static final int TOUPPER = 16;
    protected String items;
    protected int maxLength;
    protected int minLength;
    protected int param;
    protected String label1;
    protected String label2;
    protected String title;
    
    public UserSelectionStringDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final String title, final int minLength, final int maxLength) {
        super(name, state, animWindow);
        this.title = title;
        this.items = this.items;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.label1 = null;
        this.label2 = null;
        this.param = 39;
    }
    
    public UserSelectionStringDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final String title, final int minLength, final int maxLength, final String label1, final String label2) {
        super(name, state, animWindow);
        this.title = title;
        this.items = this.items;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.label1 = label1;
        this.label2 = label2;
        this.param = 39;
    }
    
    public UserSelectionStringDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final String title, final int minLength, final int maxLength, final String label1, final String label2, final int param) {
        super(name, state, animWindow);
        this.title = title;
        this.items = this.items;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.label1 = label1;
        this.label2 = label2;
        this.param = param;
    }
    
    public Copyable getData() {
        final String[] items = (String[])((String[])this.animationWindow.getCurrentData());
        UserSelectionStringDataDialog us1;
        if (this.label1 == null || this.label2 == null) {
            us1 = new UserSelectionStringDataDialog(this.title, items, this.minLength, this.maxLength, this.param);
        }
        else {
            us1 = new UserSelectionStringDataDialog(this.title, items, this.minLength, this.maxLength, this.label1, this.label2, this.param);
        }
        us1.setVisible(true);
        return new StringArray(us1.getData());
    }
}
