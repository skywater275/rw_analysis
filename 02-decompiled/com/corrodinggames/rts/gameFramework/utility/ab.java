/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ac;
import com.corrodinggames.rts.gameFramework.utility.ai;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ab {
    private static final Pattern g = Pattern.compile("\\p{C}");
    private static final Pattern h = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
    private static final Pattern i = Pattern.compile("\\s*([^=:]*)(?:=|:)(.*)");
    private LinkedHashMap j = new LinkedHashMap();
    String a = "ini";
    boolean b = true;
    LinkedHashSet c = new LinkedHashSet();
    public ArrayList d = new ArrayList();
    public ArrayList e = new ArrayList();
    private boolean k;
    public String f;

    public void a() {
        this.k = true;
        this.b = false;
    }

    public void a(String string, String string2) {
        this.a(string, string2, "Unknown");
    }

    public void a(String string, String string2, String string3) {
        if (this.b) {
            this.c.add(string + ":" + string2);
        }
    }

    public void b() {
        if (!this.b) {
            throw new RuntimeException("Not tracking reads");
        }
        for (String string : this.j.keySet()) {
            if (string != null && string.startsWith("template_")) continue;
            boolean bl = false;
            String string2 = null;
            Map map = (Map)this.j.get(string);
            for (String string3 : map.keySet()) {
                if (string3 != null && (string3.startsWith("@define ") || string3.startsWith("@global "))) continue;
                String string4 = string + ":" + string3;
                if (!this.c.contains(string4)) {
                    String string5 = (String)map.get(string3);
                    if ("IGNORE".equals(string5) || string2 != null) continue;
                    if (string3 != null && string3.trim().equals("")) {
                        string2 = this.a + " Found line in [" + string + "] with no key name.";
                        continue;
                    }
                    string2 = this.a + ": The key '[" + string + "]" + string3 + "' was not used. (hint: make sure it's valid and in the right section)";
                    continue;
                }
                bl = true;
            }
            if (string2 == null) continue;
            if (bl || this.j.size() == 1) {
                throw new RuntimeException(string2);
            }
            throw new RuntimeException(this.a + ": No keys in section: [" + string + "] were used (is this section named correctly?)");
        }
    }

    public int c() {
        try {
            Object object2;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            for (Object object2 : this.j.keySet()) {
                Map map = (Map)this.j.get(object2);
                for (String string : map.keySet()) {
                    String string2 = (String)object2 + ":" + string + ":" + (String)map.get(string);
                    byte[] byArray = string2.getBytes("UTF-8");
                    messageDigest.update(byArray);
                }
            }
            Object object3 = messageDigest.digest();
            object2 = new BigInteger(1, (byte[])object3);
            return ((BigInteger)object2).intValue();
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException(noSuchAlgorithmException);
        }
    }

    public ab(String string) {
        this.a = string;
        this.f = string;
        this.a(string);
    }

    public ab(InputStream inputStream, String string) {
        this.a = string;
        this.a(inputStream);
    }

    public void a(String string) {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(string));
        this.a(bufferedReader);
    }

    public void a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        this.a(bufferedReader);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(BufferedReader bufferedReader) {
        try {
            String string;
            String string2;
            int n2 = 0;
            String string3 = null;
            boolean bl = false;
            String string4 = "\"\"\"";
            String string5 = "";
            boolean bl2 = false;
            while ((string2 = bufferedReader.readLine()) != null) {
                String string6;
                Matcher matcher;
                ++n2;
                if (string2.startsWith("\ufeff")) {
                    string2 = string2.substring(1);
                }
                string = string2.trim();
                boolean bl3 = false;
                if (!bl && string.startsWith("#")) continue;
                if (com.corrodinggames.rts.gameFramework.f.c(string2, "\"\"\"")) {
                    int n3 = 0;
                    if (!bl && string2.trim().startsWith("\"\"\"")) {
                        bl2 = true;
                    }
                    while (true) {
                        int n4;
                        if ((n4 = string2.indexOf("\"\"\"", n3)) == -1) break;
                        string5 = string5 + string2.substring(n3, n4);
                        n3 = n4 + 3;
                        bl = !bl;
                    }
                    string5 = string5 + string2.substring(n3, string2.length());
                    if (bl) continue;
                    if (bl2) {
                        string5 = "";
                        bl2 = false;
                        continue;
                    }
                    string2 = string5;
                    string5 = "";
                    bl2 = false;
                    bl3 = true;
                } else if (bl) {
                    string5 = string5 + string2;
                    continue;
                }
                if (string.length() == 0) continue;
                if (com.corrodinggames.rts.gameFramework.f.c(string2, "[") && (matcher = h.matcher(string2)).matches()) {
                    string3 = matcher.group(1).trim();
                    continue;
                }
                if (string3 != null && string3.startsWith("comment_")) continue;
                Matcher matcher2 = i.matcher(string2);
                if (matcher2.matches()) {
                    LinkedHashMap<String, String> linkedHashMap;
                    if (string3 == null) {
                        l.b("IniFile: " + this.a + "  line " + n2 + " is not in a [section]:'" + string2 + "'");
                        this.e.add("Line " + n2 + " is not in a [section]: '" + string2 + "'");
                        continue;
                    }
                    String string7 = matcher2.group(1).trim();
                    string6 = matcher2.group(2);
                    if (!bl3) {
                        string6 = string6.trim();
                    }
                    if (string7.equals("")) {
                        linkedHashMap = this.a + ": Unexpected format on line " + n2 + ": Key cannot be empty for line '" + string2 + "'";
                        throw new IOException((String)((Object)linkedHashMap));
                    }
                    linkedHashMap = (LinkedHashMap<String, String>)this.j.get(string3);
                    if (linkedHashMap == null) {
                        linkedHashMap = new LinkedHashMap<String, String>();
                        this.j.put(string3, linkedHashMap);
                    }
                    if (linkedHashMap.get(string7) != null) {
                        this.d.add(new ac(string3, string7));
                    }
                    linkedHashMap.put(string7, string6);
                    continue;
                }
                matcher2 = g.matcher(string2);
                if (matcher2.find()) {
                    if (string2.length() == 1) continue;
                    String string8 = string2.replaceAll("\\p{C}", "?");
                    string6 = this.a + ": Unexpected format on line:" + n2 + ": '" + string8 + "' in ini file (hint: This line might have hidden unicode)";
                    throw new IOException(string6);
                }
                l.b(this.a + ": Unexpected format on line:" + n2 + ": '" + string2 + "' in ini file");
                this.e.add(string2);
            }
            if (bl) {
                string = this.a + ": End of file while in multi-line string (hint: You are likely missing a closing \"\"\")";
                throw new IOException(string);
            }
        }
        finally {
            bufferedReader.close();
        }
    }

    private String a(String string, String string2, boolean bl, String string3) {
        String string4 = this.a(string, string2, bl);
        if (string4 != null) {
            this.a(string, string2, string3);
        }
        return string4;
    }

    public String b(String string, String string2) {
        Map map = (Map)this.j.get(string);
        if (map == null) {
            return null;
        }
        String string3 = (String)map.get(string2);
        return string3;
    }

    private String a(String string, String string2, boolean bl) {
        Map map = (Map)this.j.get(string);
        if (map == null) {
            if (!bl) {
                throw new RuntimeException("Could not find section: [" + string + "] in configuration file");
            }
            return null;
        }
        String string3 = (String)map.get(string2);
        if (string3 == null) {
            if (!bl) {
                throw new RuntimeException("Could not find: " + string2 + " in configuration file under [" + string + "]");
            }
            return null;
        }
        if (string3.equals("IGNORE")) {
            if (!bl) {
                throw new RuntimeException("Key: " + string2 + " under [" + string + "], is set to IGNORE but is required and has no default");
            }
            return null;
        }
        return string3;
    }

    public String a(String string, String string2, String string3, String string4) {
        String string5 = this.b(string, string2, (String)null);
        String string6 = this.b(string, string3, (String)null);
        if (string5 != null && string6 != null) {
            throw new RuntimeException("[" + string + "]Cannot set " + string2 + " and " + string3 + " at the same time");
        }
        if (string5 != null) {
            return string5;
        }
        if (string6 != null) {
            return string6;
        }
        return string4;
    }

    public Boolean a(String string, String string2, String string3, Boolean bl) {
        String string4 = this.a(string, string2, string3, (String)null);
        if (string4 == null) {
            return bl;
        }
        if (string4.equalsIgnoreCase("true")) {
            return true;
        }
        if (string4.equalsIgnoreCase("false")) {
            return false;
        }
        if (string4.equalsIgnoreCase("1")) {
            return true;
        }
        if (string4.equalsIgnoreCase("0")) {
            return false;
        }
        throw new RuntimeException(string2 + ": unexpected boolean value:'" + string4 + "' in section:" + string);
    }

    public Boolean a(String string, String string2, Boolean bl) {
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            return bl;
        }
        if (string3.equalsIgnoreCase("true")) {
            return true;
        }
        if (string3.equalsIgnoreCase("false")) {
            return false;
        }
        if (string3.equalsIgnoreCase("1")) {
            return true;
        }
        if (string3.equalsIgnoreCase("0")) {
            return false;
        }
        throw new RuntimeException(string2 + ": unexpected boolean value:'" + string3 + "' in section:" + string);
    }

    public void c(String string, String string2) {
        throw new RuntimeException("Could not find " + string2 + " in configuration file in section:" + string);
    }

    public boolean d(String string, String string2) {
        Boolean bl = this.a(string, string2, (Boolean)null);
        if (bl == null) {
            this.c(string, string2);
        }
        return bl;
    }

    public String e(String string, String string2) {
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            this.c(string, string2);
        }
        return string3;
    }

    public String b(String string, String string2, String string3) {
        String string4 = this.a(string, string2, true, "string");
        if (string4 == null) {
            return string3;
        }
        if (string4.contains("%{") && string4.contains("}")) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Doesn't support dynamic %{} sections");
        }
        return string4;
    }

    public String c(String string, String string2, String string3) {
        String string4 = this.a(string, string2, true, "string");
        if (string4 == null) {
            return string3;
        }
        return string4;
    }

    public String f(String string, String string2) {
        String string3 = this.c(string, string2, (String)null);
        if (string3 == null) {
            this.c(string, string2);
        }
        return string3;
    }

    public static String b(String string) {
        if (string == null) {
            return null;
        }
        string = com.corrodinggames.rts.gameFramework.f.a(string, "\\n", "\n");
        return string;
    }

    public aj a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, String string3) {
        bb bb2 = this.a(string, string2, string3, true);
        if (bb2 == null) {
            return null;
        }
        try {
            aj aj2 = new aj(l2, bb2);
            return aj2;
        }
        catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            throw new bo("[" + string + "]" + string2 + ": " + runtimeException.getMessage());
        }
    }

    public bb a(String string, String string2, String string3, boolean bl) {
        String string4 = bl ? this.c(string, string2, (String)null) : this.b(string, string2, (String)null);
        if (string4 == null) {
            if (string3 == null) {
                return null;
            }
            string4 = string3;
        }
        string4 = ab.b(string4);
        bb bb2 = new bb();
        if (string4 != null && string4.startsWith("i:")) {
            bb2.e = string4.substring("i:".length());
            bb2.e = bb2.e.trim();
            com.corrodinggames.rts.gameFramework.h.a.a(bb2.e, new Object[0]);
            return bb2;
        }
        ArrayList<bc> arrayList = new ArrayList<bc>();
        bc bc2 = new bc();
        bc2.a = null;
        bc2.b = string4;
        arrayList.add(bc2);
        String string5 = string2 + "_";
        m m2 = this.k(string, string5);
        for (String string6 : m2) {
            String string7 = string6.substring(string5.length());
            string7 = string7.toLowerCase(Locale.ROOT);
            String string8 = bl ? this.f(string, string6) : this.e(string, string6);
            string8 = ab.b(string8);
            bc bc3 = new bc();
            bc3.a = string7;
            bc3.b = string8;
            arrayList.add(bc3);
        }
        bb2.b = arrayList.toArray(new bc[0]);
        bb2.b();
        return bb2;
    }

    public LogicBoolean a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2) {
        String string3 = this.e(string, string2);
        try {
            return LogicBoolean.create(l2, string3, null);
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": " + runtimeException.getMessage(), runtimeException);
        }
    }

    public LogicBoolean a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, LogicBoolean logicBoolean) {
        String string3 = this.b(string, string2, (String)null);
        try {
            return LogicBoolean.create(l2, string3, logicBoolean);
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": " + runtimeException.getMessage(), runtimeException);
        }
    }

    public LogicBoolean b(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, LogicBoolean logicBoolean) {
        return this.a(l2, string, string2, logicBoolean, LogicBoolean$ReturnType.unit);
    }

    public LogicBoolean c(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, LogicBoolean logicBoolean) {
        return this.a(l2, string, string2, logicBoolean, LogicBoolean$ReturnType.number);
    }

    public LogicBoolean a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, LogicBoolean logicBoolean, LogicBoolean$ReturnType logicBoolean$ReturnType) {
        String string3 = this.b(string, string2, (String)null);
        return ab.a(string3, l2, string, string2, logicBoolean, logicBoolean$ReturnType);
    }

    public static LogicBoolean a(String string, com.corrodinggames.rts.game.units.custom.l l2, String string2, String string3, LogicBoolean logicBoolean) {
        return ab.a(string, l2, string2, string3, logicBoolean, LogicBoolean$ReturnType.unit);
    }

    public static LogicBoolean a(String string, com.corrodinggames.rts.game.units.custom.l l2, String string2, String string3, LogicBoolean logicBoolean, LogicBoolean$ReturnType logicBoolean$ReturnType) {
        try {
            LogicBoolean logicBoolean2;
            if (string == null) {
                return logicBoolean;
            }
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number && com.corrodinggames.rts.gameFramework.f.r(string)) {
                return LogicBoolean$StaticValueBoolean.getStaticNumber(string);
            }
            if (string.toLowerCase(Locale.ROOT).startsWith("unitref ")) {
                string = string.substring("unitref ".length()).trim();
            }
            if ((logicBoolean2 = LogicBooleanLoader.parseBooleanBlock(l2, string, false)) == null) {
                return null;
            }
            LogicBoolean$ReturnType logicBoolean$ReturnType2 = logicBoolean2.getReturnType();
            if (logicBoolean$ReturnType2 != logicBoolean$ReturnType) {
                throw new RuntimeException("[" + string2 + "]" + string3 + ": Type mismatch. Expected type:" + (Object)((Object)logicBoolean$ReturnType) + " got:" + (Object)((Object)logicBoolean$ReturnType2));
            }
            return logicBoolean2;
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": " + runtimeException.getMessage(), runtimeException);
        }
    }

    public g a(String string, String string2, g g2) {
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            return g2;
        }
        if (string3.trim().equals("")) {
            return g2;
        }
        if (string3.contains(",")) {
            throw new bo("[" + string + "]" + string2 + ": Expected single tag, got:" + string3);
        }
        return com.corrodinggames.rts.game.units.custom.g.c(string3);
    }

    public h a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, h h2) {
        String string3 = this.b(string, string2, (String)null);
        return com.corrodinggames.rts.game.units.custom.g.a(string3, h2);
    }

    public u a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, u u2) {
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            return u2;
        }
        u u3 = l2.c(string3, string2, string);
        return u3;
    }

    public a a(com.corrodinggames.rts.game.units.custom.l l2, String string, String string2, a a2, boolean bl) {
        a a3;
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            return a2;
        }
        if (bl && (a3 = com.corrodinggames.rts.game.units.custom.e.a.a(string3)) != null) {
            return a3;
        }
        a3 = l2.k(string3);
        if (a3 == null) {
            throw new BooleanParseException("[" + string + "]" + string2 + ": Could not find custom resource type of:" + string3);
        }
        return a3;
    }

    public Integer a(String string, String string2, Integer n2) {
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            return n2;
        }
        if (string3.equals("")) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Unknown color: ''");
        }
        try {
            return Color.a(string3);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Unknown color:" + string3);
        }
    }

    public int g(String string, String string2) {
        String string3 = this.a(string, string2, false, "int");
        try {
            return Integer.parseInt(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Not a static integer: " + string3);
        }
    }

    public Short a(String string, String string2, Short s2) {
        String string3 = this.a(string, string2, true, "short");
        if (string3 == null) {
            return s2;
        }
        try {
            return Short.parseShort(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Not a static integer: " + string3);
        }
    }

    public Integer b(String string, String string2, Integer n2) {
        String string3 = this.a(string, string2, true, "int");
        if (string3 == null) {
            return n2;
        }
        try {
            return Integer.parseInt(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Not a static integer: " + string3);
        }
    }

    public Float a(String string, String string2, Float f2) {
        String string3 = this.a(string, string2, true, "float");
        if (string3 == null) {
            return f2;
        }
        try {
            return Float.valueOf(Float.parseFloat(string3));
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Not a static float: " + string3);
        }
    }

    public PointF a(String string, String string2, PointF pointF) {
        String string3 = this.a(string, string2, true, "point");
        if (string3 == null) {
            return pointF;
        }
        if (string3.equalsIgnoreCase("NONE")) {
            return null;
        }
        try {
            String[] stringArray = string3.split(",");
            if (stringArray.length != 2) {
                throw new NumberFormatException("Got:" + stringArray.length + " elements expected 2");
            }
            PointF pointF2 = new PointF();
            pointF2.a = Float.parseFloat(stringArray[0]);
            pointF2.b = Float.parseFloat(stringArray[1]);
            return pointF2;
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Failed to read point:" + string3 + " in key:" + string2 + " section:" + string + " expected format: x,y");
        }
    }

    public ai a(String string, String string2, ai ai2) {
        String string3 = this.a(string, string2, true, "point3d");
        if (string3 == null) {
            return ai2;
        }
        if (string3.equalsIgnoreCase("NONE")) {
            return null;
        }
        try {
            String[] stringArray = string3.split(",");
            if (stringArray.length != 2 && stringArray.length != 3) {
                throw new NumberFormatException("Got:" + stringArray.length + " elements expected 2 or 3");
            }
            ai ai3 = new ai();
            ai3.a = Float.parseFloat(stringArray[0]);
            ai3.b = Float.parseFloat(stringArray[1]);
            if (stringArray.length > 2) {
                ai3.c = Float.parseFloat(stringArray[2]);
            }
            return ai3;
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Failed to read point:" + string3 + " in key:" + string2 + " section:" + string + " expected format: x,y,[height]");
        }
    }

    public Float h(String string, String string2) {
        Float f2 = this.b(string, string2, (Float)null);
        if (f2 == null) {
            throw new RuntimeException("Could not find key:" + string2 + " in section:" + string);
        }
        return f2;
    }

    public Float b(String string, String string2, Float f2) {
        return this.a(string, string2, f2, false);
    }

    public Float c(String string, String string2, Float f2) {
        Float f3 = this.a(string, string2, (Float)null, false);
        if (f3 == null) {
            return f2;
        }
        return Float.valueOf(f3.floatValue() * 16.666666f);
    }

    public Float a(String string, String string2, Float f2, boolean bl) {
        String string3 = this.a(string, string2, true, "time");
        if (string3 == null) {
            return f2;
        }
        try {
            return Float.valueOf(ab.a(string3, bl, string, string2));
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Failed to read time:" + string3 + " in key:" + string2 + " section:" + string + " expected a float with optional 's' or 'ms' postfix");
        }
    }

    public Float d(String string, String string2, Float f2) {
        return this.a(string, string2, f2, true);
    }

    public static float a(String string, boolean bl, String string2, String string3) {
        float f2;
        float f3 = 1.0f;
        boolean bl2 = false;
        if (string.endsWith("s")) {
            string = string.substring(0, string.length() - 1);
            f3 = 60.0f;
            bl2 = true;
        } else {
            f3 = 1.0f;
        }
        try {
            f2 = Float.parseFloat(string) * f3;
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": Failed to read time:" + string + " expected a float with optional 's' postfix");
        }
        if (bl) {
            if (bl2) {
                return 1.0f / f2;
            }
            return f2;
        }
        return f2;
    }

    public float i(String string, String string2) {
        String string3 = this.a(string, string2, false, "float");
        try {
            return Float.parseFloat(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Failed to read float:" + string3 + " in key:" + string2 + " section:" + string);
        }
    }

    public double j(String string, String string2) {
        String string3 = this.a(string, string2, false, "double");
        try {
            return Double.parseDouble(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Failed to read float:" + string3 + " in key:" + string2 + " section:" + string);
        }
    }

    public double a(String string, String string2, double d2) {
        String string3 = this.a(string, string2, true, "double");
        if (string3 == null) {
            return d2;
        }
        try {
            return Double.parseDouble(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Failed to read float:" + string3 + " in key:" + string2 + " section:" + string);
        }
    }

    public long a(String string, String string2, long l2) {
        String string3 = this.a(string, string2, true, "long");
        if (string3 == null) {
            return l2;
        }
        try {
            return Long.parseLong(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Failed to read long:" + string3 + " in key:" + string2 + " section:" + string);
        }
    }

    public void d(String string, String string2, String string3) {
        if (this.k) {
            throw new RuntimeException("locked changes");
        }
        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>)this.j.get(string);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<String, String>();
            this.j.put(string, linkedHashMap);
        }
        if (linkedHashMap.get(string2) == null) {
            linkedHashMap.put(string2, string3);
        }
    }

    public void e(String string, String string2, String string3) {
        if (this.k) {
            throw new RuntimeException("locked changes");
        }
        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>)this.j.get(string);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<String, String>();
            this.j.put(string, linkedHashMap);
        }
        linkedHashMap.put(string2, string3);
    }

    public void a(ab ab2) {
        if (this.k) {
            throw new RuntimeException("locked changes");
        }
        for (String string : ab2.j.keySet()) {
            LinkedHashMap linkedHashMap = (LinkedHashMap)ab2.j.get(string);
            if (this.a(string, "@copyFrom_skipThisSection", (Boolean)false).booleanValue()) continue;
            LinkedHashMap linkedHashMap2 = (LinkedHashMap)this.j.get(string);
            if (linkedHashMap2 == null) {
                linkedHashMap2 = new LinkedHashMap();
                this.j.put(string, linkedHashMap2);
            }
            for (String string2 : linkedHashMap.keySet()) {
                if (linkedHashMap2.get(string2) != null) continue;
                linkedHashMap2.put(string2, linkedHashMap.get(string2));
            }
        }
    }

    public Rect a(String string, String string2, Rect rect) {
        String string3 = this.b(string, string2, (String)null);
        if (string3 == null) {
            return rect;
        }
        String[] stringArray = string3.split(",");
        if (stringArray.length != 4) {
            throw new RuntimeException("[" + string + "]" + string2 + ": getRect: expected 4 ints, not:" + stringArray.length);
        }
        try {
            return new Rect(Integer.valueOf(stringArray[0].trim()), Integer.valueOf(stringArray[1].trim()), Integer.valueOf(stringArray[2].trim()), Integer.valueOf(stringArray[3].trim()));
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": getRect expected ints got: " + string3);
        }
    }

    public Enum a(String string, String string2, Enum enum_, Class clazz) {
        String string3 = this.b(string, string2, (String)null);
        try {
            return ab.a(string3, enum_, clazz);
        }
        catch (bo bo2) {
            throw new RuntimeException("[" + string + "]" + string2 + ": " + bo2.getMessage(), bo2);
        }
    }

    public static Enum a(String string, Enum enum_, Class clazz) {
        if (string == null) {
            return enum_;
        }
        string = string.trim();
        for (Object t2 : clazz.getEnumConstants()) {
            Enum enum_2 = (Enum)t2;
            if (!enum_2.name().equalsIgnoreCase(string)) continue;
            return enum_2;
        }
        throw ab.a(string, clazz);
    }

    private static RuntimeException a(String string, Class clazz) {
        String string2 = "";
        for (Object t2 : clazz.getEnumConstants()) {
            Enum enum_ = (Enum)t2;
            if (!string2.equals("")) {
                string2 = string2 + ",";
            }
            string2 = string2 + enum_.name();
        }
        throw new bo("Unknown value: " + string + " (Expected: " + com.corrodinggames.rts.gameFramework.f.b(string2, 100) + ")");
    }

    public m c(String string) {
        m m2 = new m();
        for (String string2 : this.j.keySet()) {
            Map map = (Map)this.j.get(string2);
            if (map.get(string) == null) continue;
            m2.add(string2);
        }
        return m2;
    }

    public m d(String string) {
        m m2 = new m();
        block0: for (String string2 : this.j.keySet()) {
            Map map = (Map)this.j.get(string2);
            for (String string3 : map.keySet()) {
                if (!string3.startsWith(string) || "IGNORE".equals(map.get(string3))) continue;
                m2.add(string2);
                continue block0;
            }
        }
        return m2;
    }

    public LinkedHashMap d() {
        return this.j;
    }

    public m k(String string, String string2) {
        m m2 = new m();
        Map map = (Map)this.j.get(string);
        if (map != null) {
            for (String string3 : map.keySet()) {
                if (!string3.startsWith(string2) || "IGNORE".equals(map.get(string3))) continue;
                m2.add(string3);
            }
        }
        return m2;
    }

    public m f(String string, String string2, String string3) {
        m m2 = new m();
        Map map = (Map)this.j.get(string);
        if (map != null) {
            for (String string4 : map.keySet()) {
                if (!string4.startsWith(string2) && !string4.startsWith(string3)) continue;
                m2.add(string4);
            }
        }
        return m2;
    }

    public boolean l(String string, String string2) {
        Map map = (Map)this.j.get(string);
        if (map != null) {
            for (String string3 : map.keySet()) {
                if (!string3.startsWith(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public m e(String string) {
        m m2 = new m();
        Set set = this.j.keySet();
        for (String string2 : set) {
            if (!string2.startsWith(string) || !this.g(string2)) continue;
            m2.add(string2);
        }
        return m2;
    }

    public m m(String string, String string2) {
        m m2 = new m();
        Set set = this.j.keySet();
        for (String string3 : set) {
            if (!string3.startsWith(string) && !string3.startsWith(string2) || !this.g(string3)) continue;
            m2.add(string3);
        }
        return m2;
    }

    public boolean f(String string) {
        return this.j.get(string) != null;
    }

    public boolean g(String string) {
        Map map = (Map)this.j.get(string);
        if (map == null) {
            return false;
        }
        for (String string2 : map.keySet()) {
            if (string2 == null || string2.startsWith("@")) continue;
            return true;
        }
        return false;
    }

    public boolean n(String string, String string2) {
        String string3 = this.a(string, string2, true);
        return string3 != null;
    }

    public static boolean g(String string, String string2, String string3) {
        if (string3.equalsIgnoreCase("true")) {
            return true;
        }
        if (string3.equalsIgnoreCase("false")) {
            return false;
        }
        throw new RuntimeException("[" + string + "]" + string2 + ": Unexpected boolean value:'" + string3 + "'");
    }

    public static float h(String string, String string2, String string3) {
        try {
            return Float.parseFloat(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Failed to read float:" + string3);
        }
    }

    public static int i(String string, String string2, String string3) {
        try {
            return Integer.parseInt(string3);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("[" + string + "]" + string2 + ": Failed to read int:" + string3);
        }
    }

    public static g j(String string, String string2, String string3) {
        if ((string3 = string3.trim()).contains(",")) {
            throw new bo("[" + string + "]" + string2 + ": Unexpected single tag, got:'" + string3 + "'");
        }
        if (string3.contains("\"")) {
            throw new bo("[" + string + "]" + string2 + ": tag cannot contain quote, got:'" + string3 + "'");
        }
        if (string3.contains("'")) {
            throw new bo("[" + string + "]" + string2 + ": tag cannot contain quote, got:'" + string3 + "'");
        }
        if (string3.contains(" ")) {
            throw new bo("[" + string + "]" + string2 + ": tag cannot contain space, got:'" + string3 + "'");
        }
        g g2 = com.corrodinggames.rts.game.units.custom.g.c(string3);
        return g2;
    }
}
