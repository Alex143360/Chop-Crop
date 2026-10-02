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
                    new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).build()
            )));
    public static final RegistryObject<Item> APPLE = ITEMS.register("apple",
        () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(3) // Цифра 3 даст ровно 1.5 целых окорочка
                .saturationMod(0.3F) // Это то, как быстро герой снова проголодается
                .build())));
     public static final RegistryObject<Item> MEDLAR = ITEMS.register("medlar",
        () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(3) // Цифра 3 даст ровно 1.5 целых окорочка
                .saturationMod(0.3F) // Это то, как быстро герой снова проголодается
                .build())));
     public static final RegistryObject<Item> PEAR = ITEMS.register("pear",
        () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(3) // Цифра 3 даст ровно 1.5 целых окорочка
                .saturationMod(0.3F) // Это то, как быстро герой снова проголодается
                .build())));
     public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple",
        () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(3) // Цифра 3 даст ровно 1.5 целых окорочка
                .saturationMod(0.3F) // Это то, как быстро герой снова проголодается
                .build())));      
     public static final RegistryObject<Item> PLUM = ITEMS.register("plum",
        () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(3)
                .saturationMod(0.3F)
                .build())));
     public static final RegistryObject<Item> GRAPE = ITEMS.register("grape",
        () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(3) // Цифра 3 даст ровно 1.5 целых окорочка
                .saturationMod(0.3F) // Это то, как быстро герой снова проголодается
                .build())));


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
            
}