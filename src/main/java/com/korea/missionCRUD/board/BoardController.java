package com.korea.missionCRUD.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> boardCreate(@RequestBody Map<String, String> request){

        String name = request.get("name");
        Board board = boardService.create(name);
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

}
