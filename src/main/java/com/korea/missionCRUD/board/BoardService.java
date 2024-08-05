package com.korea.missionCRUD.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board create(String title){
        Board board = new Board ();
        board.setName (title);
        board.setCreateDate (LocalDateTime.now ());
       return boardRepository.save (board);
    }

    public void delete(Long id){
        Board board = boardRepository.findById (id).get ();
        boardRepository.delete (board);
    }
}
