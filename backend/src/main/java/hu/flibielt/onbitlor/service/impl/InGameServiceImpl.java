package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.InGameNameDto;
import hu.flibielt.onbitlor.entity.IgnId;
import hu.flibielt.onbitlor.entity.InGameName;
import hu.flibielt.onbitlor.model.JoinedGame;
import hu.flibielt.onbitlor.repository.IgnRepository;
import hu.flibielt.onbitlor.service.InGameNameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InGameServiceImpl implements InGameNameService {

    @Autowired
    private IgnRepository ignRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(InGameNameDto inGameNameDto) {
        long count = ignRepository.count();
        InGameName inGameName = ignRepository.save(Objects.requireNonNull(convertToEntity(inGameNameDto)));
        return inGameName.getPlayer() != null && count < ignRepository.count();
    }

    @Override
    public Boolean delete(Long playerId, Long gameId) {
        long count = ignRepository.count();
        IgnId ignId = new IgnId();
        ignId.setPlayer(playerId);
        ignId.setGame(gameId);
        InGameName inGameName = ignRepository.getOne(ignId);
        ignRepository.delete(inGameName);
        return count > ignRepository.count();
    }

    @Override
    public Boolean update(InGameNameDto inGameNameDto) {
        ignRepository.save(Objects.requireNonNull(convertToEntity(inGameNameDto)));
        IgnId ignId = new IgnId();
        ignId.setPlayer(inGameNameDto.getPlayerId());
        ignId.setGame(inGameNameDto.getPlayerId());
        InGameName inGameName = ignRepository.getOne(ignId);
        return inGameName.getIgn().equals(inGameNameDto.getIgn());
    }

    @Override
    public InGameNameDto findById(Long playerId, Long gameId) {
        IgnId ignId = new IgnId();
        ignId.setPlayer(playerId);
        ignId.setGame(gameId);
        Optional<InGameName> inGameName = ignRepository.findById(ignId);
        return inGameName.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<InGameNameDto> findAll() {
        return Lists.newArrayList(ignRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public ArrayList<JoinedGame> findByPlayer(Long playerId) {
        return Lists.newArrayList(ignRepository.findAllByPlayerId(playerId)).stream()
                .map(this::convertToJoinedGame)
                .collect(Collectors.toCollection(Lists::newArrayList));
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

    private JoinedGame convertToJoinedGame(InGameName inGameName) {
        return new JoinedGame(inGameName.getGame().getId(), inGameName.getGame().getName(), inGameName.getIgn());
    }
}
