package com.cim.common;

import java.util.*;
import java.net.*;
import java.awt.*;
import java.applet.*;

public abstract class BasicApplet extends Applet
{
    public static final Cursor CURSOR;
    public static boolean runAsApplet;
    public static String[] arguments;
    
    public URL getCodeBase() {
        if (BasicApplet.runAsApplet) {
            return super.getCodeBase();
        }
        return null;
    }
    
    public String getParameter(final String key) {
        Common.debug("Got here??", 2);
        if (BasicApplet.runAsApplet) {
            return super.getParameter(key);
        }
        for (int i = 0; i < BasicApplet.arguments.length; ++i) {
            final StringTokenizer tok = new StringTokenizer(BasicApplet.arguments[i], "=");
            if (tok.countTokens() == 2 && tok.nextToken().equals(key)) {
                return tok.nextToken();
            }
        }
        return null;
    }
    
    public void init() {
        this.setCursor(BasicApplet.CURSOR);
        System.out.println("In the basic Applet.. Cursor set...");
        this.setBackground(Color.red);
    }
    
    static {
        CURSOR = new Cursor(0);
        BasicApplet.runAsApplet = true;
    }
}
