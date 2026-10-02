/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;
import com.corrodinggames.rts.game.units.HoverUnit;
import com.corrodinggames.rts.game.GameFlag;
import com.corrodinggames.rts.game.GameFlagImpl;
import com.corrodinggames.rts.game.NeutralPlayer;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.units.WaterUnit;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.gameFramework.GameTimer;

import android.graphics.Rect;
import android.graphics.RectF;
import com.Element;
import com.ElementDocument;
import com.LibRocket;
import com.corrodinggames.librocket.ElementWrapper;
import com.corrodinggames.librocket.DocumentWrapper;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.PerformanceTimer;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.FileWatcher;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LibRocketBridge
extends LibRocket {
    public static PerformanceTimer a = new PerformanceTimer("LoadResources");
    public static String b = "";
    public ScriptEngine c;
    protected int d = 0;
    public boolean e;
    protected Rect f = new Rect();
    protected RectF g = new RectF();
    protected boolean h = false;
    private DocumentWrapper j;
    private DocumentWrapper k;
    Pattern i = Pattern.compile("\\$\\{([^\\}]*?)\\}");

    public LibRocketBridge() {
        this.c = ScriptEngine.createScriptEngine(this);
    }

    public void a() {
        this.d = 0;
    }

    public static String a(String string) {
        GlobalState.e("convertTexturePath for: " + string);
        string = com.corrodinggames.rts.gameFramework.GameUtils.unescapeHtml(string);
        if (string.startsWith("base:")) {
            return b + string.substring("base:".length());
        }
        if (string.startsWith("drawable:")) {
            return b + "res/drawable/" + string.substring("drawable:".length());
        }
        if (string.startsWith("assets:")) {
            String string2 = string.substring("assets:".length());
            String string3 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.e(string2);
            boolean bl = true;
            if (GlobalState.aZ && string3 != null && string3.startsWith(b)) {
                bl = false;
            }
            if (GlobalState.aZ && string3 != null && string3.startsWith("/private")) {
                bl = false;
            }
            GlobalState.e("convertTexturePath  (basePath:" + bl + "):" + string2 + " > " + string3);
            if (bl) {
                return b + string3;
            }
            return string3;
        }
        if (string.startsWith(b + "assets/gui/")) {
            GlobalState.e("convertTexturePath already had path:" + string);
            return string;
        }
        return b + "assets/gui/" + string;
    }

    public Matcher a(String string, String string2) {
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(string2);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    @Override
    public void ReleaseTexture(int n2) {
        this.removeTextureHolder(n2);
    }

    @Override
    public boolean LoadTexture(int n2, String string) {
        String string2;
        Matcher matcher;
        a.a();  // 02b: 静态字段 a (PerformanceTimer "LoadResources").a()
        ElementWrapper c2 = (ElementWrapper) this.findTextureHolder(n2);
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        UnitTypeHandle as2 = null;  // 02b: as var7 = null (units/as=UnitTypeHandle)
        if (string.startsWith("lazy:")) {
            string = string.substring("lazy:".length());
            bl = true;
        }
        if (string.startsWith("nocolor:")) {
            string = string.substring("nocolor:".length());
            bl2 = true;
        }
        if (string.startsWith("unit:")) {
            string = string.substring("unit:".length());
            as2 = UnitRegistry.a(string);  // 02b: ar.a(String) L203
            bl = true;
        }
        if (string.startsWith("thumbnail:")) {
            string = string.substring("thumbnail:".length());
            bl3 = true;
        }
        if ((matcher = this.a("^(alpha\\((.*)\\):).*", string)) != null) {
            string2 = matcher.group(1);
            String string3 = matcher.group(2);
            GlobalState.e("alpha=" + string3);
            c2.e = Float.parseFloat(string3);
            string = string.substring(string2.length());
        }
        string2 = com.corrodinggames.librocket.b.a(string);
        c2.b = bl;
        c2.c = bl3;
        c2.d = bl2;
        c2.f = as2;
        c2.a = string2;
        if (!bl && !c2.isEnabled()) {  // 02b: c.a()
            a.b();  // 02b: a.b() (PerformanceTimer)
            return false;
        }
        a.b();  // 02b: a.b() (PerformanceTimer)
        return true;
    }

    @Override
    public abstract void EnableScissorRegion(boolean var1);

    @Override
    public void SetScissorRegion(int n2, int n3, int n4, int n5) {
        this.f.a(n2, n3, n2 + n4, n3 + n5);
        this.g.a(n2, n3, n2 + n4, n3 + n5);
        this.EnableScissorRegion(true);
    }

    public boolean b() {
        if (this.getActiveDocument() != null) {
            return false;
        }
        if (this.k != null) {
            return false;
        }
        return this.j == null;
    }

    @Override
    public void HandleEvent(String string) {
        this.e = true;
        try {
            this.c.processScript(string);
        }
        finally {
            this.e = false;
        }
    }

    public Object b(String string) {
        HashMap hashMap = this.getActiveDocumentMetadata();
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(string);
    }

    @Override
    public void newDocumentLoaded(ElementDocument elementDocument) {
        this.c.getRootNoCheck().convertTextOnPage();
    }

    @Override
    public void newDocumentShown(ElementDocument elementDocument) {
        if (this.k != null) {
            this.k.a.pullToFront();
        }
        if (this.j != null) {
            this.j.a.pullToFront();
        }
    }

    public ElementDocument c() {
        if (this.k != null) {
            return this.k.a;
        }
        return null;
    }

    public ElementDocument d() {
        if (this.j != null) {
            return this.j.a;
        }
        return null;
    }

    public ElementDocument e() {
        ElementDocument elementDocument = this.d();
        if (elementDocument != null) {
            return elementDocument;
        }
        return this.c();
    }

    public ElementDocument f() {
        ElementDocument elementDocument = this.c();
        if (elementDocument != null) {
            return elementDocument;
        }
        elementDocument = this.getActiveDocument();
        return elementDocument;
    }

    public ElementDocument g() {
        ElementDocument elementDocument = this.d();
        if (elementDocument != null) {
            return elementDocument;
        }
        elementDocument = this.c();
        if (elementDocument != null) {
            return elementDocument;
        }
        elementDocument = this.getActiveDocument();
        return elementDocument;
    }

    public void c(String string) {
        DocumentWrapper d2 = new DocumentWrapper();
        d2.b = null;
        d2.c = string;
        this.a(d2);
    }

    public void b(String string, String string2) {
        DocumentWrapper d2 = new DocumentWrapper();
        d2.b = string;
        d2.c = string2;
        this.a(d2);
    }

    public void a(String string, String string2, String string3, String string4, String string5, boolean bl) {
        DocumentWrapper d2 = new DocumentWrapper();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = string4;
        d2.f = string5;
        d2.h = bl;
        this.a(d2);
    }

    public void a(String string, String string2, String string3, e e2, e e3, boolean bl) {  // 02b b.java L242: a(String,String,String,e,e,boolean); HumanPlayer 错标修正
        DocumentWrapper d2 = new DocumentWrapper();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = e2;
        d2.f = e3;
        d2.h = bl;
        this.a(d2);
    }

    public ElementDocument a(String string, String string2, String string3, Object object, Object object2, boolean bl, boolean bl2) {
        DocumentWrapper d2 = new DocumentWrapper();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = object;
        d2.f = object2;
        d2.g = bl;
        d2.h = bl2;
        return this.a(d2);
    }

    public ElementDocument a(DocumentWrapper d2) {
        ScriptEngine.checkThreadAccess();
        ElementDocument elementDocument = this.createPopup("messagebox.rml", null);
        HashMap hashMap = elementDocument.getMetadataMap();
        elementDocument.getElementById("message").setTextWithNewlines(d2.c);
        if (d2.b == null) {
            d2.b = "";
        }
        elementDocument.getElementById("title").setText(d2.b);
        this.a(elementDocument, "button_1", d2.e, hashMap);
        this.a(elementDocument, "button_2", d2.f, hashMap);
        Element element = elementDocument.getElementById("button_back");
        element.loadCharsetIfNeededWithCurrentText();
        String string = "closePopup();";
        if (d2.d != null) {
            string = string + "hideKeyboard();";
        }
        element.setAttribute("onclick", string);
        if (!d2.h) {
            element.hide();
        }
        if (d2.e == null && d2.f == null) {
            element.setText(com.corrodinggames.rts.gameFramework.steam.Localization.a("menus.common.ok", new Object[0]));
            element.focus();
        }
        if (d2.d != null) {
            Element element2 = elementDocument.getElementById("textInputWrapper");
            element2.show();
            Element element3 = elementDocument.getElementById("textInput");
            element3.setAttribute("value", d2.d);
            element3.focus();
        }
        d2.a = elementDocument;
        if (d2.g) {
            if (this.b(d2)) {
                return elementDocument;
            }
            this.closeDocument(elementDocument);
            return null;
        }
        return elementDocument;
    }

    public boolean b(DocumentWrapper d2) {
        if (this.j != null) {
            GlobalState.e("AlertPopup already visible closing");
            this.closeDocument(this.j.a);
            this.j = null;
        }
        this.j = d2;
        GlobalState.e("Showing popup: " + d2.b);
        if (!this.e) {
            this.update();
        } else {
            GlobalState.e("insideEvent");
        }
        GlobalState.e("popup ready..");
        d2.a.show(4);
        GlobalState.e("Popup shown..");
        return true;
    }

    public boolean a(ElementDocument elementDocument) {
        return this.c(new DocumentWrapper(elementDocument));
    }

    public boolean c(DocumentWrapper d2) {
        if (this.k != null) {
            GlobalState.e("Popup already visible, cannot show: " + d2.a.getMetadata("sourceDocumentId"));
            if (this.j != null) {
                this.j.a.pullToFront();
                return false;
            }
            this.k.a.pullToFront();
            return false;
        }
        this.k = d2;
        GlobalState.e("Showing popup: " + d2.b);
        if (!this.e) {
            this.update();
        } else {
            GlobalState.e("insideEvent");
        }
        GlobalState.e("popup ready..");
        d2.a.show(4);
        GlobalState.e("Popup shown..");
        return true;
    }

    /* 02b librocket/b.java L366: 调 GameUtils.a(File) 抛 IOException (R8 移除 throws) */
    public ElementDocument a(String string, Object object, String string2, boolean bl) throws IOException {
        ScriptEngine.checkThreadAccess();
        ElementDocument elementDocument = this.createPopup("messagebox.rml", object);
        elementDocument.setMetadata("sourceDocumentId", string);
        File file = new File(com.corrodinggames.librocket.b.a(string));
        String string3 = com.corrodinggames.rts.gameFramework.GameUtils.a(file);
        elementDocument.getElementById("mainButtons").hide();
        Element element = elementDocument.getElementById("message");
        element.setInnerRML(string3);
        this.a(element, false);
        if (string2 == null) {
            string2 = "";
        }
        elementDocument.getElementById("title").setText(string2);
        if (bl) {
            if (this.b(elementDocument)) {
                return elementDocument;
            }
            return null;
        }
        return elementDocument;
    }

    public boolean b(ElementDocument elementDocument) {
        DocumentWrapper d2 = new DocumentWrapper(elementDocument);  // 02b: d var3 = new d(var1); NeutralPlayer 幻觉名修正
        if (this.c(d2)) {  // 02b: c(d) L340
            return true;
        }
        this.closeDocument(elementDocument);
        return false;
    }

    public void a(Element element, boolean bl) {
        if (element == null) {
            GlobalState.e("loadCharsetIfNeededOnChildren: root is null");
            return;
        }
        ArrayList arrayList = element.getAllNestedChildren();
        for (Element element2 : (java.util.Collection<Element>) (java.util.Collection) arrayList) {
            boolean bl2 = false;
            String string = element2.getTagName();
            if (string.equals("p") || string.startsWith("h") || string.startsWith("label") || string.startsWith("button") || string.startsWith("select")) {
                bl2 = true;
            }
            if (bl && string.equals("option")) {
                bl2 = true;
            }
            if (!bl2) continue;
            boolean bl3 = element2.loadCharsetIfNeededWithCurrentText();
        }
    }

    public void a(ElementDocument elementDocument, String string, Object object, HashMap hashMap) {
        Element element = elementDocument.getElementById(string);
        if (object == null) {
            element.hide();
        } else if (object instanceof String) {
            String string2 = (String)object;
            int n2 = string2.indexOf(":");
            String string3 = string2.substring(0, n2);
            String string4 = "";
            if (n2 + 1 < string2.length()) {
                string4 = string2.substring(n2 + 1);
            }
            if (string3.startsWith("[onenter]")) {
                string3 = string3.substring("[onenter]".length());
                elementDocument.getElementById("textInput").setAttribute("onenter", string4);
            }
            element.setText(string3);
            element.setAttribute("onclick", string4);
        } else if (object instanceof e) {
            e e2 = (e)object;
            String string5 = "action_" + string;
            hashMap.put(string5, e2.runnable);
            element.setText(e2.string);
            element.setAttribute("onclick", "runRunnable(" + string5 + ");");
            if (e2.c) {
                elementDocument.getElementById("textInput").setAttribute("onenter", "runRunnable(" + string5 + ");");
            }
        } else {
            GlobalState.g("Unhandled type:" + object);
        }
    }

    public boolean h() {
        if (this.i()) {
            return true;
        }
        return this.j();
    }

    public boolean i() {
        DocumentWrapper d2 = this.j;  // 02b: d j 字段
        if (d2 != null) {
            GlobalState.e("Closing alert");
            this.closeDocument(d2.a);
            this.j = null;
            if (d2.i != null) {
                d2.i.run();
            }
            return true;
        }
        return false;
    }

    public boolean j() {
        DocumentWrapper d2 = this.k;  // 02b: d k 字段
        if (d2 != null) {
            GlobalState.e("Closing popup");
            this.closeDocument(d2.a);
            this.k = null;
            if (d2.i != null) {
                d2.i.run();
            }
            return true;
        }
        return false;
    }

    public String k() {
        ElementDocument elementDocument = this.e();
        Element element = elementDocument.getElementById("textInput");
        return element.getAttribute("value");
    }

    public String d(String string) {
        String string2 = null;
        if (string != null && string.contains("class=\"log-entry\"")) {
            System.out.println("parseText: skipping log line:" + string);
            return null;
        }
        int n2 = 0;
        Matcher matcher = this.i.matcher(string);
        while (matcher.find()) {
            Object object;
            if (++n2 > 100) {
                System.out.println("parseText too many loops!!");
                return null;
            }
            String string3 = matcher.group(1);
            String string4 = null;
            if (this.debug) {
                System.out.println("parseText:" + string3);
            }
            if (string3.startsWith("i:")) {
                object = string3.substring(2);
                string4 = com.corrodinggames.rts.gameFramework.steam.Localization.a((String)object, new Object[0]);
            }
            if (string4 == null && (object = this.c.processArg(string3)) != null) {
                string4 = object.toString();
            }
            string = string4 == null ? matcher.replaceFirst("(unhandled:" + string3 + ")") : matcher.replaceFirst(string4);
            if (string4 != null) {
                object = this.getActiveDocument();
                if (object != null && !((ElementDocument)object).translatedToUnicode && com.corrodinggames.rts.gameFramework.GameUtils.containsNonAscii(string4)) {
                    ((ElementDocument)object).translatedToUnicode = true;
                }
                if ((object = this.g()) != null && !((ElementDocument)object).translatedToUnicode && com.corrodinggames.rts.gameFramework.GameUtils.containsNonAscii(string4)) {
                    ((ElementDocument)object).translatedToUnicode = true;
                }
            }
            matcher = this.i.matcher(string);
            string2 = string;
        }
        return string2;
    }

    @Override
    public String TranslateString(String string) {
        try {
            String string2 = this.d(string);
            if (string2 != null) {
                return string2;
            }
        }
        catch (Exception exception) {
            ScriptEngine.throwDelayedException("TranslateString exception on: " + string, exception);
            GlobalState.a("Exception in TranslateString", (Throwable)exception);
            GlobalState.b("start");
            exception.printStackTrace();
            GlobalState.b("end");
            GlobalState.b("start logStack");
            GlobalState.T();
            GlobalState.b("end logStack");
            System.err.flush();
            System.out.flush();
            return null;
        }
        return null;
    }

    @Override
    public long getFileLastModified(String string) {
        return com.corrodinggames.rts.gameFramework.FileWatcher.a(string, false);  // 02b: j.a(String,boolean) L576
    }

    @Override
    public void postUpdate() {
        boolean bl = this.queueExtraUpdate;
        super.postUpdate();
        if (bl) {
            this.c.checkForErrors();
        }
    }
}
