package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.ProgrammingStatisticDto;

import java.util.ArrayList;
import java.util.Date;

public interface ProgrammingStatisticService {

    Boolean save(ProgrammingStatisticDto programmingStatisticDto);

    Boolean delete(Long id);

    Boolean update(ProgrammingStatisticDto programmingStatisticDto);

    ProgrammingStatisticDto findById(Long id);

    ArrayList<ProgrammingStatisticDto> findByDate(Date date);

    ArrayList<ProgrammingStatisticDto> findAll();

}