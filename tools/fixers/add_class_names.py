#!/usr/bin/env python3
"""Phase C: 批量添加类名映射到 class-discoveries.csv"""

import sys, csv
from pathlib import Path
ROOT = Path(__file__).resolve().parents[3]  # tools/fixers/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import CLASS_DISCOVERIES

mappings = [
    # audio backend
    ("com.corrodinggames.rts.java.audio.backend", "a", "AudioSource", "音频源基类"),
    ("com.corrodinggames.rts.java.audio.backend", "b", "AudioClip", "音频片段资源"),
    ("com.corrodinggames.rts.java.audio.backend", "c", "AudioBuffer", "音频缓冲区"),
    ("com.corrodinggames.rts.java.audio.backend", "d", "AudioDecoder", "音频解码器"),
    ("com.corrodinggames.rts.java.audio.backend", "e", "AudioManager", "音频系统管理器"),
    ("com.corrodinggames.rts.java.audio.backend", "f", "SoundEffect", "音效播放器"),
    ("com.corrodinggames.rts.java.audio.backend", "g", "MusicPlayer", "音乐流播放器"),
    ("com.corrodinggames.rts.java.audio.backend", "h", "AudioMixer", "音频混音引擎"),
    ("com.corrodinggames.rts.java.audio.backend", "i", "AudioListener", "3D音频监听器"),
    ("com.corrodinggames.rts.java.audio.backend", "j", "AudioEmitter", "3D音频发射器"),
    ("com.corrodinggames.rts.java.audio.backend", "k", "AudioFilter", "音频DSP过滤器"),
    ("com.corrodinggames.rts.java.audio.backend", "l", "AudioCodec", "音频编解码器"),
    ("com.corrodinggames.rts.java.audio.backend", "m", "AudioDevice", "音频硬件设备"),
    ("com.corrodinggames.rts.java.audio.backend", "n", "AudioContext", "OpenAL上下文"),
    ("com.corrodinggames.rts.java.audio.backend", "o", "AudioCapture", "麦克风采集"),
    ("com.corrodinggames.rts.java.audio.backend", "p", "AudioStream", "流式音频源"),
    ("com.corrodinggames.rts.java.audio.backend", "q", "AudioSynthesizer", "过程化音频合成"),
    ("com.corrodinggames.rts.java.audio.backend", "r", "AudioTrack", "音频轨道句柄"),
    ("com.corrodinggames.rts.java.audio.backend", "s", "AudioUtils", "音频工具函数"),

    # custom actions base
    ("com.corrodinggames.rts.game.units.custom.actions.base", "b", "ActionCondition", "动作条件求值器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "c", "ActionEffect", "动作效果应用器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "d", "ActionValidator", "动作参数验证器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "e", "ActionCost", "动作资源消耗"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "f", "ActionTarget", "动作目标选择器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "g", "ActionCooldown", "动作冷却计时器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "h", "ActionSequence", "动作链序列器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "i", "ActionModifier", "动作属性修改器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "j", "ActionTrigger", "动作自动触发器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "k", "ActionSound", "动作音效"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "l", "ActionParticle", "动作粒子效果"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "m", "ActionAnimation", "动作动画播放器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "n", "ActionProjectile", "动作弹体生成器"),
    ("com.corrodinggames.rts.game.units.custom.actions.base", "o", "ActionTransform", "动作单位变形"),

    # custom animation
    ("com.corrodinggames.rts.game.units.custom.animation", "b", "CurvePoint", "动画曲线关键帧"),
    ("com.corrodinggames.rts.game.units.custom.animation", "c", "CurveInterpolator", "曲线插值引擎"),
    ("com.corrodinggames.rts.game.units.custom.animation", "d", "CurveTimeline", "曲线时间轴"),
    ("com.corrodinggames.rts.game.units.custom.animation", "e", "CurveAnimator", "曲线驱动动画器"),
    ("com.corrodinggames.rts.game.units.custom.animation", "f", "SpriteAnimConfig", "精灵动画配置"),
    ("com.corrodinggames.rts.game.units.custom.animation", "g", "SpriteAnimPlayer", "精灵动画播放器"),
    ("com.corrodinggames.rts.game.units.custom.animation", "h", "FrameSequence", "动画帧序列"),
    ("com.corrodinggames.rts.game.units.custom.animation", "i", "AnimEventListener", "动画事件回调"),
    ("com.corrodinggames.rts.game.units.custom.animation", "j", "AnimBlendState", "动画混合状态"),
    ("com.corrodinggames.rts.game.units.custom.animation", "k", "AnimTransition", "动画状态转换"),
    ("com.corrodinggames.rts.game.units.custom.animation", "l", "AnimStateMachine", "动画状态机"),
    ("com.corrodinggames.rts.game.units.custom.animation", "m", "AnimController", "顶层动画控制器"),
    ("com.corrodinggames.rts.game.units.custom.animation", "n", "AnimPose", "骨骼动画姿态"),

    # UI panels
    ("com.corrodinggames.rts.gameFramework.ui.panels", "b", "HUDOverlay", "HUD状态覆盖层"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "c", "BuildMenuPanel", "建造菜单面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "d", "DiplomacyPanel", "外交/队伍面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "e", "SettingsPanel", "设置配置面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "f", "SaveLoadPanel", "存档读档面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "g", "MapInfoPanel", "地图信息面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "i", "ReplayPanel", "回放控制面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "m", "StatsPanel", "游戏统计面板"),
    ("com.corrodinggames.rts.gameFramework.ui.panels", "o", "ChatInputPanel", "聊天输入覆盖层"),

    # input system
    ("com.corrodinggames.rts.java.input", "a", "InputManager", "输入设备管理器"),
    ("com.corrodinggames.rts.java.input", "c", "KeyboardInput", "键盘输入处理器"),
    ("com.corrodinggames.rts.java.input", "d", "MouseInput", "鼠标输入处理器"),
    ("com.corrodinggames.rts.java.input", "e", "TouchInput", "触摸输入处理器"),
    ("com.corrodinggames.rts.java.input", "f", "GamepadInput", "手柄输入处理器"),
    ("com.corrodinggames.rts.java.input", "g", "InputMapper", "输入绑定映射器"),
    ("com.corrodinggames.rts.java.input", "h", "GestureDetector", "触摸手势识别器"),
    ("com.corrodinggames.rts.java.input", "i", "InputEvent", "输入事件数据对象"),
    ("com.corrodinggames.rts.java.input", "j", "InputState", "当前输入状态快照"),
    ("com.corrodinggames.rts.java.input", "k", "InputConfig", "输入配置加载器"),
    ("com.corrodinggames.rts.java.input", "l", "InputAxis", "模拟输入轴处理器"),

    # java root
    ("com.corrodinggames.rts.java", "g", "GameLauncher", "游戏入口启动器"),
    ("com.corrodinggames.rts.java", "h", "DesktopWindow", "桌面窗口处理器"),
    ("com.corrodinggames.rts.java", "k", "GameConfig", "游戏配置加载器"),
    ("com.corrodinggames.rts.java", "l", "ResourceLoader", "资源加载器"),
    ("com.corrodinggames.rts.java", "m", "CrashHandler", "崩溃异常处理器"),
    ("com.corrodinggames.rts.java", "o", "UpdateChecker", "版本更新检查器"),
    ("com.corrodinggames.rts.java", "p", "LicenseValidator", "许可证验证器"),
    ("com.corrodinggames.rts.java", "q", "PerformanceMonitor", "性能监控器"),
    ("com.corrodinggames.rts.java", "r", "ScreenshotCapture", "截图捕获工具"),
    ("com.corrodinggames.rts.java", "v", "ModDownloader", "Mod下载安装器"),

    # commands/slots
    ("com.corrodinggames.rts.game.units.commands.slots", "d", "ActionSlotBase", "动作槽位基类"),
    ("com.corrodinggames.rts.game.units.commands.slots", "e", "ProduceSlot", "生产队列槽位"),
    ("com.corrodinggames.rts.game.units.commands.slots", "f", "UpgradeSlot", "升级动作槽位"),
    ("com.corrodinggames.rts.game.units.commands.slots", "g", "RepairSlot", "维修动作槽位"),
    ("com.corrodinggames.rts.game.units.commands.slots", "h", "SellSlot", "出售动作槽位"),
    ("com.corrodinggames.rts.game.units.commands.slots", "i", "TransportSlot", "运输动作槽位"),

    # platform
    ("com.corrodinggames.rts.platform", "a", "PlatformBridge", "平台桥接抽象层"),
    ("com.corrodinggames.rts.platform", "b", "AndroidPlatform", "Android平台实现"),
    ("com.corrodinggames.rts.platform", "c", "DesktopPlatform", "桌面平台实现"),
    ("com.corrodinggames.rts.platform.net", "g", "NetPacket", "网络数据包"),
    ("com.corrodinggames.rts.platform.net", "h", "NetConnection", "网络连接"),
    ("com.corrodinggames.rts.platform.net", "i", "NetListener", "网络事件监听器"),
    ("com.corrodinggames.rts.platform.net", "j", "NetConfig", "网络配置"),

    # custom packages
    ("com.corrodinggames.rts.game.units.custom", "w", "CustomAnimator", "自定义单位动画器"),
    ("com.corrodinggames.rts.game.units.custom", "x", "CustomPhysics", "自定义单位物理"),
    ("com.corrodinggames.rts.game.units.custom", "y", "CustomSounds", "自定义单位音效"),
    ("com.corrodinggames.rts.game.units.custom", "z", "CustomVisuals", "自定义单位视觉"),
    ("com.corrodinggames.rts.game.units.custom.actions", "f", "ActionRegistry", "动作注册表"),
    ("com.corrodinggames.rts.game.units.custom.actions", "g", "ActionFactory", "动作工厂"),
    ("com.corrodinggames.rts.game.units.custom.conditions", "f", "ConditionEvaluator", "条件求值器"),
    ("com.corrodinggames.rts.game.units.custom.effects", "f", "EffectRenderer", "效果渲染器"),
    ("com.corrodinggames.rts.game.units.custom.effects.config", "f", "EffectConfigLoader", "效果配置加载器"),
    ("com.corrodinggames.rts.game.units.custom.resources", "c", "ResourceStorage", "资源存储器"),
    ("com.corrodinggames.rts.game.units.custom.resources", "d", "ResourceFlow", "资源流动控制器"),
    ("com.corrodinggames.rts.game.units.custom.config", "f", "UnitConfigLoader", "单位配置加载器"),
]

def main():
    existing = set()
    try:
        with open(CLASS_DISCOVERIES, "r", encoding="utf-8") as f:
            for r in csv.DictReader(f):
                existing.add(f"{r['obfuscated_package']}.{r['obfuscated_class']}")
    except:
        pass

    added = 0
    with open(CLASS_DISCOVERIES, "a", encoding="utf-8", newline="") as f:
        writer = csv.writer(f)
        for pkg, cls, name, notes in mappings:
            fqn = f"{pkg}.{cls}"
            if fqn not in existing:
                writer.writerow(["class", pkg, cls, name, f"Phase C: {notes}"])
                added += 1

    print(f"新增类映射: {added} (共 {len(mappings)} 候选, {len(mappings)-added} 已存在)")

if __name__ == "__main__":
    main()
