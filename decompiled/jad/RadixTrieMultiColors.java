// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiColors.java

import java.awt.Color;

public class RadixTrieMultiColors
{

    public RadixTrieMultiColors()
    {
    }

    protected static final Color DATA_ITEM_COLOR;
    protected static final Color DATA_ITEM_HIGHLIGHT_COLOR;
    public static final Color INSERT_HIGHLIGHT_COLOR;
    public static final Color INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
    public static final Color INSERT_DONE_COLOR = new Color(96, 127, 96);
    public static final Color INSERT_BIT_ACTIVE_COLOR;
    public static final Color INSERT_BIT_HIGHLIGHT_COLOR = new Color(127, 255, 127);
    public static final Color SEARCH_HIGHLIGHT_COLOR;
    public static final Color SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
    public static final Color SEARCH_DONE_COLOR = new Color(0, 127, 127);
    public static final Color SEARCH_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 127);
    public static final Color SEARCH_BIT_ACTIVE_COLOR;
    public static final Color COMPARE_BIT_ACTIVE_COLOR;
    public static final Color COMPARE_BIT_HIGHLIGHT_COLOR = new Color(127, 255, 255);
    public static final Color POINTER_COLOR;
    public static final Color NEW_NODE_COLOR = new Color(0, 210, 0);
    public static final Color COMPARE_NODE_COLOR;
    public static final Color DEFAULT_NODE_COLOR;
    public static final Color DEFAULT_BIT_USEFUL_COLOR;
    public static final Color DEFAULT_BIT_NOT_USEFUL_COLOR;

    static 
    {
        DATA_ITEM_COLOR = Color.green;
        DATA_ITEM_HIGHLIGHT_COLOR = Color.green.brighter();
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        INSERT_BIT_ACTIVE_COLOR = INSERT_HIGHLIGHT_COLOR;
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_BIT_ACTIVE_COLOR = SEARCH_HIGHLIGHT_COLOR;
        COMPARE_BIT_ACTIVE_COLOR = new Color(64, 191, 191);
        POINTER_COLOR = Color.gray;
        COMPARE_NODE_COLOR = COMPARE_BIT_ACTIVE_COLOR;
        DEFAULT_NODE_COLOR = Color.orange;
        DEFAULT_BIT_USEFUL_COLOR = Color.black;
        DEFAULT_BIT_NOT_USEFUL_COLOR = Color.gray.brighter();
    }
}
