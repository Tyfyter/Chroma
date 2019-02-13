package com.tyfyter.Chroma;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

@SuppressWarnings("unused")
public class CreativeTabsChroma extends CreativeTabs {

	public CreativeTabsChroma(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public CreativeTabsChroma(int index, String label) {
		super(index, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Chroma.ItemStrings[ItemString.colornames.inverse().get("red")];
	}

}
