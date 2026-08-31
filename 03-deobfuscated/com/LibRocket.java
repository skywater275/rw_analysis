/*
 * Decompiled with CFR 0.152.
 */
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
        if (this.lastDocuments.size() == 0) {
            this.closeActiveDocument();
            return;
        }
        ElementDocument elementDocument = (ElementDocument)this.lastDocuments.remove(this.lastDocuments.size() - 1);
        this.setDocument(elementDocument.documentPath, elementDocument.metadata, false);
    }

    public void clearHistory() {
        this.lastDocuments.clear();
    }

    public ElementDocument setDocument(String string) {
        return this.setDocument(string, null);
    }

    public ElementDocument setDocument(String string, HashMap hashMap) {
        return this.setDocument(string, hashMap, true);
    }

    public void reloadDocument() {
        if (this.currentDocument != null && this.currentDocumentPath != null) {
            boolean bl = false;
            this.setDocument(this.currentDocumentPath, this.getActiveDocumentMetadata(), bl);
        }
    }

    public ElementDocument setDocument(String string, HashMap hashMap, boolean bl) {
        if (this.currentDocument != null) {
            if (bl) {
                this.lastDocuments.add(this.currentDocument);
            }
            this.closeDocument(this.currentDocument);
            this.currentDocument = null;
        }
        this.currentDocumentPath = string;
        ElementDocument elementDocument = new ElementDocument();
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        elementDocument.metadata = hashMap;
        elementDocument.documentPath = string;
        this.currentDocument = elementDocument;
        this.longLastModified = this.getLastModified();
        this.loadDocumentWithContainer(this.getDocumentPath(this.currentDocumentPath), elementDocument);
        this.newDocumentLoaded(elementDocument);
        elementDocument.show();
        this.newDocumentShown(elementDocument);
        return elementDocument;
    }

    public void newDocumentLoaded(ElementDocument elementDocument) {
    }

    public void newDocumentShown(ElementDocument elementDocument) {
    }

    public ElementDocument createPopup(String string, Object object) {
        ElementDocument elementDocument = this.loadDocument(this.getDocumentPath(string));
        if (elementDocument != null) {
            elementDocument.setMetadata("mode", object);
        }
        return elementDocument;
    }

    public void closeDocument(ElementDocument elementDocument) {
        elementDocument.closeDocument();
        this.queueExtraUpdate = true;
    }

    public void closeActiveDocument() {
        if (this.currentDocument != null) {
            this.closeDocument(this.currentDocument);
            this.currentDocument = null;
        }
    }

    public ElementDocument getActiveDocument() {
        return this.currentDocument;
    }

    public HashMap getActiveDocumentMetadata() {
        if (this.currentDocument != null) {
            return this.currentDocument.metadata;
        }
        return null;
    }

    public String getActiveDocumentPath() {
        if (this.currentDocument == null) {
            return "<No Current Document>";
        }
        return this.currentDocument.documentPath;
    }

    public Element getActiveElementById(String string) {
        if (this.currentDocument == null) {
            System.out.println("currentDocument==null on getActiveElementById:" + string);
            return null;
        }
        if (string == null) {
            throw new RuntimeException("id==null");
        }
        return this.currentDocument.getElementById(string);
    }

    public String[] getActiveDocumentCss() {
        if (this.activeDocumentCss == null) {
            ArrayList<String> arrayList = new ArrayList<String>();
            String string = "assets/gui";
            File file = new File(string);
            File[] fileArray = file.listFiles();
            for (int i = 0; i < fileArray.length; ++i) {
                File file2 = fileArray[i];
                if (!file2.getName().toLowerCase(Locale.ENGLISH).endsWith(".rcss")) continue;
                String string2 = string + "/" + file2.getName();
                arrayList.add(string2);
                System.out.println("getActiveDocumentCss: " + string2);
            }
            this.activeDocumentCss = arrayList.toArray(new String[0]);
        }
        return this.activeDocumentCss;
    }

    public long getFileLastModified(String string) {
        File file = new File(string);
        return file.lastModified();
    }

    public long getLastModifiedNewestCss() {
        if (this.currentDocumentPath == null) {
            return -2L;
        }
        long l = -1L;
        for (String string : this.getActiveDocumentCss()) {
            long l2 = this.getFileLastModified(string);
            if (l2 <= l) continue;
            l = l2;
        }
        return l;
    }

    public long getLastModified() {
        if (this.currentDocumentPath == null) {
            return -2L;
        }
        return this.getFileLastModified("assets/gui/" + this.currentDocumentPath);
    }

    public void detectChangesAndReload() {
        ++this.reloadSkip;
        if (this.reloadSkip < 20) {
            return;
        }
        this.reloadSkip = 0;
        if (this.getActiveDocument() != null) {
            boolean bl = false;
            long l = this.getLastModified();
            if (this.longLastModified == -1L) {
                this.longLastModified = l;
            } else if (this.longLastModified != l) {
                System.out.println("reloadDocument: '" + this.currentDocumentPath + "' current now:" + l);
                bl = true;
                this.longLastModified = l;
            }
            long l2 = this.getLastModifiedNewestCss();
            if (this.longLastModifiedNewestCss == -1L) {
                this.longLastModifiedNewestCss = l2;
            } else if (this.longLastModifiedNewestCss != l2) {
                System.out.println("reloadDocument from css: '" + this.currentDocumentPath + "' current now:" + l);
                bl = true;
                this.longLastModifiedNewestCss = l2;
            }
            if (bl) {
                this.reloadDocument();
            }
        }
    }

    public String getDocumentPath(String string) {
        return this.documentPrefix + string;
    }

    private native ElementDocument loadDocument(String var1);

    private native void loadDocumentWithContainer(String var1, ElementDocument var2);

    public void loadFont(String string) {
        this.loadFont(string, null);
    }

    public native void loadFont(String var1, String var2);

    public void postUpdate() {
        if (this.queueExtraUpdate) {
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

    public void setDimensionsWrap(int n, int n2) {
        this.width = n;
        this.height = n2;
        this.setDimensions(n, n2);
    }

    private native void setDimensions(int var1, int var2);

    public void mouseMove(int n, int n2, int n3) {
        this.lastMouseX = n;
        this.lastMouseY = n2;
        this.processMouseMove(n, n2, n3);
    }

    public native void processMouseMove(int var1, int var2, int var3);

    public native void processMouseButtonDown(int var1, int var2);

    public native void processMouseButtonUp(int var1, int var2);

    public native void processMouseWheel(int var1, int var2);

    public native void processTextInput(String var1);

    public native void processTextInputChar(int var1);

    public native void processKeyDown(int var1, int var2);

    public native void processKeyUp(int var1, int var2);

    public void RenderGeometry(float[] fArray, float[] fArray2, int[] nArray, int[] nArray2, int n, float f, float f2) {
        this.RenderGeometryPossiblyCompiled(fArray, fArray2, nArray, nArray2, n, f, f2, null);
    }

    public void RenderGeometryPossiblyCompiled(float[] fArray, float[] fArray2, int[] nArray, int[] nArray2, int n, float f, float f2, LibRocket$CompiledGeometry compiledGeometry) {
        System.out.println("RenderGeometryPossiblyCompiled(" + nArray2.length + ")");
    }

    public int CompileGeometry(float[] fArray, float[] fArray2, int[] nArray, int[] nArray2, int n) {
        int n2;
        LibRocket$CompiledGeometry libRocket$CompiledGeometry = null;
        boolean bl = true;
        if (bl) {
            for (n2 = 1; n2 < this.compiledGeometryList.size(); ++n2) {
                LibRocket$CompiledGeometry libRocket$CompiledGeometry2 = (LibRocket$CompiledGeometry)this.compiledGeometryList.get(n2);
                if (libRocket$CompiledGeometry2 == null) continue;
                boolean bl2 = true;
                if (libRocket$CompiledGeometry2.verticesXY != fArray) {
                    if (!Arrays.equals(libRocket$CompiledGeometry2.verticesXY, fArray)) {
                        bl2 = false;
                    } else {
                        fArray = libRocket$CompiledGeometry2.verticesXY;
                    }
                }
                if (libRocket$CompiledGeometry2.verticesUV != fArray2) {
                    if (!Arrays.equals(libRocket$CompiledGeometry2.verticesUV, fArray2)) {
                        bl2 = false;
                    } else {
                        fArray2 = libRocket$CompiledGeometry2.verticesUV;
                    }
                }
                if (libRocket$CompiledGeometry2.verticesColors != nArray) {
                    if (!Arrays.equals(libRocket$CompiledGeometry2.verticesColors, nArray)) {
                        bl2 = false;
                    } else {
                        nArray = libRocket$CompiledGeometry2.verticesColors;
                    }
                }
                if (libRocket$CompiledGeometry2.indices != nArray2) {
                    if (!Arrays.equals(libRocket$CompiledGeometry2.indices, nArray2)) {
                        bl2 = false;
                    } else {
                        nArray2 = libRocket$CompiledGeometry2.indices;
                    }
                }
                if (!bl2) continue;
            }
        }
        if (libRocket$CompiledGeometry == null) {
            libRocket$CompiledGeometry = new LibRocket$CompiledGeometry();
            libRocket$CompiledGeometry.verticesXY = fArray;
            libRocket$CompiledGeometry.verticesUV = fArray2;
            libRocket$CompiledGeometry.verticesColors = nArray;
            libRocket$CompiledGeometry.indices = nArray2;
            libRocket$CompiledGeometry.textureId = n;
        }
        if (this.compiledGeometryList.size() == 0) {
            this.compiledGeometryList.add(null);
        }
        n2 = 0;
        for (int i = 1; i < this.compiledGeometryList.size(); ++i) {
            if (this.compiledGeometryList.get(i) != null) continue;
            libRocket$CompiledGeometry.id = i;
            this.compiledGeometryList.set(i, libRocket$CompiledGeometry);
            n2 = 1;
            break;
        }
        if (n2 == 0) {
            libRocket$CompiledGeometry.id = this.compiledGeometryList.size();
            this.compiledGeometryList.add(libRocket$CompiledGeometry);
        }
        return libRocket$CompiledGeometry.id;
    }

    public void RenderCompiledGeometry(int n, float f, float f2) {
        LibRocket$CompiledGeometry libRocket$CompiledGeometry = (LibRocket$CompiledGeometry)this.compiledGeometryList.get(n);
        if (libRocket$CompiledGeometry == null) {
            LibRocket.warn("CompileGeometry null for " + n);
            return;
        }
        this.RenderGeometryPossiblyCompiled(libRocket$CompiledGeometry.verticesXY, libRocket$CompiledGeometry.verticesUV, libRocket$CompiledGeometry.verticesColors, libRocket$CompiledGeometry.indices, libRocket$CompiledGeometry.textureId, f, f2, libRocket$CompiledGeometry);
    }

    public void ReleaseCompiledGeometry(int n) {
        this.compiledGeometryList.set(n, null);
    }

    public void ReleaseCompiledGeometryForTexture(int n) {
        for (int i = 1; i < this.compiledGeometryList.size(); ++i) {
            LibRocket$CompiledGeometry libRocket$CompiledGeometry = (LibRocket$CompiledGeometry)this.compiledGeometryList.get(i);
            if (libRocket$CompiledGeometry == null || libRocket$CompiledGeometry.textureId != n) continue;
            LibRocket.log("ReleaseCompiledGeometryForTexture:" + libRocket$CompiledGeometry.textureId);
            this.compiledGeometryList.set(i, null);
        }
    }

    public void EnableScissorRegion(boolean bl) {
    }

    public void SetScissorRegion(int n, int n2, int n3, int n4) {
    }

    public boolean LoadTexture(int n, String string) {
        LibRocket.log("JavaMethod:LoadTexture()");
        return true;
    }

    public boolean GenerateTexture(int n, byte[] byArray) {
        LibRocket.log("JavaMethod:GenerateTexture()");
        return true;
    }

    public void ReleaseTexture(int n) {
        LibRocket.log("JavaMethod:ReleaseTexture");
        this.ReleaseCompiledGeometryForTexture(n);
    }

    public void HandleEvent(String string) {
        LibRocket.log("JavaMethod:HandleEvent:" + string);
    }

    public String TranslateString(String string) {
        return string;
    }

    private void callback(String string) {
        System.out.println("In Java with: " + string);
    }

    public static void test() {
        LibRocket libRocket = new LibRocket();
        libRocket.setup();
        libRocket.loadDocument(libRocket.getDocumentPath("test.rml"));
        libRocket.update();
        libRocket.render();
    }

    public static void main(String[] stringArray) {
        LibRocket.test();
    }

    public LibRocket$TextureHolder getNewTextureHolder() {
        if (this.textureHolderList.size() == 0) {
            this.textureHolderList.add(null);
        }
        LibRocket$TextureHolder libRocket$TextureHolder = this.getFromTextureHolderFactory();
        boolean bl = false;
        for (int i = 1; i < this.textureHolderList.size(); ++i) {
            if (this.textureHolderList.get(i) != null) continue;
            libRocket$TextureHolder.index = i;
            this.textureHolderList.set(i, libRocket$TextureHolder);
            System.out.println("getNewTextureHolder: set:" + libRocket$TextureHolder.index);
            bl = true;
            break;
        }
        if (!bl) {
            libRocket$TextureHolder.index = this.textureHolderList.size();
            this.textureHolderList.add(libRocket$TextureHolder);
            System.out.println("getNewTextureHolder: append:" + libRocket$TextureHolder.index);
        }
        return libRocket$TextureHolder;
    }

    public LibRocket$TextureHolder findTextureHolder(int n) {
        return (LibRocket$TextureHolder)this.textureHolderList.get(n);
    }

    public boolean removeTextureHolder(int n) {
        if (this.textureHolderList.size() <= n) {
            LibRocket.warn("removeTextureHolder: cannot remove:" + n + " it is out of range");
            return false;
        }
        LibRocket$TextureHolder libRocket$TextureHolder = this.findTextureHolder(n);
        if (libRocket$TextureHolder != null) {
            libRocket$TextureHolder.remove();
        }
        this.textureHolderList.set(n, null);
        return true;
    }

    public LibRocket$TextureHolder getFromTextureHolderFactory() {
        return new LibRocket$TextureHolder(this);
    }

    public static void log(String string) {
        System.out.println(string);
    }

    public static void warn(String string) {
        System.out.println(string);
    }

    static {
        String string;
        boolean bl = false;
        String string2 = System.getProperty("os.name");
        if (string2.startsWith("Windows")) {
            bl = true;
        }
        boolean bl2 = (string = System.getProperty("os.arch")).contains("64") || string.startsWith("armv8");
        String string3 = "rocketConnector";
        if (bl && bl2) {
            string3 = "rocketConnector64";
        }
        System.loadLibrary(string3);
    }
}
