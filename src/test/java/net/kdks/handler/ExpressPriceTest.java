package net.kdks.handler;

import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import net.kdks.config.InitConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressPriceParam;
import net.kdks.model.ExpressPriceResult;
import net.kdks.model.ExpressResponse;
import org.junit.Test;

public class ExpressPriceTest {

    private final ExpressHandlers expressHandlers = new ExpressHandlers(InitConfig.getConfig());

    /**
     * 申通.
     */
    @Test
    public void sto() {
        ExpressPriceParam expressPriceParam = new ExpressPriceParam();
        expressPriceParam.setStartProvince("北京");
        expressPriceParam.setStartCity("北京市");
        expressPriceParam.setStartDistrict("通州区");
        expressPriceParam.setStartAddress("北京市通州区梨园中街道");
        expressPriceParam.setEndProvince("山西省");
        expressPriceParam.setEndCity("晋城市");
        expressPriceParam.setEndDistrict("阳城县");
        expressPriceParam.setEndAddress("山西省高平市阳城县");
        expressPriceParam.setLength(new BigDecimal("20.00"));
        expressPriceParam.setWidth(new BigDecimal("5.00"));
        expressPriceParam.setHeight(new BigDecimal("5.00"));
        expressPriceParam.setWeight(null);
        ExpressResponse<ExpressPriceResult> result =
            expressHandlers.getExpressPrice(expressPriceParam,
                ExpressCompanyCodeEnum.STO.getValue());
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 圆通.
     */
    @Test
    public void yto() {
        ExpressPriceParam expressPriceParam = new ExpressPriceParam();
        expressPriceParam.setStartProvince("北京市");
        expressPriceParam.setStartCity("北京市");
//        expressPriceParam.setStartDistrict("通州区");
//        expressPriceParam.setStartAddress("北京市通州区梨园中街道");
        expressPriceParam.setEndProvince("山西省");
        expressPriceParam.setEndCity("晋城市");
//        expressPriceParam.setEndDistrict("阳城县");
//        expressPriceParam.setEndAddress("山西省高平市阳城县");
//        expressPriceParam.setLength(new BigDecimal("20.00"));
//        expressPriceParam.setWidth(new BigDecimal("5.00"));
//        expressPriceParam.setHeight(new BigDecimal("5.00"));
        expressPriceParam.setWeight(new BigDecimal("1.00"));
        ExpressResponse<ExpressPriceResult> result =
            expressHandlers.getExpressPrice(expressPriceParam,
                ExpressCompanyCodeEnum.YTO.getValue());
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 中通.
     */
    @Test
    public void zto() {
        ExpressPriceParam expressPriceParam = new ExpressPriceParam();
        expressPriceParam.setStartProvince("北京市");
        expressPriceParam.setStartCity("北京市");
        expressPriceParam.setStartDistrict("通州区");
//        expressPriceParam.setStartAddress("北京市通州区梨园中街道");
        expressPriceParam.setEndProvince("山西省");
        expressPriceParam.setEndCity("晋城市");
        expressPriceParam.setEndDistrict("阳城县");
//        expressPriceParam.setEndAddress("山西省高平市阳城县");
//        expressPriceParam.setLength(new BigDecimal("20.00"));
//        expressPriceParam.setWidth(new BigDecimal("5.00"));
//        expressPriceParam.setHeight(new BigDecimal("5.00"));
        expressPriceParam.setWeight(new BigDecimal("11.00"));
        ExpressResponse<ExpressPriceResult> result =
            expressHandlers.getExpressPrice(expressPriceParam,
                ExpressCompanyCodeEnum.ZTO.getValue());
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 极兔.
     */
    @Test
    public void jt() {
        ExpressPriceParam expressPriceParam = new ExpressPriceParam();
        expressPriceParam.setStartProvince("北京");
        expressPriceParam.setStartCity("北京市");
        expressPriceParam.setStartDistrict("通州区");
        expressPriceParam.setStartAddress("北京市通州区梨园中街道");
        expressPriceParam.setEndProvince("山西省");
        expressPriceParam.setEndCity("晋城市");
        expressPriceParam.setEndDistrict("阳城县");
        expressPriceParam.setEndAddress("山西省高平市阳城县");
        expressPriceParam.setLength(new BigDecimal("20.00"));
        expressPriceParam.setWidth(new BigDecimal("5.00"));
        expressPriceParam.setHeight(new BigDecimal("5.00"));
        expressPriceParam.setWeight(null);
        ExpressResponse<ExpressPriceResult> result =
            expressHandlers.getExpressPrice(expressPriceParam,
                ExpressCompanyCodeEnum.JT.getValue());
        System.out.println(JSON.toJSONString(result));
    }
}
