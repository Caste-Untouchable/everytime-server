package com.untouchable.everytime.Service;

import com.untouchable.everytime.DTO.BoardTypeDTO;
import com.untouchable.everytime.Entity.BoardEntity;
import com.untouchable.everytime.Entity.BoardTypeEntity;
import com.untouchable.everytime.Repository.BoardTypeRepository;
import com.untouchable.everytime.Repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardTypeService {

    BoardTypeRepository boardTypeRepository;
    ModelMapper modelMapper;
    SchoolRepository schoolRepository;

    @Autowired
    public BoardTypeService(SchoolRepository schoolRepository,BoardTypeRepository boardTypeRepository, ModelMapper modelMapper) {
        this.boardTypeRepository = boardTypeRepository;
        this.modelMapper = modelMapper;
        this.schoolRepository = schoolRepository;
    }

    public BoardTypeDTO createBoardType(BoardTypeDTO boardTypeDTO) {
        BoardTypeEntity boardTypeEntity = modelMapper.map(boardTypeDTO, BoardTypeEntity.class);
        boardTypeEntity.setSchool(schoolRepository.findById(boardTypeDTO.getSchoolName()).get());
        BoardTypeEntity result = boardTypeRepository.save(boardTypeEntity);
        return modelMapper.map(result, BoardTypeDTO.class);
    }

    public BoardTypeDTO updateBoardType(BoardTypeDTO boardTypeDTO) {
        BoardTypeEntity boardTypeEntity = modelMapper.map(boardTypeDTO, BoardTypeEntity.class);
        BoardTypeEntity result = boardTypeRepository.save(boardTypeEntity);
        return modelMapper.map(result, BoardTypeDTO.class);
    }

    public void deleteBoardType(Long id) {
        boardTypeRepository.deleteById(id);
    }

    public ArrayList<BoardTypeDTO> getBoardTypeBySchoolName(String schoolName) {
        List<BoardTypeEntity> boardTypeEntity = boardTypeRepository.findBySchool_SchoolName(schoolName);

        ArrayList<BoardTypeDTO> boardTypeDTOS = new ArrayList<>();
        for (BoardTypeEntity boardTypeEntity1 : boardTypeEntity) {
            boardTypeDTOS.add(modelMapper.map(boardTypeEntity1, BoardTypeDTO.class));
        }

        return boardTypeDTOS;
    }


}
