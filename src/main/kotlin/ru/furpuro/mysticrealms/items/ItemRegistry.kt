package ru.furpuro.mysticrealms.items

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import ru.furpuro.mysticrealms.Mysticrealms
import ru.hollowhorizon.hc.client.utils.rl
import ru.hollowhorizon.hc.common.registry.HollowRegistry

object ItemRegistry: HollowRegistry() {
    val DEBILIUM by register("${Mysticrealms.MOD_ID}:debilium".rl) {
        object: Item(
            Item.Properties()
                .tab(CreativeModeTab.TAB_MISC)
        ) {}
    }
    val RAW_DEBILIUM by register("${Mysticrealms.MOD_ID}:raw_debilium".rl) {
        object: Item(
            Item.Properties()
                .tab(CreativeModeTab.TAB_MISC)
        ) {}
    }
}