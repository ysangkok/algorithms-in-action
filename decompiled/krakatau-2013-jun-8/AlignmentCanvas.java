public class AlignmentCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 2132280409718461071L;
    protected AlignmentMinimum theMinimum;
    protected com.cim.AIA.ElementArray string1EA;
    protected com.cim.AIA.ElementArray string2EA;
    protected AlignmentKey theKey;
    protected AlignmentMatrix3D the3D;
    protected AlignmentMatrix tempMatrix;
    protected AlignmentMatrix aMatrix;
    protected AlignmentMatrix bMatrix;
    protected AlignmentMatrix cMatrix;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenArrays;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected java.awt.Font normalFont;
    protected java.awt.Color textColor;
    protected Alignment theAlignment;
    
    public AlignmentCanvas()
    {
        super();
        this.xBuffer = 100;
        this.yBuffer = 10;
        this.spacingBetweenArrays = 100;
        this.xTextBuffer = 10;
        this.yTextBuffer = 5;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        Alignment a0 = this.theAlignment;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.Font a1 = this.normalFont;
            if(a1 != null)
            {
                java.awt.Font a2 = this.normalFont;
                a.setFont(a2);
            }
            java.awt.Color a3 = this.textColor;
            if(a3 != null)
            {
                java.awt.Color a4 = this.textColor;
                a.setColor(a4);
            }
            AlignmentMatrix a5 = this.tempMatrix;
            if(a5 != null)
            {
                AlignmentMatrix a6 = this.tempMatrix;
                a6.draw(a);
            }
            AlignmentMatrix a7 = this.aMatrix;
            if(a7 != null)
            {
                AlignmentMatrix a8 = this.aMatrix;
                a8.draw(a);
            }
            AlignmentMatrix a9 = this.bMatrix;
            if(a9 != null)
            {
                AlignmentMatrix a10 = this.bMatrix;
                a10.draw(a);
            }
            AlignmentMatrix a11 = this.cMatrix;
            if(a11 != null)
            {
                AlignmentMatrix a12 = this.cMatrix;
                a12.draw(a);
            }
            com.cim.AIA.ElementArray a13 = this.string1EA;
            label2: {
                if(a13 == null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a14 = this.string2EA;
                if(a14 != null)
                {
                    com.cim.AIA.ElementArray a15 = this.string1EA;
                    a15.draw(a);
                    com.cim.AIA.ElementArray a16 = this.string2EA;
                    a16.draw(a);
                }
            }
            AlignmentMinimum a17 = this.theMinimum;
            if(a17 != null)
            {
                AlignmentMinimum a18 = this.theMinimum;
                a18.draw(a);
            }
            AlignmentKey a19 = this.theKey;
            if(a19 != null)
            {
                AlignmentKey a20 = this.theKey;
                a20.draw(a);
            }
            AlignmentMatrix3D a21 = this.the3D;
            if(a21 != null)
            {
                AlignmentMatrix3D a22 = this.the3D;
                a22.draw(a);
            }
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.TEXT_COLOR;
        int i = s.equalsIgnoreCase(s0)?1:0;
        if(i != 0)
        {
            java.awt.Color a0 = a.getColor();
            this.textColor = a0;
        }
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label1: {
            int i = 0;
            int i0 = 0;
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                break label1;
            }
            java.awt.Dimension a1 = this.getParentSize();
            int i11 = a1.width;
            java.util.Vector a2 = this.drawables;
            a2.removeAllElements();
            this.removeAllSelectables();
            Object a3 = a.paramObj;
            Alignment dummy = (Alignment)a3;
            Alignment a4 = (Alignment)a3;
            this.theAlignment = a4;
            Alignment a5 = this.theAlignment;
            AlignmentMatrix a6 = a5.getMatrix();
            this.tempMatrix = a6;
            Alignment a7 = this.theAlignment;
            AlignmentMatrix a8 = a7.getAMatrix();
            this.aMatrix = a8;
            Alignment a9 = this.theAlignment;
            AlignmentMatrix a10 = a9.getBMatrix();
            this.bMatrix = a10;
            Alignment a11 = this.theAlignment;
            AlignmentMatrix a12 = a11.getCMatrix();
            this.cMatrix = a12;
            Alignment a13 = this.theAlignment;
            com.cim.AIA.ElementArray a14 = a13.getString1EA();
            this.string1EA = a14;
            Alignment a15 = this.theAlignment;
            com.cim.AIA.ElementArray a16 = a15.getString2EA();
            this.string2EA = a16;
            Alignment a17 = this.theAlignment;
            AlignmentMinimum a18 = a17.getMinimum();
            this.theMinimum = a18;
            Alignment a19 = this.theAlignment;
            AlignmentKey a20 = a19.getKey();
            this.theKey = a20;
            Alignment a21 = this.theAlignment;
            AlignmentMatrix3D a22 = a21.get3D();
            this.the3D = a22;
            AlignmentMatrix a23 = this.tempMatrix;
            if(a23 == null)
            {
                i = 0;
            }
            else
            {
                AlignmentMatrix a24 = this.tempMatrix;
                int i12 = a24.getWidth();
                int i13 = i12 + 20;
                i = i13;
            }
            AlignmentMatrix a25 = this.aMatrix;
            if(a25 == null)
            {
                i0 = i;
            }
            else
            {
                AlignmentMatrix a26 = this.aMatrix;
                int i14 = a26.getWidth();
                int i15 = i14 + 20;
                int i16 = i15 * 3;
                int i17 = 20 + i16;
                i0 = i17;
            }
            com.cim.AIA.ElementArray a27 = this.string1EA;
            label2: {
                int i18 = 0;
                if(a27 == null)
                {
                    i1 = i0;
                    break label2;
                }
                com.cim.AIA.ElementArray a28 = this.string2EA;
                if(a28 == null)
                {
                    i1 = i0;
                    break label2;
                }
                com.cim.AIA.ElementArray a29 = this.string1EA;
                int i19 = a29.getWidth();
                if(i19 <= i0)
                {
                    i18 = i0;
                }
                else
                {
                    com.cim.AIA.ElementArray a30 = this.string1EA;
                    int i20 = a30.getWidth();
                    i18 = i20;
                }
                com.cim.AIA.ElementArray a31 = this.string2EA;
                int i21 = a31.getWidth();
                if(i21 <= i18)
                {
                    i1 = i18;
                }
                else
                {
                    com.cim.AIA.ElementArray a32 = this.string2EA;
                    int i22 = a32.getWidth();
                    i1 = i22;
                }
            }
            int i23 = i11 >= i1?i11:i1;
            AlignmentMatrix a33 = this.tempMatrix;
            if(a33 == null)
            {
                i2 = 30;
                i3 = 0;
            }
            else
            {
                AlignmentMatrix a34 = this.tempMatrix;
                AlignmentMatrix a35 = this.tempMatrix;
                int i24 = a35.getWidth();
                int i25 = i23 - i24;
                int i26 = i25 / 2;
                a34.setLocation(i26, 30);
                AlignmentMatrix a36 = this.tempMatrix;
                int i27 = a36.getHeight();
                int i28 = 30 + i27;
                AlignmentMatrix a37 = this.tempMatrix;
                int i29 = a37.getWidth();
                i2 = i28;
                i3 = i29;
            }
            AlignmentMatrix a38 = this.aMatrix;
            if(a38 == null)
            {
                i4 = i3;
            }
            else
            {
                AlignmentMatrix a39 = this.aMatrix;
                a39.setLocation(20, i2);
                AlignmentMatrix a40 = this.aMatrix;
                int i30 = a40.getWidth();
                i4 = i30;
            }
            AlignmentMatrix a41 = this.bMatrix;
            if(a41 == null)
            {
                i5 = i4;
            }
            else
            {
                AlignmentMatrix a42 = this.bMatrix;
                int i31 = 20 + i4;
                int i32 = i31 + 20;
                a42.setLocation(i32, i2);
                AlignmentMatrix a43 = this.bMatrix;
                int i33 = a43.getWidth();
                i5 = i33;
            }
            AlignmentMatrix a44 = this.cMatrix;
            if(a44 == null)
            {
                i6 = i2;
                i7 = i5;
            }
            else
            {
                AlignmentMatrix a45 = this.cMatrix;
                int i34 = i5 + 20;
                int i35 = i34 * 2;
                int i36 = 20 + i35;
                a45.setLocation(i36, i2);
                AlignmentMatrix a46 = this.cMatrix;
                int i37 = a46.getHeight();
                int i38 = i2 + i37;
                AlignmentMatrix a47 = this.cMatrix;
                int i39 = a47.getWidth();
                i6 = i38;
                i7 = i39;
            }
            com.cim.AIA.ElementArray a48 = this.string1EA;
            label3: {
                int i40 = 0;
                if(a48 == null)
                {
                    i8 = i6;
                    break label3;
                }
                com.cim.AIA.ElementArray a49 = this.string2EA;
                if(a49 == null)
                {
                    i8 = i6;
                    break label3;
                }
                int i41 = i6 + 20;
                com.cim.AIA.ElementArray a50 = this.string1EA;
                int i42 = a50.getWidth();
                com.cim.AIA.ElementArray a51 = this.string2EA;
                int i43 = a51.getWidth();
                if(i43 <= i42)
                {
                    i40 = i42;
                }
                else
                {
                    com.cim.AIA.ElementArray a52 = this.string2EA;
                    int i44 = a52.getWidth();
                    i40 = i44;
                }
                com.cim.AIA.ElementArray a53 = this.string1EA;
                int i45 = i23 - i40;
                int i46 = i45 / 2;
                a53.setLocation(i46, i41);
                com.cim.AIA.ElementArray a54 = this.string1EA;
                int i47 = a54.getHeight();
                int i48 = i41 + i47;
                int i49 = i48 + 10;
                com.cim.AIA.ElementArray a55 = this.string2EA;
                int i50 = i23 - i40;
                int i51 = i50 / 2;
                a55.setLocation(i51, i49);
                com.cim.AIA.ElementArray a56 = this.string2EA;
                int i52 = a56.getHeight();
                int i53 = i49 + i52;
                i8 = i53;
            }
            AlignmentMinimum a57 = this.theMinimum;
            if(a57 == null)
            {
                i9 = i8;
            }
            else
            {
                int i54 = i8 + 20;
                AlignmentMinimum a58 = this.theMinimum;
                int i55 = i23 - i7;
                int i56 = i55 / 2;
                a58.setLocation(i56, i54);
                AlignmentMinimum a59 = this.theMinimum;
                int i57 = a59.getHeight();
                int i58 = i54 + i57;
                i9 = i58;
            }
            AlignmentKey a60 = this.theKey;
            label4: {
                if(a60 == null)
                {
                    i10 = i9;
                    break label4;
                }
                int i59 = i9 + 20;
                AlignmentKey a61 = this.theKey;
                AlignmentKey a62 = this.theKey;
                int i60 = a62.getWidth();
                int i61 = i23 - i60;
                int i62 = i61 / 2;
                a61.setLocation(i62, i59);
                AlignmentMatrix3D a63 = this.the3D;
                label5: {
                    if(a63 != null)
                    {
                        break label5;
                    }
                    AlignmentKey a64 = this.theKey;
                    int i63 = a64.getHeight();
                    int i64 = i59 + i63;
                    i10 = i64;
                    break label4;
                }
                AlignmentMatrix3D a65 = this.the3D;
                a65.setLocation(20, i59);
                AlignmentMatrix3D a66 = this.the3D;
                int i65 = a66.getHeight();
                if(i65 != 0)
                {
                    AlignmentMatrix3D a67 = this.the3D;
                    int i66 = a67.getHeight();
                    int i67 = i59 + i66;
                    i10 = i67;
                }
                else
                {
                    int i68 = i59 + 300;
                    i10 = i68;
                }
            }
            int i69 = i10 + 20;
            this.setSize(i23, i69);
            AlignmentMatrix a68 = this.tempMatrix;
            if(a68 != null)
            {
                AlignmentMatrix a69 = this.tempMatrix;
                this.addSelectable((com.cim.AIA.Selectable)a69);
            }
            AlignmentMatrix a70 = this.aMatrix;
            if(a70 != null)
            {
                AlignmentMatrix a71 = this.aMatrix;
                this.addSelectable((com.cim.AIA.Selectable)a71);
            }
            AlignmentMatrix a72 = this.bMatrix;
            if(a72 != null)
            {
                AlignmentMatrix a73 = this.bMatrix;
                this.addSelectable((com.cim.AIA.Selectable)a73);
            }
            AlignmentMatrix a74 = this.cMatrix;
            if(a74 != null)
            {
                AlignmentMatrix a75 = this.cMatrix;
                this.addSelectable((com.cim.AIA.Selectable)a75);
            }
            this.repaint();
        }
    }
}