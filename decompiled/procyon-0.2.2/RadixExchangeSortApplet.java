import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import localization.*;
import com.cim.AIA.*;

public class RadixExchangeSortApplet extends AlgorithmApplet
{
    private static final long serialVersionUID = -1277810669627720205L;
    protected static final String IMAGE_DIRECTORY = "images/";
    protected static final String ALGORITHM_FILE_NAME;
    protected static final String EXPLANATION_TITLE;
    protected static final String EXPLANATION_FILE_NAME;
    protected IntArray initalData;
    protected boolean debugingMode;
    
    public RadixExchangeSortApplet() {
        super();
        this.initalData = new IntArray(RadixExchangeSortAnimationWindow.DEFAULT_DATA);
        this.debugingMode = false;
        this.imageDirectory = "images/";
        this.algorithmFileName = RadixExchangeSortApplet.ALGORITHM_FILE_NAME;
        this.explanationTitle = RadixExchangeSortApplet.EXPLANATION_TITLE;
        this.explanationFileName = RadixExchangeSortApplet.EXPLANATION_FILE_NAME;
        if (this.debugingMode) {
            final Button button;
            final Button b = button = new Button(Messages.getString("RadixExchangeSortApplet.4"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    RadixExchangeSortApplet.this.displayWindowLocations();
                }
            });
            this.add(b);
        }
    }
    
    public AlgorithmCanvas getAlgorithmCanvas() {
        return new RadixExchangeSortCanvas();
    }
    
    public AlgorithmThread getAlgorithmThread(final Copyable copyable, final AlgorithmWindow algorithmWindow) {
        return new RadixExchangeSortThread(copyable, algorithmWindow);
    }
    
    public AnimationWindow getAnimationWindow(final AlgorithmCanvas algorithmCanvas, final AlgorithmThread algorithmThread, final Applet applet) {
        return new RadixExchangeSortAnimationWindow(algorithmCanvas, algorithmThread, applet);
    }
    
    public Copyable getInitialData() {
        return this.initalData;
    }
    
    public void setLocationAndSize() {
        this.explainationWindow.setLocation(0, 0);
        this.explainationWindow.setSize(235, 735);
        this.window.setLocation(235, 135);
        this.window.setSize(350, 600);
        this.helpWindow.setLocation(235, 0);
        this.helpWindow.setSize(350, 135);
        this.animWindow.setLocation(585, 0);
        this.animWindow.setSize(440, 720);
    }
    
    static {
        ALGORITHM_FILE_NAME = Messages.getString("RadixExchangeSortApplet.0");
        EXPLANATION_TITLE = Messages.getString("RadixExchangeSortApplet.2");
        EXPLANATION_FILE_NAME = Messages.getString("RadixExchangeSortApplet.1");
    }
}
