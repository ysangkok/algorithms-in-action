import java.awt.*;
import com.cim.AIA.*;
import aia.graph.common.*;

public class BFSGraphMaps extends GraphMaps implements ColorSelectionListener, MethodSelectionListener
{
    public BFSGraphMaps(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.initialise(data);
        final ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        final ColorSelection highlightSelection = new ColorSelection(this.highlightColor, BFSGraphMaps.HIGHLIGHT_COLOR);
        highlightSelection.addClass(this);
        cm.addColorSelection(highlightSelection);
        final ColorSelection nodeSelection = new ColorSelection(this.unvisitedNodeColor, BFSGraphMaps.NODE_COLOR);
        nodeSelection.addClass(this);
        cm.addColorSelection(nodeSelection);
        cm.addColorSelectionListener(this);
    }
    
    protected void run() {
        this.commons.algorithmStartRunInitialisations();
        if (this.m_backMode) {
            this.m_backMode = false;
            this.initialise(this.m_stored_data);
            this.m_stored_data = null;
        }
        this.visited_links = new LinkContainer();
        this.m_bIsRunning = true;
        this.setPosition("1");
        this.visit_sequence = new int[this.commons.getNumberOfNodes()];
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.visit_sequence[k] = -1;
        }
        this.setPosition("1.2.1");
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.k_marker = k;
            this.setPosition("1.2.3");
            this.visit_sequence[k] = 0;
            this.setPosition("1.2.4");
        }
        this.setPosition("1.2.3");
        this.setPosition("1.2.5");
        this.visit_counter = 0;
        this.setPosition("1.3.1");
        this.queue = new Queue();
        this.setPosition("1.4.1");
        this.setPosition("1.5");
        for (int k = 0; k < this.commons.getNumberOfNodes(); ++k) {
            this.k_marker = k;
            this.setPosition("2.1");
            this.setPosition("3.1");
            if (this.visit_sequence[k] == 0) {
                this.setPosition("4");
                this.visit(k);
            }
        }
        this.setPosition("2.1");
        this.setPosition("8");
        this.m_bIsRunning = false;
    }
    
    public void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection visit = new MethodSelection(BFSGraphMaps.VISIT_MODE_LABEL, dataDir, "BreadthFirstSearch.visit", "4", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(visit, true);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        if (this.queue_requests == null || this.queue_requests.size() == 0) {
            return null;
        }
        final ElementArray elementArray = (ElementArray)parameter;
        if (elementArray == null) {
            return null;
        }
        GraphMapsNode node = null;
        GraphMapsNode node2 = null;
        final MultipleTween multipleTween = new MultipleTween(paintable);
        for (int i1 = 0; i1 < this.queue_requests.size(); ++i1) {
            final QueueRequest queueRequest = (QueueRequest)this.queue_requests.elementAt(i1);
            int i2 = 0;
            while (i2 < elementArray.getSize()) {
                node = (GraphMapsNode)elementArray.getElement(i2);
                if (node.getIntValue() == queueRequest.getKey()) {
                    if (queueRequest.isInsert()) {
                        multipleTween.add(new InsertTween(paintable, node, numberOfSteps));
                    }
                    else {
                        multipleTween.add(new MoveTween(null, node, node.getPosition(), new Point(GraphMapsCanvasExt.QUEUE_X - 50, GraphMapsCanvasExt.QUEUE_Y), false, numberOfSteps));
                        int i3 = i2 + 1;
                        while (i3 < elementArray.getSize()) {
                            node2 = (GraphMapsNode)elementArray.getElement(i3);
                            multipleTween.add(new MoveTween(null, node2, node2.getPosition(), node.getPosition(), false, numberOfSteps));
                            node = node2;
                            ++i3;
                        }
                        if (node2 != null && this.isQueueOverFlow()) {
                            node2.setIsVisible(true);
                            multipleTween.add(new InsertTween(paintable, node2, numberOfSteps));
                        }
                    }
                }
                ++i2;
            }
        }
        if (multipleTween.getNumberOfTweens() == 0) {
            return null;
        }
        return multipleTween;
    }
}
