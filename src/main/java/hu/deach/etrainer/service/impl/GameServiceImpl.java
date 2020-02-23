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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(GameDto gameDto) {
        long count = gameRepository.count();
        System.out.println(convertToEntity(gameDto));
        Game game = gameRepository.save(convertToEntity(gameDto));
        return game.getId() != null && count < gameRepository.count();
    }

    @Override
    public Boolean delete(GameDto gameDto) {
        long count = gameRepository.count();
        gameRepository.delete(Objects.requireNonNull(convertToEntity(gameDto)));
        return count > gameRepository.count();
    }

    @Override
    public Boolean update(GameDto playerDto) {
        gameRepository.save(Objects.requireNonNull(convertToEntity(playerDto)));
        return true;
    }

    @Override
    public GameDto findById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(this::convertToDto).orElse(null);
    }

    @Override
    public GameDto findByName(String name) {
        Game game = gameRepository.findByName(name);
        if (game != null) {
            return convertToDto(game);
        }
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
        return modelMapper.map(gameDto, Game.class);
    }

}
