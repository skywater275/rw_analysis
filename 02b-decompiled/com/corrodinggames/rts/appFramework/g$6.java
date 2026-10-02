package com.corrodinggames.rts.appFramework;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.appFramework.g$6$1;

class g$6 implements OnClickListener {

   // $FF: synthetic field
   final EditText a;
   // $FF: synthetic field
   final g b;


   g$6(g var1, EditText var2) {
      this.b = var1;
      this.a = var2;
   }

   public void onClick(DialogInterface var1, int var2) {
      String var3 = this.a.getText().toString();
      if(!var3.contains("/") && !var3.contains("\\") && !var3.contains(":") && !var3.contains("*") && !var3.contains("?") && !var3.contains("\"") && !var3.contains("<") && !var3.contains(">")) {
         this.b.d(var3);
      } else {
         Builder var4 = new Builder(this.b);
         var4.setTitle("Bad Save Name");
         var4.setMessage("The characters /\\:*?\"<> are not allowed (fat32 formatting)");
         var4.setPositiveButton("Ok", new g$6$1(this, var3));
         var4.show();
      }

   }
}
