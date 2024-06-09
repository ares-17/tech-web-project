package com.unina.techweb.controller;

import com.unina.techweb.controller.api.ScoreApi;
import com.unina.techweb.dto.QuizResponseDto;
import com.unina.techweb.dto.ScoreDto;
import com.unina.techweb.exceptions.NotFoundException;
import com.unina.techweb.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ScoreController implements ScoreApi {

    private final ScoreService scoreService;

    @Autowired
    ScoreController(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    @Override
    public ResponseEntity<Void> completeQuiz(QuizResponseDto dto) {
        try{
            this.scoreService.completeQuiz(dto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch(NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<ScoreDto>> getScoresByQuiz(String idQuiz) {
        try{
            return ResponseEntity.ok(this.scoreService.getScoresByQuiz(idQuiz));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch(NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<ScoreDto>> getScoreByCustomer(String idCustomer) {
        try{
            return ResponseEntity.ok(this.scoreService.getScoreByCustomer(idCustomer));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch(NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
