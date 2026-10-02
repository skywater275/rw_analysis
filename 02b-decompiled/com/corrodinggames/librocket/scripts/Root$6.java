package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root;

class Root$6 implements Runnable {

   // $FF: synthetic field
   final String val$serverListDataId;
   // $FF: synthetic field
   final String val$serverRowTemplateId;
   // $FF: synthetic field
   final String val$refreshButton;
   // $FF: synthetic field
   final Root this$0;


   Root$6(Root var1, String var2, String var3, String var4) {
      this.this$0 = var1;
      this.val$serverListDataId = var2;
      this.val$serverRowTemplateId = var3;
      this.val$refreshButton = var4;
   }

   public void run() {
      this.this$0.scriptEngine.addScriptToQueue("displayServerListRaw(" + this.this$0.restrictedString(this.val$serverListDataId) + "," + this.this$0.restrictedString(this.val$serverRowTemplateId) + "," + this.this$0.restrictedString(this.val$refreshButton) + ")");
   }
}
