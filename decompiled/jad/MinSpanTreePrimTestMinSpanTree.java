// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MinSpanTreePrimTestMinSpanTree.java

import java.io.*;
import java.util.Vector;

public class MinSpanTreePrimTestMinSpanTree
{

    public MinSpanTreePrimTestMinSpanTree()
    {
    }

    static int getNumber()
    {
        byte input[] = new byte[20];
        int input_size = 0;
        try
        {
            input_size = System.in.read(input);
        }
        catch(IOException e) { }
        String stringInput = new String(input, 0, input_size - 1);
        return Integer.parseInt(stringInput);
    }

    public static void main(String args[])
    {
        MinSpanTreePrimPriorityQueue queue = new MinSpanTreePrimPriorityQueue();
        for(int command = 32; command != 113;)
        {
            try
            {
                command = System.in.read();
            }
            catch(IOException e) { }
            if(command == 105)
            {
                int member = getNumber();
                int priority = getNumber();
                queue.insert(member, 0, priority);
            } else
            if(command == 117)
            {
                int member = getNumber();
                int priority = getNumber();
                queue.update(member, 0, priority);
            } else
            if(command == 100)
            {
                int member = getNumber();
                queue.delete(member);
            } else
            if(command == 114)
            {
                int member = queue.remove().getKey();
                System.out.print("poped: ");
                System.out.println(member);
            }
            Vector queue_elements = queue.getQueue();
            System.out.println("---------");
            for(int i = 0; i < queue_elements.size(); i++)
                System.out.println(((Integer)queue_elements.elementAt(i)).intValue());

            System.out.println("---------");
        }

    }
}
