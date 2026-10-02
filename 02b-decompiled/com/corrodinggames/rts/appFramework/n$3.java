package com.corrodinggames.rts.appFramework;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import com.corrodinggames.rts.appFramework.n;
import com.corrodinggames.rts.gameFramework.j.ae;

final class n$3 implements OnClickListener {

   // $FF: synthetic field
   final EditText a;
   // $FF: synthetic field
   final ae b;


   n$3(EditText var1, ae var2) {
      this.a = var1;
      this.b = var2;
   }

   public void onClick(DialogInterface var1, int var2) {
      String var3 = this.a.getText().toString();
      this.b.a(var3);
      n.i = null;
      n.j = null;
   }
}
