import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class SList
{
    protected SList next;
    protected Object data;
    protected Node node;
    protected Node nextNode;
    protected Line nextNodeLine;
    protected Point pos;
    protected int height;
    
    public SList(final Object value, final int key) {
        super();
        this.pos = new Point(0, 0);
        this.height = 0;
        this.next = null;
        this.data = value;
        this.node = new Node(this.data, key);
        this.node.setMarkersBelowValue(false);
        this.nextNode = new Node(Messages.getString("SList.0"), key);
        this.nextNode.showObject(false);
        this.height = 2 * this.node.getHeight();
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
