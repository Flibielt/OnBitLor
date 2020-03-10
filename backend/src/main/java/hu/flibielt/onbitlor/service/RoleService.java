package hu.flibielt.onbitlor.service;

import hu.flibielt.onbitlor.dto.RoleDto;

import java.util.ArrayList;

public interface RoleService {

    Boolean save(RoleDto roleDto);

    Boolean delete(Long id);

    Boolean update(RoleDto roleDto);

    RoleDto findById(Long id);

    ArrayList<RoleDto> findAll();

}
