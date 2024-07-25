package org.georgievbozhidar.softunifinal2rest.service.impl;

import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.entity.model.User;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.ChainRepository;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.georgievbozhidar.softunifinal2rest.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChainServiceImpl implements ChainService {
    private final ChainRepository chainRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ChainServiceImpl(ChainRepository chainRepository, ModelMapper modelMapper, UserService userService) {
        this.chainRepository = chainRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public Set<ChainDTO> getAllChainsByOwner(User user) {
        Set<Chain> ownedChains = userService.getById(user.getId()).getOwnedChains();

        Set<ChainDTO> chainDTOs = new HashSet<>();
        for (Chain chain : ownedChains) {
            chainDTOs.add(modelMapper.map(chain, ChainDTO.class));
        }

        return chainDTOs;
    }

    @Override
    public ChainDTO getById(Long id){
        Optional<Chain> optChain = chainRepository.findById(id);
        if (optChain.isEmpty()) {
            throw new ChainNotFoundException();
        }

        return modelMapper.map(optChain.get(), ChainDTO.class);
    }

    @Override
    public ChainDTO createChain(CreateChainDTO createChainDTO) {
        Chain chain = modelMapper.map(createChainDTO, Chain.class);

        Optional<User> optUser = userService.findByUsername(createChainDTO.getOwner().getUsername());
        if (optUser.isEmpty()){
            throw new UserNotFoundException();
        }
        chain.setOwner(optUser.get());

        if (chainRepository.findByName(chain.getName()).isPresent()){
            throw new IllegalStateException("Chain by that name already exists");
        }

        chainRepository.save(chain);

        return modelMapper.map(chainRepository.findByName(chain.getName()), ChainDTO.class);
    }

    @Override
    public void deleteChain(Long id) {
        Optional<Chain> optChain = chainRepository.findById(id);
        if (optChain.isEmpty()){
            throw new ChainNotFoundException();
        }

        chainRepository.delete(optChain.get());
    }

    @Override
    public Set<ChainDTO> getAllChains() {
        Set<Chain> chains = new HashSet<>(chainRepository.findAll());
        Set<ChainDTO> chainDTOs = new HashSet<>();
        for (Chain chain : chains) {
            chainDTOs.add(modelMapper.map(chain, ChainDTO.class));
        }
        return chainDTOs;
    }

    @Override
    public ChainDTO getByName(String name) {
        Optional<Chain> optChain = chainRepository.findByName(name);
        if (optChain.isEmpty()){
            throw new ChainNotFoundException();
        }
        return modelMapper.map(optChain.get(), ChainDTO.class);
   }
}
