package ru.bmstu.rk9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.bmstu.rk9.network.services.TaskService;
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
  final TaskService taskService;

  @Autowired
  public WebSocketConfig(TaskService taskService) {
    this.taskService = taskService;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new MachineMessageHandler(taskService),
        "/" + Devices.MILLING_MACHINE, "/" + Devices.LATHE_MACHINE).setAllowedOrigins("*");
    registry.addHandler(new RobotMessageHandler(taskService),
        "/" + Devices.MILLING_ROBOT, "/" + Devices.LATHE_ROBOT).setAllowedOrigins("*");
    registry.addHandler(new ConveyorMessageHandler(taskService),
        "/" + Devices.CONVEYOR).setAllowedOrigins("*");
    registry.addHandler(new StackerMessageHandler(taskService),
        "/" + Devices.STACKER).setAllowedOrigins("*");
  }
}
