package pt.marcos.pokerwizardpro.core.data.training

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrainingAttemptDao {
    @Insert suspend fun insertAttempt(item: TrainingAttemptEntity)

    @Query("SELECT COUNT(*) FROM training_attempts")
    suspend fun countAttempts(): Int

    @Query("SELECT COUNT(*) FROM training_attempts WHERE isCorrect = 1")
    suspend fun countCorrectAttempts(): Int
}