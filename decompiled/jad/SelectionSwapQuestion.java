// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectionSwapQuestion.java

import com.cim.AIA.*;
import java.io.PrintStream;
import java.util.Vector;
import localization.Messages;

public class SelectionSwapQuestion extends Question
{

    public SelectionSwapQuestion(int first, int second)
    {
        super(NAME, INSTRUCTIONS, 3);
        this.first = first;
        this.second = second;
        tooManyAttemptsMessage = TOO_MANY;
        correctMessage = CORRECT_MESSAGE;
        inCorrectMessage = INCORRECT_MESSAGE;
    }

    protected boolean isCorrect(Vector selected)
    {
        boolean gotFirst = false;
        boolean gotSecond = false;
        for(int i = 0; i < selected.size(); i++)
        {
            Selectable selectedItem = (Selectable)(Selectable)selected.elementAt(i);
            if(selectedItem.getIdentifier() == first && !gotFirst)
                gotFirst = true;
            else
            if(selectedItem.getIdentifier() == second)
                gotSecond = true;
            if(gotFirst && gotSecond)
                return true;
        }

        return false;
    }

    protected boolean isInCorrect(Vector selected)
    {
        return selected.size() >= 2 && !isCorrect(selected);
    }

    protected void tooManyWrongAnswers()
    {
        if(animationWindow != null)
            animationWindow.informModeListeners(new ModeEvent(this, 12356));
        else
            System.out.println(Messages.getString("SelectionSwapQuestion.6"));
    }

    protected static final String NAME = Messages.getString("SelectionSwapQuestion.0");
    protected static final String INSTRUCTIONS = Messages.getString("SelectionSwapQuestion.1");
    protected static final int ATTEMPTS_ALLOWED = 3;
    protected static final String TOO_MANY = (new StringBuilder()).append(Messages.getString("SelectionSwapQuestion.2")).append(Messages.getString("SelectionSwapQuestion.3")).toString();
    protected static final String CORRECT_MESSAGE = Messages.getString("SelectionSwapQuestion.4");
    protected static final String INCORRECT_MESSAGE = Messages.getString("SelectionSwapQuestion.5");
    protected int first;
    protected int second;

}
