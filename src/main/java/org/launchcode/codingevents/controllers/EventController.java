package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {


    @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
        model.addAttribute("title","All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    // lives at /event/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title","Create Event");

        // The reason why we pass an empty event obj below is because
        // by passing this empty Event obj, the template will render
        // and know necessary fields related to the obj to wire up
        // automatically when particular fields are called
        model.addAttribute(new Event());
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
            return "events/create";
        }
//        EventData.add(new Event(eventName,eventDescription));
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",EventData.getAll());
        return "events/delete";
    }

    // the required next to the @REquestParam is required here. If we don't, once the
    // argument is empty it will error out
    @PostMapping("delete")
    public String processDeleteEvent(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for(int i:eventIds){
                EventData.remove(i);
            }
        }
        return "redirect:";
    }


}
