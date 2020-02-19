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

    public List<PlayerResponse> findAllPlayer() {
        List<Player> players = Lists.newArrayList(playerRepository.findAll());
        List<PlayerResponse> response = new ArrayList<>();
        for (Player player : players) {
            PlayerResponse playerResponse = new PlayerResponse();
            playerResponse.setFirstName(player.getFirstName());
            playerResponse.setLastName(player.getLastName());
            playerResponse.setId(player.getId());
            response.add(playerResponse);
        }

        return response;
    }

}
