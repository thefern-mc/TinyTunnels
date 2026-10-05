package dev.thefern2.tinytunnels.create.datagen;

import dev.thefern2.tinytunnels.create.TinyTunnelsCreate;
import dev.thefern2.tinytunnels.create.kinetic.KineticMode;
import dev.thefern2.tinytunnels.create.kinetic.KineticTunnelWallBlock;
import dev.thefern2.tinytunnels.create.port.KineticPortBlock;
import dev.thefern2.tinytunnels.create.registry.ModBlocks;
import dev.thefern2.tinytunnels.create.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModModelProvider extends BlockStateProvider {
    public ModModelProvider(PackOutput output, ExistingFileHelper files) {
        super(output, TinyTunnelsCreate.MODID, files);
    }

    @Override
    protected void registerStatesAndModels() {
        // The wall: a brass port with the face letter, per machine face (corner marks on "out").
        getVariantBuilder(ModBlocks.KINETIC_TUNNEL_WALL.get()).forAllStates(state -> {
            String name = "kinetic_tunnel_wall_" + state.getValue(KineticTunnelWallBlock.FACE).getSerializedName()
                    + "_" + state.getValue(KineticTunnelWallBlock.MODE).getSerializedName();
            return ConfiguredModel.builder().modelFile(models().cubeAll(name, modLoc("block/" + name))).build();
        });
        // Make sure every wall model exists even if a state combination is skipped.
        for (Direction face : Direction.values()) {
            for (KineticMode mode : KineticMode.values()) {
                String name = "kinetic_tunnel_wall_" + face.getSerializedName() + "_" + mode.getSerializedName();
                models().cubeAll(name, modLoc("block/" + name));
            }
        }

        // The port: the hand-written kinetic_port model (facing north), with the face letter of the face it's on.
        ModelFile base = models().getExistingFile(modLoc("block/kinetic_port"));
        getVariantBuilder(ModBlocks.KINETIC_PORT.get()).forAllStates(state -> {
            Direction facing = state.getValue(KineticPortBlock.FACING);
            ModelFile model = models().withExistingParent("kinetic_port_" + facing.getSerializedName(), base.getLocation())
                    .texture("front", modLoc("block/machine_port_kinetic_" + facing.getSerializedName()))
                    .texture("particle", modLoc("block/machine_port_kinetic_" + facing.getSerializedName()));
            int x = facing == Direction.UP ? 270 : facing == Direction.DOWN ? 90 : 0;
            int y = facing.getAxis().isHorizontal() ? (int) (facing.getOpposite().toYRot()) % 360 : 0;
            return ConfiguredModel.builder().modelFile(model).rotationX(x).rotationY(y).build();
        });

        itemModels().basicItem(ModItems.KINETIC_TUNNEL.get());
        // itemModels().withExistingParent("kinetic_port", modLoc("block/kinetic_port_north"));
    }
}
