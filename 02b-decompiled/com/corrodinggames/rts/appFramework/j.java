package com.corrodinggames.rts.appFramework;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.k;
import java.util.ArrayList;
import java.util.Collections;

public class j extends b {

   String[] c;


   public void b() {
      super.b();
      c.a(this, true);
   }

   public static String[] l() {
      String[] var0 = com.corrodinggames.rts.gameFramework.e.a.a("/SD/rustedWarfare/saves/", false);
      if(var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();
         String[] var2 = var0;
         int var3 = var0.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if(!var5.endsWith(".map") && !var5.endsWith(".tmp")) {
               var1.add(var5);
            }
         }

         Collections.sort(var1, new k());
         return (String[])var1.toArray(new String[0]);
      }
   }

   public void onCreateContextMenu(ContextMenu var1, View var2, ContextMenuInfo var3) {
      super.onCreateContextMenu(var1, var2, var3);
      Button var4 = (Button)var2;
      var1.setHeaderTitle(var4.getText());
      var1.add(0, var2.getId(), 0, "Share");
      var1.add(1, var2.getId(), 0, "Rename");
      var1.add(2, var2.getId(), 0, "Delete");
      if(this.c != null && this.c.length > 0) {
         String var5 = this.c[var2.getId()];
         String var6 = com.corrodinggames.rts.gameFramework.e.a.n(var5);
         MenuItem var7 = var1.add(3, var2.getId(), 0, "Storage: " + var6);
         if(var7 != null) {
            var7.setEnabled(false);
         }
      }

   }
}
