package ject.componote.config;

import ject.componote.dto.create.request.impl.CommentLikeNotificationCreateRequest;
import ject.componote.dto.create.request.impl.NestedReplyNotificationCreateRequest;
import ject.componote.dto.create.request.impl.NoticeNotificationCreateRequest;
import ject.componote.dto.create.request.impl.RootReplyNotificationCreateRequest;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue emailQueue() {
        return new Queue("emailQueue");
    }

    @Bean
    public Queue fcmQueue() {
        return new Queue("fcmQueue");
    }

    @Bean
    public Exchange emailEexchange() {
        return new TopicExchange("notification-mail");
    }

    @Bean
    public Exchange fcmExchange() {
        return new TopicExchange("notification-fcm");
    }

    @Bean
    public Binding emailBinding(final Queue emailQueue, final Exchange emailEexchange) {
        return BindingBuilder.bind(emailQueue)
                .to(emailEexchange)
                .with("email")
                .noargs();
    }

    @Bean
    public Binding fcmBinding(final Queue fcmQueue, final Exchange fcmExchange) {
        return BindingBuilder.bind(fcmQueue)
                .to(fcmExchange)
                .with("fcm")
                .noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                         final MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(final ConnectionFactory connectionFactory,
                                                                            final MessageConverter messageConverter) {
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAdviceChain(
                RetryInterceptorBuilder.stateless()
                        .maxAttempts(3)
                        .backOffOptions(Duration.ofSeconds(3L).toMillis(), 2, Duration.ofSeconds(10L).toMillis())
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .build()
        );
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter(final ClassMapper classMapper) {
        final Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setClassMapper(classMapper);
        return messageConverter;
    }

    @Bean
    public ClassMapper classMapper(final Map<String, Class<?>> idClassMapping) {
        final DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTrustedPackages("*");
        typeMapper.setIdClassMapping(idClassMapping);
        return typeMapper;
    }

    @Bean
    public Map<String, Class<?>> idClassMapping() {
        final Map<String, Class<?>> idClassMapping = new HashMap<>();
        // 왜 이건 명시해야 될까? 파일 서버는 안해도 되는데
        idClassMapping.put("ject.componote.infra.notification.dto.create.request.RootReplyNotificationCreateRequest", RootReplyNotificationCreateRequest.class);
        idClassMapping.put("ject.componote.infra.notification.dto.create.request.NestedReplyNotificationCreateRequest", NestedReplyNotificationCreateRequest.class);
        idClassMapping.put("ject.componote.infra.notification.dto.create.request.NoticeNotificationCreateRequest", NoticeNotificationCreateRequest.class);
        idClassMapping.put("ject.componote.infra.notification.dto.create.request.CommentLikeNotificationCreateRequest", CommentLikeNotificationCreateRequest.class);
        return idClassMapping;
    }
}
