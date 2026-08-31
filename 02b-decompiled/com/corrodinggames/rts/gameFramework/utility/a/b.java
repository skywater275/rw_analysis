package com.corrodinggames.rts.gameFramework.utility.a;

import android.content.Context;
import android.net.Uri;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.j;
import com.corrodinggames.rts.gameFramework.utility.a.a;
import com.corrodinggames.rts.gameFramework.utility.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

class b {

   Uri a;
   Uri b;
   String c;
   boolean d;
   c e;
   boolean f = false;
   int g = 1;


   public b(Uri var1, boolean var2) {
      this.a = var1;
      this.b = a.a.buildDocumentUriUsingTree(var1);
      this.c = a.a.getReadablePath(this.c(), var1);
      this.d = var2;
      a.h("== new SafLink write:" + var2 + " ==");
      a.h("root:" + this.a);
      a.h("rootDocument:" + this.b);
      a.h("shownUrl:" + this.c);
      this.e = new c(this, "", this.b, true);
   }

   public void a() {
      this.f = true;
      ++this.g;
   }

   public void b() {
      a.h("== testRoot ==");
      a.a.listWithDetails(this.c(), this.b);
   }

   public Context c() {
      return com.corrodinggames.rts.appFramework.c.a();
   }

   public boolean a(String var1) {
      boolean var2 = false;
      if("mod-info.txt".equals(var1)) {
         var2 = true;
      }

      if(var2) {
         return a.a.exists(this.c(), this.f(var1));
      } else {
         c var3 = this.k(var1);
         return var3 != null;
      }
   }

   public j b(String var1) {
      boolean var3 = false;
      if("mod-info.txt".equals(var1)) {
         var3 = true;
      }

      Uri var2;
      if(!var3) {
         c var4 = this.k(var1);
         if(var4 == null) {
            return null;
         }

         var2 = var4.b;
      } else {
         var2 = this.f(var1);
      }

      if(var2 == null) {
         return null;
      } else {
         InputStream var8;
         try {
            var8 = a.a.read(this.c(), var2);
         } catch (FileNotFoundException var6) {
            a.j("openAssetSteam: " + var6.getMessage() + " (file: " + var1 + ")");
            return null;
         } catch (IllegalArgumentException var7) {
            a.j("openAssetSteam: " + var7.getMessage() + " (file: " + var1 + ")");
            return null;
         }

         if(var8 == null) {
            return null;
         } else {
            j var5 = new j(var8, this.a + "/" + var1);
            return var5;
         }
      }
   }

   public long c(String var1) {
      Uri var2 = this.f(var1);
      if(var2 == null) {
         a.h("getLastModified file missing: " + var1);
         return 0L;
      } else {
         long var3 = a.a.getLastModified(this.c(), var2);
         return var3;
      }
   }

   public long d(String var1) {
      Uri var2 = this.f(var1);
      if(var2 == null) {
         a.h("getEntrySize file missing: " + var1);
         return -1L;
      } else {
         long var3 = a.a.getFileSize(this.c(), var2);
         return var3;
      }
   }

   public OutputStream a(String var1, boolean var2) {
      a.i("writableOutputSteam:" + var1);
      Uri var3 = this.f(var1);
      String var5;
      if(var3 == null) {
         File var4 = new File(var1);
         var5 = var4.getName();
         Uri var6 = this.i(var1);
         a.i("writableOutputSteam creating: " + var5 + " in " + var6);
         if(var6 == null) {
            a.j("writableOutputSteam: Parent folder not found for: " + var1);
            return null;
         }

         try {
            var3 = a.a.createFile(this.c(), var6, "", var5);
            a.i("newFileUri: " + var3);
         } catch (FileNotFoundException var8) {
            var8.printStackTrace();
            return null;
         }
      }

      OutputStream var10;
      try {
         var5 = "w";
         if(var2) {
            var5 = "wa";
         }

         var10 = a.a.write(this.c(), var3, var5);
      } catch (FileNotFoundException var9) {
         var9.printStackTrace();
         return null;
      }

      this.a();
      return var10;
   }

   public boolean e(String var1) {
      if(!this.d) {
         a.j("deleteFile: Not open as writable");
         return false;
      } else {
         Uri var2 = this.f(var1);
         if(var2 == null) {
            a.j("deleteFile: fileUri==null for:" + var1);
            return false;
         } else if(a.a.isDirectory(this.c(), var2)) {
            throw new RuntimeException("Attempted to delete folder at: " + var1 + " url:" + var2);
         } else {
            boolean var3;
            try {
               var3 = a.a.deleteFile(this.c(), var2);
            } catch (IOException var5) {
               var5.printStackTrace();
               return false;
            } catch (IllegalArgumentException var6) {
               var6.printStackTrace();
               return false;
            }

            this.a();
            return var3;
         }
      }
   }

   public boolean a(String var1, String var2) {
      if(!this.d) {
         a.j("renameFile: Not open as writable");
         return false;
      } else {
         Uri var3 = this.f(var1);
         if(var3 == null) {
            a.j("renameFile: fileUri==null for:" + var1);
            return false;
         } else {
            String var4 = f.k(var2);
            a.i("Rename: " + var3 + " to " + var4);

            Uri var5;
            try {
               var5 = a.a.renameFile(this.c(), var3, var4);
            } catch (IOException var7) {
               var7.printStackTrace();
               return false;
            }

            this.a();
            return var5 != null;
         }
      }
   }

   public Uri f(String var1) {
      c var2 = this.k(var1);
      return var2 == null?null:var2.b;
   }

   public String[] g(String var1) {
      c var2 = this.k(var1);
      if(var2 == null) {
         return null;
      } else if(!var2.c) {
         return null;
      } else {
         HashMap var3 = var2.a();
         ArrayList var4 = new ArrayList();
         Iterator var5 = var3.keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            var4.add(var6);
         }

         return (String[])var4.toArray(new String[0]);
      }
   }

   public boolean h(String var1) {
      if(!var1.equals("/") && !var1.equals("")) {
         c var2 = this.k(var1);
         return var2 == null?false:var2.c;
      } else {
         return true;
      }
   }

   public Uri i(String var1) {
      File var2 = new File(var1);
      String var3 = var2.getParent();
      if(var3 == null) {
         var3 = "";
      }

      Uri var4 = this.f(var3);
      if(var4 == null) {
         a.j("createDirectory: Parent folder: " + var3 + " not found");
      }

      return var4;
   }

   public boolean j(String var1) {
      File var2 = new File(var1);
      String var3 = var2.getName();
      Uri var4 = this.i(var1);
      if(var4 == null) {
         return false;
      } else {
         Uri var5 = a.a.createDirectory(this.c(), var4, var3);
         this.a();
         return var5 != null;
      }
   }

   private c k(String var1) {
      return this.l(var1);
   }

   private c l(String var1) {
      String[] var2 = var1.split("\\\\|\\/");
      c var3 = this.e;
      String[] var4 = var2;
      int var5 = var2.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String var7 = var4[var6];
         if(!var7.trim().equals("")) {
            HashMap var8;
            try {
               var8 = var3.a();
            } catch (IOException var11) {
               var11.printStackTrace();
               return null;
            }

            c var9 = (c)var8.get(var7);
            if(var9 != null) {
               var3 = var9;
            } else {
               String var10 = var7.toLowerCase(Locale.ROOT);
               var9 = (c)var3.e.get(var10);
               if(var9 == null) {
                  a.i("child null for: " + var1);
                  a.i("element: " + var7);
                  return null;
               }

               var3 = var9;
            }
         }
      }

      return var3;
   }
}
