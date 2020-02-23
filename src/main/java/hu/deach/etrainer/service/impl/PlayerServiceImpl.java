package hu.deach.etrainer.service.impl;

import com.google.common.collect.Lists;
import hu.deach.etrainer.dto.PlayerDto;
import hu.deach.etrainer.entity.Player;
import hu.deach.etrainer.repository.PlayerRepository;
import hu.deach.etrainer.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlayerDto save(PlayerDto playerDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public PlayerDto update(PlayerDto playerDto) {
        return null;
    }

    @Override
    public PlayerDto findById(Long id) {
        return null;
    }

    @Override
    public PlayerDto findByUsername(String username) {
        return null;
    }

    @Override
    public PlayerDto findByEmail(String email) {
        return null;
    }

    @Override
    public ArrayList<PlayerDto> findAll() {
        return Lists.newArrayList(playerRepository.findAll()).stream()
                .map(this::convertToDto).collect(Collectors.toCollection(Lists::newArrayList));
    }

    private PlayerDto convertToDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }

    private Player convertToEntity(PlayerDto playerDto) {
        Player player = modelMapper.map(playerDto, Player.class);

        if (playerDto.getId() == null) {
            return null;
        }

        return player;

    }
}
