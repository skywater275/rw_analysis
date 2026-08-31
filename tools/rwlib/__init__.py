"""
rwlib — Rusted Warfare 源码逆向共享库

提供项目级路径配置、映射数据库操作和字节码验证。

模块:
    rwlib.config    — 项目路径和 JDK 工具查找
    rwlib.mappings  — supplement.csv 读写和类映射
    rwlib.bytecode  — javap 调用和 .class 文件操作

使用:
    import sys
    sys.path.insert(0, str(Path(__file__).resolve().parent.parent))
    from rwlib.config import ROOT, find_javap
    from rwlib.mappings import load_supplement, append_mappings
    from rwlib.bytecode import get_methods, index_class_files
"""

__version__ = "1.1.0"  # v19.133f98: 删除 rwlib.utils (0 引用死代码)
__all__ = [
    'config', 'mappings', 'bytecode',
]
