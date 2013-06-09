public class SplitMarker
{
    protected int level;
    protected int position;
    
    public SplitMarker(final int level, final int position) {
        super();
        this.level = level;
        this.position = position;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public int getPosition() {
        return this.position;
    }
}
