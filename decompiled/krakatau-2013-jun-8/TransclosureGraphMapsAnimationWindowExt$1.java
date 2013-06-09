class TransclosureGraphMapsAnimationWindowExt$1 implements com.cim.AIA.ControlListener {
    final TransclosureGraphMapsAnimationWindowExt this$0;
    
    TransclosureGraphMapsAnimationWindowExt$1(TransclosureGraphMapsAnimationWindowExt a)
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
        if(i == 2350)
        {
            TransclosureGraphMapsAnimationWindowExt a0 = this.this$0;
            a0.hideMatrix();
        }
    }
}