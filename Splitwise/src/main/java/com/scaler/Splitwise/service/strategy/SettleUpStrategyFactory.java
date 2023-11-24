package com.scaler.Splitwise.service.strategy;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategyFactory(){
        return new HeapBasedSettleUpStrategy();
    }
}


/*
following strategy design pattern*/
