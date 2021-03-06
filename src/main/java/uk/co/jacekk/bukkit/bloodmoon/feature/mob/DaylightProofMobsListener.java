package uk.co.jacekk.bukkit.bloodmoon.feature.mob;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityCombustEvent;

import uk.co.jacekk.bukkit.baseplugin.event.BaseListener;
import uk.co.jacekk.bukkit.bloodmoon.BloodMoon;
import uk.co.jacekk.bukkit.bloodmoon.Feature;

public class DaylightProofMobsListener extends BaseListener<BloodMoon> {
	
	public DaylightProofMobsListener(BloodMoon plugin){
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityCombust(EntityCombustEvent event){
		String worldName = event.getEntity().getWorld().getName();
		EntityType type = event.getEntityType();
		
		if ((type == EntityType.ZOMBIE || type == EntityType.SKELETON) && plugin.isActive(worldName) && plugin.isFeatureEnabled(worldName, Feature.DAYLIGHT_PROOF_MOBS)){
			event.setCancelled(true);
		}
	}
	
}
