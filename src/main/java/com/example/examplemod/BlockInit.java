package com.example.examplemod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.example.examplemod.block.SieveBlock;

public class BlockInit {
    // Список для наших новых блоков
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "examplemod");

    // Регистрируем сам блок клубничной грядки
    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            () -> new CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));
            // Регистрируем блок сита
    public static final RegistryObject<Block> SIEVE = BLOCKS.register("sieve",
            () -> new SieveBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
}