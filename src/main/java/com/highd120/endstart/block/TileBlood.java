package com.highd120.endstart.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

public class TileBlood extends TileEntityBase {
	int time = 0;
	
	@Override
	public void subReadNbt(NBTTagCompound compound) {
		super.subReadNbt(compound);
		time = compound.getInteger("time");
	}
	
	@Override
	public void subWriteNbt(NBTTagCompound compound) {
		super.subWriteNbt(compound);
		compound.setInteger("time", time);
	}
	
	@Override
	public void update() {
		time++;
		if (time == 400) {
			IBlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.withProperty(BlockBlood.COLOR, BlockBlood.Color.BLACK), 3);
		}
	}
}
