public class SelectionSortCanvas extends com.cim.AIA.AlgorithmCanvas {
    final private static long serialVersionUID = 6116512715591967439L;
    final protected static String I_MARKER = "i";
    final protected static String J_MARKER = "j";
    final protected static String MIN_MARKER;
    protected SelectionSort selectionSort;
    protected com.cim.AIA.ElementArray elementArray;
    protected com.cim.AIA.StringMarker iMarker;
    protected com.cim.AIA.StringMarker jMarker;
    protected com.cim.AIA.Line line;
    protected java.awt.Color backgroundColor;
    protected java.awt.Color textColor;
    protected boolean needInitialisation;
    
    public SelectionSortCanvas()
    {
        super();
        java.awt.Color a = java.awt.Color.white;
        this.backgroundColor = a;
        java.awt.Color a0 = java.awt.Color.black;
        this.textColor = a0;
        this.needInitialisation = false;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        int i = this.needInitialisation?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                SelectionSort a0 = this.selectionSort;
                com.cim.AIA.RepaintEvent a1 = new com.cim.AIA.RepaintEvent((Object)this, (Object)a0);
                this.processRepaintEvent(a1);
                this.needInitialisation = false;
                break label1;
            }
            SelectionSort a2 = this.selectionSort;
            label2: {
                if(a2 == null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a3 = this.elementArray;
                a3.draw(a);
                com.cim.AIA.StringMarker a4 = this.iMarker;
                if(a4 != null)
                {
                    com.cim.AIA.StringMarker a5 = this.iMarker;
                    a5.draw(a);
                }
                com.cim.AIA.StringMarker a6 = this.jMarker;
                if(a6 != null)
                {
                    com.cim.AIA.StringMarker a7 = this.jMarker;
                    a7.draw(a);
                }
                com.cim.AIA.Line a8 = this.line;
                if(a8 != null)
                {
                    com.cim.AIA.Line a9 = this.line;
                    a9.draw(a);
                }
            }
        }
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
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
            if(i0 != 0)
            {
                java.awt.Color a1 = a.getColor();
                this.textColor = a1;
            }
        }
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    protected void initialise()
    {
        java.awt.Dimension a = this.getSize();
        com.cim.AIA.ElementArray a0 = this.elementArray;
        int i = a0.getWidth();
        int i0 = i + 50;
        a.width = i0;
        this.setSize(a);
        com.cim.AIA.ElementArray a1 = this.elementArray;
        java.awt.Dimension a2 = this.getSize();
        int i1 = a2.width;
        int i2 = i1 / 2;
        com.cim.AIA.ElementArray a3 = this.elementArray;
        int i3 = a3.getWidth();
        int i4 = i3 / 2;
        int i5 = i2 - i4;
        a1.setLocation(i5, 200);
        SelectionSort a4 = this.selectionSort;
        int i6 = a4.getI();
        com.cim.AIA.StringMarker a5 = this.initialiseMarker(i6, "i", 3);
        this.iMarker = a5;
        SelectionSort a6 = this.selectionSort;
        int i7 = a6.getJ();
        com.cim.AIA.StringMarker a7 = this.initialiseMarker(i7, "j", 4);
        this.jMarker = a7;
        SelectionSort a8 = this.selectionSort;
        int i8 = a8.getMinPosition();
        com.cim.AIA.Line a9 = this.initialiseLine(i8);
        this.line = a9;
    }
    
    protected com.cim.AIA.Line initialiseLine(int i)
    {
        com.cim.AIA.Line a = null;
        label2: {
            com.cim.AIA.VerticalBar a0 = null;
            label1: {
                label0: {
                    if(i == -10)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a1 = this.elementArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a2;
                    a0 = (com.cim.AIA.VerticalBar)a2;
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.awt.Point a3 = a0.getPosition();
            int i0 = a3.x;
            int i1 = a0.getWidth();
            int i2 = i1 / 2;
            int i3 = i0 + i2;
            java.awt.Point a4 = a0.getPosition();
            int i4 = a4.y;
            int i5 = a0.getHeight();
            int i6 = i4 - i5;
            java.awt.Point a5 = new java.awt.Point(i3, i6);
            java.awt.Point a6 = a0.getPosition();
            int i7 = a6.x;
            int i8 = a0.getWidth();
            int i9 = i8 / 2;
            int i10 = i7 + i9;
            java.awt.Point a7 = new java.awt.Point(i10, 50);
            com.cim.AIA.Line a8 = new com.cim.AIA.Line(a7, a5);
            a8.showArrowHead(true);
            a8.setDistanceFromStartForArrowHead(-3);
            String s = SelectionSortCanvas.MIN_MARKER;
            a8.setLabel(s);
            a8.setDistanceFromStartForLabel(-1);
            java.awt.Graphics a9 = this.getGraphics();
            java.awt.FontMetrics a10 = a9.getFontMetrics();
            String s0 = SelectionSortCanvas.MIN_MARKER;
            int i11 = a10.stringWidth(s0);
            int i12 = -1 * i11;
            int i13 = i12 / 2;
            a8.setXLabelOffset(i13);
            java.awt.Graphics a11 = this.getGraphics();
            java.awt.FontMetrics a12 = a11.getFontMetrics();
            int i14 = a12.getHeight();
            int i15 = -1 * i14;
            a8.setYLabelOffset(i15);
            a = a8;
        }
        return a;
    }
    
    protected com.cim.AIA.StringMarker initialiseMarker(int i, String s, int i0)
    {
        com.cim.AIA.StringMarker a = null;
        label2: {
            com.cim.AIA.VerticalBar a0 = null;
            label1: {
                label0: {
                    if(i == -10)
                    {
                        break label0;
                    }
                    com.cim.AIA.ElementArray a1 = this.elementArray;
                    com.cim.AIA.Element a2 = a1.getElement(i);
                    com.cim.AIA.VerticalBar dummy = (com.cim.AIA.VerticalBar)a2;
                    a0 = (com.cim.AIA.VerticalBar)a2;
                    if(a0 != null)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.awt.Point a3 = a0.getPosition();
            int i1 = a3.x;
            int i2 = a0.getWidth();
            int i3 = i2 / 2;
            int i4 = i1 + i3;
            java.awt.Point a4 = a0.getPosition();
            int i5 = a4.y;
            int i6 = i5 + 5;
            java.awt.Graphics a5 = this.getGraphics();
            java.awt.FontMetrics a6 = a5.getFontMetrics();
            int i7 = a6.getHeight();
            int i8 = i0 * i7;
            int i9 = i6 + i8;
            java.awt.Point a7 = new java.awt.Point(i4, i9);
            com.cim.AIA.StringMarker a8 = new com.cim.AIA.StringMarker(s, a7);
            java.awt.Color a9 = this.textColor;
            a8.setColor(a9);
            java.awt.Color a10 = this.backgroundColor;
            a8.setBackgroundColor(a10);
            a = a8;
        }
        return a;
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    Object a1 = a.paramObj;
                    SelectionSort dummy = (SelectionSort)a1;
                    SelectionSort a2 = (SelectionSort)a1;
                    this.selectionSort = a2;
                    this.iMarker = null;
                    this.jMarker = null;
                    this.line = null;
                    this.removeAllSelectables();
                    java.awt.Graphics a3 = this.getGraphics();
                    if(a3 == null)
                    {
                        break label1;
                    }
                    SelectionSort a4 = this.selectionSort;
                    java.awt.Graphics a5 = this.getGraphics();
                    com.cim.AIA.ElementArray a6 = a4.getElementArray(a5);
                    this.elementArray = a6;
                    com.cim.AIA.ElementArray a7 = this.elementArray;
                    this.addSelectable((com.cim.AIA.Selectable)a7);
                    this.initialise();
                    SelectionSort a8 = this.selectionSort;
                    com.cim.AIA.ElementArray a9 = this.elementArray;
                    int i = this.numberOfTweenSteps;
                    com.cim.AIA.MultipleTween a10 = a8.generateTweens((com.cim.AIA.Paintable)this, (Object)a9, i);
                    this.addTween((com.cim.AIA.Tween)a10);
                    com.cim.AIA.MultipleTween a11 = this.tweens;
                    int i0 = a11.getNumberOfTweens();
                    if(i0 > 0)
                    {
                        com.cim.AIA.MultipleTween a12 = this.tweens;
                        a12.run();
                    }
                    SelectionSort a13 = this.selectionSort;
                    a13.removeAllAnimationRequests();
                    this.repaint();
                }
                break label2;
            }
            this.needInitialisation = true;
        }
    }
    
    static
    {
        String s = localization.Messages.getString("SelectionSortCanvas.2");
        SelectionSortCanvas.MIN_MARKER = s;
    }
}