import java.awt.*;

public class LineMoveRequest
{
    public static final int IS_X1_POSITION_INSERTED_ID = -45;
    public static final int IS_X2_POSITION_INSERTED_ID = -46;
    public static final int LEFT_NULL_CHILD = -50;
    public static final int RIGHT_NULL_CHILD = -51;
    protected int fromPositionInsertedId;
    protected int toPositionInsertedId;
    protected int parentPositionInsertedId;
    protected int x1PositionInsertedId;
    protected int x2PositionInsertedId;
    protected Point fromPoint;
    protected Point toPoint;
    protected Point basePoint;
    protected String label;
    
    public LineMoveRequest() {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
    }
    
    public LineMoveRequest(final int fromPositionInsertedId, final int toPositionInsertedId) {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
        this.fromPositionInsertedId = fromPositionInsertedId;
        this.toPositionInsertedId = toPositionInsertedId;
    }
    
    public LineMoveRequest(final int fromPositionInsertedId, final int toPositionInsertedId, final int parentPositionInsertedId) {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
        this.fromPositionInsertedId = fromPositionInsertedId;
        this.toPositionInsertedId = toPositionInsertedId;
        this.parentPositionInsertedId = parentPositionInsertedId;
    }
    
    public LineMoveRequest(final Point fromPoint, final Point toPoint) {
        super();
        this.fromPositionInsertedId = -1;
        this.toPositionInsertedId = -1;
        this.parentPositionInsertedId = -1;
        this.x1PositionInsertedId = -1;
        this.x2PositionInsertedId = -1;
        this.fromPoint = null;
        this.toPoint = null;
        this.basePoint = null;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }
    
    public Point getBasePoint() {
        return this.basePoint;
    }
    
    public Point getFromPoint() {
        return this.fromPoint;
    }
    
    public int getFromPositionInsertedId() {
        return this.fromPositionInsertedId;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public int getParentPositionInsertedId() {
        return this.parentPositionInsertedId;
    }
    
    public Point getToPoint() {
        return this.toPoint;
    }
    
    public int getToPositionInsertedId() {
        return this.toPositionInsertedId;
    }
    
    public int getX1PositionInsertedId() {
        return this.x1PositionInsertedId;
    }
    
    public int getX2PositionInsertedId() {
        return this.x2PositionInsertedId;
    }
    
    public void setBasePoint(final Point basePoint) {
        this.basePoint = basePoint;
    }
    
    public void setFromPoint(final Point fromPoint) {
        this.fromPoint = fromPoint;
    }
    
    public void setFromPositionInsertedId(final int fromPositionInsertedId) {
        this.fromPositionInsertedId = fromPositionInsertedId;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public void setToPoint(final Point toPoint) {
        this.toPoint = toPoint;
    }
    
    public void setToPositionInsertedId(final int toPositionInsertedId) {
        this.toPositionInsertedId = toPositionInsertedId;
    }
    
    public void setX1PositionInsertedId(final int x1PositionInsertedId) {
        this.x1PositionInsertedId = x1PositionInsertedId;
    }
    
    public void setX2PositionInsertedId(final int x2PositionInsertedId) {
        this.x2PositionInsertedId = x2PositionInsertedId;
    }
}
