package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamNetworking$P2PSend;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.k;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class m extends OutputStream {

   boolean a;
   // $FF: synthetic field
   final k b;


   public m(k var1) {
      this.b = var1;
      this.a = true;
   }

   public void write(int var1) {
      com.corrodinggames.rts.gameFramework.l.g("SteamSocketOutputStream: Slow write: " + var1);
      byte[] var2 = new byte[]{(byte)var1};
      this.write(var2);
   }

   public void write(byte[] var1, int var2, int var3) {
      if(this.b.b) {
         com.corrodinggames.rts.gameFramework.l.e("cannot write steam socket closed");
      } else if(var3 > 307200) {
         com.corrodinggames.rts.gameFramework.l.e("Steam spliting large packet to:" + this.b.e + " len:" + var3);
         int var10 = var3;

         do {
            int var11 = var10;
            if(var10 > 256000) {
               var11 = 256000;
            }

            this.write(var1, var2, var11);
            var2 += var11;
            var10 -= var11;
         } while(var10 > 0);

      } else {
         ByteBuffer var4 = ByteBuffer.allocateDirect(var3);
         var4.put(var1, var2, var3);
         var4.flip();
         b var5 = this.b.a;
         synchronized(this.b.a) {
            try {
               if(this.a) {
                  this.a = false;
                  com.corrodinggames.rts.gameFramework.l.e("First packet to:" + this.b.e);
               }

               boolean var6 = this.b.a.h.sendP2PPacket(this.b.e, var4, SteamNetworking$P2PSend.Reliable, 0);
               if(!var6) {
                  com.corrodinggames.rts.gameFramework.l.e("steam sendP2PPacket failed (size: " + var3 + " to:" + this.b.e + ")");
               }
            } catch (SteamException var8) {
               throw new IOException(var8);
            }

         }
      }
   }

   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }
}
