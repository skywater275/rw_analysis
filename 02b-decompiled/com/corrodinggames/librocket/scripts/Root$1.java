package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root;
import java.util.HashMap;

class Root$1 implements Runnable {

   // $FF: synthetic field
   final String val$level;
   // $FF: synthetic field
   final Root this$0;


   Root$1(Root var1, String var2) {
      this.this$0 = var1;
      this.val$level = var2;
   }

   public void run() {
      Object var1 = null;
      boolean var2 = false;
      this.this$0.libRocket.setDocument(this.val$level, (HashMap)var1, var2);
      this.this$0.onShowNewScreen();
   }
}
