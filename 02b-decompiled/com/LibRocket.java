package com;

import com.Element;
import com.ElementDocument;
import com.LibRocket$CompiledGeometry;
import com.LibRocket$TextureHolder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class LibRocket {

   public boolean debug = false;
   String currentDocumentPath;
   ElementDocument currentDocument;
   ArrayList lastDocuments = new ArrayList();
   long longLastModified = -1L;
   long longLastModifiedNewestCss = -1L;
   String[] activeDocumentCss;
   int reloadSkip;
   public String documentPrefix = "assets/gui/";
   public boolean queueExtraUpdate = false;
   public int width = 1000;
   public int height = 1000;
   public int lastMouseX = 0;
   public int lastMouseY = 0;
   ArrayList compiledGeometryList = new ArrayList();
   ArrayList textureHolderList = new ArrayList();


   public native void setup();

   public void backToLastDocument() {
      if(this.lastDocuments.size() == 0) {
         this.closeActiveDocument();
      } else {
         ElementDocument var1 = (ElementDocument)this.lastDocuments.remove(this.lastDocuments.size() - 1);
         this.setDocument(var1.documentPath, var1.metadata, false);
      }
   }

   public void clearHistory() {
      this.lastDocuments.clear();
   }

   public ElementDocument setDocument(String var1) {
      return this.setDocument(var1, (HashMap)null);
   }

   public ElementDocument setDocument(String var1, HashMap var2) {
      return this.setDocument(var1, var2, true);
   }

   public void reloadDocument() {
      if(this.currentDocument != null && this.currentDocumentPath != null) {
         boolean var1 = false;
         this.setDocument(this.currentDocumentPath, this.getActiveDocumentMetadata(), var1);
      }

   }

   public ElementDocument setDocument(String var1, HashMap var2, boolean var3) {
      if(this.currentDocument != null) {
         if(var3) {
            this.lastDocuments.add(this.currentDocument);
         }

         this.closeDocument(this.currentDocument);
         this.currentDocument = null;
      }

      this.currentDocumentPath = var1;
      ElementDocument var4 = new ElementDocument();
      if(var2 == null) {
         var2 = new HashMap();
      }

      var4.metadata = var2;
      var4.documentPath = var1;
      this.currentDocument = var4;
      this.longLastModified = this.getLastModified();
      this.loadDocumentWithContainer(this.getDocumentPath(this.currentDocumentPath), var4);
      this.newDocumentLoaded(var4);
      var4.show();
      this.newDocumentShown(var4);
      return var4;
   }

   public void newDocumentLoaded(ElementDocument var1) {}

   public void newDocumentShown(ElementDocument var1) {}

   public ElementDocument createPopup(String var1, Object var2) {
      ElementDocument var3 = this.loadDocument(this.getDocumentPath(var1));
      if(var3 != null) {
         var3.setMetadata("mode", var2);
      }

      return var3;
   }

   public void closeDocument(ElementDocument var1) {
      var1.closeDocument();
      this.queueExtraUpdate = true;
   }

   public void closeActiveDocument() {
      if(this.currentDocument != null) {
         this.closeDocument(this.currentDocument);
         this.currentDocument = null;
      }

   }

   public ElementDocument getActiveDocument() {
      return this.currentDocument;
   }

   public HashMap getActiveDocumentMetadata() {
      return this.currentDocument != null?this.currentDocument.metadata:null;
   }

   public String getActiveDocumentPath() {
      return this.currentDocument == null?"<No Current Document>":this.currentDocument.documentPath;
   }

   public Element getActiveElementById(String var1) {
      if(this.currentDocument == null) {
         System.out.println("currentDocument==null on getActiveElementById:" + var1);
         return null;
      } else if(var1 == null) {
         throw new RuntimeException("id==null");
      } else {
         return this.currentDocument.getElementById(var1);
      }
   }

   public String[] getActiveDocumentCss() {
      if(this.activeDocumentCss == null) {
         ArrayList var1 = new ArrayList();
         String var2 = "assets/gui";
         File var3 = new File(var2);
         File[] var4 = var3.listFiles();

         for(int var5 = 0; var5 < var4.length; ++var5) {
            File var6 = var4[var5];
            if(var6.getName().toLowerCase(Locale.ENGLISH).endsWith(".rcss")) {
               String var7 = var2 + "/" + var6.getName();
               var1.add(var7);
               System.out.println("getActiveDocumentCss: " + var7);
            }
         }

         this.activeDocumentCss = (String[])var1.toArray(new String[0]);
      }

      return this.activeDocumentCss;
   }

   public long getFileLastModified(String var1) {
      File var2 = new File(var1);
      return var2.lastModified();
   }

   public long getLastModifiedNewestCss() {
      if(this.currentDocumentPath == null) {
         return -2L;
      } else {
         long var1 = -1L;
         String[] var3 = this.getActiveDocumentCss();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            long var7 = this.getFileLastModified(var6);
            if(var7 > var1) {
               var1 = var7;
            }
         }

         return var1;
      }
   }

   public long getLastModified() {
      return this.currentDocumentPath == null?-2L:this.getFileLastModified("assets/gui/" + this.currentDocumentPath);
   }

   public void detectChangesAndReload() {
      ++this.reloadSkip;
      if(this.reloadSkip >= 20) {
         this.reloadSkip = 0;
         if(this.getActiveDocument() != null) {
            boolean var1 = false;
            long var2 = this.getLastModified();
            if(this.longLastModified == -1L) {
               this.longLastModified = var2;
            } else if(this.longLastModified != var2) {
               System.out.println("reloadDocument: \'" + this.currentDocumentPath + "\' current now:" + var2);
               var1 = true;
               this.longLastModified = var2;
            }

            long var4 = this.getLastModifiedNewestCss();
            if(this.longLastModifiedNewestCss == -1L) {
               this.longLastModifiedNewestCss = var4;
            } else if(this.longLastModifiedNewestCss != var4) {
               System.out.println("reloadDocument from css: \'" + this.currentDocumentPath + "\' current now:" + var2);
               var1 = true;
               this.longLastModifiedNewestCss = var4;
            }

            if(var1) {
               this.reloadDocument();
            }
         }

      }
   }

   public String getDocumentPath(String var1) {
      return this.documentPrefix + var1;
   }

   private native ElementDocument loadDocument(String var1);

   private native void loadDocumentWithContainer(String var1, ElementDocument var2);

   public void loadFont(String var1) {
      this.loadFont(var1, (String)null);
   }

   public native void loadFont(String var1, String var2);

   public void postUpdate() {
      if(this.queueExtraUpdate) {
         this.queueExtraUpdate = false;
         this.update();
         this.render();
         this.processMouseMove(-1, -1, 0);
         this.processMouseMove(this.lastMouseX, this.lastMouseY, 0);
      }

   }

   public native void update();

   public native void render();

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setDimensionsWrap(int var1, int var2) {
      this.width = var1;
      this.height = var2;
      this.setDimensions(var1, var2);
   }

   private native void setDimensions(int var1, int var2);

   public void mouseMove(int var1, int var2, int var3) {
      this.lastMouseX = var1;
      this.lastMouseY = var2;
      this.processMouseMove(var1, var2, var3);
   }

   public native void processMouseMove(int var1, int var2, int var3);

   public native void processMouseButtonDown(int var1, int var2);

   public native void processMouseButtonUp(int var1, int var2);

   public native void processMouseWheel(int var1, int var2);

   public native void processTextInput(String var1);

   public native void processTextInputChar(int var1);

   public native void processKeyDown(int var1, int var2);

   public native void processKeyUp(int var1, int var2);

   public void RenderGeometry(float[] var1, float[] var2, int[] var3, int[] var4, int var5, float var6, float var7) {
      this.RenderGeometryPossiblyCompiled(var1, var2, var3, var4, var5, var6, var7, (LibRocket$CompiledGeometry)null);
   }

   public void RenderGeometryPossiblyCompiled(float[] var1, float[] var2, int[] var3, int[] var4, int var5, float var6, float var7, LibRocket$CompiledGeometry var8) {
      System.out.println("RenderGeometryPossiblyCompiled(" + var4.length + ")");
   }

   public int CompileGeometry(float[] var1, float[] var2, int[] var3, int[] var4, int var5) {
      LibRocket$CompiledGeometry var6 = null;
      boolean var7 = true;
      if(var7) {
         for(int var8 = 1; var8 < this.compiledGeometryList.size(); ++var8) {
            LibRocket$CompiledGeometry var9 = (LibRocket$CompiledGeometry)this.compiledGeometryList.get(var8);
            if(var9 != null) {
               boolean var10 = true;
               if(var9.verticesXY != var1) {
                  if(!Arrays.equals(var9.verticesXY, var1)) {
                     var10 = false;
                  } else {
                     var1 = var9.verticesXY;
                  }
               }

               if(var9.verticesUV != var2) {
                  if(!Arrays.equals(var9.verticesUV, var2)) {
                     var10 = false;
                  } else {
                     var2 = var9.verticesUV;
                  }
               }

               if(var9.verticesColors != var3) {
                  if(!Arrays.equals(var9.verticesColors, var3)) {
                     var10 = false;
                  } else {
                     var3 = var9.verticesColors;
                  }
               }

               if(var9.indices != var4) {
                  if(!Arrays.equals(var9.indices, var4)) {
                     var10 = false;
                  } else {
                     var4 = var9.indices;
                  }
               }

               if(var10) {
                  ;
               }
            }
         }
      }

      if(var6 == null) {
         var6 = new LibRocket$CompiledGeometry();
         var6.verticesXY = var1;
         var6.verticesUV = var2;
         var6.verticesColors = var3;
         var6.indices = var4;
         var6.textureId = var5;
      }

      if(this.compiledGeometryList.size() == 0) {
         this.compiledGeometryList.add((Object)null);
      }

      boolean var11 = false;

      for(int var12 = 1; var12 < this.compiledGeometryList.size(); ++var12) {
         if(this.compiledGeometryList.get(var12) == null) {
            var6.id = var12;
            this.compiledGeometryList.set(var12, var6);
            var11 = true;
            break;
         }
      }

      if(!var11) {
         var6.id = this.compiledGeometryList.size();
         this.compiledGeometryList.add(var6);
      }

      return var6.id;
   }

   public void RenderCompiledGeometry(int var1, float var2, float var3) {
      LibRocket$CompiledGeometry var4 = (LibRocket$CompiledGeometry)this.compiledGeometryList.get(var1);
      if(var4 == null) {
         warn("CompileGeometry null for " + var1);
      } else {
         this.RenderGeometryPossiblyCompiled(var4.verticesXY, var4.verticesUV, var4.verticesColors, var4.indices, var4.textureId, var2, var3, var4);
      }
   }

   public void ReleaseCompiledGeometry(int var1) {
      this.compiledGeometryList.set(var1, (Object)null);
   }

   public void ReleaseCompiledGeometryForTexture(int var1) {
      for(int var2 = 1; var2 < this.compiledGeometryList.size(); ++var2) {
         LibRocket$CompiledGeometry var3 = (LibRocket$CompiledGeometry)this.compiledGeometryList.get(var2);
         if(var3 != null && var3.textureId == var1) {
            log("ReleaseCompiledGeometryForTexture:" + var3.textureId);
            this.compiledGeometryList.set(var2, (Object)null);
         }
      }

   }

   public void EnableScissorRegion(boolean var1) {}

   public void SetScissorRegion(int var1, int var2, int var3, int var4) {}

   public boolean LoadTexture(int var1, String var2) {
      log("JavaMethod:LoadTexture()");
      return true;
   }

   public boolean GenerateTexture(int var1, byte[] var2) {
      log("JavaMethod:GenerateTexture()");
      return true;
   }

   public void ReleaseTexture(int var1) {
      log("JavaMethod:ReleaseTexture");
      this.ReleaseCompiledGeometryForTexture(var1);
   }

   public void HandleEvent(String var1) {
      log("JavaMethod:HandleEvent:" + var1);
   }

   public String TranslateString(String var1) {
      return var1;
   }

   private void callback(String var1) {
      System.out.println("In Java with: " + var1);
   }

   public static void test() {
      LibRocket var0 = new LibRocket();
      var0.setup();
      var0.loadDocument(var0.getDocumentPath("test.rml"));
      var0.update();
      var0.render();
   }

   public static void main(String[] var0) {
      test();
   }

   public LibRocket$TextureHolder getNewTextureHolder() {
      if(this.textureHolderList.size() == 0) {
         this.textureHolderList.add((Object)null);
      }

      LibRocket$TextureHolder var1 = this.getFromTextureHolderFactory();
      boolean var2 = false;

      for(int var3 = 1; var3 < this.textureHolderList.size(); ++var3) {
         if(this.textureHolderList.get(var3) == null) {
            var1.index = var3;
            this.textureHolderList.set(var3, var1);
            System.out.println("getNewTextureHolder: set:" + var1.index);
            var2 = true;
            break;
         }
      }

      if(!var2) {
         var1.index = this.textureHolderList.size();
         this.textureHolderList.add(var1);
         System.out.println("getNewTextureHolder: append:" + var1.index);
      }

      return var1;
   }

   public LibRocket$TextureHolder findTextureHolder(int var1) {
      return (LibRocket$TextureHolder)this.textureHolderList.get(var1);
   }

   public boolean removeTextureHolder(int var1) {
      if(this.textureHolderList.size() <= var1) {
         warn("removeTextureHolder: cannot remove:" + var1 + " it is out of range");
         return false;
      } else {
         LibRocket$TextureHolder var2 = this.findTextureHolder(var1);
         if(var2 != null) {
            var2.remove();
         }

         this.textureHolderList.set(var1, (Object)null);
         return true;
      }
   }

   public LibRocket$TextureHolder getFromTextureHolderFactory() {
      return new LibRocket$TextureHolder(this);
   }

   public static void log(String var0) {
      System.out.println(var0);
   }

   public static void warn(String var0) {
      System.out.println(var0);
   }

   static {
      boolean var0 = false;
      String var1 = System.getProperty("os.name");
      if(var1.startsWith("Windows")) {
         var0 = true;
      }

      String var2 = System.getProperty("os.arch");
      boolean var3 = var2.contains("64") || var2.startsWith("armv8");
      String var4 = "rocketConnector";
      if(var0 && var3) {
         var4 = "rocketConnector64";
      }

      System.loadLibrary(var4);
   }
}
