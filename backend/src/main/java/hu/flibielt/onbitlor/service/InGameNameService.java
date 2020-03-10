package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.InGameNameDto;
import hu.flibielt.onbitlor.model.JoinedGame;

import java.util.ArrayList;

public interface InGameNameService {

    Boolean save(InGameNameDto inGameNameDto);

    Boolean delete(Long playerId, Long gameId);

    Boolean update(InGameNameDto inGameNameDto);

    InGameNameDto findById(Long playerId, Long gameId);

    ArrayList<InGameNameDto> findAll();

    ArrayList<JoinedGame> findByPlayer(Long playerId);

}
