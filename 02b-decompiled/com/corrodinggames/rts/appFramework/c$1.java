package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class c$1 implements OnClickListener {

   // $FF: synthetic field
   final com.corrodinggames.rts.gameFramework.l a;
   // $FF: synthetic field
   final Runnable b;


   c$1(com.corrodinggames.rts.gameFramework.l var1, Runnable var2) {
      this.a = var1;
      this.b = var2;
   }

   public void onClick(DialogInterface var1, int var2) {
      this.a.bQ.storageType = 1;
      this.a.bQ.hasSelectedAStorageType = true;
      com.corrodinggames.rts.gameFramework.e.a.b();
      this.a.bQ.save();
      if(this.b != null) {
         this.b.run();
      }

   }
}
