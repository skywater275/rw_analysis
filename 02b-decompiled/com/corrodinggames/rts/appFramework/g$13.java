package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.corrodinggames.rts.appFramework.g;

class g$13 implements OnClickListener {

   // $FF: synthetic field
   final g a;


   g$13(g var1) {
      this.a = var1;
   }

   public void onClick(DialogInterface var1, int var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      var3.bX.b("exited");
      this.a.b();
   }
}
