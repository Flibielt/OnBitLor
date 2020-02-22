package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.InGameNameDto;

import java.util.Collection;

public interface InGameNameService {

    InGameNameDto save(InGameNameDto inGameNameDto);

    Boolean delete(Long playerId, Long gameId);

    InGameNameDto update(InGameNameDto inGameNameDto);

    InGameNameDto findById(Long playerId, Long gameId);

    Collection<InGameNameDto> findAll();

}
