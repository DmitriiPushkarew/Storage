package org.example.storage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange scenarioExchange() {
        return new FanoutExchange("scenario_exchange");
    }

    @Bean
    public Queue voiceoverQueue() {
        return new Queue("voiceover_queue", true);
    }

    @Bean
    public Queue videoFinderQueue() {
        return new Queue("video_finder_queue", true);
    }

    @Bean
    public Binding bindingServiceOne(FanoutExchange scenarioExchange, Queue voiceoverQueue) {
        return BindingBuilder.bind(voiceoverQueue).to(scenarioExchange);
    }

    @Bean
    public Binding bindingServiceTwo(FanoutExchange scenarioExchange, Queue videoFinderQueue) {
        return BindingBuilder.bind(videoFinderQueue).to(scenarioExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter jsonMessageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
