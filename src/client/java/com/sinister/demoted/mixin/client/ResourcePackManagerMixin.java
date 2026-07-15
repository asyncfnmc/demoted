package com.sinister.demoted.mixin.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.PackSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Moves server resource packs to the lowest priority directly above vanilla. */
@Mixin(value = PackRepository.class, priority = 500)
public abstract class ResourcePackManagerMixin {
    @Inject(method = "rebuildSelected", at = @At("RETURN"), cancellable = true)
    private void demoted$moveServerPacksAboveDefault(
            Collection<String> enabledNames,
            CallbackInfoReturnable<List<Pack>> cir) {
        List<Pack> original = cir.getReturnValue();
        if (original == null || original.size() < 2) {
            return;
        }

        List<Pack> serverPacks = new ArrayList<>();
        List<Pack> otherPacks = new ArrayList<>(original.size());
        for (Pack pack : original) {
            if (pack.getPackSource() == PackSource.SERVER) {
                serverPacks.add(pack);
            } else {
                otherPacks.add(pack);
            }
        }

        if (serverPacks.isEmpty()) {
            return;
        }

        int defaultIndex = findDefaultPackIndex(otherPacks);
        if (defaultIndex < 0) {
            return;
        }

        otherPacks.addAll(defaultIndex + 1, serverPacks);
        if (!otherPacks.equals(original)) {
            cir.setReturnValue(otherPacks);
        }
    }

    private static int findDefaultPackIndex(List<Pack> packs) {
        for (int i = 0; i < packs.size(); i++) {
            String id = packs.get(i).getId();
            if ("vanilla".equals(id) || "minecraft".equals(id) || "default".equals(id)) {
                return i;
            }
        }

        for (int i = 0; i < packs.size(); i++) {
            Pack pack = packs.get(i);
            if (pack.isFixedPosition() && pack.getPackSource() == PackSource.BUILT_IN) {
                return i;
            }
        }
        return -1;
    }
}
