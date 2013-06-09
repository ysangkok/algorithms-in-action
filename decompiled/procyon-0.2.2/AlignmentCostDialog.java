import com.cim.common.*;
import localization.*;
import java.awt.event.*;
import com.cim.AIA.*;
import java.awt.*;

public class AlignmentCostDialog extends Dialog implements ExitListener, KeyListener, MouseMotionListener
{
    private static final long serialVersionUID = -785833018587654574L;
    public static final String DEFAULT_TITLE;
    public static final String DIST_DEFAULT_STRING1;
    public static final String DIST_DEFAULT_STRING2;
    public static final String DIST_DEFAULT_STRING3;
    public static final String DIST_DEFAULT_STRING4;
    public static final String DIST_DEFAULT_STRING5;
    public static final String SIM_DEFAULT_STRING1;
    public static final String SIM_DEFAULT_STRING2;
    public static final String SIM_DEFAULT_STRING3;
    public static final String SIM_DEFAULT_STRING4;
    public static final String SIM_DEFAULT_STRING5;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    public static final int DEFAULT_VARIATION = 1;
    protected Button ok;
    protected Button cancel;
    protected Checkbox ignore;
    protected String string1;
    protected String string2;
    protected String string3;
    protected String string4;
    protected String string5;
    protected TextField str1;
    protected TextField str2;
    protected TextField str3;
    protected TextField str4;
    protected TextField str5;
    protected boolean cancelWasPressed;
    protected int minValue;
    protected int maxValue;
    protected Frame frame;
    
    public AlignmentCostDialog(final Frame parent, final String title, final String[] items, final int minValue, final int maxValue, final int width, final int height, String stringLbl1, String stringLbl2, String stringLbl3, String stringLbl4, String stringLbl5, final int currentVariation) {
        super(parent, title, true);
        this.ok = new Button(Messages.getString("AlignmentCostDialog.0"));
        this.cancel = new Button(Messages.getString("AlignmentCostDialog.1"));
        this.ignore = new Checkbox(Messages.getString("AlignmentCostDialog.2"), false);
        this.cancelWasPressed = false;
        this.minValue = 0;
        this.maxValue = 0;
        if (stringLbl1 == null) {
            if (Alignment.runningMode == 1) {
                stringLbl1 = AlignmentCostDialog.DIST_DEFAULT_STRING1;
            }
            else {
                stringLbl1 = AlignmentCostDialog.SIM_DEFAULT_STRING1;
            }
        }
        if (stringLbl2 == null) {
            if (Alignment.runningMode == 1) {
                stringLbl2 = AlignmentCostDialog.DIST_DEFAULT_STRING2;
            }
            else {
                stringLbl2 = AlignmentCostDialog.SIM_DEFAULT_STRING2;
            }
        }
        if (stringLbl3 == null) {
            if (Alignment.runningMode == 1) {
                stringLbl3 = AlignmentCostDialog.DIST_DEFAULT_STRING3;
            }
            else {
                stringLbl3 = AlignmentCostDialog.SIM_DEFAULT_STRING3;
            }
        }
        if (stringLbl4 == null) {
            if (Alignment.runningMode == 1) {
                stringLbl4 = AlignmentCostDialog.DIST_DEFAULT_STRING4;
            }
            else {
                stringLbl4 = AlignmentCostDialog.SIM_DEFAULT_STRING4;
            }
        }
        if (stringLbl5 == null) {
            if (Alignment.runningMode == 1) {
                stringLbl5 = AlignmentCostDialog.DIST_DEFAULT_STRING5;
            }
            else {
                stringLbl5 = AlignmentCostDialog.SIM_DEFAULT_STRING5;
            }
        }
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.frame = parent;
        if (items != null) {
            if (items.length > 0 && items[0] != null) {
                this.string1 = items[0];
            }
            else {
                this.string1 = "";
            }
            if (items.length > 1 && items[1] != null) {
                this.string2 = items[1];
            }
            else {
                this.string2 = "";
            }
            if (items.length > 2 && items[2] != null) {
                this.string3 = items[2];
            }
            else {
                this.string3 = "";
            }
            if (items.length > 3 && items[3] != null) {
                this.string4 = items[3];
            }
            else {
                this.string4 = "";
            }
            if (items.length > 4 && items[4] != null) {
                this.string5 = items[4];
            }
            else {
                this.string5 = "";
            }
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.setBackground(Color.lightGray);
        final GridBagLayout gbLayout = new GridBagLayout();
        final GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gbLayout);
        final Label instruction = new Label(Messages.getString("AlignmentCostDialog.19"));
        final Label stringLabel1 = new Label(stringLbl1);
        final Label stringLabel2 = new Label(stringLbl2);
        final Label stringLabel3 = new Label(stringLbl3);
        final Label stringLabel4 = new Label(stringLbl4);
        final Label stringLabel5 = new Label(stringLbl5);
        this.str1 = new TextField(this.string1, 10);
        this.str2 = new TextField(this.string2, 10);
        this.str3 = new TextField(this.string3, 10);
        this.str4 = new TextField(this.string4, 10);
        this.str5 = new TextField(this.string5, 10);
        if (Alignment.runningMode == 1) {
            this.str1.setEnabled(false);
        }
        if (currentVariation == 1) {
            this.str4.setEnabled(false);
            this.str5.setEnabled(false);
        }
        else {
            this.str3.setEnabled(false);
        }
        this.buildConstraints(c, 0, 0, 2, 1, 0, 0);
        c.anchor = 17;
        c.insets = new Insets(5, 10, 0, 0);
        gbLayout.setConstraints(instruction, c);
        this.add(instruction);
        this.buildConstraints(c, 0, 1, 1, 1, 30, 0);
        c.anchor = 10;
        c.fill = 10;
        c.insets = new Insets(10, 10, 0, 0);
        gbLayout.setConstraints(stringLabel1, c);
        this.add(stringLabel1);
        this.buildConstraints(c, 0, 2, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel2, c);
        this.add(stringLabel2);
        this.buildConstraints(c, 0, 3, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel3, c);
        this.add(stringLabel3);
        this.buildConstraints(c, 0, 4, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel4, c);
        this.add(stringLabel4);
        this.buildConstraints(c, 0, 5, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel5, c);
        this.add(stringLabel5);
        c.insets = new Insets(10, 10, 0, 10);
        this.buildConstraints(c, 1, 1, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str1, c);
        this.add(this.str1);
        this.buildConstraints(c, 1, 2, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str2, c);
        this.add(this.str2);
        this.buildConstraints(c, 1, 3, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str3, c);
        this.add(this.str3);
        this.buildConstraints(c, 1, 4, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str4, c);
        this.add(this.str4);
        this.buildConstraints(c, 1, 5, 1, 1, 70, 0);
        gbLayout.setConstraints(this.str5, c);
        this.add(this.str5);
        final Panel btnPanel = new Panel();
        btnPanel.add(this.ok);
        btnPanel.add(this.cancel);
        c.insets = new Insets(10, 10, 5, 10);
        this.buildConstraints(c, 0, 6, 2, 1, 0, 0);
        gbLayout.setConstraints(btnPanel, c);
        this.add(btnPanel);
        this.ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AlignmentCostDialog.this.cancelWasPressed = false;
                if (AlignmentCostDialog.this.checkLength()) {
                    AlignmentCostDialog.this.setVisible(false);
                }
            }
        });
        this.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AlignmentCostDialog.this.cancelWasPressed = true;
                AlignmentCostDialog.this.setVisible(false);
            }
        });
        this.setSize(width, height);
        this.setResizable(false);
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
    }
    
    public AlignmentCostDialog(final int maxValue) {
        this(AlignmentCostDialog.DEFAULT_TITLE, maxValue, 400, 300, 1);
    }
    
    public AlignmentCostDialog(final String title, final int maxValue, final int width, final int height, final int currentVariation) {
        this(new Frame(), title, null, 0, maxValue, width, height, null, null, null, null, null, currentVariation);
        this.frame.setSize(width, height);
    }
    
    public AlignmentCostDialog(final String title, final String[] items, final int minValue, final int maxValue, final int currentVariation) {
        this(new Frame(), title, items, minValue, maxValue, 400, 300, null, null, null, null, null, currentVariation);
    }
    
    private void buildConstraints(final GridBagConstraints gbc, final int gx, final int gy, final int gw, final int gh, final int wx, final int wy) {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = (double)wx;
        gbc.weighty = (double)wy;
    }
    
    public boolean cancelPressed() {
        return this.cancelWasPressed;
    }
    
    protected boolean checkLength() {
        try {
            int i = Integer.parseInt(this.str1.getText());
            i = Integer.parseInt(this.str2.getText());
            i = Integer.parseInt(this.str3.getText());
            i = Integer.parseInt(this.str4.getText());
            Integer.parseInt(this.str5.getText());
        }
        catch (NumberFormatException e) {
            final MessageDialog msg = new MessageDialog(Messages.getString("AlignmentCostDialog.20"), true, true);
            msg.setTitle(Messages.getString("AlignmentCostDialog.21"));
            msg.setVisible(true);
            return false;
        }
        final int i1 = Integer.parseInt(this.str1.getText());
        final int i2 = Integer.parseInt(this.str2.getText());
        final int i3 = Integer.parseInt(this.str3.getText());
        final int i4 = Integer.parseInt(this.str4.getText());
        final int i5 = Integer.parseInt(this.str5.getText());
        if (i1 < this.minValue || i1 > this.maxValue || i2 < this.minValue || i2 > this.maxValue || i3 < this.minValue || i3 > this.maxValue || i4 < this.minValue || i4 > this.maxValue || i5 < this.minValue || i5 > this.maxValue) {
            final MessageDialog msg2 = new MessageDialog(Messages.getString("AlignmentCostDialog.22") + this.minValue + Messages.getString("AlignmentCostDialog.23") + this.maxValue, true, true);
            msg2.setTitle(Messages.getString("AlignmentCostDialog.24"));
            msg2.setVisible(true);
            return false;
        }
        return true;
    }
    
    public int[] getData() {
        if (this.cancelPressed()) {
            return new int[0];
        }
        return new int[] { Integer.parseInt(this.str1.getText()), Integer.parseInt(this.str2.getText()), Integer.parseInt(this.str3.getText()), Integer.parseInt(this.str4.getText()), Integer.parseInt(this.str5.getText()) };
    }
    
    protected boolean handleAKeyEvent(final KeyEvent e, final boolean requestFocus) {
        final int code = e.getKeyCode();
        return false;
    }
    
    protected void initialiseComponents() {
    }
    
    public void keyPressed(final KeyEvent e) {
        this.handleAKeyEvent(e, true);
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void processExitEvent(final ExitEvent e) {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
    
    static {
        DEFAULT_TITLE = Messages.getString("AlignmentCostDialog.3");
        DIST_DEFAULT_STRING1 = Messages.getString("AlignmentCostDialog.4");
        DIST_DEFAULT_STRING2 = Messages.getString("AlignmentCostDialog.5");
        DIST_DEFAULT_STRING3 = Messages.getString("AlignmentCostDialog.6");
        DIST_DEFAULT_STRING4 = Messages.getString("AlignmentCostDialog.7");
        DIST_DEFAULT_STRING5 = Messages.getString("AlignmentCostDialog.8");
        SIM_DEFAULT_STRING1 = Messages.getString("AlignmentCostDialog.9");
        SIM_DEFAULT_STRING2 = Messages.getString("AlignmentCostDialog.10");
        SIM_DEFAULT_STRING3 = Messages.getString("AlignmentCostDialog.11");
        SIM_DEFAULT_STRING4 = Messages.getString("AlignmentCostDialog.12");
        SIM_DEFAULT_STRING5 = Messages.getString("AlignmentCostDialog.13");
    }
}
