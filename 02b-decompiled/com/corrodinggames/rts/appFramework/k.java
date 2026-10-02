package com.corrodinggames.rts.appFramework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class k implements Comparator {

   Pattern a = Pattern.compile(".*\\((.*)\\).*");


   public int a(String var1, String var2) {
      Date var3 = this.a(var1);
      Date var4 = this.a(var2);
      return var3 == null && var4 == null?var1.compareTo(var2):(var3 != null && var4 != null?var4.compareTo(var3):(var3 == null && var4 != null?-1:(var3 != null && var4 == null?1:0)));
   }

   public Date a(String var1) {
      Matcher var2 = this.a.matcher(var1);
      if(var2.matches()) {
         String var3 = var2.group(1);

         try {
            return (new SimpleDateFormat("d MMM yyyy HH.mm.ss", Locale.ENGLISH)).parse(var3);
         } catch (ParseException var9) {
            try {
               return (new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.ENGLISH)).parse(var3);
            } catch (ParseException var8) {
               try {
                  return (new SimpleDateFormat("d MMM yyyy HH_mm_ss", Locale.ENGLISH)).parse(var3);
               } catch (ParseException var7) {
                  try {
                     return (new SimpleDateFormat("d MMM yyyy HH-mm-ss", Locale.ENGLISH)).parse(var3);
                  } catch (ParseException var6) {
                     try {
                        return (new SimpleDateFormat("d MMM. yyyy HH.mm.ss", Locale.ENGLISH)).parse(var3);
                     } catch (ParseException var5) {
                        com.corrodinggames.rts.gameFramework.l.e("Failed to parse date:" + var3);
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((String)var1, (String)var2);
   }
}
