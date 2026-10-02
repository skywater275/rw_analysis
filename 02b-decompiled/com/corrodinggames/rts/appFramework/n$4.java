package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.j.ae;

final class n$4 implements OnClickListener {

   // $FF: synthetic field
   final ae a;


   n$4(ae var1) {
      this.a = var1;
   }

   public void onClick(DialogInterface var1, int var2) {
      this.a.a();
      n.i = null;
      n.j = null;
   }
}
