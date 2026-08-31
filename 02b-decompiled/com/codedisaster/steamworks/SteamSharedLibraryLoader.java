package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class SteamSharedLibraryLoader {

   private final String libraryPath;
   static boolean alreadyLoaded = false;
   static File librarySystemPath;
   private static final String extractSubFolder = "steamworks4j/";


   private SteamSharedLibraryLoader(String var1) {
      this.libraryPath = var1;
   }

   private String getLibNameWindows(String var1, boolean var2) {
      return var1 + (var2?"64":"") + ".dll";
   }

   private String getLibNameLinux(String var1, boolean var2) {
      return "lib" + var1 + (var2?"64":"") + ".so";
   }

   private String getLibNameMac(String var1) {
      return "lib" + var1 + ".dylib";
   }

   private void loadLibraries(String ... var1) {
      String var2 = System.getProperty("os.name");
      String var3 = System.getProperty("os.arch");
      boolean var4 = var2.contains("Windows");
      boolean var5 = var2.contains("Linux");
      boolean var6 = var2.contains("Mac");
      boolean var7 = var3.equals("amd64") || var3.equals("x86_64");
      String[] var8 = new String[var1.length];

      for(int var9 = 0; var9 < var1.length; ++var9) {
         if(var4) {
            var8[var9] = this.getLibNameWindows(var1[var9], var7);
         } else if(var5) {
            var8[var9] = this.getLibNameLinux(var1[var9], var7);
         } else {
            if(!var6) {
               throw new IOException("Unrecognized system architecture: " + var2 + ", " + var3);
            }

            var8[var9] = this.getLibNameMac(var1[var9]);
         }
      }

      String var14;
      if(this.libraryPath == null) {
         String var15 = ".nohash";
         CRC32 var10 = new CRC32();
         String[] var11 = var8;
         int var12 = var8.length;

         for(int var13 = 0; var13 < var12; ++var13) {
            var14 = var11[var13];
            var15 = this.crc(var10, this.getClass().getResourceAsStream("/" + var14));
         }

         librarySystemPath = discoverExtractLocation("steamworks4j/" + var15, UUID.randomUUID().toString());
         System.out.println("steam librarySystemPath name:" + librarySystemPath);
         if(librarySystemPath == null) {
            throw new IOException("Failed to create temp folder to extract native libraries");
         }

         librarySystemPath = librarySystemPath.getParentFile();
      } else {
         librarySystemPath = new File(this.libraryPath);
      }

      String[] var16 = var8;
      int var17 = var8.length;

      for(int var18 = 0; var18 < var17; ++var18) {
         String var19 = var16[var18];
         String var20;
         if(this.libraryPath == null) {
            var20 = this.extractLibrary(librarySystemPath, var19);
         } else {
            var20 = librarySystemPath + "/" + var19;
         }

         var14 = (new File(var20)).getCanonicalPath();
         System.load(var14);
      }

   }

   private String extractLibrary(File var1, String var2) {
      File var3 = new File(var1, var2);
      ZipFile var5 = null;
      InputStream var4;
      if(this.libraryPath != null) {
         System.out.println("steam extractLibrary zip:" + var2);
         var5 = new ZipFile(this.libraryPath);
         ZipEntry var6 = var5.getEntry(var2);
         var4 = var5.getInputStream(var6);
      } else {
         System.out.println("steam extractLibrary name:" + var2);
         var4 = SteamSharedLibraryLoader.class.getResourceAsStream("/" + var2);
      }

      if(var4 == null) {
         throw new IOException("Error extracting " + var2 + " from " + (this.libraryPath != null?this.libraryPath:"resources"));
      } else {
         try {
            FileOutputStream var10 = new FileOutputStream(var3);
            byte[] var7 = new byte[4096];

            while(true) {
               int var8 = var4.read(var7);
               if(var8 == -1) {
                  var10.close();
                  break;
               }

               var10.write(var7, 0, var8);
            }
         } catch (IOException var9) {
            if(!var3.exists()) {
               throw var9;
            }
         }

         var4.close();
         if(var5 != null) {
            var5.close();
         }

         return var3.getAbsolutePath();
      }
   }

   private String crc(CRC32 var1, InputStream var2) {
      byte[] var3 = new byte[4096];

      try {
         while(true) {
            int var4 = var2.read(var3);
            if(var4 == -1) {
               break;
            }

            var1.update(var3, 0, var4);
         }
      } catch (IOException var13) {
         var13.printStackTrace();
      } finally {
         try {
            var2.close();
         } catch (IOException var12) {
            ;
         }

      }

      return Long.toHexString(var1.getValue());
   }

   static boolean loadLibraries(String var0) {
      if(alreadyLoaded) {
         return true;
      } else {
         SteamSharedLibraryLoader var1 = new SteamSharedLibraryLoader(var0);

         try {
            var1.loadLibraries(new String[]{"steam_api", "steamworks4j"});
         } catch (Throwable var3) {
            throw new SteamException(var3);
         }

         alreadyLoaded = true;
         return true;
      }
   }

   private static File discoverExtractLocation(String var0, String var1) {
      File var2 = new File(System.getProperty("java.io.tmpdir") + "/" + var0, var1);
      if(canWrite(var2)) {
         return var2;
      } else {
         try {
            File var3 = File.createTempFile(var0, (String)null);
            if(var3.delete()) {
               var2 = new File(var3, var1);
               if(canWrite(var2)) {
                  return var2;
               }
            }
         } catch (IOException var4) {
            ;
         }

         var2 = new File(System.getProperty("user.home") + "/." + var0, var1);
         if(canWrite(var2)) {
            return var2;
         } else {
            var2 = new File(".tmp/" + var0, var1);
            return canWrite(var2)?var2:null;
         }
      }
   }

   private static boolean canWrite(File var0) {
      File var1 = var0.getParentFile();
      if(var0.exists()) {
         if(!var0.canWrite() || !canExecute(var0)) {
            return false;
         }
      } else {
         if(!var1.exists() && !var1.mkdirs()) {
            return false;
         }

         if(!var1.isDirectory()) {
            return false;
         }
      }

      File var2 = new File(var1, UUID.randomUUID().toString());

      boolean var4;
      try {
         (new FileOutputStream(var2)).close();
         boolean var3 = canExecute(var2);
         return var3;
      } catch (IOException var8) {
         var4 = false;
      } finally {
         var2.delete();
      }

      return var4;
   }

   private static boolean canExecute(File var0) {
      try {
         if(var0.canExecute()) {
            return true;
         }

         if(var0.setExecutable(true)) {
            return var0.canExecute();
         }
      } catch (Exception var2) {
         ;
      }

      return false;
   }

}
