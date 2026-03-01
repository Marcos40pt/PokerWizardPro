package pt.marcos.pokerwizardpro.core.data.training

import androidx.room.Embedded
import androidx.room.Relation

data class ScenarioWithOptions(
    @Embedded val scenario: TrainingScenarioEntity,
    @Relation(parentColumn = "id", entityColumn = "scenarioId")
    val options: List<TrainingOptionEntity>,
)