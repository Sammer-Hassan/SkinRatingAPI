package com.valorant.skinelo.controllers;


import com.valorant.skinelo.entity.Skin;
import com.valorant.skinelo.service.SkinService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/v1/skin")
public class SkinController {
    private SkinService SS;
    private final Bucket bucket;

    public SkinController(SkinService SS) {
        this.SS = SS;
        Bandwidth limit = Bandwidth.classic(200, Refill.greedy(200, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping
    public Skin createSkin(@RequestBody Skin skin){return SS.createSkin(skin);}

    @GetMapping
    public ResponseEntity<Skin>  getRandomSkin() {

        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(SS.getRandomSkin());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

    }




    @GetMapping("/Leaderboard")
    public ResponseEntity<List<Skin>> Leaderboard(){

        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(SS.getOrderedSkin());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

    }

    @PostMapping("/UpdateElo")
    public void updateElo(@RequestBody List<Long> values){
        if (bucket.tryConsume(1)) {
            SS.setElo(values);
        }
    }


}
