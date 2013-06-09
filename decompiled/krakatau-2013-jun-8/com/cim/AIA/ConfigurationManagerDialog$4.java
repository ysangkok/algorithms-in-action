package com.cim.AIA;

class ConfigurationManagerDialog$4 implements java.awt.event.AdjustmentListener {
    final com.cim.AIA.ConfigurationManagerDialog this$0;
    
    ConfigurationManagerDialog$4(com.cim.AIA.ConfigurationManagerDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void adjustmentValueChanged(java.awt.event.AdjustmentEvent a)
    {
        com.cim.AIA.ConfigurationManagerDialog a0 = this.this$0;
        a0.updateColor(true);
    }
}