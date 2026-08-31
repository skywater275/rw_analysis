package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.af;
import com.corrodinggames.rts.gameFramework.ag;
import com.corrodinggames.rts.gameFramework.ah;
import com.corrodinggames.rts.gameFramework.ak;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes$MissingKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class ad {

   public String a;
   public boolean b = false;
   public ArrayList c = new ArrayList();
   public ArrayList d = new ArrayList();


   public boolean a() {
      Iterator var1 = this.c.iterator();

      af var2;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         var2 = (af)var1.next();
      } while(var2.a != -1 || !var2.a());

      return true;
   }

   public boolean b() {
      Iterator var1 = this.c.iterator();

      af var2;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         var2 = (af)var1.next();
      } while(var2 == null || var2.a != -1 || !var2.b());

      return true;
   }

   public String c() {
      Iterator var1 = this.c.iterator();

      af var2;
      do {
         if(!var1.hasNext()) {
            return "";
         }

         var2 = (af)var1.next();
      } while(var2 == null);

      return var2.c().toUpperCase();
   }

   public af a(int var1) {
      if(this.c.size() > var1) {
         af var2 = (af)this.c.get(var1);
         return var2;
      } else {
         return null;
      }
   }

   public String b(int var1) {
      if(this.c.size() > var1) {
         af var2 = (af)this.c.get(var1);
         return var2 == null?"<null>":var2.c().toUpperCase();
      } else {
         return "";
      }
   }

   public ad c(int var1) {
      byte var2 = 0;
      return this.a(var1, 0, var2, false);
   }

   public ad a(int var1, int var2, int var3, boolean var4) {
      ag var5 = new ag();
      var5.e = var1;
      var5.a = -1;
      var5.b = var3;
      if(var4) {
         var5.d = true;
      }

      if(this.c.size() <= var2) {
         this.c.add(new ak());
      }

      if(this.c.size() <= var2) {
         this.c.add(new ak());
      }

      this.c.set(var2, var5);
      return this;
   }

   public ad a(String var1) {
      return this.a(var1, -1);
   }

   public ad a(String var1, int var2) {
      if(var1 == null) {
         throw new RuntimeException("key==null");
      } else {
         return this.a(-1, var1, var2);
      }
   }

   public ad a(int var1, String var2, int var3) {
      if(var2 == null) {
         throw new RuntimeException("key==null");
      } else {
         ag var4 = new ag();
         var4.a = var1;
         var4.b = 0;
         var2 = var2.toLowerCase(Locale.ENGLISH);
         if(var2.contains("alt+")) {
            var2 = var2.replace("alt+", "");
            var4.b += 4;
         }

         if(var2.contains("ctrl+")) {
            var2 = var2.replace("ctrl+", "");
            ++var4.b;
         }

         if(var2.contains("shift+")) {
            var2 = var2.replace("shift+", "");
            var4.b += 2;
         }

         try {
            var4.e = ac.d(var2);
            if(var3 == -1) {
               this.c.add(var4);
            } else {
               if(this.c.size() <= var3) {
                  this.c.add(new ak());
               }

               if(this.c.size() <= var3) {
                  this.c.add(new ak());
               }

               this.c.set(var3, var4);
            }
         } catch (SlickToAndroidKeycodes$MissingKey var7) {
            var7.printStackTrace();
            l var6 = l.B();
            if(var6 != null) {
               var6.a(var7.getMessage(), 1);
            }
         }

         return this;
      }
   }

   public ad b(int var1, int var2, int var3, boolean var4) {
      ah var5 = new ah();
      var5.a = var1;
      var5.e = var2;
      var5.f = var3;
      var5.g = var4;

      try {
         var5.i = var5.a(true);
      } catch (IndexOutOfBoundsException var7) {
         l.b("Failed to bind Axis:" + var3 + " on joystick:" + var2);
         return this;
      }

      this.c.add(var5);
      return this;
   }

   public boolean d() {
      return false;
   }

   public String e() {
      return this.a.replace("-", "").replace("  ", " ").replace("  ", " ").replace(" ", "_").toLowerCase(Locale.ENGLISH);
   }
}
