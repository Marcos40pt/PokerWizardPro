package pt.marcos.pokerwizardpro.core.data.training

import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.marcos.pokerwizardpro.core.domain.training.Street
import pt.marcos.pokerwizardpro.core.domain.training.TrainingMode

@Entity(tableName = "training_scenarios")
data class TrainingScenarioEntity(
    @PrimaryKey val id: String,
    val mode: TrainingMode,
    val street: Street,
    val conceptTag: String,

    val positions: String,
    val stacksBb: Int,
    val blinds: String,
    val potBb: Int,
    val board: String,
    val preAction: String,

    val correctOptionId: String,
    val explanation: String,
    val heuristic: String,
)