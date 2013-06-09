// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPAnimationWindow.java

import com.cim.AIA.*;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ladderTree extends CheckboxMenuItem
{

    private static final long serialVersionUID = 0x13ca060206b63498L;
    protected LadderTree ladderTree;
    protected KMP kmp;
    protected int variationMethod;
    protected KMPAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    final KMPAnimationWindow this$0;

    public _cls1.val.this._cls0(String name, boolean state, KMP aKmp, String dataDir, int aVariationMethod, String filename, 
            KMPAnimationWindow kmpAnimationWindow, AlgorithmWindow algorithmWindow)
    {
        this.this$0 = KMPAnimationWindow.this;
        super(name, state);
        kmp = aKmp;
        variationMethod = aVariationMethod;
        animWindow = kmpAnimationWindow;
        algoWindow = algorithmWindow;
        ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, getLogger(), getBreakPoint());
        addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                animWindow.resetVariationButtons();
                setState(true);
                kmp.setVariation(variationMethod);
                resetThread(true, true, false, false);
                algoWindow.setLadderTree(ladderTree);
            }

            final KMPAnimationWindow val$this$0;
            final KMPAnimationWindow.VariationMethod this$1;

            
            {
                this$1 = KMPAnimationWindow.VariationMethod.this;
                this$0 = kmpanimationwindow;
                super();
            }
        });
    }
}
