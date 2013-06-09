// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeColors.java

import java.awt.Color;

public class SplayTreeColors
{

    public SplayTreeColors()
    {
    }

    protected static final Color DATA_ITEM_COLOR;
    protected static final Color DATA_ITEM_HIGHLIGHT_COLOR;
    public static final Color INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
    public static final Color INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
    public static final Color INSERT_DONE_COLOR = new Color(96, 127, 96);
    public static final Color SEARCH_HIGHLIGHT_COLOR;
    public static final Color SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
    public static final Color SEARCH_DONE_COLOR = new Color(0, 127, 127);
    public static final Color POINTER_COLOR;
    public static final Color NEW_NODE_COLOR = new Color(0, 210, 0);
    public static final Color FOUND_NODE_COLOR;
    public static final Color DEFAULT_NODE_COLOR;
    public static final Color CODE_STACK_ACTIVE_COLOR;
    public static final Color CODE_STACK_SUSPENDED_COLOR;
    public static final Color CODE_STACK_CODE_COLOR;
    public static final Color CODE_STACK_COMMENT_COLOR;
    public static final Color CODE_STACK_BOX_COLOR;
    public static final Color CODE_STACK_LABEL_COLOR;

    static 
    {
        DATA_ITEM_COLOR = Color.green;
        DATA_ITEM_HIGHLIGHT_COLOR = Color.green.brighter();
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        POINTER_COLOR = Color.gray;
        FOUND_NODE_COLOR = SEARCH_HIGHLIGHT_COLOR;
        DEFAULT_NODE_COLOR = Color.orange;
        CODE_STACK_ACTIVE_COLOR = Color.green;
        CODE_STACK_SUSPENDED_COLOR = Color.green.darker();
        CODE_STACK_CODE_COLOR = Color.black;
        CODE_STACK_COMMENT_COLOR = Color.blue;
        CODE_STACK_BOX_COLOR = Color.black;
        CODE_STACK_LABEL_COLOR = Color.gray;
    }
}
