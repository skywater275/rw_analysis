/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 *  org.newdawn.slick.AppGameContainer
 *  org.newdawn.slick.Game
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Music
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.DisplayMessagePump;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameWindow
extends AppGameContainer {
    boolean a = false;
    Object b = new Object();
    DisplayMessagePump c;

    public GameWindow(Game game, int n, int n2, boolean bl) throws SlickException {
        super(game, n, n2, bl);
    }

    public Graphics a() {
        Graphics graphics = this.getGraphics();
        this.input.poll(this.width, this.height);
        Music.poll((int)1);
        if (MusicController.a != null) {
            MusicController.a.a(1);
        }
        GL.glClear(16640);
        GL.glLoadIdentity();
        graphics.resetTransform();
        graphics.resetFont();
        graphics.resetLineWidth();
        graphics.setAntiAlias(false);
        return graphics;
    }

    public void a(Graphics graphics) {
        graphics.resetTransform();
        GL.flush();
        Display.update();
    }

    protected void gameLoop() throws SlickException {
        int n = this.getDelta();
        if (!Display.isVisible() && this.updateOnlyOnVisible) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception exception) {}
        } else {
            this.updateAndRender(n);
        }
        this.updateFPS();
        Display.update((boolean)false);
        if (!this.a) {
            Display.processMessages();
        } else if (this.c == null) {
            this.c = new DisplayMessagePump(this);
            this.c.start();
        }
        if (Display.isCloseRequested() && this.game.closeRequested()) {
            this.running = false;
        }
    }

    protected void updateAndRender(int n) throws SlickException {
        if (this.smoothDeltas && this.getFPS() != 0) {
            n = 1000 / this.getFPS();
        }
        this.input.poll(this.width, this.height);
        Music.poll((int)n);
        if (MusicController.a != null) {
            MusicController.a.a(n);
        }
        if (!this.paused) {
            this.storedDelta += (long)n;
            if (this.storedDelta >= this.minimumLogicInterval) {
                if (this.maximumLogicInterval != 0L) {
                    long l = this.storedDelta / this.maximumLogicInterval;
                    int n2 = 0;
                    while ((long)n2 < l) {
                        this.game.update((GameContainer)this, (int)this.maximumLogicInterval);
                        ++n2;
                    }
                    n2 = (int)(this.storedDelta % this.maximumLogicInterval);
                    if ((long)n2 > this.minimumLogicInterval) {
                        this.game.update((GameContainer)this, (int)((long)n2 % this.maximumLogicInterval));
                        this.storedDelta = 0L;
                    } else {
                        this.storedDelta = n2;
                    }
                } else {
                    this.game.update((GameContainer)this, (int)this.storedDelta);
                    this.storedDelta = 0L;
                }
            }
        } else {
            this.game.update((GameContainer)this, 0);
        }
        if (this.hasFocus() || this.getAlwaysRender()) {
            if (this.clearEachFrame) {
                GL.glClear(16640);
            }
            GL.glLoadIdentity();
            Graphics graphics = this.getGraphics();
            graphics.resetTransform();
            graphics.resetFont();
            graphics.resetLineWidth();
            graphics.setAntiAlias(false);
            this.game.render((GameContainer)this, graphics);
            graphics.resetTransform();
            if (this.isShowingFPS()) {
                this.getDefaultFont().drawString(10.0f, 10.0f, "FPS: " + this.recordedFPS);
            }
            GL.flush();
        }
        if (this.targetFPS != -1) {
            Display.sync((int)this.targetFPS);
        }
    }

    public void destroy() {
        try {
            Display.destroy();
        }
        catch (Exception exception) {
            GlobalState.a("Error on Display.destroy in destroy", (Throwable)exception);
        }
    }
}
