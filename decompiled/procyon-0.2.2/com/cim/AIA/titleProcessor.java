package com.cim.AIA;

import java.io.*;
import java.net.*;

public class titleProcessor
{
    private URL fileURL;
    private int[] fileData;
    
    public titleProcessor(final URL fileURL) {
        super();
        this.fileData = new int[] { 71, 73, 70, 56, 57, 97, 118, 0, 83, 0, 128, 0, 0, 0, 0, 0, 255, 255, 255, 33, 249, 4, 1, 0, 0, 1, 0, 44, 0, 0, 0, 0, 118, 0, 83, 0, 0, 2, 254, 140, 143, 169, 203, 237, 15, 163, 156, 180, 218, 139, 179, 222, 188, 251, 15, 134, 226, 72, 150, 230, 137, 166, 30, 192, 2, 74, 59, 194, 129, 139, 200, 42, 214, 210, 135, 13, 218, 50, 127, 171, 228, 116, 6, 32, 137, 181, 67, 6, 47, 67, 34, 15, 246, 204, 213, 144, 212, 170, 20, 170, 44, 90, 181, 217, 217, 176, 183, 77, 234, 154, 89, 178, 24, 235, 250, 122, 155, 103, 242, 207, 188, 162, 69, 229, 74, 233, 154, 155, 158, 115, 197, 251, 53, 189, 30, 118, 215, 241, 54, 6, 88, 248, 55, 149, 231, 84, 102, 200, 39, 248, 216, 168, 21, 119, 232, 216, 245, 150, 128, 150, 216, 7, 25, 41, 72, 104, 164, 225, 70, 169, 7, 148, 89, 201, 72, 233, 137, 218, 214, 149, 33, 58, 106, 25, 168, 186, 136, 184, 73, 104, 219, 234, 55, 24, 219, 104, 119, 245, 249, 216, 231, 179, 58, 12, 155, 26, 218, 250, 251, 199, 230, 167, 166, 39, 188, 202, 89, 235, 182, 75, 180, 87, 172, 134, 71, 171, 137, 39, 141, 235, 216, 108, 189, 196, 61, 94, 238, 26, 109, 158, 110, 33, 170, 222, 190, 110, 231, 30, 47, 63, 79, 95, 111, 127, 143, 159, 175, 191, 207, 223, 239, 255, 15, 48, 96, 10, 80, 2, 151, 16, 44, 24, 228, 32, 66, 21, 183, 20, 46, 252, 240, 9, 222, 195, 35, 86, 106, 77, 140, 97, 42, 216, 69, 162, 48, 22, 29, 110, 196, 209, 201, 227, 199, 119, 29, 115, 141, 68, 38, 75, 228, 73, 9, 183, 54, 173, 228, 208, 82, 227, 203, 153, 52, 107, 218, 188, 137, 51, 167, 206, 157, 60, 123, 250, 252, 9, 52, 168, 208, 133, 18, 33, 88, 83, 57, 178, 232, 131, 152, 58, 179, 69, 48, 201, 147, 89, 162, 67, 25, 155, 105, 66, 90, 175, 42, 28, 103, 168, 224, 81, 19, 216, 105, 22, 174, 105, 104, 44, 254, 139, 89, 12, 154, 217, 140, 88, 227, 165, 149, 57, 86, 210, 85, 116, 254, 216, 193, 245, 86, 42, 80, 219, 118, 175, 92, 30, 211, 26, 18, 42, 190, 57, 138, 78, 25, 91, 22, 108, 111, 186, 188, 21, 189, 70, 178, 251, 182, 110, 174, 136, 180, 10, 183, 33, 167, 120, 168, 230, 205, 156, 59, 123, 254, 12, 58, 180, 232, 209, 164, 75, 155, 62, 141, 186, 66, 1, 0, 59 };
        this.fileURL = fileURL;
    }
    
    public boolean checkFile() {
        while (true) {
            int counter = 0;
            Label_0013:
            {
                Label_0041:
                {
                    final InputStream s;
                    final int ch;
                    final boolean b;
                    final Exception ex;
                    final Exception e;
                    Label_0035:
                    {
                        try {
                            s = this.fileURL.openConnection().getInputStream();
                            if ((ch = s.read()) == -1) {
                                break Label_0041;
                            }
                            if (ch == this.fileData[counter]) {
                                break Label_0035;
                            }
                            b = false;
                        }
                        catch (Exception) {
                            e = ex;
                            return false;
                        }
                        return b;
                        try {
                            ++counter;
                            break Label_0013;
                        }
                        catch (Exception) {}
                    }
                }
            }
            break;
        }
        return true;
    }
    
    public boolean checkFile(final InputStream s) {
        while (true) {
            int counter = 0;
            Label_0002:
            {
                Label_0030:
                {
                    final int ch;
                    final boolean b;
                    final Exception ex;
                    final Exception e;
                    Label_0024:
                    {
                        try {
                            if ((ch = s.read()) == -1) {
                                break Label_0030;
                            }
                            if (ch == this.fileData[counter]) {
                                break Label_0024;
                            }
                            b = false;
                        }
                        catch (Exception) {
                            e = ex;
                            return false;
                        }
                        return b;
                        try {
                            ++counter;
                            break Label_0002;
                        }
                        catch (Exception) {}
                    }
                }
            }
            break;
        }
        return true;
    }
}
