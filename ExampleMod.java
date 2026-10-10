package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {

    public static final String MOD_ID = "aigodmode";
    public static final Logger LOGGER =
            LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {

                dispatcher.register(
                    CommandManager.literal("ai")
                        .then(CommandManager.literal("help")
                            .executes(context -> {
                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "§bAI GOD MODE COMMANDS\n" +
                                        "§a/ai help §7- Show commands\n" +
                                        "§a/ai heal §7- Restore health\n" +
                                        "§a/ai food §7- Restore hunger"
                                    ),
                                    false
                                );
                                return 1;
                            })
                        )
                        .then(CommandManager.literal("heal")
                            .executes(context -> {
                                var player =
                                    context.getSource().getPlayerOrThrow();

                                player.setHealth(player.getMaxHealth());

                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "§aAI: Health restored!"
                                    ),
                                    false
                                );
                                return 1;
                            })
                        )
                        .then(CommandManager.literal("food")
                            .executes(context -> {
                                var player =
                                    context.getSource().getPlayerOrThrow();

                                player.getHungerManager()
                                      .setFoodLevel(20);

                                player.getHungerManager()
                                      .setSaturationLevel(20.0F);

                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "§aAI: Hunger restored!"
                                    ),
                                    false
                                );
                                return 1;
                            })
                        )
                );
            }
        );

        LOGGER.info("AI GOD MODE loaded!");
    }
}nclick="openSettings()">