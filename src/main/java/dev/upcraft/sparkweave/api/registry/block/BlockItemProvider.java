package dev.upcraft.sparkweave.api.registry.block;

import net.minecraft.world.item.Item;

public interface BlockItemProvider {

    default Item createItem() {
        return new Item(new Item.Properties());
    }
}
