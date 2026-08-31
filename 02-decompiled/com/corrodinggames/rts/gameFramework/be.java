/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.bf;
import com.corrodinggames.rts.gameFramework.l;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public strictfp class be {
    public boolean a = false;
    String b = "rtsSave";
    String c = "rtsSave.bak";
    public boolean d = false;
    static be e = null;
    HashMap f = new HashMap();

    public void a(Context context) {
        OutputStream outputStream;
        Object object;
        if (l.aU) {
            return;
        }
        if (this.a) {
            return;
        }
        try {
            if (context == null) {
                throw new IOException("context==null");
            }
            try {
                object = context.b(this.b, 0);
            }
            catch (NullPointerException nullPointerException) {
                throw new IOException("openFileOutput NullPointerException", nullPointerException);
            }
            outputStream = new DataOutputStream((OutputStream)object);
            this.a((DataOutputStream)outputStream);
            ((FilterOutputStream)outputStream).close();
            ((FileOutputStream)object).close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            Log.b("RustedWarfare", "file save error:", fileNotFoundException);
        }
        catch (IOException iOException) {
            Log.b("RustedWarfare", "file save error:", iOException);
        }
        if (this.d) {
            try {
                object = new File(Environment.getExternalStorageDirectory() + "/" + this.c);
                outputStream = new FileOutputStream((File)object);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                this.a(dataOutputStream);
                dataOutputStream.close();
                ((FileOutputStream)outputStream).close();
            }
            catch (IOException iOException) {
                Log.b("RustedWarfare", "file read error:", iOException);
            }
        }
    }

    public boolean a(DataOutputStream dataOutputStream) {
        if (l.aU) {
            return false;
        }
        try {
            dataOutputStream.writeInt(1);
            dataOutputStream.writeInt(0);
            dataOutputStream.writeInt(this.f.size());
            for (bf bf2 : this.f.values()) {
                dataOutputStream.writeInt(0);
                dataOutputStream.writeInt(bf2.a);
                dataOutputStream.writeUTF(bf2.b);
                dataOutputStream.writeInt(bf2.c);
                dataOutputStream.writeBoolean(bf2.d);
                dataOutputStream.writeBoolean(bf2.e);
                dataOutputStream.writeBoolean(bf2.f);
                dataOutputStream.writeLong(bf2.g);
                dataOutputStream.writeInt(bf2.h);
            }
            dataOutputStream.flush();
            return true;
        }
        catch (IOException iOException) {
            Log.b("RustedWarfare", "file save error:", iOException);
            return false;
        }
    }

    public void b(Context context) {
        InputStream inputStream;
        Object object;
        if (this.a) {
            return;
        }
        boolean bl = false;
        Log.d("RustedWarfare", "Trying to load from internal memory");
        try {
            object = context.a(this.b);
            inputStream = new DataInputStream((InputStream)object);
            bl = this.a((DataInputStream)inputStream);
            if (bl) {
                Log.d("RustedWarfare", "loaded from internal memory");
            }
            ((FileInputStream)object).close();
        }
        catch (IOException iOException) {
            Log.b("RustedWarfare", "file read error:", iOException);
        }
        if (this.d && !bl) {
            Log.d("RustedWarfare", "Trying to load from SD");
            try {
                object = new File(Environment.getExternalStorageDirectory() + "/" + this.c);
                inputStream = new FileInputStream((File)object);
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                bl = this.a(dataInputStream);
                if (bl) {
                    Log.d("RustedWarfare", "loaded from SD");
                }
                dataInputStream.close();
                ((FileInputStream)inputStream).close();
            }
            catch (IOException iOException) {
                Log.b("RustedWarfare", "file read error:", iOException);
            }
        }
    }

    public boolean a(DataInputStream dataInputStream) {
        try {
            int n2 = dataInputStream.readInt();
            if (n2 > 1) {
                Log.d("RustedWarfare", "Warning file is at version:" + n2);
                return false;
            }
            dataInputStream.readInt();
            int n3 = dataInputStream.readInt();
            this.f.clear();
            for (int j = 0; j < n3; ++j) {
                bf bf2 = new bf(this);
                dataInputStream.readInt();
                bf2.a = dataInputStream.readInt();
                String string = dataInputStream.readUTF();
                if (string.equals("maps/challenge/l030;Level 5.tmx")) {
                    Log.d("RustedWarfare", "converting:" + string);
                    string = "maps/challenge/l090;Level 7.tmx";
                }
                bf2.b = string;
                bf2.c = dataInputStream.readInt();
                bf2.d = dataInputStream.readBoolean();
                bf2.e = dataInputStream.readBoolean();
                bf2.f = dataInputStream.readBoolean();
                bf2.g = dataInputStream.readLong();
                bf2.h = dataInputStream.readInt();
                this.f.put(this.a(bf2.b), bf2);
            }
            return true;
        }
        catch (IOException iOException) {
            Log.b("RustedWarfare", "file read error:", iOException);
            return false;
        }
    }

    public static be c(Context context) {
        if (e == null) {
            e = new be(context);
            if (!l.aU) {
                e.b(context);
            }
        }
        return e;
    }

    private be(Context context) {
    }

    public String a(String string) {
        Integer n2 = l.l(string);
        if (n2 != null) {
            return l.j(string) + "/l" + n2;
        }
        return string;
    }

    public bf b(String string) {
        String string2 = this.a(string);
        bf bf2 = (bf)this.f.get(string2);
        Log.d("RustedWarfare", "StateEngine: get(" + string + ")=" + bf2 + "  (key=" + string2 + ")");
        if (bf2 == null) {
            bf2 = new bf(this);
            bf2.a = 1;
            bf2.b = string;
            this.f.put(string2, bf2);
        }
        return bf2;
    }
}
