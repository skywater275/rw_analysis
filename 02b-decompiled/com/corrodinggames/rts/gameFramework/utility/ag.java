package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.ag$1;
import com.corrodinggames.rts.gameFramework.utility.ah;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ag extends af {

   static HashMap a = new HashMap();


   public static void h(String var0) {
      com.corrodinggames.rts.gameFramework.l.e("Zip: " + var0);
   }

   public boolean a(String var1) {
      if(!var1.endsWith(".rwmod") && !var1.endsWith(".rwmod/") && !var1.endsWith(".rwmod\\")) {
         ah var2 = d(var1, true);
         return var2 == null?false:var2.c(l(var1));
      } else {
         return true;
      }
   }

   public String f(String var1) {
      return var1;
   }

   public static boolean i(String var0) {
      return var0.contains(".rwmod/") || var0.contains(".rwmod\\") || var0.endsWith(".rwmod");
   }

   public boolean d(String var1) {
      if(!var1.endsWith(".rwmod") && !var1.endsWith(".rwmod/") && !var1.endsWith(".rwmod\\")) {
         ah var2 = d(var1, true);
         return var2 == null?false:var2.d(l(var1));
      } else {
         return true;
      }
   }

   public boolean e(String var1) {
      h("createDirectory not supported in zip files: " + var1);
      return false;
   }

   public String[] b(String var1) {
      ah var2 = d(var1, true);
      return var2 == null?null:var2.e(l(var1));
   }

   public long a(String var1, boolean var2) {
      ah var3 = d(var1, var2);
      if(var3 == null) {
         return -1L;
      } else {
         String var4 = l(var1);
         long var5 = var3.h(var4);
         return var5;
      }
   }

   public j b(String var1, boolean var2) {
      ah var3 = d(var1, var2);
      if(var3 == null) {
         return null;
      } else {
         String var4 = l(var1);
         j var5 = var3.i(var4);
         return var5;
      }
   }

   public long g(String var1) {
      String var2 = j(var1);
      af var3 = ae.b(var2);
      if(var3 != null) {
         return var3.g(var2);
      } else {
         File var4 = new File(var2);
         return var4.lastModified();
      }
   }

   public OutputStream c(String var1, boolean var2) {
      h("writableOutputSteam not supported in zip files: " + var1);
      return null;
   }

   public boolean a(String var1, String var2) {
      h("Rename not supported in zip files: " + var1 + " to " + var2);
      return false;
   }

   public boolean c(String var1) {
      h("Delete not supported in zip files: " + var1);
      return false;
   }

   public static String j(String var0) {
      int var1 = var0.indexOf(".rwmod/");
      int var2 = var0.indexOf(".rwmod\\");
      if(var2 != -1 && (var2 < var1 || var1 == -1)) {
         var1 = var2;
      }

      if(var1 == -1 && var0.endsWith(".rwmod")) {
         var1 = var0.length() - ".rwmod".length();
      }

      if(var1 == -1) {
         throw new RuntimeException("Could not find .rwmod in path: " + var0);
      } else {
         return var0.substring(0, var1 + ".rwmod".length());
      }
   }

   public static ah d(String var0, boolean var1) {
      String var2 = j(var0);
      HashMap var3 = a;
      synchronized(a) {
         ah var4 = (ah)a.get(var2);
         if(var4 == null) {
            String var5;
            if(var1) {
               var5 = var2;
            } else {
               var5 = com.corrodinggames.rts.gameFramework.e.a.e(var2);
            }

            try {
               var4 = new ah(var2, var5);
            } catch (IOException var10) {
               h("Failed to open source zip: \'" + var5 + "\'");
               var10.printStackTrace();
               String var7 = "Failed to open zip, " + var10.getMessage();
               String var8 = "";
               if(com.corrodinggames.rts.gameFramework.e.a.g(var2)) {
                  h("isDirectory: " + var2);
                  var7 = "Failed to open .rwmod file (Appears to be a directory!). Please remove .rwmod from any folder names.";
               }

               com.corrodinggames.rts.gameFramework.e.a.b(var7 + var8);
               return null;
            } catch (IllegalArgumentException var11) {
               h("Failed to open source zip: \'" + var5 + "\'");
               var11.printStackTrace();
               com.corrodinggames.rts.gameFramework.e.a.b("Failed to open zip, " + var11.getMessage());
               return null;
            }

            a.put(var2, var4);
         }

         return var4;
      }
   }

   public static void e(String var0, boolean var1) {
      String var2 = j(var0);
      HashMap var3 = a;
      synchronized(a) {
         ah var4 = (ah)a.remove(var2);
         if(var4 != null) {
            com.corrodinggames.rts.gameFramework.l.e("Closing zip file: " + var2);
            ag$1 var5 = new ag$1(var2, var4);
            (new Thread(var5)).start();
         }

      }
   }

   public void k(String var1) {
      e(var1, false);
   }

   public void a() {}

   public static String l(String var0) {
      String var1 = j(var0);
      String var2 = var0.substring(var1.length());
      if(var2.startsWith("/") || var2.startsWith("\\")) {
         var2 = var2.substring(1);
      }

      if(var2.startsWith("/") || var2.startsWith("\\")) {
         var2 = var2.substring(1);
      }

      if(var2.contains("\\")) {
         var2 = var2.replace("\\", "/");
      }

      if(var2.contains("..")) {
         String[] var3 = com.corrodinggames.rts.gameFramework.f.c(var2, '/');
         ArrayList var4 = new ArrayList(var3.length);
         int var5 = 0;

         for(int var6 = var3.length - 1; var6 >= 0; --var6) {
            if(var3[var6].equals("..")) {
               ++var5;
            } else if(var5 > 0) {
               --var5;
            } else {
               var4.add(0, var3[var6]);
            }
         }

         if(var5 != 0) {
            h("getPathInZip: Backtracking attempt out of zip: " + var2);
         }

         var2 = com.corrodinggames.rts.gameFramework.f.a((CharSequence)"/", (Iterable)var4);
      }

      return var2;
   }

}
