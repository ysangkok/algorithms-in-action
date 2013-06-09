package com.cim.AIA;

import com.cim.common.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class ExerciseQuestion implements ItemListener
{
    protected String questionString;
    protected Vector<ExerciseQuestionOption> possibleAnswers;
    protected Vector<Integer> correctAnswers;
    protected Vector<E> canvases;
    protected boolean radioMode;
    protected CheckboxGroup checkboxGroup;
    protected NonRadioCheckboxGroup nonRadioCheckboxGroup;
    protected Vector<Checkbox> checkboxes;
    protected Vector<ActionListener> actionListeners;
    protected int numberOfItemsSelected;
    
    public ExerciseQuestion(final String question, final boolean radioMode) {
        super();
        this.radioMode = true;
        this.numberOfItemsSelected = 0;
        this.possibleAnswers = new Vector();
        this.correctAnswers = new Vector();
        this.questionString = question;
        this.radioMode = radioMode;
        this.checkboxGroup = new CheckboxGroup();
        this.checkboxes = new Vector();
        this.nonRadioCheckboxGroup = new NonRadioCheckboxGroup();
        this.actionListeners = new Vector();
    }
    
    public ExerciseQuestion(final String question, final String possibleAnswers, final String correctAnswer, final boolean radioMode) {
        this(question, radioMode);
        final StringTokenizer tokenizer = new StringTokenizer(possibleAnswers, "|");
        while (tokenizer.hasMoreTokens()) {
            this.possibleAnswers.addElement(new ExerciseQuestionOption(tokenizer.nextToken()));
        }
        final StringTokenizer st = new StringTokenizer(correctAnswer, "|");
        while (st.hasMoreTokens()) {
            try {
                this.correctAnswers.addElement(new Integer(st.nextToken()));
                continue;
            }
            catch (NumberFormatException e) {
                System.out.println("com.cim.AIA.ExerciseQuestion Incorrect Format of correct answer at question: '" + question + "'.");
                continue;
            }
            break;
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListeners.addElement(actionListener);
    }
    
    public void addOption(final ExerciseQuestionOption option, final boolean correct) {
        this.possibleAnswers.addElement(option);
        if (correct) {
            this.correctAnswers.addElement(new Integer(this.possibleAnswers.indexOf(option) + 1));
        }
    }
    
    protected Panel getAllOptionsPanel(final int width, final int height, final boolean mark, final Image correctImage, final Image incorrectImage) {
        final Panel main = new Panel();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        main.setLayout(gridBagLayout);
        constraints.insets = new Insets(1, 1, 1, 1);
        constraints.anchor = 18;
        for (int i = 0; i < this.possibleAnswers.size(); ++i) {
            final ExerciseQuestionOption option = (ExerciseQuestionOption)this.possibleAnswers.elementAt(i);
            final Panel optionPanel = this.getOptionAndExplanationPanel(option, i, mark);
            final ImageCanvas canvas = new ImageCanvas();
            canvas.setSize(width, height);
            if (mark) {
                final boolean correct = this.isCorrectAnswer(option.getOption());
                final Checkbox checkbox = (Checkbox)this.checkboxes.elementAt(i);
                final boolean answer = checkbox.getState();
                if (correct) {
                    checkbox.setBackground(Color.green);
                }
                else {
                    checkbox.setBackground(Color.red);
                }
                if (correct && answer) {
                    if (correctImage != null) {
                        canvas.setImage(correctImage);
                    }
                    else {
                        canvas.setBackground(Color.green);
                    }
                }
                else if (answer && !correct) {
                    if (incorrectImage != null) {
                        canvas.setImage(incorrectImage);
                    }
                    else {
                        canvas.setBackground(Color.red);
                    }
                }
            }
            constraints.gridwidth = width;
            gridBagLayout.setConstraints(canvas, constraints);
            main.add(canvas);
            constraints.gridwidth = 0;
            gridBagLayout.setConstraints(optionPanel, constraints);
            main.add(optionPanel);
        }
        return main;
    }
    
    protected Panel getOptionAndExplanationPanel(final ExerciseQuestionOption option, final int pos, final boolean mark) {
        final Panel main = new Panel();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        main.setLayout(gridBagLayout);
        constraints.insets = new Insets(0, 2, 0, 2);
        constraints.anchor = 18;
        constraints.gridwidth = 0;
        constraints.fill = 0;
        final Panel panel = this.getOptionPanel(option, pos, mark);
        gridBagLayout.setConstraints(panel, constraints);
        main.add(panel);
        String temp = "";
        if (mark) {
            temp = option.getExplanation();
        }
        final MultiLineLabel explanation = new MultiLineLabel(temp, 1, 1, 0);
        constraints.anchor = 17;
        constraints.fill = 0;
        gridBagLayout.setConstraints(explanation, constraints);
        main.add(explanation);
        return main;
    }
    
    protected Panel getOptionPanel(final ExerciseQuestionOption option, final int pos, final boolean mark) {
        final Panel panel = new Panel();
        Checkbox checkbox;
        if (!mark) {
            checkbox = new Checkbox(option.getOption(), false);
            checkbox.addItemListener(this);
            this.checkboxes.addElement(checkbox);
            if (this.radioMode) {
                checkbox.setCheckboxGroup(this.checkboxGroup);
            }
            else {
                this.nonRadioCheckboxGroup.add(checkbox);
            }
        }
        else {
            checkbox = (Checkbox)this.checkboxes.elementAt(pos);
        }
        panel.add(checkbox);
        return panel;
    }
    
    public Panel getPanel(final boolean mark) {
        return this.getPanel(30, 30, mark, null, null);
    }
    
    public Panel getPanel(final int width, final int height, final boolean mark, final Image correctImage, final Image incorrectImage) {
        final Panel panel = new Panel();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(gridBagLayout);
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.gridwidth = 0;
        constraints.anchor = 11;
        final MultiLineLabel multiLineLabel;
        final MultiLineLabel label = multiLineLabel = new MultiLineLabel(this.questionString, 1, 1, 1);
        multiLineLabel.setFont(new Font("Helvetica", 1, 14));
        gridBagLayout.setConstraints(label, constraints);
        panel.add(label);
        constraints.anchor = 18;
        final Panel options = this.getAllOptionsPanel(width, height, mark, correctImage, incorrectImage);
        gridBagLayout.setConstraints(options, constraints);
        panel.add(options);
        return panel;
    }
    
    public String getQuestionDescription() {
        return this.questionString;
    }
    
    protected void informActionListeners() {
        final ActionEvent e = new ActionEvent(this, this.numberOfItemsSelected, "", this.numberOfItemsSelected);
        for (int i = 0; i < this.actionListeners.size(); ++i) {
            ((ActionListener)this.actionListeners.elementAt(i)).actionPerformed(e);
        }
    }
    
    protected boolean isCorrectAnswer(final String answer) {
        for (int i = 0; i < this.correctAnswers.size(); ++i) {
            final int correctAnswer = (Integer)this.correctAnswers.elementAt(i) - 1;
            final String possibleAnswer = ((ExerciseQuestionOption)this.possibleAnswers.elementAt(correctAnswer)).getOption();
            if (answer.equals(possibleAnswer)) {
                return true;
            }
        }
        return false;
    }
    
    public void itemStateChanged(final ItemEvent e) {
        final Checkbox checkbox = (Checkbox)e.getSource();
        if (checkbox.getState()) {
            this.numberOfItemsSelected = this.numberOfItemsSelected + 1;
        }
        else {
            this.numberOfItemsSelected = this.numberOfItemsSelected - 1;
        }
        this.informActionListeners();
    }
    
    public void reinitialise() {
        this.checkboxGroup = new CheckboxGroup();
        this.checkboxes = new Vector();
        this.nonRadioCheckboxGroup = new NonRadioCheckboxGroup();
        this.actionListeners = new Vector();
    }
    
    public void removeAllActionListeners() {
        this.actionListeners.removeAllElements();
    }
}
