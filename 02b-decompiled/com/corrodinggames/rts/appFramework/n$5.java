package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.j.ae;

final class n$5 implements OnCancelListener {

   // $FF: synthetic field
   final ae a;


   n$5(ae var1) {
      this.a = var1;
   }

   public void onCancel(DialogInterface var1) {
      this.a.a();
      n.i = null;
      n.j = null;
   }
}
