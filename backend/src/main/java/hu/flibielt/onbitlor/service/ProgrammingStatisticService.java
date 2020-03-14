package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.ProgrammingStatisticDto;
import hu.flibielt.onbitlor.model.ProgrammingResultRequest;
import hu.flibielt.onbitlor.model.ProgrammingStatisticResponse;

import java.util.ArrayList;
import java.util.Date;

public interface ProgrammingStatisticService {

    ProgrammingStatisticDto save(ProgrammingStatisticDto programmingStatisticDto);

    Boolean delete(Long id);

    Boolean update(ProgrammingStatisticDto programmingStatisticDto);

    ProgrammingStatisticDto findById(Long id);

    ArrayList<ProgrammingStatisticDto> findByDate(Date date);

    ArrayList<ProgrammingStatisticDto> findAll();

    ArrayList<ProgrammingStatisticResponse> findAllInProgramming(String name);

}
