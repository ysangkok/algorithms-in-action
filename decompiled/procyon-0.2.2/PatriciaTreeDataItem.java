import com.cim.AIA.*;

public class PatriciaTreeDataItem
{
    private Node node;
    private int key;
    
    PatriciaTreeDataItem() {
        super();
        this.key = 0;
        this.node = new Node("0", 0);
        this.node.setBackgroundColor(PatriciaTreeColors.DATA_ITEM_COLOR);
        this.node.setHighlightColor(PatriciaTreeColors.DATA_ITEM_HIGHLIGHT_COLOR);
    }
    
    PatriciaTreeDataItem(final Node node, final int key) {
        super();
        this.node = node;
        this.key = key;
        this.node.setWidth(node.getWidth());
        this.node.setMarkersBelowValue(false);
        this.node.setBackgroundColor(PatriciaTreeColors.DATA_ITEM_COLOR);
        this.node.setHighlightColor(PatriciaTreeColors.DATA_ITEM_HIGHLIGHT_COLOR);
    }
    
    public int getKey() {
        return this.key;
    }
    
    public Node getNode() {
        return this.node;
    }
}
