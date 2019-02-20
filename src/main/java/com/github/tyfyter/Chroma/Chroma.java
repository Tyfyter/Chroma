package com.github.tyfyter.Chroma;

import com.github.tyfyter.Chroma.Effects.*;
import com.github.tyfyter.Chroma.Entities.*;
import com.github.tyfyter.Chroma.Entities.Render.*;
import com.github.tyfyter.Chroma.Networking.*;
import com.google.common.collect.HashBiMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
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
    
    //@SidedProxy(serverSide = "ServerProxy", clientSide = "ClientProxy")
    public static CommonProxy proxy;
    
    static ScriptEngineManager mgr = new ScriptEngineManager(null);
    static ScriptEngine engine = mgr.getEngineByName("nashorn");
    
    public static NoClipEffect noClip;
	public static SandCatEffect SandCat;
	public static SmolCatEffect SmolCat;
	public static HellCatEffect HellCat;
	public static NoFallEffect noFall;
    
    //declaration
    public static Item[] ItemStrings = new Item[16];
	public static Item[][] ItemStringyArmors = new Item[16][4];
	public ItemPotion itemPotion;
    
    public static CreativeTabs tabChroma = new CreativeTabsChroma("Chroma");
    public static int index = 0;
	
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
	    //proxy.init();
		MinecraftForge.EVENT_BUS.register(new EventHandlerMain());
		FMLCommonHandler.instance().bus().register(new EventHandlerMain());
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpiderRed.class, new RenderRedSpider(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpiderBlack.class, new RenderBlackSpider(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(EntitySpiderYellow.class, new RenderYellowSpider(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderBrown.class, new RenderBrownSpider(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderOrange.class, new RenderOrangeSpider(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayer(Minecraft.getMinecraft().getRenderManager()));
        for (int i = 0; i < ItemStrings.length; i++) {
        	ItemStack it = new ItemStack(Blocks.wool);
        	it.setItemDamage(i);
        	GameRegistry.addRecipe(it,"XX","XX",'X',ItemStrings[i]);
			it = new ItemStack(ItemStringyArmors[i][0]);
			GameRegistry.addRecipe(it,"XXX","X X","   ",'X',ItemStrings[i]);
			it = new ItemStack(ItemStringyArmors[i][1]);
			GameRegistry.addRecipe(it,"X X","XXX","XXX",'X',ItemStrings[i]);
			it = new ItemStack(ItemStringyArmors[i][2]);
			GameRegistry.addRecipe(it,"XXX","X X","X X",'X',ItemStrings[i]);
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
    //proxy.preInit();
    for (int i = 0; i < ItemStrings.length; i++) {
        ItemStrings[i] = new ItemString(i).setUnlocalizedName(ItemString.colornames.get(i)+"_string").setMaxStackSize(64);//setTextureName(EpikMod.MODID + ":" + "itemTest");
        System.out.println("registering string as chroma:"+ItemStrings[i].getUnlocalizedName().replace("item.", ""));
        GameRegistry.registerItem(ItemStrings[i], ItemStrings[i].getUnlocalizedName().replace("item.", ""));
		ModelLoader.setCustomModelResourceLocation(ItemStrings[i], 0, new ModelResourceLocation("chroma:string"));
	}
	for (int i = 0; i < ItemStrings.length; i++) {
		for(int j = 0; j < 3; j++){
			ItemStringyArmors[i][j] = new ItemStringyArmor(ItemArmor.ArmorMaterial.CHAIN, j, j, i).setUnlocalizedName(ItemString.colornames.get(i)+"_string_"+ItemStringyArmor.pieceNames[j]);//setTextureName(EpikMod.MODID + ":" + "itemTest");
			System.out.println("registering stringy armor as chroma:"+ItemStringyArmors[i][j].getUnlocalizedName().replace("item.", ""));
			GameRegistry.registerItem(ItemStringyArmors[i][j], ItemStringyArmors[i][j].getUnlocalizedName().replace("item.", ""));
			ModelLoader.setCustomModelResourceLocation(ItemStringyArmors[i][j], 0, new ModelResourceLocation("chroma:string_"+ItemStringyArmor.pieceNames[j]));
		}
	}
	
	itemPotion = new ItemPotion().setUnlocalizedName("potion");//setTextureName(EpikMod.MODID + ":" + "itemTest");
	System.out.println("registering potion as chroma:"+itemPotion.getUnlocalizedName().replace("item.", ""));
	GameRegistry.registerItem(itemPotion, itemPotion.getUnlocalizedName().replace("item.", ""));
	ModelLoader.setCustomModelResourceLocation(itemPotion, 0, new ModelResourceLocation("minecraft:potion"));
    int rindex = 0;
    noClip = new NoClipEffect(new ResourceLocation("chroma","noClip"), false, 13884650);
    SandCat = new SandCatEffect(new ResourceLocation("chroma","SandCat"), false, 16777164);
	HellCat = new HellCatEffect(new ResourceLocation("chroma","HellCat"), false, 16718105);
	noFall = new NoFallEffect(new ResourceLocation("chroma","noFall"), false, 13884650);
	SmolCat = new SmolCatEffect(new ResourceLocation("chroma","SmolCat"), false, 13884650);
	try{
		ItemStack itemStackIn = new ItemStack(Item.getByNameOrId("potion"));
		itemStackIn.setItemDamage(16);
		ItemStack itemStackOut = new ItemStack(Item.getByNameOrId("potion"));
		itemStackOut.setTagInfo("CustomPotionEffects", JsonToNBT.getTagFromJson("[{Id:"+SandCat.id+",Duration:1800}]"));
		BrewingRecipeRegistry.addRecipe(itemStackIn, new ItemStack(ItemStrings[ItemString.colornames.inverse().get("yellow")]), itemStackOut);
	}catch(NBTException e){}
	INSTANCE.registerMessage(NoClipHandler.class, NoClipPackage.class, 0, Side.CLIENT);
	INSTANCE.registerMessage(BBHandler.class, BBPackage.class, 0, Side.CLIENT);
    //since red spiders and string were the first ones I added
    EntityRegistry.registerModEntity(EntitySpiderRed.class, "RedSpider", ++rindex, CInstance, 80, 3, true/*, 10027008*/, 14155776, 16734262);
    EntityRegistry.registerModEntity(EntitySpiderBlack.class, "BlackSpider", ++rindex, CInstance, 120, 3, true, 1118481, 3355443);
    EntityRegistry.registerModEntity(EntitySpiderYellow.class, "YellowSpider", ++rindex, CInstance, 60, 3, true, 16777072, 16771328);
    EntityRegistry.registerModEntity(EntitySpiderBrown.class, "BrownSpider", ++rindex, CInstance, 50, 3, true, 9127187, 16771328);
	EntityRegistry.registerModEntity(EntitySpiderOrange.class, "OrangeSpider", ++rindex, CInstance, 40, 3, true, 16734262, 10027008);
	EntityRegistry.registerModEntity(EntityOrangeWeb.class, "OrangeSpiderWeb", ++rindex, CInstance, 40, 3, true);
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
}
