package com.corrodinggames.rts.gameFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.corrodinggames.rts.gameFramework.l$2;

class l$2$2 implements OnCancelListener {

   // $FF: synthetic field
   final l$2 a;


   l$2$2(l$2 var1) {
      this.a = var1;
   }

   public void onCancel(DialogInterface var1) {
      this.a.a.bp = false;
   }
}
