package com.corrodinggames.rts.gameFramework;

import android.app.AlertDialog.Builder;
import android.view.WindowManager.BadTokenException;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.l$2$1;
import com.corrodinggames.rts.gameFramework.l$2$2;

class l$2 implements Runnable {

   // $FF: synthetic field
   final l a;


   l$2(l var1) {
      this.a = var1;
   }

   public void run() {
      l$2$1 var1 = new l$2$1(this);
      l$2$2 var2 = new l$2$2(this);
      l.e("showMessageBoxRunnable context:" + this.a.am.getClass().getName());

      try {
         (new Builder(this.a.am)).setIcon(17301543).setTitle(this.a.dF).setMessage(this.a.dG).setOnCancelListener(var2).setPositiveButton("Ok", var1).show();
      } catch (BadTokenException var4) {
         l.b("Failed to show message: " + this.a.dG);
         var4.printStackTrace();
      }

   }
}
