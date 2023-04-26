package com.airport.convert_classes.per_to_mod;

public abstract class PerToMod<P,M> {

    public PerToMod() {
    }

    public abstract M getModelFromPersistent(P persistent);
}
