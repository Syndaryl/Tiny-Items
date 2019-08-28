package com.syndaryl.minecraft.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Hammer extends PickaxeItem implements IAOETool {

	public Hammer(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
			stack.damageItem(1, entityLiving, (entityLivingOnBroken) -> {
				entityLivingOnBroken.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}
		return true;
	}

	/**
	 * Called when this item is used when targeting a Block
	 */
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		return ActionResultType.PASS;
	}
	
	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

		return ActionResultType.PASS;
	}
}