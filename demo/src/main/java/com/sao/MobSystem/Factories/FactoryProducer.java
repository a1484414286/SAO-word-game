package com.sao.MobSystem.Factories;

public class FactoryProducer {
    public static AbstractFactory getFactory(boolean enhanced)
    {
        if(enhanced)
        {
            return new EnhancedFactory();
        }
        else
        {
            return new NormalFactory();
        }
    }
}
