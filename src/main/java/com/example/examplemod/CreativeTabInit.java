package com.example.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabInit {
    // Создаем регистратор для вкладок
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "examplemod");

    // Создаем саму вкладку
    public static final RegistryObject<CreativeModeTab> MOD_TAB = TABS.register("mod_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.examplemod.mod_tab"))
            .icon(() -> new ItemStack(ItemInit.STRAWBERRY.get())) // Иконка вкладки
            .displayItems((parameters, output) -> {
                // Сюда добавляем предметы, которые должны лежать во вкладке
                output.accept(ItemInit.STRAWBERRY.get());
                output.accept(ItemInit.STRAWBERRY_SEEDS.get());
                output.accept(ItemInit.JUICE_BOTTLE.get());
                output.accept(ItemInit.FRESH_STRAWBERRY_JUICE.get());
                output.accept(ItemInit.FERMENTED_STRAWBERRY_JUICE.get());
                output.accept(ItemInit.RAW_STRAWBERRY_SEEDS.get());
                output.accept(ItemInit.STRAWBERRY_FERTILIZER.get());
                output.accept(ItemInit.SIEVE.get());
                output.accept(ItemInit.GRAPE.get());
                output.accept(ItemInit.PLUM.get());
                output.accept(ItemInit.APPLE.get());
                output.accept(ItemInit.MEDLAR.get());
                output.accept(ItemInit.PEAR.get());
                output.accept(ItemInit.PINEAPPLE.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}