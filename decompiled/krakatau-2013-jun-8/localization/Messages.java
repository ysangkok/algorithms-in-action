package localization;

public class Messages {
    final private static String BUNDLE_NAME = "messages";
    private static java.util.ResourceBundle RESOURCE_BUNDLE;
    private static java.util.Locale activeLocale;
    
    public static java.util.Locale getActiveLocale()
    {
        java.util.Locale a = localization.Messages.activeLocale;
        return a;
    }
    
    public static void setActiveLocale(String s, String s0, String s1)
    {
        label0: {
            if(s == null)
            {
                break label0;
            }
            String s2 = s.trim();
            int i = s2.length();
            if(i != 0)
            {
                java.util.Locale a = new java.util.Locale(s, s0, s1);
                localization.Messages.activeLocale = a;
                java.util.Locale a0 = localization.Messages.activeLocale;
                java.util.ResourceBundle a1 = java.util.ResourceBundle.getBundle("messages", a0);
                localization.Messages.RESOURCE_BUNDLE = a1;
            }
        }
    }
    
    public static String getString(String s)
    {
        String s0 = null;
        label3: {
            label1: {
                java.util.ResourceBundle a = null;
                String s1 = null;
                label0: try
                {
                    a = localization.Messages.RESOURCE_BUNDLE;
                    break label0;
                }
                catch(java.util.MissingResourceException ignoredException)
                {
                    break label1;
                }
                label2: try
                {
                    s1 = a.getString(s);
                    break label2;
                }
                catch(java.util.MissingResourceException ignoredException0)
                {
                    break label1;
                }
                s0 = s1;
                break label3;
            }
            StringBuilder a0 = new StringBuilder();
            StringBuilder a1 = a0.append((char)33);
            StringBuilder a2 = a1.append(s);
            StringBuilder a3 = a2.append((char)33);
            String s2 = a3.toString();
            s0 = s2;
        }
        return s0;
    }
    
    private Messages()
    {
        super();
    }
    
    static
    {
        java.util.ResourceBundle a = java.util.ResourceBundle.getBundle("messages");
        localization.Messages.RESOURCE_BUNDLE = a;
        java.util.Locale a0 = java.util.Locale.getDefault();
        localization.Messages.activeLocale = a0;
    }
}