// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppletLauncher.java

import java.applet.AppletStub;
import java.awt.GridLayout;
import java.io.PrintStream;
import javax.swing.JApplet;
import localization.Messages;

public class AppletLauncher extends JApplet
    implements AppletStub
{

    public AppletLauncher()
    {
    }

    public void init()
    {
        appletToLoad = getParameter("appletToLoad");
        if(appletToLoad.endsWith(".class"))
            appletToLoad = appletToLoad.replaceAll(".class", "");
        String language = getParameter("LANGUAGE");
        Messages.setActiveLocale(language, "", "");
    }

    public void start()
    {
        try
        {
            Class appletClass = Class.forName(appletToLoad);
            theAppletToLoad = (JApplet)appletClass.newInstance();
            theAppletToLoad.setStub(this);
            setLayout(new GridLayout(1, 0));
            add(theAppletToLoad);
            theAppletToLoad.init();
            theAppletToLoad.start();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        validate();
    }

    public void stop()
    {
        theAppletToLoad.stop();
    }

    public void destroy()
    {
        theAppletToLoad.destroy();
    }

    public void appletResize(int width, int height)
    {
        resize(width, height);
    }

    private String appletToLoad;
    private JApplet theAppletToLoad;
    Thread appletThread;
}
