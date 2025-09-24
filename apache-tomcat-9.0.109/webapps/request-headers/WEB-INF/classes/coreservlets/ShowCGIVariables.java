package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * 第六章：CGI变量访问
 * 访问服务器信息和标准CGI环境变量
 * 学习客户端连接信息的获取
 * 了解会话管理和环境变量访问
 * 
 * 功能：
 * - 显示所有标准CGI变量
 * - 展示服务器环境信息
 * - 显示客户端连接信息
 * - 提供调试和监控功能
 */
public class ShowCGIVariables extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 创建CGI变量数组
        String[][] variables = {
            { "AUTH_TYPE", request.getAuthType() },
            { "CONTENT_LENGTH", String.valueOf(request.getContentLength()) },
            { "CONTENT_TYPE", request.getContentType() },
            { "DOCUMENT_ROOT", getServletContext().getRealPath("/") },
            { "PATH_INFO", request.getPathInfo() },
            { "PATH_TRANSLATED", request.getPathTranslated() },
            { "QUERY_STRING", request.getQueryString() },
            { "REMOTE_ADDR", request.getRemoteAddr() },
            { "REMOTE_HOST", request.getRemoteHost() },
            { "REMOTE_USER", request.getRemoteUser() },
            { "REQUEST_METHOD", request.getMethod() },
            { "SCRIPT_NAME", request.getServletPath() },
            { "SERVER_NAME", request.getServerName() },
            { "SERVER_PORT", String.valueOf(request.getServerPort()) },
            { "SERVER_PROTOCOL", request.getProtocol() },
            { "SERVER_SOFTWARE", getServletContext().getServerInfo() }
        };
        
        String title = "第六章：CGI变量访问 - 服务器环境信息";
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n";
        
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<CENTER>\n" +
                "<H1>" + title + "</H1>\n" +
                "<H2>标准CGI变量</H2>\n" +
                "<TABLE BORDER=1 WIDTH=\"90%\">\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>CGI变量名称<TH>值<TH>说明");
        
        // 显示所有CGI变量
        for (int i = 0; i < variables.length; i++) {
            String varName = variables[i][0];
            String varValue = variables[i][1];
            String description = getVariableDescription(varName);
            
            if (varValue == null) {
                varValue = "<I>未指定</I>";
            }
            
            out.println("<TR><TD><B>" + varName + "</B><TD>" + varValue + "<TD>" + description);
        }
        
        out.println("</TABLE>\n");
        
        // 显示额外的服务器信息
        out.println("<H2>额外服务器信息</H2>\n");
        out.println("<TABLE BORDER=1 WIDTH=\"90%\">\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>信息类型<TH>值<TH>说明");
        
        // 会话信息
        HttpSession session = request.getSession();
        out.println("<TR><TD><B>会话ID</B><TD>" + session.getId() + 
                "<TD>当前HTTP会话的唯一标识符");
        out.println("<TR><TD><B>会话创建时间</B><TD>" + new Date(session.getCreationTime()) + 
                "<TD>会话创建的时间戳");
        out.println("<TR><TD><B>最后访问时间</B><TD>" + new Date(session.getLastAccessedTime()) + 
                "<TD>会话最后被访问的时间");
        
        // 请求头信息
        out.println("<TR><TD><B>User-Agent</B><TD>" + 
                (request.getHeader("User-Agent") != null ? request.getHeader("User-Agent") : "<I>未指定</I>") + 
                "<TD>客户端浏览器信息");
        out.println("<TR><TD><B>Accept</B><TD>" + 
                (request.getHeader("Accept") != null ? request.getHeader("Accept") : "<I>未指定</I>") + 
                "<TD>客户端接受的MIME类型");
        out.println("<TR><TD><B>Accept-Language</B><TD>" + 
                (request.getHeader("Accept-Language") != null ? request.getHeader("Accept-Language") : "<I>未指定</I>") + 
                "<TD>客户端接受的语言");
        
        out.println("</TABLE>\n");
        
        // ========================================
        // 练习6.1：服务器性能监控 - 学生修改区域
        // ========================================
        // TODO: 在这里实现服务器性能监控
        // 要求：
        // 1. 监控内存使用情况
        // 2. 监控CPU使用情况
        // 3. 监控请求处理时间
        // 4. 生成性能报告
        //
        // 实现提示：
        // 1. 获取JVM内存信息：Runtime.getRuntime()
        // 2. 监控请求处理时间：记录开始和结束时间
        // 3. 使用ManagementFactory获取系统信息
        // 4. 生成性能监控表格和图表
        
        // 当前代码：暂无性能监控（需要添加）
        out.println("<H2>⚡ 练习6.1：服务器性能监控</H2>\n");
        out.println("<P STYLE=\"color: #666; font-style: italic;\">");
        out.println("请完成练习6.1，添加以下性能监控功能：<BR>");
        out.println("• JVM内存使用情况监控<BR>");
        out.println("• CPU使用情况监控<BR>");
        out.println("• 请求处理时间统计<BR>");
        out.println("• 性能报告生成<BR>");
        out.println("</P>\n");
        
        // ========================================
        // 练习6.2：访问日志分析 - 学生修改区域
        // ========================================
        // TODO: 在这里实现访问日志分析功能
        // 要求：
        // 1. 记录详细访问日志
        // 2. 分析访问模式
        // 3. 检测异常访问
        // 4. 生成访问统计报告
        //
        // 实现提示：
        // 1. 使用日志框架（如Log4j）记录访问信息
        // 2. 分析访问频率、时间分布等模式
        // 3. 检测异常访问（如频繁访问、异常IP等）
        // 4. 生成统计图表和报告
        
        // 当前代码：暂无日志分析（需要添加）
        out.println("<H2>📊 练习6.2：访问日志分析</H2>\n");
        out.println("<P STYLE=\"color: #666; font-style: italic;\">");
        out.println("请完成练习6.2，添加以下日志分析功能：<BR>");
        out.println("• 详细访问日志记录<BR>");
        out.println("• 访问模式分析<BR>");
        out.println("• 异常访问检测<BR>");
        out.println("• 访问统计报告生成<BR>");
        out.println("</P>\n");
        
        // 学习要点
        out.println("<H2>学习要点</H2>\n");
        out.println("<UL>\n");
        out.println("<LI><B>CGI变量</B>: 传统CGI程序使用的环境变量，在Servlet中有对应的方法</LI>\n");
        out.println("<LI><B>请求信息</B>: 通过HttpServletRequest获取客户端请求信息</LI>\n");
        out.println("<LI><B>服务器信息</B>: 通过ServletContext获取服务器环境信息</LI>\n");
        out.println("<LI><B>会话管理</B>: 通过HttpSession管理用户会话状态</LI>\n");
        out.println("<LI><B>调试工具</B>: 这些信息对调试和监控非常有用</LI>\n");
        out.println("</UL>\n");
        
        // 实际应用场景
        out.println("<H2>实际应用场景</H2>\n");
        out.println("<UL>\n");
        out.println("<LI><B>访问控制</B>: 根据REMOTE_ADDR进行IP白名单控制</LI>\n");
        out.println("<LI><B>日志记录</B>: 记录详细的访问日志信息</LI>\n");
        out.println("<LI><B>性能监控</B>: 监控服务器性能和资源使用情况</LI>\n");
        out.println("<LI><B>安全审计</B>: 进行安全审计和异常检测</LI>\n");
        out.println("<LI><B>个性化服务</B>: 根据客户端信息提供个性化服务</LI>\n");
        out.println("</UL>\n");
        
        out.println("</CENTER>\n");
        out.println("<P><A HREF=\"../index.html\">返回主页</A></P>\n");
        out.println("</BODY></HTML>");
    }
    
    /**
     * 获取CGI变量的说明
     */
    private String getVariableDescription(String varName) {
        switch (varName) {
            case "AUTH_TYPE":
                return "认证类型（basic或digest）";
            case "CONTENT_LENGTH":
                return "POST请求的数据长度（字节）";
            case "CONTENT_TYPE":
                return "请求数据的MIME类型";
            case "DOCUMENT_ROOT":
                return "服务器文档根目录的物理路径";
            case "PATH_INFO":
                return "URL中servlet路径后的额外路径信息";
            case "PATH_TRANSLATED":
                return "PATH_INFO对应的物理路径";
            case "QUERY_STRING":
                return "URL中的查询字符串部分";
            case "REMOTE_ADDR":
                return "客户端IP地址";
            case "REMOTE_HOST":
                return "客户端主机名";
            case "REMOTE_USER":
                return "认证用户名";
            case "REQUEST_METHOD":
                return "HTTP请求方法（GET、POST等）";
            case "SCRIPT_NAME":
                return "servlet的路径";
            case "SERVER_NAME":
                return "服务器主机名";
            case "SERVER_PORT":
                return "服务器端口号";
            case "SERVER_PROTOCOL":
                return "HTTP协议版本";
            case "SERVER_SOFTWARE":
                return "服务器软件信息";
            default:
                return "未知变量";
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
