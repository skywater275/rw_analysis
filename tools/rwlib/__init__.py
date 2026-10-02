"""
rwlib — Rusted Warfare 源码逆向共享库

提供项目级路径配置、映射数据库操作、字节码验证和 Debug Socket 协议客户端。

模块:
    rwlib.config      — 项目路径和 JDK 工具查找
    rwlib.mappings    — supplement.csv 读写和类映射
    rwlib.bytecode    — javap 调用和 .class 文件操作
    rwlib.debugproto  — Debug Socket (5677) 协议底座: verb→终止符表 + send_command (D-9)

使用:
    import sys
    sys.path.insert(0, str(Path(__file__).resolve().parent.parent))
    from rwlib.config import ROOT, find_javap
    from rwlib.mappings import load_supplement, append_mappings
    from rwlib.bytecode import get_methods, index_class_files
    from rwlib.debugproto import send_command, port_listening
"""

__version__ = "1.2.0"  # 2026-09-21 D-9: 新增 debugproto (Debug Socket 协议底座, 单一真源)
__all__ = [
    'config', 'mappings', 'bytecode', 'debugproto',
]
