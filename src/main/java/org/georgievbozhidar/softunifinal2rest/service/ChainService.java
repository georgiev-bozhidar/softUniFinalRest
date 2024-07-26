package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.entity.model.User;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface ChainService {
    public ChainDTO getByName(String name) throws ChainNotFoundException;
    public ChainDTO getById(Long id) throws ChainNotFoundException;
    public Set<ChainDTO> getAllChains() throws IllegalStateException;
    public Set<ChainDTO> getAllChainsByOwner(UserDTO userDTO) throws ChainNotFoundException;

    public ChainDTO createChain(CreateChainDTO createChainDTO);
    public void deleteChain(Long id);

    ChainDTO addLocationToChain(Location location, Long chainId);
}
