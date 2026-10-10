package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.server.network.ServerPlayerEntity;

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

                // AI HELP COMMAND
                dispatcher.register(
                    CommandManager.literal("ai")
                        .then(CommandManager.literal("help")
                            .executes(context -> {

                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "===== AI GOD MODE =====\n" +
                                        "/ai help - Show commands\n" +
                                        "/ai heal - Restore health\n" +
                                        "/ai food - Restore hunger\n" +
                                        "/ai god - Become invincible"
                                    ),
                                    false
                                );

                                return 1;
                            })
                        )

                        // HEAL COMMAND
                        .then(CommandManager.literal("heal")
                            .executes(context -> {

                                ServerPlayerEntity player =
                                    context.getSource().getPlayerOrThrow();

                                player.setHealth(
                                    player.getMaxHealth()
                                );

                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "AI: Health restored!"
                                    ),
                                    false
                                );

                                return 1;
                            })
                        )

                        // FOOD COMMAND
                        .then(CommandManager.literal("food")
                            .executes(context -> {

                                ServerPlayerEntity player =
                                    context.getSource().getPlayerOrThrow();

                                player.getHungerManager()
                                    .setFoodLevel(20);

                                player.getHungerManager()
                                    .setSaturationLevel(20.0F);

                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "AI: Hunger restored!"
                                    ),
                                    false
                                );

                                return 1;
                            })
                        )

                        // GOD COMMAND
                        .then(CommandManager.literal("god")
                            .executes(context -> {

                                ServerPlayerEntity player =
                                    context.getSource().getPlayerOrThrow();

                                player.setInvulnerable(true);

                                context.getSource().sendFeedback(
                                    () -> Text.literal(
                                        "AI GOD MODE ACTIVATED!"
                                    ),
                                    false
                                );

                                return 1;
                            })
                        )
                );
            }
        );

        LOGGER.info("AI GOD MODE loaded successfully!");
    }
}
