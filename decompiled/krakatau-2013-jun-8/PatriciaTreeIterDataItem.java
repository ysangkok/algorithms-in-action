public class PatriciaTreeIterDataItem {
    private com.cim.AIA.Node node;
    private int key;
    
    PatriciaTreeIterDataItem()
    {
        super();
        this.key = 0;
        com.cim.AIA.Node a = new com.cim.AIA.Node((Object)"0", 0);
        this.node = a;
        com.cim.AIA.Node a0 = this.node;
        java.awt.Color a1 = PatriciaTreeIterColors.DATA_ITEM_COLOR;
        a0.setBackgroundColor(a1);
        com.cim.AIA.Node a2 = this.node;
        java.awt.Color a3 = PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR;
        a2.setHighlightColor(a3);
    }
    
    PatriciaTreeIterDataItem(com.cim.AIA.Node a, int i)
    {
        super();
        this.node = a;
        this.key = i;
        com.cim.AIA.Node a0 = this.node;
        int i0 = a.getWidth();
        a0.setWidth(i0);
        com.cim.AIA.Node a1 = this.node;
        a1.setMarkersBelowValue(false);
        com.cim.AIA.Node a2 = this.node;
        java.awt.Color a3 = PatriciaTreeIterColors.DATA_ITEM_COLOR;
        a2.setBackgroundColor(a3);
        com.cim.AIA.Node a4 = this.node;
        java.awt.Color a5 = PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR;
        a4.setHighlightColor(a5);
    }
    
    public int getKey()
    {
        int i = this.key;
        return i;
    }
    
    public com.cim.AIA.Node getNode()
    {
        com.cim.AIA.Node a = this.node;
        return a;
    }
}