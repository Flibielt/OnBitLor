package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.ProgrammingStatisticDto;
import hu.deach.etrainer.repository.ProgrammingStatisticRepository;
import hu.deach.etrainer.service.ProgrammingStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class ProgrammingStatisticServiceImpl implements ProgrammingStatisticService {

    @Autowired
    private ProgrammingStatisticRepository programmingStatisticRepository;

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
    public Collection<ProgrammingStatisticDto> findByDate(Date date) {
        return null;
    }

    @Override
    public Collection<ProgrammingStatisticDto> findAll() {
        return null;
    }
}
