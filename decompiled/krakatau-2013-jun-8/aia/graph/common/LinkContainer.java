package aia.graph.common;

public class LinkContainer {
    java.util.Vector m_vecLinks;
    
    public LinkContainer()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.m_vecLinks = a;
    }
    
    public aia.graph.common.Link addLink(aia.graph.common.NodeInfo a, aia.graph.common.NodeInfo a0, boolean b, int i)
    {
        aia.graph.common.Link a1 = null;
        aia.graph.common.Link a2 = this.findLink(a, a0);
        int i0 = b?1:0;
        label0: {
            if(a2 != null)
            {
                a1 = a2;
                break label0;
            }
            aia.graph.common.Link a3 = this.findLink(a0, a);
            label1: {
                if(a3 == null)
                {
                    break label1;
                }
                if(i0 == 0)
                {
                    break label1;
                }
                a3.setOmniDirectional(true);
                a1 = a3;
                break label0;
            }
            aia.graph.common.Link a4 = new aia.graph.common.Link(a, a0, false);
            a4.setWeight(i);
            a4.setOmniDirectional(i0 != 0);
            java.util.Vector a5 = this.m_vecLinks;
            a5.addElement((Object)a4);
            a1 = a4;
        }
        return a1;
    }
    
    public aia.graph.common.Link findLink(aia.graph.common.NodeInfo a, aia.graph.common.NodeInfo a0)
    {
        aia.graph.common.Link a1 = null;
        java.util.Vector a2 = this.m_vecLinks;
        int i = a2.size();
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                a1 = null;
                break label1;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a3 = this.m_vecLinks;
                int i1 = a3.size();
                label2: {
                    if(i0 < i1)
                    {
                        break label2;
                    }
                    a1 = null;
                    break;
                }
                java.util.Vector a4 = this.m_vecLinks;
                Object a5 = a4.elementAt(i0);
                aia.graph.common.Link dummy = (aia.graph.common.Link)a5;
                aia.graph.common.Link a6 = (aia.graph.common.Link)a5;
                aia.graph.common.NodeInfo a7 = a6.getStart();
                int i2 = a7.getKey();
                int i3 = a.getKey();
                label3: {
                    if(i2 != i3)
                    {
                        break label3;
                    }
                    aia.graph.common.NodeInfo a8 = a6.getEnd();
                    int i4 = a8.getKey();
                    int i5 = a0.getKey();
                    if(i4 != i5)
                    {
                        break label3;
                    }
                    a1 = a6;
                    break;
                }
                int i6 = a6.getOmniDirectional()?1:0;
                label5: {
                    label4: {
                        if(i6 == 0)
                        {
                            break label4;
                        }
                        aia.graph.common.NodeInfo a9 = a6.getStart();
                        int i7 = a9.getKey();
                        int i8 = a0.getKey();
                        if(i7 != i8)
                        {
                            break label4;
                        }
                        aia.graph.common.NodeInfo a10 = a6.getEnd();
                        int i9 = a10.getKey();
                        int i10 = a.getKey();
                        if(i9 == i10)
                        {
                            break label5;
                        }
                    }
                    int i11 = i0 + 1;
                    i0 = i11;
                    continue;
                }
                a1 = a6;
                break;
            }
        }
        return a1;
    }
    
    public java.util.Vector getLinks()
    {
        java.util.Vector a = this.m_vecLinks;
        return a;
    }
    
    public int size()
    {
        java.util.Vector a = this.m_vecLinks;
        int i = a.size();
        return i;
    }
}