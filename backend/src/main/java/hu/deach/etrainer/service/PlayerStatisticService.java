package hu.deach.etrainer.service;

import hu.deach.etrainer.dto.PlayerStatisticDto;

import java.util.ArrayList;
import java.util.Date;

public interface PlayerStatisticService {

    Boolean save(PlayerStatisticDto playerStatisticDto);

    Boolean delete(Long id);

    Boolean update(PlayerStatisticDto playerStatisticDto);

    PlayerStatisticDto findById(Long id);

    ArrayList<PlayerStatisticDto> findByPlayerId(Long playerId);

    ArrayList<PlayerStatisticDto> findByGameId(Long gameId);

    ArrayList<PlayerStatisticDto> findAll();

}
