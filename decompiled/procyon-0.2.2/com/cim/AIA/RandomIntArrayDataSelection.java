package com.cim.AIA;

import java.util.*;

public class RandomIntArrayDataSelection extends DataSelection
{
    private static final long serialVersionUID = -8838631254913670936L;
    protected static Random random;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected int maxRandomValue;
    protected int minRandomValue;
    
    public RandomIntArrayDataSelection(final String name, final boolean state, final AnimationWindow animWindow, final int minimumNumberOfElements, final int maximumNumberOfElements, final int minRandomValue, final int maxRandomValue) {
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
        final int size = (int)(RandomIntArrayDataSelection.random.nextFloat() * (float)(this.maximumNumberOfElements - this.minimumNumberOfElements + 1)) + this.minimumNumberOfElements;
        final int[] randomArray = new int[size];
        for (int i = 0; i < randomArray.length; ++i) {
            randomArray[i] = (int)(RandomIntArrayDataSelection.random.nextFloat() * (float)(this.maxRandomValue - this.minRandomValue + 1)) + this.minRandomValue;
        }
        return new IntArray(randomArray);
    }
    
    static {
        RandomIntArrayDataSelection.random = new Random();
    }
}
