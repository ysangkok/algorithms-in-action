package com.cim.AIA;

public class Line implements com.cim.AIA.Drawable, com.cim.AIA.Moveable {
    final public static int START_OF_LINE = -1;
    final public static int MIDDLE_OF_LINE = -2;
    final public static int END_OF_LINE = -3;
    protected double x1;
    protected double x2;
    protected double y1;
    protected double y2;
    protected int dottedGap;
    protected boolean showAsThick;
    protected int lineThickness;
    protected boolean showAsDotted;
    protected boolean showArrowHead;
    protected int distanceFromStartForArrowHead;
    protected java.awt.Color rayColor;
    protected java.awt.Color textColor;
    protected String label;
    protected int distanceFromStartForLabel;
    protected int xLabelOffset;
    protected int yLabelOffset;
    protected int arrowHeadWidth;
    protected int arrowHeadLength;
    
    public static double getDegrees(double d)
    {
        double d0 = 57.29577951308232 * d;
        return d0;
    }
    
    public static com.cim.AIA.DoublePoint getIntersection(com.cim.AIA.Line a, com.cim.AIA.Line a0)
    {
        com.cim.AIA.DoublePoint a1 = null;
        double d = a.getConstant();
        double d0 = a0.getConstant();
        double d1 = d - d0;
        double d2 = a0.getSlope();
        double d3 = a.getSlope();
        double d4 = d2 - d3;
        double d5 = d1 / d4;
        int i = Double.isInfinite(d5)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                a1 = null;
                break label1;
            }
            double d6 = a0.getSlope();
            int i0 = Double.isInfinite(d6)?1:0;
            label3: {
                label2: {
                    if(i0 != 0)
                    {
                        break label2;
                    }
                    double d7 = a0.getConstant();
                    int i1 = Double.isInfinite(d7)?1:0;
                    if(i1 == 0)
                    {
                        break label3;
                    }
                }
                double d8 = a0.x1;
                double d9 = a.getSlope();
                double d10 = a0.x1;
                double d11 = d9 * d10;
                double d12 = a.getConstant();
                double d13 = d11 + d12;
                com.cim.AIA.DoublePoint a2 = new com.cim.AIA.DoublePoint(d8, d13);
                a1 = a2;
                break label1;
            }
            double d14 = a.getSlope();
            int i2 = Double.isInfinite(d14)?1:0;
            label4: {
                if(i2 != 0)
                {
                    break label4;
                }
                double d15 = a.getConstant();
                int i3 = Double.isInfinite(d15)?1:0;
                if(i3 != 0)
                {
                    break label4;
                }
                double d16 = a.getSlope();
                double d17 = d16 * d5;
                double d18 = a.getConstant();
                double d19 = d17 + d18;
                com.cim.AIA.DoublePoint a3 = new com.cim.AIA.DoublePoint(d5, d19);
                a1 = a3;
                break label1;
            }
            double d20 = a.x1;
            double d21 = a0.getSlope();
            double d22 = a.x1;
            double d23 = d21 * d22;
            double d24 = a0.getConstant();
            double d25 = d23 + d24;
            com.cim.AIA.DoublePoint a4 = new com.cim.AIA.DoublePoint(d20, d25);
            a1 = a4;
        }
        return a1;
    }
    
    public static double getOpposite(double d, double d0)
    {
        double d1 = com.cim.AIA.Line.getRadians(d);
        double d2 = Math.tan(d1);
        double d3 = d2 * d0;
        return d3;
    }
    
    public static double getRadians(double d)
    {
        double d0 = d / 57.29577951308232;
        return d0;
    }
    
    public static double getTheta(double d, double d0)
    {
        double d1 = d / d0;
        double d2 = Math.atan(d1);
        double d3 = com.cim.AIA.Line.getDegrees(d2);
        return d3;
    }
    
    public static int round(double d)
    {
        int i = 0;
        int i0 = d > 2147483647.0?1:d == 2147483647.0?0:-1;
        if(i0 <= 0)
        {
            long j = Math.round(d);
            int i1 = (int)j;
            i = i1;
        }
        else
        {
            i = 2147483647;
        }
        return i;
    }
    
    public Line(double d, double d0, double d1, double d2)
    {
        super();
        this.dottedGap = 5;
        this.showAsThick = false;
        this.lineThickness = 2;
        this.showAsDotted = false;
        this.showArrowHead = false;
        this.distanceFromStartForArrowHead = -2;
        java.awt.Color a = java.awt.Color.black;
        this.rayColor = a;
        java.awt.Color a0 = java.awt.Color.black;
        this.textColor = a0;
        this.label = "";
        this.distanceFromStartForLabel = -2;
        this.xLabelOffset = 0;
        this.yLabelOffset = 0;
        this.arrowHeadWidth = 5;
        this.arrowHeadLength = 10;
        this.x1 = d;
        this.x2 = d1;
        this.y1 = d0;
        this.y2 = d2;
    }
    
    public Line(com.cim.AIA.DoublePoint a, com.cim.AIA.DoublePoint a0)
    {
        double d = a.x;
        double d0 = a.y;
        double d1 = a0.x;
        double d2 = a0.y;
        this(d, d0, d1, d2);
    }
    
    public Line(int i, int i0, int i1, int i2)
    {
        double d = (double)i;
        double d0 = (double)i0;
        double d1 = (double)i1;
        double d2 = (double)i2;
        this(d, d0, d1, d2);
    }
    
    public Line(int i, int i0, int i1, int i2, java.awt.Color a)
    {
        this(i, i0, i1, i2);
        this.rayColor = a;
    }
    
    public Line(java.awt.Point a, java.awt.Point a0)
    {
        int i = a.x;
        int i0 = a.y;
        int i1 = a0.x;
        int i2 = a0.y;
        this(i, i0, i1, i2);
    }
    
    public void changeDirection()
    {
        double d = this.x1;
        double d0 = this.y1;
        double d1 = this.x2;
        this.x1 = d1;
        double d2 = this.y2;
        this.y1 = d2;
        this.x2 = d;
        this.y2 = d0;
    }
    
    public com.cim.AIA.Line copy()
    {
        double d = this.x1;
        double d0 = this.y1;
        double d1 = this.x2;
        double d2 = this.y2;
        com.cim.AIA.Line a = new com.cim.AIA.Line(d, d0, d1, d2);
        java.awt.Color a0 = this.rayColor;
        a.rayColor = a0;
        java.awt.Color a1 = this.textColor;
        a.textColor = a1;
        int i = this.showAsThick?1:0;
        a.showAsThick = i != 0;
        int i0 = this.showAsDotted?1:0;
        a.showAsDotted = i0 != 0;
        int i1 = this.showArrowHead?1:0;
        a.showArrowHead = i1 != 0;
        String s = this.label;
        a.label = s;
        int i2 = this.lineThickness;
        a.lineThickness = i2;
        int i3 = this.distanceFromStartForArrowHead;
        a.distanceFromStartForArrowHead = i3;
        int i4 = this.distanceFromStartForLabel;
        a.distanceFromStartForLabel = i4;
        int i5 = this.dottedGap;
        a.dottedGap = i5;
        int i6 = this.xLabelOffset;
        a.xLabelOffset = i6;
        int i7 = this.yLabelOffset;
        a.yLabelOffset = i7;
        int i8 = this.arrowHeadWidth;
        a.arrowHeadWidth = i8;
        int i9 = this.arrowHeadLength;
        a.arrowHeadLength = i9;
        return a;
    }
    
    public void draw(java.awt.Graphics a)
    {
        java.awt.Color a0 = a.getColor();
        java.awt.Color a1 = this.rayColor;
        a.setColor(a1);
        int i = this.showAsThick?1:0;
        label1: {
            int i0 = 0;
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.Polygon a2 = new java.awt.Polygon();
                double d = this.getSlope();
                double d0 = -1.0 * d;
                double d1 = Math.atan(d0);
                double d2 = com.cim.AIA.Line.getDegrees(d1);
                double d3 = com.cim.AIA.Line.getRadians(d2);
                double d4 = Math.cos(d3);
                int i1 = this.lineThickness;
                int i2 = i1 / 2;
                double d5 = (double)i2;
                double d6 = d4 * d5;
                double d7 = com.cim.AIA.Line.getRadians(d2);
                double d8 = Math.sin(d7);
                int i3 = this.lineThickness;
                int i4 = i3 / 2;
                double d9 = (double)i4;
                double d10 = d8 * d9;
                double d11 = this.x1;
                double d12 = d11 + d10;
                int i5 = com.cim.AIA.Line.round(d12);
                double d13 = this.y1;
                double d14 = d13 + d6;
                int i6 = com.cim.AIA.Line.round(d14);
                a2.addPoint(i5, i6);
                double d15 = this.x1;
                double d16 = d15 - d10;
                int i7 = com.cim.AIA.Line.round(d16);
                double d17 = this.y1;
                double d18 = d17 - d6;
                int i8 = com.cim.AIA.Line.round(d18);
                a2.addPoint(i7, i8);
                double d19 = this.x2;
                double d20 = d19 - d10;
                int i9 = com.cim.AIA.Line.round(d20);
                double d21 = this.y2;
                double d22 = d21 - d6;
                int i10 = com.cim.AIA.Line.round(d22);
                a2.addPoint(i9, i10);
                double d23 = this.x2;
                double d24 = d23 + d10;
                int i11 = com.cim.AIA.Line.round(d24);
                double d25 = this.y2;
                double d26 = d25 + d6;
                int i12 = com.cim.AIA.Line.round(d26);
                a2.addPoint(i11, i12);
                a.drawPolygon(a2);
                a.fillPolygon(a2);
                break label1;
            }
            int i13 = this.showAsDotted?1:0;
            label2: {
                if(i13 != 0)
                {
                    break label2;
                }
                int i14 = this.getIntX1();
                int i15 = this.getIntY1();
                int i16 = this.getIntX2();
                int i17 = this.getIntY2();
                a.drawLine(i14, i15, i16, i17);
                break label1;
            }
            double d27 = this.x2;
            double d28 = this.x1;
            int i18 = d27 < d28?-1:d27 == d28?0:1;
            if(i18 >= 0)
            {
                i0 = 0;
            }
            else
            {
                this.changeDirection();
                i0 = 1;
            }
            double d29 = this.x1;
            double d30 = this.y1;
            double d31 = this.x1;
            double d32 = this.x2;
            double d33 = d31 - d32;
            double d34 = Math.abs(d33);
            double d35 = this.y1;
            double d36 = this.y2;
            double d37 = d35 - d36;
            double d38 = Math.abs(d37);
            label4: {
                label3: {
                    int i19 = d34 > d38?1:d34 == d38?0:-1;
                    if(i19 > 0)
                    {
                        break label3;
                    }
                    double d39 = d29;
                    double d40 = d30;
                    int i20 = 0;
                    while(true)
                    {
                        double d41 = 0.0;
                        double d42 = 0.0;
                        double d43 = 0.0;
                        double d44 = 0.0;
                        double d45 = 0.0;
                        double d46 = 0.0;
                        double d47 = 0.0;
                        double d48 = 0.0;
                        double d49 = (double)i20;
                        double d50 = this.y1;
                        double d51 = d39;
                        double d52 = this.y2;
                        double d53 = d51;
                        double d54 = d50 - d52;
                        double d55 = Math.abs(d54);
                        double d56 = d53;
                        int i21 = this.dottedGap;
                        double d57 = d56;
                        double d58 = (double)i21;
                        double d59 = d55 / d58;
                        int i22 = d49 < d59?-1:d49 == d59?0:1;
                        double d60 = d57;
                        if(i22 >= 0)
                        {
                            break label4;
                        }
                        else
                        {
                            d41 = d60;
                        }
                        double d61 = this.y1;
                        double d62 = d41;
                        double d63 = this.y2;
                        double d64 = d62;
                        int i23 = d61 < d63?-1:d61 == d63?0:1;
                        double d65 = d64;
                        double d66 = d64;
                        if(i23 >= 0)
                        {
                            double d67 = d66;
                            int i24 = this.dottedGap;
                            double d68 = d67;
                            double d69 = (double)i24;
                            double d70 = d40 - d69;
                            d42 = d68;
                            d43 = d70;
                        }
                        else
                        {
                            double d71 = d65;
                            int i25 = this.dottedGap;
                            double d72 = d71;
                            double d73 = (double)i25;
                            double d74 = d40 + d73;
                            d42 = d72;
                            d43 = d74;
                        }
                        double d75 = this.getSlope();
                        double d76 = d42;
                        int i26 = Double.isInfinite(d75)?1:0;
                        double d77 = d76;
                        double d78 = d77;
                        if(i26 == 0)
                        {
                            double d79 = this.getConstant();
                            double d80 = d43 - d79;
                            double d81 = this.getSlope();
                            double d82 = d80 / d81;
                            long j = Math.round(d82);
                            int i27 = (int)j;
                            double d83 = (double)i27;
                            d44 = d77;
                            d45 = d83;
                        }
                        else
                        {
                            double d84 = d78;
                            d44 = d84;
                            d45 = d84;
                        }
                        int i28 = i20 % 2;
                        double d85 = d45;
                        double d86 = d45;
                        if(i28 != 0)
                        {
                            d46 = d86;
                        }
                        else
                        {
                            double d87 = d85;
                            int i29 = com.cim.AIA.Line.round(d44);
                            double d88 = d87;
                            int i30 = com.cim.AIA.Line.round(d40);
                            double d89 = d88;
                            int i31 = com.cim.AIA.Line.round(d89);
                            double d90 = d89;
                            int i32 = com.cim.AIA.Line.round(d43);
                            double d91 = d90;
                            a.drawLine(i29, i30, i31, i32);
                            d46 = d91;
                        }
                        double d92 = this.y1;
                        double d93 = d46;
                        double d94 = this.y2;
                        double d95 = d93;
                        int i33 = d92 < d94?-1:d92 == d94?0:1;
                        double d96 = d95;
                        double d97 = d95;
                        if(i33 >= 0)
                        {
                            double d98 = d97;
                            int i34 = this.dottedGap;
                            double d99 = d98;
                            double d100 = (double)i34;
                            double d101 = d40 - d100;
                            d47 = d101;
                            d48 = d99;
                        }
                        else
                        {
                            double d102 = d96;
                            int i35 = this.dottedGap;
                            double d103 = d102;
                            double d104 = (double)i35;
                            double d105 = d40 + d104;
                            d47 = d105;
                            d48 = d103;
                        }
                        int i36 = i20 + 1;
                        d39 = d48;
                        d40 = d47;
                        i20 = i36;
                    }
                }
                double d106 = d29;
                double d107 = d30;
                int i37 = 0;
                while(true)
                {
                    double d108 = (double)i37;
                    double d109 = this.x1;
                    double d110 = this.x2;
                    double d111 = d109 - d110;
                    double d112 = Math.abs(d111);
                    int i38 = this.dottedGap;
                    double d113 = (double)i38;
                    double d114 = d112 / d113;
                    int i39 = d108 < d114?-1:d108 == d114?0:1;
                    if(i39 >= 0)
                    {
                        break;
                    }
                    int i40 = this.dottedGap;
                    double d115 = (double)i40;
                    double d116 = d106 + d115;
                    double d117 = this.getSlope();
                    double d118 = d117 * d116;
                    double d119 = this.getConstant();
                    double d120 = d118 + d119;
                    int i41 = i37 % 2;
                    if(i41 == 0)
                    {
                        int i42 = com.cim.AIA.Line.round(d106);
                        int i43 = com.cim.AIA.Line.round(d107);
                        int i44 = com.cim.AIA.Line.round(d116);
                        int i45 = com.cim.AIA.Line.round(d120);
                        a.drawLine(i42, i43, i44, i45);
                    }
                    int i46 = this.dottedGap;
                    double d121 = (double)i46;
                    double d122 = d106 + d121;
                    int i47 = i37 + 1;
                    d106 = d122;
                    d107 = d120;
                    i37 = i47;
                }
            }
            if(i0 != 0)
            {
                this.changeDirection();
            }
        }
        int i48 = this.showArrowHead?1:0;
        label5: {
            double d123 = 0.0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            if(i48 == 0)
            {
                break label5;
            }
            int i52 = this.arrowHeadWidth;
            double d124 = (double)i52;
            int i53 = this.arrowHeadLength;
            double d125 = (double)i53;
            double d126 = com.cim.AIA.Line.getTheta(d124, d125);
            double d127 = this.y1;
            double d128 = this.y2;
            double d129 = d127 - d128;
            double d130 = this.x2;
            double d131 = this.x1;
            double d132 = d130 - d131;
            double d133 = com.cim.AIA.Line.getTheta(d129, d132);
            double d134 = this.x1;
            double d135 = this.x2;
            int i54 = d134 < d135?-1:d134 == d135?0:1;
            int i55 = i54 > 0?0:1;
            int i56 = this.arrowHeadLength;
            int i57 = this.distanceFromStartForArrowHead;
            double d136 = (double)i57;
            int i58 = this.distanceFromStartForArrowHead;
            switch(i58){
                case -1: {
                    d123 = 0.0;
                    break;
                }
                case -2: {
                    int i59 = this.getLength();
                    int i60 = i59 / 2;
                    double d137 = (double)i60;
                    d123 = d137;
                    break;
                }
                case -3: {
                    int i61 = this.getLength();
                    double d138 = (double)i61;
                    d123 = d138;
                    break;
                }
                default: {
                    d123 = d136;
                }
            }
            double d139 = Math.abs(d123);
            double d140 = Math.pow(d139, 2.0);
            double d141 = com.cim.AIA.Line.getRadians(d133);
            double d142 = Math.tan(d141);
            double d143 = Math.pow(d142, 2.0);
            double d144 = d143 + 1.0;
            double d145 = d140 / d144;
            double d146 = Math.sqrt(d145);
            double d147 = com.cim.AIA.Line.getOpposite(d133, d146);
            int[] a3 = new int[3];
            int[] a4 = new int[3];
            double d148 = (double)i56;
            double d149 = d133 + d126;
            double d150 = com.cim.AIA.Line.getRadians(d149);
            double d151 = Math.sin(d150);
            double d152 = d148 * d151;
            long j0 = Math.round(d152);
            int i62 = (int)j0;
            double d153 = (double)i56;
            double d154 = d133 + d126;
            double d155 = com.cim.AIA.Line.getRadians(d154);
            double d156 = Math.cos(d155);
            double d157 = d153 * d156;
            long j1 = Math.round(d157);
            int i63 = (int)j1;
            if(i55 == 0)
            {
                int i64 = this.getIntX1();
                long j2 = Math.round(d146);
                int i65 = (int)j2;
                int i66 = i64 - i65;
                a3[0] = i66;
                int i67 = this.getIntY1();
                long j3 = Math.round(d147);
                int i68 = (int)j3;
                int i69 = i67 + i68;
                a4[0] = i69;
                int i70 = i66 + i63;
                a3[1] = i70;
                int i71 = i69 - i62;
                a4[1] = i71;
                i49 = 0;
                i50 = i66;
                i51 = i69;
            }
            else
            {
                int i72 = this.getIntX1();
                long j4 = Math.round(d146);
                int i73 = (int)j4;
                int i74 = i72 + i73;
                a3[0] = i74;
                int i75 = this.getIntY1();
                long j5 = Math.round(d147);
                int i76 = (int)j5;
                int i77 = i75 - i76;
                a4[0] = i77;
                int i78 = i74 - i63;
                a3[1] = i78;
                int i79 = i77 + i62;
                a4[1] = i79;
                i49 = 1;
                i50 = i74;
                i51 = i77;
            }
            double d158 = (double)i56;
            double d159 = d133 - d126;
            double d160 = com.cim.AIA.Line.getRadians(d159);
            double d161 = Math.sin(d160);
            double d162 = d158 * d161;
            long j6 = Math.round(d162);
            int i80 = (int)j6;
            double d163 = (double)i56;
            double d164 = d133 - d126;
            double d165 = com.cim.AIA.Line.getRadians(d164);
            double d166 = Math.cos(d165);
            double d167 = d163 * d166;
            long j7 = Math.round(d167);
            int i81 = (int)j7;
            if(i49 == 0)
            {
                int i82 = i50 + i81;
                a3[2] = i82;
                int i83 = i51 - i80;
                a4[2] = i83;
            }
            else
            {
                int i84 = i50 - i81;
                a3[2] = i84;
                int i85 = i51 + i80;
                a4[2] = i85;
            }
            a.fillPolygon(a3, a4, 3);
        }
        String s = this.label;
        label6: {
            double d168 = 0.0;
            int i86 = 0;
            int i87 = 0;
            if(s == null)
            {
                break label6;
            }
            String s0 = this.label;
            if(s0 == "")
            {
                break label6;
            }
            java.awt.Color a5 = this.textColor;
            a.setColor(a5);
            double d169 = this.x1;
            double d170 = this.x2;
            int i88 = d169 < d170?-1:d169 == d170?0:1;
            int i89 = i88 > 0?0:1;
            double d171 = this.y1;
            double d172 = this.y2;
            double d173 = d171 - d172;
            double d174 = this.x2;
            double d175 = this.x1;
            double d176 = d174 - d175;
            double d177 = com.cim.AIA.Line.getTheta(d173, d176);
            int i90 = this.distanceFromStartForLabel;
            double d178 = (double)i90;
            int i91 = this.distanceFromStartForLabel;
            switch(i91){
                case -1: {
                    d168 = 0.0;
                    break;
                }
                case -2: {
                    int i92 = this.getLength();
                    int i93 = i92 / 2;
                    double d179 = (double)i93;
                    d168 = d179;
                    break;
                }
                case -3: {
                    int i94 = this.getLength();
                    double d180 = (double)i94;
                    d168 = d180;
                    break;
                }
                default: {
                    d168 = d178;
                }
            }
            double d181 = Math.abs(d168);
            double d182 = Math.pow(d181, 2.0);
            double d183 = com.cim.AIA.Line.getRadians(d177);
            double d184 = Math.tan(d183);
            double d185 = Math.pow(d184, 2.0);
            double d186 = d185 + 1.0;
            double d187 = d182 / d186;
            double d188 = Math.sqrt(d187);
            double d189 = com.cim.AIA.Line.getOpposite(d177, d188);
            if(i89 == 0)
            {
                int i95 = this.getIntX1();
                long j8 = Math.round(d188);
                int i96 = (int)j8;
                int i97 = i95 - i96;
                int i98 = this.getIntY1();
                long j9 = Math.round(d189);
                int i99 = (int)j9;
                int i100 = i98 + i99;
                i86 = i97;
                i87 = i100;
            }
            else
            {
                int i101 = this.getIntX1();
                long j10 = Math.round(d188);
                int i102 = (int)j10;
                int i103 = i101 + i102;
                int i104 = this.getIntY1();
                long j11 = Math.round(d189);
                int i105 = (int)j11;
                int i106 = i104 - i105;
                i86 = i103;
                i87 = i106;
            }
            String s1 = this.label;
            int i107 = this.xLabelOffset;
            int i108 = i86 + i107;
            int i109 = this.yLabelOffset;
            int i110 = i87 + i109;
            a.drawString(s1, i108, i110);
        }
        a.setColor(a0);
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        int i = a0.x;
        this.setX(i);
        int i0 = a0.y;
        this.setY(i0);
        this.draw(a);
    }
    
    public boolean equals(com.cim.AIA.Line a)
    {
        int i = 0;
        double d = a.x1;
        double d0 = this.x1;
        label2: {
            label1: {
                label0: {
                    int i0 = d > d0?1:d == d0?0:-1;
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    double d1 = a.x2;
                    double d2 = this.x2;
                    int i1 = d1 > d2?1:d1 == d2?0:-1;
                    if(i1 != 0)
                    {
                        break label0;
                    }
                    double d3 = a.y1;
                    double d4 = this.y1;
                    int i2 = d3 > d4?1:d3 == d4?0:-1;
                    if(i2 != 0)
                    {
                        break label0;
                    }
                    double d5 = a.y2;
                    double d6 = this.y2;
                    int i3 = d5 > d6?1:d5 == d6?0:-1;
                    if(i3 == 0)
                    {
                        break label1;
                    }
                }
                i = 0;
                break label2;
            }
            i = 1;
        }
        return i != 0;
    }
    
    public double getAngle()
    {
        double d = this.y1;
        double d0 = this.y2;
        double d1 = d - d0;
        double d2 = this.x2;
        double d3 = this.x1;
        double d4 = d2 - d3;
        double d5 = com.cim.AIA.Line.getTheta(d1, d4);
        return d5;
    }
    
    public java.awt.Color getColor()
    {
        java.awt.Color a = this.rayColor;
        return a;
    }
    
    public double getConstant()
    {
        double d = this.y1;
        double d0 = this.getSlope();
        double d1 = this.x1;
        double d2 = d0 * d1;
        double d3 = d - d2;
        return d3;
    }
    
    public int getIntX1()
    {
        double d = this.x1;
        int i = com.cim.AIA.Line.round(d);
        return i;
    }
    
    public int getIntX2()
    {
        double d = this.x2;
        int i = com.cim.AIA.Line.round(d);
        return i;
    }
    
    public int getIntY1()
    {
        double d = this.y1;
        int i = com.cim.AIA.Line.round(d);
        return i;
    }
    
    public int getIntY2()
    {
        double d = this.y2;
        int i = com.cim.AIA.Line.round(d);
        return i;
    }
    
    public int getLength()
    {
        double d = this.x1;
        double d0 = this.x2;
        double d1 = Math.max(d, d0);
        double d2 = this.x1;
        double d3 = this.x2;
        double d4 = Math.min(d2, d3);
        double d5 = d1 - d4;
        double d6 = this.y1;
        double d7 = this.y2;
        double d8 = Math.max(d6, d7);
        double d9 = this.y1;
        double d10 = this.y2;
        double d11 = Math.min(d9, d10);
        double d12 = d8 - d11;
        double d13 = Math.pow(d5, 2.0);
        double d14 = Math.pow(d12, 2.0);
        double d15 = d13 + d14;
        double d16 = Math.sqrt(d15);
        int i = (int)d16;
        return i;
    }
    
    public double getSlope()
    {
        double d = this.y2;
        double d0 = this.y1;
        double d1 = d - d0;
        double d2 = this.x2;
        double d3 = this.x1;
        double d4 = d2 - d3;
        double d5 = d1 / d4;
        return d5;
    }
    
    public java.awt.Color getTextColor()
    {
        java.awt.Color a = this.textColor;
        return a;
    }
    
    public int getX()
    {
        double d = this.x2;
        int i = (int)d;
        return i;
    }
    
    public double getX1()
    {
        double d = this.x1;
        return d;
    }
    
    public double getX2()
    {
        double d = this.x2;
        return d;
    }
    
    public double getXForY(double d)
    {
        double d0 = 0.0;
        com.cim.AIA.Line a = new com.cim.AIA.Line(0.0, d, 1.7976931348623157e+308, d);
        com.cim.AIA.DoublePoint a0 = com.cim.AIA.Line.getIntersection(this, a);
        if(a0 != null)
        {
            double d1 = a0.x;
            d0 = d1;
        }
        else
        {
            double d2 = this.x1;
            d0 = d2;
        }
        return d0;
    }
    
    public int getY()
    {
        double d = this.y2;
        int i = (int)d;
        return i;
    }
    
    public double getY1()
    {
        double d = this.y1;
        return d;
    }
    
    public double getY2()
    {
        double d = this.y2;
        return d;
    }
    
    public double getYForX(double d)
    {
        com.cim.AIA.Line a = new com.cim.AIA.Line(d, 0.0, d, 1.7976931348623157e+308);
        com.cim.AIA.DoublePoint a0 = com.cim.AIA.Line.getIntersection(this, a);
        double d0 = a0.y;
        return d0;
    }
    
    public java.awt.Point intersectAt(com.cim.AIA.Line a)
    {
        java.awt.Point a0 = null;
        com.cim.AIA.DoublePoint a1 = com.cim.AIA.Line.getIntersection(this, a);
        if(a1 != null)
        {
            double d = a1.x;
            int i = com.cim.AIA.Line.round(d);
            double d0 = a1.y;
            int i0 = com.cim.AIA.Line.round(d0);
            java.awt.Point a2 = new java.awt.Point(i, i0);
            a0 = a2;
        }
        else
        {
            a0 = null;
        }
        return a0;
    }
    
    public void setArrowHeadWidth(int i)
    {
        this.arrowHeadWidth = i;
    }
    
    public void setColor(java.awt.Color a)
    {
        this.rayColor = a;
    }
    
    public void setDistanceFromStartForArrowHead(int i)
    {
        this.distanceFromStartForArrowHead = i;
    }
    
    public void setDistanceFromStartForLabel(int i)
    {
        this.distanceFromStartForLabel = i;
    }
    
    public void setDottedGap(int i)
    {
        this.dottedGap = i;
    }
    
    public void setEndPosition(com.cim.AIA.DoublePoint a)
    {
        double d = a.x;
        this.x2 = d;
        double d0 = a.y;
        this.y2 = d0;
    }
    
    public void setEndPosition(java.awt.Point a)
    {
        int i = a.x;
        this.setIntX2(i);
        int i0 = a.y;
        this.setIntY2(i0);
    }
    
    public void setIntX1(int i)
    {
        double d = (double)i;
        this.x1 = d;
    }
    
    public void setIntX2(int i)
    {
        double d = (double)i;
        this.x2 = d;
    }
    
    public void setIntY1(int i)
    {
        double d = (double)i;
        this.y1 = d;
    }
    
    public void setIntY2(int i)
    {
        double d = (double)i;
        this.y2 = d;
    }
    
    public void setLabel(String s)
    {
        this.label = s;
    }
    
    public void setLineThickness(int i)
    {
        this.lineThickness = i;
    }
    
    public void setStartPosition(com.cim.AIA.DoublePoint a)
    {
        double d = a.x;
        this.x1 = d;
        double d0 = a.y;
        this.y1 = d0;
    }
    
    public void setStartPosition(java.awt.Point a)
    {
        int i = a.x;
        this.setIntX1(i);
        int i0 = a.y;
        this.setIntY1(i0);
    }
    
    public void setTextColor(java.awt.Color a)
    {
        this.textColor = a;
    }
    
    public void setX(int i)
    {
        double d = (double)i;
        this.x2 = d;
    }
    
    public void setXLabelOffset(int i)
    {
        this.xLabelOffset = i;
    }
    
    public void setY(int i)
    {
        double d = (double)i;
        this.y2 = d;
    }
    
    public void setYLabelOffset(int i)
    {
        this.yLabelOffset = i;
    }
    
    public void shiftEntire(int i, int i0)
    {
        double d = this.x1;
        double d0 = (double)i;
        double d1 = d + d0;
        this.x1 = d1;
        double d2 = this.x2;
        double d3 = (double)i;
        double d4 = d2 + d3;
        this.x2 = d4;
        double d5 = this.y1;
        double d6 = (double)i0;
        double d7 = d5 + d6;
        this.y1 = d7;
        double d8 = this.y2;
        double d9 = (double)i0;
        double d10 = d8 + d9;
        this.y2 = d10;
    }
    
    public void showArrowHead(boolean b)
    {
        this.showArrowHead = b;
    }
    
    public void showAsDotted(boolean b)
    {
        this.showAsDotted = b;
    }
    
    public void showAsThick(boolean b)
    {
        this.showAsThick = b;
    }
    
    public String toString()
    {
        StringBuilder a = new StringBuilder();
        StringBuilder a0 = a.append("com.cim.AIA.Line: X1 = ");
        double d = this.x1;
        StringBuilder a1 = a0.append(d);
        StringBuilder a2 = a1.append(" Y1 = ");
        double d0 = this.y1;
        StringBuilder a3 = a2.append(d0);
        StringBuilder a4 = a3.append(" X2 = ");
        double d1 = this.x2;
        StringBuilder a5 = a4.append(d1);
        StringBuilder a6 = a5.append(" Y2 = ");
        double d2 = this.y2;
        StringBuilder a7 = a6.append(d2);
        String s = a7.toString();
        String s0 = new String(s);
        return s0;
    }
}