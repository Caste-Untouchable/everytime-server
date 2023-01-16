package com.untouchable.everytime.Board.Service;

import com.untouchable.everytime.Board.DTO.BoardTypeDTO;
import com.untouchable.everytime.Board.Entity.BoardType;
import com.untouchable.everytime.Board.Repository.BoardTypeRepository;
import com.untouchable.everytime.School.Repository.SchoolRepository;
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
        BoardType boardType = modelMapper.map(boardTypeDTO, BoardType.class);
        boardType.setSchool(schoolRepository.findById(boardTypeDTO.getSchoolName()).get());
        BoardType result = boardTypeRepository.save(boardType);
        return modelMapper.map(result, BoardTypeDTO.class);
    }

    public BoardTypeDTO updateBoardType(BoardTypeDTO boardTypeDTO) {
        BoardType boardType = modelMapper.map(boardTypeDTO, BoardType.class);
        BoardType result = boardTypeRepository.save(boardType);
        return modelMapper.map(result, BoardTypeDTO.class);
    }

    public void deleteBoardType(Long id) {
        boardTypeRepository.deleteById(id);
    }

    public ArrayList<BoardTypeDTO> getBoardTypeBySchoolName(String schoolName) {
        List<BoardType> boardType = boardTypeRepository.findBySchool_SchoolName(schoolName);

        ArrayList<BoardTypeDTO> boardTypeDTOS = new ArrayList<>();
        for (BoardType boardType1 : boardType) {
            boardTypeDTOS.add(modelMapper.map(boardType1, BoardTypeDTO.class));
        }

        return boardTypeDTOS;
    }


}
