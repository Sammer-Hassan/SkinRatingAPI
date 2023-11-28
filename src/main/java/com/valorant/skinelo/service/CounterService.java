package com.valorant.skinelo.service;

import com.valorant.skinelo.entity.GlobalCounter;
import com.valorant.skinelo.repository.GlobalCounterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CounterService {
    public CounterService(GlobalCounterRepository GR) {
        this.GR = GR;
    }

    private GlobalCounterRepository GR;
    private final long id = 0;

    public Optional<GlobalCounter> getCounter(){
        return GR.findById(id);
    }
    public void increment(){
        Optional<GlobalCounter> hold = GR.findById(id);

        GlobalCounter output = hold.orElse(null);

        if(output == null){
            return;
        }

        output.setCount(output.getCount()+1);
        GR.save(output);

    }
}
