package net.kdks.handler;

import org.junit.Test;

import net.kdks.config.ExpressConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;

public class TestHandler {
	private final ExpressHandlers expressHandlers;

	public TestHandler() {
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
		ExpressResult expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.STO.getValue());
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
		ExpressResult expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.YTO.getValue());
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
		ExpressResult expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.HTKY.getValue());
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
		param.setMobile("3809");
		param.setExpressNo("444003077891");
		ExpressResult expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.SF.getValue());
		System.out.println(expressResult.toString());
	}
	
	@Test
	public void createOrderShunfeng() {
		expressHandlers.createOrder(ExpressCompanyCodeEnum.SF.getValue());
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
		param.setExpressNo("75401533589487");
		ExpressResult expressResult = expressHandlers.getExpressInfo(param, ExpressCompanyCodeEnum.ZTO.getValue());
		System.out.println(expressResult.toString());
	}
}
