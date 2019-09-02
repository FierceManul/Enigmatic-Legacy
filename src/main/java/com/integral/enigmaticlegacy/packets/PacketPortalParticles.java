package com.integral.enigmaticlegacy.packets;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketPortalParticles {
	
	private double x;
	private double y;
	private double z;
	private int num;
	private double rangeModifier;

	  public PacketPortalParticles(double x, double y, double z, int number, double rangeModifier) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	    this.num = number;
	    this.rangeModifier = rangeModifier;
	  }

	  public static void encode(PacketPortalParticles msg, PacketBuffer buf) {
	    buf.writeDouble(msg.x);
	    buf.writeDouble(msg.y);
	    buf.writeDouble(msg.z);
	    buf.writeInt(msg.num);
	    buf.writeDouble(msg.rangeModifier);
	  }

	  public static PacketPortalParticles decode(PacketBuffer buf) {
	    return new PacketPortalParticles(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readInt(), buf.readDouble());
	 }
	  
	  @OnlyIn(Dist.CLIENT)
	  public static void handle(PacketPortalParticles msg, Supplier<NetworkEvent.Context> ctx) {

		    ctx.get().enqueueWork(() -> {
		      PlayerEntity player = Minecraft.getInstance().player;
		      
		      for (int counter = 0; counter <= msg.num; counter++)
		    		player.world.addParticle(ParticleTypes.PORTAL, true, msg.x, msg.y, msg.z, ((Math.random()-0.5D)*2.0D)*msg.rangeModifier, ((Math.random()-0.5D)*2.0D)*msg.rangeModifier, ((Math.random()-0.5D)*2.0D)*msg.rangeModifier);
		      
		      
		    });
		    ctx.get().setPacketHandled(true);
	  }

}
