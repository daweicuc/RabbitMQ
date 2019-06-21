package com.testrabbitmq.mq;

import com.testrabbitmq.mq.Entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void CreateExChange(){
//        以下方法创建ExChange
        amqpAdmin.declareExchange(new DirectExchange("newdeExChange"));
//        创建队列
        amqpAdmin.declareQueue(new Queue("newdequeue",true));
//        创建绑定规则
        amqpAdmin.declareBinding(new Binding("newdequeue",Binding.DestinationType.QUEUE,"newdeExChange","newdeKey",null));
        System.out.println("创建完成");
    }




//发送map类型的数据
    @Test
    public void sendMsg() {
        //object默认当成消息体，只需要传入要发送的对象，自动序列化给rabbitMQ

        Map<String, Object> map = new HashMap<>();
        map.put("msg","testrabbitTemplate");
        map.put("data",Arrays.asList("helloworld",123,true));
        rabbitTemplate.convertAndSend("exchangesdawei1","queue1",map);
    }
    //发送book对象类型的数据
    @Test
    public void sendbook() {
        //object默认当成消息体，只需要传入要发送的对象，自动序列化给rabbitMQ
        Map<String, Object> map = new HashMap<>();
        rabbitTemplate.convertAndSend("exchangesdawei1","queue1",new Book("三国演义","罗贯中"));
    }
//接收数据
    @Test
    public void receive(){
        Object o=rabbitTemplate.receiveAndConvert("1");
        System.out.println(o.getClass());
        System.out.println(o);
    }
}
