package com.tyfyter.Chroma;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.Explosion;
import net.minecraft.command.CommandBase;

@SuppressWarnings("unused")
public class CommandExplode implements ICommand
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "explode";
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
     */
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.explode.usage";
    }
    
	@Override
    public List<String> getCommandAliases() {
    	@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> cmda = new ArrayList();
    	//cmda.add("explode");
    	return cmda;
    }

    /**
     * Callback for when the command is executed
     */
    /*public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.summon.usage", new Object[0]);
        }
        else
        {
            BlockPos blockpos = sender.getPosition();
            Vec3 vec3d = sender.getPositionVector();
            double d0 = vec3d.xCoord;
            double d1 = vec3d.yCoord;
            double d2 = vec3d.zCoord;
            float p = 3F;
            boolean d = false;

            if (args.length >= 3)
            {
                d0 = parseDouble(d0, args[0], true);
                d1 = parseDouble(d1, args[1], false);
                d2 = parseDouble(d2, args[2], true);
                blockpos = new BlockPos(d0, d1, d2);
            }
            if (args.length >= 4)
            {
            	p = Float.parseFloat(args[3]);
            }
            if (args.length >= 5)
            {
            	d = Boolean.parseBoolean(args[4]);
            }

            World world = sender.getEntityWorld();

            if (!world.isBlockLoaded(blockpos))
            {
                throw new CommandException("commands.summon.outOfWorld", new Object[0]);
            }
            else{
            	Explosion explosion = new Explosion(world, sender.getCommandSenderEntity(), d0, d1, d2, p, false, d);
                explosion.doExplosionA();
                explosion.doExplosionB(true);
                //notifyCommandListener(sender, this, "commands.summon.success", new Object[0]);
            }
        }
    }*/

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
        return (args.length >= 0 && args.length < 4 ? CommandBase.func_175771_a(args, 1, pos) : (args.length == 4 ? new ArrayList<String>(Arrays.asList("3")) : (args.length == 5 ? new ArrayList<String>(Arrays.asList("true", "false")) : Collections.<String>emptyList())));
    }

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {

        if (args.length < 1)
        {
            throw new WrongUsageException("commands.summon.usage", new Object[0]);
        }
        else
        {
            BlockPos blockpos = sender.getPosition();
            Vec3 vec3d = sender.getPositionVector();
            double d0 = vec3d.xCoord;
            double d1 = vec3d.yCoord;
            double d2 = vec3d.zCoord;
            float p = 3F;
            boolean d = false;
            boolean e = false;

            if (args.length >= 3)
            {
                d0 = CommandBase.parseDouble(d0, args[0], false);
                d1 = CommandBase.parseDouble(d1, args[1], false);
                d2 = CommandBase.parseDouble(d2, args[2], false);
                blockpos = new BlockPos(d0, d1, d2);
            }
            if (args.length >= 4)
            {
            	p = Float.parseFloat(args[3]);
            }
            if (args.length >= 5)
            {
            	d = Boolean.parseBoolean(args[4]);
            }
            if (args.length >= 6)
            {
            	e = Boolean.parseBoolean(args[5]);
            }

            World world = sender.getEntityWorld();

            if (!world.isBlockLoaded(blockpos))
            {
                throw new CommandException("commands.summon.outOfWorld", new Object[0]);
            }
            else{
            	Explosion explosion = new Explosion(world, sender.getCommandSenderEntity(), d0, d1, d2, p, e, d);
                explosion.doExplosionA();
                explosion.doExplosionB(true);
                //notifyCommandListener(sender, this, "commands.summon.success", new Object[0]);
            }
        }
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		// TODO Auto-generated method stub
		return sender.canCommandSenderUseCommand(getRequiredPermissionLevel(), getCommandName());
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return args.length == 0;
	}
}
