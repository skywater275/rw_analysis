package com.corrodinggames.rts.game.b;

import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.b.i;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.x;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class a {

   public int a;
   public String b;
   public String c;
   public String d;
   public float e;
   public float f;
   public float g;
   public float h;
   public float i;
   private String p;
   public RectF j;
   public int k = -1;
   public j l;
   public int m = -1;
   public Properties n;
   public m o = new m();


   static float a(Element var0, String var1) {
      String var2 = var0.getAttribute(var1);

      try {
         return Float.parseFloat(var2);
      } catch (NumberFormatException var4) {
         throw new f("Invalid map: Error reading \'" + var1 + "\' invalid float: " + var2, var4);
      }
   }

   public a(Element var1, b var2, i var3) {
      this.b = var1.getAttribute("name");
      if(this.b != null) {
         this.c = this.b.trim().toLowerCase(Locale.ENGLISH);
      }

      this.d = var1.getAttribute("type");
      this.e = Float.parseFloat(var1.getAttribute("x"));
      this.f = Float.parseFloat(var1.getAttribute("y"));
      if(var1.hasAttribute("rotation")) {
         this.i = Float.parseFloat(var1.getAttribute("rotation")) - 90.0F;
      }

      if(!var1.getAttribute("width").equals("")) {
         this.g = a(var1, "width");
      }

      if(!var1.getAttribute("height").equals("")) {
         this.h = a(var1, "height");
      }

      Element var4 = (Element)var1.getElementsByTagName("image").item(0);
      if(var4 != null) {
         this.p = var4.getAttribute("source");
      }

      Element var5 = (Element)var1.getElementsByTagName("properties").item(0);
      String var9;
      String var10;
      if(var5 != null) {
         NodeList var6 = var5.getElementsByTagName("property");
         if(var6 != null) {
            this.n = new Properties();

            for(int var7 = 0; var7 < var6.getLength(); ++var7) {
               Element var8 = (Element)var6.item(var7);
               var9 = var8.getAttribute("name");
               var10 = "";
               if(var8.hasAttribute("value")) {
                  var10 = var8.getAttribute("value");
               } else {
                  var10 = var8.getTextContent();
               }

               this.n.setProperty(var9, var10);
            }
         }
      }

      if(var1.hasAttribute("gid")) {
         this.k = Integer.parseInt(var1.getAttribute("gid"));
         this.l = var2.a(this.k);
         if(this.l == null) {
            throw new RuntimeException("Unable to decode base 64 block, could not find tileId:" + this.k);
         }

         this.l.p = true;
         this.l.r = true;
         this.m = this.k - this.l.l;
      }

      Properties var18 = this.n;
      this.j = new RectF(this.e, this.f, this.e + this.g, this.f + this.h);
      var2.a(this.j);
      this.e = this.j.a;
      this.f = this.j.b;
      this.g = this.j.b();
      this.h = this.j.c();
      float var19 = this.j.d();
      float var20 = this.j.e();
      var9 = var1.getAttribute("type");
      if(var9 != null && !var9.equals("") && !var9.equals("unit") && !var9.equals("comment") && !var3.b.equalsIgnoreCase("triggers")) {
         this.d("Triggers should be on triggers layer");
      }

      if(var18 != null) {
         var10 = var18.getProperty("unit");
         String var11 = var18.getProperty("customUnit");
         if(var10 != null || var11 != null) {
            String var12 = var18.getProperty("team");
            n var13 = null;
            if(var12 == null) {
               throw new f("Unit object team missing for:" + (var10 != null?var10:var11));
            }

            if("none".equalsIgnoreCase(var12)) {
               var13 = n.k(-1);
            } else {
               int var14;
               try {
                  var14 = Integer.valueOf(var12).intValue();
               } catch (NumberFormatException var17) {
                  throw new f("Unit object team invalid: " + var17.getMessage(), var17);
               }

               var13 = n.k(var14);
               if(var13 == null) {
                  l.b("map", "Unit object without team:" + var10 + " (skipping unit)");
                  return;
               }

               if(var13.b()) {
                  l.b("map", "Unit team is marked as spectator:" + var10 + " (skipping unit)");
                  return;
               }
            }

            Object var21;
            if(var11 != null) {
               com.corrodinggames.rts.game.units.custom.l var15 = com.corrodinggames.rts.game.units.custom.l.n(var11);
               if(var15 == null) {
                  throw new f("Could not find custom unit of:" + var11 + " at x:" + this.e + ", y:" + this.f);
               }

               as var16 = com.corrodinggames.rts.game.units.custom.l.c((as)var15);
               if(var16 != null) {
                  if(var16 instanceof com.corrodinggames.rts.game.units.custom.l) {
                     var15 = (com.corrodinggames.rts.game.units.custom.l)var16;
                  } else {
                     l.b("replacement not a custom unit:" + var16.i());
                  }
               }

               var21 = com.corrodinggames.rts.game.units.custom.l.a(false, var15);
               if(var21 == null) {
                  throw new RuntimeException("Metadata unit is null for:" + var11);
               }
            } else {
               var21 = null;
               as var22 = ar.a(var10);
               if(var22 == null) {
                  throw new f("Could not find unit type of:" + var10 + " at x:" + this.e + ", y:" + this.f);
               }

               var21 = var22.a();
            }

            ((am)var21).eo = var19;
            ((am)var21).ep = var20;
            if(!((am)var21).bI()) {
               ((am)var21).h(this.i);
            }

            if(var13 == null) {
               throw new f("team is null:" + var10);
            }

            ((am)var21).b(var13);
            if(var18.getProperty("type") != null) {
               ((am)var21).a_(var18.getProperty("type"));
            }

            if(var18.getProperty("randomRotate") != null && !((am)var21).bI()) {
               ((am)var21).h((float)com.corrodinggames.rts.gameFramework.f.a((am)var21, -180, 180));
            }

            ((am)var21).bO = "builder".equalsIgnoreCase(var10) || "builder".equalsIgnoreCase(var11);
            ((am)var21).bP = "commandCenter".equalsIgnoreCase(var10) || "commandCenter".equalsIgnoreCase(var11);
            ((am)var21).bM = true;
            ((am)var21).n();
            n.c((am)var21);
            w.dL();
         }
      }

   }

   public boolean a(am var1) {
      return this.j.b((float)((int)var1.eo), (float)((int)var1.ep));
   }

   public void a(String var1) {
      if(!this.o.contains(var1)) {
         this.o.add(var1);
      }

   }

   public String[] a() {
      if(this.n == null) {
         return x.h;
      } else {
         m var1 = new m();
         Enumeration var2 = this.n.propertyNames();

         while(var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            if(!this.o.contains(var3)) {
               var1.add(var3);
            }
         }

         return (String[])var1.toArray(x.h);
      }
   }

   public String b(String var1) {
      this.a(var1);
      return this.n == null?null:this.n.getProperty(var1);
   }

   public String a(String var1, String var2) {
      this.a(var1);
      return this.n == null?null:this.n.getProperty(var1, var2);
   }

   public Integer c(String var1) {
      String var2 = this.a(var1, (String)null);
      if(var2 == null) {
         return null;
      } else {
         try {
            return Integer.valueOf(Integer.parseInt(var2));
         } catch (NumberFormatException var4) {
            throw new f(var1 + ": Unexpected integer value:\'" + var2 + "\'");
         }
      }
   }

   public bb a(String var1, bb var2) {
      String var3 = this.a(var1, (String)null);
      if(var3 == null) {
         return var2;
      } else {
         ArrayList var4 = new ArrayList();
         bc var5 = new bc((String)null, var3);
         var4.add(var5);
         String var6 = var1 + "_";
         m var7 = new m();
         Iterator var8 = this.n.keySet().iterator();

         String var10;
         while(var8.hasNext()) {
            Object var9 = var8.next();
            if(var9 instanceof String) {
               var10 = (String)var9;
               if(var10.startsWith(var6)) {
                  var7.add(var10);
               }
            } else {
               l.b("createLocaleStringFromProperty: Non string:" + var9);
            }
         }

         var8 = var7.iterator();

         while(var8.hasNext()) {
            String var14 = (String)var8.next();
            var10 = var14.substring(var6.length());
            var10 = var10.toLowerCase(Locale.ROOT);
            l.b("createLocaleStringFromProperty checking: " + var14);
            if(var10.length() <= 4) {
               String var11 = this.b(var14);
               l.b("createLocaleStringFromProperty got: " + var11);
               l.b("createLocaleStringFromProperty code: " + var10);
               bc var12 = new bc(var10, var11);
               var4.add(var12);
            }
         }

         bc[] var13 = (bc[])var4.toArray(new bc[0]);
         bb var15 = new bb(var13);
         var15.b();
         l.b("createLocaleStringFromProperty final: " + var15.b());
         l.b("createLocaleStringFromProperty locate: " + com.corrodinggames.rts.gameFramework.h.a.c());
         return var15;
      }
   }

   public void d(String var1) {
      ad.g("(Map trigger: " + this.b + ", type:" + this.d + "): " + var1);
   }

   public String b() {
      return "(Map trigger: " + this.b + ", type:" + this.d + ")";
   }
}
