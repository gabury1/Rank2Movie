package code.Config.WebSocket;

import org.springframework.boot.autoconfigure.graphql.servlet.GraphQlWebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer
{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), "/chat")
                .addHandler(LetterManager(), "/letter")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();
    }

    @Bean
    public WebSocketHandler chatHandler()
    {
        return new ChattingHandler();
    }

    @Bean
    public WebSocketHandler LetterManager ()
    {

        return new LetterHandler();
    }
}
