package com.github.tyfyter.Chroma;

import com.google.common.collect.BiMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemString extends Item {
	public static final String[] colornamesarray = new String[]{"white","orange","magenta","skyblue","yellow","lime","pink","gray","lightgray","cyan","purple","blue","brown","green","red","black"};
	public static final BiMap<Integer,String> colornames = (BiMap<Integer, String>) Chroma.ArrtoMap(colornamesarray);
	static final int[] colorvalues = new int[]{16777215,16736000,16711935,3658495,16766720,11206400,16746700,4342338,10066329,42395,6160567,1645000,9127187,1999872,16718105,0};
	
	int color = 0;

	public ItemString(int color) {
		this.setCreativeTab(Chroma.tabChroma);
		this.color = color;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass){
		return colorvalues[color];//16777215;
	}
	
	@Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

	@Override
	public boolean onItemUse(ItemStack stack, final EntityPlayer playerIn, World worldIn, final BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}
}
