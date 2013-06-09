public class RadixTrieMultiColors {
    final protected static java.awt.Color DATA_ITEM_COLOR;
    final protected static java.awt.Color DATA_ITEM_HIGHLIGHT_COLOR;
    final public static java.awt.Color INSERT_HIGHLIGHT_COLOR;
    final public static java.awt.Color INSERT_ACTIVE_COLOR;
    final public static java.awt.Color INSERT_DONE_COLOR;
    final public static java.awt.Color INSERT_BIT_ACTIVE_COLOR;
    final public static java.awt.Color INSERT_BIT_HIGHLIGHT_COLOR;
    final public static java.awt.Color SEARCH_HIGHLIGHT_COLOR;
    final public static java.awt.Color SEARCH_ACTIVE_COLOR;
    final public static java.awt.Color SEARCH_DONE_COLOR;
    final public static java.awt.Color SEARCH_BIT_HIGHLIGHT_COLOR;
    final public static java.awt.Color SEARCH_BIT_ACTIVE_COLOR;
    final public static java.awt.Color COMPARE_BIT_ACTIVE_COLOR;
    final public static java.awt.Color COMPARE_BIT_HIGHLIGHT_COLOR;
    final public static java.awt.Color POINTER_COLOR;
    final public static java.awt.Color NEW_NODE_COLOR;
    final public static java.awt.Color COMPARE_NODE_COLOR;
    final public static java.awt.Color DEFAULT_NODE_COLOR;
    final public static java.awt.Color DEFAULT_BIT_USEFUL_COLOR;
    final public static java.awt.Color DEFAULT_BIT_NOT_USEFUL_COLOR;
    
    public RadixTrieMultiColors()
    {
        super();
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.green;
        RadixTrieMultiColors.DATA_ITEM_COLOR = a;
        java.awt.Color a0 = java.awt.Color.green;
        java.awt.Color a1 = a0.brighter();
        RadixTrieMultiColors.DATA_ITEM_HIGHLIGHT_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(0, 210, 0);
        RadixTrieMultiColors.INSERT_HIGHLIGHT_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(64, 191, 64);
        RadixTrieMultiColors.INSERT_ACTIVE_COLOR = a3;
        java.awt.Color a4 = new java.awt.Color(96, 127, 96);
        RadixTrieMultiColors.INSERT_DONE_COLOR = a4;
        java.awt.Color a5 = RadixTrieMultiColors.INSERT_HIGHLIGHT_COLOR;
        RadixTrieMultiColors.INSERT_BIT_ACTIVE_COLOR = a5;
        java.awt.Color a6 = new java.awt.Color(127, 255, 127);
        RadixTrieMultiColors.INSERT_BIT_HIGHLIGHT_COLOR = a6;
        java.awt.Color a7 = new java.awt.Color(64, 255, 255);
        RadixTrieMultiColors.SEARCH_HIGHLIGHT_COLOR = a7;
        java.awt.Color a8 = new java.awt.Color(64, 191, 191);
        RadixTrieMultiColors.SEARCH_ACTIVE_COLOR = a8;
        java.awt.Color a9 = new java.awt.Color(0, 127, 127);
        RadixTrieMultiColors.SEARCH_DONE_COLOR = a9;
        java.awt.Color a10 = new java.awt.Color(255, 255, 127);
        RadixTrieMultiColors.SEARCH_BIT_HIGHLIGHT_COLOR = a10;
        java.awt.Color a11 = RadixTrieMultiColors.SEARCH_HIGHLIGHT_COLOR;
        RadixTrieMultiColors.SEARCH_BIT_ACTIVE_COLOR = a11;
        java.awt.Color a12 = new java.awt.Color(64, 191, 191);
        RadixTrieMultiColors.COMPARE_BIT_ACTIVE_COLOR = a12;
        java.awt.Color a13 = new java.awt.Color(127, 255, 255);
        RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR = a13;
        java.awt.Color a14 = java.awt.Color.gray;
        RadixTrieMultiColors.POINTER_COLOR = a14;
        java.awt.Color a15 = new java.awt.Color(0, 210, 0);
        RadixTrieMultiColors.NEW_NODE_COLOR = a15;
        java.awt.Color a16 = RadixTrieMultiColors.COMPARE_BIT_ACTIVE_COLOR;
        RadixTrieMultiColors.COMPARE_NODE_COLOR = a16;
        java.awt.Color a17 = java.awt.Color.orange;
        RadixTrieMultiColors.DEFAULT_NODE_COLOR = a17;
        java.awt.Color a18 = java.awt.Color.black;
        RadixTrieMultiColors.DEFAULT_BIT_USEFUL_COLOR = a18;
        java.awt.Color a19 = java.awt.Color.gray;
        java.awt.Color a20 = a19.brighter();
        RadixTrieMultiColors.DEFAULT_BIT_NOT_USEFUL_COLOR = a20;
    }
}