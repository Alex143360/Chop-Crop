package com.example.examplemod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MODID)
public class ExampleMod {
    public static final String MODID = "examplemod";

    public ExampleMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Загружаем в игру блоки и предметы
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        CreativeTabInit.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        MenuInit.MENUS.register(bus);

        // Добавляем слушатель для клиентской части
        bus.addListener(this::clientSetup);
    }

    // Метод настройки клиента стоит отдельно, вне конструктора
    private void clientSetup(final net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent event) {
        net.minecraft.client.gui.screens.MenuScreens.register(MenuInit.SIEVE_MENU.get(), com.example.examplemod.screen.SieveScreen::new);
    }
}