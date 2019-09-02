package com.integral.enigmaticlegacy.packets;

import java.util.function.Supplier;

import com.integral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.integral.enigmaticlegacy.items.EyeOfNebula;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketConfirmTeleportation {
	
	private boolean pressed;

	  public PacketConfirmTeleportation(boolean pressed) {
	    this.pressed = pressed;
	  }

	  public static void encode(PacketConfirmTeleportation msg, PacketBuffer buf) {
	     buf.writeBoolean(msg.pressed);
	  }

	  public static PacketConfirmTeleportation decode(PacketBuffer buf) {
	    return new PacketConfirmTeleportation(buf.readBoolean());
	 }
	  
	  public static void handle(PacketConfirmTeleportation msg, Supplier<NetworkEvent.Context> ctx) {

		    ctx.get().enqueueWork(() -> {
		      ServerPlayerEntity playerServ = ctx.get().getSender();
				
		      System.out.println("Illuminati confirmed");
		      
		         for (int counter = 0; counter <= 32; counter++) {
		        	 if (SuperpositionHandler.validTeleportRandomly(playerServ, playerServ.world, (int) EyeOfNebula.dodgeRange))
		        		 break;
		         }
		      
		    
		    });
		    ctx.get().setPacketHandled(true);
	  }

}
