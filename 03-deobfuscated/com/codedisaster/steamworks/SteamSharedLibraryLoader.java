/*
 * Decompiled with CFR 0.152.
 */
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

    private SteamSharedLibraryLoader(String string) {
        this.libraryPath = string;
    }

    private String getLibNameWindows(String string, boolean bl) {
        return string + (bl ? "64" : "") + ".dll";
    }

    private String getLibNameLinux(String string, boolean bl) {
        return "lib" + string + (bl ? "64" : "") + ".so";
    }

    private String getLibNameMac(String string) {
        return "lib" + string + ".dylib";
    }

    private void loadLibraries(String ... stringArray) throws IOException {
        String string = System.getProperty("os.name");
        String string2 = System.getProperty("os.arch");
        boolean bl = string.contains("Windows");
        boolean bl2 = string.contains("Linux");
        boolean bl3 = string.contains("Mac");
        boolean bl4 = string2.equals("amd64") || string2.equals("x86_64");
        String[] stringArray2 = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            if (bl) {
                stringArray2[i] = this.getLibNameWindows(stringArray[i], bl4);
                continue;
            }
            if (bl2) {
                stringArray2[i] = this.getLibNameLinux(stringArray[i], bl4);
                continue;
            }
            if (bl3) {
                stringArray2[i] = this.getLibNameMac(stringArray[i]);
                continue;
            }
            throw new IOException("Unrecognized system architecture: " + string + ", " + string2);
        }
        if (this.libraryPath == null) {
            String string3 = ".nohash";
            CRC32 cRC32 = new CRC32();
            for (String string4 : stringArray2) {
                string3 = this.crc(cRC32, this.getClass().getResourceAsStream("/" + string4));
            }
            librarySystemPath = SteamSharedLibraryLoader.discoverExtractLocation(extractSubFolder + string3, UUID.randomUUID().toString());
            System.out.println("steam librarySystemPath name:" + librarySystemPath);
            if (librarySystemPath == null) {
                throw new IOException("Failed to create temp folder to extract native libraries");
            }
            librarySystemPath = librarySystemPath.getParentFile();
        } else {
            librarySystemPath = new File(this.libraryPath);
        }
        for (String string5 : stringArray2) {
            String string4;
            String string6 = this.libraryPath == null ? this.extractLibrary(librarySystemPath, string5) : librarySystemPath + "/" + string5;
            string4 = new File(string6).getCanonicalPath();
            System.load(string4);
        }
    }

    private String extractLibrary(File file, String string) throws IOException {
        InputStream inputStream;
        ZipFile zipFile;
        File file2;
        block7: {
            Object object;
            file2 = new File(file, string);
            zipFile = null;
            if (this.libraryPath != null) {
                System.out.println("steam extractLibrary zip:" + string);
                zipFile = new ZipFile(this.libraryPath);
                object = zipFile.getEntry(string);
                inputStream = zipFile.getInputStream((ZipEntry)object);
            } else {
                System.out.println("steam extractLibrary name:" + string);
                inputStream = SteamSharedLibraryLoader.class.getResourceAsStream("/" + string);
            }
            if (inputStream == null) {
                throw new IOException("Error extracting " + string + " from " + (this.libraryPath != null ? this.libraryPath : "resources"));
            }
            try {
                int n;
                object = new FileOutputStream(file2);
                byte[] byArray = new byte[4096];
                while ((n = inputStream.read(byArray)) != -1) {
                    ((FileOutputStream)object).write(byArray, 0, n);
                }
                ((FileOutputStream)object).close();
            }
            catch (IOException iOException) {
                if (file2.exists()) break block7;
                throw iOException;
            }
        }
        inputStream.close();
        if (zipFile != null) {
            zipFile.close();
        }
        return file2.getAbsolutePath();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String crc(CRC32 cRC32, InputStream inputStream) {
        byte[] byArray = new byte[4096];
        try {
            int n;
            while ((n = inputStream.read(byArray)) != -1) {
                cRC32.update(byArray, 0, n);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException iOException) {}
        }
        return Long.toHexString(cRC32.getValue());
    }

    static boolean loadLibraries(String string) throws SteamException {
        if (alreadyLoaded) {
            return true;
        }
        SteamSharedLibraryLoader steamSharedLibraryLoader = new SteamSharedLibraryLoader(string);
        try {
            steamSharedLibraryLoader.loadLibraries("steam_api", "steamworks4j");
        }
        catch (Throwable throwable) {
            throw new SteamException(throwable);
        }
        alreadyLoaded = true;
        return true;
    }

    private static File discoverExtractLocation(String string, String string2) {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + string, string2);
        if (SteamSharedLibraryLoader.canWrite(file)) {
            return file;
        }
        try {
            File file2 = File.createTempFile(string, null);
            if (file2.delete() && SteamSharedLibraryLoader.canWrite(file = new File(file2, string2))) {
                return file;
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        file = new File(System.getProperty("user.home") + "/." + string, string2);
        if (SteamSharedLibraryLoader.canWrite(file)) {
            return file;
        }
        file = new File(".tmp/" + string, string2);
        if (SteamSharedLibraryLoader.canWrite(file)) {
            return file;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean canWrite(File file) {
        File file2 = file.getParentFile();
        if (file.exists()) {
            if (!file.canWrite() || !SteamSharedLibraryLoader.canExecute(file)) {
                return false;
            }
        } else {
            if (!file2.exists() && !file2.mkdirs()) {
                return false;
            }
            if (!file2.isDirectory()) {
                return false;
            }
        }
        File file3 = new File(file2, UUID.randomUUID().toString());
        try {
            new FileOutputStream(file3).close();
            boolean bl = SteamSharedLibraryLoader.canExecute(file3);
            return bl;
        }
        catch (IOException iOException) {
            boolean bl = false;
            return bl;
        }
        finally {
            file3.delete();
        }
    }

    private static boolean canExecute(File file) {
        try {
            if (file.canExecute()) {
                return true;
            }
            if (file.setExecutable(true)) {
                return file.canExecute();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }
}
