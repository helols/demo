package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.EventStatus;
import me.whiteship.demo.domain.SimpleEvent;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author keesun
 */

public class EventServiceTest {

    EventService eventService = new SimpleEventService();

    @Test
    public void simpleEvent() {
        SimpleEvent validEvent = new SimpleEvent();
        validEvent.setName("일회성 이벤트");
        validEvent.setStartDateTime(LocalDateTime.of(2016, 11, 14, 10, 0));
        validEvent.setStartDateTime(LocalDateTime.of(2016, 11, 14, 20, 0));
        validEvent.setDescription("테스트 이벤트");

        Event createdEvent = eventService.create(validEvent);

        assertNotNull(createdEvent);
        assertNotNull(createdEvent.getId());
        assertEquals(EventStatus.PENDING, createdEvent.getEventStatus());
    }

    @Test(expected = EventCannotCreateException.class)
    public void simpleEvent_withoutRequiredField() {
        SimpleEvent withoutRequiredFields = new SimpleEvent();
        eventService.create(withoutRequiredFields);
    }

}