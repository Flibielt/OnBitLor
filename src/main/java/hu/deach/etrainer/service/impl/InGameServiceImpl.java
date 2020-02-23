package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.InGameNameDto;
import hu.deach.etrainer.entity.InGameName;
import hu.deach.etrainer.repository.IgnRepository;
import hu.deach.etrainer.service.InGameNameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InGameServiceImpl implements InGameNameService {

    @Autowired
    private IgnRepository ignRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public ArrayList<InGameNameDto> findAll() {
        return null;
    }

    private InGameNameDto convertToDto(InGameName inGameName) {
        return modelMapper.map(inGameName, InGameNameDto.class);
    }

    private InGameName convertToEntity(InGameNameDto inGameNameDto) {
        InGameName inGameName = modelMapper.map(inGameNameDto, InGameName.class);

        if (inGameNameDto.getPlayerId() == null) {
            return null;
        }

        return inGameName;
    }
}
