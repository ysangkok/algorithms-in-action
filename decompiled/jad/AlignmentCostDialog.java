// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentCostDialog.java

import com.cim.AIA.ExitEvent;
import com.cim.AIA.ExitListener;
import com.cim.common.MessageDialog;
import java.awt.*;
import java.awt.event.*;
import localization.Messages;

public class AlignmentCostDialog extends Dialog
    implements ExitListener, KeyListener, MouseMotionListener
{

    public AlignmentCostDialog(Frame parent, String title, String items[], int minValue, int maxValue, int width, int height, 
            String stringLbl1, String stringLbl2, String stringLbl3, String stringLbl4, String stringLbl5, int currentVariation)
    {
        super(parent, title, true);
        ok = new Button(Messages.getString("AlignmentCostDialog.0"));
        cancel = new Button(Messages.getString("AlignmentCostDialog.1"));
        ignore = new Checkbox(Messages.getString("AlignmentCostDialog.2"), false);
        cancelWasPressed = false;
        this.minValue = 0;
        this.maxValue = 0;
        if(stringLbl1 == null)
            if(Alignment.runningMode == 1)
                stringLbl1 = DIST_DEFAULT_STRING1;
            else
                stringLbl1 = SIM_DEFAULT_STRING1;
        if(stringLbl2 == null)
            if(Alignment.runningMode == 1)
                stringLbl2 = DIST_DEFAULT_STRING2;
            else
                stringLbl2 = SIM_DEFAULT_STRING2;
        if(stringLbl3 == null)
            if(Alignment.runningMode == 1)
                stringLbl3 = DIST_DEFAULT_STRING3;
            else
                stringLbl3 = SIM_DEFAULT_STRING3;
        if(stringLbl4 == null)
            if(Alignment.runningMode == 1)
                stringLbl4 = DIST_DEFAULT_STRING4;
            else
                stringLbl4 = SIM_DEFAULT_STRING4;
        if(stringLbl5 == null)
            if(Alignment.runningMode == 1)
                stringLbl5 = DIST_DEFAULT_STRING5;
            else
                stringLbl5 = SIM_DEFAULT_STRING5;
        addKeyListener(this);
        addMouseMotionListener(this);
        frame = parent;
        if(items != null)
        {
            if(items.length > 0 && items[0] != null)
                string1 = items[0];
            else
                string1 = "";
            if(items.length > 1 && items[1] != null)
                string2 = items[1];
            else
                string2 = "";
            if(items.length > 2 && items[2] != null)
                string3 = items[2];
            else
                string3 = "";
            if(items.length > 3 && items[3] != null)
                string4 = items[3];
            else
                string4 = "";
            if(items.length > 4 && items[4] != null)
                string5 = items[4];
            else
                string5 = "";
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
        setBackground(Color.lightGray);
        GridBagLayout gbLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbLayout);
        Label instruction = new Label(Messages.getString("AlignmentCostDialog.19"));
        Label stringLabel1 = new Label(stringLbl1);
        Label stringLabel2 = new Label(stringLbl2);
        Label stringLabel3 = new Label(stringLbl3);
        Label stringLabel4 = new Label(stringLbl4);
        Label stringLabel5 = new Label(stringLbl5);
        str1 = new TextField(string1, 10);
        str2 = new TextField(string2, 10);
        str3 = new TextField(string3, 10);
        str4 = new TextField(string4, 10);
        str5 = new TextField(string5, 10);
        if(Alignment.runningMode == 1)
            str1.setEnabled(false);
        if(currentVariation == 1)
        {
            str4.setEnabled(false);
            str5.setEnabled(false);
        } else
        {
            str3.setEnabled(false);
        }
        buildConstraints(c, 0, 0, 2, 1, 0, 0);
        c.anchor = 17;
        c.insets = new Insets(5, 10, 0, 0);
        gbLayout.setConstraints(instruction, c);
        add(instruction);
        buildConstraints(c, 0, 1, 1, 1, 30, 0);
        c.anchor = 10;
        c.fill = 10;
        c.insets = new Insets(10, 10, 0, 0);
        gbLayout.setConstraints(stringLabel1, c);
        add(stringLabel1);
        buildConstraints(c, 0, 2, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel2, c);
        add(stringLabel2);
        buildConstraints(c, 0, 3, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel3, c);
        add(stringLabel3);
        buildConstraints(c, 0, 4, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel4, c);
        add(stringLabel4);
        buildConstraints(c, 0, 5, 1, 1, 30, 0);
        gbLayout.setConstraints(stringLabel5, c);
        add(stringLabel5);
        c.insets = new Insets(10, 10, 0, 10);
        buildConstraints(c, 1, 1, 1, 1, 70, 0);
        gbLayout.setConstraints(str1, c);
        add(str1);
        buildConstraints(c, 1, 2, 1, 1, 70, 0);
        gbLayout.setConstraints(str2, c);
        add(str2);
        buildConstraints(c, 1, 3, 1, 1, 70, 0);
        gbLayout.setConstraints(str3, c);
        add(str3);
        buildConstraints(c, 1, 4, 1, 1, 70, 0);
        gbLayout.setConstraints(str4, c);
        add(str4);
        buildConstraints(c, 1, 5, 1, 1, 70, 0);
        gbLayout.setConstraints(str5, c);
        add(str5);
        Panel btnPanel = new Panel();
        btnPanel.add(ok);
        btnPanel.add(cancel);
        c.insets = new Insets(10, 10, 5, 10);
        buildConstraints(c, 0, 6, 2, 1, 0, 0);
        gbLayout.setConstraints(btnPanel, c);
        add(btnPanel);
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                cancelWasPressed = false;
                if(checkLength())
                    setVisible(false);
            }

            final AlignmentCostDialog this$0;

            
            {
                this$0 = AlignmentCostDialog.this;
                super();
            }
        });
        cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                cancelWasPressed = true;
                setVisible(false);
            }

            final AlignmentCostDialog this$0;

            
            {
                this$0 = AlignmentCostDialog.this;
                super();
            }
        });
        setSize(width, height);
        setResizable(false);
        pack();
        java.awt.Dimension screenSize = getToolkit().getScreenSize();
    }

    public AlignmentCostDialog(int maxValue)
    {
        this(DEFAULT_TITLE, maxValue, 400, 300, 1);
    }

    public AlignmentCostDialog(String title, int maxValue, int width, int height, int currentVariation)
    {
        this(new Frame(), title, null, 0, maxValue, width, height, null, null, null, null, null, currentVariation);
        frame.setSize(width, height);
    }

    public AlignmentCostDialog(String title, String items[], int minValue, int maxValue, int currentVariation)
    {
        this(new Frame(), title, items, minValue, maxValue, 400, 300, null, null, null, null, null, currentVariation);
    }

    private void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy)
    {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = wx;
        gbc.weighty = wy;
    }

    public boolean cancelPressed()
    {
        return cancelWasPressed;
    }

    protected boolean checkLength()
    {
        try
        {
            int i = Integer.parseInt(str1.getText());
            i = Integer.parseInt(str2.getText());
            i = Integer.parseInt(str3.getText());
            i = Integer.parseInt(str4.getText());
            i = Integer.parseInt(str5.getText());
        }
        catch(NumberFormatException e)
        {
            MessageDialog msg = new MessageDialog(Messages.getString("AlignmentCostDialog.20"), true, true);
            msg.setTitle(Messages.getString("AlignmentCostDialog.21"));
            msg.setVisible(true);
            return false;
        }
        int i1 = Integer.parseInt(str1.getText());
        int i2 = Integer.parseInt(str2.getText());
        int i3 = Integer.parseInt(str3.getText());
        int i4 = Integer.parseInt(str4.getText());
        int i5 = Integer.parseInt(str5.getText());
        if(i1 < minValue || i1 > maxValue || i2 < minValue || i2 > maxValue || i3 < minValue || i3 > maxValue || i4 < minValue || i4 > maxValue || i5 < minValue || i5 > maxValue)
        {
            MessageDialog msg = new MessageDialog((new StringBuilder()).append(Messages.getString("AlignmentCostDialog.22")).append(minValue).append(Messages.getString("AlignmentCostDialog.23")).append(maxValue).toString(), true, true);
            msg.setTitle(Messages.getString("AlignmentCostDialog.24"));
            msg.setVisible(true);
            return false;
        } else
        {
            return true;
        }
    }

    public int[] getData()
    {
        if(cancelPressed())
        {
            return new int[0];
        } else
        {
            int data[] = new int[5];
            data[0] = Integer.parseInt(str1.getText());
            data[1] = Integer.parseInt(str2.getText());
            data[2] = Integer.parseInt(str3.getText());
            data[3] = Integer.parseInt(str4.getText());
            data[4] = Integer.parseInt(str5.getText());
            return data;
        }
    }

    protected boolean handleAKeyEvent(KeyEvent e, boolean requestFocus)
    {
        int code = e.getKeyCode();
        return false;
    }

    protected void initialiseComponents()
    {
    }

    public void keyPressed(KeyEvent e)
    {
        handleAKeyEvent(e, true);
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
    }

    public void processExitEvent(ExitEvent e)
    {
        setVisible(false);
        removeAll();
        dispose();
    }

    private static final long serialVersionUID = 0xf51829087a4f5652L;
    public static final String DEFAULT_TITLE = Messages.getString("AlignmentCostDialog.3");
    public static final String DIST_DEFAULT_STRING1 = Messages.getString("AlignmentCostDialog.4");
    public static final String DIST_DEFAULT_STRING2 = Messages.getString("AlignmentCostDialog.5");
    public static final String DIST_DEFAULT_STRING3 = Messages.getString("AlignmentCostDialog.6");
    public static final String DIST_DEFAULT_STRING4 = Messages.getString("AlignmentCostDialog.7");
    public static final String DIST_DEFAULT_STRING5 = Messages.getString("AlignmentCostDialog.8");
    public static final String SIM_DEFAULT_STRING1 = Messages.getString("AlignmentCostDialog.9");
    public static final String SIM_DEFAULT_STRING2 = Messages.getString("AlignmentCostDialog.10");
    public static final String SIM_DEFAULT_STRING3 = Messages.getString("AlignmentCostDialog.11");
    public static final String SIM_DEFAULT_STRING4 = Messages.getString("AlignmentCostDialog.12");
    public static final String SIM_DEFAULT_STRING5 = Messages.getString("AlignmentCostDialog.13");
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

}
