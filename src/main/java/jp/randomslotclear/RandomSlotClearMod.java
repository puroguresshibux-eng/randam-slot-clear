package jp.randomslotclear;

import com.mojang.brigadier.arguments.EntityArgument;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomSlotClearMod implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(CommandManager.literal("randomclear")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(context -> clearOne(context.getSource().getPlayerOrThrow()))
                .then(CommandManager.argument("player", EntityArgument.player())
                    .executes(context -> clearOne(EntityArgument.getPlayer(context, "player"))))
            )
        );
    }

    private static int clearOne(ServerPlayerEntity player) {
        // Main inventory + hotbar only; armor and offhand excluded.
        List<Integer> occupiedSlots = new ArrayList<>();
        for (int slot = 0; slot < 36; slot++) {
            if (!player.getInventory().getStack(slot).isEmpty()) {
                occupiedSlots.add(slot);
            }
        }

        if (occupiedSlots.isEmpty()) {
            player.sendMessage(Text.literal("§cインベントリに消せるアイテムがありません。"), false);
            return 0;
        }

        int selectedSlot = occupiedSlots.get(player.getRandom().nextInt(occupiedSlots.size()));
        ItemStack stack = player.getInventory().getStack(selectedSlot);
        stack.decrement(1);
        player.getInventory().markDirty();
        player.sendMessage(Text.literal("§eランダムなスロットからアイテムを1個消しました！"), false);
        return 1;
    }
}
