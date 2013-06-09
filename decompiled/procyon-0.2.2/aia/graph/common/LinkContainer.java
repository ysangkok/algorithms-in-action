package aia.graph.common;

import java.util.*;

public class LinkContainer
{
    Vector<Link> m_vecLinks;
    
    public LinkContainer() {
        super();
        this.m_vecLinks = new Vector();
    }
    
    public Link addLink(final NodeInfo p_start, final NodeInfo p_end, final boolean p_omniDirectional, final int p_weight) {
        Link link = null;
        link = this.findLink(p_start, p_end);
        if (link == null) {
            link = this.findLink(p_end, p_start);
            if (link != null && p_omniDirectional) {
                link.setOmniDirectional(true);
            }
            else {
                link = new Link(p_start, p_end, false);
                link.setWeight(p_weight);
                link.setOmniDirectional(p_omniDirectional);
                this.m_vecLinks.addElement(link);
            }
        }
        return link;
    }
    
    public Link findLink(final NodeInfo p_start, final NodeInfo p_end) {
        if (this.m_vecLinks.size() == 0) {
            return null;
        }
        for (int i = 0; i < this.m_vecLinks.size(); ++i) {
            final Link link = (Link)this.m_vecLinks.elementAt(i);
            if (link.getStart().getKey() == p_start.getKey() && link.getEnd().getKey() == p_end.getKey()) {
                return link;
            }
            if (link.getOmniDirectional() && link.getStart().getKey() == p_end.getKey() && link.getEnd().getKey() == p_start.getKey()) {
                return link;
            }
        }
        return null;
    }
    
    public Vector<Link> getLinks() {
        return this.m_vecLinks;
    }
    
    public int size() {
        return this.m_vecLinks.size();
    }
}
