package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.ProgrammingDto;
import hu.deach.etrainer.repository.ProgrammingRepository;
import hu.deach.etrainer.service.ProgrammingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProgrammingServiceImpl implements ProgrammingService {

    @Autowired
    private ProgrammingRepository programmingRepository;

    @Override
    public ProgrammingDto save(ProgrammingDto programmingDto) {
        return null;
    }

    @Override
    public Boolean delete(ProgrammingDto programmingDto) {
        return null;
    }

    @Override
    public ProgrammingDto update(ProgrammingDto programmingDto) {
        return null;
    }

    @Override
    public ProgrammingDto findById(Long id) {
        return null;
    }

    @Override
    public Collection<ProgrammingDto> findByGameId(Long gameId) {
        return null;
    }

    @Override
    public Collection<ProgrammingDto> findAll() {
        return null;
    }
}
