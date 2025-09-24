package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 第五章：Referer头处理
 * 学习Referer头的使用和来源检测
 * 实现防盗链和来源分析功能
 * 了解跨域请求处理和Referrer Policy
 * 
 * 功能：
 * - 检测请求来源页面
 * - 根据来源显示不同的内容
 * - 实现简单的防盗链功能
 * - 分析访问来源统计
 */
public class CustomizeImage extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String referer = request.getHeader("Referer");
        String title, message, imageName, sourceInfo;
        
        // 处理Referer头
        if (referer == null) {
            referer = "<I>无（直接访问）</I>";
            sourceInfo = "直接访问";
            message = "欢迎直接访问！您可能是通过书签、地址栏输入或外部链接访问的。";
        } else {
            sourceInfo = "来自: " + referer;
            message = "欢迎从 " + referer + " 访问！";
        }
        
        // 根据来源选择不同的内容
        if (referer.contains("google.com") || referer.contains("baidu.com")) {
            imageName = "search-engine.png";
            message += " 您是通过搜索引擎找到我们的！";
        } else if (referer.contains("facebook.com") || referer.contains("twitter.com")) {
            imageName = "social-media.png";
            message += " 您是通过社交媒体找到我们的！";
        } else if (referer.contains("github.com")) {
            imageName = "github.png";
            message += " 您是从GitHub访问的！";
        } else if (referer.contains("localhost") || referer.contains("127.0.0.1")) {
            imageName = "local.png";
            message += " 您是从本地环境访问的！";
        } else if (!referer.equals("<I>无（直接访问）</I>")) {
            imageName = "external.png";
            message += " 您是从外部网站访问的！";
        } else {
            imageName = "default.png";
        }
        
        title = "第五章：Referer头处理 - " + sourceInfo;
        
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n";
        
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<CENTER>\n" +
                "<H1>" + title + "</H1>\n" +
                "<H2>" + message + "</H2>\n");
        
        // 显示图片（这里用文字代替，实际项目中可以使用真实图片）
        out.println("<DIV STYLE=\"border: 2px solid #ccc; padding: 20px; margin: 20px; background-color: #f9f9f9;\">\n" +
                "<H3>来源图片: " + imageName + "</H3>\n" +
                "<P>（实际项目中这里会显示根据来源选择的图片）</P>\n" +
                "</DIV>\n");
        
        out.println("</CENTER>\n");
        
        // 显示Referer信息
        out.println("<H2>Referer头信息</H2>\n");
        out.println("<P><B>Referer值:</B> " + referer + "</P>\n");
        out.println("<P><B>是否为空:</B> " + (request.getHeader("Referer") == null ? "是" : "否") + "</P>\n");
        
        // ========================================
        // 练习5.1：高级防盗链 - 学生修改区域
        // ========================================
        // TODO: 在这里实现更复杂的防盗链逻辑
        // 要求：
        // 1. 支持白名单和黑名单
        // 2. 支持通配符匹配
        // 3. 支持时间窗口限制
        // 4. 记录防盗链日志
        //
        // 实现提示：
        // 1. 配置文件方式管理允许的域名
        // 2. 实现通配符匹配算法
        // 3. 添加时间窗口检查
        // 4. 记录详细的防盗链日志
        
        // 当前代码：简单防盗链检测（需要扩展）
        out.println("<H2>防盗链检测</H2>\n");
        boolean isAllowed = isAllowedReferer(referer);
        out.println("<P STYLE=\"color: " + (isAllowed ? "green" : "red") + ";\">\n" +
                "<B>" + (isAllowed ? "✓ 允许访问" : "✗ 拒绝访问") + "</B>\n" +
                "</P>\n");
        
        // ========================================
        // 练习5.2：来源分析统计 - 学生修改区域
        // ========================================
        // TODO: 在这里实现来源分析统计功能
        // 要求：
        // 1. 统计不同来源的访问次数
        // 2. 分析来源类型分布
        // 3. 生成来源统计报告
        // 4. 支持数据导出
        //
        // 实现提示：
        // 1. 使用Map存储来源统计信息
        // 2. 分类统计：搜索引擎、社交媒体、直接访问等
        // 3. 生成图表和可视化展示
        // 4. 提供CSV/JSON格式导出
        
        // 当前代码：暂无统计功能（需要添加）
        out.println("<H2>📈 练习5.2：来源分析统计</H2>\n");
        out.println("<P STYLE=\"color: #666; font-style: italic;\">");
        out.println("请完成练习5.2，添加以下统计功能：<BR>");
        out.println("• 不同来源的访问次数统计<BR>");
        out.println("• 来源类型分布分析<BR>");
        out.println("• 来源统计报告生成<BR>");
        out.println("• 数据导出功能<BR>");
        out.println("</P>\n");
        
        // 学习要点
        out.println("<H2>学习要点</H2>\n");
        out.println("<UL>\n");
        out.println("<LI><B>Referer头</B>: 表示请求来源页面的URL</LI>\n");
        out.println("<LI><B>防盗链</B>: 通过检查Referer防止资源被其他网站盗用</LI>\n");
        out.println("<LI><B>来源分析</B>: 了解用户如何到达当前页面</LI>\n");
        out.println("<LI><B>个性化内容</B>: 根据来源提供不同的用户体验</LI>\n");
        out.println("<LI><B>注意事项</B>: Referer头可能为空或被伪造，不应用于安全验证</LI>\n");
        out.println("</UL>\n");
        
        // Referer Policy说明
        out.println("<H2>Referrer Policy</H2>\n");
        out.println("<P>现代浏览器支持Referrer Policy来控制Referer头的发送：</P>\n");
        out.println("<UL>\n");
        out.println("<LI><B>no-referrer</B>: 不发送Referer头</LI>\n");
        out.println("<LI><B>same-origin</B>: 仅同源请求发送Referer头</LI>\n");
        out.println("<LI><B>strict-origin</B>: 发送源信息但不包含路径</LI>\n");
        out.println("<LI><B>unsafe-url</B>: 总是发送完整的Referer头</LI>\n");
        out.println("</UL>\n");
        
        out.println("<P><A HREF=\"../index.html\">返回主页</A> | " +
                "<A HREF=\"test-referer.html\">测试Referer页面</A></P>\n");
        
        out.println("</BODY></HTML>");
    }
    
    /**
     * 简单的防盗链检测
     * 实际项目中应该使用更复杂的逻辑
     */
    private boolean isAllowedReferer(String referer) {
        if (referer == null) {
            return true; // 直接访问允许
        }
        
        // 允许的域名列表
        String[] allowedDomains = {
            "localhost", "127.0.0.1", "google.com", "baidu.com",
            "github.com", "stackoverflow.com"
        };
        
        for (String domain : allowedDomains) {
            if (referer.contains(domain)) {
                return true;
            }
        }
        
        return false; // 默认拒绝
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
