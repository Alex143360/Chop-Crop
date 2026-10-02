package com.example.examplemod.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.example.examplemod.BlockEntityInit;

public class SieveBlockEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    // Создаем инвентарь на 3 ячейки
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public SieveBlockEntity(BlockPos pPos, BlockState pBlockState) {
        // Указываем тип нашего BlockEntity (пока будет гореть красным!)
        super(BlockEntityInit.SIEVE_BE.get(), pPos, pBlockState);
    }

    // Эта часть кода позволяет воронкам и трубам из других модов взаимодействовать с ситом
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
        @Override
    public net.minecraft.network.chat.Component getDisplayName() {
        return net.minecraft.network.chat.Component.literal("Сито");
    }

    @Nullable
    @Override
    public net.minecraft.world.inventory.AbstractContainerMenu createMenu(int pContainerId, net.minecraft.world.entity.player.Inventory pPlayerInventory, net.minecraft.world.entity.player.Player pPlayer) {
        return new com.example.examplemod.menu.SieveMenu(pContainerId, pPlayerInventory, this);
    }
    }
