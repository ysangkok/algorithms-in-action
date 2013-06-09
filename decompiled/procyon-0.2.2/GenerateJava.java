import java.io.*;

public class GenerateJava
{
    static String modifier;
    static String fileName;
    
    private static void generateChecker() {
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
    
    private static void generateConstructor() {
        System.out.println("");
        System.out.println("    public " + GenerateJava.modifier + "Processor (URL fileURL) {");
        System.out.println("        this.fileURL = fileURL;");
        System.out.println("    }");
    }
    
    public static void generateFile(final String str1) {
        GenerateJava.fileName = new String(str1);
        generateHeader();
        generateFileData();
        generateConstructor();
        generateChecker();
        generateFooter();
        System.err.println("Finished");
    }
    
    private static void generateFileData() {
        int counter = 0;
        InputStream s = null;
        System.out.println("    private URL fileURL;");
        try {
            s = new FileInputStream(GenerateJava.fileName);
            System.out.println("    private int fileSize = " + s.available() + ";");
            System.out.println("    private int fileData[] = {");
            System.out.print("        ");
            int ch = s.read();
            System.out.print(Integer.toString(ch));
            while ((ch = s.read()) != -1) {
                System.out.print(", ");
                ++counter;
                if (counter == 10) {
                    System.out.println("");
                    System.out.print("        ");
                    counter = 0;
                }
                System.out.print(Integer.toString(ch));
            }
            System.out.println("");
            System.out.println("    };");
        }
        catch (Exception e) {
            System.err.println("Exception raised");
            s.close();
            return;
        }
        finally {
            s.close();
        }
        try {
            final Exception ex;
            final Exception e = ex;
            return;
        }
        catch (Exception) {}
        try {}
        catch (Exception ex2) {}
    }
    
    private static void generateFooter() {
        System.out.println("}");
    }
    
    private static void generateHeader() {
        GenerateJava.modifier = GenerateJava.fileName;
        int i = GenerateJava.modifier.length() - 1;
        while (GenerateJava.modifier.charAt(i) != '.') {
            --i;
        }
        GenerateJava.modifier = GenerateJava.modifier.substring(0, i);
        System.err.println("Creating: " + GenerateJava.modifier + "Processor.java");
        System.out.println("package com.cim.AIA;");
        System.out.println("import java.net.*;");
        System.out.println("import java.io.*;");
        System.out.println("// This is an automatically generated file, do not modifiy");
        System.out.println("");
        System.out.println("public class " + GenerateJava.modifier + "Processor {");
    }
    
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("usage: GenerateJava filename");
            System.exit(1);
        }
        generateFile(args[0]);
    }
}
