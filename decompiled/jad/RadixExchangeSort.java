// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixExchangeSort.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.Vector;
import localization.Messages;

public class RadixExchangeSort extends Algorithm
    implements ColorSelectionListener
{

    protected RadixExchangeSort(AlgorithmThread algorithmThread, int data[], boolean newSearch)
    {
        super(algorithmThread, data);
        columSpacing = 10;
        columWidth = 10;
        xTextBuffer = 20;
        yTextBuffer = 2;
        leftChild = null;
        rightChild = null;
        active = false;
        processed = false;
        doingChildren = false;
        i = -10;
        j = -10;
        swapRequests = new Vector();
        this.data = data;
        initialise(newSearch);
    }

    public RadixExchangeSort(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        columSpacing = 10;
        columWidth = 10;
        xTextBuffer = 20;
        yTextBuffer = 2;
        leftChild = null;
        rightChild = null;
        active = false;
        processed = false;
        doingChildren = false;
        i = -10;
        j = -10;
        swapRequests = new Vector();
        this.data = (int[])(int[])data;
        initialise(true);
        ConfigurationManager cm = ConfigurationManager.getConfigurationManager();
        ColorSelection activeSelection = new ColorSelection(ACTIVE_COLOR, ACTIVE);
        activeSelection.addClass(this);
        cm.addColorSelection(activeSelection);
        ColorSelection processedSelection = new ColorSelection(DOING_CHILDREN_COLOR, PROCESSED);
        processedSelection.addClass(this);
        cm.addColorSelection(processedSelection);
        ColorSelection finishedSelection = new ColorSelection(FINISHED_COLOR, FINISHED);
        finishedSelection.addClass(this);
        cm.addColorSelection(finishedSelection);
        ColorSelection waitingSelection = new ColorSelection(WAITING_COLOR, WAITING);
        waitingSelection.addClass(this);
        cm.addColorSelection(waitingSelection);
        cm.addColorSelectionListener(this);
    }

    protected void calculateColors(Color colors[], int depthArray[], int depth)
    {
        for(int i = left; i <= right; i++)
        {
            if(i < 0 || i >= colors.length)
                continue;
            if(colors[i] == null)
            {
                colors[i] = getColor();
                if(i >= 0 && i < depthArray.length)
                    depthArray[i] = depth;
                continue;
            }
            if(i >= 0 && i < depthArray.length && depth > depthArray[i])
            {
                colors[i] = getColor();
                depthArray[i] = depth;
            }
        }

        if(leftChild != null)
            leftChild.calculateColors(colors, depthArray, depth + 1);
        if(rightChild != null)
            rightChild.calculateColors(colors, depthArray, depth + 1);
    }

    protected void createDuplicateLabels(int data[])
    {
        Integer temp[] = new Integer[data.length];
        for(int i = 0; i < data.length; i++)
            temp[i] = new Integer(data[i]);

        duplicateLabels = DuplicateLabel.createDuplicateLabels(temp);
    }

    public void display()
    {
        System.out.print((new StringBuilder()).append(Messages.getString("RadixExchangeSort.10")).append(left).append(Messages.getString("RadixExchangeSort.11")).append(right).append(" || ").toString());
        for(int i = 0; i < data.length; i++)
            System.out.print((new StringBuilder()).append(data[i]).append(" ").toString());

        System.out.println("");
    }

    public void draw(Graphics g, Point initialPoint)
    {
        Point pos = new Point(initialPoint.x + g.getFontMetrics().stringWidth(MSB_LABEL) + xTextBuffer, initialPoint.y);
        ElementArray binaryElementArray = new ElementArray(pos.x, pos.y + yTextBuffer + bitLength * (g.getFontMetrics().getHeight() + yTextBuffer));
        for(int i = 0; i < data.length; i++)
        {
            BinaryElement binaryElement = new BinaryElement(i, BACKGROUND, BACKGROUND, data[i], new Point(0, 0), yTextBuffer, bitLength);
            binaryElement.setTextColor(TEXT_COLOR);
            binaryElementArray.setValue(i, binaryElement);
        }

        binaryElementArray.draw(g);
        g.setColor(TEXT_COLOR);
        for(int v = 0; v < bitLength; v++)
            g.drawString((new StringBuilder()).append("").append(v).toString(), ((getRight() + 1) * (columWidth + columSpacing) + pos.x + xTextBuffer) - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(v).toString()) / 2, pos.y + (bitLength - v) * (g.getFontMetrics().getHeight() + yTextBuffer));

        g.drawString(MSB_LABEL, pos.x - g.getFontMetrics().stringWidth(MSB_LABEL) - xTextBuffer, pos.y + (g.getFontMetrics().getHeight() + yTextBuffer));
        g.drawString(LSB_LABEL, pos.x - g.getFontMetrics().stringWidth(LSB_LABEL) - xTextBuffer, pos.y + bitLength * (g.getFontMetrics().getHeight() + yTextBuffer));
        g.drawString(BIT_LABEL, ((getRight() + 1) * (columWidth + columSpacing) + pos.x + xTextBuffer) - g.getFontMetrics().stringWidth(BIT_LABEL) / 2, pos.y);
        if(currentBit >= 0)
        {
            Line line = new Line(data.length * (columWidth + columSpacing) + pos.x + xTextBuffer + (columWidth + columSpacing) + 30, (pos.y + (bitLength - currentBit) * (g.getFontMetrics().getHeight() + yTextBuffer)) - g.getFontMetrics().getHeight() / 2, data.length * (columWidth + columSpacing) + pos.x + xTextBuffer + (columWidth + columSpacing), (pos.y + (bitLength - currentBit) * (g.getFontMetrics().getHeight() + yTextBuffer)) - g.getFontMetrics().getHeight() / 2);
            line.showArrowHead(true);
            line.setDistanceFromStartForArrowHead(-3);
            line.setArrowHeadWidth(5);
            g.setColor(Color.black);
            line.draw(g);
        }
        Point point = new Point(pos.x, pos.y);
        point.y += (bitLength + 2) * (g.getFontMetrics().getHeight() + yTextBuffer);
        draw(g, point, pos);
    }

    protected void draw(Graphics g, Point pos, Point recPoint)
    {
        g.setColor(getColor());
        g.fillRect((getLeft() * (columWidth + columSpacing) + pos.x) - columWidth / 2, pos.y - g.getFontMetrics().getHeight(), ((1 + getRight()) - getLeft()) * (columWidth + columSpacing), g.getFontMetrics().getHeight());
        g.setColor(TEXT_COLOR);
        for(int i = getLeft(); i <= getRight(); i++)
            g.drawString((new StringBuilder()).append(data[i]).append("").toString(), (i * (columWidth + columSpacing) + pos.x + columWidth / 2) - g.getFontMetrics().stringWidth((new StringBuilder()).append(data[i]).append("").toString()) / 2, pos.y);

        if(isActive() && !isDoingChildren() && !isFinished())
        {
            if(getI() != -10)
            {
                StringMarker tempIMarker = new StringMarker("i", new Point(getI() * (columWidth + columSpacing) + pos.x + columWidth / 2, pos.y + g.getFontMetrics().getHeight() + yTextBuffer), TEXT_COLOR, ACTIVE_COLOR);
                tempIMarker.useDefaultWidth(false);
                tempIMarker.setWidth(columWidth);
                tempIMarker.draw(g);
            }
            if(getJ() != -10)
            {
                StringMarker tempJMarker = new StringMarker("J", new Point(getJ() * (columWidth + columSpacing) + pos.x + columWidth / 2, pos.y + 2 * (g.getFontMetrics().getHeight() + yTextBuffer)), TEXT_COLOR, ACTIVE_COLOR);
                tempJMarker.useDefaultWidth(false);
                tempJMarker.setWidth(columWidth);
                tempJMarker.draw(g);
            }
            if(getRight() >= getLeft() && bit >= 0)
            {
                g.setColor(Color.black);
                g.drawRect((getLeft() * (columWidth + columSpacing) + recPoint.x) - columWidth / 2, ((recPoint.y + (bitLength - bit) * (g.getFontMetrics().getHeight() + yTextBuffer)) - g.getFontMetrics().getHeight()) + yTextBuffer, ((1 + getRight()) - getLeft()) * (columWidth + columSpacing), g.getFontMetrics().getHeight());
            }
        }
        if(getLeftChild() != null)
            getLeftChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * yTextBuffer), recPoint);
        if(getRightChild() != null)
            getRightChild().draw(g, new Point(pos.x, pos.y + 2 * g.getFontMetrics().getHeight() + 2 * yTextBuffer), recPoint);
    }

    public Vector generateQuestions()
    {
        return null;
    }

    protected void generateTweens(MultipleTween tweens, ElementArray elementArray, int numberOfSteps)
    {
        for(int i = 0; i < swapRequests.size(); i++)
        {
            SwapRequest swapRequest = (SwapRequest)swapRequests.elementAt(i);
            Element first = elementArray.getElement(swapRequest.getFirstId());
            Element second = elementArray.getElement(swapRequest.getSecondId());
            tweens.add(new MoveTween(null, first, second.getPosition(), first.getPosition(), false, numberOfSteps));
            tweens.add(new MoveTween(null, second, first.getPosition(), second.getPosition(), false, numberOfSteps));
        }

        if(leftChild != null)
            leftChild.generateTweens(tweens, elementArray, numberOfSteps);
        if(rightChild != null)
            rightChild.generateTweens(tweens, elementArray, numberOfSteps);
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        ElementArray elementArray = (ElementArray)(ElementArray)parameter;
        generateTweens(tweens, elementArray, numberOfSteps);
        return tweens;
    }

    public RadixExchangeSort getActive()
    {
        if(isActive() && !isDoingChildren())
            return this;
        RadixExchangeSort temp = null;
        if(leftChild != null)
            temp = leftChild.getActive();
        if(temp != null)
            return temp;
        if(rightChild != null)
            temp = rightChild.getActive();
        if(temp != null)
            return temp;
        else
            return null;
    }

    public int getArrayBuffer(Graphics g)
    {
        return g.getFontMetrics().stringWidth(MSB_LABEL) + xTextBuffer;
    }

    protected int getBit(int number, int position, int bitLength)
    {
        String binaryString;
        String numberString;
        binaryString = "";
        for(int i = 0; i < bitLength; i++)
            binaryString = (new StringBuilder()).append(binaryString).append("0").toString();

        String binary = Integer.toBinaryString(number);
        binaryString = (new StringBuilder()).append(binaryString.substring(0, binaryString.length() - binary.length())).append(binary).toString();
        String reverseString = "";
        for(int i = binaryString.length() - 1; i >= 0; i--)
            reverseString = (new StringBuilder()).append(reverseString).append(String.valueOf(binaryString.charAt(i))).toString();

        displayMessage((new StringBuilder()).append("RadixExchangeSort.get Trying to make length ").append(bitLength).append(Messages.getString("RadixExchangeSort.19")).append(Integer.toBinaryString(number)).append(Messages.getString("RadixExchangeSort.20")).append(number).append(Messages.getString("RadixExchangeSort.21")).append(binaryString).append(Messages.getString("RadixExchangeSort.22")).append(reverseString).toString());
        binaryString = reverseString;
        if(position < 0 || position >= binaryString.length())
            break MISSING_BLOCK_LABEL_374;
        numberString = (new Character(binaryString.charAt(position))).toString();
        int temp;
        temp = Integer.parseInt(numberString);
        displayMessage((new StringBuilder()).append(Messages.getString("RadixExchangeSort.23")).append(number).append(Messages.getString("RadixExchangeSort.24")).append(binaryString).append(Messages.getString("RadixExchangeSort.25")).append(position).append(Messages.getString("RadixExchangeSort.26")).append(temp).append(Messages.getString("RadixExchangeSort.27")).append(bitLength).toString());
        return temp;
        NumberFormatException e;
        e;
        displayMessage((new StringBuilder()).append(Messages.getString("RadixExchangeSort.28")).append(numberString).append(" ").append(e).toString());
        displayMessage((new StringBuilder()).append(Messages.getString("RadixExchangeSort.30")).append(position).append(Messages.getString("RadixExchangeSort.31")).append(number).append(Messages.getString("RadixExchangeSort.32")).append(binaryString).toString());
        return -1;
    }

    public String getClassName()
    {
        return Messages.getString("RadixExchangeSort.65");
    }

    protected Color getColor()
    {
        Color color;
        if(isFinished())
            color = FINISHED_COLOR;
        else
        if(isDoingChildren())
            color = DOING_CHILDREN_COLOR;
        else
        if(isActive())
            color = ACTIVE_COLOR;
        else
            color = WAITING_COLOR;
        return color;
    }

    public int[] getData()
    {
        return data;
    }

    protected int getDepth(int depth)
    {
        if(leftChild != null && rightChild != null)
            return Math.max(leftChild.getDepth(depth + 1), rightChild.getDepth(depth + 1));
        if(leftChild != null)
            return leftChild.getDepth(depth + 1);
        if(rightChild != null)
            return rightChild.getDepth(depth + 1);
        else
            return depth;
    }

    public ElementArray getElementArray(Point location)
    {
        ElementArray elementArray = new ElementArray(location.x, location.y);
        elementArray.setColumGap(columSpacing);
        elementArray.setElementWidth(columWidth);
        Color colors[] = new Color[data.length];
        int depthArray[] = new int[data.length];
        calculateColors(colors, depthArray, 0);
        for(int i = 0; i < data.length; i++)
        {
            VerticalBar verticalBar = new VerticalBar(i, data[i], colors[i], new Point(0, 0));
            verticalBar.setTextColor(TEXT_COLOR);
            elementArray.setValue(i, verticalBar);
            ((VerticalBar)elementArray.getElement(i)).setMarkersAboveValue(false);
            ((VerticalBar)elementArray.getElement(i)).setOffsetForValue(1);
            if(duplicateLabels[i] != null)
            {
                StringMarker stringMarker = new StringMarker((new StringBuilder()).append("").append(data[i]).append(duplicateLabels[i]).toString());
                stringMarker.setColor(TEXT_COLOR);
                stringMarker.setBackgroundColor(BACKGROUND);
                elementArray.getElement(i).addMarker(stringMarker);
            }
        }

        return elementArray;
    }

    public int getHeight(Graphics g)
    {
        return getDepth(1) * (2 * (g.getFontMetrics().getHeight() + yTextBuffer)) + 2 * (yTextBuffer + g.getFontMetrics().getHeight()) + (bitLength + 2) * (g.getFontMetrics().getHeight() + yTextBuffer);
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public int getLeft()
    {
        return left;
    }

    public RadixExchangeSort getLeftChild()
    {
        return leftChild;
    }

    public int getRight()
    {
        return right;
    }

    public RadixExchangeSort getRightChild()
    {
        return rightChild;
    }

    public int getWidth(Graphics g)
    {
        return data.length * (columWidth + columSpacing) + xTextBuffer + (columWidth + columSpacing) + 30 + (g.getFontMetrics().stringWidth(MSB_LABEL) + xTextBuffer);
    }

    public boolean hasQuestions()
    {
        return false;
    }

    protected void initialise(boolean newSearch)
    {
        debugMode = false;
        active = false;
        processed = false;
        leftChild = null;
        rightChild = null;
        if(newSearch)
        {
            left = 0;
            right = data.length - 1;
            int max = 0;
            int maxPosition = 0;
            if(data.length > 0)
                max = data[0];
            for(int i = 0; i < data.length; i++)
                if(data[i] > max)
                {
                    max = data[i];
                    maxPosition = i;
                }

            if(data.length > 0)
            {
                bitLength = Integer.toBinaryString(data[maxPosition]).length();
                bit = bitLength - 1;
            }
            createDuplicateLabels(data);
        }
    }

    public boolean isActive()
    {
        if(isFinished())
            return false;
        else
            return active;
    }

    public boolean isDoingChildren()
    {
        if(isActive())
            return doingChildren;
        else
            return false;
    }

    public boolean isFinished()
    {
        return processed;
    }

    public void processColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.BACKGROUND))
            BACKGROUND = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            TEXT_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(ACTIVE))
            ACTIVE_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(PROCESSED))
            DOING_CHILDREN_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(FINISHED))
            FINISHED_COLOR = colorSelection.getColor();
        else
        if(atribute.equalsIgnoreCase(WAITING))
            WAITING_COLOR = colorSelection.getColor();
    }

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise(true);
        setPosition("");
    }

    public void removeAllAnimationRequests()
    {
        swapRequests.removeAllElements();
        if(leftChild != null)
            leftChild.removeAllAnimationRequests();
        if(rightChild != null)
            rightChild.removeAllAnimationRequests();
    }

    public void removeAllQuestions()
    {
    }

    public void run()
    {
        if(!enabled)
            return;
        active = true;
        currentBit = bit;
        setPosition("1");
        setPosition("3");
        displayMessage((new StringBuilder()).append(Messages.getString("RadixExchangeSort.35")).append(right).append(Messages.getString("RadixExchangeSort.36")).append(left).append(Messages.getString("RadixExchangeSort.37")).append(bit).append(Messages.getString("RadixExchangeSort.38")).append(bitLength).toString());
        if(right > left && bit >= 0)
        {
            setPosition("3.1");
            i = left;
            j = right;
            setPosition("3.1.1");
            setPosition("3.1.2");
            do
            {
                if(i == j)
                    break;
                setPosition("3.1.2.1");
                for(; getBit(data[i], bit, bitLength) == 0 && i < j; setPosition("3.1.2.1.1.1"))
                {
                    setPosition("3.1.2.1.1");
                    i++;
                }

                setPosition("3.1.2.1.2");
                setPosition("3.1.2.2");
                for(; getBit(data[j], bit, bitLength) == 1 && j > i; setPosition("3.1.2.2.1.1"))
                {
                    setPosition("3.1.2.2.1");
                    j--;
                }

                setPosition("3.1.2.2.2");
                swap(i, j);
                setPosition("3.1.2.3");
                if(i != j)
                    setPosition("3.1.2");
            } while(true);
            setPosition("3.1.4");
            if(getBit(data[right], bit, bitLength) == 0)
            {
                j++;
                setPosition("3.1.4.1");
            }
            setPosition("3.1.4.2");
            leftChild = new RadixExchangeSort(algorithmThread, data, false);
            leftChild.enabled = enabled;
            leftChild.left = left;
            leftChild.right = j - 1;
            leftChild.bit = bit - 1;
            leftChild.bitLength = bitLength;
            if(algorithmThread == null)
                System.out.println(Messages.getString("RadixExchangeSort.55"));
            if(leftChild.algorithmThread == null)
                System.out.println(Messages.getString("RadixExchangeSort.56"));
            rightChild = new RadixExchangeSort(algorithmThread, data, false);
            rightChild.enabled = enabled;
            rightChild.left = j;
            rightChild.right = right;
            rightChild.bit = bit - 1;
            rightChild.bitLength = bitLength;
            doingChildren = true;
            setPosition("3.2");
            setPosition("3.2.1");
            displayMessage(Messages.getString("RadixExchangeSort.59"));
            leftChild.run();
            leftChild.processed = true;
            currentBit = bit;
            setPosition("3.3");
            setPosition("3.3.1");
            displayMessage(Messages.getString("RadixExchangeSort.62"));
            rightChild.run();
            rightChild.processed = true;
        }
        processed = true;
        currentBit = bit;
        setPosition("5");
    }

    public void setEnabled(boolean state)
    {
        enabled = state;
        if(rightChild != null)
            rightChild.setEnabled(state);
        if(leftChild != null)
            leftChild.setEnabled(state);
    }

    protected void swap(int j, int i)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        if(i != j)
            swapRequests.addElement(new SwapRequest(j, i));
    }

    protected static final int ARROW_LENGTH = 30;
    protected static int currentBit = -1;
    protected static final String MSB_LABEL = Messages.getString("RadixExchangeSort.0");
    protected static final String LSB_LABEL = Messages.getString("RadixExchangeSort.1");
    protected static final String BIT_LABEL = Messages.getString("RadixExchangeSort.2");
    protected static String duplicateLabels[];
    public static final String I_MARKER = "i";
    public static final String J_MARKER = "J";
    public static Color WAITING_COLOR;
    public static Color HIGHLIGHT_COLOR;
    public static Color DOING_CHILDREN_COLOR;
    protected static final String ACTIVE = Messages.getString("RadixExchangeSort.5");
    protected static final String PROCESSED = Messages.getString("RadixExchangeSort.6");
    protected static final String FINISHED = Messages.getString("RadixExchangeSort.7");
    protected static final String WAITING = Messages.getString("RadixExchangeSort.8");
    public static Color ACTIVE_COLOR;
    public static Color FINISHED_COLOR;
    public static Color BACKGROUND;
    public static Color TEXT_COLOR;
    protected static final boolean DEBUG_MODE = false;
    public static final int MARKER_NOT_READY = -10;
    protected int columSpacing;
    protected int columWidth;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected RadixExchangeSort leftChild;
    protected RadixExchangeSort rightChild;
    protected boolean active;
    protected boolean processed;
    protected boolean doingChildren;
    protected int data[];
    protected int left;
    protected int right;
    protected int bit;
    protected int bitLength;
    protected int i;
    protected int j;
    protected Vector swapRequests;

    static 
    {
        WAITING_COLOR = Color.pink;
        HIGHLIGHT_COLOR = Color.green;
        DOING_CHILDREN_COLOR = Color.red.darker();
        ACTIVE_COLOR = Color.red;
        FINISHED_COLOR = Color.gray;
        BACKGROUND = Color.white;
        TEXT_COLOR = Color.black;
    }
}
