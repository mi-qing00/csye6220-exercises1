#!/bin/bash

# HTTP请求头学习教程 - Tomcat停止脚本

echo "🛑 停止HTTP请求头学习教程..."

# 设置Tomcat路径
TOMCAT_HOME="/Users/kaitl/projects/csye6220/a1/apache-tomcat-9.0.109"

# 检查Tomcat是否存在
if [ ! -d "$TOMCAT_HOME" ]; then
    echo "❌ 错误: Tomcat目录不存在: $TOMCAT_HOME"
    exit 1
fi

# 检查Tomcat是否在运行
if ! pgrep -f "catalina" > /dev/null; then
    echo "⚠️  Tomcat没有在运行"
    exit 0
fi

# 进入Tomcat bin目录
cd "$TOMCAT_HOME/bin"

echo "📁 当前目录: $(pwd)"
echo "🔧 停止Tomcat服务器..."

# 停止Tomcat
./shutdown.sh

# 等待几秒钟让Tomcat完全停止
echo "⏳ 等待Tomcat停止..."
sleep 3

# 检查Tomcat是否成功停止
if ! pgrep -f "catalina" > /dev/null; then
    echo "✅ Tomcat已成功停止！"
else
    echo "⚠️  Tomcat可能仍在运行，请手动检查"
    echo "可以尝试强制停止: pkill -f catalina"
fi
