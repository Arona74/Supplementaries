package net.mehvahdjukaar.supplementaries.common.block.faucet;

import net.mehvahdjukaar.moonlight.api.fluids.BuiltInSoftFluids;
import net.mehvahdjukaar.moonlight.api.fluids.SoftFluidStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

class MudInteraction implements FaucetTarget.BlState {

    @Override
    public Integer fill(Level level, BlockPos pos, BlockState state, SoftFluidStack fluid, int minAmount) {
        if (state.is(Blocks.DIRT)) {
            if (fluid.is(BuiltInSoftFluids.WATER.get())) {
                level.setBlock(pos, Blocks.MUD.defaultBlockState(), 3);
                return minAmount;
            }
            return 0;
        }
        return null;
    }
}
