class RadixTrieMultiAnimationWindow$1 implements java.awt.event.ItemListener {
    final RadixTrieMultiAnimationWindow this$0;
    
    RadixTrieMultiAnimationWindow$1(RadixTrieMultiAnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        RadixTrieMultiAnimationWindow a0 = this.this$0;
        a0.oneBit();
    }
}