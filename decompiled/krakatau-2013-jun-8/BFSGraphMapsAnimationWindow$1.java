class BFSGraphMapsAnimationWindow$1 implements com.cim.AIA.ControlListener {
    final BFSGraphMapsAnimationWindow this$0;
    
    BFSGraphMapsAnimationWindow$1(BFSGraphMapsAnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        BFSGraphMapsAnimationWindow a0 = this.this$0;
        a0.setBackMode();
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = a.getType();
        label1: {
            label0: {
                if(i != 2340)
                {
                    break label0;
                }
                BFSGraphMapsAnimationWindow a0 = this.this$0;
                a0.zoom(true);
                break label1;
            }
            int i0 = a.getType();
            label2: {
                if(i0 != 2341)
                {
                    break label2;
                }
                BFSGraphMapsAnimationWindow a1 = this.this$0;
                a1.zoom(false);
                break label1;
            }
            int i1 = a.getType();
            if(i1 == 2342)
            {
                BFSGraphMapsAnimationWindow a2 = this.this$0;
                a2.restartAlgorithm();
            }
        }
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
}