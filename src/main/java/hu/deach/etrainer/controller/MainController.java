package hu.deach.etrainer.controller;

import hu.deach.etrainer.model.ExampleResponse;
import hu.deach.etrainer.model.PlayerResponse;
import hu.deach.etrainer.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/")
    public ExampleResponse index() {
        ExampleResponse response = new ExampleResponse();
        response.setResponse(mainService.hello());
        return response;
    }

    @RequestMapping("/players")
    public List<PlayerResponse> getAllPlayer() {
        return mainService.findAllPlayer();
    }
}
