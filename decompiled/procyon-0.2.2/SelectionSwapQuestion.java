import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class SelectionSwapQuestion extends Question
{
    protected static final String NAME;
    protected static final String INSTRUCTIONS;
    protected static final int ATTEMPTS_ALLOWED = 3;
    protected static final String TOO_MANY;
    protected static final String CORRECT_MESSAGE;
    protected static final String INCORRECT_MESSAGE;
    protected int first;
    protected int second;
    
    public SelectionSwapQuestion(final int first, final int second) {
        super(SelectionSwapQuestion.NAME, SelectionSwapQuestion.INSTRUCTIONS, 3);
        this.first = first;
        this.second = second;
        this.tooManyAttemptsMessage = SelectionSwapQuestion.TOO_MANY;
        this.correctMessage = SelectionSwapQuestion.CORRECT_MESSAGE;
        this.inCorrectMessage = SelectionSwapQuestion.INCORRECT_MESSAGE;
    }
    
    protected boolean isCorrect(final Vector<E> selected) {
        boolean gotFirst = false;
        boolean gotSecond = false;
        for (int i = 0; i < selected.size(); ++i) {
            final Selectable selectedItem = (Selectable)((Selectable)selected.elementAt(i));
            if (selectedItem.getIdentifier() == this.first && !gotFirst) {
                gotFirst = true;
            }
            else if (selectedItem.getIdentifier() == this.second) {
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
        if (SelectionSwapQuestion.animationWindow != null) {
            SelectionSwapQuestion.animationWindow.informModeListeners(new ModeEvent(this, 12356));
        }
        else {
            System.out.println(Messages.getString("SelectionSwapQuestion.6"));
        }
    }
    
    static {
        NAME = Messages.getString("SelectionSwapQuestion.0");
        INSTRUCTIONS = Messages.getString("SelectionSwapQuestion.1");
        TOO_MANY = Messages.getString("SelectionSwapQuestion.2") + Messages.getString("SelectionSwapQuestion.3");
        CORRECT_MESSAGE = Messages.getString("SelectionSwapQuestion.4");
        INCORRECT_MESSAGE = Messages.getString("SelectionSwapQuestion.5");
    }
}
