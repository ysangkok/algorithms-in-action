package com.cim.AIA;

import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.common.*;

public abstract class Question implements SelectionListener, Finishable, ExitListener
{
    protected static AnimationWindow animationWindow;
    public static final int INFINATE_ATTEMPTS = -1;
    protected static MessageDialog messageDialog;
    protected static Point messageDialogPoint;
    protected Vector<FinishListener> finishListeners;
    protected String name;
    protected String questionInstructions;
    protected String title;
    protected String correctMessage;
    protected String inCorrectMessage;
    protected String tooManyAttemptsMessage;
    protected Vector<Object> answers;
    protected int timesWrong;
    protected int attemtpsAllowable;
    protected Vector<Object> selected;
    protected boolean enabled;
    protected boolean questionInstructionsModalMode;
    protected boolean correctModalMode;
    protected boolean timesWrongModalMode;
    protected boolean inCorrectModalMode;
    protected boolean displayInstructionsAgainIfWrong;
    protected boolean displayCorrectMessage;
    protected boolean displayInCorrectMessage;
    protected boolean displayTooManyAttemptsMessage;
    
    public static void closeDialog() {
        if (Question.messageDialog != null) {
            Question.messageDialog.setVisible(false);
            Question.messageDialog.dispose();
        }
    }
    
    public static void setAnimationWindow(final AnimationWindow theAnimationWindow) {
        Question.animationWindow = theAnimationWindow;
    }
    
    public Question(final String name, final String questionInstructions, final int attemtpsAllowable) {
        super();
        this.finishListeners = new Vector();
        this.title = Messages.getString("Question.0");
        this.correctMessage = Messages.getString("Question.1");
        this.inCorrectMessage = Messages.getString("Question.2");
        this.tooManyAttemptsMessage = Messages.getString("Question.3");
        this.timesWrong = 0;
        this.attemtpsAllowable = 1;
        this.enabled = true;
        this.questionInstructionsModalMode = false;
        this.correctModalMode = false;
        this.timesWrongModalMode = true;
        this.inCorrectModalMode = true;
        this.displayInstructionsAgainIfWrong = true;
        this.displayCorrectMessage = true;
        this.displayInCorrectMessage = true;
        this.displayTooManyAttemptsMessage = true;
        this.name = name;
        this.questionInstructions = questionInstructions;
        this.attemtpsAllowable = attemtpsAllowable;
        this.answers = new Vector();
        this.selected = new Vector();
    }
    
    public void addAnswer(final Object answer) {
        if (answer != null) {
            this.answers.addElement(answer);
        }
    }
    
    public void addFinishListener(final FinishListener finishListener) {
        this.finishListeners.addElement(finishListener);
    }
    
    public void ask() {
        if (this.enabled) {
            this.selected = new Vector();
            if (this.attemtpsAllowable == -1) {
                this.displayMessage(this.questionInstructions, this.questionInstructionsModalMode);
            }
            else if (this.timesWrong < this.attemtpsAllowable) {
                this.displayMessage(this.questionInstructions, this.questionInstructionsModalMode);
            }
        }
    }
    
    protected void displayMessage(final String label, final boolean modalMode) {
        boolean okButton = false;
        if (modalMode) {
            okButton = true;
        }
        Question.messageDialogPoint = null;
        if (Question.messageDialog != null) {
            final Point temp = Question.messageDialog.getLocation();
            if (temp.x != 0 && temp.y != 0) {
                Question.messageDialogPoint = new Point(temp.x, temp.y);
            }
            Question.messageDialog.setVisible(false);
            Question.messageDialog.dispose();
        }
        Question.messageDialog = new MessageDialog(label, modalMode, okButton);
        if (Question.messageDialogPoint != null) {
            Question.messageDialog.setLocation(Question.messageDialogPoint);
        }
        Question.messageDialog.setTitle(this.title);
        Question.messageDialog.setVisible(true);
    }
    
    protected void finish() {
        final FinishEvent finishEvent = new FinishEvent(this);
        for (int i = 0; i < this.finishListeners.size(); ++i) {
            ((FinishListener)this.finishListeners.elementAt(i)).processFinishEvent(finishEvent);
        }
        this.enabled = false;
    }
    
    protected void gotCorrectAnswer() {
    }
    
    protected void gotInCorrectAnswer() {
    }
    
    protected abstract boolean isCorrect(final Vector<Object> p0);
    
    protected abstract boolean isInCorrect(final Vector<Object> p0);
    
    public void processExitEvent(final ExitEvent e) {
        this.finish();
        this.finishListeners.removeAllElements();
        Question.messageDialog.setVisible(false);
        Question.messageDialog.dispose();
    }
    
    public void processSelectionEvent(final SelectionEvent selectionEvent) {
        if (!this.enabled) {
            return;
        }
        final Object selection = selectionEvent.paramObj;
        this.selected.addElement(selection);
        if (this.isCorrect(this.selected)) {
            if (this.displayCorrectMessage) {
                this.displayMessage(this.correctMessage, this.correctModalMode);
            }
            this.gotCorrectAnswer();
            this.finish();
        }
        else if (this.isInCorrect(this.selected)) {
            this.timesWrong = this.timesWrong + 1;
            if (this.displayInCorrectMessage) {
                this.displayMessage(this.inCorrectMessage, this.inCorrectModalMode);
            }
            this.gotInCorrectAnswer();
            if (this.attemtpsAllowable != -1 && this.timesWrong >= this.attemtpsAllowable) {
                if (this.displayTooManyAttemptsMessage) {
                    this.displayMessage(this.tooManyAttemptsMessage, this.timesWrongModalMode);
                }
                this.tooManyWrongAnswers();
                this.finish();
                return;
            }
            ((Questionable)((Questionable)selectionEvent.getSource())).removeAllHighlight();
            this.selected = new Vector();
            if (this.displayInstructionsAgainIfWrong) {
                this.ask();
            }
        }
    }
    
    public void removeFinishListener(final FinishListener finishListener) {
        this.finishListeners.removeElement(finishListener);
    }
    
    public void reset() {
        this.timesWrong = 0;
        if (Question.messageDialog != null) {
            Question.messageDialog.dispose();
        }
        this.selected = new Vector();
        this.enabled = true;
    }
    
    public void setInstructions(final String instructions) {
        this.questionInstructions = instructions;
    }
    
    protected void tooManyWrongAnswers() {
    }
}
