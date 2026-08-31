import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 核弹任务 v5 — 静态验证修正: 弹药 d.q.c 声明类精确取 + 唯一发射签名 + 距离>450 目标. */
public class NukeTask6 implements Runnable {
    public float x = 700.0F;
    public float y = 400.0F;
    public float tx = 1600.0F;   // 距离 (700,400)→(1600,1000) = 1081 > 450
    public float ty = 1000.0F;
    public int team = 0;

    public void run() {
        try {
            Class<?> arCls = Class.forName("com.corrodinggames.rts.game.units.ar");
            Class<?> qCls = Class.forName("com.corrodinggames.rts.game.units.d.q");
            Object type = arCls.getField("C").get(null);   // clinit 铁证: C = NukeLaucher (ar$22)
            Object u = type.getClass().getMethod("a").invoke(type);  // 实例化
            Class<?> amCls = Class.forName("com.corrodinggames.rts.game.units.am");
            amCls.getField("eo").setFloat(u, x);
            amCls.getField("ep").setFloat(u, y);
            u.getClass().getMethod("Q", int.class).invoke(u, team);
            Class.forName("com.corrodinggames.rts.game.n").getMethod("c", amCls).invoke(null, u);
            System.out.println("[NUKE6] 发射井已创建+注册 @(" + x + "," + y + ")");
            // 弹药: d.q 声明类精确取 (教训: 沿类链会命中 ar.c 枚举引用!)
            Field ammo = qCls.getDeclaredField("c"); // 非 public: getDeclaredField
            ammo.setAccessible(true);
            int cur = ammo.getInt(u);
            if (cur < 1) { ammo.setInt(u, 1); }
            System.out.println("[NUKE6] 弹药: " + ammo.getInt(u));
            // 发射: 唯一 a(float,float) 实例方法
            Method fire = qCls.getMethod("a", float.class, float.class);
            fire.invoke(u, tx, ty);
            System.out.println("[NUKE6] 核弹弹丸已发射 → (" + tx + "," + ty + ")");
        } catch (Throwable t) {
            System.out.println("[NUKE6] 失败:");
            t.printStackTrace();
        }
    }
}
