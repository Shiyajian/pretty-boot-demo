package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.holder.ServletContextHolder;

import javax.servlet.http.HttpServletRequest;


/**
 * @author shiyajian
 * create: 2018-10-23
 */
public final class NetUtil {

    private static final String UNKNOWN = "unknown";

    private NetUtil() { /* no instance */ }

    /**
     * 获得请求的真实ip
     * @return 真实IP
     */
    public static String getIpAddress() {
        HttpServletRequest request = ServletContextHolder.request();
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String ipStr : ips) {
                if (!(UNKNOWN.equalsIgnoreCase(ipStr))) {
                    return ipStr;
                }
            }
        }
        return ip;
    }
}
