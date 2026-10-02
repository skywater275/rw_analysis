import java.lang.reflect.Field;
import java.util.List;

/** GameServerInfo 全字段 dump — 服务器列表所有属性 (名字/地图/状态/人数). */
public class DumpTask implements Runnable {
    public void run() {
        try {
            Class<?> libA = Class.forName("com.corrodinggames.librocket.a");
            Object gui = libA.getMethod("a").invoke(null);
            Object libB = gui.getClass().getField("b").get(gui);
            Object se = libB.getClass().getField("c").get(libB);
            Object root = Class.forName("com.corrodinggames.librocket.scripts.ScriptEngine")
                    .getMethod("getRoot").invoke(se);
            Field lf = root.getClass().getDeclaredField("lastSortedDiscoveredServers");
            lf.setAccessible(true);
            List<?> servers = (List<?>) lf.get(root);
            System.out.println("[DUMP] 服务器总数: " + (servers == null ? 0 : servers.size()));
            if (servers == null) return;
            Class<?> gCls = Class.forName("com.corrodinggames.rts.gameFramework.j.g");
            String[] fnames = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","A"};
            int n = 0;
            for (Object s : servers) {
                StringBuilder sb = new StringBuilder("[DUMP] #" + (n++) + " ");
                for (String fn : fnames) {
                    try {
                        Field f = gCls.getDeclaredField(fn);
                        f.setAccessible(true);
                        Object v = f.get(s);
                        String vs = String.valueOf(v);
                        if (vs.length() > 40) vs = vs.substring(0, 40) + "..";
                        sb.append(fn).append("=").append(vs).append(" | ");
                    } catch (Throwable t) {
                        sb.append(fn).append("=ERR | ");
                    }
                }
                System.out.println(sb);
                if (n >= 14) { System.out.println("[DUMP] ... (前 14 个)"); break; }
            }
        } catch (Throwable t) {
            System.out.println("[DUMP] 失败:"); t.printStackTrace(System.out);
        }
    }
}
