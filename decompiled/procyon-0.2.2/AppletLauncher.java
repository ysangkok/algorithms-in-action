import java.awt.*;
import localization.*;
import java.applet.*;
import javax.swing.*;

public class AppletLauncher extends JApplet implements AppletStub
{
    private String appletToLoad;
    private JApplet theAppletToLoad;
    Thread appletThread;
    
    public void init() {
        this.appletToLoad = this.getParameter("appletToLoad");
        if (this.appletToLoad.endsWith(".class")) {
            this.appletToLoad = this.appletToLoad.replaceAll(".class", "");
        }
        final String language = this.getParameter("LANGUAGE");
        Messages.setActiveLocale(language, "", "");
    }
    
    public void start() {
        try {
            final Class<T> appletClass = Class.forName(this.appletToLoad);
            this.theAppletToLoad = (JApplet)appletClass.newInstance();
            this.theAppletToLoad.setStub(this);
            this.setLayout(new GridLayout(1, 0));
            this.add(this.theAppletToLoad);
            this.theAppletToLoad.init();
            this.theAppletToLoad.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        this.validate();
    }
    
    public void stop() {
        this.theAppletToLoad.stop();
    }
    
    public void destroy() {
        this.theAppletToLoad.destroy();
    }
    
    public void appletResize(final int width, final int height) {
        this.resize(width, height);
    }
}
