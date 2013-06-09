package com.cim.AIA;

public class ExerciseQuestionOption {
    protected String option;
    protected String explanation;
    
    public ExerciseQuestionOption(String s)
    {
        super();
        java.util.StringTokenizer a = new java.util.StringTokenizer(s, "#");
        String[] a0 = new String[2];
        a0[0] = "";
        a0[1] = "";
        int i = 0;
        while(true)
        {
            int i0 = 0;
            int i1 = a.hasMoreTokens()?1:0;
            int i2 = i;
            label1: {
                label0: {
                    int i3 = 0;
                    int i4 = i2;
                    if(i1 == 0)
                    {
                        break label0;
                    }
                    else
                    {
                        i3 = i4;
                    }
                    int i5 = i3;
                    if(i3 < 2)
                    {
                        i0 = i5;
                        break label1;
                    }
                }
                String s0 = a0[0];
                this.option = s0;
                String s1 = a0[1];
                this.explanation = s1;
                return;
            }
            int i6 = i0;
            StringBuilder a1 = new StringBuilder();
            int i7 = i6;
            String s2 = a0[i7];
            int i8 = i7;
            StringBuilder a2 = a1.append(s2);
            int i9 = i8;
            String s3 = a.nextToken();
            int i10 = i9;
            StringBuilder a3 = a2.append(s3);
            int i11 = i10;
            String s4 = a3.toString();
            int i12 = i11;
            a0[i0] = s4;
            int i13 = i12;
            int i14 = i13 + 1;
            int i15 = i13;
            if(i14 >= 2)
            {
                i = i15;
            }
            else
            {
                int i16 = i13 + 1;
                i = i16;
            }
        }
    }
    
    public ExerciseQuestionOption(String s, String s0)
    {
        super();
        this.option = s;
        this.explanation = s0;
    }
    
    public String getExplanation()
    {
        String s = this.explanation;
        return s;
    }
    
    public String getOption()
    {
        String s = this.option;
        return s;
    }
}