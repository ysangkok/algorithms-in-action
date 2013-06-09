import java.awt.*;
import com.cim.AIA.*;

public class PatriciaTreeIterExtendedHierarchyTree extends HierarchyTree
{
    public PatriciaTreeIterExtendedHierarchyTree() {
        super();
        this.borderColor = Color.white;
    }
    
    public PatriciaTreeIterExtendedHierarchyTree(final Tree tree) {
        super(tree);
    }
    
    public PatriciaTreeIterExtendedHierarchyTree(final Tree tree, final Node node) {
        super(tree, node);
    }
    
    public PatriciaTreeIterExtendedHierarchyTree(final Node node) {
        super(node);
    }
    
    public Point getParentConnectPoint() {
        if (this.parentTree == null || this.elements.size() == 0) {
            return null;
        }
        final PatriciaTreeIterExtendedHierarchyTree parent = (PatriciaTreeIterExtendedHierarchyTree)this.parentTree;
        final Point connectionPoint = new Point(parent.pos.x + parent.getWidth() / 2, parent.pos.y + parent.getHeight());
        if (parent.children.size() == 1) {
            return connectionPoint;
        }
        int childValue = 0;
        int i = 0;
        while (i < this.elements.size()) {
            try {
                childValue = ((Integer)((Integer)((Node)this.elements.elementAt(i)).getObject())).intValue();
            }
            catch (ClassCastException ex) {
                ++i;
                continue;
            }
            break;
        }
        for (i = 0; i < parent.children.size(); ++i) {
            if ((PatriciaTreeIterExtendedHierarchyTree)parent.children.elementAt(i) == this) {
                int j = 0;
                while (j < parent.elements.size()) {
                    int parentValue = 0;
                    try {
                        parentValue = ((Integer)((Integer)((Node)parent.elements.elementAt(j)).getObject())).intValue();
                    }
                    catch (ClassCastException ex2) {}
                    if (i == 0) {
                        connectionPoint.x = parent.pos.x;
                        connectionPoint.y = parent.pos.y + parent.elementAt(0).getHeight();
                        break;
                    }
                    if (i == parent.children.size() - 1) {
                        connectionPoint.x = parent.pos.x + parent.getWidth();
                        break;
                    }
                    if (j == 0) {
                        connectionPoint.x = parent.pos.x;
                    }
                    if (childValue > parentValue && parentValue != 0) {
                        final Point point = connectionPoint;
                        point.x = point.x + parent.getWidthOfNode(j);
                    }
                    ++j;
                }
            }
        }
        return connectionPoint;
    }
}
