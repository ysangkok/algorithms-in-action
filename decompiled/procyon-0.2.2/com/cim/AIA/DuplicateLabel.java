package com.cim.AIA;

import java.util.*;

public class DuplicateLabel
{
    protected static final String[] DUPLICATE_LABELS;
    
    public static String[] createDuplicateLabels(final Object[] data) {
        return createDuplicateLabels(data, false);
    }
    
    public static String[] createDuplicateLabels(final Object[] data, final boolean createSingle) {
        final String[] duplicateLabels = new String[data.length];
        final Hashtable<Object, Integer> hashtable = new Hashtable(duplicateLabels.length);
        int i;
        Object count;
        int number;
        for (i = 0; i < data.length; ++i) {
            count = hashtable.get(data[i]);
            if (count == null) {
                hashtable.put(data[i], new Integer(1));
            }
            else {
                number = (Integer)count + 1;
                hashtable.put(data[i], new Integer(number));
            }
        }
        if (!createSingle) {
            for (i = 0; i < data.length; ++i) {
                count = hashtable.get(data[i]);
                if (count != null && (Integer)count == 1) {
                    hashtable.remove(data[i]);
                }
            }
        }
        for (i = data.length - 1; i >= 0; --i) {
            count = hashtable.get(data[i]);
            if (count != null) {
                number = ((Integer)count).intValue();
                if (number <= DuplicateLabel.DUPLICATE_LABELS.length) {
                    duplicateLabels[i] = DuplicateLabel.DUPLICATE_LABELS[number - 1];
                }
                hashtable.put(data[i], new Integer(number - 1));
            }
        }
        return duplicateLabels;
    }
    
    static {
        DUPLICATE_LABELS = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "q", "r", "s", "t", "u", "v", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
    }
}
