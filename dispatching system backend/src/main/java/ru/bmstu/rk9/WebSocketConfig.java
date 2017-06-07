package ru.bmstu.rk9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.bmstu.rk9.network.websocket.ConveyorMessageHandler;
import ru.bmstu.rk9.network.websocket.MachineMessageHandler;
import ru.bmstu.rk9.network.websocket.RobotMessageHandler;
import ru.bmstu.rk9.network.websocket.StackerMessageHandler;

/**
 * Created by farid on 6/7/17.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new MachineMessageHandler(), "/machine").setAllowedOrigins("*");
    registry.addHandler(new RobotMessageHandler(), "/robot").setAllowedOrigins("*");
    registry.addHandler(new ConveyorMessageHandler(), "/conveyor").setAllowedOrigins("*");
    registry.addHandler(new StackerMessageHandler(), "/stacker").setAllowedOrigins("*");
  }
}
