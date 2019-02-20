package com.github.tyfyter.Chroma;

import com.github.tyfyter.Chroma.Networking.BBPackage;
import com.github.tyfyter.Chroma.Networking.NoClipPackage;
import net.minecraft.block.BlockLadder;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class ItemStringyArmor extends ItemArmor {
	int color = -1;
	public static String[] pieceNames = new String[]{"cap","tunic","chaps","boots"/*even though that doesn't make sense*/};
	public ItemStringyArmor(ArmorMaterial material, int renderIndex, int armorType, int color) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(Chroma.tabChroma);
		this.setMaxStackSize(1);
		this.color = color;
	}
	@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass){
		return ItemString.colorvalues[color];//16777215;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
		if(armorType == 0){
			if(!getSetBonus(player))return;
		}else{
			return;
		}
		try{
			switch (color){
			case 3:
				if(!player.onGround&&player.isSneaking()&&!player.capabilities.isFlying)player.motionY+=0.075;
				if(!player.onGround&&!player.isSneaking())player.onGround=true;
				player.addPotionEffect(new PotionEffect(Chroma.noFall.id, 1));
				break;
			case 4:
				player.addPotionEffect(new PotionEffect(Chroma.SandCat.id, 1));
				break;
			case 8:
				if((player.motionY>0.1&&!player.onGround)||player.isSneaking())player.addPotionEffect(new PotionEffect(Chroma.noClip.id, 2));
				break;
			case 12:
				if(player.getActivePotionEffect(Chroma.SmolCat)==null){
					player.addPotionEffect(new PotionEffect(Chroma.SmolCat.id, 2, 1));
				}else{
					player.getActivePotionEffect(Chroma.SmolCat).duration = 2;
				}
				break;
			case 14:
				int j = 0;
				if(player.getActivePotionEffect(Chroma.HellCat)!=null)j = player.getActivePotionEffect(Chroma.HellCat).getAmplifier();
				player.addPotionEffect(new PotionEffect(Chroma.HellCat.id, 5, j));
				break;
			default:
				break;
			}
		}catch (Exception e){}
	}
	public boolean getSetBonus(EntityPlayer player){
		try{
			if(!(player.getCurrentArmor(1).getItem() instanceof ItemStringyArmor)) return false;
			if(!(player.getCurrentArmor(2).getItem() instanceof ItemStringyArmor)) return false;
			if(!(player.getCurrentArmor(3).getItem() instanceof ItemStringyArmor)) return false;
			if(((ItemStringyArmor)player.getCurrentArmor(1).getItem()).color != color) return false;
			if(((ItemStringyArmor)player.getCurrentArmor(2).getItem()).color != color) return false;
			if(((ItemStringyArmor)player.getCurrentArmor(3).getItem()).color != color) return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
