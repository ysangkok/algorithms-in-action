package com.cim.AIA;

import java.util.*;

public class ExerciseQuestionOption
{
    protected String option;
    protected String explanation;
    
    public ExerciseQuestionOption(final String optionAndExplanation) {
        super();
        final StringTokenizer tokenizer = new StringTokenizer(optionAndExplanation, "#");
        final String[] data = new String[] { "", "" };
        int pos = 0;
        while (tokenizer.hasMoreTokens() && pos < data.length) {
            data[pos] = data[pos] + tokenizer.nextToken();
            if (pos + 1 < data.length) {
                ++pos;
            }
        }
        this.option = data[0];
        this.explanation = data[1];
    }
    
    public ExerciseQuestionOption(final String option, final String explanation) {
        super();
        this.option = option;
        this.explanation = explanation;
    }
    
    public String getExplanation() {
        return this.explanation;
    }
    
    public String getOption() {
        return this.option;
    }
}
