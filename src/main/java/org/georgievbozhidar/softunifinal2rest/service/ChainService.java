package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.entity.model.User;

import java.util.Optional;
import java.util.Set;

public interface ChainService {
    public ChainDTO getByName(String name);
    public ChainDTO getById(Long id);
    public Set<ChainDTO> getAllChains();
    public Set<ChainDTO> getAllChainsByOwner(User user);

    public ChainDTO createChain(CreateChainDTO createChainDTO);
    public void deleteChain(Long id);
}
