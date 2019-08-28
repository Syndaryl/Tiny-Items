package com.syndaryl.minecraft.items;

import java.util.ArrayList;

import com.google.common.collect.ImmutableSet;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

/**
 * @author Owner
 * This Interface provides a set of common tools for the Hammer and the Excavator, which do large-area mining/digging.
 * These are for finding blocks surrounding a given block that are valid to break, 
 * updating a list of blocks for break progress, 
 * and breaking a list of blocks (correctly handling drops). 
 */
public interface IAOETool {
	/**
	 * @param block
	 * @return An ImmutableSet of the 3x3x3 cube of blocks directly adjacent to the provided BlockPos, 
	 * including the provided BlockPos 
	 */
	public default ImmutableSet<BlockPos> AdjacentBlocks(BlockPos block){
		ImmutableSet<BlockPos> adjacent = ImmutableSet.of(
				block,
				block.down(),
				block.up(),
				block.west(),
				block.north(),
				block.east(),
				block.south(),
				block.west().down(),
				block.north().down(),
				block.east().down(),
				block.south().down(),
				block.west().up(),
				block.north().up(),
				block.east().up(),
				block.south().up()
			);

		return adjacent;
		//throw new NotImplementedException("");
	}
	
	/**
	 * @param block
	 * @param facing
	 * @return an ImmutableSet of the 3x3 blocks surrounding the provided block, along the facing plane.
	 * 
	 */
	public default ImmutableSet<BlockPos> AdjacentBlocksPlane(BlockPos block, Direction facing){
		ArrayList<BlockPos> planeBlocks = new ArrayList<BlockPos>(9);
		planeBlocks.add( block );
		
		if (facing.equals(Direction.EAST) || facing.equals(Direction.WEST)) {
			planeBlocks.add(block.down());
			planeBlocks.add(block.up());
			planeBlocks.add(block.north());
			planeBlocks.add(block.south());
			planeBlocks.add(block.north().down());
			planeBlocks.add(block.south().down());
			planeBlocks.add(block.north().up());
			planeBlocks.add(block.south().up());
		} else if (facing.equals(Direction.NORTH) || facing.equals(Direction.SOUTH)) {
			planeBlocks.add(block.down());
			planeBlocks.add(block.up());
			planeBlocks.add(block.east());
			planeBlocks.add(block.west());
			planeBlocks.add(block.east().down());
			planeBlocks.add(block.west().down());
			planeBlocks.add(block.east().up());
			planeBlocks.add(block.west().up());
		} else if (facing.equals(Direction.UP) || facing.equals(Direction.DOWN)) {
			planeBlocks.add(block.north());
			planeBlocks.add(block.south());
			planeBlocks.add(block.east());
			planeBlocks.add(block.west());
			planeBlocks.add(block.east().north());
			planeBlocks.add(block.west().north());
			planeBlocks.add(block.east().south());
			planeBlocks.add(block.west().south());
		}
		ImmutableSet<BlockPos> adjacent = ImmutableSet.copyOf(planeBlocks);
		
		return adjacent;
	}
}
