import java.util.*;
import java.io.*;

public class MinSpanTreePrimTestMinSpanTree
{
    static int getNumber() {
        final byte[] input = new byte[20];
        int input_size = 0;
        try {
            input_size = System.in.read(input);
        }
        catch (IOException ex) {}
        return Integer.parseInt(new String(input, 0, input_size - 1));
    }
    
    public static void main(final String[] args) {
        final MinSpanTreePrimPriorityQueue queue = new MinSpanTreePrimPriorityQueue();
        int command = 32;
        while (command != 113) {
            try {
                command = System.in.read();
            }
            catch (IOException ex) {}
            if (command == 105) {
                final int member = getNumber();
                final int priority = getNumber();
                queue.insert(member, 0, priority);
            }
            else if (command == 117) {
                final int member = getNumber();
                final int priority = getNumber();
                queue.update(member, 0, priority);
            }
            else if (command == 100) {
                final int member = getNumber();
                queue.delete(member);
            }
            else if (command == 114) {
                final int member = queue.remove().getKey();
                System.out.print("poped: ");
                System.out.println(member);
            }
            final Vector<E> queue_elements = queue.getQueue();
            System.out.println("---------");
            for (int i = 0; i < queue_elements.size(); ++i) {
                System.out.println(((Integer)queue_elements.elementAt(i)).intValue());
            }
            System.out.println("---------");
        }
    }
}
