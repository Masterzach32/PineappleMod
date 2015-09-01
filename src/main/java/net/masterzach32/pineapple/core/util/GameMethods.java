package net.masterzach32.pineapple.core.util;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class GameMethods {
	
	private Minecraft mc = FMLClientHandler.instance().getClient();

	/**
	 * Calculates the missing health of the entity.
	 * 
	 * @param entity
	 * @return float
	 */
	public static float getMissingHealth(EntityLivingBase entity) {
        return entity.getMaxHealth() - entity.getHealth();
    }
	
	/**
	 * Calculates if the attack is a crit or not, using a chance between 0 and 100.
	 * 
	 * @param crit_chance
	 * @return true if crit, false if not.
	 */
	public static boolean isCritAttack(double crit_chance) {
		int crit = (int) (Math.random() * 100);
		if (crit <= crit_chance - 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Spawns particles around the player like potion effects. 
	 * @param type
	 * @param stack
	 * @param world
	 * @param entity
	 */
	public static void spawnParticles(String type, ItemStack stack, World world, EntityLivingBase entity) {
		if (type == "heal") {
			Random rand = new Random();
			for(int countparticles = 0; countparticles <= 150; ++countparticles) {
				world.spawnParticle("reddust", entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height - (double)entity.yOffset, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 10D, 0.0D);
			}

		} else if (type == "crit") {
			Random rand = new Random();
			for(int countparticles = 0; countparticles <= 100; ++countparticles) {
				world.spawnParticle("crit", entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height - (double)entity.yOffset, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0.0D, 0.0D, 0.0D);
			}
		} else if (type == "projectile") {
			Random rand = new Random();
			for(int countparticles = 0; countparticles <= 150; ++countparticles) {
				world.spawnParticle("reddust", entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height - (double)entity.yOffset, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, 0D, 0D, 0D);
			}
		}
	}
	
	/**
	 * Returns the closest {@link EntityLivingBase} to the cursor within the given range from the player
	 * 
	 * @param tick
	 * @param maxDistance
	 * @param player
	 * @return entity
	 */
	@SuppressWarnings({"rawtypes"})
	public EntityLivingBase getEntityMouseOver(float tick, float maxDistance, EntityPlayer player) {
		if (mc.theWorld != null) {
			double distance = (double)maxDistance;
			// Figuring out how to use this 
			MovingObjectPosition entityMouseOver = mc.renderViewEntity.rayTrace(maxDistance, tick);
			mc.objectMouseOver = mc.renderViewEntity.rayTrace(maxDistance, tick);
			double distance2 = distance;
			Vec3 vec3 = mc.renderViewEntity.getPosition(tick);
			if (this.mc.objectMouseOver != null)
            {
                distance2 = this.mc.objectMouseOver.hitVec.distanceTo(vec3);
            }
			Vec3 vec31 = mc.renderViewEntity.getLook(tick);
			Vec3 vec32 = vec3.addVector(vec31.xCoord * distance, vec31.yCoord * distance, vec31.zCoord * distance);
			List list = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.renderViewEntity, mc.renderViewEntity.boundingBox.addCoord(vec31.xCoord * distance, vec31.yCoord * distance, vec31.zCoord * distance).expand(1.0, 1.0, 1.0));
			
			for (int i = 0; i < list.size(); ++i) {
				Entity entity = (Entity) list.get(i);
				LogHelper.logInfo("Found entity " + entity.getClass());
				if (entity instanceof EntityLivingBase) {
					LogHelper.logInfo("Sending entity " + entity.getClass());
					return (EntityLivingBase) entity;
				}
				LogHelper.logInfo("Illegal Entity " + entity.getClass());
			}
		}
		return null;
	}
}