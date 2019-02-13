package com.github.tyfyter.Chroma;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
public class EventHandlerMain {
	@SubscribeEvent
	public void PlayerTickEvent2(LivingEvent.LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer)if(event.entityLiving.getActivePotionEffect(Chroma.noClip)!=null){
			event.entityLiving.noClip = true;
		}
	}
	@SubscribeEvent
	public void LivingHurtEvent(LivingAttackEvent event){
		if((event.entityLiving.getActivePotionEffect(Chroma.SandCat)!=null)&&(event.source.damageType=="inWall"||event.source.damageType=="fall")){
			event.setCanceled(true);
		}
	}
	@SubscribeEvent
	public void LivingJumpEvent(LivingEvent.LivingJumpEvent event){
		if(event.entityLiving.getActivePotionEffect(Chroma.SandCat)!=null){
			event.entityLiving.addVelocity(event.entityLiving.motionX/3.5, event.entityLiving.motionY/4.1, event.entityLiving.motionZ/3.5);
		}
	}
}
