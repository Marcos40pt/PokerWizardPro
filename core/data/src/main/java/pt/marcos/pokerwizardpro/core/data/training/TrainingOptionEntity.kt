package pt.marcos.pokerwizardpro.core.data.training

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "training_options",
    foreignKeys = [
        ForeignKey(
            entity = TrainingScenarioEntity::class,
            parentColumns = ["id"],
            childColumns = ["scenarioId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("scenarioId")],
)
data class TrainingOptionEntity(
    @PrimaryKey val id: String,
    val scenarioId: String,
    val label: String,
    val detail: String? = null,
)