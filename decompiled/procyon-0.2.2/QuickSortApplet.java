import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class QuickSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -4679757344986568093L;
    public static final String RightPartitionFileName;
    public static final String RandomPartitionFileName;
    public static final String MiddleOf3RandomPartitionFileName;
    public static final String MiddleOf3PartitionFileName;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected IntArray initalData;
    protected boolean debugingMode;
    
    public QuickSortApplet() {
        super();
        this.initalData = new IntArray(QuickSortAnimationWindow.DEFAULT_DATA);
        this.debugingMode = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = QuickSortApplet.RightPartitionFileName;
        this.explanationTitle = QuickSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = QuickSortApplet.EXPLANATION_FILE_NAME;
        if (this.debugingMode) {
            final Button button;
            final Button b = button = new Button(Messages.getString("QuickSortApplet.1"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    QuickSortApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new QuickSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new QuickSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new QuickSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return this.initalData;
    }
    
    public void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(250, 735);
        this.window.setLocation(250, 0);
        this.window.setSize(420, 600);
        this.helpWindow.setLocation(250, 600);
        this.helpWindow.setSize(420, 135);
        this.animWindow.setLocation(670, 0);
        this.animWindow.setSize(355, 720);
    }
    
    static {
        RightPartitionFileName = Messages.getString("QuickSortApplet.6");
        RandomPartitionFileName = Messages.getString("QuickSortApplet.5");
        MiddleOf3RandomPartitionFileName = Messages.getString("QuickSortApplet.4");
        MiddleOf3PartitionFileName = Messages.getString("QuickSortApplet.3");
        EXPLANATION_TITLE = Messages.getString("QuickSortApplet.0");
        EXPLANATION_FILE_NAME = Messages.getString("QuickSortApplet.2");
    }
}
