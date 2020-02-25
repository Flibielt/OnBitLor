package hu.deach.etrainer.service.impl;

import com.google.common.collect.Lists;
import hu.deach.etrainer.dto.ProgrammingStatisticDto;
import hu.deach.etrainer.entity.ProgrammingStatistic;
import hu.deach.etrainer.entity.ProgrammingStatisticId;
import hu.deach.etrainer.repository.ProgrammingStatisticRepository;
import hu.deach.etrainer.service.ProgrammingStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgrammingStatisticServiceImpl implements ProgrammingStatisticService {

    @Autowired
    private ProgrammingStatisticRepository programmingStatisticRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(ProgrammingStatisticDto programmingStatisticDto) {
        long count = programmingStatisticRepository.count();
        ProgrammingStatistic updated = programmingStatisticRepository.save(Objects.requireNonNull(convertToEntity(programmingStatisticDto)));
        return count < programmingStatisticRepository.count() && updated.getPlayer().getId() != null;
    }

    @Override
    public Boolean delete(Long programmingId, Long playerId, Date date) {
        long count = programmingStatisticRepository.count();
        ProgrammingStatisticId id = new ProgrammingStatisticId();
        id.setProgramming(programmingId);
        id.setPlayer(playerId);
        id.setDate(date);
        programmingStatisticRepository.deleteById(id);
        return count > programmingStatisticRepository.count();
    }

    @Override
    public Boolean update(ProgrammingStatisticDto programmingStatisticDto) {
        ProgrammingStatistic updated = programmingStatisticRepository.save(Objects.requireNonNull(convertToEntity(programmingStatisticDto)));
        return convertToDto(updated).equals(programmingStatisticDto);
    }

    @Override
    public ProgrammingStatisticDto findById(Long programmingId, Long playerId, Date date) {
        ProgrammingStatisticId id = new ProgrammingStatisticId();
        id.setProgramming(programmingId);
        id.setPlayer(playerId);
        id.setDate(date);
        Optional<ProgrammingStatistic> programmingStatistic = programmingStatisticRepository.findById(id);
        return programmingStatistic.map(this::convertToDto).orElse(null);
    }

    //todo: create query
    @Override
    public ArrayList<ProgrammingStatisticDto> findByDate(Date date) {
        return null;
    }

    //todo: create query
    @Override
    public ArrayList<ProgrammingStatisticDto> findAll() {
        return Lists.newArrayList(programmingStatisticRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
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
