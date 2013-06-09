class MinSpanTreePrimGraphMapsAnimationWindowExt$1 implements com.cim.AIA.ControlListener {
    final MinSpanTreePrimGraphMapsAnimationWindowExt this$0;
    
    MinSpanTreePrimGraphMapsAnimationWindowExt$1(MinSpanTreePrimGraphMapsAnimationWindowExt a)
    {
        this.this$0 = a;
        super();
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = a.getType();
        label1: {
            label0: {
                if(i != 2350)
                {
                    break label0;
                }
                MinSpanTreePrimGraphMapsAnimationWindowExt a0 = this.this$0;
                a0.hideMatrix();
                break label1;
            }
            int i0 = a.getType();
            if(i0 == 2351)
            {
                MinSpanTreePrimGraphMapsAnimationWindowExt a1 = this.this$0;
                a1.hideStructure();
            }
        }
    }
}