package dev.upcraft.sparkweave.util.vanity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.util.StringRepresentable;

import java.util.List;
import java.util.UUID;

public record SparkweaveVanityData(UUID uuid, String username, Tier tier) {

	public static final Codec<SparkweaveVanityData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			UUIDUtil.STRING_CODEC.fieldOf("uuid").forGetter(SparkweaveVanityData::uuid),
			Codec.STRING.fieldOf("username").forGetter(SparkweaveVanityData::username),
			Tier.CODEC.fieldOf("tier").forGetter(SparkweaveVanityData::tier)
			//TODO other data
	).apply(instance, SparkweaveVanityData::new));

	public static final Codec<List<SparkweaveVanityData>> LIST_CODEC = CODEC.listOf();

	public enum Tier implements StringRepresentable {
		NONE("none", "None"),
		CREATOR("creator", "Creator");

		public static final Codec<Tier> CODEC = StringRepresentable.fromEnum(Tier::values);

		private final String serializedName;
		private final String displayName;

		Tier(String serializedName, String displayName) {
			this.serializedName = serializedName;
			this.displayName = displayName;
		}

		public String getName() {
			return displayName;
		}

		@Override
		public String getSerializedName() {
			return serializedName;
		}
	}
}
