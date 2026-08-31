package com.corrodinggames.rts.gameFramework.h;

import com.corrodinggames.rts.gameFramework.l;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

class b extends ResourceBundle {

   ResourceBundle a;
   ResourceBundle b;


   public strictfp b(ResourceBundle var1, ResourceBundle var2) {
      this.a = var1;
      this.b = var2;
   }

   public strictfp Enumeration getKeys() {
      l.e("MultipleResourceBundle: Slow get keys");
      Vector var1 = new Vector();
      var1.addAll(Collections.list(this.a.getKeys()));
      if(this.b != null) {
         Iterator var2 = Collections.list(this.b.getKeys()).iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            if(!var1.contains(var3)) {
               var1.add(var3);
            }
         }
      }

      return var1.elements();
   }

   protected strictfp Object handleGetObject(String var1) {
      Object var2;
      try {
         var2 = this.a.getObject(var1);
      } catch (MissingResourceException var5) {
         var2 = null;
      }

      if(var2 == null && this.b != null) {
         try {
            var2 = this.b.getObject(var1);
         } catch (MissingResourceException var4) {
            var2 = null;
         }
      }

      return var2;
   }
}
