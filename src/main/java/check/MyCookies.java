
package check;

public class MyCookies {

    private static String cookie = "";
    
    public MyCookies() {
    }

    public MyCookies(String cookie) {
        this.cookie = cookie;
    }

    public static void setCookie(String cookie) {
        MyCookies.cookie = cookie;
    }

    public static String getCookie() {
        return cookie;
    }

}
