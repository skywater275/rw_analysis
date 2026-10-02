/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.h;

import android.os.Build;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.h.b;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class a {
    static ResourceBundle a;
    static boolean b;
    public static int c;
    public static String d;
    static Pattern e;
    static final Pattern f;

    public static strictfp void a() {
        com.corrodinggames.rts.gameFramework.h.a.e();
    }

    static strictfp ResourceBundle b() {
        if (a == null) {
            com.corrodinggames.rts.gameFramework.h.a.e();
        }
        return a;
    }

    static strictfp PropertyResourceBundle a(String string) {
        j j2 = com.corrodinggames.rts.gameFramework.e.a.k("translations/" + string);
        if (j2 == null) {
            return null;
        }
        PropertyResourceBundle propertyResourceBundle = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader((InputStream)j2, "UTF-8");
            propertyResourceBundle = new PropertyResourceBundle(inputStreamReader);
            inputStreamReader.close();
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return propertyResourceBundle;
    }

    public static strictfp String a(String string, Locale locale, boolean bl, boolean bl2) {
        if (locale == Locale.ROOT) {
            return string;
        }
        String string2 = locale.getLanguage();
        String string3 = bl ? locale.getCountry() : "";
        String string4 = bl2 ? locale.getVariant() : "";
        if (string2.equals("") && string3.equals("") && string4.equals("")) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.append('_');
        if (!string4.equals("")) {
            stringBuilder.append(string2).append('_').append(string3.toLowerCase(Locale.ROOT)).append('_').append(string4.toLowerCase(Locale.ROOT));
        } else if (!string3.equals("")) {
            stringBuilder.append(string2).append('_').append(string3.toLowerCase(Locale.ROOT));
        } else {
            stringBuilder.append(string2);
        }
        return stringBuilder.toString();
    }

    static strictfp ResourceBundle a(String string, Locale locale) {
        String string2 = com.corrodinggames.rts.gameFramework.h.a.a(string, Locale.ROOT, false, false) + ".properties";
        PropertyResourceBundle propertyResourceBundle = com.corrodinggames.rts.gameFramework.h.a.a(string2);
        if (propertyResourceBundle == null) {
            throw new RuntimeException("Root locate file:" + string2 + " is missing, it is required");
        }
        boolean bl = Locale.ROOT.equals(locale);
        if (bl) {
            l.e("Locale: Using " + string2 + " as locale");
            return propertyResourceBundle;
        }
        String string3 = com.corrodinggames.rts.gameFramework.h.a.a(string, locale, true, true) + ".properties";
        PropertyResourceBundle propertyResourceBundle2 = com.corrodinggames.rts.gameFramework.h.a.a(string3);
        if (propertyResourceBundle2 == null) {
            l.e("Locale: No locale for " + string3 + " checking locale without variant ");
            string3 = com.corrodinggames.rts.gameFramework.h.a.a(string, locale, true, false) + ".properties";
            propertyResourceBundle2 = com.corrodinggames.rts.gameFramework.h.a.a(string3);
            if (propertyResourceBundle2 == null) {
                l.e("Locale: No locale for " + string3 + " checking locale without variant or country");
                string3 = com.corrodinggames.rts.gameFramework.h.a.a(string, locale, false, false) + ".properties";
                propertyResourceBundle2 = com.corrodinggames.rts.gameFramework.h.a.a(string3);
                if (propertyResourceBundle2 == null) {
                    l.e("Locale: No locale for " + string3 + " using base locale");
                    return propertyResourceBundle;
                }
            }
        }
        l.e("Locale: Using " + string3 + " as locale");
        return new b(propertyResourceBundle2, propertyResourceBundle);
    }

    public static strictfp String c() {
        if (d != null) {
            return d;
        }
        return com.corrodinggames.rts.gameFramework.h.a.d().getLanguage();
    }

    public static strictfp Locale d() {
        l l2 = l.B();
        SettingsEngine settingsEngine = null;
        if (l2 != null) {
            settingsEngine = l2.bQ;
        }
        boolean bl = false;
        if (settingsEngine != null && settingsEngine.forceEnglish) {
            bl = true;
        }
        if (bl) {
            return Locale.ROOT;
        }
        return Locale.getDefault();
    }

    public static synchronized strictfp void e() {
        ++c;
        l l2 = l.B();
        SettingsEngine settingsEngine = null;
        if (l2 != null) {
            settingsEngine = l2.bQ;
        }
        boolean bl = false;
        if (settingsEngine != null && settingsEngine.forceEnglish) {
            bl = true;
        }
        if (a != null && b == bl) {
            l.e("Locale.reload: skipping reload");
        }
        if (Build.VERSION.SDK_INT >= 9) {
            ResourceBundle.clearCache();
        }
        if (bl) {
            l.e("Locale: forceEnglish");
            a = com.corrodinggames.rts.gameFramework.h.a.a("Strings", Locale.ROOT);
        } else if (d != null) {
            a = com.corrodinggames.rts.gameFramework.h.a.a("Strings", new Locale(d));
        } else if (settingsEngine != null && settingsEngine.overrideLanguageCode != null && !settingsEngine.overrideLanguageCode.equals("")) {
            a = com.corrodinggames.rts.gameFramework.h.a.a("Strings", new Locale(settingsEngine.overrideLanguageCode));
        } else {
            Locale locale = Locale.getDefault();
            l.e("Locale: default targetLocale:" + locale);
            if (locale != null) {
                l.e("Locale: default targetLocale ISO3:" + locale.getISO3Language());
            }
            a = com.corrodinggames.rts.gameFramework.h.a.a("Strings", locale);
        }
        b = bl;
        boolean bl2 = false;
        if (bl2) {
            // empty if block
        }
    }

    private static final strictfp String d(String string) {
        String string2;
        try {
            ResourceBundle resourceBundle = com.corrodinggames.rts.gameFramework.h.a.b();
            string2 = resourceBundle.getString(string);
        }
        catch (NullPointerException nullPointerException) {
            String string3 = "NullPointer with key:" + string + " locale:" + com.corrodinggames.rts.gameFramework.h.a.b().getLocale().toString();
            throw new RuntimeException(string3, nullPointerException);
        }
        if (string2.contains("[") || string2.contains("]")) {
            string2 = string2.replace("[[", "{{");
            string2 = string2.replace("]]", "}}");
            string2 = string2.replace("[", "{{");
            string2 = string2.replace("]", "}}");
        }
        if (string2.contains("{") || string2.contains("}")) {
            string2 = string2.replace("}}  {{", "}}{{");
            string2 = string2.replace("}} {{", "}}{{");
            string2 = string2.replace("}}{{", "\n-");
            string2 = string2.replace("{{", "-");
            string2 = string2.replace("}}", "");
        }
        return string2;
    }

    private static final strictfp boolean e(String string) {
        try {
            com.corrodinggames.rts.gameFramework.h.a.b().getString(string);
            return true;
        }
        catch (MissingResourceException missingResourceException) {
            return false;
        }
    }

    public static final strictfp String a(String string, String string2, Object ... objectArray) {
        try {
            return com.corrodinggames.rts.gameFramework.h.a.a(string, objectArray);
        }
        catch (MissingResourceException missingResourceException) {
            return string2;
        }
    }

    public static final strictfp String a(String string, Object ... objectArray) {
        String string2 = com.corrodinggames.rts.gameFramework.h.a.d(string);
        if (objectArray.length == 0) {
            return string2;
        }
        String string3 = new MessageFormat(string2).format(objectArray, new StringBuffer(), (FieldPosition)null).toString();
        return string3;
    }

    public static final strictfp String b(String string) {
        if (string == null) {
            return null;
        }
        String string2 = string;
        String string3 = null;
        Matcher matcher = e.matcher(string);
        if (matcher.matches()) {
            string2 = matcher.group(1);
            string3 = matcher.group(2);
        }
        string2 = string2.trim();
        string2 = string2.replace(" ", "_");
        string2 = string2.replace(".tmx", "");
        string2 = string2.toLowerCase(Locale.ENGLISH);
        String string4 = "maps.name." + string2;
        if (com.corrodinggames.rts.gameFramework.h.a.e(string4)) {
            String string5 = com.corrodinggames.rts.gameFramework.h.a.a(string4, new Object[0]);
            if (string3 != null) {
                string5 = string5 + string3;
            }
            l.e("translated:" + string5);
            if (string5 != null) {
                string5 = string5.replace("_", " ");
            }
            return string5;
        }
        return string;
    }

    public static strictfp String c(String string) {
        if (!string.contains("[i:")) {
            return string;
        }
        int n2 = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = f.matcher(string);
        while (matcher.find()) {
            if (++n2 > 100) {
                l.b("convertInlineBlocks: Too many loops while parsing: " + string);
                return string;
            }
            String string2 = matcher.group(1);
            String string3 = com.corrodinggames.rts.gameFramework.h.a.a(string2, null, new Object[0]);
            if (string3 == null) {
                l.e("convertInlineBlocks: No key:" + string2);
                string3 = "[No key: " + string2 + "]";
            }
            matcher.appendReplacement(stringBuffer, string3);
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    static {
        c = 0;
        e = Pattern.compile("(.*)(\\(.*\\)( *\\[by.*\\])?)");
        f = Pattern.compile("\\[i:([^\\]]*?)\\]");
    }
}
