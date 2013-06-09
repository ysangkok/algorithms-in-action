import com.cim.AIA.*;
import java.awt.*;

private class MoveMoveable
{
    private Point from;
    private Point to;
    private Moveable moveable;
    private boolean isEntire;
    
    protected MoveMoveable(final Moveable moveable, final Point from, final Point to, final boolean isEntire) {
        super();
        this.moveable = moveable;
        this.from = from;
        this.to = to;
        this.isEntire = isEntire;
    }
}
