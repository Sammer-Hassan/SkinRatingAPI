package com.valorant.skinelo.controllers;


import com.valorant.skinelo.entity.Skin;
import com.valorant.skinelo.service.SkinService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "https://skinrating.netlify.app")
@RestController
@RequestMapping("/api/v1/skin")
public class SkinController {
    private SkinService SS;

    public SkinController(SkinService SS) {
        this.SS = SS;
    }

    @PostMapping
    public Skin createSkin(@RequestBody Skin skin){return SS.createSkin(skin);}

    @GetMapping
    public Skin  getRandomSkin(){return SS.getRandomSkin();}
    @GetMapping("/Leaderboard")
    public List<Skin> Leaderboard(){return SS.getOrderedSkin();}

    @PostMapping("/UpdateElo")
    public void updateElo(@RequestBody List<Long> values){SS.setElo(values);}


}
