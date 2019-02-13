package com.tyfyter.Chroma;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.renderer.EntityRenderer;

@SuppressWarnings("unused")
public class ItemShaderHelm extends ItemArmor {

	public ItemShaderHelm(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(Chroma.tabChroma);
		this.setMaxStackSize(1);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    	NBTTagCompound compound = itemStack.getTagCompound();
		if(compound==null)return;
		if(!compound.hasKey("Shader"))return;
		if(Minecraft.getMinecraft().entityRenderer.shaderIndex != compound.getInteger("Shader") || Minecraft.getMinecraft().entityRenderer.useShader != true){
			Minecraft.getMinecraft().entityRenderer.shaderIndex = compound.getInteger("Shader");
			Minecraft.getMinecraft().entityRenderer.useShader = true;
			Minecraft.getMinecraft().entityRenderer.loadShader(EntityRenderer.shaderResourceLocations[Math.min(Minecraft.getMinecraft().entityRenderer.shaderIndex, EntityRenderer.shaderResourceLocations.length-1)]);
		}
		/*
		try{
			if(Minecraft.getMinecraft().entityRenderer.theShaderGroup.shaderGroupName!=EntityRenderer.shaderResourceLocations[Minecraft.getMinecraft().entityRenderer.shaderIndex].toString())Minecraft.getMinecraft().entityRenderer.loadShader(EntityRenderer.shaderResourceLocations[Minecraft.getMinecraft().entityRenderer.shaderIndex]);
		}catch(NullPointerException e){
			//Minecraft.getMinecraft().entityRenderer.loadShader(EntityRenderer.shaderResourceLocations[Minecraft.getMinecraft().entityRenderer.shaderIndex]);
			System.out.println(Minecraft.getMinecraft().entityRenderer.theShaderGroup.shaderGroupName.toString());
		}//*/
		super.onArmorTick(world, player, itemStack);
	}
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(!(entityIn instanceof EntityPlayer))return;
    	NBTTagCompound compound = stack.getTagCompound();
		if(compound==null)return;
		if(compound.hasKey("Shader")&&itemSlot!=103){
			if(Minecraft.getMinecraft().entityRenderer.shaderIndex == compound.getInteger("Shader")){
				Minecraft.getMinecraft().entityRenderer.useShader = false;
			}
		}
    }
	@Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
    	NBTTagCompound compound = item.getTagCompound();
		if(compound==null)return true;
		if(compound.hasKey("Shader")){
			if(Minecraft.getMinecraft().entityRenderer.shaderIndex == compound.getInteger("Shader")){
				Minecraft.getMinecraft().entityRenderer.useShader = false;
			}
		}
        return true;
    }
}
