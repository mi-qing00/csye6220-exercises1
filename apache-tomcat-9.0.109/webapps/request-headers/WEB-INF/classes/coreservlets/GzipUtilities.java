package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.zip.*;

/**
 * GZIP压缩工具类
 * 提供三个静态工具方法来协助GZIP编码：
 * - isGzipSupported: 检查浏览器是否支持gzip
 * - isGzipDisabled: 检查用户是否禁用了gzip编码
 * - getGzipWriter: 返回一个GZIP压缩的PrintWriter
 */
public class GzipUtilities {
    
    /**
     * 检查客户端是否支持gzip压缩
     * 通过检查Accept-Encoding请求头来判断
     */
    public static boolean isGzipSupported(HttpServletRequest request) {
        String encodings = request.getHeader("Accept-Encoding");
        return (encodings != null) && (encodings.indexOf("gzip") != -1);
    }
    
    /**
     * 检查用户是否禁用了gzip压缩
     * 通过检查URL参数disableGzip来判断
     * 用于性能测试时比较压缩前后的效果
     */
    public static boolean isGzipDisabled(HttpServletRequest request) {
        String flag = request.getParameter("disableGzip");
        return (flag != null) && (!flag.equalsIgnoreCase("false"));
    }
    
    /**
     * 返回一个GZIP压缩的PrintWriter
     * 用于将响应内容进行gzip压缩
     */
    public static PrintWriter getGzipWriter(HttpServletResponse response) throws IOException {
        return new PrintWriter(new GZIPOutputStream(response.getOutputStream()));
    }
}
