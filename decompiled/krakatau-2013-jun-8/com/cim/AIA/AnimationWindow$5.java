package com.cim.AIA;

class AnimationWindow$5 implements java.awt.event.ActionListener {
    final com.cim.AIA.AnimationWindow this$0;
    
    AnimationWindow$5(com.cim.AIA.AnimationWindow a)
    {
        this.this$0 = a;
        super();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent a)
    {
        com.cim.AIA.AnimationWindow a0 = this.this$0;
        String s = a0.getBuildInfo();
        com.cim.common.MessageDialog a1 = new com.cim.common.MessageDialog(s, true, true);
        StringBuilder a2 = new StringBuilder();
        com.cim.AIA.AnimationWindow a3 = this.this$0;
        String s0 = a3.aboutLabel;
        StringBuilder a4 = a2.append(s0);
        StringBuilder a5 = a4.append(" ");
        com.cim.AIA.AnimationWindow a6 = this.this$0;
        String s1 = a6.frameTitle;
        StringBuilder a7 = a5.append(s1);
        String s2 = a7.toString();
        a1.setTitle(s2);
        a1.setVisible(true);
    }
}