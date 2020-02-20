package hu.deach.etrainer.service;

import com.google.common.collect.Lists;
import hu.deach.etrainer.entity.Player;
import hu.deach.etrainer.model.PlayerResponse;
import hu.deach.etrainer.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private PlayerRepository playerRepository;

    public String hello() {
        return "Hello, from DEAC Hackers ETrainer";
    }

    public List<Player> findAllPlayer() {
        return Lists.newArrayList(playerRepository.findAll());
    }

}
