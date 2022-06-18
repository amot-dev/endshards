package dev.amot.endshards.advancements.criteria;

import com.google.gson.JsonObject;
import dev.amot.endshards.EndShards;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class EndShardsCriterion extends AbstractCriterion<EndShardsCriterion.Conditions> {
    public EndShardsCriterion(String identifier){
        this.ID = new Identifier(EndShards.modid, identifier);
    }

    Identifier ID;
    public Identifier getId() {
        return ID;
    }

    public EndShardsCriterion.Conditions conditionsFromJson(JsonObject jsonObject, EntityPredicate.Extended extended, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer) {
        return new EndShardsCriterion.Conditions(extended);
    }

    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, (conditions) -> true);
    }

    public class Conditions extends AbstractCriterionConditions {
        public Conditions(EntityPredicate.Extended player) {
            super(EndShardsCriterion.this.ID, player);
        }

        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            return super.toJson(predicateSerializer);
        }
    }

}
