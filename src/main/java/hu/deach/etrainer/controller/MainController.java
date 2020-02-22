package hu.deach.etrainer.controller;

import hu.deach.etrainer.dto.PlayerDto;
import hu.deach.etrainer.entity.Player;
import hu.deach.etrainer.model.ExampleResponse;
import hu.deach.etrainer.model.PlayerResponse;
import hu.deach.etrainer.service.MainService;
import hu.deach.etrainer.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping("/")
    public ExampleResponse index() {
        ExampleResponse response = new ExampleResponse();
        response.setResponse(mainService.hello());
        return response;
    }

    @RequestMapping("/players")
    public List<PlayerDto> getAllPlayer() {
        List<Player> players = mainService.findAllPlayer();
        return players.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping("/all_player")
    public Collection<PlayerDto> getEveryPlayer() {
        return playerService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PlayerDto convertToDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }

    private Player convertToEntity(PlayerDto playerDto) {
        Player player = modelMapper.map(playerDto, Player.class);

        if (playerDto.getId() != null) {
            return null;
        }

        return player;
    }
}
