public protected class Path
{
    boolean isLeft;
    Path next;
    
    Path(final boolean isLeft) {
        super();
        this.isLeft = isLeft;
    }
    
    void add(final boolean isLeft) {
        if (this.next == null) {
            this.next = new Path(isLeft);
        }
        else {
            this.next.add(isLeft);
        }
    }
}
