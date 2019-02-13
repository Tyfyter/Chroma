package com.tyfyter.Chroma;

import com.google.common.collect.HashBiMap;
import com.tyfyter.Chroma.Effects.NoClipEffect;
import com.tyfyter.Chroma.Entities.EntitySpiderBlack;
import com.tyfyter.Chroma.Entities.EntitySpiderRed;
import com.tyfyter.Chroma.Entities.EntitySpiderYellow;
import com.tyfyter.Chroma.Entities.Render.RenderBlackSpider;
import com.tyfyter.Chroma.Entities.Render.RenderRedSpider;
import com.tyfyter.Chroma.Entities.Render.RenderYellowSpider;
import com.tyfyter.Chroma.Networking.CommonProxy;
import com.tyfyter.Chroma.Networking.NoClipHandler;
import com.tyfyter.Chroma.Networking.NoClipPackage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

@SuppressWarnings({"unused","deprecation"})
@Mod(modid = Chroma.MODID, version = Chroma.VERSION)
public class Chroma
{
    public static final String MC_VERSION = "1.8.9";
    public static final String MODID = "Chroma";
    public static final String VERSION = "0.1.1";
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    
    @Instance
    public static Chroma CInstance;
    
    @SidedProxy(serverSide = "com.tyfyter.Chroma.Networking.ServerProxy", clientSide = "com.tyfyter.Chroma.Networking.ClientProxy")
    public static CommonProxy proxy;
    
    static ScriptEngineManager mgr = new ScriptEngineManager(null);
    static ScriptEngine engine = mgr.getEngineByName("nashorn");
    
    public static NoClipEffect noClip;
    
    //declaration
    public static Item[] ItemStrings = new Item[16];
    
    public static CreativeTabs tabChroma = new CreativeTabsChroma("Chroma");
    public static int index = 0;

	@EventHandler
    public void init(FMLInitializationEvent event)
    {
	    proxy.init();
		MinecraftForge.EVENT_BUS.register(new EventHandlerMain());
		FMLCommonHandler.instance().bus().register(new EventHandlerMain());
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpiderRed.class, new RenderRedSpider(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpiderBlack.class, new RenderBlackSpider(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpiderYellow.class, new RenderYellowSpider(Minecraft.getMinecraft().getRenderManager()));
        for (int i = 0; i < ItemStrings.length; i++) {
        	ItemStack it = new ItemStack(Blocks.wool);
        	it.setItemDamage(i);
        	GameRegistry.addRecipe(it,"XX","XX",'X',ItemStrings[i]);
    	}
		// some example code
		
    	//MinecraftForge.EVENT_BUS.register(new EventHandlerMain());
    	//FMLCommonHandler.instance().bus().register(new EventHandlerMain());
    }

	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		// some example code
		
    	//MinecraftForge.EVENT_BUS.register(new EventHandlerMain());
    	//FMLCommonHandler.instance().bus().register(new EventHandlerMain());
    }
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    proxy.preInit();
    for (int i = 0; i < ItemStrings.length; i++) {
        ItemStrings[i] = new ItemString(i).setUnlocalizedName(ItemString.colornames.get(i)+"_string").setMaxStackSize(64);//setTextureName(EpikMod.MODID + ":" + "itemTest");
        System.out.println("registering string as chroma:"+ItemStrings[i].getUnlocalizedName().replace("item.", ""));
        GameRegistry.registerItem(ItemStrings[i], ItemStrings[i].getUnlocalizedName().replace("item.", ""));
		ModelLoader.setCustomModelResourceLocation(ItemStrings[i], 0, new ModelResourceLocation("chroma:string"));
	}
    int rindex = 0;
    noClip = new NoClipEffect(new ResourceLocation("chroma","noClip"), false, 13884650);
    INSTANCE.registerMessage(NoClipHandler.class, NoClipPackage.class, 0, Side.CLIENT); 
    //since red spiders and string were the first ones I added
    EntityRegistry.registerModEntity(EntitySpiderRed.class, "RedSpider", ++rindex, CInstance, 80, 3, true/*, 10027008*/, 14155776, 16734262);
    EntityRegistry.registerModEntity(EntitySpiderBlack.class, "BlackSpider", ++rindex, CInstance, 120, 3, true, 1118481, 3355443);
    EntityRegistry.registerModEntity(EntitySpiderYellow.class, "YellowSpider", ++rindex, CInstance, 60, 3, true, 16777072, 16771328);
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event){
    }
    
	
	public static void registerRender(Item item, int i){
		boolean fail = false;
		try{
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(MODID.toLowerCase()+":"+item.getUnlocalizedName().substring(5)));
			//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(MODID.toLowerCase()+":"+item.getUnlocalizedName()));
		}catch(Exception e){
			fail = true;
			System.out.println("Failed to register render at index "+index++);
			e.printStackTrace();
		}if(!fail){
			System.out.println("Successfully registered render at index "+index++);
		}
	}
	public static void registerRender(Item item){
		registerRender(item, 0);
	}
	
	public static Object eval(String string) throws ScriptException{
		return engine.eval(string);
	}
	public static <T> Map<Integer,T> ArrtoMap(T[] in){
		HashBiMap<Integer,T> out = HashBiMap.create();//new HashMap<Integer,T>();HashBiMap.create()
		for (int i = 0; i < in.length; i++) {
			out.put(i, in[i]);
		}
		return out;
	}
	public static void PostStupidSpectatorThing(EntityPlayer player){
		try {
			MinecraftForge.EVENT_BUS.post(new PlayerTickEvent2(player));
		}catch (Exception e){e.printStackTrace();}
	}
	public static class PlayerTickEvent2 extends Event {
		public EntityPlayer player;
		public PlayerTickEvent2(){super();}
		public PlayerTickEvent2(EntityPlayer player){super();this.player = player;}
	}
}
