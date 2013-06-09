import java.awt.*;
import com.cim.AIA.*;

public class RadixTrieDataItem extends Element
{
    private int key;
    private String label;
    private int height;
    private int width;
    Point position;
    boolean highlight;
    
    public RadixTrieDataItem(final String label, final int key) {
        super(2, label);
        this.highlight = false;
        this.key = key;
    }
    
    public boolean equals(final Selectable selectable) {
        return selectable instanceof RadixTrieDataItem && this.label.compareTo(((RadixTrieDataItem)selectable).label) == 0;
    }
    
    public void highlight() {
        this.highlight = true;
    }
    
    public void unHighlight() {
        this.highlight = false;
    }
    
    public void setPosition(final Point position) {
        this.position = position;
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public Node getNode() {
        return new Node("", 0);
    }
    
    public void setX(final int x) {
        this.position.x = x;
    }
    
    public void setY(final int y) {
        this.position.y = y;
    }
    
    public int getX() {
        return this.position.x;
    }
    
    public int getY() {
        return this.position.y;
    }
    
    public void shiftEntire(final int x, final int y) {
        final Point position = this.position;
        position.x = position.x + x;
        final Point position2 = this.position;
        position2.y = position2.y + y;
    }
    
    public Selectable getItemAt(final Point point) {
        if (new Rectangle(this.position.x, this.position.y, this.getWidth(), this.getHeight()).contains(point)) {
            return this;
        }
        return null;
    }
    
    public void draw(final Graphics g, final Point position) {
    }
    
    public void draw(final Graphics g) {
    }
}
