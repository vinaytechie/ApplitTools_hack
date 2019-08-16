package com.applitools.quickstarts;

import com.applitools.eyes.AbstractProxySettings;
import com.applitools.eyes.selenium.Eyes;
import com.google.common.base.Strings;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;

class Util {

    private static boolean IS_GITPOD = !Strings.isNullOrEmpty(System.getenv("GITPOD_INSTANCE_ID"));

    /**
     * if running in Gitpod, configure API Key and Proxy server.
     */
    public static void configureProxyIfNeeded(Eyes eyes) {
        if (IS_GITPOD) {
            eyes.setProxy(new AbstractProxySettings("http://ws-fwd-proxy:3129", 3219) {
            });
            eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        }
    }

    /**
     * if running in Gitpod, configure Proxy server.
     */
    public static ChromeOptions newOptionsWithProxy() {
        ChromeOptions options = new ChromeOptions();
        if (IS_GITPOD) {
            Proxy proxy = new Proxy().setHttpProxy("http://ws-fwd-proxy:3129").setSslProxy("http://ws-fwd-proxy:3129");
            options.setProxy(proxy);
        }
        return options;
    }

}