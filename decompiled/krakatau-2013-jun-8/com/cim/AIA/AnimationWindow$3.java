package com.cim.AIA;

class AnimationWindow$3 implements java.awt.event.ActionListener {
    final com.cim.AIA.AnimationWindow this$0;
    
    AnimationWindow$3(com.cim.AIA.AnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.ConfigurationManager a0 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a0.askUserForChanges();
    }
}