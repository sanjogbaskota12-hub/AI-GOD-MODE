package com.example.aigodmode;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {

                dispatcher.register(CommandManager.literal("aigod")
                    .then(CommandManager.literal("help")
                        .executes(context -> {
                            context.getSource().sendFeedback(
                                () -> Text.literal(
                                    "AI GOD MODE: /aigod help, /aigod heal, /aigod food"
                                ), false
                            );
                            return 1;
                        })
                    )
                    .then(CommandManager.literal("heal")
                        .executes(context -> {
                            var player = context.getSource().getPlayer();
                            player.setHealth(player.getMaxHealth());
                            player.getHungerManager().setFoodLevel(20);
                            context.getSource().sendFeedback(
                                () -> Text.literal("AI GOD MODE: Health restored!"),
                                false
                            );
                            return 1;
                        })
                    )
                    .then(CommandManager.literal("food")
                        .executes(context -> {
                            var player = context.getSource().getPlayer();
                            player.getHungerManager().setFoodLevel(20);
                            player.getHungerManager().setSaturationLevel(20.0f);
                            context.getSource().sendFeedback(
                                () -> Text.literal("AI GOD MODE: Food restored!"),
                                false
                            );
                            return 1;
                        })
                    )
                );
            }
        );
    }
}
