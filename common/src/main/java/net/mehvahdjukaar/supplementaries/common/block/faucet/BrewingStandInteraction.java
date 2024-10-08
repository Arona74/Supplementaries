package net.mehvahdjukaar.supplementaries.common.block.faucet;

import net.mehvahdjukaar.moonlight.api.fluids.SoftFluidStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;

//consume to finish current group
class BrewingStandInteraction implements FaucetSource.Tile, FaucetTarget.Tile {

    @Override
    public FluidOffer getProvidedFluid(Level level, BlockPos pos, Direction dir, BlockEntity tile) {
        if (tile instanceof BrewingStandBlockEntity brewingStand) {
            for (int i = 0; i < 3; i++) {
                ItemStack stack = brewingStand.getItem(i);
                //simulate draining
                var opt = SoftFluidStack.fromItem(stack);
                if (opt != null) {
                    return FluidOffer.of(opt.getFirst());
                }
            }
        }
        return null;
    }

    @Override
    public void drain(Level level, BlockPos pos, Direction dir, BlockEntity tile, int amount) {
        if (tile instanceof BrewingStandBlockEntity brewingStand) {
            for (int i = 0; i < 3; i++) {
                ItemStack stack = brewingStand.getItem(i);
                //simulate draining
                var opt = SoftFluidStack.fromItem(stack);
                if (opt != null) {
                    brewingStand.setItem(i, stack.getItem().getCraftingRemainingItem().getDefaultInstance());
                    tile.setChanged();
                    return;
                }
            }
        }
    }

    @Override
    public Integer fill(Level level, BlockPos pos, BlockEntity tile, SoftFluidStack fluid, int minAmount) {
        if (tile instanceof BrewingStandBlockEntity brewingStand) {
            for (int i = 0; i < 3; i++) {
                ItemStack stack = brewingStand.getItem(i);
                var filled = fluid.toItem(stack, true);
                if (filled != null) {
                    ItemStack filledItem = filled.getFirst();
                    brewingStand.setItem(i, ItemStack.EMPTY);
                    if (brewingStand.canPlaceItem(i, filledItem)) {
                        brewingStand.setItem(i, filled.getFirst().copy());
                        tile.setChanged();
                        level.sendBlockUpdated(pos, tile.getBlockState(), tile.getBlockState(), 3);
                        return filled.getSecond().getAmount();
                    } else brewingStand.setItem(i, stack);
                }
            }
        }
        return null;
    }
}

