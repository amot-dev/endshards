package dev.amot.endshards.advancements.criteria;

import com.google.gson.JsonObject;
import dev.amot.endshards.Endshards;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class EndshardsCriterion extends AbstractCriterion<EndshardsCriterion.Conditions> {
    public EndshardsCriterion(String identifier){
        this.ID = new Identifier(Endshards.modid, identifier);
    }

    Identifier ID;
    public Identifier getId() {
        return ID;
    }

    public EndshardsCriterion.Conditions conditionsFromJson(JsonObject jsonObject, EntityPredicate.Extended extended, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer) {
        return new EndshardsCriterion.Conditions(extended);
    }

    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, (conditions) -> true);
    }

    public class Conditions extends AbstractCriterionConditions {
        public Conditions(EntityPredicate.Extended player) {
            super(EndshardsCriterion.this.ID, player);
        }

        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            return super.toJson(predicateSerializer);
        }
    }

}
