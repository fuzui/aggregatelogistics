package net.kdks.handler;

import java.math.BigDecimal;

import org.junit.Test;

import net.kdks.config.ExpressConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.CargoDetail;
import net.kdks.model.ContactInfo;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;

/**
 * 业务测试.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class HandlerTest {
	private final ExpressHandlers expressHandlers;

	public HandlerTest() {
		ExpressConfig config = ExpressConfig.builder()
				.build();
		this.expressHandlers = new ExpressHandlers(config);
	}
	
	@Test
	public void handlerTest() {
		ExpressParam param = new ExpressParam();
		param.setExpressNo("557014637811181");
		expressHandlers.getExpressInfo(param, "STO");
	}
	
	/**
	 * 申通
	 * 
	 * @param 
	 * @return void
	 */
	@Test
	public void queryShentong() {
		ExpressParam param = new ExpressParam();
		param.setExpressNo("773044983842140");
		ExpressResponse<ExpressResult> expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.STO.getValue());
		System.out.println(expressResult.toString());
	}
	
	/**
	 * 圆通
	 * 
	 * @param 
	 * @return void
	 */
	@Test
	public void queryYuantong() {
		ExpressParam param = new ExpressParam();
		param.setExpressNo("YT4784621755802");
		ExpressResponse<ExpressResult> expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.YTO.getValue());
		System.out.println(expressResult.toString());
	}
	
	/**
	 * 百世
	 * 
	 * @param 
	 * @return void
	 */
	@Test
	public void queryBaishi() {
		ExpressParam param = new ExpressParam();
		param.setExpressNo("550005705674324");
		ExpressResponse<ExpressResult> expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.HTKY.getValue());
		System.out.println(expressResult.toString());
	}
	/**
	 * 顺丰
	 * 
	 * @param 
	 * @return void
	 */
	@Test
	public void queryShunfeng() {
		ExpressParam param = new ExpressParam();
		param.setMobile("0728");
		param.setExpressNo("SF1028995968316");
		ExpressResponse<ExpressResult> expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.SF.getValue());
		System.out.println(expressResult.toString());
	}
	
	@Test
	public void createOrderShunfeng() {
		CreateOrderParam createOrderParam = new CreateOrderParam();
		CargoDetail cargoDetail = new CargoDetail();
		cargoDetail.setCount(new BigDecimal("2.365"));
		cargoDetail.setUnit("个");
		cargoDetail.setWeight(new BigDecimal("6.1"));
		cargoDetail.setAmount(new BigDecimal("100.5111"));
		cargoDetail.setCurrency("HKD");
		cargoDetail.setName("测试衣服1");
		ContactInfo sendContactInfo = new ContactInfo();
		sendContactInfo.setCompany("顺丰速运");
		sendContactInfo.setContact("小曾");
		sendContactInfo.setPostCode("580058");
		sendContactInfo.setProvince("北京市");
		sendContactInfo.setCity("北京市");
		sendContactInfo.setCounty("通州区");
		sendContactInfo.setAddress("软件产业基地11栋");
		sendContactInfo.setTel("4006789888");
		ContactInfo receiptContactInfo = new ContactInfo();
		receiptContactInfo.setCompany("顺丰速运");
		receiptContactInfo.setContact("小邱");
		receiptContactInfo.setPostCode("580058");
		receiptContactInfo.setProvince("山西省");
		receiptContactInfo.setCity("晋城市");
		receiptContactInfo.setCounty("高平市");
		receiptContactInfo.setAddress("湖北大厦");
		receiptContactInfo.setTel("15555542203");
		createOrderParam.setOrderId("jksdtestdev0004");
		createOrderParam.setCargoDetail(cargoDetail);
		createOrderParam.setSendContactInfo(sendContactInfo);
		createOrderParam.setReceiptContactInfo(receiptContactInfo);
		ExpressResponse<OrderResult> orderResult = expressHandlers.createOrder(createOrderParam,ExpressCompanyCodeEnum.SF.getValue());
		System.out.println(orderResult);
	}
	/**
	 * 中通
	 * 
	 * @param 
	 * @return void
	 */
	@Test
	public void queryZhongtong() {
		ExpressParam param = new ExpressParam();
		param.setExpressNo("75378742756756");
		ExpressResponse<ExpressResult> expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.YD.getValue());
		System.out.println(expressResult.toString());
	}
}
