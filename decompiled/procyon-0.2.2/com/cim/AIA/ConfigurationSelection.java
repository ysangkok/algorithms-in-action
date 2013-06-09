package com.cim.AIA;

import java.util.*;

public abstract class ConfigurationSelection
{
    public static final String ALL_CLASSES = "Applies To All Classes";
    protected Vector<E> classNameAppliesTo;
    
    public ConfigurationSelection() {
        super();
        this.classNameAppliesTo = new Vector();
    }
    
    public void addClass(final ConfigurationSelectionListener configurationSelectionListener) {
        this.classNameAppliesTo.addElement(configurationSelectionListener.getClassName());
    }
    
    public boolean appliesTo(final ConfigurationSelectionListener configurationSelectionListener) {
        for (int i = 0; i < this.classNameAppliesTo.size(); ++i) {
            final String classString = (String)((String)this.classNameAppliesTo.elementAt(i));
            if (classString.equalsIgnoreCase("Applies To All Classes")) {
                return true;
            }
            if (classString.equalsIgnoreCase(configurationSelectionListener.getClassName())) {
                return true;
            }
        }
        return false;
    }
    
    public void appliesToAll() {
        this.classNameAppliesTo.addElement("Applies To All Classes");
    }
    
    public abstract void restoreOriginal();
}
