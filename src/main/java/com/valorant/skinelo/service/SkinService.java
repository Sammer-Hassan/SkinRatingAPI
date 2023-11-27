package com.valorant.skinelo.service;

import com.valorant.skinelo.entity.Skin;
import com.valorant.skinelo.repository.SkinRepository;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkinService {
    private SkinRepository SR;
    private static final int K_FACTOR = 16;


    public SkinService(SkinRepository SR) {
        this.SR = SR;
    }

    public Skin createSkin(Skin skin){return SR.save(skin);}

    public Skin getRandomSkin(){return SR.findRandomEntity();}

    public List<Skin>getOrderedSkin(){
        List<Skin> allSkins = (List<Skin>)SR.findAll();
        allSkins.sort(Comparator.comparing(Skin::getElo));
        return allSkins;
    }

    private double calculateExpectedWinProbability(int playerElo, int opponentElo) {
        return 1.0 / (1.0 + Math.pow(10.0, (opponentElo - playerElo) / 400.0));
    }

    private int calculateNewElo(int currentElo, double score, double expectedWinProbability) {
        return (int) (currentElo + K_FACTOR * (score - expectedWinProbability));
    }
    public void setElo(List<Long> values) {
        Optional<Skin> winner = SR.findById(values.get(0));
        Optional<Skin> loser = SR.findById(values.get(1));

        Skin winnerSkin = winner.orElse(null);
        Skin loserSkin = loser.orElse(null);

        if (winnerSkin == null || loserSkin == null) {
            return;
        }

        double expectedWinProbability = calculateExpectedWinProbability(winnerSkin.getElo(), loserSkin.getElo());

        int winnerElo = calculateNewElo(winnerSkin.getElo(), 1, expectedWinProbability);
        int loserElo = calculateNewElo(loserSkin.getElo(), 0, 1 - expectedWinProbability);


        if(loserElo < 0){
            loserSkin.setElo(0);
        }else{
            loserSkin.setElo(loserElo);
        }

        if(winnerElo >= 5000){
            winnerSkin.setElo(5000);
        }else{
            winnerSkin.setElo(winnerElo);
        }

        System.out.println(winnerElo + " " + loserElo);

        SR.save(winnerSkin);
        SR.save(loserSkin);
    }


}
