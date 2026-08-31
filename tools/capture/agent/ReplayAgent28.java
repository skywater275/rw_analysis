import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/** 回放 AI 捕捉 agent — 加载回放 + 3x 加速 + 启动采样任务. */
public class ReplayAgent28 {
    public static void agentmain(String args, Instrumentation inst) {
        try {
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Object root = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine")
                    .getMethod("getRoot").invoke(se);
            // 1. 加载回放 (agent 通道 — 避免 script 空格分词问题)
            try {
                root.getClass().getMethod("loadReplay", String.class).invoke(
                        root, "Crossing Large (10p) [v1.15] (10 2月 2026 21.49.55).replay");
                System.out.println("[RPL28] 回放已加载");
            } catch (Throwable t) {
                System.out.println("[RPL28] loadReplay 失败:"); t.printStackTrace(System.out);
            }
            // 2. 投递采样任务
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
            q.add(c.newInstance(new ReplaySampleTask()));
            System.out.println("[RPL28] 采样任务已投递");
        } catch (Throwable t) { t.printStackTrace(); }
    }
}
