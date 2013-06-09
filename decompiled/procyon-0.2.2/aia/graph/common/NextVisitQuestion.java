package aia.graph.common;

import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class NextVisitQuestion extends Question
{
    protected static final String NAME;
    protected static final String INSTRUCTIONS;
    protected static final int ATTEMPTS_ALLOWED = 3;
    protected static final String TOO_MANY;
    protected static final String CORRECT_MESSAGE;
    protected static final String INCORRECT_MESSAGE;
    protected int next_to_visit;
    
    public NextVisitQuestion(final int p_next_to_visit) {
        super(NextVisitQuestion.NAME, NextVisitQuestion.INSTRUCTIONS, 3);
        this.next_to_visit = p_next_to_visit;
        this.tooManyAttemptsMessage = NextVisitQuestion.TOO_MANY;
        this.correctMessage = NextVisitQuestion.CORRECT_MESSAGE;
        this.inCorrectMessage = NextVisitQuestion.INCORRECT_MESSAGE;
    }
    
    protected boolean isCorrect(final Vector<E> selected) {
        final boolean gotSecond = false;
        for (int i = 0; i < selected.size(); ++i) {
            final Selectable selectedItem = (Selectable)((Selectable)selected.elementAt(i));
            if (selectedItem.getIdentifier() == this.next_to_visit) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isInCorrect(final Vector<E> selected) {
        return selected.size() >= 1 || !this.isCorrect(selected);
    }
    
    protected void tooManyWrongAnswers() {
        if (NextVisitQuestion.animationWindow != null) {
            NextVisitQuestion.animationWindow.informModeListeners(new ModeEvent(this, 12356));
        }
        else {
            System.out.println(Messages.getString("NextVisitQuestion.6"));
        }
    }
    
    static {
        NAME = Messages.getString("NextVisitQuestion.0");
        INSTRUCTIONS = Messages.getString("NextVisitQuestion.1");
        TOO_MANY = Messages.getString("NextVisitQuestion.2") + Messages.getString("NextVisitQuestion.3");
        CORRECT_MESSAGE = Messages.getString("NextVisitQuestion.4");
        INCORRECT_MESSAGE = Messages.getString("NextVisitQuestion.5");
    }
}
