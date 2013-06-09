package com.cim.AIA;

public class SwapRequest {
    protected int firstId;
    protected int secondId;
    
    public SwapRequest(int i, int i0)
    {
        super();
        this.firstId = i;
        this.secondId = i0;
    }
    
    public int getFirstId()
    {
        int i = this.firstId;
        return i;
    }
    
    public int getSecondId()
    {
        int i = this.secondId;
        return i;
    }
}