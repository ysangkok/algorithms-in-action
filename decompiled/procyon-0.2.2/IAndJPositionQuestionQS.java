import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class IAndJPositionQuestionQS extends Question
{
    protected static final String QUESTION_NAME;
    protected static final String QUESTION_END_UP_LABEL;
    protected static final String QUESTION_SWAP_LABEL;
    protected static final int ATTEMPTS_ALLOWABLE = 3;
    protected static final String CORRECT;
    protected static final String INCORRECT;
    protected static final String TOO_MANY;
    
    public IAndJPositionQuestionQS() {
        super(IAndJPositionQuestionQS.QUESTION_NAME, IAndJPositionQuestionQS.QUESTION_SWAP_LABEL, 3);
        this.correctMessage = IAndJPositionQuestionQS.CORRECT;
        this.inCorrectMessage = IAndJPositionQuestionQS.INCORRECT;
        this.tooManyAttemptsMessage = IAndJPositionQuestionQS.TOO_MANY;
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
    
    protected boolean isCorrect(final Vector<E> selected) {
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
    
    protected boolean isInCorrect(final Vector<E> selected) {
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
        if (IAndJPositionQuestionQS.animationWindow != null) {
            ((QuickSortAnimationWindow)IAndJPositionQuestionQS.animationWindow).setNormalMode();
        }
    }
    
    protected void unHighlightAllSelected() {
        for (int i = 0; i < this.selected.size(); ++i) {
            ((Selectable)((Selectable)this.selected.elementAt(i))).unHighlight();
        }
    }
    
    static {
        QUESTION_NAME = Messages.getString("IAndJPositionQuestionQS.0");
        QUESTION_END_UP_LABEL = Messages.getString("IAndJPositionQuestionQS.1");
        QUESTION_SWAP_LABEL = Messages.getString("IAndJPositionQuestionQS.2");
        CORRECT = Messages.getString("IAndJPositionQuestionQS.3");
        INCORRECT = Messages.getString("IAndJPositionQuestionQS.4");
        TOO_MANY = Messages.getString("IAndJPositionQuestionQS.5") + 3 + Messages.getString("IAndJPositionQuestionQS.6");
    }
}
