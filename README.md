# Conference Track Management

>非常感谢您在百忙之中抽出时间来阅读我的设计，在此之前我先介绍一下它的运行环境和运行方式。

**您需要的环境**

>该实例是基于maven + java + junit开发的，因此在运用之前，您需要安装JDK v1.6或以上版本以及Maven工具，我将不去阐述如何安装它们(因为您比我更熟练)，但以下的操作我将默认为您已经成功将它们安装。

**运行程序**

>打开Shell并更改工作目录为项目的根目录，然后执行`mvn install`命令执行相关库的安装，安装完成后执行`mvn compile`编译为可执行文件，之后我们便可以运行该项目了。输入`mvn test`(这原本是用于测试的，而我在maven中绑定了测试时运行项目，因此是直接使用它来运行程序并查看结果)指令运行程序，在测试结果的后面我们就可以找到项目运行中的输出结果了。


**项目的结构设计**

>项目名称为:meeting,包名前缀为:com.thoughtworks.meeting,在此包目录下包含项目的运行入口程序**App.java**以及项目的配置文件**config.properties**和项目的输入文件**input.txt**,其他程序分别分散在各个包中，本实例中共包含**build**,**event**,**io**,**parser**,**utils**共5个包。**build**包中只有一个类文件，它负责整合项目的输入数据然后解析到生成结果和输出之间的调度；**event**包负责生成事件和生成结果；**io**包负责读取配置和输入数据；**parser**包负责解析输入数据然后调用**event**包生成事件；**utils**包负责一些公用的静态方法，比如时间函数和随机函数。

##**整体设计**##

*io设计*

>工欲善其事，必先利其器。这个实例的目的在于读取输入的数据并按照要求随机生成事件列表，因此我们首先设计程序的输入(放置在io
包中),由于它的功能相对简单，只需要将数据读取到内存中即可，为了避免不必要的内存开销，我们考虑设置一个静态方法或者设置一个
单例来读取配置和事件的数据，事件的数据我们只需要在程序开始的时候读取一次即可，因此直接设置一个静态方法来读取即可。而配置
中的数据我们需要在多个地方来引用它，因此我们考虑使用**单例模式**，为了防止多次实例化该读取类，需要引入锁的机制，但我们并不会
修改输入的数据文件和配置文件本身，因此不需要给读取类加锁。读取配置文件在ConfigReader类初始化时进行。

    public synchronized static ConfigReader getInstance(){
        if (instance == null){
            instance = new ConfigReader();
        }
        return instance;
    }

>提供外部程序获取配置参数的值的接口

    public String get(String key) {
        return configs.get(key);
    }
    ...


*event设计*
>从input的数据中来看，我们需要两个属性值，一个是事件的名称，一个是事件的持续时间。因此我们可以很容易的设计出event的存储
结构。但是输入和输出的事件中，所包含的信息并不是完全相同的，因为在输出的信息中还包含了事件的开始时间，因此我们可以设计两
个结构，一个是输入类，一个是输出类，输出结构继承自输入类。从给定的条件中来看，一个track包含了4种事件，分别是上午、午餐、下午和
网络事件（把它当作晚上事件），因此我们可以考虑将它们分别构造，但是必须保证它们的先后顺序，将生成的事件放在一个有序表中来
存储，然后外部程序来访问这个有序表即可。此时，我们可以选择使用**建造者模式**来构造一个track，并设计出它的接口:

    public interface EventGenerator {
        public void generateMorningEvent();
        public void generateLunchEvent();
        public void generateAfternoonEvent();
        public void generateEveningEvent();
        public List<EventResult> getTrack();
    }

>然后在实现类EventGeneratorImpl中:

    public List<EventResult> getTrack(){
        this.generateMorningEvent();
        this.generateLunchEvent();
        this.generateAfternoonEvent();
        this.generateEveningEvent();
        return this.track;
    }

>逻辑部分交给各种事件的生成方法中来处理就好了。但此时还是不够，因为它要求网络事件必须要在16~17点之间开始，因此我们还需要
>对事件是否符合时间和事件类型作相应的处理，但是网络事件并不是由输入文本读入的，因此只需要对EveningEvent作时间处理就好了，
>但是这样的设计无法适应外部需求的变更，因此我们考虑设计一个事件处理器链来处理特殊情况，此处我们引入**职责链模式**，我们给每
>一个EventGenerator都设置一个事件控制器链，当有特殊要求生成时，我们只需要编写一个新的事件控制器并把它加入相应的控制器链
>中即可。

    public abstract class Controller{
        protected Controller successor; //下一个处理控制器
        protected Event event;
        protected long startTime;
        ... //省略构造方法
        public void setSuccessor(Controller controller){
            this.successor = controller;
        }
        public abstract boolean process();//处理事件
    }

> 每有一个新需求，我们只需要新编写一个控制器类继承自Controller并实现proccess方法即可:

    public boolean proccess() {
        ...//这里是事件处理逻辑
        if (this.successor != null)
            //如果有下一个控制器，则交给下一个控制器处理
            return this.successor.process();
        else
            return true;
    }

> 这样一来，即使外部条件变更，我们也可以很容易的添加处理代码了。
