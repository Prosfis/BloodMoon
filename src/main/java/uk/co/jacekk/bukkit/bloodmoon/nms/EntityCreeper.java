package uk.co.jacekk.bukkit.bloodmoon.nms;

import net.minecraft.server.v1_7_R4.World;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.plugin.Plugin;

import uk.co.jacekk.bukkit.bloodmoon.BloodMoon;
import uk.co.jacekk.bukkit.bloodmoon.entity.BloodMoonEntityCreeper;
import uk.co.jacekk.bukkit.bloodmoon.entity.BloodMoonEntityType;

public class EntityCreeper extends net.minecraft.server.v1_7_R4.EntityCreeper {
	
	private BloodMoon plugin;
	private BloodMoonEntityCreeper bloodMoonEntity;
	
	public EntityCreeper(World world){
		super(world);
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin("BloodMoon");
		
		if (plugin == null || !(plugin instanceof BloodMoon)){
			this.world.removeEntity(this);
			return;
		}
		
		this.plugin = (BloodMoon) plugin;
		
		this.bukkitEntity = new CraftCreeper((CraftServer) this.plugin.server, this);
		this.bloodMoonEntity = new BloodMoonEntityCreeper(this.plugin, this, (CraftLivingEntity) this.bukkitEntity, BloodMoonEntityType.CREEPER);
	}
	
	@Override
	public void bL(){
		try{
			this.bloodMoonEntity.onTick();
			super.bL();
		}catch (Exception e){
			plugin.log.warn("Exception caught while ticking entity");
			e.printStackTrace();
		}
	}
	
}