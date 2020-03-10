package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.ProgrammingDto;
import hu.flibielt.onbitlor.model.ProgrammingRequest;

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
