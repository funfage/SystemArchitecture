[TOC]

# Json学习

## Jackson的使用

Jackson官网教程：<https://github.com/FasterXML/jackson-docs>

Baeldung Jackson JSON教程：<https://www.baeldung.com/jackson>

### 基本注解说明以及示例

官网说明：<https://www.baeldung.com/jackson-annotations>

参考中文教程：<https://www.jianshu.com/p/201943f8d579>

实例：<https://www.baeldung.com/jackson-annotations>

#### 属性命名

- `@JsonProperty`：用来表示外部属性名字，就是使用别名序列化，而不是对象的名字。当我们希望序列化后的属性名不是驼峰命名时可以用这个属性来指定序列化后的别名。或者是setter和getter方法的方法名不是标准的命名。该注解包含三个属性：
  - `value`：指定使用的名字
  - `index`：索引值，暂不知道用途
  - `defaultValue`：指定默认值

```java
public class MyBean {
    public int id;
    private String name;
 
    @JsonProperty("name")
    public void setTheName(String name) {
        this.name = name;
    }
 
    @JsonProperty("name")
    public String getTheName() {
        return name;
    }
}
==============================================
public class MyBean {
    public int id;
    
    @JsonProperty("theName")
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }
 
    public String getName() {
        return name;
    }
}
```

#### 属性包含

- `@JsonAutoDetect`：实体类使用的注解，用于重新设置实体类中属性是否可见
  - 为不同访问器类型使用不同设置
    - `creatorVisibility`：指定构造方法的可见性
    - `fieldVisibility`：指定属性的可见性
    - `getterVisibility`：指定以“get”开头的无参数值返回方法可见性
    - `isGetterVisibility`：指定布尔/布尔返回，名称以“is”开头的无参数方法可见性
    - `setterVisibility`：指定以set开头传递单参数的方法的可见性
  - 指定可见性的层次
    - ANY：所有都可见
    - NON_PRIVAT：除了private修饰的都可见
    - PROTECTED_AND_PUBLIC：protected和public修饰的都可见
    - PUBLIC_ONLY：只有public修饰的可见
    - NONE：所有都不可见
    - DEFAULT：pseudo-value indicating "use global defaults" (also default value for above-listed annotation properties)

```java
//使得能够序列化private属性
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class PrivateBean {
  private int id;
  private String name;
}
```

- `@JsonIgnore`：用于忽略指定的属性

```java
public class BeanWithIgnore {
	//忽略id属性
    @JsonIgnore
    public int id;
    public String name;
}
```

- `@JsonIgnoreProperties`：使用在类上，用于忽略一系列指定的属性，或者忽略指定的未知属性
  - 在序列化时，使用@JsonIgnoreProperties({"prop1", "prop2"})，可以忽略列出来的属性
  - 在反序列化使，使用@JsonIgnoreProperties(ignoreUnknown=true)，可以忽略没有setters/getters方法的属性

```java
@JsonIgnoreProperties({ "id" })
public class BeanWithIgnore {
	public int id;
    public String name;
}
```

- `@JsonIgnoreType`：使用在类上，表示该类的所有属性都被忽略

```java
public class User {
    public int id;
    public Name name;
 
    @JsonIgnoreType
    public static class Name {
        public String firstName;
        public String lastName;
    }
}
```

#### 属性文档、元数据

- `@JsonPropertyDescription`：用来对属性进行描述

#### 反序列化和序列化同时作用

- `@JsonFormat`：实体类/属性使用的注解，在序列化或者反序列化的时候，指定属性格式化日期/时间。有以下属性：
  - lenient(boolean)
  - locale(String)
  - shape(Shape)
  - timezone(String)
  - with(JsonFormat.Feature[])
  - wihtout(JsonFormat.Feature[])

```java
public class EventWithFormat {
    public String name;
 
    @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd-MM-yyyy hh:mm:ss")
    public Date eventDate;
}
```

- `@JsonUnwrapped`：作用在属性字段或方法上，用来将**子JSON对象**的属性添加到封闭的JSON对象。
  - 如果没有@JsonUnwrapped，序列化后将为{"id":111,"name":{"firstName":"张","secondName":"三"}}
  - 反之：{"id":111,"firstName":"张","secondName":"三"}

```java
public class UnwrappedUser {
    public int id;
 
    @JsonUnwrapped
    public Name name;
 
    public static class Name {
        public String firstName;
        public String lastName;
    }
}
====================================================
@Test
public void whenSerializingUsingJsonUnwrapped_thenCorrect()
  throws JsonProcessingException, ParseException {
    UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
    UnwrappedUser user = new UnwrappedUser(1, name);
    String result = new ObjectMapper().writeValueAsString(user);
    System.out.println(result);
}
====================================================
执行结果为：
{
    "id":1,
    "firstName":"John",
    "lastName":"Doe"
}    
```

- `@JsonView`：当controller返回实体信息时会参考`@JsonView`来对实体中指定属性进行序列化。以下是JsonVIew的用法，[参考博客](https://blog.csdn.net/cauchy6317/article/details/92788938)

**业务描述：**有一个Item类有id，itemName，ownerName三个属性，若希望序列化该实体类时在某些情况过滤掉ownerName属性，而某些情况全部属性都不过滤，则用法如下：

**视图类：**

```java
public class Views {

    public static class Public{}
    public static class Internal extends Public{}

}
```

Public作为公共视图，Internal作为内部视图。Internal继承了Public，也就是说Internal会返回所有被@JsonView(Public.class)注解的属性。

**Item实体：**

```java
public class Item {
    @JsonView(Views.Public.class)
    public int id;
 
    @JsonView(Views.Public.class)
    public String itemName;
 
    @JsonView(Views.Internal.class)
    public String ownerName;
}
```

该实体类中id和itemName使用了@JsonView(Views.Public.class)，ownerName使用了@JsonView(Views.Internal.class)

**controller层：**

```java
@RestController
@RequestMapping("/item")
public class ItemController {

    /**
     * 返回的json中不带有ownerName
     * @return
     */
    @GetMapping("/get1")
    @JsonView(Views.Public.class)
    public List<Item> getAllItemsWithoutOwnerName() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "项目1", "张三");
        Item item2 = new Item(2, "项目2", "李四");
        items.add(item1);
        items.add(item2);
        return items;
    }

    /**
     * 返回的json中带有ownerName
     * @return
     */
    @GetMapping("/get2")
    @JsonView(Views.Internal.class)
    public List<Item> getAllItemsWithOwnerName() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "项目1", "张三");
        Item item2 = new Item(2, "项目2", "李四");
        items.add(item1);
        items.add(item2);
        return items;
    }

}
```

在controller层中方法上也使用两种不同@JsonView

**http://localhost:1000/get1返回结果：**

```json
[
    {
        "id": 1,
        "itemName": "项目1"
    },
    {
        "id": 2,
        "itemName": "项目2"
    }
]
```

**http://localhost:1000/get2返回结果**：

```json
[
    {
        "id": 1,
        "itemName": "项目1",
        "ownerName": "张三"
    },
    {
        "id": 2,
        "itemName": "项目2",
        "ownerName": "李四"
    }
]
```

**总结：**

> 使用@JsonView(Views.Public.class)会过滤掉ownerName属性
>
> 使用@JsonView(Views.Internal.class)会返回所有属性

#### 反序列化时作用

- `@JacksonInject`：属性的值是通过注入的方式获取而不从json数据中获取

```java
public class BeanWithInject {
    @JacksonInject
    public int id;
     
    public String name;
}
===============================================
    @Test
public void whenDeserializingUsingJsonInject_thenCorrect()
  throws IOException {
  
    String json = "{\"name\":\"My bean\"}";
     
    InjectableValues inject = new InjectableValues.Std()
      .addValue(int.class, 1);
    //id值将以注入的方式获得
    BeanWithInject bean = new ObjectMapper().reader(inject)
      .forType(BeanWithInject.class)
      .readValue(json);
     
    assertEquals("My bean", bean.name);
    assertEquals(1, bean.id);
}
```

- `@JsonAnySetter`：用在含有两个参数的方法上，在反序列化时通过该方法为json中的属性赋值。我们可以在类中定义一个Map属性，然后为该属性赋上键值。

实体类：

```java
public class ExtendableBean {
    public String name;
    private Map<String, String> properties;
 
    //通过该方法，在反序列化时给properties赋上键值
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }
}
```

json数据为：

```json
{
    "name":"My bean",
    "attr2":"val2",
    "attr1":"val1"
}
```

测试方法：

```java
@Test
public void whenDeserializingUsingJsonAnySetter_thenCorrect()
  throws IOException {
    String json
      = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";
 
    ExtendableBean bean = new ObjectMapper()
      .readerFor(ExtendableBean.class)
      .readValue(json);
     
    assertEquals("My bean", bean.name);
    //测试成功，说明在反序列化时getProperties成功注入键值
    assertEquals("val2", bean.getProperties().get("attr2"));
}
```

- `@JsonCreator`：指定的构造方法或静态工厂方法，在反序列化的时候通过该方法来创建实例。

json数据：

```json
{
    "id":1,
    "theName":"My bean"
}
However, t
```

实体类：

```java
public class BeanWithCreator {
    public int id;
    public String name;
 
    @JsonCreator
    public BeanWithCreator(
      @JsonProperty("id") int id, 
      @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }
}
```

由于在实体类中没有theName属性，只有一个那么属性，可以通过在构造方法上加上*@JsonCreator*注解，同时使用*@JsonProperty*注解将theName映射到name属性。

测试：

```java
@Test
public void whenDeserializingUsingJsonCreator_thenCorrect()
  throws IOException {
  
    String json = "{\"id\":1,\"theName\":\"My bean\"}";
 
    BeanWithCreator bean = new ObjectMapper()
      .readerFor(BeanWithCreator.class)
      .readValue(json);
    assertEquals("My bean", bean.name);
}
```

- `@JsonEnumDefaultValue`：在反序列化未知枚举值时，用于定义默认值。需要启用配置：READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE

#### 序列化时作用

- `@JsonAnyGetter`：允许对Map属性进行序列化

实体类：

```java
public class ExtendableBean {
    public String name;
    private Map<String, String> properties;
 
    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
```

测试代码：

```java
@Test
public void whenSerializingUsingJsonAnyGetter_thenCorrect()
  throws JsonProcessingException {
  
    ExtendableBean bean = new ExtendableBean("My bean");
    bean.add("attr1", "val1");
    bean.add("attr2", "val2");
 
    String result = new ObjectMapper().writeValueAsString(bean);
  
    assertThat(result, containsString("attr1"));
    assertThat(result, containsString("val1"));
}
```

序列化后的结果：

```json
{
    "name":"My bean",
    "attr2":"val2",
    "attr1":"val1"
}
```

- `@JsonGetter`：对get方法与属性不一致的情况进行序列化

```java
public class MyBean {
    public int id;
    private String name;
 
    @JsonGetter("name")
    public String getTheName() {
        return name;
    }
}
```

MyBean实体类中有name属性但是没有getName方法，通过在getTheName方法上加@JsonGetter("name")注解可以实现对name属性进行序列化。

- `@JsonPropertyOrder`：用在类上，指明一个或多个需要序列化的属性

```java
@JsonPropertyOrder({ "name", "id" })
public class MyBean {
    public int id;
    public String name;
}
```

测试结果：

```json
{
    "name":"My bean",
    "id":1
}
```

- `@JsonRawValue`：按照原样序列化属性而没有转义或修饰，有点抽象，看例子：

实体类：

```java
public class RawBean {
    public String name;
 
    @JsonRawValue
    public String json;
}
```

测试代码：

```java
@Test
public void whenSerializingUsingJsonRawValue_thenCorrect()
  throws JsonProcessingException {
  
    RawBean bean = new RawBean("My bean", "{\"attr\":false}");
 
    String result = new ObjectMapper().writeValueAsString(bean);
    assertThat(result, containsString("My bean"));
    assertThat(result, containsString("{\"attr\":false}"));
}
```

在序列化json属性的值"{\\"attr\\":false}"时，由于有转义字符\，如果不希望结果包含转义字符，则可以在json属性上加@JsonRawValue注解。

- `@JsonValue`：指定属性的值来对实体进行序列化

实体类：

```java
public enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type 2");
 
    private Integer id;
    private String name;
 
    // standard constructors
 
    @JsonValue
    public String getName() {
        return name;
    }
}
```

该枚举类通过name属性来对指定的枚举进行序列化

测试类：

```java
@Test
public void whenSerializingUsingJsonValue_thenCorrect()
  throws JsonParseException, IOException {
  
    String enumAsString = new ObjectMapper()
      .writeValueAsString(TypeEnumWithValue.TYPE1);
 	
    assertThat(enumAsString, is(""Type A""));
}
```

测试结果：

```
"Type A"
```

- `@JsonRootName`：在序列化时对实体序列化结果赋给一个根属性

例如User实体序列化后的json数据为：

```java
{
    "id": 1,
    "name": "John"
}
```

如果想用一个user属性来进行包裹，结果如下

```json
{
    "user":{
        "id":1,
        "name":"John"
    }
}
```

则具体操作为：

定义一个实体类，加上@JsonRootName(value = "user")注解

```java
@JsonRootName(value = "user")
public class UserWithRoot {
    public int id;
    public String name;
}
```

测试代码：

```java
@Test
public void whenSerializingUsingJsonRootName_thenCorrect()
  throws JsonProcessingException {
  
    UserWithRoot user = new UserWithRoot(1, "John");
 
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
    String result = mapper.writeValueAsString(user);
 
    assertThat(result, containsString("John"));
    assertThat(result, containsString("user"));
}
```

输出结果为：

```json
{
    "user":{
        "id":1,
        "name":"John"
    }
}
```

#### 类型处理

- `@JsonSubTypes`：指定注解类型的子类型
- `@JsonTypeInfo`：指定属性序列化时需要包含的详细信息，相关的属性说明参考博客：[@JsonTypeInfo 多态类型处理](https://www.jianshu.com/p/a21f1633d79c)
- `@JsonTypeName`：指定实体类序列化时的逻辑名称

使用以上三种注解来序列化实体类Zoo，其中Zoo实体中又包含Dog实体和Cat实体。

```java
public class Zoo {
    public Animal animal;
 
    @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME, 
      include = As.PROPERTY, 
      property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public static class Animal {
        public String name;
    }
 
    @JsonTypeName("dog")
    public static class Dog extends Animal {
        public double barkVolume;
    }
 
    @JsonTypeName("cat")
    public static class Cat extends Animal {
        boolean likesCream;
        public int lives;
    }
}
```

序列化测试代码：

```java
@Test
public void whenSerializingPolymorphic_thenCorrect()
  throws JsonProcessingException {
    Zoo.Dog dog = new Zoo.Dog("lacy");
    Zoo zoo = new Zoo(dog);
 
    String result = new ObjectMapper()
      .writeValueAsString(zoo);
 
    assertThat(result, containsString("type"));
    assertThat(result, containsString("dog"));
}
```

以下是Zoo和Dog实体一起序列化后的结果

```json
{
    "animal": {
        "type": "dog",
        "name": "lacy",
        "barkVolume": 0
    }
}
```

反序列化测试代码：

输入的json数据：

```json
{
    "animal":{
        "name":"lacy",
        "type":"cat"
    }
}
```

测试代码：

```json
@Test
public void whenDeserializingPolymorphic_thenCorrect()
throws IOException {
    String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";
 
    Zoo zoo = new ObjectMapper()
      .readerFor(Zoo.class)
      .readValue(json);
 	//测试通过
    assertEquals("lacy", zoo.animal.name);
    assertEquals(Zoo.Cat.class, zoo.animal.getClass());
}
```

### 高级注解说明以及示例

参考网站：<https://www.baeldung.com/jackson-advanced-annotations>