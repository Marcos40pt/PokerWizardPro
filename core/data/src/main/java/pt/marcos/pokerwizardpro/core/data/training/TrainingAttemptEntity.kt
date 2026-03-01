package pt.marcos.pokerwizardpro.core.data.training

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "training_attempts",
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
data class TrainingAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val scenarioId: String,
    val selectedOptionId: String,
    val isCorrect: Boolean,
    val timestampMs: Long,
)