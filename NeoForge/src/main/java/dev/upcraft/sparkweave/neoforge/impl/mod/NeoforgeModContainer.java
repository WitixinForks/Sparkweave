package dev.upcraft.sparkweave.neoforge.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModContainer;

public record NeoforgeModContainer(NeoForgeModMetadata metadata) implements ModContainer {

    public static NeoforgeModContainer of(net.neoforged.fml.ModContainer delegate) {
        return new NeoforgeModContainer(new NeoForgeModMetadata(delegate));
    }
}
