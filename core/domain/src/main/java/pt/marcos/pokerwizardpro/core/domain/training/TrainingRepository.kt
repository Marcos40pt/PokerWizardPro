package pt.marcos.pokerwizardpro.core.domain.training

interface TrainingRepository {
    suspend fun ensureSeededIfEmpty()

    suspend fun getRandomScenario(
        mode: TrainingMode,
        street: Street,
        excludeScenarioId: String? = null,
    ): TrainingScenario?

    suspend fun submitAttempt(
        scenarioId: String,
        selectedOptionId: String,
        isCorrect: Boolean,
        timestampMs: Long = System.currentTimeMillis(),
    )

    suspend fun getAttemptsCount(): Int
    suspend fun getCorrectAttemptsCount(): Int
}