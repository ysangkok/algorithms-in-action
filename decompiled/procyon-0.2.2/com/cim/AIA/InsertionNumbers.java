package com.cim.AIA;

public class InsertionNumbers
{
    protected int[] numbers;
    protected int position;
    
    public InsertionNumbers(final int[] numbers) {
        super();
        this.position = 0;
        this.setNumbers(numbers);
    }
    
    public int getNextNumber() {
        if (!this.isFinished()) {
            final int[] numbers = this.numbers;
            final int position = this.position;
            this.position = position + 1;
            return numbers[position];
        }
        return -1;
    }
    
    public int[] getNumbersDone() {
        final int[] data = new int[this.position];
        System.arraycopy(this.numbers, 0, data, 0, this.position);
        return data;
    }
    
    public int[] getNumbersToGo() {
        final int[] data = new int[this.numbers.length - this.position];
        System.arraycopy(this.numbers, this.position, data, 0, data.length);
        return data;
    }
    
    public boolean isFinished() {
        return this.position >= this.numbers.length;
    }
    
    public void setNumbers(final int[] numbers) {
        this.numbers = new int[numbers.length];
        System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
        this.position = 0;
    }
}
