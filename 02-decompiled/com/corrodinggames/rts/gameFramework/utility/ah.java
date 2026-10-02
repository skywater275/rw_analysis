/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.ag;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ah {
    String a = "";
    String b;
    ZipFile c;
    String[] d;
    boolean e;

    public ah(String string, String string2) {
        this.b = string;
        l.g("Opening new zip at: " + string2);
        af af2 = ae.b(string2);
        if (af2 != null) {
            l.e("Temp file needed for zip with SAF interface");
            if (!l.at()) {
                throw new IOException("Failed to open source zip with mapper: " + string2);
            }
            long l2 = br.a();
            j j2 = af2.b(string2, true);
            if (j2 == null) {
                throw new IOException("Failed to open file of zip: " + string2);
            }
            this.c = ah.a(j2, null);
            double d = br.a(l2);
            l.e("Streamed zip open took:" + br.a(d));
        } else {
            this.c = new ZipFile(string2);
        }
        try {
            this.b();
        }
        catch (IllegalArgumentException illegalArgumentException) {
            ag.h("Failed to open source zip with unicode encoding, attempting with ISO-8859-1");
            Charset charset = Charset.forName("ISO-8859-1");
            try {
                if (af2 != null) {
                    l.e("Temp file needed for zip with SAF interface");
                    if (!l.at()) {
                        throw new IOException("Failed to open source zip with mapper: " + string2);
                    }
                    long l3 = br.a();
                    j j3 = af2.b(string2, true);
                    this.c = ah.a(j3, charset);
                    double d = br.a(l3);
                    l.e("Streamed zip open took:" + br.a(d));
                } else {
                    this.c = ah.a(string2, charset);
                }
            }
            catch (RuntimeException runtimeException) {
                illegalArgumentException.printStackTrace();
                throw new IOException("Failed to open source zip with unicode and ISO-8859-1 encoding", runtimeException);
            }
            this.b();
        }
    }

    public void a() {
        if (!this.e) {
            this.e = true;
            if (this.c != null) {
                try {
                    this.c.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ZipFile a(InputStream inputStream, Charset charset) {
        File file = com.corrodinggames.rts.gameFramework.e.a.a(com.corrodinggames.rts.appFramework.c.a(), "safMod", "zip");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            f.a(inputStream, fileOutputStream);
            fileOutputStream.close();
            inputStream.close();
            if (charset == null) {
                ZipFile zipFile = new ZipFile(file);
                return zipFile;
            }
            ZipFile zipFile = ah.a(file.getAbsolutePath(), charset);
            return zipFile;
        }
        finally {
            file.delete();
        }
    }

    public static ZipFile a(String string, Charset charset) {
        Class[] classArray = new Class[]{String.class, Charset.class};
        Constructor constructor = null;
        try {
            constructor = ZipFile.class.getDeclaredConstructor(classArray);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
        catch (SecurityException securityException) {
            securityException.printStackTrace();
        }
        if (constructor == null) {
            throw new IOException("Failed to open source zip with unicode encoding, and no method for ISO-8859-1");
        }
        Object[] objectArray = new Object[]{string, charset};
        try {
            return (ZipFile)constructor.newInstance(objectArray);
        }
        catch (InstantiationException instantiationException) {
            throw new IOException(instantiationException);
        }
        catch (IllegalAccessException illegalAccessException) {
            throw new IOException(illegalAccessException);
        }
        catch (InvocationTargetException invocationTargetException) {
            throw new IOException(invocationTargetException);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new IOException(illegalArgumentException);
        }
    }

    public void b() {
        double d;
        String[] stringArray;
        long l2 = br.a();
        ArrayList<String> arrayList = new ArrayList<String>();
        Enumeration<? extends ZipEntry> enumeration = this.c.entries();
        while (enumeration.hasMoreElements()) {
            stringArray = enumeration.nextElement();
            String string = stringArray.getName();
            if (string == null) {
                throw new RuntimeException("filePath==null");
            }
            arrayList.add(string);
        }
        this.d = arrayList.toArray(new String[0]);
        this.a = "";
        stringArray = this.e("");
        if (stringArray.length == 1 && this.d(stringArray[0])) {
            this.a = stringArray[0] + "/";
            for (int i = 0; i < this.d.length; ++i) {
                if (!this.d[i].startsWith(this.a)) continue;
                this.d[i] = this.d[i].substring(this.a.length());
            }
        }
        if ((d = (double)br.a(l2)) > 3.0) {
            l.e("zip: buildCache for: " + this.b + ", took:" + br.a(d));
        }
    }

    public void a(String string) {
        l.e("Zip: " + string);
    }

    public boolean b(String string) {
        for (String string2 : this.d) {
            if (!string2.equals(string)) continue;
            return true;
        }
        return false;
    }

    public boolean c(String string) {
        for (String string2 : this.d) {
            if (!string2.equals(string)) continue;
            return true;
        }
        for (String string2 : this.d) {
            if (!string2.equalsIgnoreCase(string)) continue;
            return true;
        }
        return false;
    }

    public boolean d(String string) {
        if (!string.endsWith("/")) {
            string = string + "/";
        }
        if (string.equals("/")) {
            return true;
        }
        for (String string2 : this.d) {
            if (!string2.contains(string)) continue;
            return true;
        }
        return false;
    }

    public String[] e(String string) {
        if (string.equals("") || string.equals("/") || string.equals("\\")) {
            string = "";
        } else if (!string.endsWith("/")) {
            string = string + "/";
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : this.d) {
            String string3;
            if (!string.equals("") && !string2.startsWith(string) || (string3 = string2.substring(string.length())).length() == 0 || string3.equals("..")) continue;
            if (string3.contains("/")) {
                if (arrayList.contains(string3 = string3.substring(0, string3.indexOf("/")))) continue;
                arrayList.add(string3);
                continue;
            }
            arrayList.add(string3);
        }
        return arrayList.toArray(new String[0]);
    }

    public ZipEntry f(String string) {
        String string2 = this.a + string;
        ZipEntry zipEntry = null;
        IllegalArgumentException illegalArgumentException = null;
        try {
            zipEntry = this.c.getEntry(string2);
        }
        catch (IllegalArgumentException illegalArgumentException2) {
            illegalArgumentException = illegalArgumentException2;
        }
        if (zipEntry == null && this.b(string) && !this.d(string)) {
            Enumeration<? extends ZipEntry> enumeration = this.c.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry2;
                try {
                    zipEntry2 = enumeration.nextElement();
                }
                catch (IllegalArgumentException illegalArgumentException3) {
                    illegalArgumentException3.printStackTrace();
                    continue;
                }
                String string3 = zipEntry2.getName();
                if (!string3.equals(string2)) continue;
                return zipEntry2;
            }
            this.a("getEntry: Still did not find file after workaround");
        }
        if (illegalArgumentException != null) {
            throw new RuntimeException("Failed to decode data in zip: " + string + " (Check zip encoding, utf-8 is recommended)", illegalArgumentException);
        }
        return zipEntry;
    }

    public String g(String string) {
        String string2 = string;
        if (!string2.endsWith("/")) {
            string2 = string2 + "/";
        }
        for (String string3 : this.d) {
            if (!string3.equals(string)) continue;
            return string3;
        }
        for (String string3 : this.d) {
            if (!string3.equals(string2)) continue;
            return string3;
        }
        for (String string3 : this.d) {
            if (!string3.equalsIgnoreCase(string)) continue;
            return string3;
        }
        for (String string3 : this.d) {
            if (!string3.equalsIgnoreCase(string2)) continue;
            return string3;
        }
        return string;
    }

    public long h(String string) {
        ZipEntry zipEntry = this.f(string);
        if (zipEntry == null) {
            this.a("getEntrySize: File not found: " + string);
            return -1L;
        }
        return zipEntry.getSize();
    }

    public j i(String string) {
        j j2;
        InputStream inputStream;
        ZipEntry zipEntry = this.f(string);
        if (zipEntry == null) {
            zipEntry = this.f(this.g(string));
        }
        if (zipEntry == null) {
            return null;
        }
        try {
            inputStream = this.c.getInputStream(zipEntry);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
        try {
            j2 = new j(inputStream, this.b + "/" + string);
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }
        return j2;
    }
}
