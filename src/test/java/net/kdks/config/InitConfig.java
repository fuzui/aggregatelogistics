package net.kdks.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class InitConfig {

    public static ExpressConfig getConfig() {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("config-prod");
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("config-dev");
        }
        ExpressConfig.Builder configBuild = ExpressConfig.builder();
        // 顺丰速运
        if (bundle.containsKey("express.sf.partnerID") && bundle.containsKey("express.sf.requestID")
            && bundle.containsKey("express.sf.checkWord") &&
            bundle.containsKey("express.sf.isProduct")) {
            configBuild.shunfengConfig(bundle.getString("express.sf.partnerID"),
                bundle.getString("express.sf.requestID"),
                bundle.getString("express.sf.checkWord"),
                Integer.parseInt(bundle.getString("express.sf.isProduct")));
        }
        // 申通
        if (bundle.containsKey("express.sto.appkey") && bundle.containsKey("express.sto.secretKey")
            && bundle.containsKey("express.sto.isProduct")) {
            configBuild.shentongConfig(bundle.getString("express.sto.appkey"),
                bundle.getString("express.sto.secretKey"),
                Integer.parseInt(bundle.getString("express.sto.isProduct")));
        }
        // 百世
        if (bundle.containsKey("express.htky.partnerID") &&
            bundle.containsKey("express.htky.secretKey")
            && bundle.containsKey("express.htky.isProduct")) {
            configBuild.baishiConfig(bundle.getString("express.htky.partnerID"),
                bundle.getString("express.htky.secretKey"),
                Integer.parseInt(bundle.getString("express.htky.isProduct")));
        }
        // 圆通
        if (bundle.containsKey("express.yto.appkey") && bundle.containsKey("express.yto.secretKey")
            && bundle.containsKey("express.yto.userId") &&
            bundle.containsKey("express.yto.isProduct")) {
            configBuild.yuantongConfig(bundle.getString("express.yto.appkey"),
                bundle.getString("express.yto.secretKey"),
                bundle.getString("express.yto.userId"),
                Integer.parseInt(bundle.getString("express.yto.isProduct")));
        }
        // 中通
        if (bundle.containsKey("express.zto.companyId") &&
            bundle.containsKey("express.zto.secretKey")
            && bundle.containsKey("express.zto.isProduct")) {
            configBuild.zhongtongConfig(bundle.getString("express.zto.companyId"),
                bundle.getString("express.zto.secretKeyV1"),
                bundle.getString("express.zto.appKey"),
                bundle.getString("express.zto.secretKey"),
                bundle.getString("express.zto.routeVersion"),
                Integer.parseInt(bundle.getString("express.zto.isProduct")));
        }
        if (bundle.containsKey("express.jt.apiAccount") &&
            bundle.containsKey("express.jt.privateKey")
            && bundle.containsKey("express.jt.isProduct")) {
            configBuild.jituConfig(bundle.getString("express.jt.apiAccount"),
                bundle.getString("express.jt.privateKey"),
                bundle.getString("express.jt.uuid"),
                Integer.parseInt(bundle.getString("express.jt.isProduct")));
            configBuild.jituConfig(bundle.getString("express.jt.apiAccount"),
                bundle.getString("express.jt.privateKey"),
                bundle.getString("express.jt.uuid"),
                bundle.getString("express.jt.customerCode"),
                bundle.getString("express.jt.customerPwd"),
                Integer.parseInt(bundle.getString("express.jt.isProduct")));
        }
        if (bundle.containsKey("express.yd.appKey") &&
            bundle.containsKey("express.yd.appSecret")
            && bundle.containsKey("express.yd.isProduct")) {
            configBuild.yundaConfig(bundle.getString("express.yd.appKey"),
                bundle.getString("express.yd.appSecret"),
                Integer.parseInt(bundle.getString("express.jt.isProduct")));
        }
        return configBuild.build();
    }
}
