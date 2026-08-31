package com.corrodinggames.rts.gameFramework.e;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class c {

   public String a = "FileLoader: ";
   public boolean b = false;
   public boolean c = false;
   public boolean d = false;
   String e;
   String f;


   public String a() {
      String var1 = this.e;
      this.e = null;
      return var1;
   }

   public void a(String var1) {
      this.e = var1;
   }

   public String a(String var1, String var2) {
      File var3 = new File(var1);
      File[] var4 = var3.listFiles();
      if(var4 == null) {
         l.e(this.a + "findFileExtension(\'" + var1 + "\',\'" + var2 + "\'): path is not a folder");
         return null;
      } else {
         File[] var5 = var4;
         int var6 = var4.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            File var8 = var5[var7];
            String var9 = var8.getName();
            if(var9.contains(".")) {
               var9 = var9.substring(0, var9.lastIndexOf(46));
            }

            if(var9.equals(var2)) {
               return var1 + "/" + var8.getName();
            }
         }

         l.e(this.a + "Could not find file with path: " + var1 + " file:" + var2);
         return null;
      }
   }

   public boolean b(String var1) {
      return l.aU?false:(var1.startsWith("/")?false:!var1.startsWith("/SD/"));
   }

   public boolean c(String var1) {
      if(!var1.startsWith("/") && !var1.startsWith("\\")) {
         if(l.aU) {
            if(var1.startsWith("mods")) {
               return true;
            }

            if(l.aZ && var1.startsWith("converted-sounds")) {
               return true;
            }
         }

         var1 = var1.split("\\\\")[0];
         return var1.endsWith(":");
      } else {
         return true;
      }
   }

   public String d(String var1) {
      l var2 = l.B();
      String[] var3;
      String var4;
      boolean var5;
      int var6;
      String var7;
      String var8;
      if(var1.contains("MOD|")) {
         var3 = var1.split("/");
         if(var3.length >= 2) {
            var4 = var3[var3.length - 1];
            var5 = false;

            for(var6 = var3.length - 2; var6 >= 0; --var6) {
               var7 = var3[var6];
               if(var7.startsWith("MOD|")) {
                  var8 = var7.substring("MOD|".length());
                  com.corrodinggames.rts.gameFramework.i.b var9 = var2.bZ.c(var8);
                  if(var9 != null) {
                     var4 = com.corrodinggames.rts.gameFramework.f.b(var9.g(), var4);
                     l.e(this.a + "Path changed to mod path:" + var4);
                     var5 = true;
                     break;
                  }

                  l.e(this.a + "Failed to find mod with hash:" + var8);
               }

               var4 = var7 + File.separator + var4;
            }

            if(var5) {
               var1 = var4;
            }
         }

         if(var1.contains("MOD|")) {
            l.e(this.a + "Path still contains prefix: " + var1);
         }
      }

      if(var1.contains("NEW_PATH|")) {
         var3 = var1.split("/");
         if(var3.length >= 2) {
            var4 = var3[var3.length - 1];
            var5 = false;

            for(var6 = var3.length - 2; var6 >= 0; --var6) {
               var7 = var3[var6];
               if(var7.startsWith("NEW_PATH|")) {
                  var8 = var7.substring("NEW_PATH|".length());
                  if(var8.equals("maps2")) {
                     var4 = "/SD/rustedWarfare/maps" + File.separator + var4;
                     l.e(this.a + "Path changed to maps2 path:" + var4);
                     var5 = true;
                     break;
                  }
               }

               var4 = var7 + File.separator + var4;
            }

            if(var5) {
               var1 = var4;
            }
         }
      }

      return var1;
   }

   public String e(String var1) {
      boolean var2 = false;
      if(var1 == null) {
         return "<null>";
      } else {
         var1 = this.f(var1);
         af var3 = ae.b(var1);
         if(var3 != null) {
            String var4 = var3.f(var1);
            return var4;
         } else {
            return var1;
         }
      }
   }

   public String f(String var1) {
      var1 = this.d(var1);
      String var2;
      String var3;
      if(l.aU) {
         if(var1.startsWith("/SD/rusted_warfare_maps")) {
            var1 = "/SD/mods/maps" + var1.substring("/SD/rusted_warfare_maps".length());
            l.e(this.a + "convertAbstractPath: Changing to:" + var1);
         }

         if(var1.startsWith("/SD/rustedWarfare/maps")) {
            var1 = "/SD/mods/maps" + var1.substring("/SD/rustedWarfare/maps".length());
            l.e(this.a + "convertAbstractPath2: Changing to:" + var1);
         }

         if(!var1.startsWith("/SD/") && !var1.startsWith("\\SD\\")) {
            return this.c(var1)?var1:"assets/" + var1;
         } else {
            var2 = var1.substring("/SD/".length());
            var3 = "rustedWarfare/";
            if(var2.startsWith(var3)) {
               var2 = var2.substring(var3.length());
            }

            var2 = this.b() + var2;
            return var2;
         }
      } else if(var1.startsWith("/SD/")) {
         var2 = var1.substring("/SD/".length());
         var3 = "rustedWarfare/";
         if(var2.startsWith(var3)) {
            var2 = var2.substring(var3.length());
         }

         return this.b() + var2;
      } else {
         return var1;
      }
   }

   private String f() {
      if(this.f == null) {
         this.f = Environment.getExternalStorageDirectory() + "";
      }

      return this.f;
   }

   public boolean a(String var1, boolean var2) {
      String var3 = this.f(var1);
      af var4;
      if(var2) {
         var4 = ae.b(var3);
      } else {
         var4 = ae.a(var3);
      }

      if(var4 != null) {
         return var4.d(var3);
      } else if(this.b(var1)) {
         if(this.d) {
            return false;
         } else if(!l.B().bK.a(var3)) {
            l.e(this.a + "isDirectory: asset file doesn\'t exist:" + var3);
            return false;
         } else {
            String var6 = com.corrodinggames.rts.gameFramework.f.k(var1);
            return !var6.contains(".");
         }
      } else {
         File var5 = new File(var3);
         if(!var5.exists()) {
            l.e(this.a + "isDirectory: file doesn\'t exist:" + var3);
            return false;
         } else {
            return var5.isDirectory();
         }
      }
   }

   public boolean g(String var1) {
      String var2 = this.f(var1);
      af var3 = ae.a(var2);
      boolean var5;
      if(var3 != null) {
         var5 = var3.a(var2);
         if(this.c) {
            l.e("fileExists: " + var5 + " with reader: " + var3 + " convertedDir:" + var2);
         }

         return var5;
      } else if(this.b(var1)) {
         if(this.d) {
            if(this.c) {
               l.e("fileExists: false with disableAssets");
            }

            return false;
         } else {
            var5 = l.B().bK.a(var2);
            if(this.c) {
               l.e("fileExists: " + var5 + " with abstractPathAsset convertedDir:" + var2);
            }

            return var5;
         }
      } else {
         File var4 = new File(var2);
         if(var4 != null && var4.exists()) {
            return true;
         } else {
            if(this.c) {
               l.e("fileExists: false with normal file convertedDir:" + var2);
            }

            return false;
         }
      }
   }

   public String[] b(String var1, boolean var2) {
      try {
         String var4 = this.f(var1);
         af var5 = ae.a(var4);
         String[] var3;
         if(var5 != null) {
            var3 = var5.b(var4);
         } else if(this.b(var1)) {
            if(this.d) {
               return null;
            }

            var3 = l.B().bK.b(var4);
         } else {
            File var6 = new File(var4);
            if(var6 == null || !var6.exists()) {
               String var13 = "listDir: path doesn\'t exist:" + var4;
               l.b(var13);
               a.b(var13);
               return null;
            }

            var3 = var6.list();
            if(var3 == null) {
               if(var6 != null && !var6.isDirectory()) {
                  a.b("path is not a directory, .rwmod or .zip");
               }

               return null;
            }
         }

         if(var3 == null) {
            l.e(this.a + "listDir baseList==null:" + var1 + " (non folder?)");
            return null;
         } else {
            ArrayList var12 = new ArrayList();
            String[] var7;
            int var8;
            int var9;
            String var10;
            if(var2) {
               var7 = var3;
               var8 = var3.length;

               for(var9 = 0; var9 < var8; ++var9) {
                  var10 = var7[var9];
                  if(var10.toLowerCase(Locale.ENGLISH).endsWith(".tmx")) {
                     var12.add(var10);
                  }
               }
            } else {
               var7 = var3;
               var8 = var3.length;

               for(var9 = 0; var9 < var8; ++var9) {
                  var10 = var7[var9];
                  var12.add(var10);
               }
            }

            Collections.sort(var12);
            return (String[])var12.toArray(new String[0]);
         }
      } catch (OutOfMemoryError var11) {
         a.b(var11.getMessage());
         return null;
      }
   }

   public File h(String var1) {
      if(var1.contains("\\")) {
         var1 = var1.replace('\\', '/');
      }

      File var2 = new File(var1);
      if(var2.exists()) {
         return var2;
      } else {
         File var3 = var2.getParentFile();
         if(var3 == null || !var3.isDirectory()) {
            var3 = this.h(var3.getAbsolutePath());
            if(var3 == null || !var3.isDirectory()) {
               l.e(this.a + "createFileCaseInsensitive: did not find parent for: " + var1);
               return null;
            }
         }

         File[] var4 = var3.listFiles();
         if(var4 == null) {
            l.e(this.a + "createFileCaseInsensitive: Failed to list files for: " + var1 + " in " + var3);
            return null;
         } else {
            File[] var5 = var4;
            int var6 = var4.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               File var8 = var5[var7];
               if(var8.getName().equalsIgnoreCase(var2.getName())) {
                  return var8;
               }
            }

            return null;
         }
      }
   }

   public j i(String var1) {
      if(var1.startsWith("assets/") || var1.startsWith("assets\\")) {
         var1 = var1.substring("assets/".length());
      }

      String var2 = var1;
      String var3 = "assets/" + var1;
      Context var4 = com.corrodinggames.rts.appFramework.c.a();
      AssetManager var5 = var4.d();
      if(l.aY) {
         ;
      }

      InputStream var6;
      try {
         var6 = var5.a(var2);
      } catch (IOException var9) {
         l.e(this.a + "Could not find asset:" + var3);
         return null;
      }

      try {
         return new j(var6, var3, var2);
      } catch (FileNotFoundException var8) {
         return null;
      }
   }

   public j j(String var1) {
      String var3 = this.f(var1);
      af var4 = ae.a(var3);
      if(var4 != null && !var3.endsWith(".rwmod")) {
         return var4.b(var3, true);
      } else {
         j var2;
         if(!var1.startsWith("/SD/") && !var1.startsWith("\\SD\\")) {
            if(this.c(var1)) {
               try {
                  File var11 = this.h(var1);
                  if(var11 == null) {
                     return null;
                  }

                  FileInputStream var12 = new FileInputStream(var11);
                  var2 = new j(var12, var11.getAbsolutePath());
               } catch (FileNotFoundException var9) {
                  return null;
               }
            } else {
               var2 = this.i(var1);
            }
         } else {
            var1 = var1.substring("/SD/".length());
            String var6 = "rustedWarfare/";
            String var5 = var1;
            if(var1.startsWith(var6)) {
               var5 = var1.substring(var6.length());
            }

            var5 = this.b() + var5;
            if(this.b) {
               l.e(this.a + "openAssetSteam converted:" + var1 + " to: " + var5);
            }

            try {
               File var7 = this.h(var5);
               if(var7 == null) {
                  return null;
               }

               FileInputStream var8 = new FileInputStream(var7);
               var2 = new j(var8, var7.getAbsolutePath());
            } catch (FileNotFoundException var10) {
               return null;
            }
         }

         return var2;
      }
   }

   public OutputStream c(String var1, boolean var2) {
      var1 = this.f(var1);
      af var3 = ae.a(var1);
      if(var3 != null && !var1.endsWith(".rwmod")) {
         return var3.c(var1, var2);
      } else {
         FileOutputStream var4 = new FileOutputStream(var1, var2);
         return var4;
      }
   }

   public boolean k(String var1) {
      String var2 = this.f(var1);
      af var3 = ae.a(var2);
      boolean var4;
      if(var3 != null && !var2.endsWith(".rwmod")) {
         var4 = var3.e(var2);
         if(!var4) {
            l.e("Failed to create directory: " + var2 + " using reader:" + var3);
         }

         return var4;
      } else {
         var4 = (new File(var2)).mkdirs();
         if(!var4) {
            l.e("Failed to create directory: " + var2);
         }

         return var4;
      }
   }

   public String b() {
      return l.aU?"":this.f() + "/rustedWarfare/";
   }

   public String c() {
      String var1;
      if(l.at()) {
         var1 = com.corrodinggames.rts.appFramework.c.a().i().getAbsolutePath();
         if(!var1.endsWith("/")) {
            var1 = var1 + "/";
         }

         return var1;
      } else {
         var1 = this.b();
         return var1.equals("")?"cache/":var1 + "/cache/";
      }
   }

   public long l(String var1) {
      var1 = this.f(var1);
      af var2 = ae.a(var1);
      if(var2 != null) {
         return var2.g(var1);
      } else {
         File var3 = new File(var1);
         if(!var3.exists()) {
            ;
         }

         return var3.lastModified();
      }
   }

   public void a(File var1) {
      if(l.at()) {
         ;
      }

   }

   public File a(String var1, String var2, boolean var3) {
      String var4 = this.b();
      String var5 = var4 + var2 + var1;
      File var6 = new File(var5);
      if(var3) {
         File var7 = var6.getParentFile();
         if(!a.i(var7.getAbsolutePath())) {
            l.e("Making missing parent dir: " + var7.getAbsolutePath());
            if(!a.l(var7.getAbsolutePath())) {
               l.b("getRWFile: Could not create parent directory");
            }
         }

         if(l.at()) {
            ;
         }
      }

      return var6;
   }

   public String d() {
      return "external";
   }

   public String m(String var1) {
      return this.d();
   }

   public boolean e() {
      return true;
   }

   public String n(String var1) {
      if(var1 == null) {
         return null;
      } else {
         String var2 = "[INTERNAL-PATH]/";
         String var3 = "[EXTERNAL-PATH]/";
         int var4 = var1.indexOf("[INTERNAL-PATH]/");
         if(var4 != -1) {
            String var7 = var1.substring(0, var4) + var1.substring(var4 + "[INTERNAL-PATH]/".length());
            if(var7.contains("[INTERNAL-PATH]/") || var7.contains("[EXTERNAL-PATH]/")) {
               l.e("fixPath: double tag for: " + var1);
            }

            return var7;
         } else {
            int var5 = var1.indexOf("[EXTERNAL-PATH]/");
            if(var5 == -1) {
               return var1;
            } else {
               String var6 = var1.substring(0, var5) + var1.substring(var5 + "[EXTERNAL-PATH]/".length());
               if(var6.contains("[INTERNAL-PATH]/") || var6.contains("[EXTERNAL-PATH]/")) {
                  l.e("fixPath: double tag for: " + var1);
               }

               return var6;
            }
         }
      }
   }

   public String o(String var1) {
      return var1;
   }

   public boolean b(File var1) {
      l.e("deleteFile: " + var1.getAbsolutePath());
      af var2 = ae.b(var1.getAbsolutePath());
      if(var2 != null) {
         l.e("Mapped delete");
         return var2.c(var1.getAbsolutePath());
      } else {
         l.e("Native delete");
         return var1.delete();
      }
   }

   public boolean a(File var1, File var2) {
      l.e("renameFile: " + var1.getAbsolutePath() + " to:" + var2.getAbsolutePath());
      af var3 = ae.b(var1.getAbsolutePath());
      boolean var4;
      if(var3 != null) {
         try {
            var4 = var3.a(var1.getAbsolutePath(), var2.getAbsolutePath());
         } catch (Exception var6) {
            var6.printStackTrace();
            return false;
         }

         ae.c(var2.getAbsolutePath());
         return var4;
      } else {
         var4 = var1.renameTo(var2);
         ae.c(var2.getAbsolutePath());
         return var4;
      }
   }

   public boolean p(String var1) {
      var1 = this.f(var1);
      af var2 = ae.b(var1);
      return var2 != null && var2 instanceof com.corrodinggames.rts.gameFramework.utility.a.a;
   }
}
