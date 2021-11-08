package rmc.mixins.advent_ascension_newmagic.inject;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.library.misc.AoATeleporter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Function;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = AoATeleporter.class)
public abstract class AoATeleporterMixin {

    @Inject(method = "Lnet/tslat/aoa3/library/misc/AoATeleporter;findSuitablePortalLocation(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/math/BlockPos;",
            remap = false,
            cancellable = true,
            at = @At(value = "RETURN"))
    private void correctPortalSpawn(World world, Entity entity, CallbackInfoReturnable<BlockPos> mixin) {
        BlockPos current = mixin.getReturnValue();
        if (world.dimension() != World.OVERWORLD)
            mixin.setReturnValue(new BlockPos(current.getX() / 2, current.getY(), current.getZ() / 2));
        else
            mixin.setReturnValue(new BlockPos(current.getX() * 2, current.getY(), current.getZ() * 2));
    }

}
