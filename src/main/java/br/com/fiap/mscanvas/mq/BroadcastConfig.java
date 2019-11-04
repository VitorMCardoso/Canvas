package br.com.fiap.mscanvas.mq;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BroadcastConfig {

    private static final boolean NON_DURABLE = false;

    public final static String FANOUT_QUEUE_1_NAME = "amqp-simple.fanout.queue";
    public final static String FANOUT_EXCHANGE_NAME = "amqp-simple.fanout.exchange";

    @Bean
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue(FANOUT_QUEUE_1_NAME, NON_DURABLE);

        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_NAME, NON_DURABLE, false);

        return new Declarables(fanoutQueue1, fanoutExchange, BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange));
    }

}
