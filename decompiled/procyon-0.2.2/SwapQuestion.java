import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class SwapQuestion extends Question
{
    protected static final String NAME;
    protected static final String INSTRUCTIONS;
    protected static final int ATTEMPTS_ALLOWED = 3;
    protected static final String TOO_MANY;
    protected static final String CORRECT_MESSAGE;
    protected static final String INCORRECT_MESSAGE;
    protected int first;
    protected int second;
    
    public SwapQuestion(final int first, final int second) {
        super(SwapQuestion.NAME, SwapQuestion.INSTRUCTIONS, 3);
        this.first = first;
        this.second = second;
        this.tooManyAttemptsMessage = SwapQuestion.TOO_MANY;
        this.correctMessage = SwapQuestion.CORRECT_MESSAGE;
        this.inCorrectMessage = SwapQuestion.INCORRECT_MESSAGE;
    }
    
    protected boolean isCorrect(final Vector<Object> selected) {
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
    
    protected boolean isInCorrect(final Vector<Object> selected) {
        return selected.size() >= 2 && !this.isCorrect(selected);
    }
    
    protected void tooManyWrongAnswers() {
        if (SwapQuestion.animationWindow != null) {
            SwapQuestion.animationWindow.informModeListeners(new ModeEvent(this, 12356));
        }
        else {
            System.out.println(Messages.getString("SwapQuestion.6"));
        }
    }
    
    static {
        NAME = Messages.getString("SwapQuestion.0");
        INSTRUCTIONS = Messages.getString("SwapQuestion.1");
        TOO_MANY = Messages.getString("SwapQuestion.2") + Messages.getString("SwapQuestion.3");
        CORRECT_MESSAGE = Messages.getString("SwapQuestion.4");
        INCORRECT_MESSAGE = Messages.getString("SwapQuestion.5");
    }
}
