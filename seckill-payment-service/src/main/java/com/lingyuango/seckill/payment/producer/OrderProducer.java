package com.lingyuango.seckill.payment.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import javax.annotation.PostConstruct;

/**
 * @author ChaconneLuo
 */

@Component
public class OrderProducer {

    //生产者的组名
    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    //NameServer 地址
    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    private DefaultMQProducer producer;

    public DefaultMQProducer getProducer() {
        return this.producer;
    }

    @PostConstruct
    public void init() {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
