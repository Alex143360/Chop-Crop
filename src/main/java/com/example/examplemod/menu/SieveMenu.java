package com.example.examplemod.menu;

import com.example.examplemod.BlockInit;
import com.example.examplemod.blockentity.SieveBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import com.example.examplemod.MenuInit;

public class SieveMenu extends AbstractContainerMenu {
    public final SieveBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    // Конструктор для клиентской части
    public SieveMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    // Конструктор для серверной части
    public SieveMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(MenuInit.SIEVE_MENU.get(), pContainerId);
        checkContainerSize(inv, 3);
        blockEntity = (SieveBlockEntity) entity;
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        // Добавляем инвентарь игрока
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

      // Слоты сита
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 30, 22));  // Ягода (без изменений)
            this.addSlot(new SlotItemHandler(handler, 1, 124, 14)); // Бутылочки (сдвинули на 1 пиксель влево)
            this.addSlot(new SlotItemHandler(handler, 2, 124, 32)); // Сок (сдвинули на 1 пиксель влево)
        });
    }

  private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 81 + i * 18)); // Y теперь 81
            }
        }
    }

   private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 140)); // X теперь 8
        }
    }
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        // Заглушка для Shift-клика, чтобы игра не вылетала при быстром перемещении
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            return slot.getItem().copy();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.levelAccess, pPlayer, BlockInit.SIEVE.get());
    }
}