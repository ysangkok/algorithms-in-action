package com.cim.AIA;

import java.util.*;

public class RandomSameIntArrayDataSelection extends DataSelection
{
    private static final long serialVersionUID = -4400211993253155548L;
    protected static Random random;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxRandomValue;
    protected int minRandomValue;
    
    public RandomSameIntArrayDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final int minimumNumberOfElements, final int maximumNumberOfElements, final int minRandomValue, final int maxRandomValue) {
        super(name, state, animWindow);
        this.maximumNumberOfElements = maximumNumberOfElements;
        if (minimumNumberOfElements < maximumNumberOfElements) {
            this.minimumNumberOfElements = minimumNumberOfElements;
        }
        else {
            this.minimumNumberOfElements = maximumNumberOfElements;
        }
        this.maxRandomValue = maxRandomValue;
        if (minRandomValue < maxRandomValue) {
            this.minRandomValue = minRandomValue;
        }
        else {
            this.minRandomValue = maxRandomValue;
        }
    }
    
    public Copyable getData() {
        final int size = (int)(RandomSameIntArrayDataSelection.random.nextFloat() * (float)(this.maximumNumberOfElements - this.minimumNumberOfElements + 1)) + this.minimumNumberOfElements;
        final int[] randomArray = new int[size];
        final int value = (int)(RandomSameIntArrayDataSelection.random.nextFloat() * (float)(this.maxRandomValue - this.minRandomValue + 1)) + this.minRandomValue;
        for (int i = 0; i < randomArray.length; ++i) {
            randomArray[i] = value;
        }
        return new IntArray(randomArray);
    }
    
    static {
        RandomSameIntArrayDataSelection.random = new Random();
    }
}
