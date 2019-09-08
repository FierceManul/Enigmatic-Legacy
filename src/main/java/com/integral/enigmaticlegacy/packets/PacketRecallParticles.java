package com.integral.enigmaticlegacy.packets;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketRecallParticles {
	
	private double x;
	private double y;
	private double z;
	private int num;

	  public PacketRecallParticles(double x, double y, double z, int number) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	    this.num = number;
	  }

	  public static void encode(PacketRecallParticles msg, PacketBuffer buf) {
	    buf.writeDouble(msg.x);
	    buf.writeDouble(msg.y);
	    buf.writeDouble(msg.z);
	    buf.writeInt(msg.num);
	  }

	  public static PacketRecallParticles decode(PacketBuffer buf) {
	    return new PacketRecallParticles(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readInt());
	 }
	  
	  @OnlyIn(Dist.CLIENT)
	  public static void handle(PacketRecallParticles msg, Supplier<NetworkEvent.Context> ctx) {

		    ctx.get().enqueueWork(() -> {
		      PlayerEntity player = Minecraft.getInstance().player;
		      
		      for (int counter = 0; counter <= msg.num; counter++)
		    		player.world.addParticle(ParticleTypes.DRAGON_BREATH, true, msg.x, msg.y, msg.z, (Math.random()-0.5D)*0.2D, (Math.random()-0.5D)*0.2D, (Math.random()-0.5D)*0.2D);
		      
		      
		    });
		    ctx.get().setPacketHandled(true);
	  }

}