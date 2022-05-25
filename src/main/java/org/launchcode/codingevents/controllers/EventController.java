package org.launchcode.codingevents.controllers;

// EventData is not needed since we are using DB
//import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
//import org.launchcode.codingevents.models.EventType;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    // Telling Spring that it should auto populate
    // eventRepository, which does not have constructor,getters, and setters
    // It's using the dependency injection(inversion of control)
    // Asks SpringBoot to
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

//    @RequestParam
//    // If we want to use @GetMapping to receive categoryId, change the categoryId annotation to
//    // @PathVariable and data type from Integer to String, since pathVariable
//    // will come in String then use Integer.parseInt(categoryId) to convert back to int.
//    // The two values inside annotation are two to make the controller to respond
//    // with or without the pathVariable.
//    @GetMapping(value = {"","{categoryId}"})
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId,
                                   Model model){
        if(categoryId == null){
            //List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
            model.addAttribute("title","All Events");

//        // We no longer need EvenData, data layer, since we are
//        // using DB to keep track of the Even obj
//        model.addAttribute("events", EventData.getAll());
            // returns collection of events from DB
            model.addAttribute("events", eventRepository.findAll());
        }else{
            // This is an optional obj used when you want
            // to access the CRUD repository but you never
            // know if it'll be null or contain a value.
            // We never know if there will be event category or not.
            // However, this isn't the value itself. Hence, use
            // .get() method on it.
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: "+categoryId);
            }else{
                EventCategory category = result.get();
                model.addAttribute("title","Event Category: "+category.getName());
                model.addAttribute("events",category.getEvents());
            }
        }

        return "events/index";
    }

    // lives at /event/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title","Create Event");

        // The reason why we pass an empty event obj below is because
        // by passing this empty Event obj, the template will render
        // and know necessary fields related to the obj to bind
        // automatically when particular fields are called.
        // Lastly an argument for label is missing. In such case
        // Spring will implicitly create all lower case "event"
        // label for you

        // *** because we initialize and pass new event here
        // the id number will always be in multiple of two
        // so the .getId method from Event will return id/2
        model.addAttribute(new Event());


//        model.addAttribute("types", EventType.values());

        // Now we'll be using EventCategoryRepository so
        // no need to pass the EventType.values() above, infact EventData is no longer needed
        model.addAttribute("categories", eventCategoryRepository.findAll());


        return "events/create";
    }

    // Adding @Valid annotation will check any validation annotations
    // on the fields for this class and make sure they are satisfied

    // When EventData class needs to add more fields. the
    // controller below will need to add that many more arguments and
    // it quickly will get unwieldy however model binding
    // eliminates the need to do that and stream line everything
    // @ModelAttribute will let Spring look into request data and look
    // for fields of Event class

    // lives at /event/create
    // It is ok to have two handlers with same path b/c they handle two
    // different requests
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent,
                              Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Event");

            // Now, because we had validation annotations with custom msgs
            // we don't need to pass an errorMsg like below. Instead, we can
            // render directly from the template using th:errors = "${fieldName}"
            // As long as you have proper validation annotations inside the class
            // plus passing the Errors obj in the method as above.

//            model.addAttribute("errorMsg","Bad Data!");

//            // You have to pass the select options again
//            // if not, the redirect will not render options at all
//            // when validation error occurs.
//            model.addAttribute("types", EventType.values());

            // no need for above command
            model.addAttribute("categories", eventCategoryRepository.findAll());
            return "events/create";
        }
//        EventData.add(new Event(eventName,eventDescription));

        // We are no longer using EventData, data layer
//        EventData.add(newEvent);

        //newEvent now saved at DB instead of memory collection
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title","Delete Events");

        // No loner using the data layer, EventData
//        model.addAttribute("events",EventData.getAll());

        model.addAttribute("events",eventRepository.findAll());
        return "events/delete";
    }

    // the required next to the @REquestParam is required here. If we don't, once the
    // argument is empty it will error out
    @PostMapping("delete")
    public String processDeleteEvent(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for(int i:eventIds){

//                EventData.remove(i);
                // this is the equivalent of remove from DB
                eventRepository.deleteById(i);
            }
        }
        return "redirect:";
    }



}
