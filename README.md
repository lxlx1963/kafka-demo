# kafka-demo
该例子，会出现消息消费延迟或者卡顿的问题
如下面情况，数组里的值都为空：
 partitions assigned: []
 partitions revoked: []
此问题为spring-kafka的一个bug
spring-kafka高版本已解决此问题
 解决方案：
升级springboot版本，将2.1.13版本升级到2.2.6，springboot会自动升级spring-kafka对应的版本
 
