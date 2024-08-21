
package eventsDAO;
import java.util.List;
import models.Event;
public interface EventDAO {
    void addEvent(Event event);
    List<Event> getAllEvents();
    Event getEventByName(String eventName);
    void updateEvent(Event event, Event eventUpdate);
    void deleteEvent(String eventName);
}
