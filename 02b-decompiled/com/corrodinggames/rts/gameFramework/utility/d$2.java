package com.corrodinggames.rts.gameFramework.utility;

import android.util.Log;
import com.corrodinggames.rts.gameFramework.utility.f;

final class d$2 implements f {

   public void a(InterruptedException var1) {
      Log.c("ANRWatchdog", "Interrupted: " + var1.getMessage());
   }
}
