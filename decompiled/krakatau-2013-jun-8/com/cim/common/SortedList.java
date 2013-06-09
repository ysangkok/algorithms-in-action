package com.cim.common;

public class SortedList extends com.cim.common.List {
    final private static long serialVersionUID = 3581944959002533490L;
    
    public SortedList()
    {
        super();
    }
    
    public void addElement(com.cim.common.Sortable a)
    {
        int i = this.size();
        Object a0 = a;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                com.cim.common.Common.debug("Added first node to sortable list..", 4);
                ((com.cim.common.List)this).addElement(a0);
                break label1;
            }
            int i0 = ((com.cim.common.Sortable)a0).getSortKey();
            StringBuilder a1 = new StringBuilder();
            StringBuilder a2 = a1.append("NEW SORT KEY = ");
            StringBuilder a3 = a2.append(i0);
            String s = a3.toString();
            com.cim.common.Common.debug(s, 3);
            int i1 = 0;
            while(true)
            {
                int i2 = this.size();
                label2: {
                    if(i1 < i2)
                    {
                        break label2;
                    }
                    com.cim.common.Common.debug("Could not find location.. Appending to the end..", 3);
                    ((com.cim.common.List)this).addElement(a0);
                    break;
                }
                Object a4 = this.elementAt(i1);
                com.cim.common.Sortable dummy = (com.cim.common.Sortable)a4;
                int i3 = ((com.cim.common.Sortable)a4).getSortKey();
                if(i3 <= i0)
                {
                    int i4 = i1 + 1;
                    i1 = i4;
                    continue;
                }
                StringBuilder a5 = new StringBuilder();
                StringBuilder a6 = a5.append("CUR SORT KEY + ");
                int i5 = ((com.cim.common.Sortable)a4).getSortKey();
                StringBuilder a7 = a6.append(i5);
                String s0 = a7.toString();
                com.cim.common.Common.debug(s0, 3);
                this.insertElementAt(a0, i1);
                break;
            }
        }
    }
}