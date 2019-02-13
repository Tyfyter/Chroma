/*
package com.tyfyter.Chroma.ASM;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

;

@MCVersion("1.8.9")
@TransformerExclusions({"com.tyfyter.Chroma"})
public class ChromaPlugin implements IFMLLoadingPlugin {
	public ChromaPlugin(){
		System.out.println("Not Bad Idea");
		MixinBootstrap.init();
	}
	@Override
	public String[] getASMTransformerClass() {
		return new String[0];//{"com.tyfyter.Chroma.ASM.ChromaClassTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "com.tyfyter.Chroma.ASM.ChromaModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}//*/
