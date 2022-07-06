package net.mehvahdjukaar.supplementaries.common.items;


import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class BlackboardItem extends BlockItem {
    public BlackboardItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        CompoundTag tag = pStack.getTagElement("BlockEntityTag");
        if (tag != null && tag.contains("Waxed")) {
            pTooltip.add((Component.translatable("message.supplementaries.blackboard")).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack pStack) {
        CompoundTag cmp = pStack.getTagElement("BlockEntityTag");
        if (cmp != null && cmp.contains("Pixels")) {
            return Optional.of(new BlackboardTooltip(cmp.getLongArray("Pixels")));
        }
        return Optional.empty();
    }

    public record BlackboardTooltip(long[] packed) implements TooltipComponent {
    }
}
