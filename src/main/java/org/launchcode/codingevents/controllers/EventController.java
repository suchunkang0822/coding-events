package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
        model.addAttribute("events",events);
        return "events/index";
    }

    // lives at /event/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }

    // lives at /event/create
    // It is ok to have two handlers with same path b/c they handle two
    // different requests
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName,@RequestParam String eventDescription){
        events.add(new Event(eventName,eventDescription));
        return "redirect:";
    }


}
