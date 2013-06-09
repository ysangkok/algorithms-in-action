// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Alignment3DEditDialog.java

import com.cim.AIA.ExitEvent;
import com.cim.AIA.ExitListener;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import localization.Messages;

public class Alignment3DEditDialog extends Dialog
    implements ExitListener
{

    public Alignment3DEditDialog(Frame parent, String title, Alignment theAlgorithm)
    {
        super(parent, title, false);
        ok = new Button(Messages.getString("Alignment3DEditDialog.0"));
        dump = new Button(Messages.getString("Alignment3DEditDialog.1"));
        final Alignment algorithm = theAlgorithm;
        Panel panel = new Panel();
        Panel scrollPanel = new Panel();
        xScroll = new Scrollbar(0, -65, 1, -90, 90);
        yScroll = new Scrollbar(0, 0, 1, -90, 90);
        zScroll = new Scrollbar(0, 0, 1, -90, 90);
        xTranslate = new Scrollbar(0, 0, 1, -100, 100);
        yTranslate = new Scrollbar(0, 0, 1, -100, 100);
        zTranslate = new Scrollbar(0, 0, 1, -100, 100);
        xMove = new Scrollbar(0, 50, 1, -100, 200);
        scrollPanel.setLayout(new GridLayout(7, 1));
        scrollPanel.add(xScroll);
        scrollPanel.add(yScroll);
        scrollPanel.add(zScroll);
        scrollPanel.add(xTranslate);
        scrollPanel.add(yTranslate);
        scrollPanel.add(zTranslate);
        scrollPanel.add(xMove);
        panel.setLayout(new GridLayout(3, 1));
        panel.add(scrollPanel);
        panel.add(dump);
        panel.add(ok);
        add(panel);
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }

            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                super();
            }
        });
        dump.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.2")).append(xScroll.getValue()).toString());
                System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.3")).append(yScroll.getValue()).toString());
                System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.4")).append(zScroll.getValue()).toString());
                System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.5")).append(xTranslate.getValue()).toString());
                System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.6")).append(yTranslate.getValue()).toString());
                System.out.println((new StringBuilder()).append(Messages.getString("Alignment3DEditDialog.7")).append(zTranslate.getValue()).toString());
            }

            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                super();
            }
        });
        xScroll.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.rotateX(xScroll.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        yScroll.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.rotateY(yScroll.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        zScroll.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.rotateZ(zScroll.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        xTranslate.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.translateX(xTranslate.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        yTranslate.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.translateY(yTranslate.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        zTranslate.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.translateZ(zTranslate.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        xMove.addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                algorithm.moveX(xMove.getValue());
            }

            final Alignment val$algorithm;
            final Alignment3DEditDialog this$0;

            
            {
                this$0 = Alignment3DEditDialog.this;
                algorithm = alignment;
                super();
            }
        });
        setSize(400, 300);
        setResizable(false);
    }

    public Alignment3DEditDialog(String title, Alignment algorithm)
    {
        this(new Frame(), title, algorithm);
    }

    public void processExitEvent(ExitEvent e)
    {
        setVisible(false);
        removeAll();
        dispose();
    }

    private static final long serialVersionUID = 0x5ab574fe2454c31cL;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    protected Button ok;
    protected Button dump;
    protected Scrollbar xScroll;
    protected Scrollbar yScroll;
    protected Scrollbar zScroll;
    protected Scrollbar xTranslate;
    protected Scrollbar yTranslate;
    protected Scrollbar zTranslate;
    protected Scrollbar xMove;
}
