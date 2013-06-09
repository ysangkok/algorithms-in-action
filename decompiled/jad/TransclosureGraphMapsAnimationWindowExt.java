// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransclosureGraphMapsAnimationWindowExt.java

import aia.graph.common.GraphMaps;
import aia.graph.common.HideMatrixButton;
import com.cim.AIA.*;
import java.applet.Applet;
import java.awt.MenuBar;
import localization.Messages;

public class TransclosureGraphMapsAnimationWindowExt extends AnimationWindow
{

    public String getAlgorithmName()
    {
        return "Transitive Closure";
    }

    public TransclosureGraphMapsAnimationWindowExt(AlgorithmCanvas canvas, AlgorithmThread thread, Applet applet)
    {
        super(canvas, thread, applet);
        graphMapsThread = null;
        m_matrixShowing = true;
        m_hideMatrixButton = null;
        frameTitle = FRAME_TITLE;
        m_hideMatrixButton = new HideMatrixButton(this, thread);
        addControlButton(m_hideMatrixButton);
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
            }

            final TransclosureGraphMapsAnimationWindowExt this$0;

            
            {
                this$0 = TransclosureGraphMapsAnimationWindowExt.this;
                super();
            }
        });
        GraphMaps graphMaps = (GraphMaps)getAlgorithm();
        graphMapsThread = (TransclosureGraphMapsThread)thread;
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
            m_hideMatrixButton.setLabel(Messages.getString("TransclosureGraphMapsAnimationWindowExt.1"));
        else
            m_hideMatrixButton.setLabel(Messages.getString("TransclosureGraphMapsAnimationWindowExt.2"));
    }

    protected static final String FRAME_TITLE = Messages.getString("TransclosureGraphMapsAnimationWindowExt.0");
    protected TransclosureGraphMapsThread graphMapsThread;
    protected boolean m_matrixShowing;
    protected HideMatrixButton m_hideMatrixButton;

}
