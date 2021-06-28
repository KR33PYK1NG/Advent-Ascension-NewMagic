package rmc.mixins.advent_ascension_newmagic.inject;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoADimensions.AoADimension;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = PortalBlock.class)
public abstract class PortalBlockMixin {

    @Inject(method = "Lnet/tslat/aoa3/block/functional/portal/PortalBlock;getTeleporterForWorld(Lnet/minecraft/world/server/ServerWorld;)Lnet/minecraftforge/common/util/ITeleporter;",
            remap = false,
            cancellable = true,
            at = @At(value = "HEAD"))
    private static void disableSeveralWorlds(ServerWorld world, CallbackInfoReturnable<ITeleporter> mixin) {
        AoADimension dim = AoADimensions.getDim(world.dimension());
        if (dim == AoADimension.LUNALUS
         || dim == AoADimension.CRYSTEVIA
         || dim == AoADimension.MYSTERIUM
         || dim == AoADimension.SHYRELANDS) {
            mixin.setReturnValue(world.getPortalForcer());
        }
    }

}