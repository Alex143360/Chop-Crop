package com.example.examplemod.screen;

import com.example.examplemod.menu.SieveMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SieveScreen extends AbstractContainerScreen<SieveMenu> {
    // Указываем точный путь к твоей текстуре интерфейса
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("examplemod", "textures/gui/sieve_gui.png");

    public SieveScreen(SieveMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

   @Override
    protected void init() {
        super.init();
        
        // Указываем игре рисовать картинку большего размера, чтобы низ не обрезался
        this.imageWidth = 176; 
        this.imageHeight = 190; // Если край всё равно чуть срежется, поменяй на 190

        this.titleLabelX = 10000;
        this.inventoryLabelY = 10000;
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        
        // Вычисляем центр экрана
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Отрисовываем основную рамку (берет координаты 0, 0 из левого верхнего угла картинки)
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}