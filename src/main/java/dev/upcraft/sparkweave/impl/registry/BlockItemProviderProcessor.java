package dev.upcraft.sparkweave.impl.registry;

import dev.upcraft.sparkweave.api.registry.RegistryVisitor;
import dev.upcraft.sparkweave.api.registry.block.BlockItemProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class BlockItemProviderProcessor {

    public static void register() {
        RegistryVisitor.visitRegistry(BuiltInRegistries.BLOCK, (id, block) -> {
            if(block instanceof BlockItemProvider provider) {
                Registry.register(BuiltInRegistries.ITEM, id, provider.createItem());
            }
        });
    }
}
