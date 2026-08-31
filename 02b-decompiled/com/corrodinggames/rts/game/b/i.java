package com.corrodinggames.rts.game.b;

import com.corrodinggames.rts.game.b.a;
import com.corrodinggames.rts.game.b.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class i {

   public int a;
   public String b;
   public ArrayList c;
   public int d;
   public int e;
   public Properties f;


   public strictfp a a(String var1) {
      if(this.c != null) {
         Iterator var2 = this.c.iterator();

         while(var2.hasNext()) {
            a var3 = (a)var2.next();
            if(var1.equalsIgnoreCase(var3.b)) {
               return var3;
            }
         }
      }

      return null;
   }

   public strictfp i(Element var1, b var2) {
      this.b = var1.getAttribute("name");
      if(var1.hasAttribute("width")) {
         this.d = Integer.parseInt(var1.getAttribute("width"));
      }

      if(var1.hasAttribute("height")) {
         this.e = Integer.parseInt(var1.getAttribute("height"));
      }

      this.c = new ArrayList();
      Element var3 = (Element)var1.getElementsByTagName("properties").item(0);
      NodeList var4;
      int var5;
      Element var6;
      if(var3 != null) {
         var4 = var3.getElementsByTagName("property");
         if(var4 != null) {
            this.f = new Properties();

            for(var5 = 0; var5 < var4.getLength(); ++var5) {
               var6 = (Element)var4.item(var5);
               String var7 = var6.getAttribute("name");
               String var8 = var6.getAttribute("value");
               this.f.setProperty(var7, var8);
            }
         }
      }

      var4 = var1.getElementsByTagName("object");

      for(var5 = 0; var5 < var4.getLength(); ++var5) {
         var6 = (Element)var4.item(var5);
         a var9 = new a(var6, var2, this);
         var9.a = var5;
         this.c.add(var9);
      }

   }
}
