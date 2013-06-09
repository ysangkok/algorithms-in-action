public class AlignmentMatrix3D implements com.cim.AIA.Drawable {
    private java.awt.Color HIGHLIGHT_COLOR;
    private java.awt.Color IRRELEVANT_COLOR;
    private java.awt.Color NOTHING_COLOR;
    private java.awt.Color DARKENED_COLOR;
    private java.awt.Color DARKENIRRELEVANT_COLOR;
    private java.awt.Color DISABLE_COLOR;
    private Polygon3D[][][] theMatrix;
    private java.awt.Color[][][] layerColor;
    private java.awt.Color[][][] fillColor;
    private World3D theWorld;
    final private double DEFAULT_SIZE;
    final private double DEFAULT_GAP;
    final private double DEFAULT_ZGAP;
    final private double DEFAULT_X;
    final private double DEFAULT_Y;
    final private double DEFAULT_Z;
    private int height;
    private int width;
    private int xMove;
    private int yMove;
    private boolean isVisible;
    int[] arrayX;
    int[] arrayY;
    java.awt.Polygon[] prebuiltPolygons;
    
    public AlignmentMatrix3D()
    {
        super();
        java.awt.Color a = java.awt.Color.red;
        this.HIGHLIGHT_COLOR = a;
        java.awt.Color a0 = java.awt.Color.lightGray;
        this.IRRELEVANT_COLOR = a0;
        java.awt.Color a1 = new java.awt.Color(240, 240, 240);
        this.NOTHING_COLOR = a1;
        java.awt.Color a2 = new java.awt.Color(79, 147, 205);
        this.DARKENED_COLOR = a2;
        java.awt.Color a3 = new java.awt.Color(120, 200, 255);
        this.DARKENIRRELEVANT_COLOR = a3;
        java.awt.Color a4 = java.awt.Color.gray;
        this.DISABLE_COLOR = a4;
        Polygon3D[][][] a5 = new Polygon3D[3][3][3];
        this.theMatrix = a5;
        java.awt.Color[][][] a6 = new java.awt.Color[3][3][3];
        this.layerColor = a6;
        java.awt.Color[][][] a7 = new java.awt.Color[3][3][3];
        this.fillColor = a7;
        World3D a8 = new World3D();
        this.theWorld = a8;
        this.DEFAULT_SIZE = 20.0;
        this.DEFAULT_GAP = 20.0;
        this.DEFAULT_ZGAP = 20.0;
        this.DEFAULT_X = -50.0;
        this.DEFAULT_Y = -50.0;
        this.DEFAULT_Z = -20.0;
        this.isVisible = false;
        int[] a9 = new int[108];
        a9[0] = 88;
        a9[1] = 88;
        a9[2] = 119;
        a9[3] = 119;
        a9[4] = 150;
        a9[5] = 150;
        a9[6] = 181;
        a9[7] = 181;
        a9[8] = 212;
        a9[9] = 212;
        a9[10] = 243;
        a9[11] = 243;
        a9[12] = 88;
        a9[13] = 88;
        a9[14] = 119;
        a9[15] = 119;
        a9[16] = 150;
        a9[17] = 150;
        a9[18] = 181;
        a9[19] = 181;
        a9[20] = 212;
        a9[21] = 212;
        a9[22] = 243;
        a9[23] = 243;
        a9[24] = 88;
        a9[25] = 88;
        a9[26] = 119;
        a9[27] = 119;
        a9[28] = 150;
        a9[29] = 150;
        a9[30] = 181;
        a9[31] = 181;
        a9[32] = 212;
        a9[33] = 212;
        a9[34] = 243;
        a9[35] = 243;
        a9[36] = 72;
        a9[37] = 72;
        a9[38] = 105;
        a9[39] = 105;
        a9[40] = 138;
        a9[41] = 138;
        a9[42] = 171;
        a9[43] = 171;
        a9[44] = 204;
        a9[45] = 204;
        a9[46] = 237;
        a9[47] = 237;
        a9[48] = 72;
        a9[49] = 72;
        a9[50] = 105;
        a9[51] = 105;
        a9[52] = 138;
        a9[53] = 138;
        a9[54] = 171;
        a9[55] = 171;
        a9[56] = 204;
        a9[57] = 204;
        a9[58] = 237;
        a9[59] = 237;
        a9[60] = 72;
        a9[61] = 72;
        a9[62] = 105;
        a9[63] = 105;
        a9[64] = 138;
        a9[65] = 138;
        a9[66] = 171;
        a9[67] = 171;
        a9[68] = 204;
        a9[69] = 204;
        a9[70] = 237;
        a9[71] = 237;
        a9[72] = 53;
        a9[73] = 53;
        a9[74] = 89;
        a9[75] = 89;
        a9[76] = 124;
        a9[77] = 124;
        a9[78] = 160;
        a9[79] = 160;
        a9[80] = 195;
        a9[81] = 195;
        a9[82] = 231;
        a9[83] = 231;
        a9[84] = 53;
        a9[85] = 53;
        a9[86] = 89;
        a9[87] = 89;
        a9[88] = 124;
        a9[89] = 124;
        a9[90] = 160;
        a9[91] = 160;
        a9[92] = 195;
        a9[93] = 195;
        a9[94] = 231;
        a9[95] = 231;
        a9[96] = 53;
        a9[97] = 53;
        a9[98] = 89;
        a9[99] = 89;
        a9[100] = 124;
        a9[101] = 124;
        a9[102] = 160;
        a9[103] = 160;
        a9[104] = 195;
        a9[105] = 195;
        a9[106] = 231;
        a9[107] = 231;
        this.arrayX = a9;
        int[] a10 = new int[108];
        a10[0] = 418;
        a10[1] = 449;
        a10[2] = 449;
        a10[3] = 418;
        a10[4] = 418;
        a10[5] = 449;
        a10[6] = 449;
        a10[7] = 418;
        a10[8] = 418;
        a10[9] = 449;
        a10[10] = 449;
        a10[11] = 418;
        a10[12] = 479;
        a10[13] = 511;
        a10[14] = 511;
        a10[15] = 479;
        a10[16] = 479;
        a10[17] = 511;
        a10[18] = 511;
        a10[19] = 479;
        a10[20] = 479;
        a10[21] = 511;
        a10[22] = 511;
        a10[23] = 479;
        a10[24] = 542;
        a10[25] = 573;
        a10[26] = 573;
        a10[27] = 542;
        a10[28] = 542;
        a10[29] = 573;
        a10[30] = 573;
        a10[31] = 542;
        a10[32] = 542;
        a10[33] = 573;
        a10[34] = 573;
        a10[35] = 542;
        a10[36] = 413;
        a10[37] = 446;
        a10[38] = 446;
        a10[39] = 413;
        a10[40] = 413;
        a10[41] = 446;
        a10[42] = 446;
        a10[43] = 413;
        a10[44] = 413;
        a10[45] = 446;
        a10[46] = 446;
        a10[47] = 412;
        a10[48] = 478;
        a10[49] = 512;
        a10[50] = 512;
        a10[51] = 478;
        a10[52] = 478;
        a10[53] = 512;
        a10[54] = 512;
        a10[55] = 478;
        a10[56] = 478;
        a10[57] = 512;
        a10[58] = 512;
        a10[59] = 478;
        a10[60] = 545;
        a10[61] = 578;
        a10[62] = 578;
        a10[63] = 545;
        a10[64] = 545;
        a10[65] = 578;
        a10[66] = 578;
        a10[67] = 545;
        a10[68] = 545;
        a10[69] = 578;
        a10[70] = 579;
        a10[71] = 545;
        a10[72] = 407;
        a10[73] = 443;
        a10[74] = 443;
        a10[75] = 407;
        a10[76] = 407;
        a10[77] = 442;
        a10[78] = 442;
        a10[79] = 407;
        a10[80] = 407;
        a10[81] = 442;
        a10[82] = 442;
        a10[83] = 406;
        a10[84] = 477;
        a10[85] = 513;
        a10[86] = 513;
        a10[87] = 477;
        a10[88] = 477;
        a10[89] = 513;
        a10[90] = 513;
        a10[91] = 477;
        a10[92] = 477;
        a10[93] = 513;
        a10[94] = 513;
        a10[95] = 477;
        a10[96] = 548;
        a10[97] = 584;
        a10[98] = 584;
        a10[99] = 548;
        a10[100] = 549;
        a10[101] = 584;
        a10[102] = 584;
        a10[103] = 549;
        a10[104] = 549;
        a10[105] = 584;
        a10[106] = 585;
        a10[107] = 549;
        this.arrayY = a10;
        java.awt.Polygon[] a11 = new java.awt.Polygon[27];
        this.prebuiltPolygons = a11;
        World3D a12 = this.theWorld;
        a12.initialisePolyWorld();
        World3D a13 = this.theWorld;
        a13.initialiseWorldView();
        this.rotateX(180.0);
        this.rotateY(-1.0);
        this.translateX(-100.0);
        this.translateZ(-100.0);
        this.xMove = 250;
        this.yMove = 60;
        World3D a14 = this.theWorld;
        int i = this.xMove;
        int i0 = this.yMove;
        a14.setTranslation(i, i0);
        this.createArray();
        this.initialiseColor();
        this.width = 0;
        this.height = 0;
        int i1 = 0;
        while(true)
        {
            if(i1 >= 27)
            {
                return;
            }
            else
            {
                java.awt.Polygon[] a15 = this.prebuiltPolygons;
                java.awt.Polygon a16 = new java.awt.Polygon();
                a15[i1] = a16;
                java.awt.Polygon[] a17 = this.prebuiltPolygons;
                java.awt.Polygon a18 = a17[i1];
                int[] a19 = this.arrayX;
                int i2 = 4 * i1;
                int i3 = a19[i2];
                int[] a20 = this.arrayY;
                int i4 = 4 * i1;
                int i5 = a20[i4];
                a18.addPoint(i3, i5);
                java.awt.Polygon[] a21 = this.prebuiltPolygons;
                java.awt.Polygon a22 = a21[i1];
                int[] a23 = this.arrayX;
                int i6 = 4 * i1;
                int i7 = i6 + 1;
                int i8 = a23[i7];
                int[] a24 = this.arrayY;
                int i9 = 4 * i1;
                int i10 = i9 + 1;
                int i11 = a24[i10];
                a22.addPoint(i8, i11);
                java.awt.Polygon[] a25 = this.prebuiltPolygons;
                java.awt.Polygon a26 = a25[i1];
                int[] a27 = this.arrayX;
                int i12 = 4 * i1;
                int i13 = i12 + 2;
                int i14 = a27[i13];
                int[] a28 = this.arrayY;
                int i15 = 4 * i1;
                int i16 = i15 + 2;
                int i17 = a28[i16];
                a26.addPoint(i14, i17);
                java.awt.Polygon[] a29 = this.prebuiltPolygons;
                java.awt.Polygon a30 = a29[i1];
                int[] a31 = this.arrayX;
                int i18 = 4 * i1;
                int i19 = i18 + 3;
                int i20 = a31[i19];
                int[] a32 = this.arrayY;
                int i21 = 4 * i1;
                int i22 = i21 + 3;
                int i23 = a32[i22];
                a30.addPoint(i20, i23);
                int i24 = i1 + 1;
                i1 = i24;
            }
        }
    }
    
    private void createArray()
    {
        double d = -20.0;
        int i = 0;
        label0: while(true)
        {
            if(i >= 3)
            {
                return;
            }
            double d0 = -50.0;
            int i0 = 0;
            while(true)
            {
                if(i0 >= 3)
                {
                    double d1 = d + 20.0;
                    int i1 = i + 1;
                    d = d1;
                    i = i1;
                    continue label0;
                }
                double d2 = -50.0;
                int i2 = 0;
                while(true)
                {
                    if(i2 >= 3)
                    {
                        double d3 = d0 + 20.0;
                        double d4 = d3 + 20.0;
                        int i3 = i0 + 1;
                        d0 = d4;
                        i0 = i3;
                    }
                    else
                    {
                        Polygon3D[][][] a = this.theMatrix;
                        Polygon3D[][] a0 = a[i2];
                        Polygon3D[] a1 = a0[i0];
                        Polygon3D a2 = new Polygon3D();
                        a1[i] = a2;
                        Polygon3D[][][] a3 = this.theMatrix;
                        Polygon3D[][] a4 = a3[i2];
                        Polygon3D[] a5 = a4[i0];
                        Polygon3D a6 = a5[i];
                        Point3D a7 = new Point3D(d2, d0, d, 1.0);
                        a6.addPoint(a7);
                        Polygon3D[][][] a8 = this.theMatrix;
                        Polygon3D[][] a9 = a8[i2];
                        Polygon3D[] a10 = a9[i0];
                        Polygon3D a11 = a10[i];
                        double d5 = d0 + 20.0;
                        Point3D a12 = new Point3D(d2, d5, d, 1.0);
                        a11.addPoint(a12);
                        Polygon3D[][][] a13 = this.theMatrix;
                        Polygon3D[][] a14 = a13[i2];
                        Polygon3D[] a15 = a14[i0];
                        Polygon3D a16 = a15[i];
                        double d6 = d2 + 20.0;
                        double d7 = d0 + 20.0;
                        Point3D a17 = new Point3D(d6, d7, d, 1.0);
                        a16.addPoint(a17);
                        Polygon3D[][][] a18 = this.theMatrix;
                        Polygon3D[][] a19 = a18[i2];
                        Polygon3D[] a20 = a19[i0];
                        Polygon3D a21 = a20[i];
                        double d8 = d2 + 20.0;
                        Point3D a22 = new Point3D(d8, d0, d, 1.0);
                        a21.addPoint(a22);
                        double d9 = d2 + 20.0;
                        double d10 = d9 + 20.0;
                        int i4 = i2 + 1;
                        d2 = d10;
                        i2 = i4;
                    }
                }
            }
        }
    }
    
    public void darkenX(int i)
    {
        java.awt.Color[][][] a = this.layerColor;
        java.awt.Color[][] a0 = a[1];
        java.awt.Color[] a1 = a0[1];
        java.awt.Color a2 = this.DARKENED_COLOR;
        a1[i] = a2;
        java.awt.Color[][][] a3 = this.layerColor;
        java.awt.Color[][] a4 = a3[1];
        java.awt.Color[] a5 = a4[2];
        java.awt.Color a6 = this.DARKENED_COLOR;
        a5[i] = a6;
        java.awt.Color[][][] a7 = this.layerColor;
        java.awt.Color[][] a8 = a7[2];
        java.awt.Color[] a9 = a8[1];
        java.awt.Color a10 = this.DARKENED_COLOR;
        a9[i] = a10;
        java.awt.Color[][][] a11 = this.layerColor;
        java.awt.Color[][] a12 = a11[2];
        java.awt.Color[] a13 = a12[2];
        java.awt.Color a14 = this.DARKENED_COLOR;
        a13[i] = a14;
        java.awt.Color[][][] a15 = this.layerColor;
        java.awt.Color[][] a16 = a15[0];
        java.awt.Color[] a17 = a16[0];
        java.awt.Color a18 = this.DARKENIRRELEVANT_COLOR;
        a17[i] = a18;
        java.awt.Color[][][] a19 = this.layerColor;
        java.awt.Color[][] a20 = a19[1];
        java.awt.Color[] a21 = a20[0];
        java.awt.Color a22 = this.DARKENIRRELEVANT_COLOR;
        a21[i] = a22;
        java.awt.Color[][][] a23 = this.layerColor;
        java.awt.Color[][] a24 = a23[2];
        java.awt.Color[] a25 = a24[0];
        java.awt.Color a26 = this.DARKENIRRELEVANT_COLOR;
        a25[i] = a26;
        java.awt.Color[][][] a27 = this.layerColor;
        java.awt.Color[][] a28 = a27[0];
        java.awt.Color[] a29 = a28[1];
        java.awt.Color a30 = this.DARKENIRRELEVANT_COLOR;
        a29[i] = a30;
        java.awt.Color[][][] a31 = this.layerColor;
        java.awt.Color[][] a32 = a31[0];
        java.awt.Color[] a33 = a32[2];
        java.awt.Color a34 = this.DARKENIRRELEVANT_COLOR;
        a33[i] = a34;
    }
    
    public void disable(int i, int i0)
    {
        this.disable(i, i0, 0);
        this.disable(i, i0, 1);
        this.disable(i, i0, 2);
    }
    
    public void disable(int i, int i0, int i1)
    {
        java.awt.Color a = this.DISABLE_COLOR;
        this.fillColor(i, i0, i1, a);
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = this.isVisible?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = 2;
            label2: while(true)
            {
                if(i0 < 0)
                {
                    break;
                }
                int i1 = 0;
                label3: while(true)
                {
                    if(i1 >= 3)
                    {
                        int i2 = i0 + -1;
                        i0 = i2;
                        continue label2;
                    }
                    int i3 = 0;
                    while(true)
                    {
                        if(i3 >= 3)
                        {
                            int i4 = i1 + 1;
                            i1 = i4;
                            continue label3;
                        }
                        java.awt.Polygon[] a0 = this.prebuiltPolygons;
                        int i5 = 2 - i0;
                        int i6 = i5 * 9;
                        int i7 = i1 * 3;
                        int i8 = i6 + i7;
                        int i9 = i8 + i3;
                        java.awt.Polygon a1 = a0[i9];
                        java.awt.Color[][][] a2 = this.fillColor;
                        java.awt.Color[][] a3 = a2[i3];
                        java.awt.Color[] a4 = a3[i1];
                        java.awt.Color a5 = a4[i0];
                        if(a5 != null)
                        {
                            java.awt.Color[][][] a6 = this.fillColor;
                            java.awt.Color[][] a7 = a6[i3];
                            java.awt.Color[] a8 = a7[i1];
                            java.awt.Color a9 = a8[i0];
                            a.setColor(a9);
                            a.fillPolygon(a1);
                        }
                        java.awt.Color[][][] a10 = this.layerColor;
                        java.awt.Color[][] a11 = a10[i3];
                        java.awt.Color[] a12 = a11[i1];
                        java.awt.Color a13 = a12[i0];
                        a.setColor(a13);
                        a.drawPolygon(a1);
                        int i10 = i3 + 1;
                        i3 = i10;
                    }
                }
            }
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        World3D a1 = this.theWorld;
        int i = a0.x;
        int i0 = this.xMove;
        int i1 = i + i0;
        int i2 = a0.y;
        int i3 = this.yMove;
        int i4 = i2 + i3;
        a1.setTranslation(i1, i4);
        this.draw(a);
    }
    
    public void fillColor(int i, int i0, int i1, java.awt.Color a)
    {
        java.awt.Color[][][] a0 = this.fillColor;
        java.awt.Color[][] a1 = a0[i];
        java.awt.Color[] a2 = a1[i0];
        a2[i1] = a;
    }
    
    public int getHeight()
    {
        int i = this.height;
        return i;
    }
    
    public int getWidth()
    {
        int i = this.width;
        return i;
    }
    
    public void highlightA()
    {
        this.highlightX(0);
    }
    
    public void highlightB()
    {
        this.highlightX(1);
    }
    
    public void highlightC()
    {
        this.highlightX(2);
    }
    
    public void highlightX(int i)
    {
        int i0 = 0;
        while(true)
        {
            if(i0 >= 3)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                if(i1 >= 3)
                {
                    int i2 = i0 + 1;
                    i0 = i2;
                }
                else
                {
                    java.awt.Color[][][] a = this.layerColor;
                    java.awt.Color[][] a0 = a[i1];
                    java.awt.Color[] a1 = a0[i0];
                    java.awt.Color a2 = this.HIGHLIGHT_COLOR;
                    a1[i] = a2;
                    int i3 = i1 + 1;
                    i1 = i3;
                }
            }
        }
    }
    
    public void highlightX(int i, int i0)
    {
        int i1 = 0;
        while(true)
        {
            if(i1 >= 3)
            {
                return;
            }
            else
            {
                java.awt.Color[][][] a = this.layerColor;
                java.awt.Color[][] a0 = a[i];
                java.awt.Color[] a1 = a0[i0];
                java.awt.Color a2 = this.HIGHLIGHT_COLOR;
                a1[i1] = a2;
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
    }
    
    public void highlightX(int i, int i0, int i1)
    {
        java.awt.Color[][][] a = this.layerColor;
        java.awt.Color[][] a0 = a[i];
        java.awt.Color[] a1 = a0[i0];
        java.awt.Color a2 = this.HIGHLIGHT_COLOR;
        a1[i1] = a2;
    }
    
    public void initialiseColor()
    {
        int i = 0;
        label0: while(true)
        {
            if(i >= 3)
            {
                break;
            }
            int i0 = 0;
            while(true)
            {
                if(i0 >= 3)
                {
                    int i1 = i + 1;
                    i = i1;
                    continue label0;
                }
                int i2 = 0;
                while(true)
                {
                    if(i2 >= 3)
                    {
                        int i3 = i0 + 1;
                        i0 = i3;
                    }
                    else
                    {
                        java.awt.Color[][][] a = this.layerColor;
                        java.awt.Color[][] a0 = a[i2];
                        java.awt.Color[] a1 = a0[i0];
                        java.awt.Color a2 = this.IRRELEVANT_COLOR;
                        a1[i] = a2;
                        java.awt.Color[][][] a3 = this.fillColor;
                        java.awt.Color[][] a4 = a3[i2];
                        java.awt.Color[] a5 = a4[i0];
                        java.awt.Color a6 = java.awt.Color.white;
                        a5[i] = a6;
                        int i4 = i2 + 1;
                        i2 = i4;
                    }
                }
            }
        }
        int i5 = 0;
        while(true)
        {
            if(i5 >= 3)
            {
                return;
            }
            else
            {
                java.awt.Color[][][] a7 = this.layerColor;
                java.awt.Color[][] a8 = a7[0];
                java.awt.Color[] a9 = a8[0];
                java.awt.Color a10 = this.NOTHING_COLOR;
                a9[i5] = a10;
                java.awt.Color[][][] a11 = this.layerColor;
                java.awt.Color[][] a12 = a11[0];
                java.awt.Color[] a13 = a12[1];
                java.awt.Color a14 = this.NOTHING_COLOR;
                a13[i5] = a14;
                java.awt.Color[][][] a15 = this.layerColor;
                java.awt.Color[][] a16 = a15[0];
                java.awt.Color[] a17 = a16[2];
                java.awt.Color a18 = this.NOTHING_COLOR;
                a17[i5] = a18;
                java.awt.Color[][][] a19 = this.layerColor;
                java.awt.Color[][] a20 = a19[1];
                java.awt.Color[] a21 = a20[0];
                java.awt.Color a22 = this.NOTHING_COLOR;
                a21[i5] = a22;
                java.awt.Color[][][] a23 = this.layerColor;
                java.awt.Color[][] a24 = a23[2];
                java.awt.Color[] a25 = a24[0];
                java.awt.Color a26 = this.NOTHING_COLOR;
                a25[i5] = a26;
                int i6 = i5 + 1;
                i5 = i6;
            }
        }
    }
    
    public void irrelevantA()
    {
        this.irrelevantX(0);
    }
    
    public void irrelevantAll()
    {
        this.irrelevantX(0);
        this.irrelevantX(1);
        this.irrelevantX(2);
    }
    
    public void irrelevantB()
    {
        this.irrelevantX(1);
    }
    
    public void irrelevantC()
    {
        this.irrelevantX(2);
    }
    
    public void irrelevantX(int i)
    {
        java.awt.Color[][][] a = this.layerColor;
        java.awt.Color[][] a0 = a[0];
        java.awt.Color[] a1 = a0[0];
        java.awt.Color a2 = this.NOTHING_COLOR;
        a1[i] = a2;
        java.awt.Color[][][] a3 = this.layerColor;
        java.awt.Color[][] a4 = a3[0];
        java.awt.Color[] a5 = a4[1];
        java.awt.Color a6 = this.NOTHING_COLOR;
        a5[i] = a6;
        java.awt.Color[][][] a7 = this.layerColor;
        java.awt.Color[][] a8 = a7[0];
        java.awt.Color[] a9 = a8[2];
        java.awt.Color a10 = this.NOTHING_COLOR;
        a9[i] = a10;
        java.awt.Color[][][] a11 = this.layerColor;
        java.awt.Color[][] a12 = a11[1];
        java.awt.Color[] a13 = a12[0];
        java.awt.Color a14 = this.NOTHING_COLOR;
        a13[i] = a14;
        java.awt.Color[][][] a15 = this.layerColor;
        java.awt.Color[][] a16 = a15[2];
        java.awt.Color[] a17 = a16[0];
        java.awt.Color a18 = this.NOTHING_COLOR;
        a17[i] = a18;
    }
    
    public void moveX(int i)
    {
        this.xMove = i;
        World3D a = this.theWorld;
        int i0 = this.xMove;
        int i1 = this.yMove;
        a.setTranslation(i0, i1);
    }
    
    public void rotateX(double d)
    {
        World3D a = this.theWorld;
        double d0 = 0.017453292519943295 * d;
        a.rotatePolyWorldX(d0);
    }
    
    public void rotateY(double d)
    {
        World3D a = this.theWorld;
        double d0 = 0.017453292519943295 * d;
        a.rotatePolyWorldY(d0);
    }
    
    public void rotateZ(double d)
    {
        World3D a = this.theWorld;
        double d0 = 0.017453292519943295 * d;
        a.rotatePolyWorldZ(d0);
    }
    
    public void setColor(int i, int i0, int i1, java.awt.Color a)
    {
        java.awt.Color[][][] a0 = this.layerColor;
        java.awt.Color[][] a1 = a0[i];
        java.awt.Color[] a2 = a1[i0];
        a2[i1] = a;
    }
    
    public void setLocation(int i, int i0)
    {
        World3D a = this.theWorld;
        int i1 = this.xMove;
        int i2 = i + i1;
        int i3 = this.yMove;
        int i4 = i0 + i3;
        a.setTranslation(i2, i4);
    }
    
    public void setVisible(boolean b)
    {
        this.isVisible = b;
    }
    
    public void translateX(double d)
    {
        World3D a = this.theWorld;
        a.translatePolyWorldX(d);
    }
    
    public void translateY(double d)
    {
        World3D a = this.theWorld;
        a.translatePolyWorldY(d);
    }
    
    public void translateZ(double d)
    {
        World3D a = this.theWorld;
        a.translatePolyWorldZ(d);
    }
    
    public void unDarkenX(int i)
    {
        java.awt.Color[][][] a = this.layerColor;
        java.awt.Color[][] a0 = a[1];
        java.awt.Color[] a1 = a0[1];
        java.awt.Color a2 = this.IRRELEVANT_COLOR;
        a1[i] = a2;
        java.awt.Color[][][] a3 = this.layerColor;
        java.awt.Color[][] a4 = a3[1];
        java.awt.Color[] a5 = a4[2];
        java.awt.Color a6 = this.IRRELEVANT_COLOR;
        a5[i] = a6;
        java.awt.Color[][][] a7 = this.layerColor;
        java.awt.Color[][] a8 = a7[2];
        java.awt.Color[] a9 = a8[1];
        java.awt.Color a10 = this.IRRELEVANT_COLOR;
        a9[i] = a10;
        java.awt.Color[][][] a11 = this.layerColor;
        java.awt.Color[][] a12 = a11[2];
        java.awt.Color[] a13 = a12[2];
        java.awt.Color a14 = this.IRRELEVANT_COLOR;
        a13[i] = a14;
        java.awt.Color[][][] a15 = this.layerColor;
        java.awt.Color[][] a16 = a15[0];
        java.awt.Color[] a17 = a16[0];
        java.awt.Color a18 = this.NOTHING_COLOR;
        a17[i] = a18;
        java.awt.Color[][][] a19 = this.layerColor;
        java.awt.Color[][] a20 = a19[1];
        java.awt.Color[] a21 = a20[0];
        java.awt.Color a22 = this.NOTHING_COLOR;
        a21[i] = a22;
        java.awt.Color[][][] a23 = this.layerColor;
        java.awt.Color[][] a24 = a23[2];
        java.awt.Color[] a25 = a24[0];
        java.awt.Color a26 = this.NOTHING_COLOR;
        a25[i] = a26;
        java.awt.Color[][][] a27 = this.layerColor;
        java.awt.Color[][] a28 = a27[0];
        java.awt.Color[] a29 = a28[1];
        java.awt.Color a30 = this.NOTHING_COLOR;
        a29[i] = a30;
        java.awt.Color[][][] a31 = this.layerColor;
        java.awt.Color[][] a32 = a31[0];
        java.awt.Color[] a33 = a32[2];
        java.awt.Color a34 = this.NOTHING_COLOR;
        a33[i] = a34;
    }
    
    public void unHighlightX(int i)
    {
        int i0 = 0;
        while(true)
        {
            if(i0 >= 3)
            {
                return;
            }
            int i1 = 0;
            while(true)
            {
                if(i1 >= 3)
                {
                    int i2 = i0 + 1;
                    i0 = i2;
                }
                else
                {
                    java.awt.Color[][][] a = this.layerColor;
                    java.awt.Color[][] a0 = a[i1];
                    java.awt.Color[] a1 = a0[i0];
                    java.awt.Color a2 = this.IRRELEVANT_COLOR;
                    a1[i] = a2;
                    int i3 = i1 + 1;
                    i1 = i3;
                }
            }
        }
    }
    
    public void unHighlightX(int i, int i0)
    {
        int i1 = 0;
        while(true)
        {
            if(i1 >= 3)
            {
                return;
            }
            else
            {
                java.awt.Color[][][] a = this.layerColor;
                java.awt.Color[][] a0 = a[i];
                java.awt.Color[] a1 = a0[i0];
                java.awt.Color a2 = this.IRRELEVANT_COLOR;
                a1[i1] = a2;
                int i2 = i1 + 1;
                i1 = i2;
            }
        }
    }
    
    public void unHighlightX(int i, int i0, int i1)
    {
        java.awt.Color[][][] a = this.layerColor;
        java.awt.Color[][] a0 = a[i];
        java.awt.Color[] a1 = a0[i0];
        java.awt.Color a2 = this.IRRELEVANT_COLOR;
        a1[i1] = a2;
    }
}