package com.corrodinggames.rts.appFramework;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import com.corrodinggames.rts.appFramework.g;
import com.corrodinggames.rts.appFramework.g$4$1;

class g$4 implements OnClickListener {

   // $FF: synthetic field
   final EditText a;
   // $FF: synthetic field
   final com.corrodinggames.rts.gameFramework.l b;
   // $FF: synthetic field
   final g c;


   g$4(g var1, EditText var2, com.corrodinggames.rts.gameFramework.l var3) {
      this.c = var1;
      this.a = var2;
      this.b = var3;
   }

   public void onClick(DialogInterface var1, int var2) {
      String var3 = this.a.getText().toString();
      if(!var3.contains("/") && !var3.contains("\\") && !var3.contains(":") && !var3.contains("*") && !var3.contains("?") && !var3.contains("\"") && !var3.contains("<") && !var3.contains(">")) {
         this.b.bL.a(this.b.dl, "/SD/rustedWarfare/maps/" + var3 + ".tmx");
      } else {
         Builder var4 = new Builder(this.c);
         var4.setTitle("Bad Map Name");
         var4.setMessage("The characters /\\:*?\"<> are not allowed (fat32 formatting)");
         var4.setPositiveButton("Ok", new g$4$1(this, var3));
         var4.show();
      }

   }
}
