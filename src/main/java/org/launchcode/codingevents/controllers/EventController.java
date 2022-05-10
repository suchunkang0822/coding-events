package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
//    private static List<String> events = new ArrayList<>();
    private static HashMap<String,String> events = new HashMap<>();
    private static HashMap<String,String> url = new HashMap<>();

    @GetMapping
    public String displayAllEvents(Model model){
////        List<String> events = new ArrayList<>();
////        events.add("Code With Pride");
////        events.add("Strange Loop");
////        events.add("Apple WWDC");
////        events.add("SpringOne Platform");
//        model.addAttribute("events",events);
//        return "events/index";
        events.put("Inclusive Data Hackathon","A Data Analytics hackathon hosted by Digital Page in partnership with Capgemini to expand and empower the future leaders of tech.");
        events.put("Hack-AI-Thon","Learn to use artificial intelligence (AI) with image recognition at this Day Of AI inspired hackathon! (Free, online, for students 13+)");
        events.put("Tron Grand Hackathon 2022","The TRON Grand Hackathon 2022 is an exciting opportunity for entrepreneurs, developers, and designers who have always wanted to build a smart contract or blockchain-related application.");
        model.addAttribute("events",events);

        url.put("Hack-AI-Thon","www.allevents.in/online/10000296443298887");
        url.put("Inclusive Data Hackathon","www.allevents.in/online/10000296123201467");
        url.put("Tron Grand Hackathon 2022","www.allevents.in/online/10000310063727957");
        model.addAttribute("url",url);

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
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDetail){
        events.put(eventName,eventDetail);
        return "redirect:";
    }

}
