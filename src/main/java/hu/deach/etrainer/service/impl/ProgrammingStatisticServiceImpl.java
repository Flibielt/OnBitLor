package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.ProgrammingStatisticDto;
import hu.deach.etrainer.entity.ProgrammingStatistic;
import hu.deach.etrainer.repository.ProgrammingStatisticRepository;
import hu.deach.etrainer.service.ProgrammingStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ProgrammingStatisticServiceImpl implements ProgrammingStatisticService {

    @Autowired
    private ProgrammingStatisticRepository programmingStatisticRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProgrammingStatisticDto save(ProgrammingStatisticDto programmingStatisticDto) {
        return null;
    }

    @Override
    public Boolean delete(Long programmingId, Long playerId) {
        return null;
    }

    @Override
    public ProgrammingStatisticDto update(ProgrammingStatisticDto programmingStatisticDto) {
        return null;
    }

    @Override
    public ProgrammingStatisticDto findById(Long programmingId, Long playerId) {
        return null;
    }

    @Override
    public ArrayList<ProgrammingStatisticDto> findByDate(Date date) {
        return null;
    }

    @Override
    public ArrayList<ProgrammingStatisticDto> findAll() {
        return null;
    }

    private ProgrammingStatisticDto convertToDto(ProgrammingStatistic programmingStatistic) {
        return modelMapper.map(programmingStatistic, ProgrammingStatisticDto.class);
    }

    private ProgrammingStatistic convertToEntity(ProgrammingStatisticDto programmingStatisticDto) {
        ProgrammingStatistic programmingStatistic = modelMapper.map(programmingStatisticDto, ProgrammingStatistic.class);

        if (programmingStatisticDto.getPlayerId() == null) {
            return null;
        }

        return programmingStatistic;
    }

}
