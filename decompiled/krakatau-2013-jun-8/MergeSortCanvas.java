public class MergeSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    protected java.awt.Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndMergeSort;
    protected int spacingBetweenInformationAndMergeSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected MergeSort mergeSort;
    protected MyElementArray elementArray;
    protected java.util.Vector childElements;
    protected int iWidth;
    protected int jWidth;
    protected java.awt.Point iPoint;
    protected java.awt.Point jPoint;
    protected int pValue;
    protected boolean pValueReady;
    protected java.awt.Point activeLeft;
    protected java.awt.Point activeRight;
    protected java.awt.Point oldIPoint;
    protected java.awt.Point oldJPoint;
    protected boolean tweening;
    
    public MergeSortCanvas()
    {
        super();
        java.awt.Color a = java.awt.Color.black;
        this.textColor = a;
        this.xBuffer = 60;
        this.yBuffer = 80;
        this.gapBetweenArrayAndMergeSort = 120;
        this.spacingBetweenInformationAndMergeSort = 10;
        this.boxWidth = 20;
        this.activeYBuffer = 5;
        this.iWidth = 0;
        this.jWidth = 0;
        this.iPoint = null;
        this.jPoint = null;
        this.pValue = -1;
        this.pValueReady = false;
        this.activeLeft = null;
        this.activeRight = null;
        this.tweening = false;
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
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        MergeSort a0 = this.mergeSort;
        if(a0 != null)
        {
            java.awt.Graphics2D dummy = (java.awt.Graphics2D)a;
            java.awt.Graphics2D a1 = (java.awt.Graphics2D)a;
            java.awt.RenderingHints$Key a2 = java.awt.RenderingHints.KEY_ANTIALIASING;
            Object a3 = java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
            a1.setRenderingHint(a2, a3);
            java.awt.Graphics2D dummy0 = (java.awt.Graphics2D)a;
            java.awt.Graphics2D a4 = (java.awt.Graphics2D)a;
            java.awt.RenderingHints$Key a5 = java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
            Object a6 = java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
            a4.setRenderingHint(a5, a6);
            java.awt.Graphics a7 = a.create(0, 0, 600, 765);
            MergeSort a8 = this.mergeSort;
            int i = this.xBuffer;
            int i0 = this.yBuffer;
            java.awt.Point a9 = new java.awt.Point(i, i0);
            a8.drawTree(a9, a7);
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        if(a0 != null)
        {
            Object a1 = a.paramObj;
            MergeSort dummy = (MergeSort)a1;
            MergeSort a2 = (MergeSort)a1;
            this.mergeSort = a2;
            this.removeAllSelectables();
            MergeSort a3 = this.mergeSort;
            a3.removeAllAnimationRequests();
            this.tweening = true;
            this.tweening = false;
        }
        this.repaint();
    }
}