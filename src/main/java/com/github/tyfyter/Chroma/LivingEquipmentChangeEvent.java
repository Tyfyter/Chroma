package com.github.tyfyter.Chroma;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LivingEquipmentChangeEvent extends LivingEvent {
    public final ItemStack item;
	public LivingEquipmentChangeEvent(EntityLivingBase entity, ItemStack item) {
		super(entity);
		this.item = item;
	}

}