package com.cim.AIA;

public class StepLog extends Log
{
    public int nHiddenLines;
    
    public StepLog(final byte tclass, final byte event, final String lineno, final int nHiddenLines) {
        super(tclass, event, lineno);
        this.nHiddenLines = nHiddenLines;
    }
    
    public StepLog(final byte tclass, final byte event, final String lineno, final int nHiddenLines, final String extra) {
        super(tclass, event, lineno, extra);
        this.nHiddenLines = nHiddenLines;
    }
    
    public int getHiddenLines() {
        return this.nHiddenLines;
    }
    
    public void setParam(final String param, final String val) {
        if (param.equalsIgnoreCase("nhiddenlines")) {
            try {
                this.nHiddenLines = Integer.parseInt(val);
                return;
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
        }
        super.setParam(param, val);
    }
    
    public void xmlBody(final StringBuffer sb) {
        super.xmlBody(sb);
        sb.append("<param name="nhiddenlines" value="" + this.nHiddenLines + ""/>\n");
    }
}
