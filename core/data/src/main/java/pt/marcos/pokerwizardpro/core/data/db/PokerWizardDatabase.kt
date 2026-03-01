package pt.marcos.pokerwizardpro.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pt.marcos.pokerwizardpro.core.data.training.TrainingAttemptDao
import pt.marcos.pokerwizardpro.core.data.training.TrainingAttemptEntity
import pt.marcos.pokerwizardpro.core.data.training.TrainingOptionEntity
import pt.marcos.pokerwizardpro.core.data.training.TrainingScenarioDao
import pt.marcos.pokerwizardpro.core.data.training.TrainingScenarioEntity

@Database(
    entities = [
        TrainingScenarioEntity::class,
        TrainingOptionEntity::class,
        TrainingAttemptEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(RoomConverters::class)
abstract class PokerWizardDatabase : RoomDatabase() {
    abstract fun trainingScenarioDao(): TrainingScenarioDao
    abstract fun trainingAttemptDao(): TrainingAttemptDao
}