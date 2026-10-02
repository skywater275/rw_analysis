package com.corrodinggames.rts.gameFramework;

import android.media.MediaPlayer;
import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.ao;
import com.corrodinggames.rts.gameFramework.ap;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class an extends aq {

   ArrayList a = new ArrayList();
   ArrayList b = new ArrayList();
   ArrayList c = new ArrayList();
   boolean d = false;


   public ar a(String var1) {
      return new ao(var1, this);
   }

   public as a() {
      ap var1 = new ap(this);
      return var1;
   }

   public void a(am var1) {
      this.e = var1;
      if(this.d) {
         l.e("AndroidMusicFactory already loaded");
      }

      l.e("AndroidMusicFactory - load");
      this.d = true;
      this.a.add(new MediaPlayer());
      this.a.add(new MediaPlayer());
      this.b.addAll(this.a);
   }

   public void b() {}
}
