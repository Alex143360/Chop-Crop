package com.example.examplemod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "examplemod");

    // Наша еда - клубника
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build()
            )));
            

    // Наши семена
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(BlockInit.STRAWBERRY_CROP.get(), new Item.Properties()));
            public static final RegistryObject<Item> JUICE_BOTTLE = ITEMS.register("juice_bottle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRESH_STRAWBERRY_JUICE = ITEMS.register("fresh_strawberry_juice",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FERMENTED_STRAWBERRY_JUICE = ITEMS.register("fermented_strawberry_juice",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_STRAWBERRY_SEEDS = ITEMS.register("raw_strawberry_seeds",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRAWBERRY_FERTILIZER = ITEMS.register("strawberry_fertilizer",
            () -> new Item(new Item.Properties()));
            public static final RegistryObject<Item> SIEVE = ITEMS.register("sieve",
            () -> new BlockItem(BlockInit.SIEVE.get(), new Item.Properties()));
            public static final RegistryObject<Item> GRAPE = ITEMS.register("grape",
            () -> new Item(new Item.Properties()));
}