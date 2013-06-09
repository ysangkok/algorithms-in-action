package aia.graph.common;

import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class NextNewLinkQuestion extends Question
{
    protected static final String NAME;
    protected static final String INSTRUCTIONS;
    protected static final int ATTEMPTS_ALLOWED = 3;
    protected static final String TOO_MANY;
    protected static final String CORRECT_MESSAGE;
    protected static final String INCORRECT_MESSAGE;
    protected int from_node;
    protected int to_node;
    
    public NextNewLinkQuestion(final int p_from_node, final int p_to_node) {
        super(NextNewLinkQuestion.NAME, NextNewLinkQuestion.INSTRUCTIONS, 3);
        this.from_node = p_from_node;
        this.to_node = p_to_node;
        this.tooManyAttemptsMessage = NextNewLinkQuestion.TOO_MANY;
        this.correctMessage = NextNewLinkQuestion.CORRECT_MESSAGE;
        this.inCorrectMessage = NextNewLinkQuestion.INCORRECT_MESSAGE;
    }
    
    protected boolean isCorrect(final Vector<E> selected) {
        boolean gotFirst = false;
        boolean gotSecond = false;
        for (int i = 0; i < selected.size(); ++i) {
            final Selectable selectedItem = (Selectable)((Selectable)selected.elementAt(i));
            if (selectedItem.getIdentifier() == this.from_node) {
                gotFirst = true;
            }
            else if (selectedItem.getIdentifier() == this.to_node) {
                gotSecond = true;
            }
            if (gotFirst && gotSecond) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isInCorrect(final Vector<E> selected) {
        return selected.size() >= 2 && !this.isCorrect(selected);
    }
    
    protected void tooManyWrongAnswers() {
        if (NextNewLinkQuestion.animationWindow != null) {
            NextNewLinkQuestion.animationWindow.informModeListeners(new ModeEvent(this, 12356));
        }
        else {
            System.out.println(Messages.getString("NextNewLinkQuestion.6"));
        }
    }
    
    static {
        NAME = Messages.getString("NextNewLinkQuestion.0");
        INSTRUCTIONS = Messages.getString("NextNewLinkQuestion.1");
        TOO_MANY = Messages.getString("NextNewLinkQuestion.2") + Messages.getString("NextNewLinkQuestion.3");
        CORRECT_MESSAGE = Messages.getString("NextNewLinkQuestion.4");
        INCORRECT_MESSAGE = Messages.getString("NextNewLinkQuestion.5");
    }
}
