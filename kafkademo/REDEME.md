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