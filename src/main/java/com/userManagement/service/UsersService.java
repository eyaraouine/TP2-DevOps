package com.userManagement.service;
import com.userManagement.entity.UsersEntity;
import com.userManagement.dto.UsersDto;
import com.userManagement.repository.UsersRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @SneakyThrows
    public UsersEntity fetchUserByUsername(String username){
        Optional<UsersEntity> usersEntityOptional = usersRepository.findByUsername(username);
        if (usersEntityOptional.isPresent()){
            return usersEntityOptional.get();
        }
        throw new AccountNotFoundException("User with username "+username+" not found");
    }

    public UsersEntity addNewUser(UsersDto usersDto){

        log.info("");
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserRole(usersDto.getUserRole());
        usersEntity.setFirstName(usersDto.getFirstName());
        usersEntity.setLastName(usersDto.getLastName());
        usersEntity.setUsername(usersDto.getUsername());

        return usersRepository.save(usersEntity);
    }

    public List<UsersEntity> fetchAllUsers(){
        return usersRepository.findAll();
    }
}
