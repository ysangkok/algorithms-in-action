package com.cim.AIA;

import java.awt.*;
import java.io.*;

public class StreamImage
{
    public static Image getImage(final InputStream in) {
        if (in == null) {
            return null;
        }
        final Image m_image;
        try {
            final byte[] buffer = new byte[2000];
            in.read(buffer);
            m_image = Toolkit.getDefaultToolkit().createImage(buffer);
        }
        catch (IOException e) {
            return null;
        }
        return m_image;
    }
}
