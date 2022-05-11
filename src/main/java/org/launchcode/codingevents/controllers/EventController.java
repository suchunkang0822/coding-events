package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        return "events/create";
    }

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
    public String createEvent(@ModelAttribute Event newEvent){
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

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model,@PathVariable int eventId){
        model.addAttribute("title","Edit Event " + EventData.getById(eventId).getName() + "(id = "+eventId+")" );
        model.addAttribute("event",EventData.getById(eventId));
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        // controller code will go here
        EventData.getById(eventId).setName(name);
        EventData.getById(eventId).setDescription(description);
        return "redirect:";
    }


}
