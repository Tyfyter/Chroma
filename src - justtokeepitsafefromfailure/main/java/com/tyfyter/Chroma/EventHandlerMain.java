package com.tyfyter.Chroma;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
public class EventHandlerMain {
	@SubscribeEvent
	public void PlayerTickEvent(Chroma.PlayerTickEvent2 event){
		if(event.player.getActivePotionEffect(Chroma.noClip)!=null){
			System.out.println(event.player.noClip);
			event.player.noClip = true;
		}
	}
}
