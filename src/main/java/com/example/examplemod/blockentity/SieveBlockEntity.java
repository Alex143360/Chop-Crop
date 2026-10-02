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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import com.example.examplemod.ItemInit;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SieveBlockEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    
    // Добавь эти две строчки:
    public int progress = 0;
    public int maxProgress = 60; // 100 тиков = 5 секунд реального времени
    
    // ... тут идет твой остальной код (itemHandler, конструктор, методы tick и т.д.) ...
    // Создаем инвентарь на 3 ячейки
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
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
    private boolean hasRecipe() {
        boolean hasInput = this.itemHandler.getStackInSlot(0).getItem() == ItemInit.STRAWBERRY.get() 
                        && this.itemHandler.getStackInSlot(1).getItem() == ItemInit.JUICE_BOTTLE.get();
        if (!hasInput) {
            return false;
        }

        // Проверяем, есть ли место для свежего сока (Слот 2)
        ItemStack freshSlot = this.itemHandler.getStackInSlot(2);
        boolean canFitFresh = freshSlot.isEmpty() || (freshSlot.getItem() == ItemInit.FRESH_STRAWBERRY_JUICE.get() && freshSlot.getCount() < freshSlot.getMaxStackSize());

        // Проверяем, есть ли место для семян (Слот 3)
        ItemStack seedSlot = this.itemHandler.getStackInSlot(3);
        boolean canFitSeeds = seedSlot.isEmpty() || (seedSlot.getItem() == ItemInit.STRAWBERRY_SEEDS.get() && seedSlot.getCount() < seedSlot.getMaxStackSize());

        // Проверяем, есть ли место для забродившего сока (Слот 4)
        ItemStack fermentedSlot = this.itemHandler.getStackInSlot(4);
        boolean canFitFermented = fermentedSlot.isEmpty() || (fermentedSlot.getItem() == ItemInit.FERMENTED_STRAWBERRY_JUICE.get() && fermentedSlot.getCount() < fermentedSlot.getMaxStackSize());

        return canFitFresh && canFitSeeds && canFitFermented;
    }

    private void craftItem() {
        this.itemHandler.extractItem(0, 1, false);
        this.itemHandler.extractItem(1, 1, false);

        // Всегда выдаем 1 семечко клубники в слот 3
        this.itemHandler.insertItem(3, new ItemStack(ItemInit.STRAWBERRY_SEEDS.get(), 1), false);

        // 50% шанс: выбираем, какой сок получится
        boolean isFresh = this.level.random.nextInt(100) < 40;
        if (isFresh) {
            this.itemHandler.insertItem(2, new ItemStack(ItemInit.FRESH_STRAWBERRY_JUICE.get(), 1), false); // В слот 2
        } else {
            this.itemHandler.insertItem(4, new ItemStack(ItemInit.FERMENTED_STRAWBERRY_JUICE.get(), 1), false); // В слот 4
        }
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        // Логика работает только на сервере
        if (pLevel.isClientSide()) {
            return;
        }

        if (hasRecipe()) {
            this.progress++;
            setChanged(pLevel, pPos, pState); // Сохраняем состояние
            
            // Если процесс достиг максимума (например, this.maxProgress = 100)
            if (this.progress >= this.maxProgress) {
                craftItem();
                this.progress = 0; // Сбрасываем шкалу после крафта
                setChanged(pLevel, pPos, pState);
            }
        } else {
            // Если игрок забрал ягоду во время процесса — сбрасываем прогресс до нуля
            if (this.progress != 0) {
                this.progress = 0;
                setChanged(pLevel, pPos, pState);
            }
        }
    }
}