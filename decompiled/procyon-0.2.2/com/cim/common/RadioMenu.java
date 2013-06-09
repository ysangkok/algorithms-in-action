package com.cim.common;

import java.awt.*;

public class RadioMenu extends Menu
{
    private static final long serialVersionUID = -8903947943034089806L;
    
    public RadioMenu(final String name) {
        super(name, false);
    }
    
    public RadioMenu(final String name, final boolean tearOff) {
        super(name, tearOff);
    }
    
    public MenuItem add(final MenuItem item) {
        return super.add(item);
    }
    
    public void add(final String name) {
        this.add(new CheckboxMenuItem(name));
    }
    
    public MenuItem getSelectedItem() {
        return this.getSelectedItem(0);
    }
    
    public MenuItem getSelectedItem(final int startAt) {
        for (int i = startAt; i < this.getItemCount(); ++i) {
            if (this.getItem(i) instanceof CheckboxMenuItem) {
                final CheckboxMenuItem item = (CheckboxMenuItem)this.getItem(i);
                if (item.getState()) {
                    return item;
                }
            }
        }
        return null;
    }
    
    public void selectItem(final MenuItem item) {
        final int numberOfItems = this.getItemCount();
        int sectionStart = 0;
        int sectionStop = numberOfItems;
        boolean withinRange = false;
        for (int i = 0; i < numberOfItems; ++i) {
            if (item == this.getItem(i)) {
                withinRange = true;
            }
            if (this.getItem(i).getLabel().equals("-")) {
                if (withinRange) {
                    sectionStop = i;
                }
                else {
                    sectionStart = i;
                }
            }
        }
        final MenuItem oldSelectedMenu = this.getSelectedItem();
        boolean itemFound = false;
        for (int j = sectionStart; j < sectionStop; ++j) {
            try {
                final CheckboxMenuItem currentItem = (CheckboxMenuItem)this.getItem(j);
                if (item == currentItem) {
                    currentItem.setState(true);
                    itemFound = true;
                }
                else {
                    currentItem.setState(false);
                }
            }
            catch (ClassCastException e) {}
        }
        if (!itemFound && oldSelectedMenu != null) {
            ((CheckboxMenuItem)oldSelectedMenu).setState(true);
        }
    }
}
