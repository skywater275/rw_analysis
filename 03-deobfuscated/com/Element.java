/*
 * Decompiled with CFR 0.152.
 */
package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Element {
    private long nativeHandle;
    boolean loadedChinese = false;
    HashSet charsetsLoaded;

    public native Element getElementById(String var1);

    public boolean equals(Object object) {
        if (object == null || !(object instanceof Element)) {
            return false;
        }
        Element element = (Element)object;
        return element.nativeHandle == this.nativeHandle;
    }

    public String getAttribute(String string) {
        return this.getAttribute(string, null);
    }

    public boolean getAttributeBoolean(String string, boolean bl) {
        String string2 = this.getAttribute(string, null);
        if (string2 == null) {
            return bl;
        }
        if (string2.equalsIgnoreCase("true")) {
            return true;
        }
        if (string2.equalsIgnoreCase("false")) {
            return false;
        }
        return bl;
    }

    public String getId() {
        return this.getAttribute("id");
    }

    public native String getTagName();

    public native boolean focus();

    public native void blur();

    public native void click();

    public native void addReference();

    public native void removeReference();

    public native String getAttribute(String var1, String var2);

    public native void setAttribute(String var1, String var2);

    public void addStyle(String string) {
        String string2 = this.getAttribute("style");
        if (string2 == null) {
            string2 = "";
        }
        string2 = string2 + string;
        this.setAttribute("style", string2);
    }

    public void setStyle(String string) {
        this.setAttribute("style", string);
    }

    public void setValue(String string) {
        this.setAttribute("value", string);
    }

    public void setCheckbox(boolean bl) {
        if (bl) {
            this.setAttribute("checked", "");
        } else {
            this.setAttribute("checked", null);
        }
    }

    public boolean getCheckbox() {
        String string = this.getAttribute("checked");
        return string != null && !"false".equals(string);
    }

    public String getValue() {
        return this.getAttribute("value");
    }

    public Integer getValueAsInt(Integer n) {
        String string = this.getValue();
        if (string == null || string.equals("") || string.equals("null")) {
            return n;
        }
        return Integer.parseInt(string);
    }

    public Float getValueAsFloat(Float f) {
        String string = this.getValue();
        if (string == null || string.equals("")) {
            return f;
        }
        string = string.replace(",", ".");
        return Float.valueOf(Float.parseFloat(string));
    }

    public Boolean getValueAsBoolean(Boolean bl) {
        String string = this.getValue();
        if (string == null || string.equals("")) {
            return bl;
        }
        return Boolean.parseBoolean(string);
    }

    public native String getAttributeKey(int var1);

    public native String getAttributeValue(int var1);

    public native int getNumAttributes();

    public native Element getChild(int var1);

    public native int getNumChildren();

    public native String getInnerRML();

    public native void setInnerRML(String var1);

    public static String excapeHTML(String string) {
        string = string.replace("&", "&amp;");
        string = string.replace("<", "&lt;");
        string = string.replace(">", "&gt;");
        string = string.replace("${", "$ {");
        return string;
    }

    public void setTextNoCharset(String string, boolean bl) {
        if (string == null) {
            string = "";
        }
        string = Element.excapeHTML(string);
        if (bl && string.contains("\n")) {
            string = string.replaceAll("\n", "<br/>\n");
        }
        this.setInnerRML(string);
    }

    public boolean loadCharsetIfNeededWithCurrentText() {
        return this.loadCharsetIfNeeded(this.getInnerRML());
    }

    public boolean loadCharsetIfNeeded(String string) {
        int n;
        String string2 = "";
        int n2 = string.length();
        for (int i = 0; i < n2; i += Character.charCount(n)) {
            n = string.codePointAt(i);
            if (n <= 128) continue;
            boolean bl = false;
            if (n >= 196 && n <= 252) {
                bl = true;
            }
            if (bl) continue;
            if (this.charsetsLoaded == null) {
                this.charsetsLoaded = new HashSet();
            }
            if (this.charsetsLoaded.contains(n)) continue;
            this.charsetsLoaded.add(n);
            if (!string2.equals("")) {
                string2 = string2 + ",";
            }
            string2 = string2 + Element.keycodeToHex(n);
        }
        if (!string2.equals("")) {
            this.compareAndAddClass("needsUnicodeFont");
            return true;
        }
        return false;
    }

    public static final String keycodeToHex(int n) {
        String string = String.format("U+%04X", n);
        return string;
    }

    public void compareAndSetText(String string) {
        if (string == null) {
            string = "";
        }
        if (!string.equals(this.getInnerRML())) {
            this.setText(string);
        }
    }

    public void setText(String string) {
        this.loadCharsetIfNeeded(string);
        this.setTextNoCharset(string, false);
    }

    public void setTextWithNewlines(String string) {
        this.loadCharsetIfNeeded(string);
        this.setTextNoCharset(string, true);
    }

    public void addClass(String string) {
        this.setClassNames(this.getClassNames() + " " + string);
    }

    public void compareAndAddClass(String string) {
        if (!this.hasClassName(string)) {
            this.addClass(string);
        }
    }

    public void removeClass(String string) {
        String string2;
        String string3 = this.getClassNames();
        if (!string3.equals(string2 = string3.replaceAll("\\b" + string + "\\b", ""))) {
            this.setClassNames(string2);
        }
    }

    public void compareAndSetClassNames(String string) {
        if (!string.equals(this.getClassNames())) {
            this.setClassNames(string);
        }
    }

    public native void setClassNames(String var1);

    public native String getClassNames();

    public Element cloneAndFix() {
        Element element = this.clone();
        element.setClassNames(this.getClassNames());
        return element;
    }

    public native Element clone();

    public native void appendChild(Element var1);

    public native void insertBefore(Element var1, Element var2);

    public native void removeChild(Element var1);

    public native String getProperty(String var1, String var2);

    public native void setProperty(String var1, String var2);

    public native boolean isPseudoClassSet(String var1);

    public native float getAbsoluteLeft();

    public native float getAbsoluteTop();

    public native float getOffsetLeft();

    public native float getOffsetTop();

    public native float getOffsetWidth();

    public native float getOffsetHeight();

    public native float getScrollTop();

    public native void setScrollTop(float var1);

    public native void scrollIntoView(boolean var1);

    public boolean isFocused() {
        return this.isPseudoClassSet("focus");
    }

    public boolean isHovering() {
        return this.isPseudoClassSet("hover");
    }

    public void prependChild(Element element) {
        if (this.getNumChildren() == 0) {
            this.appendChild(element);
        } else {
            this.insertBefore(element, this.getChild(0));
        }
    }

    public void clearAllChildren() {
        int n;
        for (int i = n = this.getNumChildren(); i >= 0; --i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            String string = element.getTagName();
            if (string.equals("scrollbarvertical")) continue;
            this.removeChild(element);
        }
    }

    public ArrayList getChildren() {
        ArrayList<Element> arrayList = new ArrayList<Element>();
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            arrayList.add(element);
        }
        return arrayList;
    }

    public Element getTopLevelFocusedElement() {
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            Element element2 = element.getTopLevelFocusedElement();
            if (element2 == null) continue;
            return element2;
        }
        if (this.isFocused()) {
            return this;
        }
        return null;
    }

    public ArrayList getChainFromChildElement(Element element) {
        if (element.equals(this)) {
            ArrayList<Element> arrayList = new ArrayList<Element>();
            arrayList.add(this);
            return arrayList;
        }
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element2 = this.getChild(i);
            if (element2 == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            ArrayList arrayList = element2.getChainFromChildElement(element);
            if (arrayList == null) continue;
            arrayList.add(this);
            return arrayList;
        }
        return null;
    }

    public ArrayList getAllNestedChildren() {
        ArrayList arrayList = new ArrayList();
        this.getAllNestedChildren(arrayList);
        return arrayList;
    }

    public void getAllNestedChildren(ArrayList arrayList) {
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            arrayList.add(element);
            element.getAllNestedChildren(arrayList);
        }
    }

    public boolean hasClassName(String string) {
        return (" " + this.getClassNames() + " ").contains(" " + string + " ");
    }

    public Element findByClassName(String string) {
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            if (element.hasClassName(string)) {
                return element;
            }
            Element element2 = element.findByClassName(string);
            if (element2 == null) continue;
            return element2;
        }
        return null;
    }

    public Element findByTagName(String string) {
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            if (element.getTagName().equalsIgnoreCase(string)) {
                return element;
            }
            Element element2 = element.findByTagName(string);
            if (element2 == null) continue;
            return element2;
        }
        return null;
    }

    public ArrayList findElementsByClassName(String string) {
        ArrayList arrayList = new ArrayList();
        this.findElementsByClassName(string, arrayList);
        return arrayList;
    }

    public void findElementsByClassName(String string, List list) {
        int n = this.getNumChildren();
        for (int i = 0; i < n; ++i) {
            Element element = this.getChild(i);
            if (element == null) {
                throw new RuntimeException("child==null, i:" + i);
            }
            if ((" " + element.getClassNames() + " ").contains(" " + string + " ")) {
                list.add(element);
            }
            element.findElementsByClassName(string, list);
        }
    }

    public void hide() {
        this.show(false);
    }

    public void show() {
        this.show(true);
    }

    public void show(boolean bl) {
        if (!bl) {
            this.compareAndAddClass("hide");
        } else {
            this.removeClass("hide");
        }
    }
}
