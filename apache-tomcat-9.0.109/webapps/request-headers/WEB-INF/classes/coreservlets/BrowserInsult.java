package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 第四章：浏览器检测
 * 通过User-Agent头智能识别浏览器类型、版本和操作系统
 * 实现设备类型检测（桌面、移动、平板）
 * 学习浏览器检测的实际应用场景
 * 
 * 功能：
 * - 检测浏览器类型（Chrome、Firefox、Safari、Edge等）
 * - 检测操作系统（Windows、macOS、Linux、Android、iOS等）
 * - 检测设备类型（桌面、移动、平板）
 * - 提供个性化的欢迎信息
 */
public class BrowserInsult extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String userAgent = request.getHeader("User-Agent");
        String title, message, browserInfo, osInfo, deviceInfo;
        
        // ========================================
        // 练习4.1：详细浏览器信息解析 - 学生修改区域
        // ========================================
        // TODO: 在这里实现更详细的浏览器信息解析
        // 要求：
        // 1. 解析浏览器版本号
        // 2. 解析操作系统版本
        // 3. 检测浏览器引擎（WebKit, Gecko, Trident等）
        // 4. 检测设备类型（手机、平板、桌面）
        //
        // 实现提示：
        // 1. 使用正则表达式提取版本号
        // 2. 分析User-Agent中的引擎标识
        // 3. 检测移动设备特征字符串
        // 4. 提供更详细的设备信息
        
        // 当前代码：基础浏览器检测（需要扩展）
        if (userAgent == null) {
            browserInfo = "未知浏览器";
            message = "欢迎，神秘的访客！";
        } else if (userAgent.contains("Chrome") && !userAgent.contains("Edg")) {
            browserInfo = "Google Chrome";
            message = "欢迎使用Chrome浏览器！您选择了最快的浏览器。";
        } else if (userAgent.contains("Firefox")) {
            browserInfo = "Mozilla Firefox";
            message = "欢迎使用Firefox！开源浏览器的忠实用户。";
        } else if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) {
            browserInfo = "Safari";
            message = "欢迎使用Safari！苹果生态系统的用户。";
        } else if (userAgent.contains("Edg")) {
            browserInfo = "Microsoft Edge";
            message = "欢迎使用Edge！微软的新一代浏览器。";
        } else if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            browserInfo = "Internet Explorer";
            message = "欢迎使用IE！经典浏览器的用户。";
        } else if (userAgent.contains("Opera")) {
            browserInfo = "Opera";
            message = "欢迎使用Opera！小众但优秀的浏览器。";
        } else {
            browserInfo = "其他浏览器";
            message = "欢迎使用其他浏览器！";
        }
        
        // 操作系统检测
        if (userAgent == null) {
            osInfo = "未知操作系统";
        } else if (userAgent.contains("Windows NT 10.0")) {
            osInfo = "Windows 10/11";
        } else if (userAgent.contains("Windows NT 6.3")) {
            osInfo = "Windows 8.1";
        } else if (userAgent.contains("Windows NT 6.1")) {
            osInfo = "Windows 7";
        } else if (userAgent.contains("Mac OS X")) {
            osInfo = "macOS";
        } else if (userAgent.contains("Linux")) {
            osInfo = "Linux";
        } else if (userAgent.contains("Android")) {
            osInfo = "Android";
        } else if (userAgent.contains("iPhone") || userAgent.contains("iPad")) {
            osInfo = "iOS";
        } else {
            osInfo = "其他操作系统";
        }
        
        // 设备类型检测
        if (userAgent == null) {
            deviceInfo = "未知设备";
        } else if (userAgent.contains("Mobile") || userAgent.contains("Android") || 
                   userAgent.contains("iPhone")) {
            deviceInfo = "移动设备";
        } else if (userAgent.contains("iPad") || userAgent.contains("Tablet")) {
            deviceInfo = "平板设备";
        } else {
            deviceInfo = "桌面设备";
        }
        
        title = "第四章：浏览器检测 - " + browserInfo;
        
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n";
        
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
                "<CENTER><H2>" + message + "</H2></CENTER>\n" +
                "<H2>检测结果</H2>\n" +
                "<TABLE BORDER=1 ALIGN=\"CENTER\" WIDTH=\"60%\">\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>检测项目<TH>检测结果\n" +
                "<TR><TD><B>浏览器类型</B><TD>" + browserInfo + "\n" +
                "<TR><TD><B>操作系统</B><TD>" + osInfo + "\n" +
                "<TR><TD><B>设备类型</B><TD>" + deviceInfo + "\n" +
                "</TABLE>\n" +
                "<H2>User-Agent信息</H2>\n" +
                "<P><B>原始User-Agent:</B></P>\n" +
                "<P STYLE=\"background-color: #f0f0f0; padding: 10px; font-family: monospace;\">" +
                (userAgent != null ? userAgent : "未提供") + "</P>\n" +
                "<H2>学习要点</H2>\n" +
                "<UL>\n" +
                "<LI><B>User-Agent头</B>: 客户端标识信息，包含浏览器、操作系统等详细信息</LI>\n" +
                "<LI><B>浏览器检测</B>: 通过分析User-Agent字符串识别浏览器类型和版本</LI>\n" +
                "<LI><B>设备检测</B>: 区分桌面、移动、平板等不同设备类型</LI>\n" +
                "<LI><B>个性化服务</B>: 根据浏览器类型提供不同的用户体验</LI>\n" +
                "<LI><B>注意事项</B>: User-Agent可以被伪造，不应用于安全验证</LI>\n" +
                "</UL>\n" +
                "<H2>检测技巧</H2>\n" +
                "<UL>\n" +
                "<LI>Chrome和Edge都包含\"Chrome\"，需要额外检查\"Edg\"来区分</LI>\n" +
                "<LI>IE的User-Agent包含\"MSIE\"或\"Trident\"</LI>\n" +
                "<LI>Safari需要排除Chrome（Chrome也包含Safari字符串）</LI>\n" +
                "<LI>移动设备通常包含\"Mobile\"、\"Android\"或\"iPhone\"</LI>\n" +
                "</UL>\n" +
                "\n" +
                "        // ========================================\n" +
                "        // 练习4.2：浏览器能力检测 - 学生修改区域\n" +
                "        // ========================================\n" +
                "        // TODO: 在这里实现浏览器能力检测功能\n" +
                "        // 要求：\n" +
                "        // 1. 检测JavaScript支持\n" +
                "        // 2. 检测Cookie支持\n" +
                "        // 3. 检测HTML5支持\n" +
                "        // 4. 检测CSS3支持\n" +
                "        //\n" +
                "        // 实现提示：\n" +
                "        // 1. 通过User-Agent分析浏览器版本和引擎\n" +
                "        // 2. 根据浏览器类型推断支持的功能\n" +
                "        // 3. 提供功能支持矩阵表格\n" +
                "        // 4. 添加功能检测的JavaScript代码\n" +
                "\n" +
                "        // 当前代码：暂无能力检测（需要添加）\n" +
                "        out.println(\"<H2>🔍 练习4.2：浏览器能力检测</H2>\\n\");\n" +
                "        out.println(\"<P STYLE=\\\"color: #666; font-style: italic;\\\">\");\n" +
                "        out.println(\"请完成练习4.2，添加以下功能检测：<BR>\");\n" +
                "        out.println(\"• JavaScript支持检测<BR>\");\n" +
                "        out.println(\"• Cookie支持检测<BR>\");\n" +
                "        out.println(\"• HTML5功能支持检测<BR>\");\n" +
                "        out.println(\"• CSS3特性支持检测<BR>\");\n" +
                "        out.println(\"• 功能支持矩阵展示<BR>\");\n" +
                "        out.println(\"</P>\\n\");\n" +
                "\n" +
                "<P><A HREF=\"../index.html\">返回主页</A></P>\n" +
                "</BODY></HTML>");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
