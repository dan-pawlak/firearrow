package com.danpawlak.firearrow.events;

import com.danpawlak.firearrow.FireArrow;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import static net.minecraft.item.FlintAndSteelItem.func_219996_a;
import static net.minecraft.item.FlintAndSteelItem.isUnlitCampfire;

@Mod.EventBusSubscriber(modid = FireArrow.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FireArrowBlockEvent {
    @SubscribeEvent
    public static void FireArrowBlockEvent(ProjectileImpactEvent.Arrow event) {
        FireArrow.LOGGER.info("ProjectileImpactEvent Fired!");
        AbstractArrowEntity arrow = event.getArrow();
        World world = arrow.getEntityWorld();
        RayTraceResult result = event.getRayTraceResult();
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            FireArrow.LOGGER.info("Block was hit!");
            if (arrow.isBurning()) {
                FireArrow.LOGGER.info("Arrow On Fire!");
                BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult)result;
                BlockState blockState = world.getBlockState(blockRayTraceResult.getPos());
                BlockPos position = blockRayTraceResult.getPos();
                if (isUnlitCampfire(blockState)) {
                    world.setBlockState(position, blockState.with(BlockStateProperties.LIT, true),11);
                }
                else {
                    BlockPos face = position.offset(blockRayTraceResult.getFace());
                    BlockState faceBlockState = world.getBlockState(face);
                    BlockState fire = ((FireBlock) Blocks.FIRE).getStateForPlacement(world, face);

                    if (func_219996_a(faceBlockState, world, face)) {
                        world.setBlockState(face, fire, 11);
                    }
                }
            }
        }
    }
}