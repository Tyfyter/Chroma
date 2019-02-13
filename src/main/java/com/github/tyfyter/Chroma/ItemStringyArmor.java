package com.github.tyfyter.Chroma;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class ItemStringyArmor extends ItemArmor {
	int color = -1;
	public ItemStringyArmor(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(Chroma.tabChroma);
		this.setMaxStackSize(1);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
		try{
			switch (ItemString.colornames.inverse().get(color)){
			
			}
		}catch (Exception e){}
	}
}
