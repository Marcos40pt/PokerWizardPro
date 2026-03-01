package pt.marcos.pokerwizardpro.core.data.db

import androidx.room.TypeConverter
import pt.marcos.pokerwizardpro.core.domain.training.Street
import pt.marcos.pokerwizardpro.core.domain.training.TrainingMode

class RoomConverters {
    @TypeConverter fun trainingModeToString(value: TrainingMode): String = value.name
    @TypeConverter fun stringToTrainingMode(value: String): TrainingMode = TrainingMode.valueOf(value)

    @TypeConverter fun streetToString(value: Street): String = value.name
    @TypeConverter fun stringToStreet(value: String): Street = Street.valueOf(value)
}