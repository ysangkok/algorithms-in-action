package localization;

import java.util.*;

public class Messages
{
    private static final String BUNDLE_NAME = "messages";
    private static ResourceBundle RESOURCE_BUNDLE;
    private static Locale activeLocale;
    
    public static Locale getActiveLocale() {
        return Messages.activeLocale;
    }
    
    public static void setActiveLocale(final String languageCode, final String country, final String variant) {
        if (languageCode != null && languageCode.trim().length() != 0) {
            Messages.activeLocale = new Locale(languageCode, country, variant);
            Messages.RESOURCE_BUNDLE = ResourceBundle.getBundle("messages", Messages.activeLocale);
        }
    }
    
    public static String getString(final String key) {
        try {
            return Messages.RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
    
    static {
        Messages.RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
        Messages.activeLocale = Locale.getDefault();
    }
}
