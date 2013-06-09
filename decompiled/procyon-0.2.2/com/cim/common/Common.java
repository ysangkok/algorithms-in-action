package com.cim.common;

import java.net.*;
import java.io.*;
import java.awt.*;

public class Common
{
    public static boolean DEBUG;
    public static int DEBUG_LEVEL;
    public static final int MENUBAR_HEIGHT = 0;
    public static final int CONTROL_PANEL_HEIGHT = 30;
    public static final int CONTROL_PANEL_LEFT_MARGIN = 5;
    public static final int CONTROL_PANEL_TOP_MARGIN = 5;
    public static final int CONTROL_PANEL_HGAP = 5;
    public static final int CONTROL_PANEL_VGAP = 2;
    public static final Color CONTROL_PANEL_BGCOLOR;
    public static final Dimension CONTROL_PANEL_BUTTON_DIMENSION;
    public static final Dimension CONTROL_PANEL_LABEL_DIMENSION;
    public static final Dimension CONTROL_PANEL_SPEEDBAR_DIMENSION;
    public static final Font FT_BUTTON;
    public static final Font FT_LABEL;
    public static final int MAX_SPEED = 1000;
    private static Common instance;
    
    public static void debug(final String msg) {
        debug(msg, 5);
    }
    
    public static void debug(final String msg, final int debugLevel) {
        if (Common.DEBUG && debugLevel < Common.DEBUG_LEVEL) {
            message(msg);
        }
    }
    
    public static void exitError() {
        System.exit(1);
    }
    
    public static void exitOk() {
        System.exit(0);
    }
    
    public static Common getInstance() {
        if (Common.instance == null) {
            Common.instance = new Common();
        }
        return Common.instance;
    }
    
    public static void message(final String msg) {
        System.out.println(msg);
    }
    
    public Image getImage(final String name) {
        final MediaTracker tracker = new MediaTracker(new Canvas());
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = null;
        debug("FILE TO BE LOADED " + name, 5);
        final InputStream in;
        final Image image2;
        final Exception ex;
        final Exception e;
        final byte[] imageBytes;
        Label_0093:
        {
            try {
                in = this.getClass().getResourceAsStream("/" + name);
                if (in != null) {
                    break Label_0093;
                }
                System.out.println("getResourceAsStream returned null!");
                image2 = null;
            }
            catch (Exception) {
                e = ex;
                System.out.println("Exception while getting image??");
                e.printStackTrace();
            }
            return image2;
            try {
                imageBytes = new byte[in.available()];
                in.read(imageBytes);
                image = toolkit.createImage(imageBytes);
            }
            catch (Exception) {}
        }
        tracker.addImage(image, 1);
        try {
            tracker.waitForID(1);
            if (tracker.isErrorAny()) {
                System.out.println("Could not load all the images..");
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return image;
    }
    
    public Image getImage(final URL name) {
        return this.getImage(name.toString());
    }
    
    static {
        Common.DEBUG = false;
        Common.DEBUG_LEVEL = 2;
        CONTROL_PANEL_BGCOLOR = Color.lightGray;
        CONTROL_PANEL_BUTTON_DIMENSION = new Dimension(50, 20);
        CONTROL_PANEL_LABEL_DIMENSION = new Dimension(25, 10);
        CONTROL_PANEL_SPEEDBAR_DIMENSION = new Dimension(150, 10);
        FT_BUTTON = new Font("Helvetica", 3, 11);
        FT_LABEL = new Font("Helvetica", 0, 10);
        Common.instance = null;
    }
}
