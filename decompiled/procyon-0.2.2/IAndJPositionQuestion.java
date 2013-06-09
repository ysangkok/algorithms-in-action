import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class IAndJPositionQuestion extends Question
{
    public static final String QUESTION_J_LABEL;
    public static final String QUESTION_SWAP_LABEL;
    protected static final String QUESTION_NAME;
    protected static final int ATTEMPTS_ALLOWABLE = 3;
    protected static final String CORRECT;
    protected static final String INCORRECT;
    protected static final String TOO_MANY;
    
    public IAndJPositionQuestion() {
        super(IAndJPositionQuestion.QUESTION_NAME, IAndJPositionQuestion.QUESTION_J_LABEL, 3);
        this.correctMessage = IAndJPositionQuestion.CORRECT;
        this.inCorrectMessage = IAndJPositionQuestion.INCORRECT;
        this.tooManyAttemptsMessage = IAndJPositionQuestion.TOO_MANY;
        this.attemtpsAllowable = 3;
        this.inCorrectModalMode = false;
        this.displayInstructionsAgainIfWrong = false;
    }
    
    public void addAnswer(final Integer index) {
        super.addAnswer(index);
    }
    
    protected void gotCorrectAnswer() {
    }
    
    protected void gotInCorrectAnswer() {
    }
    
    protected boolean isCorrect(final Vector<Object> selected) {
        if (selected.size() < this.answers.size()) {
            return false;
        }
        final Vector<Integer> correct = new Vector();
        int correctCount = 0;
        for (int i = 0; i < selected.size(); ++i) {
            final Selectable selectedItem = (Selectable)((Selectable)selected.elementAt(i));
            int j = 0;
            while (j < this.answers.size()) {
                final Integer answerItem = (Integer)((Integer)this.answers.elementAt(j));
                if (answerItem == selectedItem.getIdentifier()) {
                    boolean newAnswer = true;
                    int k = 0;
                    while (k < correct.size()) {
                        if ((Integer)correct.elementAt(k) == answerItem) {
                            newAnswer = false;
                        }
                        ++k;
                    }
                    if (newAnswer) {
                        ++correctCount;
                        correct.addElement(answerItem);
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        return correctCount >= this.answers.size();
    }
    
    protected boolean isInCorrect(final Vector<Object> selected) {
        if (selected.size() < this.answers.size()) {
            return false;
        }
        final Vector<Integer> correct = new Vector();
        int correctCount = 0;
        for (int i = 0; i < selected.size(); ++i) {
            final Selectable selectedItem = (Selectable)((Selectable)selected.elementAt(i));
            int j = 0;
            while (j < this.answers.size()) {
                final Integer answerItem = (Integer)((Integer)this.answers.elementAt(j));
                if (answerItem == selectedItem.getIdentifier()) {
                    boolean newAnswer = true;
                    int k = 0;
                    while (k < correct.size()) {
                        if ((Integer)correct.elementAt(k) == answerItem) {
                            newAnswer = false;
                        }
                        ++k;
                    }
                    if (newAnswer) {
                        ++correctCount;
                        correct.addElement(answerItem);
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        return correctCount <= this.answers.size();
    }
    
    protected void tooManyWrongAnswers() {
        if (IAndJPositionQuestion.animationWindow != null) {
            ((HeapSortAnimationWindow)IAndJPositionQuestion.animationWindow).setNormalMode();
        }
    }
    
    protected void unHighlightAllSelected() {
        for (int i = 0; i < this.selected.size(); ++i) {
            ((Selectable)((Selectable)this.selected.elementAt(i))).unHighlight();
        }
    }
    
    static {
        QUESTION_J_LABEL = Messages.getString("IAndJPositionQuestion.0") + HeapSortAnimationWindow.FRAME_TITLE + Messages.getString("IAndJPositionQuestion.1");
        QUESTION_SWAP_LABEL = Messages.getString("IAndJPositionQuestion.2") + HeapSortAnimationWindow.FRAME_TITLE + Messages.getString("IAndJPositionQuestion.3");
        QUESTION_NAME = Messages.getString("IAndJPositionQuestion.4");
        CORRECT = Messages.getString("IAndJPositionQuestion.5");
        INCORRECT = Messages.getString("IAndJPositionQuestion.6");
        TOO_MANY = Messages.getString("IAndJPositionQuestion.7") + 3 + Messages.getString("IAndJPositionQuestion.8");
    }
}
