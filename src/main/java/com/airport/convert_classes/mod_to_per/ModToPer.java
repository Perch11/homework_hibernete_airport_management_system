package com.airport.convert_classes.mod_to_per;

public abstract class ModToPer<M,P> {
    public ModToPer() {
    }

    public abstract P getPersistentFromModel(M model);
}
