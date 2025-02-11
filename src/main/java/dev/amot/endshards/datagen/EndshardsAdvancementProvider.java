package dev.amot.endshards.datagen;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.advancements.criteria.EndshardsCriterion;
import dev.amot.endshards.blocks.EndshardsBlocks;
import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.items.EndshardsItems;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.items.SculkGear;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static dev.amot.endshards.Endshards.modid;

public class EndshardsAdvancementProvider extends FabricAdvancementProvider {
    protected EndshardsAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        final RegistryWrapper.Impl<Item> itemLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);

        AdvancementEntry root = Advancement.Builder.create()
                .display(
                        EndshardsItems.ENDSHARD,
                        Text.literal("Endshards"),
                        Text.literal("The beginning of something awesome"),
                        Identifier.of(modid, "textures/block/ender_block.png"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("tick", TickCriterion.Conditions.createTick())
                .build(consumer, modid + ":root");

        AdvancementEntry infusionCore = Advancement.Builder.create()
                .parent(root)
                .display(
                        EndshardsItems.INFUSION_CORE,
                        Text.literal("Is this a Poké Ball?"),
                        Text.literal("Craft an Infusion Core"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("picked_up_infusion_core", InventoryChangedCriterion.Conditions.items(EndshardsItems.INFUSION_CORE))
                .build(consumer, modid + ":infusion_core");

        AdvancementEntry infusionCoreEssence = Advancement.Builder.create()
                .parent(infusionCore)
                .display(
                        EndshardsItems.INFUSION_CORE_ENDER,
                        Text.literal("It's a Poké Ball!"),
                        Text.literal("Encase the energy of a powerful mob in an Infusion Core"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("picked_up_infused_core", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().items(
                                itemLookup,
                                EndshardsItems.INFUSION_CORE_ENDER,
                                EndshardsItems.INFUSION_CORE_NETHERITE,
                                EndshardsItems.INFUSION_CORE_SCULK,
                                EndshardsItems.INFUSION_CORE_NIGHTMARE
                        ).build()))
                .build(consumer, modid + ":infusion_core_essence");

        AdvancementEntry endshard = Advancement.Builder.create()
                .parent(root)
                .display(
                        EndshardsBlocks.STRANGE_CRYSTAL,
                        Text.literal("A Strange Crystal"),
                        Text.literal("Obtain an Endshard"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("picked_up_endshard", InventoryChangedCriterion.Conditions.items(EndshardsItems.ENDSHARD))
                .build(consumer, modid + ":endshard");

        AdvancementEntry infusedIngot = Advancement.Builder.create()
                .parent(endshard)
                .display(
                        EndshardsItems.ENDER_INGOT_INFUSED,
                        Text.literal("It's... not a Poké Ball"),
                        Text.literal("Infuse an Ingot"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("picked_up_infused_ingot", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().items(
                                itemLookup,
                                EndshardsItems.ENDER_INGOT_INFUSED,
                                EndshardsItems.NETHERITE_INGOT_INFUSED,
                                EndshardsItems.SCULK_GEM_INFUSED,
                                EndshardsItems.NIGHTMARE_PEARL_INFUSED
                        ).build()))
                .build(consumer, modid + ":infused_ingot");

        AdvancementEntry enderSwordWarp = Advancement.Builder.create()
                .parent(infusedIngot)
                .display(
                        EnderGear.ENDER_SWORD,
                        Text.literal("Banished to the Shadow Realm"),
                        Text.literal("Send an enemy to the void"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("ender_sword_warp", EndshardsCriteria.ENDER_SWORD_WARP_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":ender_sword_warp");

        AdvancementEntry enderArmorFall = Advancement.Builder.create()
                .parent(infusedIngot)
                .display(
                        EnderGear.ENDER_CHESTPLATE,
                        Text.literal("Humpty Dumpty"),
                        Text.literal("Have a great fall while wearing Ender Armor"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("ender_armor_fall", EndshardsCriteria.ENDER_ARMOR_FALL_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":ender_armor_fall");

        AdvancementEntry enderArmorPlayedSelf = Advancement.Builder.create()
                .parent(enderArmorFall)
                .display(
                        EnderGear.ENDER_BOOTS,
                        Text.literal("Congrats, You Played Yourself"),
                        Text.literal("Warp a mob right before taking preventable critical fall damage"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("ender_armor_played_self", EndshardsCriteria.ENDER_ARMOR_PLAYED_SELF_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":ender_armor_played_self");

        AdvancementEntry enderToolWarp = Advancement.Builder.create()
                .parent(infusedIngot)
                .display(
                        EnderGear.ENDER_PICKAXE,
                        Text.literal("Beam Me Up, Scotty"),
                        Text.literal("Have a block be warped to your inventory"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("ender_tool_warp", EndshardsCriteria.ENDER_TOOL_WARP_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":ender_tool_warp");

        AdvancementEntry netheriteIngot = Advancement.Builder.create()
                .parent(enderToolWarp)
                .display(
                        Items.NETHERITE_INGOT,
                        Text.literal("It Needs to be Infused"),
                        Text.literal("Obtain a Netherite Ingot"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("picked_up_netherite_ingot", InventoryChangedCriterion.Conditions.items(Items.NETHERITE_INGOT))
                .build(consumer, modid + ":netherite_ingot");

        AdvancementEntry netheriteSwordFlame = Advancement.Builder.create()
                .parent(netheriteIngot)
                .display(
                        Items.NETHERITE_SWORD,
                        Text.literal("Smells Like Bacon"),
                        Text.literal("Light a mob on fire without touching it"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("netherite_sword_flame", EndshardsCriteria.NETHERITE_SWORD_FLAME_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":netherite_sword_flame");

        AdvancementEntry netheriteSwordSacrifice = Advancement.Builder.create()
                .parent(netheriteSwordFlame)
                .display(
                        Items.COOKED_MUTTON,
                        Text.literal("Sacrificial Rite"),
                        Text.literal("Conduct a mass sacrifice of 100 creatures with the Netherite Sword"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("netherite_sword_sacrifice", EndshardsCriteria.NETHERITE_SWORD_SACRIFICE_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":netherite_sword_sacrifice");

        AdvancementEntry netheriteArmorProtect = Advancement.Builder.create()
                .parent(netheriteIngot)
                .display(
                        Items.NETHERITE_CHESTPLATE,
                        Text.literal("Pls Help"),
                        Text.literal("Be given some extra protection by your armor"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("netherite_armor_protect", EndshardsCriteria.NETHERITE_ARMOR_PROTECT_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":netherite_armor_protect");

        AdvancementEntry netheriteToolAutosmelt = Advancement.Builder.create()
                .parent(netheriteIngot)
                .display(
                        Items.NETHERITE_PICKAXE,
                        Text.literal("Personal Forge"),
                        Text.literal("Autosmelt a mined block"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("netherite_tool_autosmelt", EndshardsCriteria.NETHERITE_TOOL_AUTOSMELT_CRITERION.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":netherite_tool_autosmelt");

        AdvancementEntry sculkGem = Advancement.Builder.create()
                .parent(netheriteIngot)
                .display(
                        EndshardsItems.SCULK_GEM,
                        Text.literal("This isn't Right..."),
                        Text.literal("Obtain a Sculk Gem"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("picked_up_sculk_gem", InventoryChangedCriterion.Conditions.items(EndshardsItems.SCULK_GEM))
                .build(consumer, modid + ":sculk_gem");

        AdvancementEntry sculkSwordEnthrall = Advancement.Builder.create()
                .parent(sculkGem)
                .display(
                        SculkGear.SCULK_SWORD,
                        Text.literal("The Gamesters of Triskelion"),
                        Text.literal("Enthrall an enemy with your sword"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("sculk_sword_enthrall", EndshardsCriteria.SCULK_SWORD_ENTHRALL.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":sculk_sword_enthrall");

        AdvancementEntry sculkArmorLight = Advancement.Builder.create()
                .parent(sculkGem)
                .display(
                        SculkGear.SCULK_CHESTPLATE,
                        Text.literal("A Glare's Best Friend"),
                        Text.literal("Have your armor light your way"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("sculk_armor_light", EndshardsCriteria.SCULK_ARMOR_LIGHT.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":sculk_armor_light");

        AdvancementEntry sculkToolXp = Advancement.Builder.create()
                .parent(sculkGem)
                .display(
                        SculkGear.SCULK_PICKAXE,
                        Text.literal("Mending++"),
                        Text.literal("Get XP from a basic block"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("sculk_tool_xp", EndshardsCriteria.SCULK_TOOL_XP.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":sculk_tool_xp");

        AdvancementEntry sculkToolMendingBreak = Advancement.Builder.create()
                .parent(sculkToolXp)
                .display(
                        SculkGear.SCULK_HOE,
                        Text.literal("Rock Bottom"),
                        Text.literal("Break your Sculk tool despite Mending"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("sculk_tool_mending_break", EndshardsCriteria.SCULK_TOOL_MENDING_BREAK.create(new EndshardsCriterion.Conditions(Optional.empty())))
                .build(consumer, modid + ":sculk_tool_mending_break");
    }
}
