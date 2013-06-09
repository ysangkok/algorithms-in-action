public class TransclosureGraphAlgorithmCommon {
    protected boolean m_bOmniDirectional;
    protected int m_nZoomFactor;
    protected boolean m_bInsertionMode;
    protected boolean m_bDeleteMode;
    protected boolean m_bKeyChangeMode;
    protected boolean m_structureShowing;
    protected boolean m_matrixShowing;
    protected int[] data;
    protected aia.graph.common.GraphMaps m_algorithm;
    protected int[][] adj_matrix;
    protected int m_nNumberOfNodes;
    protected aia.graph.common.GraphNode[] adj_structure;
    protected java.util.Vector m_nodeInfo;
    protected static com.cim.common.MessageDialog messageDialog;
    protected int m_editLinkStep;
    protected int m_startNode;
    protected int m_endNode;
    protected Integer m_linkWeight;
    protected boolean m_editLinkStarted;
    
    TransclosureGraphAlgorithmCommon(aia.graph.common.GraphMaps a, Object a0)
    {
        super();
        this.m_bOmniDirectional = true;
        this.m_nZoomFactor = 1;
        this.m_bInsertionMode = false;
        this.m_bDeleteMode = false;
        this.m_bKeyChangeMode = false;
        this.m_structureShowing = true;
        this.m_matrixShowing = true;
        this.adj_matrix = null;
        this.adj_structure = null;
        this.m_editLinkStep = 0;
        this.m_startNode = -10;
        this.m_endNode = -10;
        this.m_linkWeight = null;
        this.m_editLinkStarted = false;
        this.m_algorithm = a;
        int[] dummy = (int[])a0;
        int[] a1 = (int[])a0;
        this.data = a1;
        this.generateNodeInfo();
        this.generateAdjacencyMatrix();
        this.generateAdjacencyStructure();
        this.outputDataArray();
        this.m_structureShowing = true;
        this.m_matrixShowing = true;
    }
    
    public void algorithmStartRunInitialisations()
    {
        this.endEditLink();
        this.setKeyChangeMode(false);
        this.setInsertionMode(false);
        this.setDeleteMode(false);
    }
    
    public void setData(Object a)
    {
        int[] dummy = (int[])a;
        int[] a0 = (int[])a;
        this.data = a0;
        this.generateNodeInfo();
        this.generateAdjacencyMatrix();
        this.generateAdjacencyStructure();
        this.outputDataArray();
    }
    
    public void outputDataArray()
    {
        java.io.PrintStream a = System.out;
        a.println("******Graph Data******");
        int i = 0;
        while(true)
        {
            int[] a0 = this.data;
            int i0 = a0.length;
            if(i >= i0)
            {
                java.io.PrintStream a1 = System.out;
                a1.println();
                java.io.PrintStream a2 = System.out;
                a2.println("----------------------");
                java.io.PrintStream a3 = System.out;
                a3.println();
                return;
            }
            java.io.PrintStream a4 = System.out;
            int[] a5 = this.data;
            int i1 = a5[i];
            a4.print(i1);
            int[] a6 = this.data;
            int i2 = a6.length;
            int i3 = i2 - 1;
            if(i < i3)
            {
                java.io.PrintStream a7 = System.out;
                a7.print(", ");
            }
            int i4 = i + 1;
            i = i4;
        }
    }
    
    public void startEditLink(Integer a)
    {
        aia.graph.common.GraphMaps a0 = this.m_algorithm;
        int i = a0.getIsRunning()?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                break label1;
            }
            this.m_editLinkStarted = true;
            this.m_linkWeight = a;
            this.m_startNode = -10;
            this.m_endNode = -10;
            com.cim.common.MessageDialog a1 = TransclosureGraphAlgorithmCommon.messageDialog;
            if(a1 != null)
            {
                com.cim.common.MessageDialog a2 = TransclosureGraphAlgorithmCommon.messageDialog;
                a2.dispose();
            }
            String s = localization.Messages.getString("TransclosureGraphAlgorithmCommon.3");
            com.cim.common.MessageDialog a3 = new com.cim.common.MessageDialog(s, false, false);
            TransclosureGraphAlgorithmCommon.messageDialog = a3;
            com.cim.common.MessageDialog a4 = TransclosureGraphAlgorithmCommon.messageDialog;
            String s0 = localization.Messages.getString("TransclosureGraphAlgorithmCommon.4");
            a4.setTitle(s0);
            com.cim.common.MessageDialog a5 = TransclosureGraphAlgorithmCommon.messageDialog;
            a5.setVisible(true);
            this.m_editLinkStep = 1;
        }
    }
    
    public void setStartNode(int i)
    {
        this.m_startNode = i;
        com.cim.common.MessageDialog a = TransclosureGraphAlgorithmCommon.messageDialog;
        if(a != null)
        {
            com.cim.common.MessageDialog a0 = TransclosureGraphAlgorithmCommon.messageDialog;
            a0.dispose();
        }
        String s = localization.Messages.getString("TransclosureGraphAlgorithmCommon.5");
        com.cim.common.MessageDialog a1 = new com.cim.common.MessageDialog(s, false, false);
        TransclosureGraphAlgorithmCommon.messageDialog = a1;
        com.cim.common.MessageDialog a2 = TransclosureGraphAlgorithmCommon.messageDialog;
        String s0 = localization.Messages.getString("TransclosureGraphAlgorithmCommon.6");
        a2.setTitle(s0);
        com.cim.common.MessageDialog a3 = TransclosureGraphAlgorithmCommon.messageDialog;
        a3.setVisible(true);
        int i0 = this.m_editLinkStep;
        int i1 = i0 + 1;
        this.m_editLinkStep = i1;
        aia.graph.common.GraphMaps a4 = this.m_algorithm;
        a4.repaint();
    }
    
    public int getStartNode()
    {
        int i = this.m_startNode;
        return i;
    }
    
    public void setEndNode(int i)
    {
        this.m_endNode = i;
        int i0 = this.m_editLinkStep;
        int i1 = i0 + 1;
        this.m_editLinkStep = i1;
        int i2 = this.needPromptForLinkWeight()?1:0;
        if(i2 == 0)
        {
            Integer a = this.m_linkWeight;
            int i3 = a.intValue();
            this.setLinkWeight(i3);
        }
        aia.graph.common.GraphMaps a0 = this.m_algorithm;
        a0.repaint();
    }
    
    public int getEndNode()
    {
        int i = this.m_endNode;
        return i;
    }
    
    public int getCurrentLinkLinkWeight()
    {
        int[][] a = this.adj_matrix;
        int i = this.m_startNode;
        int[] a0 = a[i];
        int i0 = this.m_endNode;
        int i1 = a0[i0];
        return i1;
    }
    
    public void setLinkWeight(int i)
    {
        int i0 = this.getCurrentLinkLinkWeight();
        Integer a = new Integer(i0);
        aia.graph.common.GraphMapsNode a0 = new aia.graph.common.GraphMapsNode((Object)a, 0, 0);
        int i1 = this.m_startNode;
        a0.setFrom(i1);
        int i2 = this.m_endNode;
        a0.setTo(i2);
        this.editLink(a0, i);
        this.endEditLink();
    }
    
    public void endEditLink()
    {
        this.m_editLinkStarted = false;
        this.m_startNode = -10;
        this.m_endNode = -10;
        com.cim.common.MessageDialog a = TransclosureGraphAlgorithmCommon.messageDialog;
        if(a != null)
        {
            com.cim.common.MessageDialog a0 = TransclosureGraphAlgorithmCommon.messageDialog;
            a0.dispose();
        }
        TransclosureGraphAlgorithmCommon.messageDialog = null;
        aia.graph.common.GraphMaps a1 = this.m_algorithm;
        a1.repaint();
    }
    
    public boolean needPromptForLinkWeight()
    {
        Integer a = this.m_linkWeight;
        int i = a != null?0:1;
        return i != 0;
    }
    
    public boolean editLinkStarted()
    {
        int i = this.m_editLinkStarted?1:0;
        return i != 0;
    }
    
    public int getEditLinkStep()
    {
        int i = this.m_editLinkStep;
        return i;
    }
    
    public void setStructureShowing(boolean b)
    {
        this.m_structureShowing = b;
        aia.graph.common.GraphMaps a = this.m_algorithm;
        a.repaint();
    }
    
    public void setMatrixShowing(boolean b)
    {
        this.m_matrixShowing = b;
        aia.graph.common.GraphMaps a = this.m_algorithm;
        a.repaint();
    }
    
    public boolean getStructureShowing()
    {
        int i = this.m_structureShowing?1:0;
        return i != 0;
    }
    
    public boolean getMatrixShowing()
    {
        int i = this.m_matrixShowing?1:0;
        return i != 0;
    }
    
    public void generateAdjacencyMatrix()
    {
        int i = this.m_nNumberOfNodes;
        int i0 = this.m_nNumberOfNodes;
        int[][] a = new int[i][i0];
        this.adj_matrix = a;
        int i1 = 0;
        while(true)
        {
            int i2 = this.m_nNumberOfNodes;
            if(i1 >= i2)
            {
                break;
            }
            int i3 = 0;
            while(true)
            {
                int i4 = this.m_nNumberOfNodes;
                if(i3 >= i4)
                {
                    int i5 = i1 + 1;
                    i1 = i5;
                }
                else
                {
                    int[][] a0 = this.adj_matrix;
                    int[] a1 = a0[i1];
                    a1[i3] = 0;
                    int i6 = i3 + 1;
                    i3 = i6;
                }
            }
        }
        int i7 = this.m_nNumberOfNodes;
        int i8 = i7 * 2;
        int i9 = i8 + 1;
        int i10 = i9;
        while(true)
        {
            int i11 = i10 + 2;
            int[] a2 = this.data;
            int i12 = a2.length;
            if(i11 >= i12)
            {
                return;
            }
            int[] a3 = this.data;
            int i13 = a3[i10];
            int[] a4 = this.data;
            int i14 = i10 + 1;
            int i15 = a4[i14];
            int[] a5 = this.data;
            int i16 = i10 + 2;
            int i17 = a5[i16];
            int[][] a6 = this.adj_matrix;
            int[] a7 = a6[i13];
            a7[i15] = i17;
            int i18 = this.m_bOmniDirectional?1:0;
            if(i18 != 0)
            {
                int[][] a8 = this.adj_matrix;
                int[] a9 = a8[i15];
                a9[i13] = i17;
            }
            int i19 = i10 + 3;
            i10 = i19;
        }
    }
    
    public void generateAdjacencyStructure()
    {
        int i = this.m_nNumberOfNodes;
        aia.graph.common.GraphNode[] a = new aia.graph.common.GraphNode[i];
        this.adj_structure = a;
        int i0 = 0;
        while(true)
        {
            int i1 = this.m_nNumberOfNodes;
            if(i0 >= i1)
            {
                break;
            }
            else
            {
                aia.graph.common.GraphNode a0 = new aia.graph.common.GraphNode();
                a0.setNext(a0);
                a0.setKey(-1);
                aia.graph.common.GraphNode[] a1 = this.adj_structure;
                a1[i0] = a0;
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
        int i3 = 0;
        label0: while(true)
        {
            int i4 = this.m_nNumberOfNodes;
            if(i3 >= i4)
            {
                return;
            }
            int i5 = 0;
            while(true)
            {
                int i6 = this.m_nNumberOfNodes;
                if(i5 >= i6)
                {
                    int i7 = i3 + 1;
                    i3 = i7;
                    continue label0;
                }
                label1: {
                    if(i5 == i3)
                    {
                        break label1;
                    }
                    int[][] a2 = this.adj_matrix;
                    int[] a3 = a2[i3];
                    int i8 = a3[i5];
                    if(i8 != 0)
                    {
                        aia.graph.common.GraphNode a4 = new aia.graph.common.GraphNode();
                        a4.setKey(i5);
                        aia.graph.common.GraphNode[] a5 = this.adj_structure;
                        aia.graph.common.GraphNode a6 = a5[i3];
                        a4.setNext(a6);
                        aia.graph.common.GraphNode[] a7 = this.adj_structure;
                        a7[i3] = a4;
                    }
                }
                int i9 = i5 + 1;
                i5 = i9;
            }
        }
    }
    
    public void setNodePosition(int i, int i0, int i1)
    {
        int i2 = this.m_nZoomFactor;
        int i3 = i0 / i2;
        int i4 = this.m_nZoomFactor;
        int i5 = i1 / i4;
        int i6 = this.m_nZoomFactor;
        int i7 = i6 * 5;
        int i8 = i0 / i7;
        double d = (double)i8;
        double d0 = Math.floor(d);
        double d1 = d0 * 5.0;
        int i9 = (int)d1;
        int i10 = this.m_nZoomFactor;
        int i11 = i10 * 5;
        int i12 = i1 / i11;
        double d2 = (double)i12;
        double d3 = Math.floor(d2);
        double d4 = d3 * 5.0;
        int i13 = (int)d4;
        aia.graph.common.NodeInfo a = this.findNodeInfo(i);
        if(a != null)
        {
            a.setX(i9);
            a.setY(i13);
            int[] a0 = this.data;
            int i14 = a.getKey();
            int i15 = i14 * 2;
            int i16 = i15 + 1;
            a0[i16] = i9;
            int[] a1 = this.data;
            int i17 = a.getKey();
            int i18 = i17 * 2;
            int i19 = i18 + 2;
            a1[i19] = i13;
            aia.graph.common.GraphMaps a2 = this.m_algorithm;
            a2.repaint();
        }
    }
    
    public void setOmniDirectional(boolean b)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        int i = b?1:0;
        int i0 = a.getIsRunning()?1:0;
        if(i0 == 0)
        {
            this.m_bOmniDirectional = i != 0;
            int[] a0 = this.data;
            this.setData((Object)a0);
            aia.graph.common.GraphMaps a1 = this.m_algorithm;
            a1.repaint();
        }
    }
    
    public void zoomIn()
    {
        int i = this.m_nZoomFactor;
        if(i != 10)
        {
            int i0 = this.m_nZoomFactor;
            int i1 = i0 + 1;
            this.m_nZoomFactor = i1;
            aia.graph.common.GraphMaps a = this.m_algorithm;
            a.repaint();
        }
    }
    
    public void zoomOut()
    {
        int i = this.m_nZoomFactor;
        if(i != 1)
        {
            int i0 = this.m_nZoomFactor;
            int i1 = i0 - 1;
            this.m_nZoomFactor = i1;
            aia.graph.common.GraphMaps a = this.m_algorithm;
            a.repaint();
        }
    }
    
    public void setKeyChangeMode(boolean b)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        int i = b?1:0;
        int i0 = a.getIsRunning()?1:0;
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                break label1;
            }
            this.m_bKeyChangeMode = i != 0;
            com.cim.common.MessageDialog a0 = TransclosureGraphAlgorithmCommon.messageDialog;
            if(a0 != null)
            {
                com.cim.common.MessageDialog a1 = TransclosureGraphAlgorithmCommon.messageDialog;
                a1.dispose();
            }
            int i1 = this.m_bKeyChangeMode?1:0;
            if(i1 != 0)
            {
                String s = localization.Messages.getString("TransclosureGraphAlgorithmCommon.7");
                com.cim.common.MessageDialog a2 = new com.cim.common.MessageDialog(s, false, false);
                TransclosureGraphAlgorithmCommon.messageDialog = a2;
                com.cim.common.MessageDialog a3 = TransclosureGraphAlgorithmCommon.messageDialog;
                String s0 = localization.Messages.getString("TransclosureGraphAlgorithmCommon.8");
                a3.setTitle(s0);
                com.cim.common.MessageDialog a4 = TransclosureGraphAlgorithmCommon.messageDialog;
                a4.setVisible(true);
            }
        }
    }
    
    public boolean getKeyChangeMode()
    {
        int i = this.m_bKeyChangeMode?1:0;
        return i != 0;
    }
    
    public void keyChange(int i, int i0)
    {
        int i1 = this.m_nNumberOfNodes;
        label2: {
            label1: {
                label0: {
                    if(i0 >= i1)
                    {
                        break label0;
                    }
                    if(i0 >= 0)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            int[] a = this.data;
            int i2 = i * 2;
            int i3 = i2 + 1;
            int i4 = a[i3];
            int[] a0 = this.data;
            int i5 = i * 2;
            int i6 = i5 + 2;
            int i7 = a0[i6];
            int[] a1 = this.data;
            int i8 = i * 2;
            int i9 = i8 + 1;
            int[] a2 = this.data;
            int i10 = i0 * 2;
            int i11 = i10 + 1;
            int i12 = a2[i11];
            a1[i9] = i12;
            int[] a3 = this.data;
            int i13 = i * 2;
            int i14 = i13 + 2;
            int[] a4 = this.data;
            int i15 = i0 * 2;
            int i16 = i15 + 2;
            int i17 = a4[i16];
            a3[i14] = i17;
            int[] a5 = this.data;
            int i18 = i0 * 2;
            int i19 = i18 + 1;
            a5[i19] = i4;
            int[] a6 = this.data;
            int i20 = i0 * 2;
            int i21 = i20 + 2;
            a6[i21] = i7;
            int i22 = this.m_nNumberOfNodes;
            int i23 = i22 * 2;
            int i24 = i23 + 1;
            int i25 = i24;
            while(true)
            {
                int i26 = i25 + 2;
                int[] a7 = this.data;
                int i27 = a7.length;
                if(i26 >= i27)
                {
                    break;
                }
                int[] a8 = this.data;
                int i28 = a8[i25];
                label4: {
                    label3: {
                        if(i28 != i)
                        {
                            break label3;
                        }
                        int[] a9 = this.data;
                        a9[i25] = i0;
                        break label4;
                    }
                    int[] a10 = this.data;
                    int i29 = a10[i25];
                    if(i29 == i0)
                    {
                        int[] a11 = this.data;
                        a11[i25] = i;
                    }
                }
                int[] a12 = this.data;
                int i30 = i25 + 1;
                int i31 = a12[i30];
                label6: {
                    label5: {
                        if(i31 != i)
                        {
                            break label5;
                        }
                        int[] a13 = this.data;
                        int i32 = i25 + 1;
                        a13[i32] = i0;
                        break label6;
                    }
                    int[] a14 = this.data;
                    int i33 = i25 + 1;
                    int i34 = a14[i33];
                    if(i34 == i0)
                    {
                        int[] a15 = this.data;
                        int i35 = i25 + 1;
                        a15[i35] = i;
                    }
                }
                int i36 = i25 + 3;
                i25 = i36;
            }
            int[] a16 = this.data;
            this.setData((Object)a16);
            aia.graph.common.GraphMaps a17 = this.m_algorithm;
            a17.repaint();
        }
    }
    
    public void setInsertionMode(boolean b)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        int i = b?1:0;
        int i0 = a.getIsRunning()?1:0;
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                break label1;
            }
            this.m_bInsertionMode = i != 0;
            com.cim.common.MessageDialog a0 = TransclosureGraphAlgorithmCommon.messageDialog;
            if(a0 != null)
            {
                com.cim.common.MessageDialog a1 = TransclosureGraphAlgorithmCommon.messageDialog;
                a1.dispose();
            }
            int i1 = this.m_bInsertionMode?1:0;
            if(i1 != 0)
            {
                String s = localization.Messages.getString("TransclosureGraphAlgorithmCommon.9");
                com.cim.common.MessageDialog a2 = new com.cim.common.MessageDialog(s, false, false);
                TransclosureGraphAlgorithmCommon.messageDialog = a2;
                com.cim.common.MessageDialog a3 = TransclosureGraphAlgorithmCommon.messageDialog;
                String s0 = localization.Messages.getString("TransclosureGraphAlgorithmCommon.10");
                a3.setTitle(s0);
                com.cim.common.MessageDialog a4 = TransclosureGraphAlgorithmCommon.messageDialog;
                a4.setVisible(true);
            }
        }
    }
    
    public boolean getInsertionMode()
    {
        int i = this.m_bInsertionMode?1:0;
        return i != 0;
    }
    
    public void insertNewNode(int i, int i0)
    {
        int i1 = this.m_nZoomFactor;
        int i2 = i / i1;
        int i3 = this.m_nZoomFactor;
        int i4 = i0 / i3;
        int i5 = this.m_nZoomFactor;
        int i6 = i5 * 5;
        int i7 = i / i6;
        double d = (double)i7;
        double d0 = Math.floor(d);
        double d1 = d0 * 5.0;
        int i8 = (int)d1;
        int i9 = this.m_nZoomFactor;
        int i10 = i9 * 5;
        int i11 = i0 / i10;
        double d2 = (double)i11;
        double d3 = Math.floor(d2);
        double d4 = d3 * 5.0;
        int i12 = (int)d4;
        java.util.Vector a = this.m_nodeInfo;
        if(a == null)
        {
            java.util.Vector a0 = new java.util.Vector();
            this.m_nodeInfo = a0;
            this.m_nNumberOfNodes = 0;
            int[] a1 = new int[1];
            this.data = a1;
            int[] a2 = this.data;
            a2[0] = 0;
        }
        java.util.Vector a3 = this.m_nodeInfo;
        int i13 = this.m_nNumberOfNodes;
        int i14 = this.m_nZoomFactor;
        int i15 = i / i14;
        int i16 = this.m_nZoomFactor;
        int i17 = i0 / i16;
        aia.graph.common.NodeInfo a4 = new aia.graph.common.NodeInfo(i13, i15, i17);
        a3.addElement((Object)a4);
        int[] a5 = this.data;
        int i18 = a5.length;
        int i19 = i18 + 2;
        int[] a6 = new int[i19];
        int i20 = 0;
        while(true)
        {
            int i21 = this.m_nNumberOfNodes;
            if(i20 >= i21)
            {
                break;
            }
            else
            {
                int i22 = i20 * 2;
                int i23 = i22 + 1;
                int[] a7 = this.data;
                int i24 = i20 * 2;
                int i25 = i24 + 1;
                int i26 = a7[i25];
                a6[i23] = i26;
                int i27 = i20 * 2;
                int i28 = i27 + 2;
                int[] a8 = this.data;
                int i29 = i20 * 2;
                int i30 = i29 + 2;
                int i31 = a8[i30];
                a6[i28] = i31;
                int i32 = i20 + 1;
                i20 = i32;
            }
        }
        int i33 = this.m_nNumberOfNodes;
        int i34 = i33 * 2;
        int i35 = i34 + 1;
        a6[i35] = i8;
        int i36 = this.m_nNumberOfNodes;
        int i37 = i36 * 2;
        int i38 = i37 + 2;
        a6[i38] = i12;
        int i39 = this.m_nNumberOfNodes;
        int i40 = i39 * 2;
        int i41 = i40 + 1;
        int i42 = i41;
        while(true)
        {
            int[] a9 = this.data;
            int i43 = a9.length;
            if(i42 >= i43)
            {
                break;
            }
            else
            {
                int i44 = i42 + 2;
                int[] a10 = this.data;
                int i45 = a10[i42];
                a6[i44] = i45;
                int i46 = i42 + 1;
                i42 = i46;
            }
        }
        int i47 = this.m_nNumberOfNodes;
        int i48 = i47 + 1;
        this.m_nNumberOfNodes = i48;
        int i49 = this.m_nNumberOfNodes;
        a6[0] = i49;
        int i50 = a6.length;
        int[] a11 = new int[i50];
        this.data = a11;
        int i51 = 0;
        while(true)
        {
            int i52 = a6.length;
            if(i51 >= i52)
            {
                int[] a12 = this.data;
                this.setData((Object)a12);
                aia.graph.common.GraphMaps a13 = this.m_algorithm;
                a13.repaint();
                return;
            }
            else
            {
                int[] a14 = this.data;
                int i53 = a6[i51];
                a14[i51] = i53;
                int i54 = i51 + 1;
                i51 = i54;
            }
        }
    }
    
    public void setDeleteMode(boolean b)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        int i = b?1:0;
        int i0 = a.getIsRunning()?1:0;
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                break label1;
            }
            this.m_bDeleteMode = i != 0;
            com.cim.common.MessageDialog a0 = TransclosureGraphAlgorithmCommon.messageDialog;
            if(a0 != null)
            {
                com.cim.common.MessageDialog a1 = TransclosureGraphAlgorithmCommon.messageDialog;
                a1.dispose();
            }
            int i1 = this.m_bDeleteMode?1:0;
            if(i1 != 0)
            {
                String s = localization.Messages.getString("TransclosureGraphAlgorithmCommon.11");
                com.cim.common.MessageDialog a2 = new com.cim.common.MessageDialog(s, false, false);
                TransclosureGraphAlgorithmCommon.messageDialog = a2;
                com.cim.common.MessageDialog a3 = TransclosureGraphAlgorithmCommon.messageDialog;
                String s0 = localization.Messages.getString("TransclosureGraphAlgorithmCommon.12");
                a3.setTitle(s0);
                com.cim.common.MessageDialog a4 = TransclosureGraphAlgorithmCommon.messageDialog;
                a4.setVisible(true);
            }
        }
    }
    
    public boolean getDeleteMode()
    {
        int i = this.m_bDeleteMode?1:0;
        return i != 0;
    }
    
    public void deleteNode(int i)
    {
        aia.graph.common.GraphMaps a = this.m_algorithm;
        int i0 = a.getIsRunning()?1:0;
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                break label1;
            }
            java.util.Vector a0 = new java.util.Vector();
            int i1 = this.m_nNumberOfNodes;
            label3: {
                label2: {
                    if(i1 == 0)
                    {
                        break label2;
                    }
                    int i2 = this.m_nNumberOfNodes;
                    if(i >= i2)
                    {
                        break label2;
                    }
                    if(i >= 0)
                    {
                        break label3;
                    }
                }
                break label1;
            }
            aia.graph.common.NodeInfo a1 = this.findNodeInfo(i);
            java.util.Vector a2 = this.m_nodeInfo;
            int i3 = a2.removeElement((Object)a1)?1:0;
            int[] a3 = this.data;
            int i4 = a3.length;
            int i5 = i4 - 2;
            int[] a4 = new int[i5];
            int i6 = 0;
            while(true)
            {
                int i7 = this.m_nNumberOfNodes;
                if(i6 >= i7)
                {
                    break;
                }
                label4: {
                    if(i6 == i)
                    {
                        break label4;
                    }
                    if(i6 <= i)
                    {
                        int i8 = i6 * 2;
                        int i9 = i8 + 1;
                        int[] a5 = this.data;
                        int i10 = i6 * 2;
                        int i11 = i10 + 1;
                        int i12 = a5[i11];
                        a4[i9] = i12;
                        int i13 = i6 * 2;
                        int i14 = i13 + 2;
                        int[] a6 = this.data;
                        int i15 = i6 * 2;
                        int i16 = i15 + 2;
                        int i17 = a6[i16];
                        a4[i14] = i17;
                    }
                    else
                    {
                        int i18 = i6 * 2;
                        int i19 = i18 - 1;
                        int[] a7 = this.data;
                        int i20 = i6 * 2;
                        int i21 = i20 + 1;
                        int i22 = a7[i21];
                        a4[i19] = i22;
                        int i23 = i6 * 2;
                        int[] a8 = this.data;
                        int i24 = i6 * 2;
                        int i25 = i24 + 2;
                        int i26 = a8[i25];
                        a4[i23] = i26;
                    }
                }
                int i27 = i6 + 1;
                i6 = i27;
            }
            int i28 = this.m_nNumberOfNodes;
            int i29 = i28 * 2;
            int i30 = i29 + 1;
            int i31 = i30;
            while(true)
            {
                int[] a9 = this.data;
                int i32 = a9.length;
                if(i31 >= i32)
                {
                    break;
                }
                else
                {
                    int i33 = i31 - 2;
                    int[] a10 = this.data;
                    int i34 = a10[i31];
                    a4[i33] = i34;
                    int i35 = i31 + 1;
                    i31 = i35;
                }
            }
            int i36 = this.m_nNumberOfNodes;
            int i37 = i36 - 1;
            a4[0] = i37;
            int i38 = a4.length;
            int[] a11 = new int[i38];
            this.data = a11;
            int i39 = 0;
            while(true)
            {
                int i40 = a4.length;
                if(i39 >= i40)
                {
                    break;
                }
                else
                {
                    int[] a12 = this.data;
                    int i41 = a4[i39];
                    a12[i39] = i41;
                    int i42 = i39 + 1;
                    i39 = i42;
                }
            }
            int i43 = this.m_nNumberOfNodes;
            int i44 = i43 - 1;
            this.m_nNumberOfNodes = i44;
            int i45 = this.m_nNumberOfNodes;
            int i46 = i45 * 2;
            int i47 = i46 + 1;
            int i48 = i47;
            while(true)
            {
                int i49 = i48 + 2;
                int[] a13 = this.data;
                int i50 = a13.length;
                if(i49 >= i50)
                {
                    break;
                }
                int[] a14 = this.data;
                int i51 = a14[i48];
                label5: {
                    if(i51 == i)
                    {
                        break label5;
                    }
                    int[] a15 = this.data;
                    int i52 = i48 + 1;
                    int i53 = a15[i52];
                    if(i53 == i)
                    {
                        break label5;
                    }
                    int[] a16 = this.data;
                    int i54 = a16[i48];
                    if(i54 <= i)
                    {
                        int[] a17 = this.data;
                        int i55 = a17[i48];
                        Integer a18 = new Integer(i55);
                        a0.addElement((Object)a18);
                    }
                    else
                    {
                        int[] a19 = this.data;
                        int i56 = a19[i48];
                        int i57 = i56 - 1;
                        Integer a20 = new Integer(i57);
                        a0.addElement((Object)a20);
                    }
                    int[] a21 = this.data;
                    int i58 = i48 + 1;
                    int i59 = a21[i58];
                    if(i59 <= i)
                    {
                        int[] a22 = this.data;
                        int i60 = i48 + 1;
                        int i61 = a22[i60];
                        Integer a23 = new Integer(i61);
                        a0.addElement((Object)a23);
                    }
                    else
                    {
                        int[] a24 = this.data;
                        int i62 = i48 + 1;
                        int i63 = a24[i62];
                        int i64 = i63 - 1;
                        Integer a25 = new Integer(i64);
                        a0.addElement((Object)a25);
                    }
                    int[] a26 = this.data;
                    int i65 = i48 + 2;
                    int i66 = a26[i65];
                    Integer a27 = new Integer(i66);
                    a0.addElement((Object)a27);
                }
                int i67 = i48 + 3;
                i48 = i67;
            }
            int i68 = this.m_nNumberOfNodes;
            int i69 = i68 * 2;
            int i70 = i69 + 1;
            int i71 = a0.size();
            int i72 = i70 + i71;
            int[] a28 = new int[i72];
            int i73 = 0;
            while(true)
            {
                int i74 = this.m_nNumberOfNodes;
                int i75 = i74 * 2;
                int i76 = i75 + 1;
                if(i73 >= i76)
                {
                    break;
                }
                else
                {
                    int[] a29 = this.data;
                    int i77 = a29[i73];
                    a28[i73] = i77;
                    int i78 = i73 + 1;
                    i73 = i78;
                }
            }
            int i79 = 0;
            while(true)
            {
                int i80 = a0.size();
                if(i79 >= i80)
                {
                    break;
                }
                else
                {
                    Object a30 = a0.elementAt(i79);
                    Integer dummy = (Integer)a30;
                    Integer a31 = (Integer)a30;
                    int i81 = this.m_nNumberOfNodes;
                    int i82 = i81 * 2;
                    int i83 = i82 + 1;
                    int i84 = i83 + i79;
                    int i85 = a31.intValue();
                    a28[i84] = i85;
                    int i86 = i79 + 1;
                    i79 = i86;
                }
            }
            int i87 = a28.length;
            int[] a32 = new int[i87];
            this.data = a32;
            int i88 = 0;
            while(true)
            {
                int i89 = a28.length;
                if(i88 >= i89)
                {
                    break;
                }
                else
                {
                    int[] a33 = this.data;
                    int i90 = a28[i88];
                    a33[i88] = i90;
                    int i91 = i88 + 1;
                    i88 = i91;
                }
            }
            int[] a34 = this.data;
            this.setData((Object)a34);
            aia.graph.common.GraphMaps a35 = this.m_algorithm;
            a35.repaint();
        }
    }
    
    public void editLink(aia.graph.common.GraphMapsNode a, int i)
    {
        int i0 = a.getIntValue();
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                if(i <= 0)
                {
                    break label1;
                }
                int[] a0 = this.data;
                int i1 = a0.length;
                int i2 = i1 + 3;
                int[] a1 = new int[i2];
                int i3 = 0;
                while(true)
                {
                    int[] a2 = this.data;
                    int i4 = a2.length;
                    if(i3 >= i4)
                    {
                        break;
                    }
                    else
                    {
                        int[] a3 = this.data;
                        int i5 = a3[i3];
                        a1[i3] = i5;
                        int i6 = i3 + 1;
                        i3 = i6;
                        continue;
                    }
                }
                int[] a4 = this.data;
                int i7 = a4.length;
                int i8 = a.getFrom();
                a1[i7] = i8;
                int[] a5 = this.data;
                int i9 = a5.length;
                int i10 = i9 + 1;
                int i11 = a.getTo();
                a1[i10] = i11;
                int[] a6 = this.data;
                int i12 = a6.length;
                int i13 = i12 + 2;
                a1[i13] = i;
                this.data = a1;
                int[] a7 = this.data;
                this.setData((Object)a7);
                break label1;
            }
            label2: {
                if(i <= 0)
                {
                    break label2;
                }
                int i14 = this.m_nNumberOfNodes;
                int i15 = i14 * 2;
                int i16 = i15 + 1;
                int i17 = i16;
                while(true)
                {
                    int i18 = i17 + 2;
                    int[] a8 = this.data;
                    int i19 = a8.length;
                    if(i18 >= i19)
                    {
                        break;
                    }
                    int[] a9 = this.data;
                    int i20 = a9[i17];
                    int i21 = a.getFrom();
                    label4: {
                        label3: {
                            if(i20 != i21)
                            {
                                break label3;
                            }
                            int[] a10 = this.data;
                            int i22 = i17 + 1;
                            int i23 = a10[i22];
                            int i24 = a.getTo();
                            if(i23 != i24)
                            {
                                break label3;
                            }
                            int[] a11 = this.data;
                            int i25 = i17 + 2;
                            a11[i25] = i;
                            break label4;
                        }
                        int i26 = this.m_bOmniDirectional?1:0;
                        if(i26 == 0)
                        {
                            break label4;
                        }
                        int[] a12 = this.data;
                        int i27 = a12[i17];
                        int i28 = a.getTo();
                        if(i27 != i28)
                        {
                            break label4;
                        }
                        int[] a13 = this.data;
                        int i29 = i17 + 1;
                        int i30 = a13[i29];
                        int i31 = a.getFrom();
                        if(i30 == i31)
                        {
                            int[] a14 = this.data;
                            int i32 = i17 + 2;
                            a14[i32] = i;
                        }
                    }
                    int i33 = i17 + 3;
                    i17 = i33;
                    continue;
                }
                int[] a15 = this.data;
                this.setData((Object)a15);
                break label1;
            }
            java.util.Vector a16 = new java.util.Vector();
            int i34 = this.m_nNumberOfNodes;
            int i35 = i34 * 2;
            int i36 = i35 + 1;
            int i37 = i36;
            while(true)
            {
                int i38 = i37 + 2;
                int[] a17 = this.data;
                int i39 = a17.length;
                if(i38 >= i39)
                {
                    break;
                }
                int i40 = this.m_bOmniDirectional?1:0;
                label7: {
                    label5: {
                        if(i40 != 0)
                        {
                            break label5;
                        }
                        int[] a18 = this.data;
                        int i41 = a18[i37];
                        int i42 = a.getFrom();
                        label6: {
                            if(i41 != i42)
                            {
                                break label6;
                            }
                            int[] a19 = this.data;
                            int i43 = i37 + 1;
                            int i44 = a19[i43];
                            int i45 = a.getTo();
                            if(i44 == i45)
                            {
                                break label7;
                            }
                        }
                        int[] a20 = this.data;
                        int i46 = a20[i37];
                        Integer a21 = new Integer(i46);
                        a16.addElement((Object)a21);
                        int[] a22 = this.data;
                        int i47 = i37 + 1;
                        int i48 = a22[i47];
                        Integer a23 = new Integer(i48);
                        a16.addElement((Object)a23);
                        int[] a24 = this.data;
                        int i49 = i37 + 2;
                        int i50 = a24[i49];
                        Integer a25 = new Integer(i50);
                        a16.addElement((Object)a25);
                        break label7;
                    }
                    int[] a26 = this.data;
                    int i51 = a26[i37];
                    int i52 = a.getFrom();
                    label8: {
                        if(i51 != i52)
                        {
                            break label8;
                        }
                        int[] a27 = this.data;
                        int i53 = i37 + 1;
                        int i54 = a27[i53];
                        int i55 = a.getTo();
                        if(i54 == i55)
                        {
                            break label7;
                        }
                    }
                    int[] a28 = this.data;
                    int i56 = a28[i37];
                    int i57 = a.getTo();
                    label9: {
                        if(i56 != i57)
                        {
                            break label9;
                        }
                        int[] a29 = this.data;
                        int i58 = i37 + 1;
                        int i59 = a29[i58];
                        int i60 = a.getFrom();
                        if(i59 == i60)
                        {
                            break label7;
                        }
                    }
                    int[] a30 = this.data;
                    int i61 = a30[i37];
                    Integer a31 = new Integer(i61);
                    a16.addElement((Object)a31);
                    int[] a32 = this.data;
                    int i62 = i37 + 1;
                    int i63 = a32[i62];
                    Integer a33 = new Integer(i63);
                    a16.addElement((Object)a33);
                    int[] a34 = this.data;
                    int i64 = i37 + 2;
                    int i65 = a34[i64];
                    Integer a35 = new Integer(i65);
                    a16.addElement((Object)a35);
                }
                int i66 = i37 + 3;
                i37 = i66;
            }
            int i67 = this.m_nNumberOfNodes;
            int i68 = i67 * 2;
            int i69 = i68 + 1;
            int i70 = a16.size();
            int i71 = i69 + i70;
            int[] a36 = new int[i71];
            int i72 = 0;
            while(true)
            {
                int i73 = this.m_nNumberOfNodes;
                int i74 = i73 * 2;
                int i75 = i74 + 1;
                if(i72 >= i75)
                {
                    break;
                }
                else
                {
                    int[] a37 = this.data;
                    int i76 = a37[i72];
                    a36[i72] = i76;
                    int i77 = i72 + 1;
                    i72 = i77;
                }
            }
            int i78 = 0;
            while(true)
            {
                int i79 = a16.size();
                if(i78 >= i79)
                {
                    break;
                }
                else
                {
                    Object a38 = a16.elementAt(i78);
                    Integer dummy = (Integer)a38;
                    Integer a39 = (Integer)a38;
                    int i80 = this.m_nNumberOfNodes;
                    int i81 = i80 * 2;
                    int i82 = i81 + 1;
                    int i83 = i82 + i78;
                    int i84 = a39.intValue();
                    a36[i83] = i84;
                    int i85 = i78 + 1;
                    i78 = i85;
                }
            }
            int i86 = a36.length;
            int[] a40 = new int[i86];
            this.data = a40;
            int i87 = 0;
            while(true)
            {
                int[] a41 = this.data;
                int i88 = a41.length;
                if(i87 >= i88)
                {
                    break;
                }
                else
                {
                    int[] a42 = this.data;
                    int i89 = a36[i87];
                    a42[i87] = i89;
                    int i90 = i87 + 1;
                    i87 = i90;
                }
            }
            int[] a43 = this.data;
            this.setData((Object)a43);
        }
        aia.graph.common.GraphMaps a44 = this.m_algorithm;
        a44.repaint();
    }
    
    public com.cim.AIA.IntArray getData()
    {
        com.cim.AIA.IntArray a = null;
        int[] a0 = this.data;
        if(a0 != null)
        {
            int[] a1 = this.data;
            com.cim.AIA.IntArray a2 = new com.cim.AIA.IntArray(a1);
            a = a2;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public void generateNodeInfo()
    {
        java.util.Vector a = new java.util.Vector();
        this.m_nodeInfo = a;
        int[] a0 = this.data;
        int i = a0.length;
        label0: {
            if(i <= 0)
            {
                break label0;
            }
            int[] a1 = this.data;
            int i0 = a1[0];
            this.m_nNumberOfNodes = i0;
            int[] a2 = this.data;
            int i1 = a2.length;
            int i2 = this.m_nNumberOfNodes;
            int i3 = i2 * 2;
            int i4 = i3 + 1;
            if(i1 < i4)
            {
                break label0;
            }
            int i5 = 0;
            while(true)
            {
                int i6 = this.m_nNumberOfNodes;
                if(i5 >= i6)
                {
                    break;
                }
                else
                {
                    java.util.Vector a3 = this.m_nodeInfo;
                    int[] a4 = this.data;
                    int i7 = i5 * 2;
                    int i8 = i7 + 1;
                    int i9 = a4[i8];
                    int[] a5 = this.data;
                    int i10 = i5 * 2;
                    int i11 = i10 + 2;
                    int i12 = a5[i11];
                    aia.graph.common.NodeInfo a6 = new aia.graph.common.NodeInfo(i5, i9, i12);
                    a3.addElement((Object)a6);
                    int i13 = i5 + 1;
                    i5 = i13;
                }
            }
        }
    }
    
    public boolean getOmniDirectional()
    {
        int i = this.m_bOmniDirectional?1:0;
        return i != 0;
    }
    
    public java.util.Vector getNodes()
    {
        java.util.Vector a = null;
        java.util.Vector a0 = this.m_nodeInfo;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            java.util.Vector a1 = new java.util.Vector();
            int i = 0;
            while(true)
            {
                java.util.Vector a2 = this.m_nodeInfo;
                int i0 = a2.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a3 = this.m_nodeInfo;
                Object a4 = a3.elementAt(i);
                aia.graph.common.NodeInfo dummy = (aia.graph.common.NodeInfo)a4;
                aia.graph.common.NodeInfo a5 = (aia.graph.common.NodeInfo)a4;
                aia.graph.common.NodeInfo a6 = a5.copy();
                int i1 = a6.getX();
                int i2 = this.m_nZoomFactor;
                int i3 = i1 * i2;
                a6.setX(i3);
                int i4 = a6.getY();
                int i5 = this.m_nZoomFactor;
                int i6 = i4 * i5;
                a6.setY(i6);
                int i7 = a6.getKey();
                Integer a7 = new Integer(i7);
                int i8 = a6.getKey();
                aia.graph.common.GraphMapsNode a8 = new aia.graph.common.GraphMapsNode((Object)a7, i8, 1);
                aia.graph.common.GraphMaps a9 = this.m_algorithm;
                a9.setNodeAppearance(a8);
                a8.setWidth(20);
                a8.setIsVisible(true);
                a8.setHeight(20);
                int i9 = a6.getKey();
                int i10 = this.m_startNode;
                label3: {
                    label2: {
                        if(i9 == i10)
                        {
                            break label2;
                        }
                        int i11 = a6.getKey();
                        int i12 = this.m_endNode;
                        if(i11 != i12)
                        {
                            break label3;
                        }
                    }
                    aia.graph.common.GraphMaps a10 = this.m_algorithm;
                    java.awt.Color a11 = a10.getHighlightColor();
                    a8.setBackgroundColor(a11);
                }
                int i13 = a6.getX();
                int i14 = a6.getY();
                java.awt.Point a12 = new java.awt.Point(i13, i14);
                a8.setPosition(a12);
                a1.addElement((Object)a8);
                int i15 = i + 1;
                i = i15;
            }
            a = a1;
        }
        return a;
    }
    
    public java.util.Vector getLinks()
    {
        java.util.Vector a = null;
        aia.graph.common.LinkContainer a0 = new aia.graph.common.LinkContainer();
        aia.graph.common.GraphNode[] a1 = this.adj_structure;
        label2: {
            label1: {
                label0: {
                    if(a1 == null)
                    {
                        break label0;
                    }
                    aia.graph.common.GraphNode[] a2 = this.adj_structure;
                    int i = a2.length;
                    if(i != 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            int i0 = 0;
            while(true)
            {
                aia.graph.common.GraphNode[] a3 = this.adj_structure;
                int i1 = a3.length;
                if(i0 >= i1)
                {
                    break;
                }
                aia.graph.common.GraphNode[] a4 = this.adj_structure;
                aia.graph.common.GraphNode a5 = a4[i0];
                aia.graph.common.NodeInfo a6 = this.findNodeInfo(i0);
                aia.graph.common.GraphNode a7 = a5;
                while(true)
                {
                    int i2 = a7.getKey();
                    if(i2 == -1)
                    {
                        int i3 = i0 + 1;
                        i0 = i3;
                    }
                    else
                    {
                        int i4 = a7.getKey();
                        aia.graph.common.NodeInfo a8 = this.findNodeInfo(i4);
                        aia.graph.common.NodeInfo a9 = a6.copy();
                        aia.graph.common.NodeInfo a10 = a8.copy();
                        int i5 = a9.getX();
                        int i6 = this.m_nZoomFactor;
                        int i7 = i5 * i6;
                        a9.setX(i7);
                        int i8 = a9.getY();
                        int i9 = this.m_nZoomFactor;
                        int i10 = i8 * i9;
                        a9.setY(i10);
                        int i11 = a10.getX();
                        int i12 = this.m_nZoomFactor;
                        int i13 = i11 * i12;
                        a10.setX(i13);
                        int i14 = a10.getY();
                        int i15 = this.m_nZoomFactor;
                        int i16 = i14 * i15;
                        a10.setY(i16);
                        int i17 = this.m_bOmniDirectional?1:0;
                        int[][] a11 = this.adj_matrix;
                        int i18 = a6.getKey();
                        int[] a12 = a11[i18];
                        int i19 = a8.getKey();
                        int i20 = a12[i19];
                        aia.graph.common.Link a13 = a0.addLink(a9, a10, i17 != 0, i20);
                        aia.graph.common.GraphMaps a14 = this.m_algorithm;
                        a14.setLinkAppearance(a13);
                        aia.graph.common.GraphMaps a15 = this.m_algorithm;
                        a15.setLinkHighlighted(a13);
                        aia.graph.common.GraphNode a16 = a7.getNext();
                        a7 = a16;
                    }
                }
            }
            java.util.Vector a17 = a0.getLinks();
            a = a17;
        }
        return a;
    }
    
    private aia.graph.common.NodeInfo findNodeInfo(int i)
    {
        aia.graph.common.NodeInfo a = null;
        java.util.Vector a0 = this.m_nodeInfo;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    java.util.Vector a1 = this.m_nodeInfo;
                    int i0 = a1.size();
                    if(i0 != 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            int i1 = 0;
            while(true)
            {
                java.util.Vector a2 = this.m_nodeInfo;
                int i2 = a2.size();
                label3: {
                    if(i1 < i2)
                    {
                        break label3;
                    }
                    a = null;
                    break;
                }
                java.util.Vector a3 = this.m_nodeInfo;
                Object a4 = a3.elementAt(i1);
                aia.graph.common.NodeInfo dummy = (aia.graph.common.NodeInfo)a4;
                aia.graph.common.NodeInfo a5 = (aia.graph.common.NodeInfo)a4;
                int i3 = a5.getKey();
                if(i3 != i)
                {
                    int i4 = i1 + 1;
                    i1 = i4;
                    continue;
                }
                a = a5;
                break;
            }
        }
        return a;
    }
    
    public aia.graph.common.GraphNode getAdjacentNode(int i)
    {
        aia.graph.common.GraphNode a = null;
        aia.graph.common.GraphNode[] a0 = this.adj_structure;
        label2: {
            label1: {
                label0: {
                    if(a0 == null)
                    {
                        break label0;
                    }
                    aia.graph.common.GraphNode[] a1 = this.adj_structure;
                    int i0 = a1.length;
                    if(i0 != 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            aia.graph.common.GraphNode[] a2 = this.adj_structure;
            aia.graph.common.GraphNode a3 = a2[i];
            a = a3;
        }
        return a;
    }
    
    public int getNumberOfNodes()
    {
        int i = this.m_nNumberOfNodes;
        return i;
    }
    
    public java.util.Vector getAdjacencyMatrix()
    {
        java.util.Vector a = null;
        java.util.Vector a0 = new java.util.Vector();
        int[][] a1 = this.adj_matrix;
        label1: {
            label0: {
                if(a1 != null)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            int i = 0;
            while(true)
            {
                int i0 = this.m_nNumberOfNodes;
                if(i >= i0)
                {
                    break;
                }
                com.cim.AIA.ElementArray a2 = new com.cim.AIA.ElementArray(0, 0);
                a2.setColumGap(0);
                a2.setElementWidth(20);
                int i1 = 0;
                while(true)
                {
                    int i2 = this.m_nNumberOfNodes;
                    if(i1 >= i2)
                    {
                        a0.addElement((Object)a2);
                        int i3 = i + 1;
                        i = i3;
                    }
                    else
                    {
                        int[][] a3 = this.adj_matrix;
                        int[] a4 = a3[i];
                        int i4 = a4[i1];
                        Integer a5 = new Integer(i4);
                        aia.graph.common.GraphMapsNode a6 = new aia.graph.common.GraphMapsNode((Object)a5, i1, 2);
                        a6.setFrom(i);
                        a6.setTo(i1);
                        aia.graph.common.GraphMaps a7 = this.m_algorithm;
                        a7.setMatrixNodeAppearance(a6);
                        a6.setHeight(20);
                        a2.setValue(i1, (com.cim.AIA.Element)a6);
                        int i5 = i1 + 1;
                        i1 = i5;
                    }
                }
            }
            a = a0;
        }
        return a;
    }
    
    public com.cim.AIA.ElementArray getAdjacencyStructure()
    {
        com.cim.AIA.ElementArray a = null;
        aia.graph.common.GraphNode[] a0 = this.adj_structure;
        label1: {
            label0: {
                if(a0 != null)
                {
                    break label0;
                }
                a = null;
                break label1;
            }
            com.cim.AIA.ElementArray a1 = new com.cim.AIA.ElementArray(0, 0);
            a1.setColumGap(10);
            a1.setElementWidth(20);
            int i = 0;
            while(true)
            {
                int i0 = this.m_nNumberOfNodes;
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    Integer a2 = new Integer(i);
                    aia.graph.common.GraphMapsNode a3 = new aia.graph.common.GraphMapsNode((Object)a2, i, 3);
                    aia.graph.common.GraphMaps a4 = this.m_algorithm;
                    a4.setStructureNodeAppearance(a3);
                    a3.setHeight(20);
                    a1.setValue(i, (com.cim.AIA.Element)a3);
                    int i1 = i + 1;
                    i = i1;
                }
            }
            a = a1;
        }
        return a;
    }
    
    public int[][] getAdjacencyMatrixArray()
    {
        int[][] a = this.adj_matrix;
        return a;
    }
    
    public void setAdjacencyMatrixArray(int[][] a)
    {
        this.adj_matrix = a;
    }
    
    public aia.graph.common.GraphNode[] getAdjacencyStructureArray()
    {
        aia.graph.common.GraphNode[] a = this.adj_structure;
        return a;
    }
    
    static
    {
        TransclosureGraphAlgorithmCommon.messageDialog = null;
    }
}