package aia.graph.common;

import java.awt.*;

public class Link
{
    protected boolean m_bOmniDirectional;
    protected NodeInfo m_start;
    protected NodeInfo m_end;
    protected boolean m_bHighlighted;
    protected Color m_linkColor;
    protected int m_weight;
    protected boolean m_bUseWeight;
    
    public Link(final NodeInfo p_start, final NodeInfo p_end, final boolean p_omniDirectional) {
        super();
        this.m_start = p_start;
        this.m_end = p_end;
        this.m_bOmniDirectional = p_omniDirectional;
        this.m_bHighlighted = false;
        this.m_linkColor = Color.black;
        this.m_bUseWeight = true;
    }
    
    public NodeInfo getEnd() {
        return this.m_end;
    }
    
    public boolean getHighlighted() {
        return this.m_bHighlighted;
    }
    
    public Color getLinkColor() {
        return this.m_linkColor;
    }
    
    public boolean getOmniDirectional() {
        return this.m_bOmniDirectional;
    }
    
    public NodeInfo getStart() {
        return this.m_start;
    }
    
    public int getWeight() {
        return this.m_weight;
    }
    
    public boolean isEqualTo(final Link p_link) {
        return (p_link.getStart().getKey() == this.m_start.getKey() && p_link.getEnd().getKey() == this.m_end.getKey()) || (this.m_bOmniDirectional && p_link.getStart().getKey() == this.m_end.getKey() && p_link.getEnd().getKey() == this.m_start.getKey());
    }
    
    public void setEnd(final NodeInfo p_end) {
        this.m_end = p_end;
    }
    
    public void setHighlighted(final boolean p_highlighted) {
        this.m_bHighlighted = p_highlighted;
    }
    
    public void setLinkColor(final Color p_linkColor) {
        this.m_linkColor = p_linkColor;
    }
    
    public void setOmniDirectional(final boolean p_omnidirectional) {
        this.m_bOmniDirectional = p_omnidirectional;
    }
    
    public void setStart(final NodeInfo p_start) {
        this.m_start = p_start;
    }
    
    public void setUseWeight(final boolean p_useWeight) {
        this.m_bUseWeight = p_useWeight;
    }
    
    public void setWeight(final int p_weight) {
        this.m_weight = p_weight;
    }
    
    public boolean useWeight() {
        return this.m_bUseWeight;
    }
}
