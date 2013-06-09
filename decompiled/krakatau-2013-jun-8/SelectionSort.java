public class SelectionSort extends com.cim.AIA.Algorithm implements com.cim.AIA.ColorSelectionListener {
    final public static int MARKER_NOT_READY = -10;
    final protected static String ARRAY_COLOR;
    final protected static String FINISHED_ARRAY_COLOR;
    protected int[] data;
    protected int recordOfI;
    protected java.util.Vector swapRequests;
    protected java.util.Vector questions;
    protected String[] duplicateLabels;
    protected int i;
    protected int j;
    protected int minPosition;
    protected java.awt.Color textColor;
    protected java.awt.Color arrayColor;
    protected java.awt.Color finishedArrayColor;
    protected java.awt.Color backgroundColor;
    
    public SelectionSort(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super(a, a0);
        this.recordOfI = 0;
        java.util.Vector a1 = new java.util.Vector();
        this.swapRequests = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.questions = a2;
        this.i = -10;
        this.j = -10;
        this.minPosition = -10;
        java.awt.Color a3 = java.awt.Color.black;
        this.textColor = a3;
        java.awt.Color a4 = java.awt.Color.blue;
        this.arrayColor = a4;
        java.awt.Color a5 = java.awt.Color.gray;
        this.finishedArrayColor = a5;
        java.awt.Color a6 = java.awt.Color.white;
        this.backgroundColor = a6;
        int[] dummy = (int[])a0;
        int[] a7 = (int[])a0;
        this.initialise(a7);
        com.cim.AIA.ConfigurationManager a8 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        java.awt.Color a9 = this.arrayColor;
        String s = SelectionSort.ARRAY_COLOR;
        com.cim.AIA.ColorSelection a10 = new com.cim.AIA.ColorSelection(a9, s);
        a10.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        java.awt.Color a11 = this.finishedArrayColor;
        String s0 = SelectionSort.FINISHED_ARRAY_COLOR;
        com.cim.AIA.ColorSelection a12 = new com.cim.AIA.ColorSelection(a11, s0);
        a12.addClass((com.cim.AIA.ConfigurationSelectionListener)this);
        a8.addColorSelection(a10);
        a8.addColorSelection(a12);
        a8.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
    }
    
    protected void createDuplicateLabels()
    {
        int[] a = this.data;
        int i = a.length;
        Integer[] a0 = new Integer[i];
        int i0 = 0;
        while(true)
        {
            int i1 = a0.length;
            if(i0 >= i1)
            {
                String[] a1 = com.cim.AIA.DuplicateLabel.createDuplicateLabels((Object[])a0);
                this.duplicateLabels = a1;
                return;
            }
            else
            {
                int[] a2 = this.data;
                int i2 = a2[i0];
                Integer a3 = new Integer(i2);
                a0[i0] = a3;
                int i3 = i0 + 1;
                i0 = i3;
            }
        }
    }
    
    protected java.util.Vector generateQuestions()
    {
        java.util.Vector a = new java.util.Vector();
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.questions;
            int i0 = a0.size();
            if(i >= i0)
            {
                return a;
            }
            else
            {
                java.util.Vector a1 = this.questions;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.SwapRequest dummy = (com.cim.AIA.SwapRequest)a2;
                com.cim.AIA.SwapRequest a3 = (com.cim.AIA.SwapRequest)a2;
                int i1 = a3.getFirstId();
                int i2 = a3.getSecondId();
                SelectionSwapQuestion a4 = new SelectionSwapQuestion(i1, i2);
                a.addElement((Object)a4);
                int i3 = i + 1;
                i = i3;
            }
        }
    }
    
    public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable a, Object a0, int i)
    {
        Object a1 = a;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)a1);
        com.cim.AIA.ElementArray dummy = (com.cim.AIA.ElementArray)a0;
        com.cim.AIA.ElementArray a3 = (com.cim.AIA.ElementArray)a0;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a4 = this.swapRequests;
            int i1 = a4.size();
            if(i0 >= i1)
            {
                return a2;
            }
            else
            {
                java.util.Vector a5 = this.swapRequests;
                Object a6 = a5.elementAt(i0);
                com.cim.AIA.SwapRequest dummy0 = (com.cim.AIA.SwapRequest)a6;
                com.cim.AIA.SwapRequest a7 = (com.cim.AIA.SwapRequest)a6;
                int i2 = a7.getFirstId();
                com.cim.AIA.Element a8 = a3.getElement(i2);
                com.cim.AIA.VerticalBar dummy1 = (com.cim.AIA.VerticalBar)a8;
                com.cim.AIA.VerticalBar a9 = (com.cim.AIA.VerticalBar)a8;
                int i3 = a7.getSecondId();
                com.cim.AIA.Element a10 = a3.getElement(i3);
                com.cim.AIA.VerticalBar dummy2 = (com.cim.AIA.VerticalBar)a10;
                com.cim.AIA.VerticalBar a11 = (com.cim.AIA.VerticalBar)a10;
                java.awt.Point a12 = a11.getPosition();
                java.awt.Point a13 = a9.getPosition();
                com.cim.AIA.MoveTween a14 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a9, a12, a13, false, i);
                a2.add((com.cim.AIA.Tween)a14);
                java.awt.Point a15 = a9.getPosition();
                java.awt.Point a16 = a11.getPosition();
                com.cim.AIA.MoveTween a17 = new com.cim.AIA.MoveTween((com.cim.AIA.Paintable)null, (com.cim.AIA.Moveable)a11, a15, a16, false, i);
                a2.add((com.cim.AIA.Tween)a17);
                int i4 = i0 + 1;
                i0 = i4;
            }
        }
    }
    
    public String getClassName()
    {
        return "SelectionSortCanvas";
    }
    
    public com.cim.AIA.ElementArray getElementArray(java.awt.Graphics a)
    {
        com.cim.AIA.ElementArray a0 = new com.cim.AIA.ElementArray(0, 0);
        java.awt.FontMetrics a1 = a.getFontMetrics();
        int i = a1.stringWidth("8");
        int i0 = i + 2;
        a0.setElementWidth(i0);
        a0.setAllHaveSameWidth(true);
        int i1 = 0;
        while(true)
        {
            int[] a2 = this.data;
            int i2 = a2.length;
            if(i1 >= i2)
            {
                return a0;
            }
            int[] a3 = this.data;
            int i3 = a3[i1];
            java.awt.Color a4 = this.arrayColor;
            java.awt.Point a5 = new java.awt.Point(0, 0);
            com.cim.AIA.VerticalBar a6 = new com.cim.AIA.VerticalBar(i1, i3, a4, a5);
            int i4 = this.recordOfI;
            if(i1 < i4)
            {
                java.awt.Color a7 = this.finishedArrayColor;
                a6.setColor(a7);
            }
            a6.setMarkersAboveValue(false);
            java.awt.Color a8 = this.textColor;
            a6.setTextColor(a8);
            a0.setValue(i1, (com.cim.AIA.Element)a6);
            String[] a9 = this.duplicateLabels;
            String s = a9[i1];
            if(s != null)
            {
                StringBuilder a10 = new StringBuilder();
                StringBuilder a11 = a10.append("");
                int[] a12 = this.data;
                int i5 = a12[i1];
                StringBuilder a13 = a11.append(i5);
                String[] a14 = this.duplicateLabels;
                String s0 = a14[i1];
                StringBuilder a15 = a13.append(s0);
                String s1 = a15.toString();
                com.cim.AIA.StringMarker a16 = new com.cim.AIA.StringMarker(s1);
                java.awt.Color a17 = this.backgroundColor;
                a16.setBackgroundColor(a17);
                java.awt.Color a18 = this.textColor;
                a16.setColor(a18);
                com.cim.AIA.Element a19 = a0.getElement(i1);
                a19.addMarker(a16);
            }
            int i6 = i1 + 1;
            i1 = i6;
        }
    }
    
    public int getI()
    {
        int i = this.i;
        return i;
    }
    
    public int getJ()
    {
        int i = this.j;
        return i;
    }
    
    public int getMinPosition()
    {
        int i = this.minPosition;
        return i;
    }
    
    protected boolean hasQuestions()
    {
        java.util.Vector a = this.questions;
        int i = a.size();
        int i0 = i <= 0?0:1;
        return i0 != 0;
    }
    
    protected void initialise(int[] a)
    {
        this.data = a;
        this.createDuplicateLabels();
        this.i = -10;
        this.j = -10;
        this.recordOfI = 0;
        this.minPosition = -10;
    }
    
    protected void makeQuestion(int i)
    {
        int i0 = i;
        int i1 = i;
        while(true)
        {
            int i2 = 0;
            int[] a = this.data;
            int i3 = i0;
            int i4 = a.length;
            int i5 = i3;
            int i6 = i5;
            if(i1 >= i4)
            {
                java.util.Vector a0 = this.questions;
                com.cim.AIA.SwapRequest a1 = new com.cim.AIA.SwapRequest(i, i5);
                a0.addElement((Object)a1);
                return;
            }
            else
            {
                i2 = i6;
            }
            int[] a2 = this.data;
            int i7 = i2;
            int i8 = a2[i1];
            int i9 = i7;
            int[] a3 = this.data;
            int i10 = i9;
            int i11 = a3[i10];
            int i12 = i10;
            int i13 = i12;
            int i14 = i8 >= i11?i13:i1;
            int i15 = i1 + 1;
            i0 = i14;
            i1 = i15;
        }
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i = s.equalsIgnoreCase(s0)?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                java.awt.Color a0 = a.getColor();
                this.backgroundColor = a0;
                break label1;
            }
            String s1 = com.cim.AIA.ColorSelection.TEXT_COLOR;
            int i0 = s.equalsIgnoreCase(s1)?1:0;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                java.awt.Color a1 = a.getColor();
                this.textColor = a1;
                break label1;
            }
            String s2 = SelectionSort.ARRAY_COLOR;
            int i1 = s.equalsIgnoreCase(s2)?1:0;
            label3: {
                if(i1 == 0)
                {
                    break label3;
                }
                java.awt.Color a2 = a.getColor();
                this.arrayColor = a2;
                break label1;
            }
            String s3 = SelectionSort.FINISHED_ARRAY_COLOR;
            int i2 = s.equalsIgnoreCase(s3)?1:0;
            if(i2 != 0)
            {
                java.awt.Color a3 = a.getColor();
                this.finishedArrayColor = a3;
            }
        }
    }
    
    public void reInitialise(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.initialise(a0);
    }
    
    protected void removeAllAnimationRequests()
    {
        java.util.Vector a = this.swapRequests;
        a.removeAllElements();
    }
    
    protected void removeAllQuestions()
    {
        java.util.Vector a = this.questions;
        a.removeAllElements();
    }
    
    protected void run()
    {
        this.setPosition("1");
        this.i = 0;
        label0: while(true)
        {
            int i = this.i;
            int[] a = this.data;
            int i0 = a.length;
            if(i >= i0)
            {
                int[] a0 = this.data;
                int i1 = a0.length;
                this.recordOfI = i1;
                this.i = -10;
                this.setPosition("15");
                return;
            }
            int i2 = this.i;
            this.recordOfI = i2;
            int i3 = this.i;
            this.makeQuestion(i3);
            this.askQuestionsWithoutSetPosition();
            this.setPosition("2");
            int i4 = this.i;
            this.minPosition = i4;
            this.setPosition("3");
            this.setPosition("4");
            int i5 = this.i;
            int i6 = i5 + 1;
            this.j = i6;
            while(true)
            {
                int i7 = this.j;
                int[] a1 = this.data;
                int i8 = a1.length;
                if(i7 >= i8)
                {
                    this.setPosition("5");
                    this.j = -10;
                    this.setPosition("8");
                    this.setPosition("10");
                    int i9 = this.i;
                    int i10 = this.minPosition;
                    this.swap(i9, i10);
                    this.setPosition("13");
                    this.minPosition = -10;
                    int i11 = this.i;
                    int i12 = i11 + 1;
                    this.i = i12;
                    continue label0;
                }
                this.setPosition("5");
                this.setPosition("6");
                int[] a2 = this.data;
                int i13 = this.j;
                int i14 = a2[i13];
                int[] a3 = this.data;
                int i15 = this.minPosition;
                int i16 = a3[i15];
                if(i14 < i16)
                {
                    int i17 = this.j;
                    this.minPosition = i17;
                    this.setPosition("7");
                }
                int i18 = this.j;
                int i19 = i18 + 1;
                this.j = i19;
            }
        }
    }
    
    protected void swap(int i, int i0)
    {
        int[] a = this.data;
        int i1 = a[i];
        int[] a0 = this.data;
        int[] a1 = this.data;
        int i2 = a1[i0];
        a0[i] = i2;
        int[] a2 = this.data;
        a2[i0] = i1;
        String[] a3 = this.duplicateLabels;
        String s = a3[i];
        String[] a4 = this.duplicateLabels;
        String[] a5 = this.duplicateLabels;
        String s0 = a5[i0];
        a4[i] = s0;
        String[] a6 = this.duplicateLabels;
        a6[i0] = s;
        if(i != i0)
        {
            java.util.Vector a7 = this.swapRequests;
            com.cim.AIA.SwapRequest a8 = new com.cim.AIA.SwapRequest(i, i0);
            a7.addElement((Object)a8);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("SelectionSort.0");
        SelectionSort.ARRAY_COLOR = s;
        String s0 = localization.Messages.getString("SelectionSort.1");
        SelectionSort.FINISHED_ARRAY_COLOR = s0;
    }
}