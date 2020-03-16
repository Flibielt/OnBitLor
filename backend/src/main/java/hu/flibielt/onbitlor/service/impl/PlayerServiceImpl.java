package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.PlayerDto;
import hu.flibielt.onbitlor.entity.Player;
import hu.flibielt.onbitlor.model.PlayerInfoResponse;
import hu.flibielt.onbitlor.repository.PlayerRepository;
import hu.flibielt.onbitlor.service.PlayerService;
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
        Optional<Player> player = playerRepository.findByUsername(username);
        return player.map(this::convertToDto).orElse(null);
    }

    @Override
    public PlayerDto findByEmail(String email) {
        Optional<Player> player = playerRepository.findByEmail(email);
        return player.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<PlayerDto> findAll() {
        return Lists.newArrayList(playerRepository.findAll()).stream()
                .map(this::convertToDto).collect(Collectors.toCollection(Lists::newArrayList));
    }

    @Override
    public ArrayList<PlayerInfoResponse> getAll() {
        return Lists.newArrayList(playerRepository.findAll()).stream()
                .map(this::convertToPlayerInfo).collect(Collectors.toCollection(Lists::newArrayList));
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

    private PlayerInfoResponse convertToPlayerInfo(Player player) {
        PlayerInfoResponse response = new PlayerInfoResponse();
        response.setId(player.getId());
        response.setFirstName(player.getFirstName());
        response.setLastName(player.getLastName());
        response.setUsername(player.getUsername());
        response.setBit(player.getBit());
        return response;
    }
}
