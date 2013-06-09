package aia.graph.common;

public class Link {
    protected boolean m_bOmniDirectional;
    protected aia.graph.common.NodeInfo m_start;
    protected aia.graph.common.NodeInfo m_end;
    protected boolean m_bHighlighted;
    protected java.awt.Color m_linkColor;
    protected int m_weight;
    protected boolean m_bUseWeight;
    
    public Link(aia.graph.common.NodeInfo a, aia.graph.common.NodeInfo a0, boolean b)
    {
        super();
        int i = b?1:0;
        this.m_start = a;
        this.m_end = a0;
        this.m_bOmniDirectional = i != 0;
        this.m_bHighlighted = false;
        java.awt.Color a1 = java.awt.Color.black;
        this.m_linkColor = a1;
        this.m_bUseWeight = true;
    }
    
    public aia.graph.common.NodeInfo getEnd()
    {
        aia.graph.common.NodeInfo a = this.m_end;
        return a;
    }
    
    public boolean getHighlighted()
    {
        int i = this.m_bHighlighted?1:0;
        return i != 0;
    }
    
    public java.awt.Color getLinkColor()
    {
        java.awt.Color a = this.m_linkColor;
        return a;
    }
    
    public boolean getOmniDirectional()
    {
        int i = this.m_bOmniDirectional?1:0;
        return i != 0;
    }
    
    public aia.graph.common.NodeInfo getStart()
    {
        aia.graph.common.NodeInfo a = this.m_start;
        return a;
    }
    
    public int getWeight()
    {
        int i = this.m_weight;
        return i;
    }
    
    public boolean isEqualTo(aia.graph.common.Link a)
    {
        int i = 0;
        aia.graph.common.NodeInfo a0 = a.getStart();
        int i0 = a0.getKey();
        aia.graph.common.NodeInfo a1 = this.m_start;
        int i1 = a1.getKey();
        label1: {
            label0: {
                if(i0 != i1)
                {
                    break label0;
                }
                aia.graph.common.NodeInfo a2 = a.getEnd();
                int i2 = a2.getKey();
                aia.graph.common.NodeInfo a3 = this.m_end;
                int i3 = a3.getKey();
                if(i2 != i3)
                {
                    break label0;
                }
                i = 1;
                break label1;
            }
            int i4 = this.m_bOmniDirectional?1:0;
            label3: {
                label2: {
                    if(i4 == 0)
                    {
                        break label2;
                    }
                    aia.graph.common.NodeInfo a4 = a.getStart();
                    int i5 = a4.getKey();
                    aia.graph.common.NodeInfo a5 = this.m_end;
                    int i6 = a5.getKey();
                    if(i5 != i6)
                    {
                        break label2;
                    }
                    aia.graph.common.NodeInfo a6 = a.getEnd();
                    int i7 = a6.getKey();
                    aia.graph.common.NodeInfo a7 = this.m_start;
                    int i8 = a7.getKey();
                    if(i7 == i8)
                    {
                        break label3;
                    }
                }
                i = 0;
                break label1;
            }
            i = 1;
        }
        return i != 0;
    }
    
    public void setEnd(aia.graph.common.NodeInfo a)
    {
        this.m_end = a;
    }
    
    public void setHighlighted(boolean b)
    {
        this.m_bHighlighted = b;
    }
    
    public void setLinkColor(java.awt.Color a)
    {
        this.m_linkColor = a;
    }
    
    public void setOmniDirectional(boolean b)
    {
        this.m_bOmniDirectional = b;
    }
    
    public void setStart(aia.graph.common.NodeInfo a)
    {
        this.m_start = a;
    }
    
    public void setUseWeight(boolean b)
    {
        this.m_bUseWeight = b;
    }
    
    public void setWeight(int i)
    {
        this.m_weight = i;
    }
    
    public boolean useWeight()
    {
        int i = this.m_bUseWeight?1:0;
        return i != 0;
    }
}