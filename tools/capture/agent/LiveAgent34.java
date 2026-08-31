import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/** 实时 AI 采样 agent v34 — 开局 ais=1 (gui 注入修复) + aiDifficulty=1. */
public class LiveAgent34 {
    public static void agentmain(String args, Instrumentation inst) {
        try {
            String map = (args != null && !args.trim().isEmpty())
                    ? args.trim() : "maps/skirmish/[p2]Beach landing (2p) [by hxyy].tmx";
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Field qf = null;
            for (Field f : se.getClass().getDeclaredFields()) {
                if (List.class.isAssignableFrom(f.getType())) { qf = f; break; }
            }
            qf.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Object> q = (List<Object>) qf.get(se);
            Class<?> ra = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction");
            Constructor<?> c = ra.getDeclaredConstructor(Runnable.class);
            c.setAccessible(true);
            AISandboxTask task = new AISandboxTask();
            task.map = map;
            task.gui = gui;
            task.libB = libB;
            q.add(c.newInstance(task));
            System.out.println("[LIVE34] 开局任务已投递 (ais=1, gui 注入)");
        } catch (Throwable t) { t.printStackTrace(); }
    }
}
