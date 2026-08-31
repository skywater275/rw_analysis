package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import com.corrodinggames.rts.appFramework.g;

class g$16 implements OnClickListener {

   // $FF: synthetic field
   final EditText a;
   // $FF: synthetic field
   final boolean b;
   // $FF: synthetic field
   final g c;


   g$16(g var1, EditText var2, boolean var3) {
      this.c = var1;
      this.a = var2;
      this.b = var3;
   }

   public void onClick(DialogInterface var1, int var2) {
      String var3 = this.a.getText().toString();
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var3.trim().equals("")) {
         if(this.b) {
            var4.bX.l(var3);
         } else {
            var4.bX.m(var3);
         }
      }

      var4.bS.u = false;
   }
}
