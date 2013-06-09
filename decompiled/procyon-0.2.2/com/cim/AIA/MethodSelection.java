package com.cim.AIA;

public class MethodSelection
{
    protected String name;
    protected String key;
    protected LadderTree ladderTree;
    
    public MethodSelection(final String name, LadderTree ladderTree, final String key) {
        super();
        this.name = name;
        this.key = key;
        this.ladderTree = ladderTree;
        if (ladderTree.containsKey(key)) {
            ladderTree = ladderTree.getLadderTree(key);
            ladderTree.setParent(null);
        }
    }
    
    public MethodSelection(final String name, final String dataDir, final String filename, final String key, final Logger logger, final BreakPoint brkpnt) {
        super();
        this.name = name;
        this.key = key;
        this.ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, logger, brkpnt);
        if (key != null && key != "" && this.ladderTree.containsKey(key)) {
            this.ladderTree = this.ladderTree.getLadderTree(key);
            this.ladderTree.setParent(null);
        }
    }
    
    public String getKey() {
        return this.key;
    }
    
    public LadderTree getLadderTree() {
        return this.ladderTree;
    }
    
    public String getName() {
        return this.name;
    }
}
