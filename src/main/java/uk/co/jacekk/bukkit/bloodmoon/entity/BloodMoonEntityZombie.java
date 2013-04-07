package uk.co.jacekk.bukkit.bloodmoon.entity;

import net.minecraft.server.v1_5_R2.EntityHuman;
import net.minecraft.server.v1_5_R2.EntityMonster;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftLivingEntity;

import uk.co.jacekk.bukkit.baseplugin.config.PluginConfig;
import uk.co.jacekk.bukkit.bloodmoon.BloodMoon;
import uk.co.jacekk.bukkit.bloodmoon.Config;
import uk.co.jacekk.bukkit.bloodmoon.Feature;

public class BloodMoonEntityZombie extends BloodMoonEntityMonster {
	
	public BloodMoonEntityZombie(BloodMoon plugin, EntityMonster nmsEntity, CraftLivingEntity bukkitEntity, BloodMoonEntityType type){
		super(plugin, nmsEntity, bukkitEntity, type);
	}
	
	@Override
	public void onTick(){
		String worldName = nmsEntity.world.worldData.getName();
		String entityName = bukkitEntity.getType().name().toUpperCase();
		PluginConfig worldConfig = plugin.getConfig(worldName);
		
		if (nmsEntity.target instanceof EntityHuman && plugin.isActive(worldName) && plugin.isFeatureEnabled(worldName, Feature.BREAK_BLOCKS) && worldConfig.getStringList(Config.FEATURE_BREAK_BLOCKS_MOBS).contains(entityName) && nmsEntity.world.getTime() % 20 == 0 && nmsEntity.world.worldData.getName().equals(nmsEntity.target.world.worldData.getName())){
			Block[] blocks = new Block[2];
			
			blocks[0] = this.getBreakableTargetBlock();
			blocks[1] = blocks[0].getRelative(BlockFace.UP);
			
			for (Block block : blocks){
				this.attemptBreakBlock(worldConfig, block);
			}
		}
	}
	
}
