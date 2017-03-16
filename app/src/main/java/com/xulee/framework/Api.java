package com.xulee.framework;

/**
 * Desc:
 * Created by xulee on 2017/3/13
 */

public class Api {

    //摇后台
    private static final String YAO_HOST = "http://yao.cutv.com";

    //CMS后台
    private static String CMS_HOST = "https://pgytv-api.cutv.com";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        switch (hostType) {
            case HostType.YAO:
                return Api.YAO_HOST;
            case HostType.CMS:
                return Api.CMS_HOST;
        }
        return "";
    }
}
