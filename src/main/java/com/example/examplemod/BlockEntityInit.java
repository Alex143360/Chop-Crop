package com.example.examplemod;

import com.example.examplemod.blockentity.SieveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    // Создаем список для регистрации всех BlockEntity нашего мода
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "examplemod");

    // Регистрируем "мозг" именно для нашего блока сита
    public static final RegistryObject<BlockEntityType<SieveBlockEntity>> SIEVE_BE =
            BLOCK_ENTITIES.register("sieve",
                    () -> BlockEntityType.Builder.of(SieveBlockEntity::new, BlockInit.SIEVE.get()).build(null));
}