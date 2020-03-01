package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.ProgrammingDto;
import hu.deach.etrainer.model.ProgrammingRequest;

import java.util.ArrayList;

public interface ProgrammingService {

    Boolean save(ProgrammingDto programmingDto);

    ProgrammingDto save(ProgrammingRequest programmingRequest);

    Boolean delete(ProgrammingDto programmingDto);

    Boolean update(ProgrammingDto programmingDto);

    ProgrammingDto findById(Long id);

    ArrayList<ProgrammingDto> findByGameId(Long gameId);

    ArrayList<ProgrammingDto> findAll();

}
