public class AppletLauncher extends javax.swing.JApplet implements java.applet.AppletStub {
    private String appletToLoad;
    private javax.swing.JApplet theAppletToLoad;
    Thread appletThread;
    
    public AppletLauncher()
    {
        super();
    }
    
    public void init()
    {
        String s = this.getParameter("appletToLoad");
        this.appletToLoad = s;
        String s0 = this.appletToLoad;
        int i = s0.endsWith(".class")?1:0;
        if(i != 0)
        {
            String s1 = this.appletToLoad;
            String s2 = s1.replaceAll(".class", "");
            this.appletToLoad = s2;
        }
        String s3 = this.getParameter("LANGUAGE");
        localization.Messages.setActiveLocale(s3, "", "");
    }
    
    public void start()
    {
        try
        {
            String s = this.appletToLoad;
            Class a = Class.forName(s);
            Object a0 = a.newInstance();
            javax.swing.JApplet dummy = (javax.swing.JApplet)a0;
            javax.swing.JApplet a1 = (javax.swing.JApplet)a0;
            this.theAppletToLoad = a1;
            javax.swing.JApplet a2 = this.theAppletToLoad;
            a2.setStub((java.applet.AppletStub)this);
            java.awt.GridLayout a3 = new java.awt.GridLayout(1, 0);
            this.setLayout((java.awt.LayoutManager)a3);
            javax.swing.JApplet a4 = this.theAppletToLoad;
            java.awt.Component a5 = this.add((java.awt.Component)a4);
            javax.swing.JApplet a6 = this.theAppletToLoad;
            a6.init();
            javax.swing.JApplet a7 = this.theAppletToLoad;
            a7.start();
        }
        catch(Exception a8)
        {
            java.io.PrintStream a9 = System.out;
            a9.println((Object)a8);
        }
        this.validate();
    }
    
    public void stop()
    {
        javax.swing.JApplet a = this.theAppletToLoad;
        a.stop();
    }
    
    public void destroy()
    {
        javax.swing.JApplet a = this.theAppletToLoad;
        a.destroy();
    }
    
    public void appletResize(int i, int i0)
    {
        this.resize(i, i0);
    }
}