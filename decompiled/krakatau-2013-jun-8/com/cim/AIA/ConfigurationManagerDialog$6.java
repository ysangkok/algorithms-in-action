package com.cim.AIA;

class ConfigurationManagerDialog$6 implements java.awt.event.ItemListener {
    final com.cim.AIA.ConfigurationManagerDialog this$0;
    
    ConfigurationManagerDialog$6(com.cim.AIA.ConfigurationManagerDialog a)
    {
        this.this$0 = a;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        com.cim.AIA.ConfigurationManagerDialog a0 = this.this$0;
        a0.updateFont(true);
    }
}