public class KMPMoveRequest {
    public java.awt.Point from;
    public java.awt.Point to;
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;
    public KMPString theString;
    
    public KMPMoveRequest(KMPString a, int i, int i0, int i1, int i2)
    {
        super();
        java.awt.Point a0 = new java.awt.Point();
        this.from = a0;
        java.awt.Point a1 = new java.awt.Point();
        this.to = a1;
        this.theString = a;
        this.fromX = i;
        this.fromY = i0;
        this.toX = i1;
        this.toY = i2;
        java.awt.Point a2 = this.from;
        a2.x = i;
        java.awt.Point a3 = this.from;
        a3.y = i0;
        java.awt.Point a4 = this.to;
        a4.x = i1;
        java.awt.Point a5 = this.to;
        a5.y = i2;
    }
}