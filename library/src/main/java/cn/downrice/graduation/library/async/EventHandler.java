package cn.downrice.graduation.library.async;

import java.util.List;

public interface EventHandler {
    /**
     * 执行体的具体工作
     * @param model
     */
    void doHandle(EventModel model);

    /**
     * 执行体所支持的事件类型列表
     * @return
     */
    List<EventType> getSupportEventTypes();
}
