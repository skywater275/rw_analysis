package com.corrodinggames.rts.appFramework;

import android.net.Uri;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.d;
import com.corrodinggames.rts.appFramework.s$1;

public class s extends b {

   boolean c = true;
   static boolean d = false;
   int[] e = new int[]{100, 250, 500, 1000, 2000, 5000, 10000};
   d f = new s$1(this);


   public void l() {
      String var1 = "rustedWarfare".replace("//", "%2F");
      Uri var2 = Uri.parse("content://com.android.externalstorage.documents/document/primary%3A" + var1);
      c.a(this, 9, true, "Select a Rusted Warfare Folder to use", var2);
   }

}
