package com.cim.AIA;

import java.awt.*;
import java.util.*;

public class NonRadioCheckboxGroup
{
    protected Vector<E> checkboxes;
    
    public NonRadioCheckboxGroup() {
        super();
        this.checkboxes = new Vector();
    }
    
    public void add(final Checkbox checkbox) {
        this.checkboxes.addElement(checkbox);
    }
    
    public Checkbox[] getSelected() {
        int selectedCount = 0;
        for (int i = 0; i < this.checkboxes.size(); ++i) {
            if (((Checkbox)this.checkboxes.elementAt(i)).getState()) {
                ++selectedCount;
            }
        }
        final Checkbox[] selected = new Checkbox[selectedCount];
        int posi = 0;
        for (int j = 0; j < this.checkboxes.size(); ++j) {
            if (((Checkbox)this.checkboxes.elementAt(j)).getState()) {
                selected[posi] = (Checkbox)this.checkboxes.elementAt(j);
                ++posi;
            }
        }
        return selected;
    }
}
