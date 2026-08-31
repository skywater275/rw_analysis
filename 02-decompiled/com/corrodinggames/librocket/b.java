/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import android.graphics.Rect;
import android.graphics.RectF;
import com.Element;
import com.ElementDocument;
import com.LibRocket;
import com.corrodinggames.librocket.c;
import com.corrodinggames.librocket.d;
import com.corrodinggames.librocket.e;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.bt;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j;
import com.corrodinggames.rts.gameFramework.l;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class b
extends LibRocket {
    public static bt a = new bt("LoadResources");
    public static String b = "";
    public ScriptEngine c;
    protected int d = 0;
    public boolean e;
    protected Rect f = new Rect();
    protected RectF g = new RectF();
    protected boolean h = false;
    private d j;
    private d k;
    Pattern i = Pattern.compile("\\$\\{([^\\}]*?)\\}");

    public b() {
        this.c = ScriptEngine.createScriptEngine(this);
    }

    public void a() {
        this.d = 0;
    }

    public static String a(String string) {
        l.e("convertTexturePath for: " + string);
        string = com.corrodinggames.rts.gameFramework.f.o(string);
        if (string.startsWith("base:")) {
            return b + string.substring("base:".length());
        }
        if (string.startsWith("drawable:")) {
            return b + "res/drawable/" + string.substring("drawable:".length());
        }
        if (string.startsWith("assets:")) {
            String string2 = string.substring("assets:".length());
            String string3 = com.corrodinggames.rts.gameFramework.e.a.e(string2);
            boolean bl = true;
            if (l.aZ && string3 != null && string3.startsWith(b)) {
                bl = false;
            }
            if (l.aZ && string3 != null && string3.startsWith("/private")) {
                bl = false;
            }
            l.e("convertTexturePath  (basePath:" + bl + "):" + string2 + " > " + string3);
            if (bl) {
                return b + string3;
            }
            return string3;
        }
        if (string.startsWith(b + "assets/gui/")) {
            l.e("convertTexturePath already had path:" + string);
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
        a.a();
        c c2 = (c)this.findTextureHolder(n2);
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        as as2 = null;
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
            as2 = ar.a(string);
            bl = true;
        }
        if (string.startsWith("thumbnail:")) {
            string = string.substring("thumbnail:".length());
            bl3 = true;
        }
        if ((matcher = this.a("^(alpha\\((.*)\\):).*", string)) != null) {
            string2 = matcher.group(1);
            String string3 = matcher.group(2);
            l.e("alpha=" + string3);
            c2.e = Float.parseFloat(string3);
            string = string.substring(string2.length());
        }
        string2 = com.corrodinggames.librocket.b.a(string);
        c2.b = bl;
        c2.c = bl3;
        c2.d = bl2;
        c2.f = as2;
        c2.a = string2;
        if (!bl && !c2.a()) {
            a.b();
            return false;
        }
        a.b();
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
        d d2 = new d();
        d2.b = null;
        d2.c = string;
        this.a(d2);
    }

    public void b(String string, String string2) {
        d d2 = new d();
        d2.b = string;
        d2.c = string2;
        this.a(d2);
    }

    public void a(String string, String string2, String string3, String string4, String string5, boolean bl) {
        d d2 = new d();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = string4;
        d2.f = string5;
        d2.h = bl;
        this.a(d2);
    }

    public void a(String string, String string2, String string3, e e2, e e3, boolean bl) {
        d d2 = new d();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = e2;
        d2.f = e3;
        d2.h = bl;
        this.a(d2);
    }

    public ElementDocument a(String string, String string2, String string3, Object object, Object object2, boolean bl, boolean bl2) {
        d d2 = new d();
        d2.b = string;
        d2.c = string2;
        d2.d = string3;
        d2.e = object;
        d2.f = object2;
        d2.g = bl;
        d2.h = bl2;
        return this.a(d2);
    }

    public ElementDocument a(d d2) {
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
            element.setText(com.corrodinggames.rts.gameFramework.h.a.a("menus.common.ok", new Object[0]));
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

    public boolean b(d d2) {
        if (this.j != null) {
            l.e("AlertPopup already visible closing");
            this.closeDocument(this.j.a);
            this.j = null;
        }
        this.j = d2;
        l.e("Showing popup: " + d2.b);
        if (!this.e) {
            this.update();
        } else {
            l.e("insideEvent");
        }
        l.e("popup ready..");
        d2.a.show(4);
        l.e("Popup shown..");
        return true;
    }

    public boolean a(ElementDocument elementDocument) {
        return this.c(new d(elementDocument));
    }

    public boolean c(d d2) {
        if (this.k != null) {
            l.e("Popup already visible, cannot show: " + d2.a.getMetadata("sourceDocumentId"));
            if (this.j != null) {
                this.j.a.pullToFront();
                return false;
            }
            this.k.a.pullToFront();
            return false;
        }
        this.k = d2;
        l.e("Showing popup: " + d2.b);
        if (!this.e) {
            this.update();
        } else {
            l.e("insideEvent");
        }
        l.e("popup ready..");
        d2.a.show(4);
        l.e("Popup shown..");
        return true;
    }

    public ElementDocument a(String string, Object object, String string2, boolean bl) {
        ScriptEngine.checkThreadAccess();
        ElementDocument elementDocument = this.createPopup("messagebox.rml", object);
        elementDocument.setMetadata("sourceDocumentId", string);
        File file = new File(com.corrodinggames.librocket.b.a(string));
        String string3 = com.corrodinggames.rts.gameFramework.f.a(file);
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
        d d2 = new d(elementDocument);
        if (this.c(d2)) {
            return true;
        }
        this.closeDocument(elementDocument);
        return false;
    }

    public void a(Element element, boolean bl) {
        if (element == null) {
            l.e("loadCharsetIfNeededOnChildren: root is null");
            return;
        }
        ArrayList arrayList = element.getAllNestedChildren();
        for (Element element2 : arrayList) {
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
            hashMap.put(string5, e2.b);
            element.setText(e2.a);
            element.setAttribute("onclick", "runRunnable(" + string5 + ");");
            if (e2.c) {
                elementDocument.getElementById("textInput").setAttribute("onenter", "runRunnable(" + string5 + ");");
            }
        } else {
            l.g("Unhandled type:" + object);
        }
    }

    public boolean h() {
        if (this.i()) {
            return true;
        }
        return this.j();
    }

    public boolean i() {
        d d2 = this.j;
        if (d2 != null) {
            l.e("Closing alert");
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
        d d2 = this.k;
        if (d2 != null) {
            l.e("Closing popup");
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
                string4 = com.corrodinggames.rts.gameFramework.h.a.a((String)object, new Object[0]);
            }
            if (string4 == null && (object = this.c.processArg(string3)) != null) {
                string4 = object.toString();
            }
            string = string4 == null ? matcher.replaceFirst("(unhandled:" + string3 + ")") : matcher.replaceFirst(string4);
            if (string4 != null) {
                object = this.getActiveDocument();
                if (object != null && !((ElementDocument)object).translatedToUnicode && com.corrodinggames.rts.gameFramework.f.n(string4)) {
                    ((ElementDocument)object).translatedToUnicode = true;
                }
                if ((object = this.g()) != null && !((ElementDocument)object).translatedToUnicode && com.corrodinggames.rts.gameFramework.f.n(string4)) {
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
            l.a("Exception in TranslateString", (Throwable)exception);
            l.b("start");
            exception.printStackTrace();
            l.b("end");
            l.b("start logStack");
            l.T();
            l.b("end logStack");
            System.err.flush();
            System.out.flush();
            return null;
        }
        return null;
    }

    @Override
    public long getFileLastModified(String string) {
        return com.corrodinggames.rts.gameFramework.j.a(string, false);
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
