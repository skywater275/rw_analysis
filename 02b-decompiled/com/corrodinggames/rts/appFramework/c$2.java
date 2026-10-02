package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.c$2$1;
import com.corrodinggames.rts.appFramework.s;

final class c$2 implements OnClickListener {

   // $FF: synthetic field
   final Activity a;
   // $FF: synthetic field
   final com.corrodinggames.rts.gameFramework.l b;
   // $FF: synthetic field
   final Runnable c;


   c$2(Activity var1, com.corrodinggames.rts.gameFramework.l var2, Runnable var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public void onClick(DialogInterface var1, int var2) {
      com.corrodinggames.rts.gameFramework.e.b var3 = com.corrodinggames.rts.gameFramework.e.a.a(true);
      if(!var3.b) {
         com.corrodinggames.rts.gameFramework.l.e("Storage setup: Not using SAF, not showing setup folder popup");
         boolean var7 = c.b(this.a);
         if(var7) {
            this.b.bQ.storageType = 2;
            this.b.bQ.hasSelectedAStorageType = true;
            com.corrodinggames.rts.gameFramework.e.a.b();
            this.b.bQ.save();
         }

      } else if(this.a instanceof s) {
         com.corrodinggames.rts.gameFramework.l.e("Storage setup: Already on settings page");
         s var6 = (s)this.a;
         var6.l();
      } else {
         Intent var4 = new Intent(this.a, s.class);
         var4.putExtra("mode", "setupExternalFolder");
         c.a(var4);
         this.a.a(var4);
         if(this.a instanceof b) {
            if(this.c != null) {
               c$2$1 var5 = new c$2$1(this);
               ((b)this.a).a(var5);
            }
         } else {
            com.corrodinggames.rts.gameFramework.l.b("context not instance CommonActivity");
         }

      }
   }
}
