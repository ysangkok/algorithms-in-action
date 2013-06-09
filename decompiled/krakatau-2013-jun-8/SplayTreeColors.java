public class SplayTreeColors {
    final protected static java.awt.Color DATA_ITEM_COLOR;
    final protected static java.awt.Color DATA_ITEM_HIGHLIGHT_COLOR;
    final public static java.awt.Color INSERT_HIGHLIGHT_COLOR;
    final public static java.awt.Color INSERT_ACTIVE_COLOR;
    final public static java.awt.Color INSERT_DONE_COLOR;
    final public static java.awt.Color SEARCH_HIGHLIGHT_COLOR;
    final public static java.awt.Color SEARCH_ACTIVE_COLOR;
    final public static java.awt.Color SEARCH_DONE_COLOR;
    final public static java.awt.Color POINTER_COLOR;
    final public static java.awt.Color NEW_NODE_COLOR;
    final public static java.awt.Color FOUND_NODE_COLOR;
    final public static java.awt.Color DEFAULT_NODE_COLOR;
    final public static java.awt.Color CODE_STACK_ACTIVE_COLOR;
    final public static java.awt.Color CODE_STACK_SUSPENDED_COLOR;
    final public static java.awt.Color CODE_STACK_CODE_COLOR;
    final public static java.awt.Color CODE_STACK_COMMENT_COLOR;
    final public static java.awt.Color CODE_STACK_BOX_COLOR;
    final public static java.awt.Color CODE_STACK_LABEL_COLOR;
    
    public SplayTreeColors()
    {
        super();
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.green;
        SplayTreeColors.DATA_ITEM_COLOR = a;
        java.awt.Color a0 = java.awt.Color.green;
        java.awt.Color a1 = a0.brighter();
        SplayTreeColors.DATA_ITEM_HIGHLIGHT_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(0, 210, 0);
        SplayTreeColors.INSERT_HIGHLIGHT_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(64, 191, 64);
        SplayTreeColors.INSERT_ACTIVE_COLOR = a3;
        java.awt.Color a4 = new java.awt.Color(96, 127, 96);
        SplayTreeColors.INSERT_DONE_COLOR = a4;
        java.awt.Color a5 = new java.awt.Color(64, 255, 255);
        SplayTreeColors.SEARCH_HIGHLIGHT_COLOR = a5;
        java.awt.Color a6 = new java.awt.Color(64, 191, 191);
        SplayTreeColors.SEARCH_ACTIVE_COLOR = a6;
        java.awt.Color a7 = new java.awt.Color(0, 127, 127);
        SplayTreeColors.SEARCH_DONE_COLOR = a7;
        java.awt.Color a8 = java.awt.Color.gray;
        SplayTreeColors.POINTER_COLOR = a8;
        java.awt.Color a9 = new java.awt.Color(0, 210, 0);
        SplayTreeColors.NEW_NODE_COLOR = a9;
        java.awt.Color a10 = SplayTreeColors.SEARCH_HIGHLIGHT_COLOR;
        SplayTreeColors.FOUND_NODE_COLOR = a10;
        java.awt.Color a11 = java.awt.Color.orange;
        SplayTreeColors.DEFAULT_NODE_COLOR = a11;
        java.awt.Color a12 = java.awt.Color.green;
        SplayTreeColors.CODE_STACK_ACTIVE_COLOR = a12;
        java.awt.Color a13 = java.awt.Color.green;
        java.awt.Color a14 = a13.darker();
        SplayTreeColors.CODE_STACK_SUSPENDED_COLOR = a14;
        java.awt.Color a15 = java.awt.Color.black;
        SplayTreeColors.CODE_STACK_CODE_COLOR = a15;
        java.awt.Color a16 = java.awt.Color.blue;
        SplayTreeColors.CODE_STACK_COMMENT_COLOR = a16;
        java.awt.Color a17 = java.awt.Color.black;
        SplayTreeColors.CODE_STACK_BOX_COLOR = a17;
        java.awt.Color a18 = java.awt.Color.gray;
        SplayTreeColors.CODE_STACK_LABEL_COLOR = a18;
    }
}