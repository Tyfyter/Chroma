package com.tyfyter.Chroma.ASM;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Arrays;

public class ChromaModContainer extends DummyModContainer {
	public ChromaModContainer(){
		super();
		ModMetadata meta = new ModMetadata();
		meta.modId = "ChromaASM";
		meta.name = "ChromaASM";
		meta.version = "hopefully the only one I need";
		meta.authorList = Arrays.asList("Tyfyter");
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller){
		bus.register(this);
		return true;
	}
}
