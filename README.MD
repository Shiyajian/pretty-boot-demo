# 这只是一个 DEMO

> 一千个人心中有一千个哈姆雷特，一千个架构师心中有一千种完美架构，而这个项目，是我追求完美路上的一个初号机

## 初衷

&emsp;设计这个项目初衷是对现在项目中的过度封装设计感觉深恶痛绝。本人崇尚简约而又不简单，所以按照自己的思路编写一个优美而又简单的DEMO。希望打造一个从结构领域概念上面条例清晰，从代码结构上轻松易懂，方便开发人员编写和维护，而且有完整的功能和完全性的业务架构。

## 这个 DEMO 所实现的案例

1. 全局的枚举转换，包含国际化处理；
2. 全局异常处理，包含国际化处理；
3. 项目分包的一些想法；
4. 基于 Gradle 替换掉 Maven 的优势；

## 全局国际化配置

&emsp;曾经我一直在想，为什么要国际化呢，我如果做软件呢，肯定是国内的，国际化功能完全用不到呢。突然有一天我灵光乍现。万一呢，万一有老外在咱们国内使用我写的软件呢。这样回过头一想，我觉得国际化功能就必不可少了。所以我开始配置我的国际化方案。

&emsp;既然决定要使用国际化功能了，就应该再思考下一步，哪些部分需要用国际化翻译，哪一部分并不需求呢。最后经过我的一番思考，得出一个规则：<u>凡是应该给用户看到的信息，都应该用国际化处理，否则直接使用中文简单处理</u>。

&emsp;Spring的国际化功能在 **spring-context** 子模块下，对应的接口类为 **MessageSource** 。我的项目基于 **SpringBoot 2.1.2.Release**，在 *springboot* 中，有对于国际化功能的自动配置类和属性类，分别对应 **MessageSourceAutoConfiguration** 和 **MessageSourceProperties** ，通过配置文件，我们可以看到一些已经约定好的配置属性，比如：*basename* 代表的是资源文件对应的文件夹名称，一般位于*resources*文件下面，同时还有缓存过期时间，默认编码格式等，就不详细解释了。我下面列出我的操作步骤：

&emsp;1、配置*MessageSource*。在**application.yml** 文件，我只对默认的Locale进行了修改，其他一切采用的默认配置。如果你需要进行对其他参数进行修改，在 **application.yml** 输入 *spring.message* 开头就可以得到相关提示，或者查找 **WebMvcProperties** 这个类。

```yaml
spring:
  mvc:
    locale: zh_CN
```

​&emsp;2、创建国际化资源文件。创建于*resource*目录下，属性文件命名规则为：*{basename}* 代表默认读取的资源文件，或 *{basename}_{locale}*，代表某种特别区域下面对应的资源文件。关于更多 *Locale* 的知识，请大家自行百度。我的文件结构如下：

```
- resources
	|- application.yml
	|- messages (这个 messages 就是上文所提到的 basename)
		|- messages.properties 
		|- messages_zh_CN.properties
		|- messages_en_US.properties
		......
```

&emsp;3、创建 *Locale* 拦截器并注册。当一个请求过来的时候，我们要进行判断，判断这个请求是需要采取哪种策略进行处理。理所当然，我们最后选择的是基于 *spring* 的 拦截器。这里我们也采用了默认配置，在 **WebMvcAutoConfiguration** 中已经配置好了默认的 *LocaleResolver*，选择 **AcceptHeaderLocaleResolver** 作为默认的解析器，如果发送的请求，在 **header** 中包含 *Accept-Language*值时候，就选择将它对应的值作为本次请求的*Locale*，如果没有的话就使用默认的，我们已经在第1步设置过了。

&emsp;4、编写工具类，**MessageSource** 的实现类受 *spring* 管，在使用的时候，需要进行手动注入，增加一定的复杂度，所以编写了一个工具类，方便后面各处进行调用，代码如下：

```java
public class I18nMessageUtil {

    /**
     * 根据key和参数获取对应的内容信息
     * @param key  在国际化资源文件中对应的key
     * @param args 参数
     * @return 对应的内容信息
     */
    public static String getMessage(@Nonnull String key,@Nullable Object[] args) {
        MessageSource messageSource = (MessageSource) SpringContextHolder.getBean(MessageSource.class);
        Locale locale = RequestContextUtils.getLocale(request());
        return messageSource.getMessage(key, args, locale);
    }
}

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static Object getBean(@Nonnull Class clazz) {
        return ctx.getBean(clazz);
    }
}
```

&emsp;这样，关于国际化部分的配置就完成了，国际化部分的配置将在下面各个功能中都有所体现。

## 全局枚举值处理

to do 
