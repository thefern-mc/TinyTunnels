package dev.thefern2.tinytunnels.registry;

import java.util.EnumMap;
import java.util.Map;

import dev.thefern2.tinytunnels.TinyTunnels;
import dev.thefern2.tinytunnels.machine.MachineBlock;
import dev.thefern2.tinytunnels.machine.MachineSize;
import dev.thefern2.tinytunnels.tunnel.RedstoneTunnelWallBlock;
import dev.thefern2.tinytunnels.tunnel.TunnelWallBlock;
import dev.thefern2.tinytunnels.wall.RoomWallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TinyTunnels.MODID);

    public static final Map<MachineSize, DeferredBlock<MachineBlock>> MACHINES = new EnumMap<>(MachineSize.class);

    static {
        for (MachineSize size : MachineSize.values()) {
            MACHINES.put(size, BLOCKS.registerBlock(size.blockId(), p -> new MachineBlock(size, p), p -> p
                    .mapColor(MapColor.METAL)
                    .sound(SoundType.METAL)
                    // Blast-proof: losing the machine item would strand its room.
                    .strength(4f, 1200f)
                    .requiresCorrectToolForDrops()
                    .isRedstoneConductor(ModBlocks::never)));
        }
    }

    // Room walls are unbreakable, drop nothing and can't be pushed; the tunnel wall replaces one of them.
    public static final DeferredBlock<RoomWallBlock> ROOM_WALL = BLOCKS.registerBlock("room_wall", RoomWallBlock::new, ModBlocks::wallProperties);
    public static final DeferredBlock<TunnelWallBlock> TUNNEL_WALL = BLOCKS.registerBlock("tunnel_wall", TunnelWallBlock::new, ModBlocks::wallProperties);
    public static final DeferredBlock<RedstoneTunnelWallBlock> REDSTONE_TUNNEL_WALL = BLOCKS.registerBlock("redstone_tunnel_wall", RedstoneTunnelWallBlock::new, ModBlocks::wallProperties);

    private static BlockBehaviour.Properties wallProperties(BlockBehaviour.Properties p) {
        return p.mapColor(MapColor.WOOL)
                .sound(SoundType.METAL)
                .strength(-1f, 3_600_000f)
                .noLootTable()
                .pushReaction(PushReaction.BLOCK)
                .isValidSpawn((state, level, pos, type) -> false)
                .lightLevel(state -> 15)
                .isRedstoneConductor(ModBlocks::never);
    }

    // Not redstone conductors: chests open underneath, and power doesn't leak through walls or machines by accident.
    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    private ModBlocks() {}
}
