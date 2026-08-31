package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.bf;
import com.corrodinggames.rts.gameFramework.l;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class be {

   public boolean a = false;
   String b = "rtsSave";
   String c = "rtsSave.bak";
   public boolean d = false;
   static be e = null;
   HashMap f = new HashMap();


   public strictfp void a(Context var1) {
      if(!l.aU) {
         if(!this.a) {
            try {
               if(var1 == null) {
                  throw new IOException("context==null");
               }

               FileOutputStream var2;
               try {
                  var2 = var1.b(this.b, 0);
               } catch (NullPointerException var6) {
                  throw new IOException("openFileOutput NullPointerException", var6);
               }

               DataOutputStream var3 = new DataOutputStream(var2);
               this.a(var3);
               var3.close();
               var2.close();
            } catch (FileNotFoundException var7) {
               Log.b("RustedWarfare", "file save error:", var7);
            } catch (IOException var8) {
               Log.b("RustedWarfare", "file save error:", var8);
            }

            if(this.d) {
               try {
                  File var9 = new File(Environment.getExternalStorageDirectory() + "/" + this.c);
                  FileOutputStream var10 = new FileOutputStream(var9);
                  DataOutputStream var4 = new DataOutputStream(var10);
                  this.a(var4);
                  var4.close();
                  var10.close();
               } catch (IOException var5) {
                  Log.b("RustedWarfare", "file read error:", var5);
               }
            }

         }
      }
   }

   public strictfp boolean a(DataOutputStream var1) {
      if(l.aU) {
         return false;
      } else {
         try {
            var1.writeInt(1);
            var1.writeInt(0);
            var1.writeInt(this.f.size());
            Iterator var2 = this.f.values().iterator();

            while(var2.hasNext()) {
               bf var3 = (bf)var2.next();
               var1.writeInt(0);
               var1.writeInt(var3.a);
               var1.writeUTF(var3.b);
               var1.writeInt(var3.c);
               var1.writeBoolean(var3.d);
               var1.writeBoolean(var3.e);
               var1.writeBoolean(var3.f);
               var1.writeLong(var3.g);
               var1.writeInt(var3.h);
            }

            var1.flush();
            return true;
         } catch (IOException var4) {
            Log.b("RustedWarfare", "file save error:", var4);
            return false;
         }
      }
   }

   public strictfp void b(Context var1) {
      if(!this.a) {
         boolean var2 = false;
         Log.d("RustedWarfare", "Trying to load from internal memory");

         try {
            FileInputStream var3 = var1.a(this.b);
            DataInputStream var4 = new DataInputStream(var3);
            var2 = this.a(var4);
            if(var2) {
               Log.d("RustedWarfare", "loaded from internal memory");
            }

            var3.close();
         } catch (IOException var7) {
            Log.b("RustedWarfare", "file read error:", var7);
         }

         if(this.d && !var2) {
            Log.d("RustedWarfare", "Trying to load from SD");

            try {
               File var8 = new File(Environment.getExternalStorageDirectory() + "/" + this.c);
               FileInputStream var9 = new FileInputStream(var8);
               DataInputStream var5 = new DataInputStream(var9);
               var2 = this.a(var5);
               if(var2) {
                  Log.d("RustedWarfare", "loaded from SD");
               }

               var5.close();
               var9.close();
            } catch (IOException var6) {
               Log.b("RustedWarfare", "file read error:", var6);
            }
         }

      }
   }

   public strictfp boolean a(DataInputStream var1) {
      try {
         int var2 = var1.readInt();
         if(var2 > 1) {
            Log.d("RustedWarfare", "Warning file is at version:" + var2);
            return false;
         } else {
            var1.readInt();
            int var3 = var1.readInt();
            this.f.clear();

            for(int var4 = 0; var4 < var3; ++var4) {
               bf var5 = new bf(this);
               var1.readInt();
               var5.a = var1.readInt();
               String var6 = var1.readUTF();
               if(var6.equals("maps/challenge/l030;Level 5.tmx")) {
                  Log.d("RustedWarfare", "converting:" + var6);
                  var6 = "maps/challenge/l090;Level 7.tmx";
               }

               var5.b = var6;
               var5.c = var1.readInt();
               var5.d = var1.readBoolean();
               var5.e = var1.readBoolean();
               var5.f = var1.readBoolean();
               var5.g = var1.readLong();
               var5.h = var1.readInt();
               this.f.put(this.a(var5.b), var5);
            }

            return true;
         }
      } catch (IOException var7) {
         Log.b("RustedWarfare", "file read error:", var7);
         return false;
      }
   }

   public static strictfp be c(Context var0) {
      if(e == null) {
         e = new be(var0);
         if(!l.aU) {
            e.b(var0);
         }
      }

      return e;
   }

   private strictfp be(Context var1) {}

   public strictfp String a(String var1) {
      Integer var2 = l.l(var1);
      return var2 != null?l.j(var1) + "/l" + var2:var1;
   }

   public strictfp bf b(String var1) {
      String var2 = this.a(var1);
      bf var3 = (bf)this.f.get(var2);
      Log.d("RustedWarfare", "StateEngine: get(" + var1 + ")=" + var3 + "  (key=" + var2 + ")");
      if(var3 == null) {
         var3 = new bf(this);
         var3.a = 1;
         var3.b = var1;
         this.f.put(var2, var3);
      }

      return var3;
   }

}
