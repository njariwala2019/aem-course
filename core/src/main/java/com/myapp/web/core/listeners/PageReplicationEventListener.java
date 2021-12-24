package com.myapp.web.core.listeners;

import com.day.cq.replication.ReplicationAction;
import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,
        immediate = true,
        property = {EventConstants.EVENT_TOPIC +"="+ ReplicationAction.EVENT_TOPIC}
)
public class PageReplicationEventListener implements EventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleEvent(Event event) {
        logger.info("Resource event:{} at {}", event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));

        ReplicationAction action = ReplicationAction.fromEvent(event);
        if(action != null){
            logger.info("Replication action:{}",action);
        }
    }
}
