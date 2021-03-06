package com.highd120.endstart.item;

import com.highd120.endstart.EndStartCreativeTab;
import com.highd120.endstart.block.IUsableFireStarter;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFireStarter extends ItemBase {
	public ItemFireStarter() {
        setCreativeTab(EndStartCreativeTab.INSTANCE);
        this.maxStackSize = 1;
        this.setMaxDamage(299);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
        if (!worldIn.isRemote) {
            stack.damageItem(1, player);
        }
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (!(block instanceof IUsableFireStarter)) {
			return EnumActionResult.PASS;
		}
		IUsableFireStarter target = (IUsableFireStarter)block;
		if (!target.isUsable(worldIn, pos, state)) {
			return EnumActionResult.PASS;
		}
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
			long x = worldIn.getWorldTime();
			if (x == 0) {
				x = 10000;
			}
			tag.setLong("x", x);
			tag.setInteger("count", (int) x % 43);
		} else {
			long x = tag.getLong("x");
			int count = tag.getInteger("count");
			if (count == 0) {
				x = x ^ (x << 7);
				x = x ^ (x >> 9);
				tag.setLong("x", x);
				count = (int)(x % 133);
				tag.setInteger("count", (count + 150) % 40);
				target.fire(worldIn, pos, state);
                worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			} else {
				tag.setInteger("count", count - 1);
			}
		}
		return EnumActionResult.SUCCESS;
	}
}
