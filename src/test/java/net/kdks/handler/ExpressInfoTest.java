package net.kdks.handler;

import com.alibaba.fastjson.JSON;
import net.kdks.config.InitConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExpressInfoTest {

  private final ExpressHandlers expressHandlers = new ExpressHandlers(InitConfig.getConfig());

  /**
   * 中通
   */
  @Test
  public void zto() {
    ExpressParam expressParam = new ExpressParam();
    List<String> expressNo = new ArrayList<>();
    expressNo.add("73177487726227");
    expressParam.setExpressNos(expressNo);
    ExpressResponse<List<ExpressResult>> result = expressHandlers.getExpressInfo(expressParam, "ZTO");
    System.out.println(JSON.toJSONString(result));
  }

  /**
   * 顺丰
   */
  @Test
  public void sf() {
    ExpressParam expressParam = new ExpressParam();
    List<String> expressNo = new ArrayList<>();
    expressNo.add("");
    expressParam.setExpressNos(expressNo);
    expressParam.setMobile("");
    ExpressResponse<List<ExpressResult>> result = expressHandlers.getExpressInfo(expressParam, ExpressCompanyCodeEnum.SF.getValue());
    System.out.println(JSON.toJSONString(result));
  }

  /**
   * 圆通
   */
  @Test
  public void yto() {
    ExpressParam expressParam = new ExpressParam();
    List<String> expressNo = new ArrayList<>();
    expressNo.add("YT1447279684726");
    expressParam.setExpressNos(expressNo);
    ExpressResponse<List<ExpressResult>> result = expressHandlers.getExpressInfo(expressParam, ExpressCompanyCodeEnum.YTO.getValue());
    System.out.println(JSON.toJSONString(result));
  }

  /**
   * 申通
   */
  @Test
  public void sto() {
    ExpressParam expressParam = new ExpressParam();
    List<String> expressNo = new ArrayList<>();
    expressNo.add("");
    expressParam.setExpressNos(expressNo);
    ExpressResponse<List<ExpressResult>> result = expressHandlers.getExpressInfo(expressParam, ExpressCompanyCodeEnum.STO.getValue());
    System.out.println(JSON.toJSONString(result));
  }

  /**
   * 百世
   */
  @Test
  public void htky() {
    ExpressParam expressParam = new ExpressParam();
    List<String> expressNo = new ArrayList<>();
    expressNo.add("");
    expressParam.setExpressNos(expressNo);
    ExpressResponse<List<ExpressResult>> result = expressHandlers.getExpressInfo(expressParam, ExpressCompanyCodeEnum.HTKY.getValue());
    System.out.println(JSON.toJSONString(result));
  }
}
