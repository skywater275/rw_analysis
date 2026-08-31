package com.corrodinggames.rts.gameFramework.h;

import android.os.Build.VERSION;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.h.b;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.IOException;
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
   public static int c = 0;
   public static String d;
   static Pattern e = Pattern.compile("(.*)(\\(.*\\)( *\\[by.*\\])?)");
   static final Pattern f = Pattern.compile("\\[i:([^\\]]*?)\\]");


   public static strictfp void a() {
      e();
   }

   static strictfp ResourceBundle b() {
      if(a == null) {
         e();
      }

      return a;
   }

   static strictfp PropertyResourceBundle a(String var0) {
      j var1 = com.corrodinggames.rts.gameFramework.e.a.k("translations/" + var0);
      if(var1 == null) {
         return null;
      } else {
         PropertyResourceBundle var2 = null;

         try {
            InputStreamReader var3 = new InputStreamReader(var1, "UTF-8");
            var2 = new PropertyResourceBundle(var3);
            var3.close();
         } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
         } catch (IOException var5) {
            var5.printStackTrace();
         }

         return var2;
      }
   }

   public static strictfp String a(String var0, Locale var1, boolean var2, boolean var3) {
      if(var1 == Locale.ROOT) {
         return var0;
      } else {
         String var4 = var1.getLanguage();
         String var5;
         if(var2) {
            var5 = var1.getCountry();
         } else {
            var5 = "";
         }

         String var6;
         if(var3) {
            var6 = var1.getVariant();
         } else {
            var6 = "";
         }

         if(var4.equals("") && var5.equals("") && var6.equals("")) {
            return var0;
         } else {
            StringBuilder var7 = new StringBuilder(var0);
            var7.append('_');
            if(!var6.equals("")) {
               var7.append(var4).append('_').append(var5.toLowerCase(Locale.ROOT)).append('_').append(var6.toLowerCase(Locale.ROOT));
            } else if(!var5.equals("")) {
               var7.append(var4).append('_').append(var5.toLowerCase(Locale.ROOT));
            } else {
               var7.append(var4);
            }

            return var7.toString();
         }
      }
   }

   static strictfp ResourceBundle a(String var0, Locale var1) {
      String var2 = a(var0, Locale.ROOT, false, false) + ".properties";
      PropertyResourceBundle var3 = a(var2);
      if(var3 == null) {
         throw new RuntimeException("Root locate file:" + var2 + " is missing, it is required");
      } else {
         boolean var4 = Locale.ROOT.equals(var1);
         if(var4) {
            l.e("Locale: Using " + var2 + " as locale");
            return var3;
         } else {
            String var5 = a(var0, var1, true, true) + ".properties";
            PropertyResourceBundle var6 = a(var5);
            if(var6 == null) {
               l.e("Locale: No locale for " + var5 + " checking locale without variant ");
               var5 = a(var0, var1, true, false) + ".properties";
               var6 = a(var5);
               if(var6 == null) {
                  l.e("Locale: No locale for " + var5 + " checking locale without variant or country");
                  var5 = a(var0, var1, false, false) + ".properties";
                  var6 = a(var5);
                  if(var6 == null) {
                     l.e("Locale: No locale for " + var5 + " using base locale");
                     return var3;
                  }
               }
            }

            l.e("Locale: Using " + var5 + " as locale");
            return new b(var6, var3);
         }
      }
   }

   public static strictfp String c() {
      return d != null?d:d().getLanguage();
   }

   public static strictfp Locale d() {
      l var0 = l.B();
      SettingsEngine var1 = null;
      if(var0 != null) {
         var1 = var0.bQ;
      }

      boolean var2 = false;
      if(var1 != null && var1.forceEnglish) {
         var2 = true;
      }

      return var2?Locale.ROOT:Locale.getDefault();
   }

   public static synchronized strictfp void e() {
      ++c;
      l var0 = l.B();
      SettingsEngine var1 = null;
      if(var0 != null) {
         var1 = var0.bQ;
      }

      boolean var2 = false;
      if(var1 != null && var1.forceEnglish) {
         var2 = true;
      }

      if(a != null && b == var2) {
         l.e("Locale.reload: skipping reload");
      }

      if(VERSION.SDK_INT >= 9) {
         ResourceBundle.clearCache();
      }

      if(var2) {
         l.e("Locale: forceEnglish");
         a = a("Strings", Locale.ROOT);
      } else if(d != null) {
         a = a("Strings", new Locale(d));
      } else if(var1 != null && var1.overrideLanguageCode != null && !var1.overrideLanguageCode.equals("")) {
         a = a("Strings", new Locale(var1.overrideLanguageCode));
      } else {
         Locale var3 = Locale.getDefault();
         l.e("Locale: default targetLocale:" + var3);
         if(var3 != null) {
            l.e("Locale: default targetLocale ISO3:" + var3.getISO3Language());
         }

         a = a("Strings", var3);
      }

      b = var2;
      boolean var4 = false;
      if(var4) {
         ;
      }

   }

   private static final strictfp String d(String var0) {
      String var1;
      try {
         ResourceBundle var2 = b();
         var1 = var2.getString(var0);
      } catch (NullPointerException var4) {
         String var3 = "NullPointer with key:" + var0 + " locale:" + b().getLocale().toString();
         throw new RuntimeException(var3, var4);
      }

      if(var1.contains("[") || var1.contains("]")) {
         var1 = var1.replace("[[", "{{");
         var1 = var1.replace("]]", "}}");
         var1 = var1.replace("[", "{{");
         var1 = var1.replace("]", "}}");
      }

      if(var1.contains("{") || var1.contains("}")) {
         var1 = var1.replace("}}  {{", "}}{{");
         var1 = var1.replace("}} {{", "}}{{");
         var1 = var1.replace("}}{{", "\n-");
         var1 = var1.replace("{{", "-");
         var1 = var1.replace("}}", "");
      }

      return var1;
   }

   private static final strictfp boolean e(String var0) {
      try {
         b().getString(var0);
         return true;
      } catch (MissingResourceException var2) {
         return false;
      }
   }

   public static final strictfp String a(String var0, String var1, Object ... var2) {
      try {
         return a(var0, var2);
      } catch (MissingResourceException var4) {
         return var1;
      }
   }

   public static final strictfp String a(String var0, Object ... var1) {
      String var2 = d(var0);
      if(var1.length == 0) {
         return var2;
      } else {
         String var3 = (new MessageFormat(var2)).format(var1, new StringBuffer(), (FieldPosition)null).toString();
         return var3;
      }
   }

   public static final strictfp String b(String var0) {
      if(var0 == null) {
         return null;
      } else {
         String var1 = var0;
         String var2 = null;
         Matcher var3 = e.matcher(var0);
         if(var3.matches()) {
            var1 = var3.group(1);
            var2 = var3.group(2);
         }

         var1 = var1.trim();
         var1 = var1.replace(" ", "_");
         var1 = var1.replace(".tmx", "");
         var1 = var1.toLowerCase(Locale.ENGLISH);
         String var4 = "maps.name." + var1;
         if(e(var4)) {
            String var5 = a(var4, new Object[0]);
            if(var2 != null) {
               var5 = var5 + var2;
            }

            l.e("translated:" + var5);
            if(var5 != null) {
               var5 = var5.replace("_", " ");
            }

            return var5;
         } else {
            return var0;
         }
      }
   }

   public static strictfp String c(String var0) {
      if(!var0.contains("[i:")) {
         return var0;
      } else {
         int var1 = 0;
         StringBuffer var2 = new StringBuffer();

         Matcher var3;
         String var5;
         for(var3 = f.matcher(var0); var3.find(); var3.appendReplacement(var2, var5)) {
            ++var1;
            if(var1 > 100) {
               l.b("convertInlineBlocks: Too many loops while parsing: " + var0);
               return var0;
            }

            String var4 = var3.group(1);
            var5 = a(var4, (String)null, new Object[0]);
            if(var5 == null) {
               l.e("convertInlineBlocks: No key:" + var4);
               var5 = "[No key: " + var4 + "]";
            }
         }

         var3.appendTail(var2);
         return var2.toString();
      }
   }

}
