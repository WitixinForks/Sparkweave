package dev.upcraft.sparkweave.quilt.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModContainer;

public record QuiltModContainer(QuiltModMetadata metadata) implements ModContainer {

    public static QuiltModContainer of(org.quiltmc.loader.api.ModContainer delegate) {
        return new QuiltModContainer(new QuiltModMetadata(delegate));
    }
}
