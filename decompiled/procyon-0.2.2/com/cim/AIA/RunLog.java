package com.cim.AIA;

public class RunLog extends Log
{
    public static final int PAUSED = 1;
    public static final int COMPLETED = 2;
    public static final String[] exitstrings;
    public int exitmode;
    public int nsteps;
    public int hiddenlines;
    public int ncycles;
    private int startSpeed;
    private int endSpeed;
    
    private static String exitToString(final int exitmode) {
        return RunLog.exitstrings[exitmode];
    }
    
    private static int stringToExit(final String sexit) {
        for (int i = 0; i < RunLog.exitstrings.length; ++i) {
            if (sexit.equalsIgnoreCase(sexit)) {
                return i;
            }
        }
        return 0;
    }
    
    public RunLog(final byte tclass, final byte event, final String lineno) {
        super(tclass, event, lineno);
        this.startSpeed = -1;
        this.endSpeed = -1;
    }
    
    public int getEndSpeed() {
        return this.endSpeed;
    }
    
    public int getExitMode() {
        return this.exitmode;
    }
    
    public int getHiddenLines() {
        return this.hiddenlines;
    }
    
    public int getNCycles() {
        return this.ncycles;
    }
    
    public int getNSteps() {
        return this.nsteps;
    }
    
    public int getStartSpeed() {
        return this.startSpeed;
    }
    
    public void incrHiddenLines(final int incr) {
        this.hiddenlines = this.hiddenlines + incr;
    }
    
    public void incrSteps() {
        this.nsteps = this.nsteps + 1;
    }
    
    public void setEndSpeed(final int endSpeed) {
        this.endSpeed = endSpeed;
    }
    
    public void setExitMode(final int exitmode) {
        this.exitmode = exitmode;
    }
    
    public void setHiddenLines(final int hl) {
        this.hiddenlines = hl;
    }
    
    public void setNCycles(final int ncycles) {
        this.ncycles = ncycles;
    }
    
    public void setNSteps(final int nsteps) {
        this.nsteps = nsteps;
    }
    
    public void setParam(final String param, final String val) {
        if (!param.equalsIgnoreCase("averagehidden")) {
            if (param.equalsIgnoreCase("exitmode")) {
                this.exitmode = stringToExit(val);
            }
            else {
                if (param.equalsIgnoreCase("ncycles")) {
                    try {
                        this.ncycles = Integer.parseInt(val);
                        return;
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (param.equalsIgnoreCase("steps")) {
                    try {
                        this.nsteps = Integer.parseInt(val);
                        return;
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (param.equalsIgnoreCase("hiddenlines")) {
                    try {
                        this.hiddenlines = Integer.parseInt(val);
                        return;
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                super.setParam(param, val);
            }
        }
    }
    
    public void setStartSpeed(final int startSpeed) {
        this.startSpeed = startSpeed;
    }
    
    public void xmlBody(final StringBuffer sb) {
        super.xmlBody(sb);
        final float avhidden = (this.nsteps == 0) ? 1.0f : ((float)this.hiddenlines / (float)this.nsteps);
        sb.append("<param name="averagehidden" value="" + avhidden + ""/>\n");
        sb.append("<param name="exitmode" value="" + exitToString(this.exitmode) + ""/>\n");
        sb.append("<param name="ncycles" value="" + this.ncycles + ""/>\n");
        sb.append("<param name="steps" value="" + this.nsteps + ""/>\n");
        sb.append("<param name="hiddenlines" value="" + this.hiddenlines + ""/>\n");
        sb.append("<param name="startspeed" value="" + this.startSpeed + ""/>\n");
        sb.append("<param name="endspeed" value="" + this.endSpeed + ""/>\n");
    }
    
    static {
        exitstrings = new String[] { "Unrecorded", "Paused", "Completed" };
    }
}
