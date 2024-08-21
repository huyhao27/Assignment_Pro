
package eventsDAO;
import models.Event;
import java.util.*;
/**
 *
 * @author ADMIN
 */
public class EventDAOImpl implements EventDAO{
    private List<Event> events = new ArrayList<>();

    public EventDAOImpl() {
        //events = new ArrayList<>();
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
        System.out.println("Sự kiện đã được thêm thành công.");
    }

    @Override
    public List<Event> getAllEvents() {
        return events;
    }

    @Override
    public Event getEventByName(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public void updateEvent(Event event) {
        Event existingEvent = getEventByName(event.getEventName());
        if (existingEvent != null) {
            existingEvent.setDate(event.getDate());
            existingEvent.setLocation(event.getLocation());
            System.out.println("Sự kiện đã được cập nhật.");
        } else {
            System.out.println("Sự kiện không tồn tại.");
        }
    }

    @Override
    public void deleteEvent(String eventName) {
        Event event = getEventByName(eventName);
        if (event != null) {
            events.remove(event);
            System.out.println("Sự kiện đã được xóa.");
        } else {
            System.out.println("Sự kiện không tồn tại.");
        }
    }

    // Sorting method
    public void sortEventsByDate() {
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                String[] dateSplit1 = o1.getDate().split("/");
                String[] dateSplit2 = o2.getDate().split("/");
                StringBuilder ob1 = new StringBuilder();
                ob1.append(dateSplit1[2]).append(dateSplit1[1]).append(dateSplit1[0]);
                StringBuilder ob2 = new StringBuilder();
                ob2.append(dateSplit2[2]).append(dateSplit2[1]).append(dateSplit2[0]);
                return ob1.toString().compareTo(ob2.toString());
            }
        });
    }
    public boolean checkDate(int d, int m, int y)
    {
        int maxD = 31;
	if ( d <= 0 || d > 31 || m <=0 || m > 12)
	return false;
	if ( m == 4 || m == 6 || m == 9 || m == 11)
	maxD = 30;
	else if ( m ==2 ){
	     if ( y % 4 == 0 && y % 100 != 0 || y % 400 ==0 ){
	     maxD= 29;
	    }else maxD = 28;
	}
	return d <= maxD;
    }
    public boolean isEmpty(){
        if (events.isEmpty() ){
           return true;
        } 
        return false;
    }
    
}
