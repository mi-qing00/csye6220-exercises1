#!/bin/bash

# HTTP请求头学习教程 - 编译脚本
# 用于编译所有Java源文件

echo "开始编译HTTP请求头学习教程..."

# 设置Tomcat路径
TOMCAT_HOME="/Users/kaitl/projects/csye6220/a1/apache-tomcat-9.0.109"
WEBAPP_HOME="$TOMCAT_HOME/webapps/request-headers"

# 检查Tomcat是否存在
if [ ! -d "$TOMCAT_HOME" ]; then
    echo "错误: Tomcat目录不存在: $TOMCAT_HOME"
    exit 1
fi

# 检查Web应用目录是否存在
if [ ! -d "$WEBAPP_HOME" ]; then
    echo "错误: Web应用目录不存在: $WEBAPP_HOME"
    exit 1
fi

# 进入classes目录
cd "$WEBAPP_HOME/WEB-INF/classes"

# 设置classpath，包含Tomcat的servlet API
CLASSPATH="$TOMCAT_HOME/lib/servlet-api.jar:$TOMCAT_HOME/lib/jsp-api.jar:."

echo "使用classpath: $CLASSPATH"

# 编译所有Java文件
echo "编译Java源文件..."
javac -cp "$CLASSPATH" coreservlets/*.java

# 检查编译结果
if [ $? -eq 0 ]; then
    echo "✅ 编译成功！"
    echo "生成的文件："
    ls -la coreservlets/*.class
else
    echo "❌ 编译失败！"
    exit 1
fi

echo ""
echo "🎉 编译完成！"
echo "现在可以启动Tomcat并访问: http://localhost:8080/request-headers/"
echo ""
echo "启动Tomcat命令:"
echo "cd $TOMCAT_HOME/bin && ./startup.sh"
echo ""
echo "停止Tomcat命令:"
echo "cd $TOMCAT_HOME/bin && ./shutdown.sh"
