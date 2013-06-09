public class PatriciaTree$Path {
    boolean isLeft;
    PatriciaTree$Path next;
    final PatriciaTree this$0;
    
    PatriciaTree$Path(PatriciaTree a, boolean b)
    {
        this.this$0 = a;
        int i = b?1:0;
        super();
        this.isLeft = i != 0;
    }
    
    void add(boolean b)
    {
        PatriciaTree$Path a = this.next;
        int i = b?1:0;
        if(a != null)
        {
            PatriciaTree$Path a0 = this.next;
            a0.add(i != 0);
        }
        else
        {
            PatriciaTree a1 = this.this$0;
            PatriciaTree$Path a2 = new PatriciaTree$Path(a1, i != 0);
            this.next = a2;
        }
    }
}