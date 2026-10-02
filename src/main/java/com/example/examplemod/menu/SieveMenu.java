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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class SieveMenu extends AbstractContainerMenu {
    public final SieveBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data; // Переменная для синхронизации

    // Конструктор для клиента
    public SieveMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    // Конструктор для сервера (здесь мы связываем данные с SieveBlockEntity)
    public SieveMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        this(pContainerId, inv, entity, new ContainerData() {
            @Override
            public int get(int index) {
                if (entity instanceof SieveBlockEntity sieve) {
                    return index == 0 ? sieve.progress : sieve.maxProgress;
                }
                return 0;
            }
            @Override
            public void set(int index, int value) {
                if (entity instanceof SieveBlockEntity sieve) {
                    if (index == 0) sieve.progress = value;
                    if (index == 1) sieve.maxProgress = value;
                }
            }
            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    // Внутренний конструктор, куда сходятся оба предыдущих
    private SieveMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MenuInit.SIEVE_MENU.get(), pContainerId);
        checkContainerSize(inv, 5); // 5 слотов вместо 3
        blockEntity = (SieveBlockEntity) entity;
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 30, 22));  // Ягода
            this.addSlot(new SlotItemHandler(handler, 1, 124, 14)); // Пустые бутылки
            this.addSlot(new SlotItemHandler(handler, 2, 124, 32)); // Свежий сок
            this.addSlot(new SlotItemHandler(handler, 3, 142, 14)); // Семена
            this.addSlot(new SlotItemHandler(handler, 4, 142, 32)); // Забродивший сок
        });

        addDataSlots(data); // Активируем синхронизацию шкалы!
    }

    // Метод для SieveScreen: он вычисляет длину шкалы в пикселях
    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        // Теперь игра знает, что 100% прогресса = 58 пикселей
        int barWidth = 58; 
        return maxProgress != 0 && progress != 0 ? progress * barWidth / maxProgress : 0;
    }
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 81 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 140));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
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