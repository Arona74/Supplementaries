package net.mehvahdjukaar.supplementaries.integration;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class CreateCompat {

    @ExpectPlatform
    public static void initialize(){
        throw new AssertionError();
    }
}
