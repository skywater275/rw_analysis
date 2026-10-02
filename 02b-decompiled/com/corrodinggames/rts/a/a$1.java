package com.corrodinggames.rts.a;

import com.corrodinggames.rts.a.a;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

final class a$1 implements Runnable {

   public strictfp void run() {
      String var2;
      for(Iterator var1 = a.e.iterator(); var1.hasNext(); l.e("End of:" + var2)) {
         var2 = (String)var1.next();
         l.e("Running debug script:" + var2);

         try {
            FileReader var3 = new FileReader(var2);
            BufferedReader var4 = new BufferedReader(var3);

            while(true) {
               String var5 = var4.readLine();
               if(var5 == null) {
                  var4.close();
                  var3.close();
                  break;
               }

               var5 = var5.trim();
               if(!var5.equals("") && !var5.startsWith("#")) {
                  l.e("Running: " + var5);
                  String var6 = a.b("script " + var5);
                  l.e("got: " + var6.trim());
               }
            }
         } catch (IOException var7) {
            throw new RuntimeException(var7);
         }
      }

   }
}
