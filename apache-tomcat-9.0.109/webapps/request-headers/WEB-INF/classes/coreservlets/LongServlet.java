package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 第三章：页面压缩演示
 * 学习Accept-Encoding和Content-Encoding头的使用
 * 实现页面压缩功能以节省带宽
 * 了解GZIP、DEFLATE、Brotli等压缩格式
 * 
 * 功能：
 * - 检查浏览器是否支持gzip压缩
 * - 对支持gzip的浏览器发送压缩内容
 * - 对不支持gzip的浏览器发送普通内容
 * - 提供性能对比功能
 */
public class LongServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // 根据是否支持gzip来改变"out"的定义
        PrintWriter out;
        boolean isGzipSupported = GzipUtilities.isGzipSupported(request);
        boolean isGzipDisabled = GzipUtilities.isGzipDisabled(request);
        
        if (isGzipSupported && !isGzipDisabled) {
            out = GzipUtilities.getGzipWriter(response);
            response.setHeader("Content-Encoding", "gzip");
        } else {
            out = response.getWriter();
        }
        
        // 一旦"out"被适当赋值，页面的其余部分就不依赖于使用的writer类型
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n";
        String title = "第三章：页面压缩演示";
        
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n");
        
        // 显示压缩状态信息
        out.println("<H2>压缩状态</H2>\n");
        out.println("<P><B>浏览器支持GZIP:</B> " + (isGzipSupported ? "是" : "否") + "</P>\n");
        out.println("<P><B>GZIP被禁用:</B> " + (isGzipDisabled ? "是" : "否") + "</P>\n");
        out.println("<P><B>实际使用压缩:</B> " + (isGzipSupported && !isGzipDisabled ? "是" : "否") + "</P>\n");
        
        if (isGzipSupported && !isGzipDisabled) {
            out.println("<P STYLE=\"color: green;\"><B>✓ 页面正在使用GZIP压缩传输，可以显著减少下载时间！</B></P>\n");
        } else if (isGzipDisabled) {
            out.println("<P STYLE=\"color: orange;\"><B>⚠ GZIP压缩已被禁用（用于性能测试）</B></P>\n");
        } else {
            out.println("<P STYLE=\"color: red;\"><B>✗ 浏览器不支持GZIP压缩，使用普通传输</B></P>\n");
        }
        
        out.println("<H2>压缩效果演示</H2>\n");
        out.println("<P>下面的内容重复了10000次，用于演示压缩效果：</P>\n");
        
        // ========================================
        // 练习3.1：压缩效果对比 - 学生修改区域
        // ========================================
        // TODO: 在这里实现压缩前后的详细对比
        // 要求：
        // 1. 显示原始内容大小
        // 2. 显示压缩后大小
        // 3. 计算压缩比例
        // 4. 估算传输时间节省
        //
        // 实现提示：
        // 1. 在生成内容前记录原始大小
        // 2. 在压缩后记录压缩大小
        // 3. 计算压缩比例：(1 - compressedSize/originalSize) * 100
        // 4. 估算传输时间节省（假设不同网络速度）
        
        // 当前代码：生成大量重复内容（需要添加统计功能）
        String line = "这是一行重复的文本内容，用于演示GZIP压缩的效果。 " +
                "GZIP压缩可以显著减少HTML页面的传输大小，特别是在使用拨号连接时。 " +
                "重复内容越多，压缩效果越明显。";
        
        for (int i = 0; i < 10000; i++) {
            out.println(line + "<BR>\n");
        }
        
        // ========================================
        // 练习3.2：多种压缩格式支持 - 学生修改区域
        // ========================================
        // TODO: 在这里实现多种压缩格式支持
        // 要求：
        // 1. 支持GZIP和DEFLATE压缩
        // 2. 根据客户端支持情况选择最佳压缩格式
        // 3. 显示使用的压缩格式信息
        //
        // 实现提示：
        // 1. 检查Accept-Encoding头中的支持格式
        // 2. 按优先级选择：Brotli > GZIP > DEFLATE
        // 3. 在HTML中显示选择的压缩格式
        // 4. 提供不同压缩格式的对比信息
        
        // 当前代码：仅支持GZIP（需要扩展）
        out.println("<H2>🔧 练习3.2：多种压缩格式支持</H2>\n");
        out.println("<P STYLE=\"color: #666; font-style: italic;\">");
        out.println("请完成练习3.2，添加以下功能：<BR>");
        out.println("• 支持GZIP和DEFLATE压缩<BR>");
        out.println("• 根据客户端支持情况选择最佳压缩格式<BR>");
        out.println("• 显示使用的压缩格式信息<BR>");
        out.println("• 提供不同压缩格式的对比<BR>");
        out.println("</P>\n");
        
        out.println("<H2>学习要点</H2>\n");
        out.println("<UL>\n");
        out.println("<LI><B>Accept-Encoding头</B>: 客户端告诉服务器它支持的编码格式</LI>\n");
        out.println("<LI><B>Content-Encoding头</B>: 服务器告诉客户端响应使用的编码格式</LI>\n");
        out.println("<LI><B>GZIP压缩</B>: 最常用的文本压缩格式，可以大幅减少传输时间</LI>\n");
        out.println("<LI><B>性能优化</B>: 压缩和解压缩的时间成本远小于网络传输时间的节省</LI>\n");
        out.println("</UL>\n");
        
        out.println("<P><A HREF=\"../index.html\">返回主页</A> | " +
                "<A HREF=\"LongServlet?disableGzip=true\">禁用GZIP测试</A></P>\n");
        
        out.println("</BODY></HTML>");
        out.close(); // 对于gzip是必需的；其他情况下是可选的
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
