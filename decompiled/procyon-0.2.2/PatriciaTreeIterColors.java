import java.awt.*;

public final class PatriciaTreeIterColors
{
    public static final Color DEFAULT_BACKGROUND_COLOR;
    public static final Color DEFAULT_HIGHLIGHT_COLOR;
    public static final Color DEFAULT_TEXT_COLOR;
    public static final Color DIFFERENTIATING_COLOR;
    public static final Color UPLINK_COLOR;
    public static final Color POINTER_COLOR;
    public static final Color YES_COLOR;
    public static final Color NO_COLOR;
    public static final Color CURRENT_BIT_COLOR;
    public static final Color DEFAULT_TREE_COLOR;
    public static final Color SEARCH_PATH_COLOR;
    public static final Color DEFAULT_RING_COLOR;
    public static final Color INSERT_HIGHLIGHT_COLOR;
    public static final Color INSERT_ACTIVE_COLOR;
    public static final Color INSERT_DONE_COLOR;
    public static final Color INSERT_BIT_HIGHLIGHT_COLOR;
    public static final Color INSERT_BIT_ACTIVE_COLOR;
    public static final Color SEARCH_HIGHLIGHT_COLOR;
    public static final Color SEARCH_ACTIVE_COLOR;
    public static final Color SEARCH_DONE_COLOR;
    public static final Color SEARCH_BIT_HIGHLIGHT_COLOR;
    public static final Color SEARCH_BIT_ACTIVE_COLOR;
    public static final Color DATA_ITEM_COLOR;
    public static final Color DATA_ITEM_HIGHLIGHT_COLOR;
    
    static {
        DEFAULT_BACKGROUND_COLOR = Color.white;
        DEFAULT_HIGHLIGHT_COLOR = Color.orange;
        DEFAULT_TEXT_COLOR = Color.black;
        DIFFERENTIATING_COLOR = new Color(255, 191, 191);
        UPLINK_COLOR = Color.gray;
        POINTER_COLOR = Color.gray;
        YES_COLOR = Color.green;
        NO_COLOR = Color.red;
        CURRENT_BIT_COLOR = Color.yellow;
        DEFAULT_TREE_COLOR = Color.orange;
        SEARCH_PATH_COLOR = Color.red;
        DEFAULT_RING_COLOR = Color.black;
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
        INSERT_DONE_COLOR = new Color(96, 127, 96);
        INSERT_BIT_HIGHLIGHT_COLOR = new Color(127, 255, 127);
        INSERT_BIT_ACTIVE_COLOR = PatriciaTreeIterColors.INSERT_HIGHLIGHT_COLOR;
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
        SEARCH_DONE_COLOR = new Color(0, 127, 127);
        SEARCH_BIT_HIGHLIGHT_COLOR = new Color(255, 255, 127);
        SEARCH_BIT_ACTIVE_COLOR = PatriciaTreeIterColors.SEARCH_HIGHLIGHT_COLOR;
        DATA_ITEM_COLOR = new Color(128, 128, 255);
        DATA_ITEM_HIGHLIGHT_COLOR = new Color(191, 191, 255);
    }
}
