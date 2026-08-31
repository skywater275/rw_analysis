package com.corrodinggames.rts.gameFramework;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import com.corrodinggames.rts.gameFramework.ap;

class ap$2 implements OnPreparedListener {

   // $FF: synthetic field
   final ap a;


   ap$2(ap var1) {
      this.a = var1;
   }

   public void onPrepared(MediaPlayer var1) {
      var1.start();
   }
}
