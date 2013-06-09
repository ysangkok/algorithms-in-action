import localization.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import com.cim.AIA.*;

public class MergeBUSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = 5498336342267893336L;
    public static final String RightPartitionFileName;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    public static final int ANIM_WIN_WIDTH = 600;
    public static final int ANIM_WIN_HEIGHT = 765;
    protected IntArray initalData;
    protected boolean debugingMode;
    
    public MergeBUSortApplet() {
        super();
        this.initalData = new IntArray(MergeBUSortAnimationWindow.DEFAULT_DATA);
        this.debugingMode = true;
        this.imageDirectory = "images/";
        this.algorithmFileName = MergeBUSortApplet.RightPartitionFileName;
        this.explanationTitle = MergeBUSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = MergeBUSortApplet.EXPLANATION_FILE_NAME;
        if (this.debugingMode) {
            final Button button;
            final Button b = button = new Button("Display Positions");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    MergeBUSortApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public Copyable getInitialData() {
        return this.initalData;
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new MergeBUSortThread(copyable, algorithmWindow);
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new MergeBUSortCanvas();
    }
    
    public void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(250, 735);
        this.window.setLocation(250, 0);
        this.window.setSize(570, 765);
        this.helpWindow.setLocation(250, 600);
        this.helpWindow.setSize(420, 135);
        this.animWindow.setLocation(820, 0);
        this.animWindow.setSize(600, 765);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new MergeBUSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    static {
        RightPartitionFileName = Messages.getString("MergeBUSortApplet.0");
        EXPLANATION_TITLE = Messages.getString("MergeBUSortApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("MergeBUSortApplet.3");
    }
}
