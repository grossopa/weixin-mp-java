weixin-mp-java
基于Java，Spring，Maven实现的微信公众平台一整套代码，从前端Controller到后端的Dao的实现<br />
==============

1.0.1 2013-1月更新:
支持上传下载多媒体文件
支持接收消息(语音似乎总有问题,同时收到来自微信两个服务器的空的POST的请求,论坛上也有很多人反映此情况)
支持用户管理
支持自定义菜单CRD
支持推广支持接口

强化测试代码
优化代码结构,增加WxMessageHandlerIfc, 只要实现该接口的所有spring bean在收到消息后都会被自动调用.

==============

实现功能：消息接口,通用接口和菜单接口（没有内测号无法测试）<br />

==============

由于涉及的框架比较杂乱，在此一一解释：<br />

1. 简便实用的前置条件：<br />
   你的项目是基于Spring，Maven，Hibernate架构；<br />
   你的项目至少有一个已经存在的配置文件；<br />
   需要在配置文件(例子：application.properties)中添加<br />
     wx_token=your_token<br />
     wx_appid=asdf<br />
     wx_appsecret=secret<br />
   没有在线的Maven仓库，强烈建议clone代码到本地作为子工程使用；<br />

2. 如果你是通过spring-annotation配置bean的话，那么只要在你的Spring xml配置文件里加入以下两句便可：<br />
   	&lt;context:component-scan base-package="com.hamster.weixinmp" /&gt;<br />
	&lt;util:properties id="wxProperties" location="classpath:/application.properties"/&gt;<br />
   如果没有util的话，在beans xml声明中加入：<br />
      xmlns:util="http://www.springframework.org/schema/util"<br />
      xsi:schemaLocation="…..<br />
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.1.xsd"<br />
   在org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean的packageToScan中添加org.hamster.weixinmp.dao

3. 如果不想用数据库，那么只扫描com.hamster.weixinmp.service和com.hamster.weixinmp.controller即可，所有的dao在wxService中配置模式均为可选，如果没有注入，则不会执行存储操作；<br />

4. 项目使用了lombok生成Getter/Setter, toString, hashCode, equals方法，lombok有eclipse插件，具体怎么安装请看这里：http://projectlombok.org/download.html，如果不想用lombok的话那么就手动删掉那些注解并用eclipse等工具重新生成一下这些方法便可。<br />

5. 如果你的项目是通过xml的方式配置的话，你需要将所有的dao，service和controller配置到xml中（浩大的工程= =）<br />

6. 数据库的前缀为wx_，一般来说不会有冲突，真冲突了那就自己手动改改吧，反正也不麻烦<br />

7. 数据库有些额外的字段，比如自增长的id，created_date等，用不到就无视吧<br />

8. 如果你不用maven的话……那就把java代码都拷贝到自己的工程里面去吧……<br />