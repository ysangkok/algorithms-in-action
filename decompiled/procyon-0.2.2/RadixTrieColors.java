import java.awt.*;

public class RadixTrieColors
{
    protected static final Color DATA_ITEM_COLOR;
    protected static final Color DATA_ITEM_HIGHLIGHT_COLOR;
    public static final Color INSERT_HIGHLIGHT_COLOR;
    public static final Color INSERT_ACTIVE_COLOR;
    public static final Color INSERT_DONE_COLOR;
    public static final Color INSERT_BIT_ACTIVE_COLOR;
    public static final Color INSERT_BIT_HIGHLIGHT_COLOR;
    public static final Color SEARCH_HIGHLIGHT_COLOR;
    public static final Color SEARCH_ACTIVE_COLOR;
    public static final Color SEARCH_DONE_COLOR;
    public static final Color SEARCH_BIT_HIGHLIGHT_COLOR;
    public static final Color SEARCH_BIT_ACTIVE_COLOR;
    public static final Color COMPARE_BIT_ACTIVE_COLOR;
    public static final Color COMPARE_BIT_HIGHLIGHT_COLOR;
    public static final Color POINTER_COLOR;
    public static final Color NEW_NODE_COLOR;
    public static final Color COMPARE_NODE_COLOR;
    public static final Color DEFAULT_NODE_COLOR;
    public static final Color DEFAULT_BIT_USEFUL_COLOR;
    public static final Color DEFAULT_BIT_NOT_USEFUL_COLOR;
    
    static {
        DATA_ITEM_COLOR = Color.green;
        DATA_ITEM_HIGHLIGHT_COLOR = Color.green.brighter();
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
        INSERT_DONE_COLOR = new Color(96, 127, 96);
        INSERT_BIT_ACTIVE_COLOR = RadixTrieColors.INSERT_HIGHLIGHT_COLOR;
        INSERT_BIT_HIGHLIGHT_COLOR = new Color(127, 255, 127);
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
        SEARCH_DONE_COLOR = new Color(0, 127, 127);
        SEARCH_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 127);
        SEARCH_BIT_ACTIVE_COLOR = RadixTrieColors.SEARCH_HIGHLIGHT_COLOR;
        COMPARE_BIT_ACTIVE_COLOR = new Color(64, 191, 191);
        COMPARE_BIT_HIGHLIGHT_COLOR = new Color(127, 255, 255);
        POINTER_COLOR = Color.gray;
        NEW_NODE_COLOR = new Color(0, 210, 0);
        COMPARE_NODE_COLOR = RadixTrieColors.COMPARE_BIT_ACTIVE_COLOR;
        DEFAULT_NODE_COLOR = Color.orange;
        DEFAULT_BIT_USEFUL_COLOR = Color.black;
        DEFAULT_BIT_NOT_USEFUL_COLOR = Color.gray.brighter();
    }
}
