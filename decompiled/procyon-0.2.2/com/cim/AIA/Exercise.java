package com.cim.AIA;

import java.io.*;
import localization.*;
import java.net.*;
import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise implements ActionListener
{
    protected Dialog dialog;
    protected Image correctImage;
    protected Image incorrectImage;
    protected Vector<ExerciseQuestion> exerciseQuestions;
    protected int questionPosition;
    protected ExerciseQuestion currentQuestion;
    protected Panel questionPanel;
    protected Button ok;
    protected Button next;
    protected Button exit;
    protected Frame frame;
    protected String title;
    
    public Exercise(final Frame frame, final String title, final Applet applet, String imgDir, final String correctImageFilename, final String incorrectImageFileName) {
        super();
        URL newURL = applet.getDocumentBase();
        String modifier = newURL.toString();
        int i = modifier.length() - 1;
        while (modifier.charAt(i) != '/') {
            --i;
        }
        modifier = modifier.substring(0, i);
        modifier = modifier + "/";
        try {
            newURL = new URL(modifier);
        }
        catch (Exception ex) {}
        imgDir = newURL.toString() + "images/";
        this.frame = frame;
        this.title = title;
        this.exerciseQuestions = new Vector();
        this.questionPosition = 0;
        this.initaliseButtons();
        this.init(false);
        this.loadImages(applet, imgDir, correctImageFilename, incorrectImageFileName);
        frame.addWindowListener(new WindowListener() {
            public void windowActivated(final WindowEvent e) {
            }
            
            public void windowClosed(final WindowEvent e) {
            }
            
            public void windowClosing(final WindowEvent e) {
                Exercise.this.exit();
            }
            
            public void windowDeactivated(final WindowEvent e) {
            }
            
            public void windowDeiconified(final WindowEvent e) {
            }
            
            public void windowIconified(final WindowEvent e) {
            }
            
            public void windowOpened(final WindowEvent e) {
            }
        });
    }
    
    public Exercise(final String title, final Applet applet) {
        this(new Frame(), title, applet, "", "correct.gif", "incorrect.gif");
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getModifiers() > 0) {
            this.ok.setEnabled(true);
        }
        else {
            this.ok.setEnabled(false);
        }
    }
    
    public void addExerciseQuestion(final ExerciseQuestion exerciseQuestion) {
        this.exerciseQuestions.addElement(exerciseQuestion);
    }
    
    public void addExerciseQuestion(final String question) {
        final String[] data = new String[] { "", "", "" };
        boolean mode = false;
        int position = 0;
        for (StringTokenizer tokenizer = new StringTokenizer(question, "\t"); tokenizer.hasMoreTokens() && position < data.length; ++position) {
            data[position] = tokenizer.nextToken();
            if (position == data.length - 1) {
                final String lastToken = tokenizer.nextToken();
                if (lastToken.indexOf("y") != -1) {
                    mode = true;
                }
            }
        }
        this.addExerciseQuestion(new ExerciseQuestion(data[0], data[1], data[2], mode));
    }
    
    protected void exit() {
        if (this.dialog != null) {
            this.dialog.setVisible(false);
            this.dialog.dispose();
        }
        this.questionPosition = 0;
    }
    
    protected Panel getQuestionPanel(final boolean mark) {
        if (this.questionPosition >= 0 && this.questionPosition < this.exerciseQuestions.size()) {
            final ExerciseQuestion question = (ExerciseQuestion)this.exerciseQuestions.elementAt(this.questionPosition);
            Panel questionPanel;
            if (this.correctImage != null || this.incorrectImage != null) {
                int width = 0;
                int height = 0;
                if (this.correctImage != null) {
                    width = Math.max(this.correctImage.getWidth(this.dialog), width);
                    height = Math.max(this.correctImage.getHeight(this.dialog), height);
                }
                if (this.incorrectImage != null) {
                    width = Math.max(this.incorrectImage.getWidth(this.dialog), width);
                    height = Math.max(this.incorrectImage.getHeight(this.dialog), height);
                }
                questionPanel = question.getPanel(width, height, mark, this.correctImage, this.incorrectImage);
            }
            else {
                questionPanel = question.getPanel(mark);
            }
            this.currentQuestion = question;
            if (!mark) {
                this.currentQuestion.addActionListener(this);
            }
            return questionPanel;
        }
        return null;
    }
    
    protected void init(final boolean mark) {
        if (this.dialog != null) {
            this.dialog.setVisible(false);
            this.dialog.dispose();
        }
        this.dialog = new Dialog(this.frame, this.title, true);
        this.dialog.setLayout(new BorderLayout());
        this.questionPanel = this.getQuestionPanel(mark);
        if (this.questionPanel != null) {
            this.dialog.add(this.questionPanel, "Center");
        }
        final Panel controlPanel = new Panel();
        controlPanel.add(this.ok);
        controlPanel.add(this.next);
        controlPanel.add(this.exit);
        this.dialog.add(controlPanel, "South");
        this.dialog.pack();
    }
    
    protected void initaliseButtons() {
        this.ok = new Button(Messages.getString("Exercise.17"));
        this.next = new Button(Messages.getString("Exercise.18"));
        this.exit = new Button(Messages.getString("Exercise.19"));
        this.ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (Exercise.this.currentQuestion != null) {
                    Exercise.this.currentQuestion.removeAllActionListeners();
                    Exercise.this.ok.setEnabled(false);
                    if (Exercise.this.questionPosition < Exercise.this.exerciseQuestions.size() - 1) {
                        Exercise.this.next.setEnabled(true);
                    }
                    else {
                        Exercise.this.next.setEnabled(false);
                    }
                    Exercise.this.init(true);
                    Exercise.this.showDialog();
                }
            }
        });
        this.next.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (Exercise.this.questionPosition < Exercise.this.exerciseQuestions.size() - 1) {
                    final Exercise this$0 = Exercise.this;
                    this$0.questionPosition = this$0.questionPosition + 1;
                    Exercise.this.ok.setEnabled(false);
                    Exercise.this.next.setEnabled(false);
                    Exercise.this.init(false);
                    Exercise.this.showDialog();
                }
            }
        });
        this.exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Exercise.this.exit();
            }
        });
        this.ok.setEnabled(false);
        this.next.setEnabled(false);
        this.exit.setEnabled(true);
    }
    
    public void initialiseExercise(final String dataPath, final String fileName) {
        this.exerciseQuestions = new Vector();
        this.questionPosition = 0;
        URL url = null;
        try {
            url = new URL(dataPath + fileName);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: com.cim.AIA.Exercise " + e);
            return;
        }
        while (true) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            }
            catch (FileNotFoundException e2) {
                System.out.println("FileNotFound: com.cim.AIA.Exercise " + e2);
                return;
            }
            catch (IOException e2) {
                System.out.println("com.cim.AIA.Exercise: IOEXCEPTION" + e2);
                return;
            }
            Label_0163:
            {
                Label_0178:
                {
                    final String line;
                    try {
                        line = in.readLine();
                        if (line != null) {
                            break Label_0178;
                        }
                    }
                    catch (IOException) {
                        final IOException ex;
                        final IOException e3 = ex;
                        System.out.println("com.cim.AIA.Exercise: IOEXCEPTION" + e3);
                        return;
                    }
                    break;
                    try {
                        String tempLine = "";
                        final StringTokenizer st = new StringTokenizer(line, "`");
                        while (st.hasMoreTokens()) {
                            tempLine = tempLine + st.nextToken() + "\n";
                        }
                        this.addExerciseQuestion(tempLine);
                        break Label_0163;
                    }
                    catch (IOException) {}
                }
            }
            break;
        }
    }
    
    protected void loadImages(final Applet applet, final String imgDir, final String correctImageFilename, final String incorrectImageFilename) {
        final MediaTracker tracker = new MediaTracker(this.dialog);
        try {
            URL url = new URL(imgDir + correctImageFilename);
            URL otherUrl = new URL(applet.getCodeBase() + "images/" + correctImageFilename);
            InputStream i1 = applet.getClass().getResourceAsStream("images/" + correctImageFilename);
            this.correctImage = StreamImage.getImage(i1);
            if (this.correctImage == null) {
                System.out.println("Correct Image is loading from local..." + otherUrl.toString());
                this.correctImage = applet.getAppletContext().getImage(otherUrl);
            }
            if (this.correctImage != null) {
                tracker.addImage(this.correctImage, 0);
            }
            else {
                applet.showStatus("com.cim.AIA.Exercise: Unable to load image: " + url);
                System.out.println("com.cim.AIA.Exercise:  Unable to load image: " + url);
            }
            i1 = applet.getClass().getResourceAsStream("images/" + incorrectImageFilename);
            url = new URL(imgDir + incorrectImageFilename);
            otherUrl = new URL(applet.getCodeBase() + "images/" + incorrectImageFilename);
            this.incorrectImage = StreamImage.getImage(i1);
            if (this.incorrectImage == null) {
                System.out.println("Incorrect Image is loading from local..." + otherUrl.toString());
                this.incorrectImage = applet.getAppletContext().getImage(otherUrl);
            }
            if (this.incorrectImage != null) {
                tracker.addImage(this.incorrectImage, 0);
            }
            else {
                applet.showStatus("com.cim.AIA.Exercise: Unable to load image: " + url);
                System.out.println("com.cim.AIA.Exercise: Unable to load image: " + url);
            }
        }
        catch (MalformedURLException e) {
            applet.showStatus("com.cim.AIA.Exercise: Invalid URL: " + e);
            System.out.println("com.cim.AIA.Exercise: Invalid URL: " + e);
        }
        try {
            tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
    
    protected void showDialog() {
        final Dimension screenSize = this.frame.getToolkit().getScreenSize();
        this.dialog.setLocation((screenSize.width - this.dialog.getSize().width) / 2, (screenSize.height - this.dialog.getSize().height) / 2);
        this.dialog.setVisible(true);
    }
    
    public void start() {
        this.init(false);
        this.showDialog();
        for (int i = 0; i < this.exerciseQuestions.size(); ++i) {
            ((ExerciseQuestion)this.exerciseQuestions.elementAt(i)).reinitialise();
        }
    }
}
