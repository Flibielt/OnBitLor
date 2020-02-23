package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.GameDto;

import java.util.ArrayList;

public interface GameService {

    GameDto save(GameDto gameDto);

    Boolean delete(GameDto gameDto);

    GameDto update(GameDto playerDto);

    GameDto findById(GameDto playerDto);

    GameDto findByName(String name);

    ArrayList<GameDto> findAll();
}
