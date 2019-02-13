package com.tyfyter.Chroma.Effects;

import java.util.UUID;

import com.tyfyter.Chroma.Chroma;
import com.tyfyter.Chroma.Networking.NoClipPackage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class NoClipEffect extends Potion {
	public ResourceLocation Sprite = new ResourceLocation("chroma","/textures/gui/noClip.png");
	public NoClipEffect(ResourceLocation location, boolean badEffect, int potionColor) {
		super(location, badEffect, potionColor);
		// TODO Auto-generated constructor stub
	}
	
	public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111185_2_, int amplifier) {
		if(entityLivingBaseIn instanceof EntityPlayer)Chroma.INSTANCE.sendTo(new NoClipPackage(true), (EntityPlayerMP) entityLivingBaseIn);
		entityLivingBaseIn.noClip = true;
	}

	public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111187_2_, int amplifier){
		System.out.println("buff3:"+(entityLivingBaseIn.worldObj.isRemote?"CLIENT":"SERVER")+";"+entityLivingBaseIn.noClip);
    	if(entityLivingBaseIn instanceof EntityPlayer)Chroma.INSTANCE.sendTo(new NoClipPackage(false), (EntityPlayerMP) entityLivingBaseIn);
        entityLivingBaseIn.noClip = false;
		System.out.println("buff4:"+(entityLivingBaseIn.worldObj.isRemote?"CLIENT":"SERVER")+";"+entityLivingBaseIn.noClip);
    }
}
