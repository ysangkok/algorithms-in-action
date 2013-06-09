package com.cim.AIA;

public class RunLog extends com.cim.AIA.Log {
    final public static int PAUSED = 1;
    final public static int COMPLETED = 2;
    final public static String[] exitstrings;
    public int exitmode;
    public int nsteps;
    public int hiddenlines;
    public int ncycles;
    private int startSpeed;
    private int endSpeed;
    
    private static String exitToString(int i)
    {
        String[] a = com.cim.AIA.RunLog.exitstrings;
        String s = a[i];
        return s;
    }
    
    private static int stringToExit(String s)
    {
        int i = 0;
        while(true)
        {
            int i0 = 0;
            String[] a = com.cim.AIA.RunLog.exitstrings;
            int i1 = a.length;
            label1: {
                label0: {
                    if(i < i1)
                    {
                        break label0;
                    }
                    i0 = 0;
                    break label1;
                }
                int i2 = s.equalsIgnoreCase(s)?1:0;
                if(i2 == 0)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue;
                }
                i0 = i;
            }
            return i0;
        }
    }
    
    public RunLog(byte a, byte a0, String s)
    {
        super(a, a0, s);
        this.startSpeed = -1;
        this.endSpeed = -1;
    }
    
    public int getEndSpeed()
    {
        int i = this.endSpeed;
        return i;
    }
    
    public int getExitMode()
    {
        int i = this.exitmode;
        return i;
    }
    
    public int getHiddenLines()
    {
        int i = this.hiddenlines;
        return i;
    }
    
    public int getNCycles()
    {
        int i = this.ncycles;
        return i;
    }
    
    public int getNSteps()
    {
        int i = this.nsteps;
        return i;
    }
    
    public int getStartSpeed()
    {
        int i = this.startSpeed;
        return i;
    }
    
    public void incrHiddenLines(int i)
    {
        int i0 = this.hiddenlines;
        int i1 = i0 + i;
        this.hiddenlines = i1;
    }
    
    public void incrSteps()
    {
        int i = this.nsteps;
        int i0 = i + 1;
        this.nsteps = i0;
    }
    
    public void setEndSpeed(int i)
    {
        this.endSpeed = i;
    }
    
    public void setExitMode(int i)
    {
        this.exitmode = i;
    }
    
    public void setHiddenLines(int i)
    {
        this.hiddenlines = i;
    }
    
    public void setNCycles(int i)
    {
        this.ncycles = i;
    }
    
    public void setNSteps(int i)
    {
        this.nsteps = i;
    }
    
    public void setParam(String s, String s0)
    {
        int i = s.equalsIgnoreCase("averagehidden")?1:0;
        label1: {
            NumberFormatException a = null;
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = s.equalsIgnoreCase("exitmode")?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                int i1 = com.cim.AIA.RunLog.stringToExit(s0);
                this.exitmode = i1;
                break label1;
            }
            int i2 = s.equalsIgnoreCase("ncycles")?1:0;
            label3: {
                NumberFormatException a0 = null;
                if(i2 == 0)
                {
                    break label3;
                }
                label5: {
                    int i3 = 0;
                    label4: try
                    {
                        i3 = Integer.parseInt(s0);
                        break label4;
                    }
                    catch(NumberFormatException a1)
                    {
                        a0 = a1;
                        break label5;
                    }
                    label6: try
                    {
                        this.ncycles = i3;
                        break label6;
                    }
                    catch(NumberFormatException a2)
                    {
                        a0 = a2;
                        break label5;
                    }
                    break label1;
                }
                a0.printStackTrace();
                break label1;
            }
            int i4 = s.equalsIgnoreCase("steps")?1:0;
            label7: {
                NumberFormatException a3 = null;
                if(i4 == 0)
                {
                    break label7;
                }
                label9: {
                    int i5 = 0;
                    label8: try
                    {
                        i5 = Integer.parseInt(s0);
                        break label8;
                    }
                    catch(NumberFormatException a4)
                    {
                        a3 = a4;
                        break label9;
                    }
                    label10: try
                    {
                        this.nsteps = i5;
                        break label10;
                    }
                    catch(NumberFormatException a5)
                    {
                        a3 = a5;
                        break label9;
                    }
                    break label1;
                }
                a3.printStackTrace();
                break label1;
            }
            int i6 = s.equalsIgnoreCase("hiddenlines")?1:0;
            label11: {
                if(i6 != 0)
                {
                    break label11;
                }
                ((com.cim.AIA.Log)this).setParam(s, s0);
                break label1;
            }
            label13: {
                int i7 = 0;
                label12: try
                {
                    i7 = Integer.parseInt(s0);
                    break label12;
                }
                catch(NumberFormatException a6)
                {
                    a = a6;
                    break label13;
                }
                label14: try
                {
                    this.hiddenlines = i7;
                    break label14;
                }
                catch(NumberFormatException a7)
                {
                    a = a7;
                    break label13;
                }
                break label1;
            }
            a.printStackTrace();
        }
    }
    
    public void setStartSpeed(int i)
    {
        this.startSpeed = i;
    }
    
    public void xmlBody(StringBuffer a)
    {
        float f = 0.0f;
        ((com.cim.AIA.Log)this).xmlBody(a);
        int i = this.nsteps;
        if(i == 0)
        {
            f = 1.0f;
        }
        else
        {
            int i0 = this.hiddenlines;
            float f0 = (float)i0;
            int i1 = this.nsteps;
            float f1 = (float)i1;
            float f2 = f0 / f1;
            f = f2;
        }
        StringBuilder a0 = new StringBuilder();
        StringBuilder a1 = a0.append("<param name=\"averagehidden\" value=\"");
        StringBuilder a2 = a1.append(f);
        StringBuilder a3 = a2.append("\"/>\n");
        String s = a3.toString();
        StringBuffer a4 = a.append(s);
        StringBuilder a5 = new StringBuilder();
        StringBuilder a6 = a5.append("<param name=\"exitmode\" value=\"");
        int i2 = this.exitmode;
        String s0 = com.cim.AIA.RunLog.exitToString(i2);
        StringBuilder a7 = a6.append(s0);
        StringBuilder a8 = a7.append("\"/>\n");
        String s1 = a8.toString();
        StringBuffer a9 = a.append(s1);
        StringBuilder a10 = new StringBuilder();
        StringBuilder a11 = a10.append("<param name=\"ncycles\" value=\"");
        int i3 = this.ncycles;
        StringBuilder a12 = a11.append(i3);
        StringBuilder a13 = a12.append("\"/>\n");
        String s2 = a13.toString();
        StringBuffer a14 = a.append(s2);
        StringBuilder a15 = new StringBuilder();
        StringBuilder a16 = a15.append("<param name=\"steps\" value=\"");
        int i4 = this.nsteps;
        StringBuilder a17 = a16.append(i4);
        StringBuilder a18 = a17.append("\"/>\n");
        String s3 = a18.toString();
        StringBuffer a19 = a.append(s3);
        StringBuilder a20 = new StringBuilder();
        StringBuilder a21 = a20.append("<param name=\"hiddenlines\" value=\"");
        int i5 = this.hiddenlines;
        StringBuilder a22 = a21.append(i5);
        StringBuilder a23 = a22.append("\"/>\n");
        String s4 = a23.toString();
        StringBuffer a24 = a.append(s4);
        StringBuilder a25 = new StringBuilder();
        StringBuilder a26 = a25.append("<param name=\"startspeed\" value=\"");
        int i6 = this.startSpeed;
        StringBuilder a27 = a26.append(i6);
        StringBuilder a28 = a27.append("\"/>\n");
        String s5 = a28.toString();
        StringBuffer a29 = a.append(s5);
        StringBuilder a30 = new StringBuilder();
        StringBuilder a31 = a30.append("<param name=\"endspeed\" value=\"");
        int i7 = this.endSpeed;
        StringBuilder a32 = a31.append(i7);
        StringBuilder a33 = a32.append("\"/>\n");
        String s6 = a33.toString();
        StringBuffer a34 = a.append(s6);
    }
    
    static
    {
        String[] a = new String[3];
        a[0] = "Unrecorded";
        a[1] = "Paused";
        a[2] = "Completed";
        com.cim.AIA.RunLog.exitstrings = a;
    }
}