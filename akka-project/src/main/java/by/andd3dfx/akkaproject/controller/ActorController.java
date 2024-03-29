package by.andd3dfx.akkaproject.controller;

import by.andd3dfx.akkaproject.schedule.ParamsHolder;
import by.andd3dfx.akkaproject.schedule.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    Scheduler scheduler;

    @Autowired
    ParamsHolder paramsHolder;

    @RequestMapping(value = "/actorsCount", method = RequestMethod.GET)
    public int getActorsCount() {
        return paramsHolder.getActorsCount();
    }

    @RequestMapping(value = "/actorsCount/{change}", method = RequestMethod.POST)
    public void changeActorsCount(@PathVariable("change") int change) {
        scheduler.changeActorsCount(change);
    }
}
