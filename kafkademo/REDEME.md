## kafka下载
https://mirrors.tuna.tsinghua.edu.cn/apache/kafka/2.4.0/kafka_2.12-2.4.0.tgz
## 安装
将文件夹解压即可，注意不要解压到目录名有空格的文件夹中，会找不到运行的类。

## kafka简单使用，

在解压后的kakfa主目录中使用命令行运行以下命令行参数

1. 启动zookeeper
   `bin\windows\zookeeper-server-start.bat config\zookeeper.properties`
2. 启动kafka
   `bin\windows\kafka-server-start.bat config\server.properties`
3. 创建topic demo
   `bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic demo`
4. 查看topic列表
   `bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092`
5. 启动生产者 producer
   `bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic demo`
6. 启动消费者 customer
   `bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic demo --from-beginning`

## 集群部署

再启动两个节点

```properties
bin\windows\kafka-server-start.bat config\server-1.properties
bin\windows\kafka-server-start.bat config\server-2.properties
```

现在创建一个复制因子为3的新主题

```
bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic my-replicated-topic
```

但现在我们有了集群我们怎么知道哪个代理在做什么

```
bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic my-replicated-topic
```

![image-20200305230540721](C:\Users\coy\AppData\Roaming\Typora\typora-user-images\image-20200305230540721.png)

创建my-replicated-topic主题的Producer

```
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic my-replicated-topic
```

创建my-replicated-topic主题的Consumer

```
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-replicated-topic --from-beginning
```

现在让我们测试一下容错能力。broker0扮演了leader的角色，所以我们杀了它

```
wmic process where "caption = 'java.exe' and commandline like '%server.properties%'" get processid
```

![image-20200305230413527](C:\Users\coy\AppData\Roaming\Typora\typora-user-images\image-20200305230413527.png)

怎么杀了leader报错呢。。

```
taskkill /pid 6016 /f
```

![image-20200305232151100](C:\Users\coy\AppData\Roaming\Typora\typora-user-images\image-20200305232151100.png)

![image-20200305232404070](C:\Users\coy\AppData\Roaming\Typora\typora-user-images\image-20200305232404070.png)

使用Kafka Connect导入/导出数据

```
> bin\windows\connect-standalone.bat config/connect-standalone.properties config/connect-file-source.properties config/connect-file-sink.properties
```

![image-20200305233741668](C:\Users\coy\AppData\Roaming\Typora\typora-user-images\image-20200305233741668.png)

请注意，数据存储在Kafka主题connect-test中，因此我们也可以运行控制台使用者以查看该主题中的数据（或使用自定义使用者代码进行处理）

```
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic connect-test --from-beginning
```

![image-20200305233723269](C:\Users\coy\AppData\Roaming\Typora\typora-user-images\image-20200305233723269.png)

## kafka配置

```properties
#kafka相关配置
spring.kafka.bootstrap-servers={kafka实例ip地址}:9092
#设置一个默认组
spring.kafka.consumer.group-id=defaultGroup
#key-value序列化反序列化
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.batch-size=65536
spring.kafka.producer.buffer-memory=524288
```



参考连接
kafka 在windows 平台的搭建和简单实用
https://blog.csdn.net/woshixiazaizhe/article/details/80610432
spring boot + kafka 使用详细步骤
https://blog.csdn.net/lcj_star/article/details/77337640?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
Spring Boot + Kafka的使用
https://blog.csdn.net/Black1499/article/details/90474929