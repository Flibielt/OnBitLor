package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.GameDto;
import hu.deach.etrainer.dto.PlayerDto;
import hu.deach.etrainer.repository.GameRepository;
import hu.deach.etrainer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

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
    public Collection<GameDto> findAll() {
        return null;
    }
}
