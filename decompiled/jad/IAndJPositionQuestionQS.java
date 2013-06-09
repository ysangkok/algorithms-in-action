// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IAndJPositionQuestionQS.java

import com.cim.AIA.Question;
import com.cim.AIA.Selectable;
import java.util.Vector;
import localization.Messages;

public class IAndJPositionQuestionQS extends Question
{

    public IAndJPositionQuestionQS()
    {
        super(QUESTION_NAME, QUESTION_SWAP_LABEL, 3);
        correctMessage = CORRECT;
        inCorrectMessage = INCORRECT;
        tooManyAttemptsMessage = TOO_MANY;
        attemtpsAllowable = 3;
        inCorrectModalMode = false;
        displayInstructionsAgainIfWrong = false;
    }

    public void addAnswer(Integer index)
    {
        super.addAnswer(index);
    }

    protected void gotCorrectAnswer()
    {
    }

    protected void gotInCorrectAnswer()
    {
    }

    protected boolean isCorrect(Vector selected)
    {
        if(selected.size() < answers.size())
            return false;
        Vector correct = new Vector();
        int correctCount = 0;
label0:
        for(int i = 0; i < selected.size(); i++)
        {
            Selectable selectedItem = (Selectable)(Selectable)selected.elementAt(i);
            int j = 0;
            do
            {
                if(j >= answers.size())
                    continue label0;
                Integer answerItem = (Integer)(Integer)answers.elementAt(j);
                if(answerItem.intValue() == selectedItem.getIdentifier())
                {
                    boolean newAnswer = true;
                    for(int k = 0; k < correct.size(); k++)
                        if(((Integer)correct.elementAt(k)).intValue() == answerItem.intValue())
                            newAnswer = false;

                    if(newAnswer)
                    {
                        correctCount++;
                        correct.addElement(answerItem);
                    }
                    continue label0;
                }
                j++;
            } while(true);
        }

        return correctCount >= answers.size();
    }

    protected boolean isInCorrect(Vector selected)
    {
        if(selected.size() < answers.size())
            return false;
        Vector correct = new Vector();
        int correctCount = 0;
label0:
        for(int i = 0; i < selected.size(); i++)
        {
            Selectable selectedItem = (Selectable)(Selectable)selected.elementAt(i);
            int j = 0;
            do
            {
                if(j >= answers.size())
                    continue label0;
                Integer answerItem = (Integer)(Integer)answers.elementAt(j);
                if(answerItem.intValue() == selectedItem.getIdentifier())
                {
                    boolean newAnswer = true;
                    for(int k = 0; k < correct.size(); k++)
                        if(((Integer)correct.elementAt(k)).intValue() == answerItem.intValue())
                            newAnswer = false;

                    if(newAnswer)
                    {
                        correctCount++;
                        correct.addElement(answerItem);
                    }
                    continue label0;
                }
                j++;
            } while(true);
        }

        return correctCount <= answers.size();
    }

    protected void tooManyWrongAnswers()
    {
        if(animationWindow != null)
            ((QuickSortAnimationWindow)animationWindow).setNormalMode();
    }

    protected void unHighlightAllSelected()
    {
        for(int i = 0; i < selected.size(); i++)
            ((Selectable)(Selectable)selected.elementAt(i)).unHighlight();

    }

    protected static final String QUESTION_NAME = Messages.getString("IAndJPositionQuestionQS.0");
    protected static final String QUESTION_END_UP_LABEL = Messages.getString("IAndJPositionQuestionQS.1");
    protected static final String QUESTION_SWAP_LABEL = Messages.getString("IAndJPositionQuestionQS.2");
    protected static final int ATTEMPTS_ALLOWABLE = 3;
    protected static final String CORRECT = Messages.getString("IAndJPositionQuestionQS.3");
    protected static final String INCORRECT = Messages.getString("IAndJPositionQuestionQS.4");
    protected static final String TOO_MANY = (new StringBuilder()).append(Messages.getString("IAndJPositionQuestionQS.5")).append(3).append(Messages.getString("IAndJPositionQuestionQS.6")).toString();

}
