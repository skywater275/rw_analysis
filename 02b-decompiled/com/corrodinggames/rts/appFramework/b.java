package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import java.util.ArrayList;

public class b extends Activity {

   ArrayList b = new ArrayList();


   public void a(Runnable var1) {
      ArrayList var2 = this.b;
      synchronized(this.b) {
         this.b.add(var1);
      }
   }
}
