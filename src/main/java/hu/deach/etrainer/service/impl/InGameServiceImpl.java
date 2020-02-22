package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.InGameNameDto;
import hu.deach.etrainer.repository.IgnRepository;
import hu.deach.etrainer.service.InGameNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InGameServiceImpl implements InGameNameService {

    @Autowired
    private IgnRepository ignRepository;

    @Override
    public InGameNameDto save(InGameNameDto inGameNameDto) {
        return null;
    }

    @Override
    public Boolean delete(Long playerId, Long gameId) {
        return null;
    }

    @Override
    public InGameNameDto update(InGameNameDto inGameNameDto) {
        return null;
    }

    @Override
    public InGameNameDto findById(Long playerId, Long gameId) {
        return null;
    }

    @Override
    public Collection<InGameNameDto> findAll() {
        return null;
    }
}
