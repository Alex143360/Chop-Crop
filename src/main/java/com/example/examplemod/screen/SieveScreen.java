package com.example.examplemod.screen;

import com.example.examplemod.menu.SieveMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SieveScreen extends AbstractContainerScreen<SieveMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("examplemod", "textures/gui/sieve_gui.png");

    public SieveScreen(SieveMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.imageWidth = 176; 
        this.imageHeight = 190;

        this.titleLabelX = 10000;
        this.inventoryLabelY = 10000;
    }

   @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // 1. Отрисовываем основной фон
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // 2. Отрисовываем горизонтальную шкалу прогресса
       // ВРЕМЕННО заставим игру рисовать шкалу всегда на 100%, даже если сито стоит
        int progressWidth = this.menu.getScaledProgress(); 
        if (progressWidth > 0) {
            // Координаты на экране (подгони на 1-2 пикселя, если не попадет в ячейку)
            int barX = x + 60; 
            int barY = y + 57; 
            
            // Твои точные координаты из Piskel
            int textureU = 10; 
            int textureV = 226; // Берем цвет прямо с самого верха жидкости
            int height = 10; 

            // Жестко указываем игре твои размеры картинки: 256 и 244
            guiGraphics.blit(TEXTURE, barX, barY, textureU, textureV, progressWidth, height, 256, 244);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}