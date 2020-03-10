package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.GameDto;
import hu.flibielt.onbitlor.model.GameRequest;

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
