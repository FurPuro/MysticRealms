package ru.furpuro.mysticrealms.items

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Material
import ru.furpuro.mysticrealms.Mysticrealms
import ru.hollowhorizon.hc.client.utils.rl
import ru.hollowhorizon.hc.common.objects.blocks.BlockItemProperties
import ru.hollowhorizon.hc.common.registry.AutoModelType
import ru.hollowhorizon.hc.common.registry.HollowRegistry

object BlockRegistry : HollowRegistry() {
    val DEBILIUM_ORE = register("${Mysticrealms.MOD_ID}:debilium_ore".rl, AutoModelType.CUBE_ALL) {
        OreBlock()
    }
}
class OreBlock : Block(Properties.of(Material.STONE).requiresCorrectToolForDrops()),BlockItemProperties {
    override val properties = Item.Properties().tab(CreativeModeTab.TAB_MISC)
}