import java.awt.*;

public class KMPMoveRequest
{
    public Point from;
    public Point to;
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;
    public KMPString theString;
    
    public KMPMoveRequest(final KMPString theString, final int fromX, final int fromY, final int toX, final int toY) {
        super();
        this.from = new Point();
        this.to = new Point();
        this.theString = theString;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.from.x = fromX;
        this.from.y = fromY;
        this.to.x = toX;
        this.to.y = toY;
    }
}
