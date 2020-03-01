package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.PlayerStatisticDto;

import java.util.ArrayList;
import java.util.Date;

public interface PlayerStatisticService {

    Boolean save(PlayerStatisticDto playerStatisticDto);

    Boolean delete(Long playerId, Long gameId, Date fromDate);

    Boolean update(PlayerStatisticDto playerStatisticDto);

    PlayerStatisticDto findById(Long playerId, Long gameId, Date fromDate);

    ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId);

    ArrayList<PlayerStatisticDto> findByGameId(Long gameId);

    ArrayList<PlayerStatisticDto> findAll();

}
