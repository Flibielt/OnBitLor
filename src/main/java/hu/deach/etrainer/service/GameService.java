package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.GameDto;

import java.util.Collection;

public interface GameService {

    GameDto save(GameDto gameDto);

    Boolean delete(GameDto gameDto);

    GameDto update(GameDto playerDto);

    GameDto findById(GameDto playerDto);

    GameDto findByName(String name);

    Collection<GameDto> findAll();
}
