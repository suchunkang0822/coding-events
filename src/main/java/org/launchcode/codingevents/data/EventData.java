// ************ We do not need this datalayer anymore since we are
// directly working with the DB

//package org.launchcode.codingevents.data;
//
//import org.launchcode.codingevents.models.Event;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class EventData {
//
//    private static final Map<Integer, Event> events = new HashMap<>();
//
//    // need a place to store events
//
//
//    // get all events
//    public static Collection<Event> getAll(){
//        return events.values();
//    }
//
//    // get a single event
//    public static Event getById(int id){
//        return events.get(id);
//    }
//
//    // add an event
//    public static void add(Event event){
//        events.put(event.getId(), event);
//    }
//
//    //remove event
//    public static void remove(int id){
//        events.remove(id);
//    }
//}
