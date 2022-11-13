package org.mybatis.jpetstore.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Chatconfig implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHanlder;

    @Autowired
    public Chatconfig(StompHandler stompHanlder) {
        this.stompHanlder = stompHanlder;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         *
         * 소켓 연결 uri
         * 소켓을 연결할때 통신 이용
         * CONNECT : 연결 중
         * CONNECTED : 연결 성공
         * ERROR : 연결 실패  setAllowedOriginPatterns("*") CORS 설정
         *
         * */
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         *
         * Stomp를 사용하기 위한 Message Broker 설정해주는 메서드
         * enableSimpleBroker : 메시지를 받을 때, 경로를 설정해주는 함수이다.
         * 스프링에서 제공해주는 내장 브로커
         * "/queue" -> 1:1
         * "/topic" -> 1:N
         * api에 해당 queue 또는 topic이 있을 경우 브로커가 가로챈다.
         * 클라이언트가 메세지를 보낼 때, 경로 앞에 "/app"이 있으면 Broker에게 보내짐
         *
         * */

        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHanlder);
    }

}
