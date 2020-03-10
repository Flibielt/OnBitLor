package hu.flibielt.onbitlor.service.impl;

import com.google.common.collect.Lists;
import hu.flibielt.onbitlor.dto.RoleDto;
import hu.flibielt.onbitlor.entity.Role;
import hu.flibielt.onbitlor.repository.RoleRepository;
import hu.flibielt.onbitlor.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(RoleDto roleDto) {
        long count = roleRepository.count();
        Role role = roleRepository.save(Objects.requireNonNull(convertToEntity(roleDto)));
        return count < roleRepository.count() && role.getId() != null;
    }

    @Override
    public Boolean delete(Long id) {
        long count = roleRepository.count();
        roleRepository.deleteById(id);
        return count > roleRepository.count();
    }

    @Override
    public Boolean update(RoleDto roleDto) {
        Role updated = roleRepository.save(Objects.requireNonNull(convertToEntity(roleDto)));
        return convertToDto(updated).equals(roleDto);
    }

    @Override
    public RoleDto findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(this::convertToDto).orElse(null);
    }

    @Override
    public ArrayList<RoleDto> findAll() {
        return Lists.newArrayList(roleRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toCollection(Lists::newArrayList));
    }

    private RoleDto convertToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    private Role convertToEntity(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);

        if (roleDto.getId() == null) {
            return null;
        }

        return role;
    }

}
