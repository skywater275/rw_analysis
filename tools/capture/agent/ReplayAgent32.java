import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/** 回放 AI 采样 agent v4 — 仅投递任务 (loadReplay 在任务内游戏线程执行). */
public class ReplayAgent32 {
    public static void agentmain(String args, Instrumentation inst) {
        try {
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
            q.add(c.newInstance(new ReplaySampleTask5()));
            System.out.println("[RPL32] 采样任务已投递");
        } catch (Throwable t) { t.printStackTrace(); }
    }
}
