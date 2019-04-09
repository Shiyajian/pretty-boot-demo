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

为了表达某一个属性，具备一组可选的范围，我们一般会采用两种方式。枚举类和数据字典，两者具有各自的优点。枚举类写在Java代码中，方便编写相应的判断逻辑，代码可读性高。数据字典，一般保存在数据库，不便于编写判断和分支逻辑，因为数据如果有所变动，那么对应的代码逻辑很有可能失效，强依赖数据库数据的正确性，日常开发中常用做数据分类，打标签使用。

很少有公司能完美的处理好枚举类，更多的时候，会自定义一个枚举转换工具类，或者在枚举类中编写一个静态方法实现Integer转换枚举的方式。比如：

```java
// 静态方法方式
public enum GenderEnum {

	 // 代码略
    
     public static GenderEnum get(int value) {
         for (GenderEnum item : GenderEnum.values()) {
            if (value == item.getValue()) {
                 return item;
             }
         }
         return null;
     }
}
// 工具类方式
public class EnumUtil {

    public static <E extends Enumerable> E of(@Nonnull Class<E> classType, int value) {
        for (E enumConstant : classType.getEnumConstants()) {
            if (value == enumConstant.getValue()) {
                return enumConstant;
            }
        }
        return null;
    }

}

GenderEnum gender = EnumUtil.of(GenderEnum.class,1);
```

这种手动的转换方式很麻烦，所以我们做了一个全局枚举值转换的方案。这个方案可以实现前端通过传递int到服务端，服务端自动转换成枚举类，进行相应的业务判断之后，再以数据的形式存到数据库；我们在查数据的时候，又能将数据库的数字转换成java枚举类，在处理完对应的业务逻辑之后，将枚举和枚举类对应的展示信息一起传递到前台，前台不需要维护这个枚举类和展示信息的对应关系，同时展示信息支持国际化处理，具体的方案如下：

1、基于约定大于配置的原则，我们首先要明确，我们的枚举类的统一编写规则。大概规则如下：

- 每个枚举类有两个字段: int value（存数据库），String key（通过key找对应的i18n属性）。这块需要细细讨论下，枚举值通常存数据库有存int值，也有存String值，各有利弊。存int的好处就是体积小，如果枚举的值是包含规律的，比如-1是删除，0是预处理，1是处理，2是处理完成，那么我们正常查询状态的，我们可以不使用 status in ( 0,1,2)这种方式，而转换为 status >= 0 ; 存String的话，好处就是可读性高，直接能从数据库的值中明白对应的状态，劣势就是占的体积大点。当然这些都是相对的，存int的时候，我们可以完善好注释，也具备好的可读性。如果int换成String，占的体积多的那一点，其实也可以忽略不计的。
- 枚举枚举类需要继承统一接口，提供相应的方法供通用处理枚举时候使用。

下面是枚举接口和一个枚举示例：

```java
public interface Enumerable<E extends Enumerable> {

    /**
     *i18nKey
     * @return i18nKey
     */
    @Nonnull
    String getKey();

    /**
     * 获取最终保存到数据库的值
     * @return 值
     */
    @Nonnull
    int getValue();

    /**
     *i18nKey
     * @return 文本信息
     */
    @Nonnull
    default String getText() {
        return I18nMessageUtil.getMessage(this.getKey(), null);
    }
}
public enum GenderEnum implements Enumerable {

    /** 男 */
    MALE(1, "male"),

    /** 女 */
    FEMALE(2, "female");

    private int value;

    private String key;

    GenderEnum(int value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
```

我们要做的就是，每个我们编写的枚举类，都需要按这样的方式进行编写，然后我们进行对应的配置

2、我们分析下controller层面的数据进和出，如果做好controller层的接受int，自动转换成枚举，在使用了spring MVC框架之后，我们的参数转换都由框架进行处理了，我们首先分析下切入点。前台发送到服务端的请求，一般有参数在url中和body中两种方式为主，分别以get请求和post请求配合@RequestBody为代表。

- 【入参】get方法为代表，请求的MediaType为"application/x-www-form-urlencoded"，此时将 int 转换成枚举，我们注册一个新的Converter，如果spring MVC判断到一个值要转换成我们定义的枚举类对象时，调用我们设定的这个转换器

  ```java
  @Configuration
  public class MvcConfiguration implements WebMvcConfigurer, WebBindingInitializer {
  
      /**
       * [get]请求中，将int值转换成枚举类
       * @param registry
       */
      @Override
      public void addFormatters(FormatterRegistry registry) {
          registry.addConverterFactory(new EnumConverterFactory());
      }
  }
  
  public class EnumConverterFactory implements ConverterFactory<String, Enumerable> {
  
      private final Map<Class, Converter> converterCache = new WeakHashMap<>();
  
      @Override
      @SuppressWarnings({"rawtypes", "unchecked"})
      public <T extends Enumerable> Converter<String, T> getConverter(@Nonnull Class<T> targetType) {
          return converterCache.computeIfAbsent(targetType,
                  k -> converterCache.put(k, new EnumConverter(k))
          );
      }
  
      protected class EnumConverter<T extends Enumerable> implements Converter<Integer, T> {
  
          private final Class<T> enumType;
  
          public EnumConverter(@Nonnull Class<T> enumType) {
              this.enumType = enumType;
          }
  
          @Override
          public T convert(@Nonnull Integer value) {
              return EnumUtil.of(this.enumType, value);
          }
      }
  }
  
  ```

- 【入参】post为代表，将 int 转换成枚举。这块我们和前台达成一个约定，就是所有在body中的数据必须为json格式，那么后台@RequestBody对应的参数的请求的MediaType为"application/application/json"，spring MVC中对于Json格式的数据，默认是用 JacksonHttpMessageConverter，我们知道了这一点，我们只需要修改Jackson对枚举类的序列化和反序列的支持就可以了。在 Jackson 中，SimpleModule 只能对确切的类进行序列化配置，要想以接口的形式，对枚举类进行统一的序列化那就需要自己重新配置序列化的实现，本文中实现了`EnumModule` 专门负责我们自定义枚举类的序列化好反序列化，配置如下：

  ```java
  @Configuration
  @Slf4j
  public class JacksonConfiguration {
  
      /**
       * Jackson的转换器
       * @return
       */
      @Bean
      @Primary
      public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
          final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
          ObjectMapper objectMapper = converter.getObjectMapper();
          // 空字段不序列化，包括list中空对象，和map中value为null的对象
          objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
          // 反序列化时候，遇到多余的字段不失败，忽略
          objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
          // 允许出现特殊字符和转义符
          objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
          // 允许出现单引号
          objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
          SimpleModule customerModule = new SimpleModule();
          customerModule.addDeserializer(String.class, new StringTrimDeserializer(String.class));
          objectMapper.registerModule(customerModule);
          objectMapper.registerModule(new EnumModule());
          converter.setSupportedMediaTypes(ImmutableList.of(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
          return converter;
      }
      
      
  /**
   * @author shiyajian
   * create: 2019-03-21
   */
  public class EnumModule extends SimpleModule {
  
      public EnumModule() {
          super("jacksonEnumTypeModule", Version.unknownVersion());
          this.setDeserializers(new CustomDeserializers());
          this.addSerializer(new EnumSerializer());
      }
  
      private static class CustomDeserializers extends SimpleDeserializers {
          private CustomDeserializers() {
          }
  
          @Override
          @SuppressWarnings({"rawtypes", "unchecked"})
          public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
              // 如果是Enumerable的实现类，调用此序列化方法，否则使用 jackson 默认的序列化方法
              return Enumerable.class.isAssignableFrom(type) ?
                      new EnumDeserializer(type) :
                      super.findEnumDeserializer(type, config, beanDesc);
          }
  
          private static class EnumDeserializer<E extends Enumerable> extends StdScalarDeserializer<E> {
  
              private Class<E> enumType;
  
              private EnumDeserializer(Class<E> clazz) {
                  super(clazz);
                  this.enumType = clazz;
              }
  
              @Override
              public E deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                  // 前台如果传递只传value
                  if (parser.getCurrentToken().isNumeric()) {
                      return EnumUtil.of(this.enumType, parser.getIntValue());
                  }
  
                  // 前台以对象形式传递
                  TreeNode node = parser.getCodec().readTree(parser).get("value");
                  if (!(node instanceof IntNode)) {
                      throw new IllegalArgumentException(" enum value not numeric type");
                  }
                  IntNode intNode = (IntNode) node;
                  return EnumUtil.of(this.enumType, intNode.intValue());
  
              }
          }
      }
  
      private static class EnumSerializer extends StdSerializer<Enumerable> {
  
          private EnumSerializer() {
              super(Enumerable.class);
          }
  
          @Override
          public void serialize(Enumerable enumerable, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
              jsonGenerator.writeStartObject();
              jsonGenerator.writeNumberField("value", enumerable.getValue());
              jsonGenerator.writeStringField("text", enumerable.getText());
              jsonGenerator.writeEndObject();
          }
      }
  }
  
  ```

- 【出参】当我们查询出结果，要展示给前台的时候，我们会对结果集增加@ResponseBody注解，这时候会调用Jackson的序列化方法，在EnumModule中已经有配置。为了避免前端维护枚举值和展示文本的关系，所以值和展示文本都由后台提供。


这样关于入参和出参的配置都完成了，我们可以保证，所有前台传递到后台的 int 都会自动转换成枚举类。如果返回的数据有枚举类，枚举类也会包含值和展示文本，方便简单。

3、存储层关于枚举类的转换。这里选的 ORM 框架为 Mybatis ，但是你如果翻看[官网](http://www.mybatis.org/mybatis-3/zh/configuration.html)，官网的资料也并无法给你提供方案，官网信息没有更新，保留了陈旧的通过枚举隐藏字段name和ordinal的转换，没有一个通用枚举的解决方案。但是通过翻看 github 中的 [issue](https://github.com/mybatis/mybatis-3/issues/970) 和 release 记录，发现在 3.4.5版本中就提供了对应的自定义枚举处理配置，这里通过增加了 mybatis-spring-boot-starter 的依赖，直接配置对应的Yaml 文件就实现了功能。

```yml
application.yml
--
mybatis:
  configuration:
    default-enum-type-handler: github.shiyajian.pretty.config.enums.EnumTypeHandler
```

```java
public class EnumTypeHandler<E extends Enumerable> extends BaseTypeHandler<E> {

    private Class<E> enumType;

    public EnumTypeHandler() { /* instance */ }


    public EnumTypeHandler(@Nonnull Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, e.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return rs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return rs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return cs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

}
```

这样我们就完成了从前台页面到业务代码到数据库的存储，从数据库查询到业务代码再到页面的枚举类转换。整个项目中完全不需要再手动去处理枚举类了。我们的开发流程简单了很多。

## 全局异常数据

todo
