public class ChangeModeButton extends com.cim.AIA.ControlButton {
    final private static long serialVersionUID = 3962709668836978663L;
    final private static int CHANGE_MODE_EVENT = 129381;
    protected boolean reInitialiseAlgorithm;
    protected boolean clearAlgorithmState;
    protected boolean storeAlgorithmState;
    protected boolean forceAlgorithmStore;
    
    public ChangeModeButton(String s, String s0, com.cim.AIA.AnimationWindow a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0, (com.cim.AIA.Controlable)a, a0);
        this.reInitialiseAlgorithm = false;
        this.clearAlgorithmState = false;
        this.storeAlgorithmState = true;
        this.forceAlgorithmStore = true;
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = a.getType();
        label1: {
            label0: {
                if(i != 123128)
                {
                    break label0;
                }
                this.setEnabled(false);
                break label1;
            }
            int i0 = a.getType();
            if(i0 == 123129)
            {
                this.setEnabled(true);
            }
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(false);
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(false);
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 129381);
        return a;
    }
    
    protected void processThread()
    {
    }
    
    public void setClearAlgorithmState(boolean b)
    {
        this.clearAlgorithmState = b;
    }
    
    public void setForceAlgorithmStore(boolean b)
    {
        this.forceAlgorithmStore = b;
    }
    
    public void setReinitialiseAlgorithm(boolean b)
    {
        this.reInitialiseAlgorithm = b;
    }
    
    public void setStoreAlgorithmState(boolean b)
    {
        this.storeAlgorithmState = b;
    }
}