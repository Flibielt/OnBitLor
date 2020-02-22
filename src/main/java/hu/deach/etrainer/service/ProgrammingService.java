package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.ProgrammingDto;

import java.util.Collection;

public interface ProgrammingService {

    ProgrammingDto save(ProgrammingDto programmingDto);

    Boolean delete(ProgrammingDto programmingDto);

    ProgrammingDto update(ProgrammingDto programmingDto);

    ProgrammingDto findById(Long id);

    Collection<ProgrammingDto> findByGameId(Long gameId);

    Collection<ProgrammingDto> findAll();

}
