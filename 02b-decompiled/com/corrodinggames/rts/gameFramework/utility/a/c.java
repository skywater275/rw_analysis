package com.corrodinggames.rts.gameFramework.utility.a;

import android.content.Context;
import android.net.Uri;
import com.corrodinggames.rts.appFramework.common.SAFInterface.FileRow;
import com.corrodinggames.rts.gameFramework.utility.a.a;
import com.corrodinggames.rts.gameFramework.utility.a.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

class c {

   String a;
   Uri b;
   boolean c;
   HashMap d;
   HashMap e;
   boolean f;
   int g;
   // $FF: synthetic field
   final b h;


   public c(b var1, String var2, Uri var3, boolean var4) {
      this.h = var1;
      this.a = var2;
      this.b = var3;
      this.c = var4;
   }

   public HashMap a() {
      if(this.d == null || this.f || this.g != this.h.g) {
         synchronized(this) {
            if(this.d == null || this.f || this.g != this.h.g) {
               this.a(com.corrodinggames.rts.appFramework.c.a());
            }
         }
      }

      return this.d;
   }

   public void a(Context var1) {
      HashMap var2 = new HashMap();
      HashMap var3 = new HashMap();
      if(this.c) {
         ArrayList var4 = a.a.listWithDetails(var1, this.b);
         Iterator var5 = var4.iterator();

         while(var5.hasNext()) {
            FileRow var6 = (FileRow)var5.next();
            String var7 = var6.id;
            Uri var8 = a.a.getChildUri(this.b, var7);
            String var9 = var6.name;
            boolean var10 = var6.isDirectory;
            if(var9.contains("/")) {
               a.h("Name contains symbols: " + var9);
               var9 = var9.replace("/", "_");
            }

            String var11 = this.a + "/" + var9;
            c var12 = new c(this.h, var11, var8, var10);
            var2.put(var9, var12);
            String var13 = var9.toLowerCase(Locale.ROOT);
            if(var3.get(var13) == null) {
               var3.put(var13, var12);
            }
         }
      }

      this.d = var2;
      this.e = var3;
      this.f = false;
      this.g = this.h.g;
   }
}
