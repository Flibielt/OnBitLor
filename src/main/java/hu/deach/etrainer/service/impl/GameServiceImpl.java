package hu.deach.etrainer.service.impl;

import com.google.common.collect.Lists;
import hu.deach.etrainer.dto.GameDto;
import hu.deach.etrainer.entity.Game;
import hu.deach.etrainer.repository.GameRepository;
import hu.deach.etrainer.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GameDto save(GameDto gameDto) {
        return null;
    }

    @Override
    public Boolean delete(GameDto gameDto) {
        return null;
    }

    @Override
    public GameDto update(GameDto playerDto) {
        return null;
    }

    @Override
    public GameDto findById(GameDto playerDto) {
        return null;
    }

    @Override
    public GameDto findByName(String name) {
        return null;
    }

    @Override
    public ArrayList<GameDto> findAll() {
        return Lists.newArrayList(gameRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    private GameDto convertToDto(Game game) {
        return modelMapper.map(game, GameDto.class);
    }

    private Game convertToEntity(GameDto gameDto) {
        Game game = modelMapper.map(gameDto, Game.class);

        if (gameDto.getId() == null) {
            return null;
        }

        return game;
    }

}
