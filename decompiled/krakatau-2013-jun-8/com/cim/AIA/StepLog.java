package com.cim.AIA;

public class StepLog extends com.cim.AIA.Log {
    public int nHiddenLines;
    
    public StepLog(byte a, byte a0, String s, int i)
    {
        super(a, a0, s);
        this.nHiddenLines = i;
    }
    
    public StepLog(byte a, byte a0, String s, int i, String s0)
    {
        super(a, a0, s, s0);
        this.nHiddenLines = i;
    }
    
    public int getHiddenLines()
    {
        int i = this.nHiddenLines;
        return i;
    }
    
    public void setParam(String s, String s0)
    {
        int i = s.equalsIgnoreCase("nhiddenlines")?1:0;
        label1: {
            NumberFormatException a = null;
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                ((com.cim.AIA.Log)this).setParam(s, s0);
                break label1;
            }
            label3: {
                int i0 = 0;
                label2: try
                {
                    i0 = Integer.parseInt(s0);
                    break label2;
                }
                catch(NumberFormatException a0)
                {
                    a = a0;
                    break label3;
                }
                label4: try
                {
                    this.nHiddenLines = i0;
                    break label4;
                }
                catch(NumberFormatException a1)
                {
                    a = a1;
                    break label3;
                }
                break label1;
            }
            a.printStackTrace();
        }
    }
    
    public void xmlBody(StringBuffer a)
    {
        ((com.cim.AIA.Log)this).xmlBody(a);
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("<param name=\"nhiddenlines\" value=\"");
        int i = this.nHiddenLines;
        StringBuilder a2 = a1.append(i);
        StringBuilder a3 = a2.append("\"/>\n");
        String s = a3.toString();
        StringBuffer a4 = a.append(s);
    }
}