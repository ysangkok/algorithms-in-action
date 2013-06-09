import java.awt.*;

public class SplayTreeColors
{
    protected static final Color DATA_ITEM_COLOR;
    protected static final Color DATA_ITEM_HIGHLIGHT_COLOR;
    public static final Color INSERT_HIGHLIGHT_COLOR;
    public static final Color INSERT_ACTIVE_COLOR;
    public static final Color INSERT_DONE_COLOR;
    public static final Color SEARCH_HIGHLIGHT_COLOR;
    public static final Color SEARCH_ACTIVE_COLOR;
    public static final Color SEARCH_DONE_COLOR;
    public static final Color POINTER_COLOR;
    public static final Color NEW_NODE_COLOR;
    public static final Color FOUND_NODE_COLOR;
    public static final Color DEFAULT_NODE_COLOR;
    public static final Color CODE_STACK_ACTIVE_COLOR;
    public static final Color CODE_STACK_SUSPENDED_COLOR;
    public static final Color CODE_STACK_CODE_COLOR;
    public static final Color CODE_STACK_COMMENT_COLOR;
    public static final Color CODE_STACK_BOX_COLOR;
    public static final Color CODE_STACK_LABEL_COLOR;
    
    static {
        DATA_ITEM_COLOR = Color.green;
        DATA_ITEM_HIGHLIGHT_COLOR = Color.green.brighter();
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
        INSERT_DONE_COLOR = new Color(96, 127, 96);
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
        SEARCH_DONE_COLOR = new Color(0, 127, 127);
        POINTER_COLOR = Color.gray;
        NEW_NODE_COLOR = new Color(0, 210, 0);
        FOUND_NODE_COLOR = SplayTreeColors.SEARCH_HIGHLIGHT_COLOR;
        DEFAULT_NODE_COLOR = Color.orange;
        CODE_STACK_ACTIVE_COLOR = Color.green;
        CODE_STACK_SUSPENDED_COLOR = Color.green.darker();
        CODE_STACK_CODE_COLOR = Color.black;
        CODE_STACK_COMMENT_COLOR = Color.blue;
        CODE_STACK_BOX_COLOR = Color.black;
        CODE_STACK_LABEL_COLOR = Color.gray;
    }
}
