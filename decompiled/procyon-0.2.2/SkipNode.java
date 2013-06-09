import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class SkipNode
{
    protected SkipNode[] next;
    protected Object data;
    protected int levels;
    protected Node[] nodes;
    protected Line[] nextNodesLines;
    protected Point pos;
    protected int height;
    
    public SkipNode(final Object value, final int key, final int size) {
        super();
        this.pos = new Point(0, 0);
        this.height = 0;
        this.next = new SkipNode[size];
        for (int j = 0; j < size; ++j) {
            this.next[j] = null;
        }
        this.data = value;
        this.levels = size;
        this.nodes = new Node[this.levels + 1];
        this.nextNodesLines = new Line[this.levels + 1];
        this.nodes[0] = new Node(this.data, key);
        this.nodes[0].setMarkersBelowValue(false);
        for (int i = 1; i < this.nodes.length; ++i) {
            this.nodes[i] = new Node(Messages.getString("SkipNode.0"), key);
            this.nodes[i].showObject(false);
            this.nodes[i].setMarkersBelowValue(false);
        }
        for (int k = 0; k < this.nextNodesLines.length; ++k) {
            this.nextNodesLines[k] = new Line(new Point(0, 0), new Point(0, 0));
        }
        this.height = (this.levels + 1) * this.nodes[0].getHeight();
    }
    
    public Point getPosition() {
        return this.pos;
    }
    
    public void setPosition(final Point pos) {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void incrementHeight(final int increment) {
        this.height = this.height + increment;
    }
}
