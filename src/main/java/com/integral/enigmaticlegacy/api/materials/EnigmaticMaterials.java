package com.integral.enigmaticlegacy.api.materials;

import java.util.function.Supplier;

import com.integral.enigmaticlegacy.EnigmaticLegacy;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

/**
 * Tools material provider.
 * @author Integral
 */

public enum EnigmaticMaterials implements IItemTier {
	   FORBIDDENAXE(0, 2000, 6.0F, 3.0F, 16, () -> {
	      return Ingredient.EMPTY;
	   }),
	
	   ETHERIUM(4, 3000, 8.0F, 5.0F, 32, () -> {
	      return Ingredient.fromItems(EnigmaticLegacy.etheriumIngot);
	   });

	   private final int harvestLevel;
	   private final int maxUses;
	   private final float efficiency;
	   private final float attackDamage;
	   private final int enchantability;
	   private final LazyValue<Ingredient> repairMaterial;

	   private EnigmaticMaterials(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
	      this.harvestLevel = harvestLevelIn;
	      this.maxUses = maxUsesIn;
	      this.efficiency = efficiencyIn;
	      this.attackDamage = attackDamageIn;
	      this.enchantability = enchantabilityIn;
	      this.repairMaterial = new LazyValue<>(repairMaterialIn);
	   }

	   public int getMaxUses() {
	      return this.maxUses;
	   }

	   public float getEfficiency() {
	      return this.efficiency;
	   }

	   public float getAttackDamage() {
	      return this.attackDamage;
	   }

	   public int getHarvestLevel() {
	      return this.harvestLevel;
	   }

	   public int getEnchantability() {
	      return this.enchantability;
	   }

	   public Ingredient getRepairMaterial() {
	      return this.repairMaterial.getValue();
	   }
	}
