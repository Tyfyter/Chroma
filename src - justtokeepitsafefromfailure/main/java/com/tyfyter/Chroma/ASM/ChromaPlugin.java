package com.tyfyter.Chroma.ASM;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

import java.util.Map;

;

@MCVersion("1.8.9")
@TransformerExclusions({"com.tyfyter.Chroma"})
public class ChromaPlugin implements IFMLLoadingPlugin {
	
	@Override
	public String[] getASMTransformerClass() {
		// TODO Auto-generated method stub
		System.out.println("found Problem at 1");
		return new String[]{"com.tyfyter.Chroma.ASM.ChromaClassTransformer"};
	}

	@Override
	public String getModContainerClass() {
		// TODO Auto-generated method stub
		System.out.println("found Problem at 2");
		return null;
	}

	@Override
	public String getSetupClass() {
		// TODO Auto-generated method stub
		System.out.println("found Problem at 3");
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		System.out.println("found Problem at 4");
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
