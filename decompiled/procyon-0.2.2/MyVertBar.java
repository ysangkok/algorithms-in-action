import java.awt.*;
import com.cim.AIA.*;

public class MyVertBar extends VerticalBar
{
    private boolean _isEmpty;
    
    public MyVertBar(final int seq, final int val, final Color color, final Point p) {
        super(seq, val, color, p);
        this._isEmpty = false;
    }
    
    public MyVertBar(final int seq, final Integer val, final Color color, final Point p) {
        this(seq, val.intValue(), color, p);
    }
    
    public void draw(final Graphics g) {
        super.draw(g);
    }
    
    public boolean isEmpty() {
        return this._isEmpty;
    }
    
    public void setIsEmpty(final boolean val) {
        this._isEmpty = val;
    }
}
