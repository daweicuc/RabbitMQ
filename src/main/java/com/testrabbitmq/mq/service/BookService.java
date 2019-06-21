package com.testrabbitmq.mq.service;


import com.testrabbitmq.mq.Entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @ClassName BppkService
 * @Description TODO
 * @Author Mike
 * @Date 2019/6/21 14:06
 * @Version 1.0
 */
@Service
public class BookService {
//    收到消息体的信息
//    @RabbitListener(queues = "1")
//    public void receive(Book book){
//        System.out.println("您已经收到消息"+book);
//    }
//    收到消息体+消息头的信息
    @RabbitListener(queues = "1")
    public void receive2(Message message){
        System.out.println("您已经收到消息"+ message.getBody());
        System.out.println("您已经收到消息"+message.getMessageProperties());
        if(message.getBody().equals("exception")){
            String nullStr = null;
            nullStr.toString();
        }
    }
}
