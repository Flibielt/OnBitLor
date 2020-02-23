package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.PlayerStatisticDto;
import hu.deach.etrainer.entity.PlayerStatistic;
import hu.deach.etrainer.repository.PlayerStatisticRepository;
import hu.deach.etrainer.service.PlayerStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlayerStatisticServiceImpl implements PlayerStatisticService {

    @Autowired
    private PlayerStatisticRepository playerStatisticRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlayerStatisticDto save(PlayerStatisticDto playerStatisticDto) {
        return null;
    }

    @Override
    public Boolean delete(Long playerId, Long gameId) {
        return null;
    }

    @Override
    public PlayerStatisticDto update(PlayerStatisticDto playerStatisticDto) {
        return null;
    }

    @Override
    public PlayerStatisticDto findById(Long playerId, Long gameId) {
        return null;
    }

    @Override
    public ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId) {
        return null;
    }

    @Override
    public ArrayList<PlayerStatisticDto> findByGameId(Long gameId) {
        return null;
    }

    @Override
    public ArrayList<PlayerStatisticDto> findAll() {
        return null;
    }

    private PlayerStatisticDto convertToDto(PlayerStatistic playerStatistic) {
        return modelMapper.map(playerStatistic, PlayerStatisticDto.class);
    }

    private PlayerStatistic convertToEntity(PlayerStatisticDto playerStatisticDto) {
        PlayerStatistic playerStatistic = modelMapper.map(playerStatisticDto, PlayerStatistic.class);

        if (playerStatisticDto.getPlayerId() == null) {
            return null;
        }

        return playerStatistic;
    }
}
