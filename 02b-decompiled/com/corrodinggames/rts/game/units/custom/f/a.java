package com.corrodinggames.rts.game.units.custom.f;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.f.b;
import com.corrodinggames.rts.game.units.custom.f.c;
import com.corrodinggames.rts.game.units.custom.f.e;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a {

   static final Pattern a = Pattern.compile("\\$\\{([^\\}]*)\\}");
   static final Pattern b = Pattern.compile("[A-Za-z_][A-Za-z_.0-9]*");
   static b c = new b();


   public static void a(l var0, ab var1) {
      int var4 = 0;
      c.a();
      m var5 = var1.d("@global ");
      Iterator var6 = var5.iterator();

      Iterator var8;
      String var9;
      while(var6.hasNext()) {
         String var7 = (String)var6.next();
         var8 = var1.k(var7, "@global ").iterator();

         while(var8.hasNext()) {
            var9 = (String)var8.next();
            String var10 = var9.substring("@global ".length()).trim();

            try {
               e.a(var10);
            } catch (bo var23) {
               throw new bo("[" + var7 + "]" + var9 + ": " + var23.getMessage());
            }

            if(var1.f(var10)) {
               throw new bo("[" + var7 + "]" + var9 + ": A section already has that name");
            }

            String var11 = var1.e(var7, var9);
            if(var11.contains("${")) {
               throw new bo("[" + var7 + "]" + var9 + " has dynamic value: \'" + var11 + "\', this is not yet supported");
            }

            c.a.a(var10, var11);
         }
      }

      ArrayList var24 = new ArrayList();
      LinkedHashMap var25 = var1.d();
      var8 = var25.keySet().iterator();

      while(var8.hasNext()) {
         var9 = (String)var8.next();
         if(var9 != null && !var9.startsWith("comment_") && !var9.startsWith("template_")) {
            b var27 = c.b();
            Iterator var28 = var1.k(var9, "@define ").iterator();

            String var13;
            String var14;
            while(var28.hasNext()) {
               String var12 = (String)var28.next();
               var13 = var12.substring("@define ".length()).trim();

               try {
                  e.a(var13);
               } catch (bo var22) {
                  throw new bo("[" + var9 + "]" + var12 + ": " + var22.getMessage());
               }

               if(var1.f(var13)) {
                  throw new bo("[" + var9 + "]" + var12 + ": A section already has that name");
               }

               var14 = var1.e(var9, var12);
               if(var14.contains("${")) {
                  throw new bo("[" + var9 + "]" + var12 + " has dynamic value: \'" + var14 + "\', this is not yet supported");
               }

               var27.b.a(var13, var14);
            }

            Map var29 = (Map)var25.get(var9);
            Iterator var30 = var29.keySet().iterator();

            while(var30.hasNext()) {
               var13 = (String)var30.next();
               var14 = (String)var29.get(var13);
               if(var14 != null && var14.contains("${")) {
                  int var15 = 0;
                  StringBuffer var16 = new StringBuffer();

                  Matcher var17;
                  String var19;
                  for(var17 = a.matcher(var14); var17.find(); var17.appendReplacement(var16, Matcher.quoteReplacement(var19))) {
                     ++var15;
                     if(var15 > 100) {
                        throw new bo("[" + var9 + "]" + var13 + ": Too many loops while parsing");
                     }

                     String var18 = var17.group(1);
                     ++var4;

                     try {
                        var19 = var27.a(var0, var1, var9, var18);
                     } catch (bo var21) {
                        var21.printStackTrace();
                        throw new bo("[" + var9 + "]" + var13 + ": " + var21.getMessage());
                     }

                     if(!var18.equals(var19)) {
                        ;
                     }
                  }

                  var17.appendTail(var16);
                  var14 = var16.toString();
                  var24.add(new c(var9, var13, var14));
               }
            }
         }
      }

      var8 = var24.iterator();

      while(var8.hasNext()) {
         c var26 = (c)var8.next();
         var1.e(var26.a, var26.b, var26.c);
      }

      var24.clear();
   }

}
