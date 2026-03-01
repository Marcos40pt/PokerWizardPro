package pt.marcos.pokerwizardpro.core.data.training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import pt.marcos.pokerwizardpro.core.domain.training.Street
import pt.marcos.pokerwizardpro.core.domain.training.TrainingMode

@Dao
interface TrainingScenarioDao {

    @Query("SELECT COUNT(*) FROM training_scenarios")
    suspend fun countScenarios(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScenarios(items: List<TrainingScenarioEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOptions(items: List<TrainingOptionEntity>)

    @Transaction
    @Query(
        """
        SELECT * FROM training_scenarios
        WHERE mode = :mode
          AND street = :street
          AND (:excludeId IS NULL OR id != :excludeId)
        ORDER BY RANDOM()
        LIMIT 1
        """
    )
    suspend fun getRandomScenarioWithOptions(
        mode: TrainingMode,
        street: Street,
        excludeId: String?,
    ): ScenarioWithOptions?
}