package com.cim.AIA;

public interface ModeListener
{
    void modeNormal(final ModeEvent p0);
    
    void modeOther(final ModeEvent p0);
    
    void modeQuiz(final ModeEvent p0);
    
    void modeSelfTest(final ModeEvent p0);
}
