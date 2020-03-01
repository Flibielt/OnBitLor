package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.GameDto;
import hu.deach.etrainer.model.GameRequest;

import java.util.ArrayList;

public interface GameService {

    Boolean save(GameDto gameDto);

    GameDto save(GameRequest gameRequest);

    Boolean delete(GameDto gameDto);

    Boolean update(GameDto playerDto);

    GameDto findById(Long id);

    GameDto findByName(String name);

    ArrayList<GameDto> findAll();
}
