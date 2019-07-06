package eu.usrv.lootgames.command;


import eu.usrv.lootgames.LootGames;
import eu.usrv.yamcore.auxiliary.PlayerChatHelper;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;
import java.util.List;


public class PeacefulEntityCommand implements ICommand {
    private List aliases;

    public PeacefulEntityCommand() {
        this.aliases = new ArrayList();
        this.aliases.add("pfen");
    }

    @Override
    public int compareTo(Object arg0) {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "peacefullentity";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Check the readme for usage";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender pCmdSender, String[] pArgs) {
        try {
            if (pArgs.length < 1) {
                SendHelpToPlayer(pCmdSender);
                return;
            }
            String tSubCommand = pArgs[0];

            if (tSubCommand.equalsIgnoreCase("spawn")) {
                EntityZombie zombie = new EntityZombie(((EntityPlayer) pCmdSender).worldObj);
                zombie.forceSpawn = true;
                zombie.tasks.taskEntries.clear();
                zombie.setLocationAndAngles(((EntityPlayer) pCmdSender).posX, ((EntityPlayer) pCmdSender).posY + 1, ((EntityPlayer) pCmdSender).posZ, MathHelper.wrapAngleTo180_float(LootGames.Rnd.nextFloat() * 360.0F), 0.0F);
                zombie.rotationYawHead = zombie.rotationYaw;
                zombie.renderYawOffset = zombie.rotationYaw;
                ((EntityPlayer) pCmdSender).worldObj.spawnEntityInWorld(zombie);
                zombie.playLivingSound();
            }
        } catch (Exception e) {
            e.printStackTrace();
            PlayerChatHelper.SendError(pCmdSender, "Unknown error occoured");
        }
    }

    private boolean InGame(ICommandSender pCmdSender) {
        return pCmdSender instanceof EntityPlayer;
    }

    private void SendHelpToPlayer(ICommandSender pCmdSender) {
        if (!InGame(pCmdSender)) {
            PlayerChatHelper.SendPlain(pCmdSender, "Command can only be executed ingame");
        } else {
            PlayerChatHelper.SendInfo(pCmdSender, "Check the readme for usage");
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender pCommandSender) {
        if (pCommandSender instanceof EntityPlayerMP) {
            EntityPlayerMP tEP = (EntityPlayerMP) pCommandSender;
            boolean tPlayerOpped = MinecraftServer.getServer().getConfigurationManager().func_152596_g(tEP.getGameProfile());
            boolean tIncreative = tEP.capabilities.isCreativeMode;
            return tPlayerOpped && tIncreative;
        } else return pCommandSender instanceof MinecraftServer;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }
}