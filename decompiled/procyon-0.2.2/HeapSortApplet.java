import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class HeapSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -6131463170900705273L;
    protected static final String FILE_NAME;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected boolean debugingMode;
    
    public HeapSortApplet() {
        super();
        this.debugingMode = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = HeapSortApplet.FILE_NAME;
        this.explanationTitle = HeapSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = HeapSortApplet.EXPLANATION_FILE_NAME;
        if (this.debugingMode) {
            final Button button;
            final Button b = button = new Button(Messages.getString("HeapSortApplet.0"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    HeapSortApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new HeapSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new HeapSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new HeapSortAnimationWindow(algorithmCanvas, algorithmThread, this);
    }
    
    public Copyable getInitialData() {
        return new RandomIntArrayDataSelection("", false, null, 3, 20, 1, 99).getData();
    }
    
    protected void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(250, 735);
        this.window.setLocation(250, 0);
        this.window.setSize(385, 735);
        this.helpWindow.setLocation(635, 0);
        this.helpWindow.setSize(390, 150);
        this.animWindow.setLocation(635, 150);
        this.animWindow.setSize(390, 565);
    }
    
    static {
        FILE_NAME = Messages.getString("HeapSortApplet.3");
        EXPLANATION_TITLE = Messages.getString("HeapSortApplet.1");
        EXPLANATION_FILE_NAME = Messages.getString("HeapSortApplet.2");
    }
}
