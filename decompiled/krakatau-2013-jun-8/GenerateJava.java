public class GenerateJava {
    static String modifier;
    static String fileName;
    
    private static void generateChecker()
    {
        java.io.PrintStream a = System.out;
        a.println("");
        java.io.PrintStream a0 = System.out;
        a0.println("    public boolean checkFile (InputStream s) {");
        java.io.PrintStream a1 = System.out;
        a1.println("        int counter = 0;");
        java.io.PrintStream a2 = System.out;
        a2.println("        int ch;");
        java.io.PrintStream a3 = System.out;
        a3.println("        try");
        java.io.PrintStream a4 = System.out;
        a4.println("        {");
        java.io.PrintStream a5 = System.out;
        a5.println("//            if (s.available() != fileSize)");
        java.io.PrintStream a6 = System.out;
        a6.println("//               return false;");
        java.io.PrintStream a7 = System.out;
        a7.println("");
        java.io.PrintStream a8 = System.out;
        a8.println("            while ((ch = s.read()) != -1)");
        java.io.PrintStream a9 = System.out;
        a9.println("            {");
        java.io.PrintStream a10 = System.out;
        a10.println("                if (ch != fileData[counter])");
        java.io.PrintStream a11 = System.out;
        a11.println("                    return false;");
        java.io.PrintStream a12 = System.out;
        a12.println("                counter++;");
        java.io.PrintStream a13 = System.out;
        a13.println("            }");
        java.io.PrintStream a14 = System.out;
        a14.println("        }");
        java.io.PrintStream a15 = System.out;
        a15.println("        catch(Exception e) {return false;}");
        java.io.PrintStream a16 = System.out;
        a16.println("        return true;");
        java.io.PrintStream a17 = System.out;
        a17.println("    }");
        java.io.PrintStream a18 = System.out;
        a18.println("");
        java.io.PrintStream a19 = System.out;
        a19.println("    public boolean checkFile () {");
        java.io.PrintStream a20 = System.out;
        a20.println("        int counter = 0;");
        java.io.PrintStream a21 = System.out;
        a21.println("        int ch;");
        java.io.PrintStream a22 = System.out;
        a22.println("        try");
        java.io.PrintStream a23 = System.out;
        a23.println("        {");
        java.io.PrintStream a24 = System.out;
        a24.println("            InputStream s = fileURL.openConnection().getInputStream();");
        java.io.PrintStream a25 = System.out;
        a25.println("//            if (s.available() != fileSize)");
        java.io.PrintStream a26 = System.out;
        a26.println("//                return false;");
        java.io.PrintStream a27 = System.out;
        a27.println("");
        java.io.PrintStream a28 = System.out;
        a28.println("            while ((ch = s.read()) != -1)");
        java.io.PrintStream a29 = System.out;
        a29.println("            {");
        java.io.PrintStream a30 = System.out;
        a30.println("                if (ch != fileData[counter])");
        java.io.PrintStream a31 = System.out;
        a31.println("                    return false;");
        java.io.PrintStream a32 = System.out;
        a32.println("                counter++;");
        java.io.PrintStream a33 = System.out;
        a33.println("            }");
        java.io.PrintStream a34 = System.out;
        a34.println("        }");
        java.io.PrintStream a35 = System.out;
        a35.println("        catch(Exception e) {return false;}");
        java.io.PrintStream a36 = System.out;
        a36.println("        return true;");
        java.io.PrintStream a37 = System.out;
        a37.println("    }");
    }
    
    private static void generateConstructor()
    {
        java.io.PrintStream a = System.out;
        a.println("");
        java.io.PrintStream a0 = System.out;
        StringBuilder a1 = new StringBuilder();
        StringBuilder a2 = a1.append("    public ");
        String s = GenerateJava.modifier;
        StringBuilder a3 = a2.append(s);
        StringBuilder a4 = a3.append("Processor (URL fileURL) {");
        String s0 = a4.toString();
        a0.println(s0);
        java.io.PrintStream a5 = System.out;
        a5.println("        this.fileURL = fileURL;");
        java.io.PrintStream a6 = System.out;
        a6.println("    }");
    }
    
    public static void generateFile(String s)
    {
        String s0 = new String(s);
        GenerateJava.fileName = s0;
        GenerateJava.generateHeader();
        GenerateJava.generateFileData();
        GenerateJava.generateConstructor();
        GenerateJava.generateChecker();
        GenerateJava.generateFooter();
        java.io.PrintStream a = System.err;
        a.println("Finished");
    }
    
    private static void generateFileData()
    {
        java.io.FileInputStream a = null;
        Throwable a0 = null;
        java.io.PrintStream a1 = System.out;
        a1.println("    private URL fileURL;");
        label1: {
            label2: {
                java.io.FileInputStream a2 = null;
                label0: {
                    java.io.FileInputStream a3 = null;
                    try
                    {
                        a = null;
                        try
                        {
                            a = null;
                            a3 = null;
                            String s = GenerateJava.fileName;
                            a = null;
                            a3 = null;
                            a2 = new java.io.FileInputStream(s);
                            a = a2;
                            a3 = a2;
                            java.io.PrintStream a4 = System.out;
                            a = a2;
                            a = a2;
                            a3 = a2;
                            StringBuilder a5 = new StringBuilder();
                            a = a2;
                            a3 = a2;
                            StringBuilder a6 = a5.append("    private int fileSize = ");
                            a = a2;
                            a3 = a2;
                            int i = ((java.io.InputStream)a2).available();
                            a = a2;
                            a3 = a2;
                            StringBuilder a7 = a6.append(i);
                            a = a2;
                            a3 = a2;
                            StringBuilder a8 = a7.append(";");
                            a = a2;
                            a3 = a2;
                            String s0 = a8.toString();
                            a = a2;
                            a3 = a2;
                            a4.println(s0);
                            a = a2;
                            a3 = a2;
                            java.io.PrintStream a9 = System.out;
                            a = a2;
                            a3 = a2;
                            a9.println("    private int fileData[] = {");
                            a = a2;
                            a3 = a2;
                            java.io.PrintStream a10 = System.out;
                            a = a2;
                            a3 = a2;
                            a10.print("        ");
                            a = a2;
                            a3 = a2;
                            int i0 = ((java.io.InputStream)a2).read();
                            a = a2;
                            a3 = a2;
                            java.io.PrintStream a11 = System.out;
                            a = a2;
                            a3 = a2;
                            String s1 = Integer.toString(i0);
                            a = a2;
                            a3 = a2;
                            a11.print(s1);
                            int i1 = 0;
                            while(true)
                            {
                                int i2 = 0;
                                a = a2;
                                a3 = a2;
                                int i3 = ((java.io.InputStream)a2).read();
                                if(i3 == -1)
                                {
                                    break;
                                }
                                a = a2;
                                a3 = a2;
                                java.io.PrintStream a12 = System.out;
                                a = a2;
                                a3 = a2;
                                a12.print(", ");
                                int i4 = i1 + 1;
                                if(i4 != 10)
                                {
                                    i2 = i4;
                                }
                                else
                                {
                                    a = a2;
                                    a3 = a2;
                                    java.io.PrintStream a13 = System.out;
                                    a = a2;
                                    a3 = a2;
                                    a13.println("");
                                    a = a2;
                                    a3 = a2;
                                    java.io.PrintStream a14 = System.out;
                                    a = a2;
                                    a3 = a2;
                                    a14.print("        ");
                                    i2 = 0;
                                }
                                a = a2;
                                a3 = a2;
                                java.io.PrintStream a15 = System.out;
                                a = a2;
                                a3 = a2;
                                String s2 = Integer.toString(i3);
                                a = a2;
                                a3 = a2;
                                a15.print(s2);
                                i1 = i2;
                                continue;
                            }
                            a = a2;
                            a3 = a2;
                            java.io.PrintStream a16 = System.out;
                            a = a2;
                            a3 = a2;
                            a16.println("");
                            a = a2;
                            a3 = a2;
                            java.io.PrintStream a17 = System.out;
                            a = a2;
                            a3 = a2;
                            a17.println("    };");
                            break label0;
                        }
                        catch(Exception ignoredException)
                        {
                        }
                        a = a3;
                        java.io.PrintStream a18 = System.err;
                        a = a3;
                        a18.println("Exception raised");
                    }
                    catch(Throwable a19)
                    {
                        a0 = a19;
                        break label1;
                    }
                    try
                    {
                        ((java.io.InputStream)a3).close();
                        break label2;
                    }
                    catch(Exception ignoredException0)
                    {
                        break label2;
                    }
                }
                try
                {
                    ((java.io.InputStream)a2).close();
                }
                catch(Exception ignoredException1)
                {
                }
            }
            return;
        }
        try
        {
            ((java.io.InputStream)a).close();
        }
        catch(Exception ignoredException2)
        {
        }
        throw a0;
    }
    
    private static void generateFooter()
    {
        java.io.PrintStream a = System.out;
        a.println("}");
    }
    
    private static void generateHeader()
    {
        String s = GenerateJava.fileName;
        GenerateJava.modifier = s;
        String s0 = GenerateJava.modifier;
        int i = s0.length();
        int i0 = i - 1;
        int i1 = i0;
        while(true)
        {
            String s1 = GenerateJava.modifier;
            int i2 = s1.charAt(i1);
            if(i2 == 46)
            {
                String s2 = GenerateJava.modifier;
                String s3 = s2.substring(0, i1);
                GenerateJava.modifier = s3;
                java.io.PrintStream a = System.err;
                StringBuilder a0 = new StringBuilder();
                StringBuilder a1 = a0.append("Creating: ");
                String s4 = GenerateJava.modifier;
                StringBuilder a2 = a1.append(s4);
                StringBuilder a3 = a2.append("Processor.java");
                String s5 = a3.toString();
                a.println(s5);
                java.io.PrintStream a4 = System.out;
                a4.println("package com.cim.AIA;");
                java.io.PrintStream a5 = System.out;
                a5.println("import java.net.*;");
                java.io.PrintStream a6 = System.out;
                a6.println("import java.io.*;");
                java.io.PrintStream a7 = System.out;
                a7.println("// This is an automatically generated file, do not modifiy");
                java.io.PrintStream a8 = System.out;
                a8.println("");
                java.io.PrintStream a9 = System.out;
                StringBuilder a10 = new StringBuilder();
                StringBuilder a11 = a10.append("public class ");
                String s6 = GenerateJava.modifier;
                StringBuilder a12 = a11.append(s6);
                StringBuilder a13 = a12.append("Processor {");
                String s7 = a13.toString();
                a9.println(s7);
                return;
            }
            else
            {
                int i3 = i1 + -1;
                i1 = i3;
            }
        }
    }
    
    public static void main(String[] a)
    {
        int i = a.length;
        if(i != 1)
        {
            java.io.PrintStream a0 = System.err;
            a0.println("usage: GenerateJava filename");
            System.exit(1);
        }
        String s = a[0];
        GenerateJava.generateFile(s);
    }
    
    public GenerateJava()
    {
        super();
    }
}