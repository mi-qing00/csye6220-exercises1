package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * 第一章：基础请求头读取
 * 学习如何读取和处理基本的HTTP请求头
 * 掌握getHeader()、getHeaderNames()等核心API
 * 了解特殊访问方法如getMethod()、getRequestURI()
 * 
 * 功能：
 * - 显示所有接收到的请求头及其值
 * - 显示请求方法、URI和协议信息
 * - 演示如何遍历和分析请求头信息
 */
public class ShowRequestHeaders extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String title = "第一章：基础请求头读取 - 显示所有请求头";
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n";
        
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "<H2>请求基本信息</H2>\n" +
                "<B>请求方法: </B>" + request.getMethod() + "<BR>\n" +
                "<B>请求URI: </B>" + request.getRequestURI() + "<BR>\n" +
                "<B>请求协议: </B>" + request.getProtocol() + "<BR>\n" +
                "<B>查询字符串: </B>" + 
                (request.getQueryString() != null ? request.getQueryString() : "<I>无</I>") + "<BR><BR>\n" +
                "<H2>所有请求头</H2>\n" +
                "<TABLE BORDER=1 ALIGN=\"CENTER\" WIDTH=\"80%\">\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>请求头名称<TH>请求头值");
        
        // ========================================
        // 练习1.1：请求头分类显示 - 学生修改区域
        // ========================================
        // TODO: 在这里实现请求头分类功能
        // 要求：将请求头分为以下几类：
        // - 认证相关：Authorization, Cookie
        // - 内容协商：Accept, Accept-Language, Accept-Encoding
        // - 连接管理：Connection, Keep-Alive
        // - 客户端信息：User-Agent, Host
        // - 其他：Referer, If-Modified-Since等
        //
        // 实现提示：
        // 1. 创建分类映射 Map<String, List<String>> categorizedHeaders = new HashMap<>();
        // 2. 遍历所有请求头，根据名称进行分类
        // 3. 在HTML中按分类显示，而不是简单的表格
        // 4. 为每个分类添加不同的颜色和样式
        
        // 当前代码：简单显示所有请求头（需要替换）
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<TR><TD><B>" + headerName + "</B>");
            out.println("<TD>" + request.getHeader(headerName));
        }
        
        out.println("</TABLE>\n");
        
        // ========================================
        // 练习1.2：请求头统计 - 学生修改区域
        // ========================================
        // TODO: 在这里添加请求头统计信息
        // 要求：
        // 1. 统计请求头总数
        // 2. 统计每个分类的请求头数量
        // 3. 显示最常用的请求头
        // 4. 添加请求头长度统计
        //
        // 实现提示：
        // 1. 在显示分类后添加统计信息表格
        // 2. 计算原始大小和压缩后大小
        // 3. 显示压缩比例和传输时间节省
        // 4. 使用不同的颜色突出显示重要统计信息
        
        // 当前代码：暂无统计信息（需要添加）
        out.println("<H2>📊 练习1.2：请求头统计信息</H2>\n");
        out.println("<P STYLE=\"color: #666; font-style: italic;\">");
        out.println("请完成练习1.2，添加以下统计功能：<BR>");
        out.println("• 请求头总数统计<BR>");
        out.println("• 各分类请求头数量<BR>");
        out.println("• 最常用请求头排行<BR>");
        out.println("• 请求头长度统计<BR>");
        out.println("</P>\n");
        
        out.println("<BR><BR>\n" +
                "<H2>学习要点</H2>\n" +
                "<UL>\n" +
                "<LI><B>getHeader(String name)</B>: 获取指定名称的请求头值</LI>\n" +
                "<LI><B>getHeaderNames()</B>: 获取所有请求头名称的枚举</LI>\n" +
                "<LI><B>getMethod()</B>: 获取HTTP请求方法（GET、POST等）</LI>\n" +
                "<LI><B>getRequestURI()</B>: 获取请求的URI部分</LI>\n" +
                "<LI><B>getProtocol()</B>: 获取HTTP协议版本</LI>\n" +
                "<LI><B>getQueryString()</B>: 获取查询字符串</LI>\n" +
                "</UL>\n" +
                "<P><A HREF=\"../index.html\">返回主页</A></P>\n" +
                "</BODY></HTML>");
    }
    
    /**
     * 为了调试方便，让POST请求和GET请求使用相同的处理方式
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
