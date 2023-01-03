package com.untouchable.everytime.Service;

import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO login(String ID, String PWD) {
        UserDTO result = modelMapper.map(userRepository.findByIDAndPWD(ID, PWD).orElse(null), UserDTO.class);
        return result;

    }

    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        System.out.println(userEntity.toString());
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

}