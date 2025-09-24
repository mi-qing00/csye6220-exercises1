#!/bin/bash

# HTTP请求头学习教程 - Tomcat启动脚本

echo "🚀 启动HTTP请求头学习教程..."

# 设置Tomcat路径
TOMCAT_HOME="/Users/kaitl/projects/csye6220/a1/apache-tomcat-9.0.109"
WEBAPP_HOME="$TOMCAT_HOME/webapps/request-headers"

# 检查Tomcat是否存在
if [ ! -d "$TOMCAT_HOME" ]; then
    echo "❌ 错误: Tomcat目录不存在: $TOMCAT_HOME"
    exit 1
fi

# 检查Web应用是否存在
if [ ! -d "$WEBAPP_HOME" ]; then
    echo "❌ 错误: Web应用目录不存在: $WEBAPP_HOME"
    exit 1
fi

# 检查Tomcat是否已经在运行
if pgrep -f "catalina" > /dev/null; then
    echo "⚠️  Tomcat可能已经在运行中"
    echo "请先停止Tomcat或检查端口8080是否被占用"
    exit 1
fi

# 进入Tomcat bin目录
cd "$TOMCAT_HOME/bin"

echo "📁 当前目录: $(pwd)"
echo "🔧 启动Tomcat服务器..."

# 启动Tomcat
./startup.sh

# 等待几秒钟让Tomcat启动
echo "⏳ 等待Tomcat启动..."
sleep 5

# 检查Tomcat是否成功启动
if pgrep -f "catalina" > /dev/null; then
    echo "✅ Tomcat启动成功！"
    echo ""
    echo "🌐 访问地址:"
    echo "   主页: http://localhost:8080/request-headers/"
    echo "   第一章: http://localhost:8080/request-headers/ShowRequestHeaders"
    echo "   第三章: http://localhost:8080/request-headers/LongServlet"
    echo "   第四章: http://localhost:8080/request-headers/BrowserInsult"
    echo "   第五章: http://localhost:8080/request-headers/CustomizeImage"
    echo "   第六章: http://localhost:8080/request-headers/ShowCGIVariables"
    echo ""
    echo "📚 教程特色:"
    echo "   - 6个独立章节模块"
    echo "   - 完整的中文注释和说明"
    echo "   - 实际可运行的代码示例"
    echo "   - 详细的学习指导"
    echo ""
    echo "🛑 停止Tomcat:"
    echo "   cd $TOMCAT_HOME/bin && ./shutdown.sh"
    echo "   或者运行: ./stop-tomcat.sh"
else
    echo "❌ Tomcat启动失败！"
    echo "请检查日志文件: $TOMCAT_HOME/logs/catalina.out"
    exit 1
fi
