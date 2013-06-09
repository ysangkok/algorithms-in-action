package com.cim.common;

import java.awt.*;
import java.util.*;

public class StringManipulate
{
    String string;
    String separator;
    
    public StringManipulate(final String str) {
        super();
        this.separator = "\n";
        this.string = str;
    }
    
    protected String getNext(final int after) {
        int start = -1;
        int end = -1;
        String s;
        Label_0022:
        Label_0058:
        {
             {
                try {
                    if (after != this.string.length()) {
                        break Label_0022;
                    }
                    s = "";
                }
                finally {
                    if (end == -1) {
                        end = this.string.length();
                    }
                }
                return s;
                try {
                    start = this.string.indexOf(" ", after);
                    if (start != -1) {
                        break Label_0058;
                    }
                    s = "";
                }
            }
            if (end == -1) {
                end = this.string.length();
            }
            return s;
            try {
                ++start;
                end = this.string.indexOf(" ", start);
            }
        }
        if (end == -1) {
            end = this.string.length();
        }
        return this.string.substring(start, end).trim();
    }
    
    protected String getNth(int word_index) {
        try {
            final StringTokenizer tokenizer = new StringTokenizer(this.string, this.separator);
            while (true) {
                --word_index;
                if (word_index < 0) {
                    break;
                }
                tokenizer.nextElement();
            }
            return tokenizer.nextToken().trim();
        }
        catch (NoSuchElementException e) {
            return "";
        }
    }
    
    protected String getPrevious(final int before) {
        int start = -1;
        int end = -1;
        final String s;
        Label_0015:
        {
            try {
                if (before != 0) {
                    break Label_0015;
                }
                s = "";
            }
            finally {
                if (start == -1) {
                    start = 0;
                }
            }
            return s;
            try {
                end = before - 2;
                start = this.string.lastIndexOf(" ", end);
            }
        }
        if (start == -1) {
            start = 0;
        }
        if (end <= 0) {
            return "";
        }
        return this.string.substring(start, end + 1).trim();
    }
    
    protected int getTokenCount() {
        return new StringTokenizer(this.string, this.separator).countTokens();
    }
    
    protected String replace(final String target, final int source) {
        return this.replace(target, new Integer(source).toString());
    }
    
    protected String replace(final String target, final String source) {
        return this.replace(target, source, 0);
    }
    
    private String replace(final String target, final String source, final int start) {
        return this.replace(target, source, 0, false);
    }
    
    protected String replace(final String target, final String source, final int start, final boolean word) {
        final StringBuffer new_buffer = new StringBuffer();
        int count = 0;
        int i;
        final String string2;
        final int j;
        Label_0053:
        Label_0075:
        {
             {
                final StringManipulate manipulate;
                final String string;
                final StringIndexOutOfBoundsException ex;
                final StringIndexOutOfBoundsException e;
                Label_0050:
                {
                    try {
                        if (!word) {
                            break Label_0053;
                        }
                        manipulate = new StringManipulate(this.string);
                        i = manipulate.wordIndex(target, start);
                        if (i != -1) {
                            break Label_0050;
                        }
                        string = this.string;
                    }
                    catch (StringIndexOutOfBoundsException) {
                        e = ex;
                        System.out.println(e);
                    }
                    return string;
                    try {
                        break Label_0075;
                        Block_8:
                        {
                            string2 = this.string;
                            i = this.string.indexOf(target, start);
                            break Block_8;
                        }
                    }
                    // iftrue(Label_0075:, i != -1)
                    catch (StringIndexOutOfBoundsException) {}
                }
            }
            return string2;
            try {
                j = target.length();
                for (; count < this.string.length(); ++count) {
                    if (count < i || count >= i + j) {
                        new_buffer.append(this.string.charAt(count));
                    }
                }
                new_buffer.insert(i, source);
            }
            catch (StringIndexOutOfBoundsException) {}
        }
        this.string = new_buffer.toString();
        return new_buffer.toString();
    }
    
    protected void setSeparator(final String str) {
        this.separator = str;
    }
    
    protected String shrink() {
        return this.shrink(false);
    }
    
    protected String shrink(final boolean empty) {
        int space_count = 0;
        final char space_char = ' ';
        final char newline_char = '\n';
        final char tab_char = '\t';
        final StringBuffer buffer = new StringBuffer();
        if (this.string == null) {
            return this.string;
        }
        for (int i = 0; i < this.string.length(); ++i) {
            if (this.string.charAt(i) == newline_char) {
                if (!empty) {
                    space_count = space_count + 2;
                    buffer.append(" ");
                }
                else {
                    ++i;
                }
            }
            if (this.string.charAt(i) == tab_char) {
                space_count = space_count + 2;
                if (!empty) {
                    buffer.append(" ");
                }
            }
            if (this.string.charAt(i) == space_char || this.string.charAt(i) == newline_char || this.string.charAt(i) == tab_char) {
                ++space_count;
            }
            else {
                space_count = 0;
            }
            if (space_count <= 1) {
                buffer.append(this.string.charAt(i));
            }
        }
        return buffer.toString();
    }
    
    public String splitLine(final Font font, int width, final boolean wrap, int max_lines) {
        width = width - 20;
        final StringBuffer buffer = new StringBuffer(this.string);
        String line = this.string;
        final FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int line_start = 0;
        int i = 0;
        if (max_lines == 0) {
            max_lines = 2147483647;
        }
        for (i = 0; i < line.length(); ++i) {
            if (max_lines == 0) {
                break;
            }
            if (line.charAt(i) == '\n') {
                line_start = i;
            }
            if (metrics.stringWidth(line.substring(line_start, i)) >= width) {
                if (!wrap) {
                    buffer.insert(i - 1, "\n");
                    --max_lines;
                }
                else {
                    final int valid = line.substring(line_start, i).lastIndexOf(" ");
                    if (valid == -1) {
                        buffer.insert(i - 1, "\n");
                        --max_lines;
                        if (metrics.stringWidth(line.substring(line_start, i - 1)) > width) {
                            line = line;
                        }
                    }
                    else {
                        buffer.insert(line_start + valid + 1, "\n");
                        i = line_start + valid + 1;
                        --max_lines;
                        if (line_start > 0 && valid > line_start) {
                            Common.debug("DEBUG " + line_start + " " + valid + " " + width, 2);
                            Common.debug("LEN " + line.length(), 2);
                            Common.debug("L    " + line.substring(line_start, valid), 2);
                            if (metrics.stringWidth(line.substring(line_start, valid)) > width) {
                                line = line;
                            }
                        }
                    }
                }
                if (max_lines == 0) {
                    break;
                }
                line_start = i + 1;
                line = buffer.toString();
            }
        }
        return buffer.toString().substring(0, i);
    }
    
    protected String stripHeader() {
        return this.string.substring(this.string.indexOf(this.separator) + 1, this.string.length());
    }
    
    public String toString() {
        return this.string;
    }
    
    protected int wordCount() {
        int count = 0;
        try {
            final StringTokenizer tokenizer = new StringTokenizer(this.string, this.separator);
            while (tokenizer.hasMoreTokens()) {
                tokenizer.nextElement();
                ++count;
            }
        }
        catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return count;
    }
    
    protected int wordIndex(final String word, final int from_index) {
        if (from_index >= this.string.length()) {
            return -1;
        }
        if (from_index < 0) {
            return -1;
        }
        if (this.string.substring(from_index, this.string.length()).equalsIgnoreCase(word)) {
            return 0;
        }
        int start = -1;
        for (int l = from_index; l <= this.string.length() - word.length(); ++l) {
            if (this.string.substring(l, l + word.length()).equalsIgnoreCase(word)) {
                start = l;
                break;
            }
        }
        if (start == -1) {
            return -1;
        }
        if (start != 0 && !this.string.substring(start - 1, start).equals(" ")) {
            return this.wordIndex(word, start + 1);
        }
        if (this.string.length() == start + word.length()) {
            return start;
        }
        if (this.string.substring(start + word.length(), start + word.length() + 1).equals(" ")) {
            return start;
        }
        return this.wordIndex(word, start + 1);
    }
}
