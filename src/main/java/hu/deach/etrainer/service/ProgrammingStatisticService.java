package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.ProgrammingStatisticDto;

import java.util.ArrayList;
import java.util.Date;

public interface ProgrammingStatisticService {

    ProgrammingStatisticDto save(ProgrammingStatisticDto programmingStatisticDto);

    Boolean delete(Long programmingId, Long playerId);

    ProgrammingStatisticDto update(ProgrammingStatisticDto programmingStatisticDto);

    ProgrammingStatisticDto findById(Long programmingId, Long playerId);

    ArrayList<ProgrammingStatisticDto> findByDate(Date date);

    ArrayList<ProgrammingStatisticDto> findAll();

}
