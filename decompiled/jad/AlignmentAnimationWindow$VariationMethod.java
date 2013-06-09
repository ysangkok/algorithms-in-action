// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentAnimationWindow.java

import com.cim.AIA.*;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class _cls1.val.name extends CheckboxMenuItem
{

    private static final long serialVersionUID = 0xce64be2ef3f94f2aL;
    protected LadderTree ladderTree;
    protected Alignment alignment;
    protected int variationMethod;
    protected AlignmentAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    protected String expName;
    final AlignmentAnimationWindow this$0;

    public _cls1.val.name(final String name, boolean state, Alignment aAlignment, String dataDir, int aVariationMethod, String filename, 
            String expFileName, AlignmentAnimationWindow alignmentAnimationWindow, AlgorithmWindow algorithmWindow)
    {
        this.this$0 = AlignmentAnimationWindow.this;
        super(name, state);
        alignment = aAlignment;
        variationMethod = aVariationMethod;
        animWindow = alignmentAnimationWindow;
        algoWindow = algorithmWindow;
        expName = expFileName;
        ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, getLogger(), getBreakPoint());
        addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                animWindow.resetVariationButtons();
                if(Alignment.runningMode == 1 && (ds1.getState() || ds2.getState()))
                    if(Alignment.currentVariation == 1)
                    {
                        ds1.setState(false);
                        com.cim.AIA.Copyable temp = ds2.getData();
                        ds2.setState(true);
                        setCurrentData(temp, true, true);
                    } else
                    {
                        ds2.setState(false);
                        com.cim.AIA.Copyable temp = ds1.getData();
                        ds1.setState(true);
                        setCurrentData(temp, true, true);
                    }
                if(Alignment.runningMode == 2 && (ds3.getState() || ds4.getState()))
                    if(Alignment.currentVariation == 1)
                    {
                        ds3.setState(false);
                        com.cim.AIA.Copyable temp = ds4.getData();
                        ds4.setState(true);
                        setCurrentData(temp, true, true);
                    } else
                    {
                        ds4.setState(false);
                        com.cim.AIA.Copyable temp = ds3.getData();
                        ds3.setState(true);
                        setCurrentData(temp, true, true);
                    }
                setState(true);
                AlignmentApplet.expWin.initialiseMainData(AlignmentApplet.codeBaseString, expName);
                alignment.setVariation(variationMethod);
                resetThread(true, true, false, false);
                algoWindow.setLadderTree(ladderTree);
                Log log = new Log((byte)4, (byte)21, null, name);
                if(getLogger() != null)
                    getLogger().addLog(log);
            }

            final AlignmentAnimationWindow val$this$0;
            final String val$name;
            final AlignmentAnimationWindow.VariationMethod this$1;

            
            {
                this$1 = AlignmentAnimationWindow.VariationMethod.this;
                this$0 = alignmentanimationwindow;
                name = s;
                super();
            }
        });
    }
}
