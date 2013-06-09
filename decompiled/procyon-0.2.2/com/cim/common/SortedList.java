package com.cim.common;

public class SortedList extends List
{
    private static final long serialVersionUID = 3581944959002533490L;
    
    public void addElement(final Sortable sortableObject) {
        if (this.size() == 0) {
            Common.debug("Added first node to sortable list..", 4);
            super.addElement(sortableObject);
            return;
        }
        final int sortKey = sortableObject.getSortKey();
        Common.debug("NEW SORT KEY = " + sortKey, 3);
        for (int i = 0; i < this.size(); ++i) {
            final Sortable obj = (Sortable)this.elementAt(i);
            if (obj.getSortKey() > sortKey) {
                Common.debug("CUR SORT KEY + " + obj.getSortKey(), 3);
                this.insertElementAt(sortableObject, i);
                return;
            }
        }
        Common.debug("Could not find location.. Appending to the end..", 3);
        super.addElement(sortableObject);
    }
}
