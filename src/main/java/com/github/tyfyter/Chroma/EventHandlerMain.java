package com.github.tyfyter.Chroma;
import net.minecraft.entity.player.EntityPlayer;
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
}
