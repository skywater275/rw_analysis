import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 游戏线程沙盒启动任务 v2 — 补全 loadConfigAndStartNewSandbox 全序列 (含 UI 切换 3 步). */
public class SandboxTask2 implements Runnable {
    public String map;
    public Object gui;
    public Object libB;

    public void run() {
        try {
            Class<?> lCls = Class.forName("com.corrodinggames.rts.gameFramework.l");
            Object gs = lCls.getMethod("B").invoke(null);
            Object bQ = lCls.getField("bQ").get(gs);
            if (bQ != null) {
                for (Field f : bQ.getClass().getFields()) {
                    if (f.getName().equals("aiDifficulty")) { f.set(bQ, 0); break; }
                }
            }
            gui.getClass().getMethod("b", boolean.class).invoke(gui, true);
            gui.getClass().getMethod("c", boolean.class).invoke(gui, false);
            Class<?> iCls = Class.forName("com.corrodinggames.rts.appFramework.i");
            boolean skirmish = (Boolean) iCls.getMethod("f", String.class).invoke(null, map);
            iCls.getMethod("a", String.class, boolean.class, int.class, int.class, boolean.class, boolean.class)
                    .invoke(null, map, skirmish, 0, 0, true, false);
            lCls.getField("bv").set(gs, true);
            Object bL = lCls.getField("bL").get(gs);
            if (bL != null) { bL.getClass().getField("E").set(bL, false); }
            Object bS = lCls.getField("bS").get(gs);
            if (bS != null) { bS.getClass().getMethod("y").invoke(bS); }
            // v2 补全: guiEngine.f() + closeActiveDocument + clearHistory (02b _loadConfigAndStartNewSandboxCommon 尾段)
            gui.getClass().getMethod("f").invoke(gui);
            libB.getClass().getMethod("closeActiveDocument").invoke(libB);
            libB.getClass().getMethod("clearHistory").invoke(libB);
            System.out.println("[SA7] 沙盒正式开局完成 (bv=true + UI 已切换) — 地图: " + map);
        } catch (Throwable t) {
            System.out.println("[SA7] 游戏线程执行失败:");
            t.printStackTrace();
        }
    }
}
