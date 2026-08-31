/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.Sys
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 *  org.lwjgl.opengl.GL11
 *  org.newdawn.slick.SlickException
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.GameLauncher;
import java.awt.Toolkit;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;

class LWJGLDisplayThread
implements Runnable {
    final /* synthetic */ GameLauncher a;

    public LWJGLDisplayThread(GameLauncher main) {  // 02b java/j.java 内部类构造 (v19.133f)
        this.a = main;
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();  // 02b l.aq() (v19.133f2 语义名修正)
        try {
            this.a.k.start();
        }
        catch (SlickException slickException) {
            if (!"Failed to initialise the LWJGL display".equals(slickException.getMessage())) {
                throw new RuntimeException(slickException);
            }
            GlobalState.a("Error starting display", (Throwable)slickException);
            String string = "\nFailed to get opengl version";
            try {
                System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
                Display.setDisplayMode((DisplayMode)new DisplayMode(100, 100));
                Display.create();
                String string2 = GL11.glGetString((int)7938);
                Display.destroy();
                GlobalState.e("OpenGL version: " + string2);
                string = "\n";
                string = string + "OpenGL version detected: " + string2;
                if (string2.startsWith("1.0") || string2.startsWith("1.1")) {
                    string = string + "\n---\nOpenGL 1.1 is over 20 years old you might be using a fallback microsoft driver. Try updating your graphics drivers from the manufacturer.";
                }
            }
            catch (Exception exception) {
                GlobalState.a("Failed to get opengl info", (Throwable)exception);
            }
            Toolkit.getDefaultToolkit();
            Sys.alert((String)"Error", (String)("Failed to create display." + string));
            System.exit(1);
        }
        GlobalState.e("Game stopped running shutting down");
        GlobalState l2 = GlobalState.B();
        l2.ca.b("lastgame", false);
        this.a.a(true);
    }
}
