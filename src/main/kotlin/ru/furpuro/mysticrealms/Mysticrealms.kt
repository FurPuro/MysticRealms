package ru.furpuro.mysticrealms

import net.fabricmc.api.ModInitializer
import ru.furpuro.mysticrealms.items.ItemRegistry
import ru.hollowhorizon.hc.common.events.EventBus
import ru.hollowhorizon.hc.common.registry.HollowRegistry

class Mysticrealms : ModInitializer {

    val fabricBus = EventBus

    companion object {
        const val MOD_ID = "mysticrealms"
    }

    override fun onInitialize() {

    }
}