package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.ProgrammingStatisticDto;

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
