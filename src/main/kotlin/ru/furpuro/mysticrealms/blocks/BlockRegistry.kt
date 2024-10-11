package ru.furpuro.mysticrealms.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Direction.Axis
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.levelgen.feature.stateproviders.RotatedBlockProvider
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.MaterialColor
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import ru.furpuro.mysticrealms.Mysticrealms
import ru.hollowhorizon.hc.client.utils.rl
import ru.hollowhorizon.hc.common.objects.blocks.BlockItemProperties
import ru.hollowhorizon.hc.common.registry.AutoModelType
import ru.hollowhorizon.hc.common.registry.HollowRegistry

object BlockRegistry : HollowRegistry() {
    val DEBILIUM_ORE = register("${Mysticrealms.MOD_ID}:debilium_ore".rl, AutoModelType.CUBE_ALL) {
        Block(
            Properties.of(Material.STONE)
                .color(MaterialColor.STONE)
                .requiresCorrectToolForDrops()
                .strength(2f)
                .sound(SoundType.STONE)
            ,
            Item.Properties()
                .tab(CreativeModeTab.TAB_MISC)
        )
    }
    val DEBILIUM_BLOCK = register("${Mysticrealms.MOD_ID}:debilium_block".rl, AutoModelType.CUBE_ALL) {
        Block(
            Properties.of(Material.METAL)
                .requiresCorrectToolForDrops()
                .strength(1.5f)
                .sound(SoundType.METAL)
            ,
            Item.Properties()
                .tab(CreativeModeTab.TAB_MISC)
        )
    }
    val DEEPSLATE_DEBILIUM_ORE = register("${Mysticrealms.MOD_ID}:deepslate_debilium_ore".rl, autoModel = null) {
        RotatableBlock(
            Properties.of(Material.STONE)
                .color(MaterialColor.DEEPSLATE)
                .requiresCorrectToolForDrops()
                .strength(4f)
                .sound(SoundType.DEEPSLATE),
            Item.Properties()
                .tab(CreativeModeTab.TAB_MISC)
        )
    }

}

class Block(settings : Properties,itemProperties: Item.Properties): Block(settings), BlockItemProperties {
    override val properties = itemProperties
}

class RotatableBlock(settings : Properties,itemProperties: Item.Properties) : RotatedPillarBlock(settings), BlockItemProperties {
    override val properties = itemProperties

    companion object {
        val AXIS: EnumProperty<Axis> = RotatedPillarBlock.AXIS
    }

    init {
        registerDefaultState(defaultBlockState().setValue(AXIS, Axis.Y))
    }


    override fun rotate(blockState: BlockState, rotation: Rotation): BlockState? {
        return rotateBlock(blockState,rotation)
    }

    fun rotateBlock(blockState:BlockState,rotation:Rotation): BlockState? {
        when (rotation) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> {
                when ((blockState.getValue(AXIS) as Direction.Axis)) {
                    Axis.X -> return blockState.setValue(AXIS, Axis.Z)
                    Axis.Z -> return blockState.setValue(AXIS, Axis.X)
                    else -> return blockState
                }
            }
            else -> return blockState
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS)
    }

    override fun getShape(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos, collisionContext: CollisionContext): VoxelShape {
        return Shapes.block()
    }

    override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(AXIS, blockPlaceContext.clickedFace.axis)
    }
}




