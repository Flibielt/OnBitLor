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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(PlayerDto playerDto) {
        long count = playerRepository.count();
        Player player = playerRepository.save(Objects.requireNonNull(convertToEntity(playerDto)));
        return player.getId() != null && count < playerRepository.count();
    }

    @Override
    public Boolean delete(Long id) {
        long count = playerRepository.count();
        playerRepository.deleteById(id);
        return count > playerRepository.count();
    }

    @Override
    public Boolean update(PlayerDto playerDto) {
        playerRepository.save(Objects.requireNonNull(convertToEntity(playerDto)));
        Optional<Player> updated = playerRepository.findById(playerDto.getId());
        return updated.filter(player -> convertToDto(player).equals(playerDto)).isPresent();
    }

    @Override
    public PlayerDto findById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(this::convertToDto).orElse(null);
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

    @Override
    public Boolean existsByUsername(String username) {
        return playerRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return playerRepository.existsByEmail(email);
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
