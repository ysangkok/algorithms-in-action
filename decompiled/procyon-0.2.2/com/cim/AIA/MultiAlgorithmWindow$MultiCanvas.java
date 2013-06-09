package com.cim.AIA;

import java.awt.*;

public class MultiCanvas extends Frame
{
    private static final long serialVersionUID = -8625342235649276241L;
    CodeCanvas canvas;
    boolean isVisible;
    String triggerKey;
    String unHighlight;
    
    MultiCanvas(final String title, final String dataDir, final String filename, final String triggerKey, final String unHighlight, final ExplainationListener expLis, final HelpListener helpLis) {
        super("Code Canvas" + title);
        this.isVisible = false;
        this.canvas = new CodeCanvas(MultiAlgorithmWindow.this, MultiAlgorithmWindow.this.getCodeBase().toString(), filename, MultiAlgorithmWindow.this.getCodeBase().toString() + MultiAlgorithmWindow.access$000());
        final ScrollPane newScrollPane = new ScrollPane(0);
        this.canvas.setParent(newScrollPane);
        newScrollPane.add(this.canvas);
        this.canvas.setLadderTree(CodeCanvas.getLadderTreeFromFile(dataDir, filename, x0.getLogger(), x0.getBreakPoint()));
        this.canvas.addMouseListener(MultiAlgorithmWindow.access$100(x0));
        this.canvas.addExplainationListener(expLis);
        this.canvas.addHelpListener(helpLis);
        this.triggerKey = triggerKey;
        this.unHighlight = unHighlight;
        this.add(newScrollPane);
        this.setSize(new Dimension(350, 400));
        this.setLocation(new Point(200, 200));
        this.setVisible(false);
        this.addWindowListener(MultiAlgorithmWindow.access$200(x0));
    }
}
