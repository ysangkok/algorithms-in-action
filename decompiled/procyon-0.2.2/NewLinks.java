import com.cim.AIA.*;
import java.awt.*;

public class NewLinks
{
    private static final Color LINECOLOR;
    private Node srcNode;
    private Node destNode;
    private boolean direction;
    
    public NewLinks(final Node source, final boolean dir, final Node destination) {
        super();
        this.srcNode = source;
        this.destNode = destination;
        this.direction = dir;
    }
    
    public Line getLine() {
        int srcX;
        if (!this.direction) {
            srcX = this.srcNode.getX();
        }
        else {
            srcX = this.srcNode.getX() + this.srcNode.getWidth();
        }
        final Line newLine = new Line(srcX, this.srcNode.getY() + this.srcNode.getHeight(), this.destNode.getX() + this.destNode.getWidth() / 2, this.destNode.getY());
        newLine.showArrowHead(true);
        newLine.showAsDotted(true);
        newLine.setDistanceFromStartForArrowHead(-3);
        newLine.setColor(NewLinks.LINECOLOR);
        return newLine;
    }
    
    static {
        LINECOLOR = Color.blue;
    }
}
