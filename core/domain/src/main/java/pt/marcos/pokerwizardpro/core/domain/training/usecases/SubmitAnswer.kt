package pt.marcos.pokerwizardpro.core.domain.training.usecases

import pt.marcos.pokerwizardpro.core.domain.training.TrainingRepository

class SubmitAnswer(
    private val repo: TrainingRepository,
) {
    data class Result(val isCorrect: Boolean)

    suspend operator fun invoke(
        scenarioId: String,
        selectedOptionId: String,
        correctOptionId: String,
    ): Result {
        val isCorrect = selectedOptionId == correctOptionId
        repo.submitAttempt(
            scenarioId = scenarioId,
            selectedOptionId = selectedOptionId,
            isCorrect = isCorrect,
        )
        return Result(isCorrect)
    }
}