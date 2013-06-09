// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenerateJava.java

import java.io.*;

public class GenerateJava
{

    private static void generateChecker()
    {
        System.out.println("");
        System.out.println("    public boolean checkFile (InputStream s) {");
        System.out.println("        int counter = 0;");
        System.out.println("        int ch;");
        System.out.println("        try");
        System.out.println("        {");
        System.out.println("//            if (s.available() != fileSize)");
        System.out.println("//               return false;");
        System.out.println("");
        System.out.println("            while ((ch = s.read()) != -1)");
        System.out.println("            {");
        System.out.println("                if (ch != fileData[counter])");
        System.out.println("                    return false;");
        System.out.println("                counter++;");
        System.out.println("            }");
        System.out.println("        }");
        System.out.println("        catch(Exception e) {return false;}");
        System.out.println("        return true;");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    public boolean checkFile () {");
        System.out.println("        int counter = 0;");
        System.out.println("        int ch;");
        System.out.println("        try");
        System.out.println("        {");
        System.out.println("            InputStream s = fileURL.openConnection().getInputStream();");
        System.out.println("//            if (s.available() != fileSize)");
        System.out.println("//                return false;");
        System.out.println("");
        System.out.println("            while ((ch = s.read()) != -1)");
        System.out.println("            {");
        System.out.println("                if (ch != fileData[counter])");
        System.out.println("                    return false;");
        System.out.println("                counter++;");
        System.out.println("            }");
        System.out.println("        }");
        System.out.println("        catch(Exception e) {return false;}");
        System.out.println("        return true;");
        System.out.println("    }");
    }

    private static void generateConstructor()
    {
        System.out.println("");
        System.out.println((new StringBuilder()).append("    public ").append(modifier).append("Processor (URL fileURL) {").toString());
        System.out.println("        this.fileURL = fileURL;");
        System.out.println("    }");
    }

    public static void generateFile(String str1)
    {
        fileName = new String(str1);
        generateHeader();
        generateFileData();
        generateConstructor();
        generateChecker();
        generateFooter();
        System.err.println("Finished");
    }

    private static void generateFileData()
    {
        int counter;
        InputStream s;
        counter = 0;
        s = null;
        System.out.println("    private URL fileURL;");
        s = new FileInputStream(fileName);
        System.out.println((new StringBuilder()).append("    private int fileSize = ").append(s.available()).append(";").toString());
        System.out.println("    private int fileData[] = {");
        System.out.print("        ");
        int ch = s.read();
        System.out.print(Integer.toString(ch));
        while((ch = s.read()) != -1) 
        {
            System.out.print(", ");
            if(++counter == 10)
            {
                System.out.println("");
                System.out.print("        ");
                counter = 0;
            }
            System.out.print(Integer.toString(ch));
        }
        System.out.println("");
        System.out.println("    };");
        Exception e;
        try
        {
            s.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_206;
        e;
        System.err.println("Exception raised");
        try
        {
            s.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_206;
        Exception exception;
        exception;
        try
        {
            s.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    private static void generateFooter()
    {
        System.out.println("}");
    }

    private static void generateHeader()
    {
        modifier = fileName;
        int i;
        for(i = modifier.length() - 1; modifier.charAt(i) != '.'; i--);
        modifier = modifier.substring(0, i);
        System.err.println((new StringBuilder()).append("Creating: ").append(modifier).append("Processor.java").toString());
        System.out.println("package com.cim.AIA;");
        System.out.println("import java.net.*;");
        System.out.println("import java.io.*;");
        System.out.println("// This is an automatically generated file, do not modifiy");
        System.out.println("");
        System.out.println((new StringBuilder()).append("public class ").append(modifier).append("Processor {").toString());
    }

    public static void main(String args[])
    {
        if(args.length != 1)
        {
            System.err.println("usage: GenerateJava filename");
            System.exit(1);
        }
        generateFile(args[0]);
    }

    public GenerateJava()
    {
    }

    static String modifier;
    static String fileName;
}
