/*package com.tyfyter.Chroma.ASM;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import Chroma;

@Mixin(EntityPlayer.class)
public abstract class MixinEntPlayer {
	@Inject(method="onUpdate", at=@At(value = "INVOKE",target = "Lnet/minecraft/entity/player/EntityPlayer;isSpectator()Z",ordinal = 0, shift = At.Shift.AFTER))
	public void onOnUpdate(CallbackInfo ci){
		System.out.println("not here");
		System.out.println(ci.toString());
		Chroma.CInstance.PostStupidSpectatorThing((EntityPlayer)(Object)this);
	}
}*/