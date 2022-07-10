package com.krakenus00.hookah_boss

import net.minecraft.client.Minecraft
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.registerObject
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(HookahBossMod.ID)
object HookahBossMod {
    const val ID = "hookah_boss"
    @JvmField val LOGGER: Logger = LogManager.getLogger(ID)
    @JvmField val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, HookahBossMod.ID)
    @JvmField val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, HookahBossMod.ID)

    // Mod blocks
    val BLOCK_1 by BLOCKS.registerObject("hookah_pot") { HookahPot() }
    // Mod items

    init {
        LOGGER.log(Level.INFO, "Initializing Hookah BOSS...")
        BLOCKS.register(MOD_BUS)
        ITEMS.register(MOD_BUS)
        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })
        println(obj)
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}
