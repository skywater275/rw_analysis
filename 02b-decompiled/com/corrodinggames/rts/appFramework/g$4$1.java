package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.appFramework.g$4;

class g$4$1 implements OnClickListener {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final g$4 b;


   g$4$1(g$4 var1, String var2) {
      this.b = var1;
      this.a = var2;
   }

   public void onClick(DialogInterface var1, int var2) {
      g.b(this.b.c, this.a);
   }
}
