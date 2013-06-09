// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSTKGraphMapsAnimationWindowExt.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.MenuBar;
import localization.Messages;

public class MSTKGraphMapsAnimationWindowExt extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "Minimum Spanning Tree";
    }

    public MSTKGraphMapsAnimationWindowExt(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        graphMapsThread = null;
        m_matrixShowing = false;
        m_structureShowing = false;
        m_hideMatrixButton = null;
        m_hideStructureButton = null;
        HIDE_MATRIX_LABEL = Messages.getString("MSTKGraphMapsAnimationWindowExt.1");
        SHOW_MATRIX_LABEL = Messages.getString("MSTKGraphMapsAnimationWindowExt.2");
        HIDE_STRUCTURE_LABEL = Messages.getString("MSTKGraphMapsAnimationWindowExt.3");
        SHOW_STRUCTURE_LABEL = Messages.getString("MSTKGraphMapsAnimationWindowExt.4");
        frameTitle = FRAME_TITLE;
        m_hideMatrixButton = new HideMatrixButton(this, thread);
        m_hideMatrixButton.setLabel(SHOW_MATRIX_LABEL);
        addControlButton(m_hideMatrixButton);
        m_hideStructureButton = new HideStructureButton(this, thread);
        m_hideStructureButton.setLabel(SHOW_STRUCTURE_LABEL);
        addControlButton(m_hideStructureButton);
        addControlListener(new ControlListener() {

            public void controlBack(ControlEvent controlevent)
            {
            }

            public void controlPause(ControlEvent controlevent)
            {
            }

            public void controlReset(ControlEvent controlevent)
            {
            }

            public void controlRestart(ControlEvent controlevent)
            {
            }

            public void controlRun(ControlEvent controlevent)
            {
            }

            public void controlStep(ControlEvent controlevent)
            {
            }

            public void controlOther(ControlEvent e)
            {
                if(e.getType() == 2350)
                    hideMatrix();
                else
                if(e.getType() == 2351)
                    hideStructure();
            }

            final MSTKGraphMapsAnimationWindowExt this$0;

            
            {
                this$0 = MSTKGraphMapsAnimationWindowExt.this;
                super();
            }
        });
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        graphMapsThread = (MSTKGraphMapsThread)thread;
    }

    protected void initialiseMenuBar()
    {
        menuBar = new MenuBar();
        setMenuBar(menuBar);
        initialiseHelpMenuItem();
    }

    protected void hideMatrix()
    {
        m_matrixShowing = !m_matrixShowing;
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        graphMaps.setMatrixShowing(m_matrixShowing);
        if(m_matrixShowing)
            m_hideMatrixButton.setLabel(HIDE_MATRIX_LABEL);
        else
            m_hideMatrixButton.setLabel(SHOW_MATRIX_LABEL);
    }

    protected void hideStructure()
    {
        m_structureShowing = !m_structureShowing;
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        graphMaps.setStructureShowing(m_structureShowing);
        if(m_structureShowing)
            m_hideStructureButton.setLabel(HIDE_STRUCTURE_LABEL);
        else
            m_hideStructureButton.setLabel(SHOW_STRUCTURE_LABEL);
    }

    protected static final String FRAME_TITLE = Messages.getString("MSTKGraphMapsAnimationWindowExt.0");
    protected MSTKGraphMapsThread graphMapsThread;
    protected boolean m_matrixShowing;
    protected boolean m_structureShowing;
    protected HideMatrixButton m_hideMatrixButton;
    protected HideStructureButton m_hideStructureButton;
    protected String HIDE_MATRIX_LABEL;
    protected String SHOW_MATRIX_LABEL;
    protected String HIDE_STRUCTURE_LABEL;
    protected String SHOW_STRUCTURE_LABEL;

}
