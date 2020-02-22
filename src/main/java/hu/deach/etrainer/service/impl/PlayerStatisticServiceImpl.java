package hu.deach.etrainer.service.impl;

import hu.deach.etrainer.dto.PlayerStatisticDto;
import hu.deach.etrainer.repository.PlayerStatisticRepository;
import hu.deach.etrainer.service.PlayerStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlayerStatisticServiceImpl implements PlayerStatisticService {

    @Autowired
    private PlayerStatisticRepository playerStatisticRepository;

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
    public Collection<PlayerStatisticDto> findByPlayerId(Long playerId) {
        return null;
    }

    @Override
    public Collection<PlayerStatisticDto> findByGameId(Long gameId) {
        return null;
    }

    @Override
    public Collection<PlayerStatisticDto> findAll() {
        return null;
    }
}
