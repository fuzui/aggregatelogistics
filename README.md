<p align="center">
 <img src="https://gitee.com/fuzui/aggregatelogistics/badge/star.svg?theme=dark" alt="Build Status">
 <img src="https://img.shields.io/github/stars/fuzui/aggregatelogistics.svg?style=social" alt="Build Status">
 <img src="https://img.shields.io/badge/aggregatelogistics-0.0.6-brightgreen" alt="Build Status">
</p>

# 聚合物流(aggregatelogistics)

它是一个聚合各物流公司开放平台的**Java工具类库**，为解决公司电商项目集成物流而生。

当前已聚合顺丰、中通、申通、圆通、百世、极兔。

> 提示：多数快递公司都需要企业认证，即营业执照(公司或个体工商户)。所以个人用户难以配置并使用该类库。

# 功能

* 运单轨迹查询：

  当前支持顺丰、中通、申通、圆通、百世、极兔；

* 运单轨迹查询：

  当前支持中通、申通、圆通、极兔；

* 下单：

  当前支持顺丰；

# 使用

## 1. 引入

* Maven

  ```xml
  <dependency>
  	<groupId>net.kdks</groupId>
  	<artifactId>aggregatelogistics</artifactId>
  	<version>0.0.6</version>
  </dependency>
  ```

* Gradle

  ```
  compile 'net.kdks:aggregatelogistics:0.0.6'
  ```

## 2. 调用

```java
// 配置,每行最后一个参数0表示测试环境,1表示正式环境,不填为正式环境. 可选择性配置自己所需的快递公司
// 所有配置参数需注册该快递公司开放平台，且多数需要企业认证(营业执照)
ExpressConfig config=ExpressConfig.builder()
  // 顺丰配置
  .shunfengConfig("partnerId", "requestId", "checkWord", 1)
  // 申通配置
  .shentongConfig("appkey","secretKey",1)
  // 百世配置
  .baishiConfig("partnerId","secretKey",1)
  // 圆通配置
  .yuantongConfig("appkey", "secretKey", "userId", 1)
  // 中通配置
  .zhongtongConfig("companyId", "secretKey", 1)
  // 中通配置
  .jituConfig("apiAccount", "privateKey", "uuid", "customerCode", "customerPwd", 1)
  .build();
ExpressHandlers expressHandlers=new ExpressHandlers(config);
// 快递公司编号，具体查看net.kdks.enums.ExpressCompanyCodeEnum
String expressCompanyNo="SF";
// 轨迹查询参数
ExpressParam param=new ExpressParam();
// 单号必传
param.setExpressNo("SF1028911111316");
// 手机号,顺丰必填(全11位或后4位)
param.setMobile("0728");
// 调用运单轨迹查询
ExpressResponse<ExpressResult> expressResult=expressHandlers.getExpressInfo(param,"SF");
// 下单参数，此处省略赋值，具体查看net.kdks.model.CreateOrderParam
CreateOrderParam createOrderParam=new CreateOrderParam();
// 调用下单
ExpressResponse<OrderResult> orderResult=expressHandlers.createOrder(createOrderParam,"SF");
```



## 3. 参数申请(待完善步骤)

> 提示：多数都需要企业认证，即营业执照(公司或个体工商户)。所以个人用户难以配置并使用该类库。

* 顺丰

  [顺丰开放平台](http://qiao.sf-express.com/)

* 申通

  [申通开放平台](https://open.sto.cn/)

* 圆通

  [圆通开放平台](http://open.yto.net.cn/)

* 中通

  [中通开放平台](https://zop.zto.com/)

* 百世

  [百世开放平台](https://open.800best.com/)

* 极兔

  [极兔开放平台](https://open.jtexpress.com.cn/)

# 示例

* [aggregatelogistics-demo-jfinal]([王泽/aggregatelogistics-demo-jfinal (gitee.com)](https://gitee.com/fuzui/aggregatelogistics-demo-jfinal))

# 反馈或建议

* [Gitee issues](https://gitee.com/fuzui/aggregatelogistics/issues)

* [Github issues](https://github.com/fuzui/aggregatelogistics/issues)

# 致谢

* [JustAuth](小而全而美的第三方登录开源组件)  参考了其部分设计结构与思想
* [halo](https://github.com/halo-dev/halo)  参考了其部分设计结构与思想
* [fastjson](https://github.com/alibaba/fastjson)

* [hutool-http](https://gitee.com/loolly/hutool)