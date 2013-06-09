import java.awt.event.*;
import localization.*;
import com.cim.AIA.*;
import java.awt.*;

public class Alignment3DEditDialog extends Dialog implements ExitListener
{
    private static final long serialVersionUID = 6536259069078192924L;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    protected Button ok;
    protected Button dump;
    protected Scrollbar xScroll;
    protected Scrollbar yScroll;
    protected Scrollbar zScroll;
    protected Scrollbar xTranslate;
    protected Scrollbar yTranslate;
    protected Scrollbar zTranslate;
    protected Scrollbar xMove;
    
    public Alignment3DEditDialog(final Frame parent, final String title, final Alignment theAlgorithm) {
        super(parent, title, false);
        this.ok = new Button(Messages.getString("Alignment3DEditDialog.0"));
        this.dump = new Button(Messages.getString("Alignment3DEditDialog.1"));
        final Panel panel = new Panel();
        final Panel scrollPanel = new Panel();
        this.xScroll = new Scrollbar(0, -65, 1, -90, 90);
        this.yScroll = new Scrollbar(0, 0, 1, -90, 90);
        this.zScroll = new Scrollbar(0, 0, 1, -90, 90);
        this.xTranslate = new Scrollbar(0, 0, 1, -100, 100);
        this.yTranslate = new Scrollbar(0, 0, 1, -100, 100);
        this.zTranslate = new Scrollbar(0, 0, 1, -100, 100);
        this.xMove = new Scrollbar(0, 50, 1, -100, 200);
        scrollPanel.setLayout(new GridLayout(7, 1));
        scrollPanel.add(this.xScroll);
        scrollPanel.add(this.yScroll);
        scrollPanel.add(this.zScroll);
        scrollPanel.add(this.xTranslate);
        scrollPanel.add(this.yTranslate);
        scrollPanel.add(this.zTranslate);
        scrollPanel.add(this.xMove);
        panel.setLayout(new GridLayout(3, 1));
        panel.add(scrollPanel);
        panel.add(this.dump);
        panel.add(this.ok);
        this.add(panel);
        this.ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Alignment3DEditDialog.this.setVisible(false);
            }
        });
        this.dump.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.out.println(Messages.getString("Alignment3DEditDialog.2") + Alignment3DEditDialog.this.xScroll.getValue());
                System.out.println(Messages.getString("Alignment3DEditDialog.3") + Alignment3DEditDialog.this.yScroll.getValue());
                System.out.println(Messages.getString("Alignment3DEditDialog.4") + Alignment3DEditDialog.this.zScroll.getValue());
                System.out.println(Messages.getString("Alignment3DEditDialog.5") + Alignment3DEditDialog.this.xTranslate.getValue());
                System.out.println(Messages.getString("Alignment3DEditDialog.6") + Alignment3DEditDialog.this.yTranslate.getValue());
                System.out.println(Messages.getString("Alignment3DEditDialog.7") + Alignment3DEditDialog.this.zTranslate.getValue());
            }
        });
        this.xScroll.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.rotateX(Alignment3DEditDialog.this.xScroll.getValue());
            }
        });
        this.yScroll.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.rotateY(Alignment3DEditDialog.this.yScroll.getValue());
            }
        });
        this.zScroll.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.rotateZ(Alignment3DEditDialog.this.zScroll.getValue());
            }
        });
        this.xTranslate.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.translateX(Alignment3DEditDialog.this.xTranslate.getValue());
            }
        });
        this.yTranslate.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.translateY(Alignment3DEditDialog.this.yTranslate.getValue());
            }
        });
        this.zTranslate.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.translateZ(Alignment3DEditDialog.this.zTranslate.getValue());
            }
        });
        this.xMove.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent e) {
                theAlgorithm.moveX(Alignment3DEditDialog.this.xMove.getValue());
            }
        });
        this.setSize(400, 300);
        this.setResizable(false);
    }
    
    public Alignment3DEditDialog(final String title, final Alignment algorithm) {
        this(new Frame(), title, algorithm);
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
}
