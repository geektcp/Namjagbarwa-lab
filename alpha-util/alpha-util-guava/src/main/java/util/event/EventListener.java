package util.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author tanghaiyang on 2019/7/21.
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
