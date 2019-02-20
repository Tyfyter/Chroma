package com.github.tyfyter.Chroma.Effects;

import com.github.tyfyter.Chroma.Chroma;
import com.github.tyfyter.Chroma.Networking.BBPackage;
import com.github.tyfyter.Chroma.Networking.NoClipPackage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

public class SmolCatEffect extends Potion {
	public ResourceLocation Sprite = new ResourceLocation("chroma","/textures/gui/SmolCat.png");
	public SmolCatEffect(ResourceLocation location, boolean badEffect, int potionColor) {
		super(location, badEffect, potionColor);
	}
	
	//public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier){
	public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111185_2_, int amplifier) {
		/*
				AxisAlignedBB bb = player.boundingBox;
				bb = new AxisAlignedBB(bb.minX,bb.minY,bb.minZ,bb.maxX,bb.minY+((bb.minY-bb.maxY)/2),bb.maxZ);
				player.setEntityBoundingBox(bb);
				Chroma.INSTANCE.sendTo(new BBPackage(bb),(EntityPlayerMP)player);
		 */
		//AxisAlignedBB bb = entityLivingBaseIn.boundingBox;
		//bb = new AxisAlignedBB(bb.minX,bb.minY,bb.minZ,bb.maxX,bb.minY+((bb.minY-bb.maxY)/(amplifier+1)),bb.maxZ);
		//entityLivingBaseIn.boundingBox = bb;//.offset(0,-1,0);
		//if(entityLivingBaseIn instanceof EntityPlayerMP)Chroma.INSTANCE.sendTo(new BBPackage(bb),(EntityPlayerMP)entityLivingBaseIn);
		System.out.println("pot is "+entityLivingBaseIn.worldObj.isRemote);
		entityLivingBaseIn.setSize(entityLivingBaseIn.width/(amplifier+1),entityLivingBaseIn.height/(amplifier+1));
		if(entityLivingBaseIn instanceof EntityPlayerMP)Chroma.INSTANCE.sendTo(new BBPackage(entityLivingBaseIn.width,entityLivingBaseIn.height),(EntityPlayerMP)entityLivingBaseIn);
	}
	
	public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111187_2_, int amplifier){
		/*AxisAlignedBB bb = entityLivingBaseIn.boundingBox;
		bb = new AxisAlignedBB(bb.minX,bb.minY,bb.minZ,bb.maxX,bb.minY+((bb.minY-bb.maxY)*(amplifier+3)),bb.maxZ);
		entityLivingBaseIn.setEntityBoundingBox(bb);
		Chroma.INSTANCE.sendTo(new BBPackage(bb),(EntityPlayerMP)entityLivingBaseIn);
		*/
		entityLivingBaseIn.setSize(entityLivingBaseIn.width*(amplifier+1),entityLivingBaseIn.height*(amplifier+1));
		if(entityLivingBaseIn instanceof EntityPlayerMP)Chroma.INSTANCE.sendTo(new BBPackage(entityLivingBaseIn.width,entityLivingBaseIn.height),(EntityPlayerMP)entityLivingBaseIn);
    }
	public static void applyAttributesModifiersToEntityStatically(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111185_2_, int amplifier) {
		System.out.println("static pot is "+entityLivingBaseIn.worldObj.isRemote);
		entityLivingBaseIn.setSize(entityLivingBaseIn.width/(amplifier+1),entityLivingBaseIn.height/(amplifier+1));
		if(entityLivingBaseIn instanceof EntityPlayerMP)Chroma.INSTANCE.sendTo(new BBPackage(entityLivingBaseIn.width,entityLivingBaseIn.height),(EntityPlayerMP)entityLivingBaseIn);
	}
}
