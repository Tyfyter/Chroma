package com.github.tyfyter.Chroma;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;

public class CommandEffect extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "effect";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    /**
     * Gets the usage string for the command.
     *  
     * @param sender The command sender that executed the command
     */
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.effect.usage"+" [isTickDuration]";
    }

    /**
     * Callback when the command is invoked
     *  
     * @param sender The command sender that executed the command
     * @param args The arguments that were passed
     */
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("commands.effect.usage", new Object[0]);
        }
        else
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)getEntity(sender, args[0], EntityLivingBase.class);

            if(args.length>=3)if (args[2].toString().equals("get")){
                int i;

                try
                {
                    i = parseInt(args[1], 1);
                }
                catch (NumberInvalidException numberinvalidexception)
                {
                    Potion potion = Potion.getPotionFromResourceLocation(args[1]);

                    if (potion == null)
                    {
                        throw numberinvalidexception;
                    }

                    i = potion.id;
                }
    			sender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, -1);
                for(PotionEffect e : entitylivingbase.getActivePotionEffects()){
                	if(e.getPotionID()==i){
                		if(args.length>3){
                			//args[3].toString().toLowerCase().equals("dur")||args[3].toString().toLowerCase().equals("duration")?e.getDuration():e.getAmplifier();
                			sender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, args[3].contains("dur")?e.getDuration():e.getAmplifier()+1);
                			if(sender.sendCommandFeedback()){
                				if(args[3].contains("dur")){
                					Minecraft.getMinecraft().getIntegratedServer().getCommandManager().executeCommand(sender, "/say successfully detected "+e.getEffectName()+" with duration "+(e.getDuration())+" on "+entitylivingbase.getName());
                				}else{
                					Minecraft.getMinecraft().getIntegratedServer().getCommandManager().executeCommand(sender, "/say successfully detected "+e.getEffectName()+" level "+(e.getAmplifier()+1)+" on "+entitylivingbase.getName());
                				}
                			}
                			//notifyOperators(sender, this, "commands effect success "+e.getAmplifier()+", "+e.getDuration(), new Object[] {new ChatComponentTranslation(e.getEffectName(), new Object[0]), Integer.valueOf(i), Integer.valueOf(e.getAmplifier()), entitylivingbase.getName(), Integer.valueOf(e.getDuration())});
                		}else{
                			//e.getAmplifier();
                			sender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, e.getAmplifier());
                			if(sender.sendCommandFeedback())Minecraft.getMinecraft().getIntegratedServer().getCommandManager().executeCommand(sender, "/say successfully detected "+e.getEffectName()+" level "+(e.getAmplifier()+1)+" on "+entitylivingbase.getName());
                            //notifyOperators(sender, this, "commands effect success "+e.getAmplifier(), new Object[] {new ChatComponentTranslation(e.getEffectName(), new Object[0]), Integer.valueOf(i), Integer.valueOf(e.getAmplifier()), entitylivingbase.getName(), Integer.valueOf(e.getDuration())});
                		}
                        return;
                	}
                	//if(e.getPotionID()==(Potion.getPotionFromResourceLocation(args[1])==null?Potion.getPotionFromResourceLocation(args[1]).id:Potion.getPotionFromResourceLocation(args[1])));
                }
                return;
            }if (args[1].equals("clear")){
                if (entitylivingbase.getActivePotionEffects().isEmpty())
                {
                    throw new CommandException("commands.effect.failure.notActive.all", new Object[] {entitylivingbase.getName()});
                }
                else
                {
                    entitylivingbase.clearActivePotions();
                    notifyOperators(sender, this, "commands.effect.success.removed.all", new Object[] {entitylivingbase.getName()});
                }
            }else{
                int i;

                try
                {
                    i = parseInt(args[1], 1);
                }
                catch (NumberInvalidException numberinvalidexception)
                {
                    Potion potion = Potion.getPotionFromResourceLocation(args[1]);

                    if (potion == null)
                    {
                        throw numberinvalidexception;
                    }

                    i = potion.id;
                }

                int j = 600;
                int l = 30;
                int k = 0;

                if (i >= 0 && i < Potion.potionTypes.length && Potion.potionTypes[i] != null)
                {
                    Potion potion1 = Potion.potionTypes[i];

                    if (args.length >= 3)
                    {
                        l = parseInt(args[2], 0, 1000000);

                        if (potion1.isInstant() || (args.length >= 6 && "true".equalsIgnoreCase(args[5])))
                        {
                            j = l;
                        }
                        else
                        {
                            j = l * 20;
                        }
                    }
                    else if (potion1.isInstant())
                    {
                        j = 1;
                    }

                    if (args.length >= 4)
                    {
                        k = parseInt(args[3], -256, 255);
                    }

                    boolean flag = true;

                    if (args.length >= 5 && "true".equalsIgnoreCase(args[4]))
                    {
                        flag = false;
                    }

                    if (l > 0)
                    {
                        PotionEffect potioneffect = new PotionEffect(i, j, k, false, flag);
                        entitylivingbase.addPotionEffect(potioneffect);
                        notifyOperators(sender, this, "commands.effect.success", new Object[] {new ChatComponentTranslation(potioneffect.getEffectName(), new Object[0]), Integer.valueOf(i), Integer.valueOf(k), entitylivingbase.getName(), Integer.valueOf(l)});
                    }
                    else if (entitylivingbase.isPotionActive(i))
                    {
                        entitylivingbase.removePotionEffect(i);
                        notifyOperators(sender, this, "commands.effect.success.removed", new Object[] {new ChatComponentTranslation(potion1.getName(), new Object[0]), entitylivingbase.getName()});
                    }
                    else
                    {
                        throw new CommandException("commands.effect.failure.notActive", new Object[] {new ChatComponentTranslation(potion1.getName(), new Object[0]), entitylivingbase.getName()});
                    }
                }
                else
                {
                    throw new NumberInvalidException("commands.effect.notFound", new Object[] {Integer.valueOf(i)});
                }
            }
        }
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, this.getAllUsernames()) : (args.length == 2 ? getListOfStringsMatchingLastWord(args, Potion.getPotionLocations()) : (args.length == 5 ? getListOfStringsMatchingLastWord(args, new String[] {"true", "false"}): null));
    }

    protected String[] getAllUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     *  
     * @param args The arguments that were passed
     */
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}
