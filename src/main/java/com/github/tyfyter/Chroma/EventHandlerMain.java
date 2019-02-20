package com.github.tyfyter.Chroma;
import com.github.tyfyter.Chroma.Effects.SmolCatEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

@SuppressWarnings("unused")
public class EventHandlerMain {
	public List<String> registeredPlayers = new ArrayList<String>();
	@SubscribeEvent
	public void PlayerTickEvent2(LivingEvent.LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer)if(event.entityLiving.getActivePotionEffect(Chroma.noClip)!=null){
			event.entityLiving.noClip = true;
		}
	}
	@SubscribeEvent
	public void LivingHurtEvent(LivingAttackEvent event){
		if((event.entityLiving.getActivePotionEffect(Chroma.SandCat)!=null)&&(event.source.damageType.equals("inWall")||event.source.damageType.equals("fall"))){
			event.setCanceled(true);
		}
		if((event.entityLiving.getActivePotionEffect(Chroma.noFall)!=null)&&event.source.damageType.equals("fall")){
			event.setCanceled(true);
		}
		if((event.entityLiving.getActivePotionEffect(Chroma.HellCat)!=null)&&(event.source.damageType.toLowerCase().contains("fire")||event.source.damageType.toLowerCase().contains("lava"))){
			//int a = event.entityLiving.getActivePotionEffect(Chroma.HellCat).getAmplifier();
			event.entityLiving.addPotionEffect(new PotionEffect(Chroma.HellCat.id,5, (int)Math.min(event.entityLiving.getActivePotionEffect(Chroma.HellCat).getAmplifier()+Math.max(event.ammount,1), 255f)));
			//System.out.println(a+";"+event.entityLiving.getActivePotionEffect(Chroma.HellCat).getAmplifier());
			event.setCanceled(true);
		}
	}
	@SubscribeEvent
	public void LivingJumpEvent(LivingEvent.LivingJumpEvent event){
		if(event.entityLiving.getActivePotionEffect(Chroma.SandCat)!=null){
			event.entityLiving.addVelocity(event.entityLiving.motionX/3.5, event.entityLiving.motionY/4.75, event.entityLiving.motionZ/3.5);
		}
		if(event.entityLiving.getActivePotionEffect(Chroma.noFall)!=null){
			event.entityLiving.addVelocity(event.entityLiving.motionX/4.5, event.entityLiving.motionY/5.75, event.entityLiving.motionZ/4.5);
		}
	}
	
	@SubscribeEvent
	public void LivingAttackEvent(LivingAttackEvent event) throws Exception{
		try{
			if(((EntityLivingBase)event.source.getEntity()).getActivePotionEffect(Chroma.HellCat).getAmplifier()>0&&!event.entityLiving.isImmuneToFire){
				int damage = ((EntityLivingBase)event.source.getEntity()).getActivePotionEffect(Chroma.HellCat).getAmplifier();
				((EntityLivingBase)event.source.getEntity()).removePotionEffect(Chroma.HellCat.id);
				event.entityLiving.attackEntityFrom(new EntityDamageSource("inFire", event.source.getEntity()){public boolean isDifficultyScaled(){return false;}}.setFireDamage(),damage/5);
			}
		}catch(Exception e){}
	}
	
	@SubscribeEvent
	public void EntityJoinWorldEvent(EntityJoinWorldEvent event){
		/*
		for(EntityPlayer entity: event.world.playerEntities){
			PotionEffect effect = entity.getActivePotionEffect(Chroma.SmolCat);
			if(effect!=null){
				Chroma.SmolCat.applyAttributesModifiersToEntity(entity,entity.getAttributeMap(),effect.getAmplifier());
				//entity.removePotionEffect(Chroma.SmolCat.id);
				System.err.println("auhjioiymgujk ("+entity.getName()+")");
			}
		}//*/
		//for(Entity entity: event.world.loadedEntityList){
			if(event.entity instanceof EntityLivingBase && !(event.entity instanceof EntityPlayer) && !registeredPlayers.contains(event.entity.getUniqueID().toString())){
				PotionEffect effect = ((EntityLivingBase)event.entity).getActivePotionEffect(Chroma.SmolCat);
				if(effect!=null)if(!effect.getIsPotionDurationMax()){
					SmolCatEffect.applyAttributesModifiersToEntityStatically((EntityLivingBase)event.entity,((EntityLivingBase)event.entity).getAttributeMap(),effect.getAmplifier());
					//((EntityLivingBase)entity).removePotionEffect(Chroma.SmolCat.id);
					System.err.println("auhjioiymgujk ("+event.entity.getName()+")"+" with "+effect.getAmplifier()+" and "+event.entity.getUniqueID());
					registeredPlayers.add(event.entity.getUniqueID().toString());
					System.out.println(registeredPlayers);
					effect.setPotionDurationMax(true);
				}else{
					effect.setPotionDurationMax(false);
				}
			}
		//}
	}
	@SubscribeEvent
	public void PlayerTickEvent(TickEvent.PlayerTickEvent event){
		PotionEffect effect = event.player.getActivePotionEffect(Chroma.SmolCat);
		/*
		if(effect != null){
			if(event.phase == TickEvent.Phase.START){
				event.player.setEntityBoundingBox(event.player.getEntityBoundingBox().offset(0,-0.1,0));
			}else{
				event.player.setEntityBoundingBox(event.player.getEntityBoundingBox().offset(0,0.1,0));
			}
		}//*/
	}
	@SubscribeEvent
	public void RenderPlayerEvent(RenderPlayerEvent event){
		event.renderer.getMainModel().aimedBow = true;
	}
	@SubscribeEvent
	public void PlayerLogInEvent(PlayerEvent.PlayerLoggedInEvent event){
		PotionEffect effect = event.player.getActivePotionEffect(Chroma.SmolCat);
		if(effect!=null)if(!effect.getIsPotionDurationMax()){
			SmolCatEffect.applyAttributesModifiersToEntityStatically(event.player,event.player.getAttributeMap(),effect.getAmplifier());
			//((EntityLivingBase)entity).removePotionEffect(Chroma.SmolCat.id);
			System.err.println("auh but not jioiymgujk ("+event.player.getName()+")"+" with "+effect.getAmplifier()+" and "+event.player.getUniqueID());
			registeredPlayers.add(event.player.getUniqueID().toString());
			System.out.println(registeredPlayers);
			effect.setPotionDurationMax(true);
		}else{
			effect.setPotionDurationMax(false);
		}
	}
}