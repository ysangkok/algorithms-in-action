public class PatriciaTreeIter$Path {
    boolean isLeft;
    PatriciaTreeIter$Path next;
    final PatriciaTreeIter this$0;
    
    PatriciaTreeIter$Path(PatriciaTreeIter a, boolean b)
    {
        this.this$0 = a;
        int i = b?1:0;
        super();
        this.isLeft = i != 0;
    }
    
    void add(boolean b)
    {
        PatriciaTreeIter$Path a = this.next;
        int i = b?1:0;
        if(a != null)
        {
            PatriciaTreeIter$Path a0 = this.next;
            a0.add(i != 0);
        }
        else
        {
            PatriciaTreeIter a1 = this.this$0;
            PatriciaTreeIter$Path a2 = new PatriciaTreeIter$Path(a1, i != 0);
            this.next = a2;
        }
    }
}