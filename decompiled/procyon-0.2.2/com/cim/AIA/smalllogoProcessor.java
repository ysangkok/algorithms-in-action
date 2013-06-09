package com.cim.AIA;

import java.io.*;
import java.net.*;

public class smalllogoProcessor
{
    private URL fileURL;
    private int[] fileData;
    
    public smalllogoProcessor(final URL fileURL) {
        super();
        this.fileData = new int[] { 71, 73, 70, 56, 57, 97, 57, 0, 75, 0, 247, 0, 0, 0, 0, 0, 16, 66, 255, 82, 255, 0, 255, 24, 0, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 33, 249, 4, 1, 0, 0, 4, 0, 44, 0, 0, 0, 0, 57, 0, 75, 0, 0, 8, 254, 0, 9, 8, 28, 72, 176, 160, 193, 131, 8, 19, 42, 92, 200, 176, 161, 195, 135, 16, 35, 74, 156, 72, 177, 98, 69, 0, 24, 45, 106, 60, 136, 81, 128, 199, 140, 27, 55, 2, 240, 72, 242, 99, 72, 139, 35, 63, 118, 52, 121, 82, 98, 74, 146, 47, 89, 182, 124, 24, 83, 64, 77, 0, 51, 33, 222, 44, 41, 51, 167, 194, 154, 54, 121, 218, 244, 217, 112, 39, 79, 156, 68, 23, 26, 133, 153, 148, 97, 76, 140, 79, 155, 58, 21, 202, 84, 234, 84, 161, 72, 149, 66, 5, 57, 115, 43, 212, 162, 1, 194, 138, 229, 106, 53, 33, 0, 177, 104, 195, 102, 45, 107, 240, 44, 90, 183, 106, 217, 34, 132, 171, 150, 238, 90, 185, 2, 237, 6, 176, 139, 151, 32, 221, 189, 80, 199, 246, 29, 168, 87, 173, 224, 159, 100, 79, 194, 93, 124, 152, 35, 128, 1, 144, 19, 107, 116, 91, 120, 239, 92, 200, 152, 35, 183, 252, 155, 246, 46, 225, 204, 160, 61, 95, 76, 251, 150, 99, 232, 208, 155, 255, 74, 206, 155, 249, 241, 99, 204, 162, 47, 122, 53, 123, 250, 181, 230, 193, 4, 108, 15, 216, 138, 122, 176, 110, 219, 192, 113, 179, 222, 221, 26, 182, 240, 220, 145, 139, 223, 118, 188, 58, 164, 238, 222, 204, 191, 18, 125, 190, 188, 173, 215, 230, 34, 175, 155, 189, 46, 253, 120, 110, 238, 216, 229, 131, 130, 15, 207, 118, 124, 108, 188, 230, 189, 19, 230, 174, 222, 239, 108, 167, 231, 125, 198, 95, 223, 221, 234, 124, 237, 226, 183, 111, 245, 61, 119, 63, 110, 207, 239, 9, 183, 86, 128, 82, 53, 151, 149, 127, 226, 213, 151, 219, 119, 228, 53, 101, 222, 124, 229, 225, 215, 158, 117, 19, 58, 86, 33, 133, 23, 186, 151, 161, 134, 27, 230, 5, 225, 127, 18, 122, 247, 160, 136, 8, 122, 248, 161, 124, 10, 210, 119, 98, 106, 247, 173, 232, 92, 131, 223, 37, 184, 34, 140, 217, 185, 72, 163, 108, 46, 230, 101, 95, 142, 30, 58, 120, 99, 65, 63, 234, 196, 163, 95, 211, 5, 9, 228, 144, 90, 33, 217, 161, 69, 1, 1, 0, 59 };
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