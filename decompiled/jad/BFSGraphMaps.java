// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BFSGraphMaps.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.Point;
import java.util.Vector;

public class BFSGraphMaps extends GraphMaps
    implements ColorSelectionListener, MethodSelectionListener
{

    public BFSGraphMaps(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        initialise(data);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection highlightSelection = new ColorSelection(highlightColor, HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        ColorSelection nodeSelection = new ColorSelection(unvisitedNodeColor, NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }

    protected void run()
    {
        commons.algorithmStartRunInitialisations();
        if(m_backMode)
        {
            m_backMode = false;
            initialise(m_stored_data);
            m_stored_data = null;
        }
        visited_links = new LinkContainer();
        m_bIsRunning = true;
        setPosition("1");
        visit_sequence = new int[commons.getNumberOfNodes()];
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
            visit_sequence[k] = -1;

        setPosition("1.2.1");
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
        {
            k_marker = k;
            setPosition("1.2.3");
            visit_sequence[k] = 0;
            setPosition("1.2.4");
        }

        setPosition("1.2.3");
        setPosition("1.2.5");
        visit_counter = 0;
        setPosition("1.3.1");
        queue = new Queue();
        setPosition("1.4.1");
        setPosition("1.5");
        for(int k = 0; k < commons.getNumberOfNodes(); k++)
        {
            k_marker = k;
            setPosition("2.1");
            setPosition("3.1");
            if(visit_sequence[k] == 0)
            {
                setPosition("4");
                visit(k);
            }
        }

        setPosition("2.1");
        setPosition("8");
        m_bIsRunning = false;
    }

    public void initialiseMethods(String dataDir, MethodSelectable methodSelectable)
    {
        MethodSelection visit = new MethodSelection(VISIT_MODE_LABEL, dataDir, "BreadthFirstSearch.visit", "4", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(visit, true);
        methodSelectable.addMethodSelectionListener(this);
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        if(queue_requests == null || queue_requests.size() == 0)
            return null;
        ElementArray elementArray = (ElementArray)parameter;
        if(elementArray == null)
            return null;
        GraphMapsNode node = null;
        GraphMapsNode node2 = null;
        MultipleTween multipleTween = new MultipleTween(paintable);
        for(int i1 = 0; i1 < queue_requests.size(); i1++)
        {
            QueueRequest queueRequest = (QueueRequest)queue_requests.elementAt(i1);
            for(int i2 = 0; i2 < elementArray.getSize(); i2++)
            {
                node = (GraphMapsNode)elementArray.getElement(i2);
                if(node.getIntValue() != queueRequest.getKey())
                    continue;
                if(queueRequest.isInsert())
                {
                    multipleTween.add(new InsertTween(paintable, node, numberOfSteps));
                    continue;
                }
                multipleTween.add(new MoveTween(null, node, node.getPosition(), new Point(GraphMapsCanvasExt.QUEUE_X - 50, GraphMapsCanvasExt.QUEUE_Y), false, numberOfSteps));
                for(int i3 = i2 + 1; i3 < elementArray.getSize(); i3++)
                {
                    node2 = (GraphMapsNode)elementArray.getElement(i3);
                    multipleTween.add(new MoveTween(null, node2, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                    node = node2;
                }

                if(node2 != null && isQueueOverFlow())
                {
                    node2.setIsVisible(true);
                    multipleTween.add(new InsertTween(paintable, node2, numberOfSteps));
                }
            }

        }

        if(multipleTween.getNumberOfTweens() == 0)
            return null;
        else
            return multipleTween;
    }
}
