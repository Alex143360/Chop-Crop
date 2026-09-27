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
    }
}