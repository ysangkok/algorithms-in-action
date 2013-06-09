package com.cim.AIA;

public class SwapRequest
{
    protected int firstId;
    protected int secondId;
    
    public SwapRequest(final int firstId, final int secondId) {
        super();
        this.firstId = firstId;
        this.secondId = secondId;
    }
    
    public int getFirstId() {
        return this.firstId;
    }
    
    public int getSecondId() {
        return this.secondId;
    }
}
