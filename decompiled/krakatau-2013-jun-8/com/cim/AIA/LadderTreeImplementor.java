package com.cim.AIA;

public class LadderTreeImplementor {
    protected int rowCount;
    protected boolean isInitialised;
    protected int maxX;
    protected int maxY;
    protected int xBuffer;
    protected int yBuffer;
    
    public LadderTreeImplementor()
    {
        super();
        this.rowCount = 0;
        this.isInitialised = false;
        this.maxX = 0;
        this.maxY = 0;
    }
    
    public int getMaxX()
    {
        int i = this.maxX;
        int i0 = this.xBuffer;
        int i1 = i + i0;
        return i1;
    }
    
    public int getMaxY()
    {
        int i = this.maxY;
        int i0 = this.yBuffer;
        int i1 = i + i0;
        return i1;
    }
    
    public synchronized void layout(com.cim.AIA.LadderTree a, com.cim.AIA.CodeCanvas a0)
    {
        this.layout(a, a0, 0);
    }
    
    public synchronized void layout(com.cim.AIA.LadderTree a, com.cim.AIA.CodeCanvas a0, int i)
    {
        int i0 = a.getDepth();
        int i1 = i0 - i;
        if(i1 <= 0)
        {
            this.isInitialised = false;
        }
        int i2 = this.isInitialised?1:0;
        if(i2 == 0)
        {
            this.rowCount = 0;
            this.isInitialised = true;
            int i3 = a0.getXBuffer();
            this.xBuffer = i3;
            int i4 = a0.getYBuffer();
            this.yBuffer = i4;
            this.maxX = 0;
            this.maxY = 0;
        }
        com.cim.AIA.AlgorithmLine a1 = a.getAlgorithmLine();
        int i5 = a1.getIsLabelMinMax()?1:0;
        if(i5 == 0)
        {
            int i6 = this.rowCount;
            int i7 = i6 + 1;
            this.rowCount = i7;
        }
        int i8 = a.getDepth();
        int i9 = i8 - i;
        int i10 = this.xBuffer;
        int i11 = i9 * i10;
        int i12 = this.xBuffer;
        int i13 = i11 + i12;
        a.setXPos(i13);
        int i14 = this.rowCount;
        int i15 = this.yBuffer;
        int i16 = i14 * i15;
        a.setYPos(i16);
        com.cim.AIA.AlgorithmLine a2 = a.getAlgorithmLine();
        int i17 = a2.getIsMinMax()?1:0;
        label1: {
            label0: {
                if(i17 != 0)
                {
                    break label0;
                }
                int i18 = a.getXPos();
                com.cim.AIA.AlgorithmLine a3 = a.getAlgorithmLine();
                int i19 = a0.getWidthOf(a3);
                int i20 = i18 + i19;
                int i21 = this.maxX;
                if(i20 <= i21)
                {
                    break label1;
                }
                else
                {
                    int i22 = a.getXPos();
                    com.cim.AIA.AlgorithmLine a4 = a.getAlgorithmLine();
                    int i23 = a0.getWidthOf(a4);
                    int i24 = i22 + i23;
                    this.maxX = i24;
                    break label1;
                }
            }
            int i25 = a.getXPos();
            com.cim.AIA.AlgorithmLine a5 = a.getAlgorithmLine();
            int i26 = a0.getWidthOf(a5);
            int i27 = i25 + i26;
            com.cim.AIA.AlgorithmLine a6 = a.getAlgorithmLine();
            String s = a6.getMinMaxLabel();
            int i28 = a0.getWidthOf(s);
            int i29 = i27 + i28;
            int i30 = a0.getMinMaxLineLength();
            int i31 = i30 * 2;
            int i32 = i29 + i31;
            int i33 = a0.getMinMaxXGap();
            int i34 = i33 * 2;
            int i35 = i32 + i34;
            int i36 = this.maxX;
            if(i35 > i36)
            {
                int i37 = a.getXPos();
                com.cim.AIA.AlgorithmLine a7 = a.getAlgorithmLine();
                int i38 = a0.getWidthOf(a7);
                int i39 = i37 + i38;
                com.cim.AIA.AlgorithmLine a8 = a.getAlgorithmLine();
                String s0 = a8.getMinMaxLabel();
                int i40 = a0.getWidthOf(s0);
                int i41 = i39 + i40;
                int i42 = a0.getMinMaxLineLength();
                int i43 = i42 * 2;
                int i44 = i41 + i43;
                int i45 = a0.getMinMaxXGap();
                int i46 = i45 * 2;
                int i47 = i44 + i46;
                this.maxX = i47;
            }
        }
        int i48 = a.getYPos();
        int i49 = this.yBuffer;
        int i50 = i48 + i49;
        int i51 = this.maxY;
        if(i50 > i51)
        {
            int i52 = a.getYPos();
            int i53 = this.yBuffer;
            int i54 = i52 + i53;
            this.maxY = i54;
        }
        a.setVisible(true);
        int i55 = a.isExpanded()?1:0;
        label2: {
            if(i55 == 0)
            {
                break label2;
            }
            int i56 = 0;
            while(true)
            {
                int i57 = a.getNumberOfChildren();
                if(i56 >= i57)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a9 = a.getChild(i56);
                    com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a9;
                    com.cim.AIA.LadderTree a10 = (com.cim.AIA.LadderTree)a9;
                    this.layout(a10, a0, i);
                    int i58 = i56 + 1;
                    i56 = i58;
                }
            }
        }
    }
    
    public synchronized void paintTree(java.awt.Graphics a, com.cim.AIA.LadderTree a0, com.cim.AIA.CodeCanvas a1)
    {
        a0.display(a, a1);
    }
}