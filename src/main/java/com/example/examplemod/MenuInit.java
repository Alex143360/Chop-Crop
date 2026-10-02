package com.example.examplemod;

import com.example.examplemod.menu.SieveMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    // Создаем список для регистрации всех меню нашего мода
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, "examplemod");

    // Регистрируем меню именно для сита
    public static final RegistryObject<MenuType<SieveMenu>> SIEVE_MENU =
            MENUS.register("sieve_menu",
                    () -> IForgeMenuType.create(SieveMenu::new));
}