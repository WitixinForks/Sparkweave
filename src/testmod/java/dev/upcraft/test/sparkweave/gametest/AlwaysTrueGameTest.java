package dev.upcraft.test.sparkweave.gametest;

import net.minecraft.gametest.framework.GameTest;
import org.quiltmc.qsl.testing.api.game.QuiltGameTest;
import org.quiltmc.qsl.testing.api.game.QuiltTestContext;

public class AlwaysTrueGameTest implements QuiltGameTest {

    @GameTest(template = QuiltGameTest.EMPTY_STRUCTURE)
    public void alwaysTrue(QuiltTestContext ctx) {
        ctx.succeed();
    }
}
